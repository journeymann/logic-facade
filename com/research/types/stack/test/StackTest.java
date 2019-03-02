package com.research.types.stack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.research.types.stack.Stack;

/**
 * @author cgordon
 * @version
 *  
 * Base test for any class that implements the Stack interface.
 */
public abstract class StackTest {

	/**
	 * The stack to use in all the tests: set this in subclasses.
	 */
	protected Stack stack;

	@Test
	public void testNewStackIsEmpty() {
		assertTrue(stack.isEmpty());
		assertEquals(stack.size(), 0);
	}

	@Test
	public void testPushesToEmptyStack() {
		int numberOfPushes = 6;
		for (int i = 0; i < numberOfPushes; i++) {
			stack.push("zzz");
		}
		assertFalse(stack.isEmpty());
		assertEquals(stack.size(), numberOfPushes);
	}

	@Test
	public void testPushThenPop() {
		String message = "hello";
		stack.push(message);
		assertEquals(stack.pop(), message);
	}

	@Test
	public void testPushThenPeek() {
		String message = "hello";
		stack.push(message);
		int size = stack.size();
		assertEquals(stack.peek(), message);
		assertEquals(stack.size(), size);
	}

	@Test
	public void testPoppingDownToEmpty() {
		int numberOfPushes = (int)(Math.random() * 20 + 1);
		for (int i = 0; i < numberOfPushes; i++) {
			stack.push("zzz");
		}
		for (int i = 0; i < numberOfPushes; i++) {
			stack.pop();
		}
		assertTrue(stack.isEmpty());
		assertEquals(stack.size(), 0);
	}

	@Test(expected=NoSuchElementException.class)
	public void testPopOnEmptyStack() {
		assertTrue(stack.isEmpty());
		stack.pop();
	}

	@Test(expected=NoSuchElementException.class)
	public void testPeekIntoEmptyStack() {
		assertTrue(stack.isEmpty());
		stack.peek();
	}
}