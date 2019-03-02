package com.research.functions.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.research.functions.LambdaStreams;
import com.research.functions.Predicates;
import com.research.types.common.Data;
import com.research.types.common.DataHelper;


/**
 * @author cgordon
 * @version
 *  
 * Base test for the LambdaStream implementation.
 */
public abstract class UnitTest {

	/**
	 * The LambdaStream class to use in all the tests: set this in subclasses.
	 */
	protected LambdaStreams p;

	@Test
	public void testOutputPrintFunctions() {

		p = new LambdaStreams();
		assertEquals("foo: apply: ", p.foo.apply("test"), "test_bar");

		System.out.printf("foo: apply: %s \n", p.foo.apply("test"));	
		System.out.printf("foo1.1: apply: %s \n", p.renderer.apply(p.foo1.apply("test-bar")));
		System.out.printf("foo1.2: apply: %s \n", p.renderer.apply(p.foo1.apply("test*bar")));
		System.out.printf("foo2: apply: %s \n", p.renderer.apply(p.foo2.apply("test-bar")));
		System.out.printf("foo3: apply: %s \n", p.foo3.apply( new String[] {"test","bar"}));
		System.out.printf("foo4: apply: %s \n", p.foo4.apply( new String[] {"test","bar"}));	

		System.out.printf("foo5: apply: %s \n", p.foo5.apply( "test","bar"));
		System.out.printf("foo7.1: apply: %s \n", p.foo7.apply( "test","bar"));
		System.out.printf("foo7.2: apply: %s \n", p.foo7.apply( "foo","bar"));

		System.out.printf("foo8: apply: %s \n", p.foo8.apply( "foobar:thefoobar"));
		assertTrue("foo9: apply: (string > 5)? %s \n", p.foo9.apply( "fobar"));
		System.out.printf("foo10: apply: %s \n", p.foo10.apply(p.listerer.apply( "foo,bar,the,foor,bar,the,foo")));
		System.out.printf("foo11: apply: %s \n", p.foo11.apply( "foo","bar"));
		System.out.printf("foo12: apply: (string foo plus > 5) %s \n", p.foo12.apply(p.listerer.apply( "foo,bar,the,foor, ,the,foo,boo,bar,foort,foo,foo,fooht")));
		System.out.printf("foo14: apply: (string o plus > 3) %s \n", p.foo14.apply(p.listerer.apply( "foo,bar,the,foor, ,the,foo, ,foo,foo")));
		System.out.printf("foo15: apply: %s \n", p.foo15.apply(p.listerer.apply( "foo,bar,the,foor, ,the,foo, ,foo,foo")));
		System.out.printf("foo7: apply: %s \n", p.foo7.apply( "foo","bar"));
		System.out.printf("foo16: [custom collector]: apply: { %s }\n", p.foo16.apply(p.convertToDataList(p.listerer.apply("foo,bar,the,foor, ,the,foo, ,foo,foo"))));

		System.out.printf("slowExtract: apply: %s \n", p.foo3.apply(p.slowExtract.apply(p.convertToDataList(p.listerer.apply("foo,bar,the,foor, ,the,foo, ,foo,foo")))));
		System.out.printf("fastExtract: apply: %s \n", p.foo3.apply(p.fastExtract.apply(p.convertToDataList(p.listerer.apply("foo,bar,the,foor, ,the,foo, ,foo,foo")))));

	}

	@Test 
	public void testPresetValueNameFunctions() {

		DataHelper helper = new DataHelper();

		Predicates p = new Predicates();		

		List<Data> inventory = Arrays.asList(helper.getRandomData(), helper.getRandomData() );

		List<Data> data = p.filter(inventory, Predicates::isValueNamePreset);
		System.out.println(data);
	}

	@Test
	public void testPresetValueAgeFunctions() {

		DataHelper helper = new DataHelper();

		Predicates p = new Predicates();		

		List<Data> inventory = Arrays.asList(helper.getRandomData(), helper.getRandomData() );

		List<Data> data = p.filter(inventory,Predicates::isValueAgePreset);
		System.out.println(data);

	}
	
	@Test
	public void testPresetValueGradeFunctions() {

		DataHelper helper = new DataHelper();

		Predicates p = new Predicates();		

		List<Data> inventory = Arrays.asList(helper.getRandomData(), helper.getRandomData() );

		List<Data> data = p.filter(inventory,Predicates::isValueGradePreset);
		System.out.println(data);
	}
	
	@Test
	public void testPresetValueGradeVerifFunctions() {

		DataHelper helper = new DataHelper();

		Predicates p = new Predicates();		

		List<Data> inventory = Arrays.asList(helper.getRandomData(), helper.getRandomData() );

		List<Data> data = p.filter(inventory,Predicates.presetGradeVerify);
		System.out.println(data);
		
	}
	
	@Test
	public void testLambdaAgeFunctions() {

		DataHelper helper = new DataHelper();

		Predicates p = new Predicates();		

		List<Data> inventory = Arrays.asList(helper.getRandomData(), helper.getRandomData() );

		List<Data> data = p.filter(inventory,(d)-> d.getAge() > 100);
		System.out.println(data);
		assertEquals("Has no values", data.size(), 0);
	}
	
	@Test
	public void testLambdaNameFunctions() {

		DataHelper helper = new DataHelper();

		Predicates p = new Predicates();		

		List<Data> inventory = Arrays.asList(helper.getRandomData(), helper.getRandomData() );

		List<Data> data = p.filter(inventory,(d)-> d.getName().equalsIgnoreCase("Ninja Gaiden"));
		System.out.println(data);
		assertTrue("Should have no values present", data.isEmpty());
	}
	
}