/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.api.tools.comparator.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.pde.api.tools.internal.provisional.IApiComponent;
import org.eclipse.pde.api.tools.internal.provisional.IApiProfile;
import org.eclipse.pde.api.tools.internal.provisional.VisibilityModifiers;
import org.eclipse.pde.api.tools.internal.provisional.comparator.ApiComparator;
import org.eclipse.pde.api.tools.internal.provisional.comparator.DeltaProcessor;
import org.eclipse.pde.api.tools.internal.provisional.comparator.IDelta;

/**
 * Delta tests for method
 */
public class MethodDeltaTests extends DeltaTestSetup {
	
	public static Test suite() {
		if (true) return new TestSuite(MethodDeltaTests.class);
		TestSuite suite = new TestSuite(MethodDeltaTests.class.getName());
		suite.addTest(new MethodDeltaTests("test53"));
		return suite;
	}

	public MethodDeltaTests(String name) {
		super(name);
	}

	public String getTestRoot() {
		return "method";
	}

	/**
	 * Change method body
	 */
	public void test1() {
		deployBundles("test1");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		assertTrue("Not empty", delta.isEmpty());
		assertTrue("Different from NO_DELTA", delta == ApiComparator.NO_DELTA);
	}

	/**
	 * rename method parameter
	 */
	public void test2() {
		deployBundles("test2");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		assertTrue("Not empty", delta.isEmpty());
		assertTrue("Different from NO_DELTA", delta == ApiComparator.NO_DELTA);
	}
	
