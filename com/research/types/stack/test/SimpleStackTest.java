/**
 * 
 */
package com.research.types.stack.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.research.types.common.Data;
import com.research.types.common.DataHelper;
import com.research.types.stack.SimpleStack;

/**
 * @author cgordon
 * @version 1.0 
 * 
 * Unit test for SimpleStack.
 */
public class SimpleStackTest extends StackTest {

	private final int MAX = 10;
	
    @Before
    public void makeSimpleStack() {
        stack = new SimpleStack();
        
    	DataHelper helper = new DataHelper();

		for(int i=0; i < MAX;i++) {
			Data item = helper.getRandomData();

			System.out.printf("Push item: %s \n", item);
			stack.push(item);
		}	
		System.out.printf("@BeforeClass: setup complete");
        
        
    }

    @Test public void verifySize() {
    	
    	assertEquals("",stack.size(),MAX);
    }
    
    @Test public void simpleTest() {
    	
    	do {
    		
    		System.out.printf("@Test: pop: %s \n", stack.pop());
    	}while(!stack.isEmpty());

    	System.out.printf("simpleTest: is complete\n");
    }
    
    @Test public void simpleTestUnderflow() {
    	
    	stack = new SimpleStack();
    	
    	do {
    		
    		System.out.printf("@Test: pop: %s \n", stack.pop());
    	}while(!stack.isEmpty());

    	System.out.printf("simpleTest: is complete\n");
    }
    
}