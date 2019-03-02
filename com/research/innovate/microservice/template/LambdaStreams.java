/**
 * 
 */

package com.research.innovate.microservice.template;


import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author cgordon
 *
 */
public class LambdaStreams {

	public final Function<String,String> foo = x -> x + "_bar";
	public final Function<String,String[]> splitStringByDashDefault = x -> x.contains("-")? x.split("-") : new String[] {"foo","bar"};
	public final Function<String,String[]> splitStringByDash = x -> x.split("-");
	public final Function<String[],String> printStringArray = x ->  Arrays.asList(x).toString(); 
	public final Function<String[],String> joinStringArrayByDash = x ->  Arrays.asList(x).stream().collect(Collectors.joining("-"));
	public final Function<String[],String> joinStringArray = x ->  Arrays.asList(x).stream().collect(Collectors.joining(""));
	
	public final Function<String,List<String>> splitStringToArrayByComma = x ->  Arrays.asList(x.split(","));

	//Note: This is normal reduction which performs in O(n2)
	public final Function<List<Data>, String[]> slowExtractDataFieldToStringArray = x ->  x.stream().map(e -> e.field().concat(",")).reduce("", String::concat).split(",");

	//Note: This is a more efficient approach which performs in O(n)
	public final Function<List<Data>, String[]> fastExtractDataFieldToStringArray = x ->  x.stream().map(Data::field).collect(Collectors.joining(",")).split(","); 

	public final Function2<String,String> concatString = (x,y) -> x + y;
	public final Function2<String,String> concatStringWithCondition = (x,y) -> x.equalsIgnoreCase("test")? x.concat("_barara_").concat(y) : x.concat(y);

	public final Function<String,String> substrByColon = s -> s.substring(0, s.indexOf(":"));

	public final Function<String,Boolean> isStringLongerThan = y -> y.length() > 3;

	public final Function<List<String>, String> splitStringByDashDefault0 = x ->  x.stream().filter(s->s.contains("foo")).collect(Collectors.joining(","));

	public final Function2<String,String> splitStringByDashDefault1 = (x,y) -> x + y;

	public final Function<List<String>, Map<Boolean, List<String>>> splitStringByDashDefault2 = x ->  x.stream().filter(s->s.contains("foo")).collect(Collectors.groupingBy(y -> y.length() > 3));	

	public final Function<List<String>, Map<Boolean, List<String>>> splitStringByDashDefault4 = x ->  x.stream().filter(s->s.contains("o")).collect(Collectors.groupingBy(isStringLongerThan));

	public final Function<List<String>, Map<Boolean, List<String>>> splitStringByDashDefault5 = x ->  x.stream().filter(s->s.contains("foo")).collect(Collectors.groupingBy(String::isEmpty));

	public final Function<List<Data>, String> splitStringByDashDefault6 = x ->  x.stream().filter(s->s.field.contains("foo")).collect(this.collect(x));


	//@SuppressWarnings("unchecked")
	public final Map<Integer,  Function<?,?>> functions = new HashMap<Integer, Function<?,?>>() {

		private static final long serialVersionUID = -4431080516803522711L;
		{
			 put(1, foo); //Function<String,String>
			 put(2, substrByColon); //Function<String,String>			 
			 put(3, splitStringByDashDefault); //Function<String,String[]>
			 put(4, splitStringByDash); //Function<String,String[]>
			 put(5, printStringArray); //Function<String[],String>
			 put(6, joinStringArrayByDash); //Function<String[],String>
			 put(7, joinStringArray); //Function<String[],String>
			 put(8, splitStringToArrayByComma); //Function<String,List<String>>
			 put(9, slowExtractDataFieldToStringArray); //Function<List<Data>, String[]>
			 put(10, fastExtractDataFieldToStringArray); //Function<List<Data>, String[]>
			 put(11, isStringLongerThan); //Function<String,Boolean>
			 put(12, splitStringByDashDefault2); //Function<List<String>, Map<Boolean, List<String>>>
			 put(13, splitStringByDashDefault4); //Function<List<String>, Map<Boolean, List<String>>>
			 put(14, splitStringByDashDefault5); //Function<List<String>, Map<Boolean, List<String>>>
			 put(15, splitStringByDashDefault6); //Function<List<Data>, String>
			 put(16, splitStringByDashDefault0); //Function<List<String>, String>

		   };

		 };

	@SuppressWarnings("unused")

