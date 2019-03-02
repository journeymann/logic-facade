package com.research.documents;

	/**
	 * 
	 *  @author cgordon
 	 *  @version 1.0
	 *  created 04-13-2017  
	 * 	  
	 *  <h2>Java Programming Best Practices</h2> 
	 *     
	 *     <h3>Just a few tips and pointers that I have picked up over the many years that I have been programming java:</h3>
	 * 	  
	 *  1. <b>Prefer returning Empty Collections/Objects instead of null</b><br><br>
	 *  
	 *  If a program is returning a collection which does not have any value, make sure an Empty collection is returned rather than Null elements. This saves a lot of “if else” testing on Null Elements.<br>
	 *  <br>
	 * 	 
	 *  Return an empty string instead of a null
	 * 	<pre>
	 *  {@code
	 *  	public class getLocationName {
	 *      	return (null==cityName ? "": cityName);
	 *   	}
	 *  }
	 * 	</pre>  	  
	 *  Return an empty Node object
	 * 	<pre>  
	 *  {@code
	 *  	public Node getLocationNode(List list, Key key) {
	 *  
	 *      	return (list.contains(key))? list.get(key) : new Node();
	 *  	}
	 *  }  
	 * 	</pre>
 	 *  Use NullObject design pattern. Define a custom Null Object as an alternative solution. Especially useful for collections.
	 * 	<pre>   
	 *  {@code
	 *  	public class Foo{
	 *  	
	 *  		private class NullObject extends Node{
	 *  
	 *  			public NullObject(){}
	 *  		}
	 *  	}
	 *  }
	 * 	</pre>  
	 *  Edge case (list == null) return custom NullObject (extends Node) to simplify downstream validation checks etc
	 * 	<pre>   
	 *  {@code
	 *  	public Node getLocationNode(List list, Key key) {
	 *  
	 *  	  	return (list.contains(key))? list.get(key) : new NullObject();
	 *  	}
	 *  }
	 * 	</pre>  
	 *  Return an empty collection [List] object 
	 * 	<pre>  
	 *  {@code
	 *  	public List getMyList(Map map, Key key) {
	 *  
	 *      	return (map.contains(key))? (ArrayList)map.get(key) : new List();
	 *  	}
	 *  }
	 * 	</pre>  
	 *  2. <b>Use Strings carefully</b><br><br>
	 *  String class/ objects are immutable which means the JVM will not allow their value to be changed once the instance has been created. <br>
	 *  If two Strings are concatenated using “+” operator in a “for” loop, then it creates a new String Object, every time. <br>
	 *  This causes wastage of memory and increases performance time as object instantiation in java is very performance expensive.<br> 
	 *  Also, while instantiating a String Object, constructors should be avoided and instantiation should happen directly. <br><br>
	 *  
	 *  For example:
	 * 	<pre>
	 *  {@code  
	 *    //Slower Instantiation
	 *    String bad = new String("Yet another string object");
	 *  
	 *    //Faster Instantiation
	 *    String good = "Yet another string object"
	 *  }  
	 * 	</pre>
	 *  <p>    
	 *  3. <b>Avoid unnecessary Objects</b><br><br>
	 *  One of the most expensive operations (in terms of Memory Utilization) in Java is Object Creation. <br>
	 *  Thus it is recommended that Objects should only be created or initialized if necessary. Following code gives an example:<br>
	 * 	<pre>
	 *  {@code  
	 *  	import java.util.ArrayList;
	 *  	import java.util.List;
	 *  
	 *  	public class Employees {
	 *  
	 *      	private List employees;
	 *  	
	 *      	public List getEmployees() {
	 *          	//initialize only when required
	 *  		    employees = (employees == null)? new ArrayList() : employees;
	 *          
	 *          	return employees;
	 *      	}
	 *  	}
	 *  }
	 * 	</pre>  
	 * 	<p>  
	 *  4. <b>Avoiding Memory leaks</b> 
	 *  <br><br>
	 *  a) Memory leaks often cause performance degradation of software. Since, Java manages memory automatically, the developers do not have much control. But there are still some standard practices which can be used to protect from memory leakages.<br>
	 *  b) Always release database connections when querying is complete.<br>
	 *  d) Try to use Finally block as often possible.<br>
	 *  e) Release instances stored in Static Tables.<br>
	 * 	<p> 	  
	 *  5. <b>Computation of power</b>
	 *  <br><br>
	 *  To compute power (^), java performs Exclusive OR (XOR). In order to compute power, Java offers two options:
	 *  <br><br>
	 *  Multiplication:<br><br>
	 *  double square = double a * double a;                            // Optimized<br>
	 *  double cube = double a * double a * double a;                   // Non-optimized<br>
	 *  double cube = double a * double square;                         // Optimized<br>
	 *  double quad = double a * double a * double a * double a;        // Non-optimized<br>
	 *  double quad = double square * double square;                    // Optimized<br>
	 *  <br>
	 *  Math.pow():<br>
	 *  pow(double base, double exponent):‘pow’ method is used to calculate where multiplication is not possible (base^exponent)<br>
	 *  double cube = Math.pow(base, exponent);<br>
	 *  <br>
	 *  Math.pow should be used ONLY when necessary. For example, exponent is a fractional value. That is because Math.pow() method is typically around 300-600 times slower than a multiplication.<br>
	 * 	<p> 	  
	 *  6. <b>How to handle Null Pointer Exceptions</b><br>
	 *  <br>
	 *  a) If the Object method will be referenced and used then always check for null before doing so.<br>
	 *  b) Null Pointer Exceptions are quite common in Java. This exception occurs when we try to call a method on a Null Object Reference. For example,<br>
	 * 	<pre>
	 * {@code  
     *  	int noOfStudents = school.listStudents().count;<br>
	 *  }
	 * 	</pre>  
	 *  If in the above example, if get a NullPointerException, then either school is null or listStudents() is Null. It’s a good idea to check Nulls early so that they can be eliminated.<br>
	 * 	<pre>
	 *  {@code  
	 *      private int getListOfStudents(File[] files) {
	 *        if (files == null)
	 *          throw new NullPointerException("File list cannot be null");
	 *      }
	 *  }    
	 * 	</pre>  
	 * 	<p> 	  
	 *  7. <b>FileOutputStream Vs. FileWriter</b><br><br>
	 *  File writing in Java is done mainly in two ways: FileOutputStream and FileWriter. Sometimes, developers struggle to choose one among them. This example helps them in choosing which one should be used under given requirements. First, let’s take a look at the implementation part:<br>
	 *  <br>
	 *  Using FileOutputStream:
	 * 	<pre>
	 * 	{@code  
	 *    File foutput = new File(file_location_string);
	 *    FileOutputStream fos = new FileOutputStream(foutput);
	 *    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));
	 *    output.write("Buffered Content");
	 *  }
	 * 	</pre>  
	 *  Using FileWriter:
	 * 	<pre>
	 * 	{@code  
	 *  	FileWriter fstream = new FileWriter(file_location_string);
	 *  	BufferedWriter output = new BufferedWriter(fstream);
	 *  	output.write("Buffered Content");
	 *  }
	 * 	</pre>  
	 *  According to Java API specifications:<br>
	 *    "FileOutputStream is meant for writing streams of raw bytes such as image data. For writing streams of characters, consider using FileWriter."<br>
	 *  This makes it pretty clear that for image type of Data FileOutputStream should be used and for Text type of data FileWriter should be used.<br>
	 * 	<p> 	  
	 *  8. <b>Use Collections</b><br>
	 *  <br>
	 *  Java is shipped with a few collection classes – for example, Vector, Stack, Hashtable, Array. The developers are encouraged to use collections as extensively as possible for the following reasons:<br>
	 *  <br>
	 *  a) Use of collections makes the code reusable and interoperable.<br>
	 *  b) Collections make the code more structured, easier to understand and maintainable.<br>
	 *  c) Out of the box collection classes are well tested so the quality of code is good.<br>
	 * 	<p> 	  
	 *  9. <b>10-50-500 Rule</b><br>
	 *  <br>
	 *  In big software packages, maintaining code becomes very challenging. Developers who join fresh ongoing support projects, often complain about: Monolithic Code, Spaghetti Code. There is a very simple rule to avoid that or keep the code clean and maintainable: 10-50-500.<br>
	 *  <br>
	 *  a) 10: No package can have more than 10 classes.<br>
	 *  b) 50: No method can have more than 50 lines of code.<br>
	 *  c) 500: No class can have more than 500 lines of code.<br>
	 * 	<p>   
	 *  10. <b>Usage of Design Patterns</b><br>
	 *  <br>
	 *  Design patterns help developers to incorporate best Software Design Principles in their software.<br> 
	 *  They also provide common platform for developers across the globe. <br>
	 *  They provide standard terminology which makes developers to collaborate and easier to communicate to each other.<br>
	 * 	<p> 	  
	 *  11. <b>Document ideas</b><br>
	 *  <br>
	 *  Never just start writing code. Strategize, Prepare, Design, Document, Review and Implementation.<br> 
	 *  First of all, jot down your requirements. Prepare a design document. Identify and mention assumptions properly.<br> 
	 *  Write higher level pseudo code of the overall system as well as each function so as to flesh out errors earlier in the development lifecycle.<br>
	 *  Get the documents peer reviewed and take a sign off on them.<br>
	 *  <br>
	 *  As the saying goes: "use javadoc liberally with feelings of unbridled joy ;)". Documenting the code written is good not only for the developer, but also for the poor fellow who will be inheriting the code you wrote.<br>
	 * 	<p> 	  
	 *  12. <b>Use "equals" over ==</b><br>
	 *  <br>
	 *  == compares object references or the memory address (C, C++ pointer) of the objects, it checks to see if the two operands point to the same object (not equivalent objects, the same object).<br>
	 *  On the other hand, “equals” perform actual comparison of two strings.<br>
	 * 	<p> 	  
	 *  13. <b>Name method carefully</b><br>
	 *  <br>
	 *  a) A good coding pattern when it comes to method names is the intention-revealing method-names pattern.<br> 
	 *  b) Using short method names may seem easy at first but after several months the name will not mean anything to you nor the guy after you.<br>
	 *  c) Its recommended to use a meaningful method name that adequately conveys the function or purpose of the method. Even familiar abbreviations may get confusing after a while.<br>
	 *  d) Method names should be verbs e.g. getHelloWorld(), searchForKeyvalue(), setValue() etc<br>
	 *  e) Boolean methods should be isConnected(), hasMutex() etc<br>
	 * 	<p> 	  
	 *  14. <b>Use consistent coding style and variable naming</b><br>
	 *  <br>
	 *  a) Bracketology<br>
	 *  <br>
	 *  The way how brackets are placed in the code is really a matter of preference as it does not affect the code execution etc. However, for the sake of readability and consistency the preferred coding style is highly recommended:<br>
	 *  <br>
	 *  This coding style is recommended - GOOD:
	 * 	<pre>
	 * 	{@code  
	 *  	public static void main(String[] args) {
	 *  	}
	 *  }
	 * 	</pre>  
	 *  This format and its variations are not recommended - BAD:
	 * 	<pre>
	 * {@code  
	 *  	public static void main(String[] args)
	 *    	{
	 *    	}
	 *  }
	 * 	</pre>
	 *  b) Variable naming<br>
	 *  <br>
	 *  <ul>
	 *  	<li> give meaningful names</li>
	 *  	<li> shortest name that conveys purpose of variable</li>
	 *  	<li> avoid similar names</li>
	 *  	<li> use most descriptive name</li>
	 *  	<li> follow company code convention, if it exists.<br>
	 *   		<ul>
	 *          	<li>Start name of class as capital letter e.g. Employee, Student or Thread.</li>
	 *          	<li>Start name of method from small character and follow camel case e.g. getEmployee(), getPayDate() etc.</li>
	 *          	<li>Use camel case in variable names as well e.g. price, quantity, totalAmount etc.</li>
	 *          	<li>Use all caps for constants in Java e.g. MAX_QUANTITY, MAX_PRICE etc.</li>
	 *          	<li>follow bean naming convention, because many open source framework use reflection, which works on bean naming convention.</li>
	 *   		</ul>
	 *   	</li>          
	 *  	<li> class names should be nouns e.g. Car, House, School etc.</li>
	 *  	<li> make good use of verbs, especially for boolean methods and operations.</li>
	 *  </ul>  
	 * 	<p> 	
	 *  15. <b>Use API libraries preferentially</b><br>
	 *  <br>
	 *  a) Adopt the "proudly found elsewhere" (PFE) or "invented elsewhere" mentality.<br>
	 *  b) No need to reinvent the wheel.<br>
	 *  c) Software(s) can be developed much faster and is likely to be a lot more stable.<br>
	 *  d) APIs libraries tend to be more robust and errors affecting you are that you may not even be aware of may get resolved during library patches.<br>
	 * 	<p>	
	 *  16. <b>S.O.L.I.D. Class Design Principle</b><br>
	 *  <br>
	 *  <b>S</b>ingle responsibility principle:	<br>
	 *  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A class should have one and only one task/responsibility. If class is performing more than one task, it leads to confusion.<br>
	 *  <b>O</b>pen/closed principle:	<br>
	 *  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The developers should focus more on extending the software entities rather than modifying them.<br>
	 *  <b>L</b>iskov substitution principle:	<br>
	 *  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;It should be possible to substitute the derived class with base class.<br>
	 *  <b>I</b>nterface segregation principle:	<br>
	 *  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;It’s like Single Responsibility Principle but applicable to interfaces. Each interface should be responsible for a specific task. The developers should need to implement methods which he/she doesn’t need.<br>
	 *  <b>D</b>ependency inversion principle:<br>	
	 *  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Depend upon Abstractions- but not on concretions. This means that each module should be separated from other using an abstract layer which binds them together.<br>
	 */

public class JavaBestPracticeNotes {
}
