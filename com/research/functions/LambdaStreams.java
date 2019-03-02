/**
 * 
 */
package com.research.functions;

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
	public final Function<String,String[]> foo1 = x -> x.contains("-")? x.split("-") : new String[] {"foo","bar"};
	public final Function<String,String[]> foo2 = x -> x.split("-");
	public final Function<String[],String> foo3 = x ->  Arrays.asList(x).toString(); 
	public final Function<String[],String> foo4 = x ->  Arrays.asList(x).stream().collect(Collectors.joining("-"));
	public final Function<String[],String> renderer = x ->  Arrays.asList(x).stream().collect(Collectors.joining(""));
	
	public final Function<String,List<String>> listerer = x ->  Arrays.asList(x.split(","));

	//Note: This is normal reduction which performs in O(n2)
	public final Function<List<Data>, String[]> slowExtract = x ->  x.stream().map(e -> e.field().concat(",")).reduce("", String::concat).split(",");
	
	//Note: This is a more efficient approach which performs in O(n)
	public final Function<List<Data>, String[]> fastExtract = x ->  x.stream().map(Data::field).collect(Collectors.joining(",")).split(","); 
	
	public final Function2<String,String> foo5 = (x,y) -> x + y;
	public final Function2<String,String> foo7 = (x,y) -> x.equalsIgnoreCase("test")? x.concat("_barara_").concat(y) : x.concat(y);
	
	public final Function<String,String> foo8 = s -> s.substring(0, s.indexOf(":"));
	public final Function<String,Boolean> foo9 = y -> y.length() > 3;
	public final Function<List<String>, String> foo10 = x ->  x.stream().filter(s->s.contains("foo")).collect(Collectors.joining(","));

	public final Function2<String,String> foo11 = (x,y) -> x + y;
	public final Function<List<String>, Map<Boolean, List<String>>> foo12 = x ->  x.stream().filter(s->s.contains("foo")).collect(Collectors.groupingBy(y -> y.length() > 3));	
	public final Function<List<String>, Map<Boolean, List<String>>> foo14 = x ->  x.stream().filter(s->s.contains("o")).collect(Collectors.groupingBy(foo9));
	public final Function<List<String>, Map<Boolean, List<String>>> foo15 = x ->  x.stream().filter(s->s.contains("foo")).collect(Collectors.groupingBy(String::isEmpty));
	public final Function<List<Data>, String> foo16 = x ->  x.stream().filter(s->s.field.contains("foo")).collect(this.collect(x));
	
	//@SuppressWarnings("unchecked")
	public final Map<Integer,  Function<?,?>> functions = new HashMap<Integer, Function<?,?>>() {
		private static final long serialVersionUID = -4431080516803522711L;

		{
			 put(1, foo); //Function<String,String>
			 put(2, foo8); //Function<String,String>			 
			 put(3, foo1); //Function<String,String[]>
			 put(4, foo2); //Function<String,String[]>
			 put(5, foo3); //Function<String[],String>
			 put(6, foo4); //Function<String[],String>
			 put(7, renderer); //Function<String[],String>
			 put(8, listerer); //Function<String,List<String>>
			 put(9, slowExtract); //Function<List<Data>, String[]>
			 put(10, fastExtract); //Function<List<Data>, String[]>
			 put(11, foo9); //Function<String,Boolean>
			 put(12, foo12); //Function<List<String>, Map<Boolean, List<String>>>
			 put(13, foo14); //Function<List<String>, Map<Boolean, List<String>>>
			 put(14, foo15); //Function<List<String>, Map<Boolean, List<String>>>
			 put(15, foo16); //Function<List<Data>, String>
			 put(16, foo10); //Function<List<String>, String>
		   };
		 };
	
	@SuppressWarnings("unused")
	private static void main(String... args) {
		
		LambdaStreams p = new LambdaStreams();
		
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
		System.out.printf("foo9: apply: (string > 5)? %s \n", p.foo9.apply( "fobar"));
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

	@FunctionalInterface
	public interface Function2<One, Two> {
	    public Two apply(One one, Two two);
	}	
	
    interface Property<T>
    {
        T get();
    }
    
	public Collector<Data, StringJoiner, String> collect(List<Data> list){

		return Collector.of(
				() -> new StringJoiner(list.stream().map(Data::field).collect(Collectors.joining(","))),    // supplier
				(j, s) -> j.add(s.toString().toUpperCase()), 												// accumulator
		        (j1, j2) -> j1.merge(j2),               													// combiner
		        StringJoiner::toString);  																	// finisher
	
	}

	public <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
	    return from.stream().map(func).collect(Collectors.toList());
	}	
	
	public List<Data> convertToDataList(List<String> list) {
		
		List<Data> output = new ArrayList<Data>();
		
		for(String s: list) {
			output.add(new Data(s));
		}
		
		return output;
	}	
	
	
	/**public <T,A,R> R collect(Iterable<T> elements) {
	 * 

		@SuppressWarnings("unchecked")
		BinaryOperator<T> binaryOpt = (s1,s2)-> (T)String.valueOf(s1).concat("*+*").concat(String.valueOf(s2)); 
	
		@SuppressWarnings("unchecked")
		MyCollector<T, A , R> collector = (MyCollector<T, A , R>)Collectors.reducing(binaryOpt);
		
		//CustomCollector<T,A,R> collector = Collector.of(ArrayList::new, List::add, (left, right) -> {
		//            left.addAll(right);
		//            return left;
		//        }, List);
			
		
		Collector<Data, StringJoiner, String> personNameCollector =
			    Collector.of(
			        () -> new StringJoiner(" | "),          // supplier
			        (j, d) -> j.add(d.field.toUpperCase()), // accumulator
			        (j1, j2) -> j1.merge(j2),               // combiner
			        StringJoiner::toString);  				// finisher
		
		
	    Supplier<A> supplier = collector.supplier();
	    A acc = supplier.get();

	    BiConsumer<A,T> accumulator = collector.accumulator();
	    for (T elem: elements) {
	        accumulator.accept(acc, elem);
	    }

	    Function<A,R> finisher = collector.finisher();
	    R result = finisher.apply(acc);

	    return result;
	}*/
	
	public <T,A,R> R collect(
	        Collector<T,A,R> collector, Iterable<T> elements) {

		
	    Supplier<A> supplier = collector.supplier();
	    A acc = supplier.get();

	    BiConsumer<A,T> accumulator = collector.accumulator();
	    for (T elem: elements) {
	        accumulator.accept(acc, elem);
	    }

	    Function<A,R> finisher = collector.finisher();
	    R result = finisher.apply(acc);

	    return result;
	}
	
	public class Data{
		
		String field="none supplied"; 
		
		Data(){}
		Data(String str){ field = str;}
		
		String field(){ return field ;}
		public void field(String field_){ field = field_ ;}
		
		public String toString() { return String.format("Data: field: %s\n", field); }
	}
	

	  public static List<Data> filter(List<Data> inventory,
		      Predicate<Data> p) {
		    List<Data> result = new ArrayList<>();
		    for (Data apple : inventory) {
		      if (p.test(apple)) {
		        result.add(apple);
		      }
		    }
		    return result;
		  }
	
}
