/**
 * 
 */
package com.research.functions.test;

/**
 * @author cgordon
 *
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import com.research.functions.LambdaStreams;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedTest {
    
    private String param;
    private String expected;
    
    public ParameterizedTest(String expectedResult, String inputParam) {
    	
    	param = inputParam;
    	expected = expectedResult;
    }
    
    @Parameters
    public static Collection<String[]> inputData() {
        return Arrays.asList(new String[][] {
        	{"foo_bar, foo", "dread_bar, dread", "car_bar, car" },
        	{"foo1_bar, foo1", "dread1_bar, dread1", "car1_bar, car1_bar" }
        	});
         }
    
    @Test
    public void fooTest() {
    	
    	LambdaStreams lambda = new LambdaStreams();
    	System.out.printf("DEBUG: Apply functions with parameters: first- %s \n", param);
    	
        assertEquals(expected, lambda.foo.apply(param));
    }

}