	private static void main(String... args) {

		LambdaStreams p = new LambdaStreams();
		
		System.out.printf("foo: apply: %s \n", p.foo.apply("test"));	
		System.out.printf("splitStringByDashDefault.1: apply: %s \n", p.joinStringArray.apply(p.splitStringByDashDefault.apply("test-bar")));
		System.out.printf("splitStringByDashDefault.2: apply: %s \n", p.joinStringArray.apply(p.splitStringByDashDefault.apply("test*bar")));
		System.out.printf("splitStringByDash: apply: %s \n", p.joinStringArray.apply(p.splitStringByDash.apply("test-bar")));
		System.out.printf("printStringArray: apply: %s \n", p.printStringArray.apply( new String[] {"test","bar"}));
		System.out.printf("joinStringArrayByDash: apply: %s \n", p.joinStringArrayByDash.apply( new String[] {"test","bar"}));	
		
		System.out.printf("concatString: apply: %s \n", p.concatString.apply( "test","bar"));
		System.out.printf("concatStringWithCondition.1: apply: %s \n", p.concatStringWithCondition.apply( "test","bar"));
		System.out.printf("concatStringWithCondition.2: apply: %s \n", p.concatStringWithCondition.apply( "foo","bar"));
		
		System.out.printf("substrByColon: apply: %s \n", p.substrByColon.apply( "foobar:thefoobar"));
		System.out.printf("isStringLongerThan: apply: (string > 5)? %s \n", p.isStringLongerThan.apply( "fobar"));
		System.out.printf("splitStringByDashDefault0: apply: %s \n", p.splitStringByDashDefault0.apply(p.splitStringToArrayByComma.apply( "foo,bar,the,foor,bar,the,foo")));
		System.out.printf("splitStringByDashDefault1: apply: %s \n", p.splitStringByDashDefault1.apply( "foo","bar"));
		System.out.printf("splitStringByDashDefault2: apply: (string foo plus > 5) %s \n", p.splitStringByDashDefault2.apply(p.splitStringToArrayByComma.apply( "foo,bar,the,foor, ,the,foo,boo,bar,foort,foo,foo,fooht")));
		System.out.printf("splitStringByDashDefault4: apply: (string o plus > 3) %s \n", p.splitStringByDashDefault4.apply(p.splitStringToArrayByComma.apply( "foo,bar,the,foor, ,the,foo, ,foo,foo")));
		System.out.printf("splitStringByDashDefault5: apply: %s \n", p.splitStringByDashDefault5.apply(p.splitStringToArrayByComma.apply( "foo,bar,the,foor, ,the,foo, ,foo,foo")));
		System.out.printf("concatStringWithCondition: apply: %s \n", p.concatStringWithCondition.apply( "foo","bar"));
		System.out.printf("splitStringByDashDefault6: [custom collector]: apply: { %s }\n", p.splitStringByDashDefault6.apply(p.convertToDataList(p.splitStringToArrayByComma.apply("foo,bar,the,foor, ,the,foo, ,foo,foo"))));
		
		System.out.printf("slowExtractDataFieldToStringArray: apply: %s \n", p.printStringArray.apply(p.slowExtractDataFieldToStringArray.apply(p.convertToDataList(p.splitStringToArrayByComma.apply("foo,bar,the,foor, ,the,foo, ,foo,foo")))));
		System.out.printf("fastExtractDataFieldToStringArray: apply: %s \n", p.printStringArray.apply(p.fastExtractDataFieldToStringArray.apply(p.convertToDataList(p.splitStringToArrayByComma.apply("foo,bar,the,foor, ,the,foo, ,foo,foo")))));

	}

	@FunctionalInterface
	public interface Function2<One, Two> {
	    public Two apply(One one, Two two);
	}	
		
	public class Data <Z>{
		
		List<Z> _fields = new ArrayList<Z>();
	
		Data(){}
		Data(Z fld1){ setData(fld1,0,0,0,0);}
		Data(Z fld1,Z fld2){ setData(fld1,fld21,0,0,0);}
		Data(Z fld1,Z fld2,Z fld3){ setData(fld1,fld2,fld3,0,0);}
		Data(Z fld1,Z fld2,Z fld3,Z fld4){ setData(fld1,fld2,fld3,fld4,0);}
		Data(Z fld1,Z fld2,Z fld3,Z fld4,Z fld5){ setData(fld1,fld2,fld3,fld4,fld5);}

		void setData(Z fld1,Z fld2,Z fld3,Z fld4,Z fld5){
			 _field[0] = fld1;
			 _field[1] = fld2;
			 _field[2] = fld3;
			 _field[3] = fld4;
			 _field[4] = fld5;
		}
	
		String field(Short pos){ return String.valueOf(_fields[pos-1]) ;}
		public void field(Short pos, Z field){ _fields[pos-1] = field ;}
		
		public String toString() { return String.format("Data: field: %s\n", _fields); }
	}

}
