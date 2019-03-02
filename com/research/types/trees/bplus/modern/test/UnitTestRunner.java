/**
 * 
 */
package com.research.types.trees.bplus.modern.test;

/**
 * @author cgordon
 *
 */
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UnitTestRunner {
	public static void main(String[] args) {

		UnitTestRunner runner= new UnitTestRunner();

		for(int i=1; i < 3; i++) {
			//runner.process(String.format("com.research.types.trees.bplus.modern.BPTreeTestSuite"));
			runner.process(String.format("com.research.types.trees.bplus.modern.BPTreeTestUnit%d",i));
		}

		System.out.println("Test Runner Complete!");
	}

	private void process(String testCaseName) {

		Class<?> testCase = null;
		try {
			testCase = Class.forName(testCaseName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Result result = JUnitCore.runClasses(testCase);

		for (Failure failure : result.getFailures()) {
			System.out.printf("failed: %s result: %s\n", testCase.getSimpleName(), failure.toString());
		}

		System.out.printf("Test: %s was %s \n", testCase.getSimpleName(), result.wasSuccessful());
	}
}