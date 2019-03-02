/**
 * 
 */
package com.research.types.stack.test;

/**
 * @author cgordon
 * @version 1.0
 *
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({ 
	SimpleStackTest.class ,LinkedStackTest.class
})

public class StackTestSuite {
}