	/**
	 * Change method name
	 */
	public void test3() {
		deployBundles("test3");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 2, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[1];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Add formal parameter
	 */
	public void test4() {
		deployBundles("test4");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 2, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[1];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Delete formal parameter
	 */
	public void test5() {
		deployBundles("test5");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 2, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[1];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Change type of formal parameter
	 */
	public void test6() {
		deployBundles("test6");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 2, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[1];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Change result type
	 */
	public void test7() {
		deployBundles("test7");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 2, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[1];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Add checked exception
	 */
	public void test8() {
		deployBundles("test8");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.CHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Add unchecked exception
	 */
	public void test9() {
		deployBundles("test9");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.UNCHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Remove checked exception
	 */
	public void test10() {
		deployBundles("test10");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.CHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Remove unchecked exception
	 */
	public void test11() {
		deployBundles("test11");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.UNCHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Reorder list of thrown exceptions
	 */
	public void test12() {
		deployBundles("test12");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		assertTrue("Not empty", delta.isEmpty());
		assertTrue("Different from NO_DELTA", delta == ApiComparator.NO_DELTA);
	}
	
	/**
	 * Decrease visibility
	 */
	public void test13() {
		deployBundles("test13");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.DECREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Decrease visibility
	 */
	public void test14() {
		deployBundles("test14");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.DECREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Decrease visibility
	 */
	public void test15() {
		deployBundles("test15");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.DECREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Decrease visibility
	 */
	public void test16() {
		deployBundles("test16");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.DECREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Decrease visibility
	 */
	public void test17() {
		deployBundles("test17");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.DECREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Increase visibility
	 */
	public void test18() {
		deployBundles("test18");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED_NON_VISIBLE, child.getKind());
		assertEquals("Wrong flag", IDelta.INCREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Increase visibility
	 */
	public void test19() {
		deployBundles("test19");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.INCREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Increase visibility
	 */
	public void test20() {
		deployBundles("test20");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED_NON_VISIBLE, child.getKind());
		assertEquals("Wrong flag", IDelta.INCREASE_ACCESS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Abstract to non-abstract
	 */
	public void test21() {
		deployBundles("test21");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.ABSTRACT_TO_NON_ABSTRACT, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * non-abstract to abstract
	 */
	public void test22() {
		deployBundles("test22");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.NON_ABSTRACT_TO_ABSTRACT, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * final to non-final
	 */
	public void test23() {
		deployBundles("test23");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.FINAL_TO_NON_FINAL, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * non-final to final
	 */
	public void test24() {
		deployBundles("test24");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED_NOT_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.NON_FINAL_TO_FINAL, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * static to non-static
	 */
	public void test25() {
		deployBundles("test25");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.STATIC_TO_NON_STATIC, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * non-static to static
	 */
	public void test26() {
		deployBundles("test26");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.NON_STATIC_TO_STATIC, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * native to non-native
	 */
	public void test27() {
		deployBundles("test27");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.NATIVE_TO_NON_NATIVE, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * non-native to native
	 */
	public void test28() {
		deployBundles("test28");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.NON_NATIVE_TO_NATIVE, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * synchronized to non-synchronized
	 */
	public void test29() {
		deployBundles("test29");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.SYNCHRONIZED_TO_NON_SYNCHRONIZED, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * non-synchronized to synchronized
	 */
	public void test30() {
		deployBundles("test30");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.NON_SYNCHRONIZED_TO_SYNCHRONIZED, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Add type parameters
	 */
	public void test31() {
		deployBundles("test31");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.TYPE_PARAMETERS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Add type parameter
	 */
	public void test32() {
		deployBundles("test32");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.TYPE_PARAMETER, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Delete type parameters
	 */
	public void test33() {
		deployBundles("test33");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.TYPE_PARAMETERS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Delete type parameter
	 */
	public void test34() {
		deployBundles("test34");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.TYPE_PARAMETER, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Rename type parameter
	 */
	public void test35() {
		deployBundles("test35");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.TYPE_PARAMETER_NAME, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Reorder type parameters + changed class bound and interface bound
	 */
	public void test36() {
		deployBundles("test36");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 6, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.INTERFACE_BOUNDS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[1];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.CLASS_BOUND, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[2];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.CLASS_BOUND, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[3];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.TYPE_PARAMETER_NAME, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[4];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.TYPE_PARAMETER_NAME, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[5];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.INTERFACE_BOUNDS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Change last parameter from array to varargs
	 */
	public void test37() {
		deployBundles("test37");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.ARRAY_TO_VARARGS, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Change last parameter from varargs to array
	 */
	public void test38() {
		deployBundles("test38");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.VARARGS_TO_ARRAY, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Removed unchecked exception
	 */
	public void test39() {
		deployBundles("test39");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.UNCHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Removed checked exception
	 */
	public void test40() {
		deployBundles("test40");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.CHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Add checked exception
	 */
	public void test41() {
		deployBundles("test41");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.CHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Added unchecked exception
	 */
	public void test42() {
		deployBundles("test42");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.UNCHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
	
	/**
	 * Added default value
	 */
	public void test43() {
		deployBundles("test43");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED, child.getKind());
		assertEquals("Wrong flag", IDelta.ANNOTATION_DEFAULT_VALUE, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Removed default value
	 */
	public void test44() {
		deployBundles("test44");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.REMOVED, child.getKind());
		assertEquals("Wrong flag", IDelta.ANNOTATION_DEFAULT_VALUE, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Changed default value
	 */
	public void test45() {
		deployBundles("test45");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.ANNOTATION_DEFAULT_VALUE, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * non-final to final
	 */
	public void test46() {
		deployBundles("test46");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.CHANGED, child.getKind());
		assertEquals("Wrong flag", IDelta.NON_FINAL_TO_FINAL, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * change return type of a package visible method
	 */
	public void test47() {
		deployBundles("test47");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 2, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_NON_VISIBLE, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
		child = allLeavesDeltas[1];
		assertEquals("Wrong kind", IDelta.REMOVED_NON_VISIBLE, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Add checked exception
	 */
	public void test48() {
		deployBundles("test48");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_NON_VISIBLE, child.getKind());
		assertEquals("Wrong flag", IDelta.CHECKED_EXCEPTION, child.getFlags());
		assertEquals("Wrong element type", IDelta.METHOD_ELEMENT_TYPE, child.getElementType());
		assertTrue("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Added abstract method
	 */
	public void test49() {
		deployBundles("test49");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_NOT_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertFalse("Is binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Changes in a non-visible type should not report delta when only API is requested
	 */
	public void test50() {
		deployBundles("test50");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after, VisibilityModifiers.API);
		assertNotNull("No delta", delta);
		assertTrue("No NO_DELTA", delta == ApiComparator.NO_DELTA);
	}

	/**
	 * Changes in a visible type should report delta when only API is requested
	 */
	public void test51() {
		deployBundles("test51");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after, VisibilityModifiers.API);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Changes in a visible type should report delta when only API is requested
	 * with extend restriction
	 */
	public void test52() {
		deployBundles("test52");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after, VisibilityModifiers.API);
		assertNotNull("No delta", delta);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}

	/**
	 * Changes in a visible type should report delta when only API is requested
	 */
	public void test53() {
		deployBundles("test53");
		IApiProfile before = getBeforeState();
		IApiProfile after = getAfterState();
		IApiComponent beforeApiComponent = before.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", beforeApiComponent);
		IApiComponent afterApiComponent = after.getApiComponent(BUNDLE_NAME);
		assertNotNull("no api component", afterApiComponent);
		IDelta delta = ApiComparator.compare(beforeApiComponent, afterApiComponent, before, after, VisibilityModifiers.ALL_VISIBILITIES);
		assertNotNull("No delta", delta);
		assertFalse("Should not be NO_DELTA", delta == ApiComparator.NO_DELTA);
		IDelta[] allLeavesDeltas = collectLeaves(delta);
		assertEquals("Wrong size", 1, allLeavesDeltas.length);
		IDelta child = allLeavesDeltas[0];
		assertEquals("Wrong kind", IDelta.ADDED_EXTEND_RESTRICTION, child.getKind());
		assertEquals("Wrong flag", IDelta.METHOD, child.getFlags());
		assertEquals("Wrong element type", IDelta.CLASS_ELEMENT_TYPE, child.getElementType());
		assertTrue("Not binary compatible", DeltaProcessor.isBinaryCompatible(child));
	}
}
