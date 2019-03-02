/**
 * 
 */
package com.research.functions.test;

import static org.junit.Assert.assertEquals;

/**
 * @author cgordon
 *
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;

import com.research.functions.LambdaStreams;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class LambdaStreamParameterizedTest {
	private int index;	
	private Object inputParam;
	private Object expectedResult;
	private LambdaStreams lambdas;

	@Before
	public void initialize() {
		lambdas = new LambdaStreams();
	}

	// Each parameter should be placed as an argument here
	// Every time runner triggers, it will pass the arguments
	// from parameters we defined in primeNumbers() method

	public LambdaStreamParameterizedTest(int index, Object inputParam, Object expectedResult) {
		this.inputParam = inputParam;
		this.expectedResult = expectedResult;
		this.index = index;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> functionInputdata() {
		return Arrays.asList(new Object[][] {
			{ 1, "test", "test_bar" },
			{ 1, "tester", "tester_bar" },
			{ 1, "test", "test_bart" },
			{ 2, "test:R", true },
			{ 2, "testRre", false },
			{ 3, "test-bar", new String[] {"test","bar"} },
			{ 3, "testbat", new String[] {"foo","bar"} },
			{ 4, "test-bar", new String[] {"test","bar"} },
			{ 4, "testbar", "" },
			{ 5, new String[] {"test","bar"}, "testbar" },
			{ 5, new String[] {"test","bar","marge"}, "testbarmarge" },
			{ 6, new String[] {"test","bar","marge"}, "test-bar-marge" },
			{ 7, new String[] {"test","bar","marge"}, "testbarmarge" },
			{ 8, "test123,test456,test567",  Arrays.asList(new String[] {"test123","test456","test567"}) },
			{ 12, "test123", true },
			{ 12, "te3", false },			
		});
	}

	// This test will run 4 times since we have 5 parameters defined
	@Test
	public void testLambdaFunctions() {
		System.out.printf("Parameterized input: %s | expect: %s | index: %d \n", inputParam, expectedResult, index);

		@SuppressWarnings("unchecked")
		Function<Object,?> function = (Function<Object,?>)lambdas.functions.get(index);

		//System.out.printf("DEBUG: foo: inputParam: %s | expectedResult: %s | description: %s | index: %d \n", inputParam,expectedResult,func.toString(), i);
		//System.out.printf("DEBUG: foo: inputParam: %s | expectedResult: %s | apply: %s | index: %d \n", inputParam,expectedResult,func.apply(inputParam), i);
		//System.out.printf("foo: inputParam: %s | expectedResult: %s | apply: %s | counter: %d \n", inputParam,expectedResult,function.apply(inputParam), index);
		assertEquals(String.format("function: index: %s -> apply: \n", index), expectedResult.toString(), function.apply(inputParam).toString());

	}
}