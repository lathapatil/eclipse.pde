/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.internal.core.text;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Stack;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.pde.internal.core.util.CoreUtility;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class DocumentHandler extends DefaultHandler {

	protected FindReplaceDocumentAdapter fFindReplaceAdapter;
	protected Stack fDocumentNodeStack = new Stack();
	protected int fHighestOffset = 0;
	private Locator fLocator;
	private IDocumentNode fLastError;
	private boolean fReconciling;
	
	public DocumentHandler(boolean reconciling) {
		fReconciling = reconciling;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	public void startDocument() throws SAXException {
		fDocumentNodeStack.clear();
		fHighestOffset = 0;
		fLastError = null;
		fFindReplaceAdapter = new FindReplaceDocumentAdapter(getDocument());
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		IDocumentNode parent = fDocumentNodeStack.isEmpty() ? null : (IDocumentNode)fDocumentNodeStack.peek();		
		IDocumentNode node = getDocumentNode(qName, parent);
		node.setXMLTagName(qName);
		try {
			int nodeOffset = getStartOffset(qName);
			node.setOffset(nodeOffset);
			IDocument doc = getDocument();
			int line = doc.getLineOfOffset(nodeOffset);
			node.setLineIndent(node.getOffset() - doc.getLineOffset(line));
			// create attributes
			for (int i = 0; i < attributes.getLength(); i++) {
				String attName = attributes.getQName(i);
				String attValue = attributes.getValue(i);
				IDocumentAttribute attribute = getDocumentAttribute(attName, attValue, node);
				if (attribute != null) {
					IRegion region = getAttributeRegion(attName, attValue, nodeOffset);
					if (region == null) {
						attValue = CoreUtility.getWritableString(attValue);
						region = getAttributeRegion(attName, attValue, nodeOffset);
					}
					if (region != null) {
						attribute.setNameOffset(region.getOffset());
						attribute.setNameLength(attName.length());
						attribute.setValueOffset(region.getOffset() + region.getLength() - 1 - attValue.length());
						attribute.setValueLength(attValue.length());
					}
					node.setXMLAttribute(attribute);
				}
			}
			removeOrphanAttributes(node);
		} catch (BadLocationException e) {
		}
		appendChildToParent(parent, node);
		fDocumentNodeStack.push(node);
	}
	
	protected void appendChildToParent(IDocumentNode parent, IDocumentNode child) {
		if (parent != null && child != null && child.getParentNode() == null) {
			parent.addChildNode(child);
		}
	}
	
	protected abstract IDocumentNode getDocumentNode(String name, IDocumentNode parent);
	
	protected abstract IDocumentAttribute getDocumentAttribute(String name, String value, IDocumentNode parent);
	
	private int getStartOffset(String elementName) throws BadLocationException {
		int line = fLocator.getLineNumber();
		int col = fLocator.getColumnNumber();
		IDocument doc = getDocument();
		if (col < 0)
			col = doc.getLineLength(line);
		String text = doc.get(fHighestOffset + 1, doc.getLineOffset(line) - fHighestOffset - 1);

		ArrayList commentPositions = new ArrayList();
		for (int idx = 0; idx < text.length();) {
			idx = text.indexOf("<!--", idx); //$NON-NLS-1$
			if (idx == -1)
				break;
			int end = text.indexOf("-->", idx); //$NON-NLS-1$
			if (end == -1) 
				break;
			
			commentPositions.add(new Position(idx, end - idx));
			idx = end + 1;
		}

		int idx = 0;
		for (; idx < text.length(); idx += 1) {
			idx = text.indexOf("<" + elementName, idx); //$NON-NLS-1$
			if (idx == -1)
				break;
			boolean valid = true;
			for (int i = 0; i < commentPositions.size(); i++) {
				Position pos = (Position)commentPositions.get(i);
				if (pos.includes(idx)) {
					valid = false;
					break;
				}
			}
			if (valid)
				break;
		}
		if (idx > -1)
			fHighestOffset += idx + 1;
		return fHighestOffset;
	}
	
	private int getElementLength(IDocumentNode node, int line, int column) throws BadLocationException {
		int endIndex = node.getOffset();
		IDocument doc = getDocument();
		int start = Math.max(doc.getLineOffset(line), node.getOffset());
		column = doc.getLineLength(line);
		String lineText= doc.get(start, column - start + doc.getLineOffset(line));
		
		int index = lineText.indexOf("</" + node.getXMLTagName() + ">"); //$NON-NLS-1$ //$NON-NLS-2$
		if (index == -1) {
			index= lineText.indexOf("/>"); //$NON-NLS-1$
			if (index == -1 ) {
				endIndex = column;
			} else {
				endIndex = index + 2;
			}
		} else{
			endIndex = index + node.getXMLTagName().length() + 3;
		}
		return start + endIndex - node.getOffset();
	}
	
	private IRegion getAttributeRegion(String name, String value, int offset) throws BadLocationException{
		IRegion nameRegion = fFindReplaceAdapter.find(offset, name+"\\s*=\\s*\"", true, true, false, true); //$NON-NLS-1$
		if (nameRegion != null) {
			if (getDocument().get(nameRegion.getOffset() + nameRegion.getLength(), value.length()).equals(value))
				return new Region(nameRegion.getOffset(), nameRegion.getLength() + value.length() + 1);
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (fDocumentNodeStack.isEmpty())
			return;
		
		IDocumentNode node = (IDocumentNode)fDocumentNodeStack.pop();
		try {
			node.setLength(getElementLength(node, fLocator.getLineNumber() - 1, fLocator.getColumnNumber()));
			setTextNodeOffset(node);
		} catch (BadLocationException e) {
		}
		removeOrphanElements(node);
	}
	
	protected void setTextNodeOffset(IDocumentNode node) throws BadLocationException {
		IDocumentTextNode textNode = node.getTextNode();
		if (textNode != null && textNode.getText() != null) {
			if (textNode.getText().trim().length() == 0) {
				node.removeTextNode();
				return;
			}
			IDocument doc = getDocument();
			String text = doc.get(node.getOffset(), node.getLength());
			// 1st char of text node
			int relativeStartOffset = text.indexOf('>') + 1;
			// last char of text node
			int relativeEndOffset = text.lastIndexOf('<') - 1;
			
			// trim whitespace
			while (Character.isWhitespace(text.charAt(relativeStartOffset)))
				relativeStartOffset += 1;
			while (Character.isWhitespace(text.charAt(relativeEndOffset)))
				relativeEndOffset -= 1;
			
			textNode.setOffset(node.getOffset() + relativeStartOffset);
		    textNode.setLength(relativeEndOffset - relativeStartOffset + 1);
		    textNode.setText(textNode.getText().trim());
		}	
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	public void fatalError(SAXParseException e) throws SAXException {
		generateErrorElementHierarchy();
	}
	
	/**
	 * 
	 */
	private void generateErrorElementHierarchy() {
		while (!fDocumentNodeStack.isEmpty()) {
			IDocumentNode node = (IDocumentNode)fDocumentNodeStack.pop(); 
			node.setIsErrorNode(true);
			removeOrphanAttributes(node);
			removeOrphanElements(node);
			if (fLastError == null)
				fLastError = node;
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
	 */
	public void error(SAXParseException e) throws SAXException {
		generateErrorElementHierarchy();
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	public void setDocumentLocator(Locator locator) {
		fLocator = locator;
	}
	
	protected abstract IDocument getDocument();
	
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
		// Prevent the resolution of external entities in order to
		// prevent the parser from accessing the Internet
		// This will prevent huge workbench performance degradations and hangs
		return new InputSource(new StringReader("")); //$NON-NLS-1$
	}
	
	public IDocumentNode getLastErrorNode() {
		return fLastError;
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {	
		if (!fReconciling || fDocumentNodeStack.isEmpty())
			return;
		
		IDocumentNode parent = (IDocumentNode)fDocumentNodeStack.peek();
		StringBuffer buffer = new StringBuffer();
		buffer.append(ch, start, length);
		IDocumentTextNode textNode = parent.getTextNode();
		if (textNode == null) {
			if (buffer.toString().trim().length() > 0) {
				textNode = new DocumentTextNode();
				textNode.setEnclosingElement(parent);
				parent.addTextNode(textNode);
				textNode.setText(buffer.toString().trim());
			}
		} else {
			textNode.setText(buffer.insert(0, textNode.getText()).toString());
		}
	}
	
	private void removeOrphanAttributes(IDocumentNode node) {
		// when typing by hand, one element may overwrite a different existing one
		// remove all attributes from previous element, if any.
		if (fReconciling) {
			IDocumentAttribute[] attrs = node.getNodeAttributes();
			for (int i = 0; i < attrs.length; i++) {
				if (attrs[i].getNameOffset() == -1)
					node.removeDocumentAttribute(attrs[i]);
			}
		}		
	}

	private void removeOrphanElements(IDocumentNode node) {
		// when typing by hand, one element may overwrite a different existing one
		// remove all excess children elements, if any.
		if (fReconciling) {
			IDocumentNode[] children = node.getChildNodes();
			for (int i = 0; i < children.length; i++) {
				if (children[i].getOffset() == -1) {
					node.removeChildNode(children[i]);
				}
			}
		}
	}
	
}