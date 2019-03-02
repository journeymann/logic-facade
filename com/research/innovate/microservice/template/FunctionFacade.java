/**
 * 
 */
package com.research.innovate.microservice.template; 

import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.json.JsonSanitizer;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  09/14/2018
 * @version 1.0
 * 
 * abstract facade design pattern implementation with various functions used throughout application.
 * Intention is to avoid unnecessary code repetition and provide uniform behavior for common functionality.
 *
 */
public abstract class FunctionFacade {
	
	/**
	 * This method effeciently searches a <code>T</code> array primitive for element
	 * 
	 * @param array primitive of generic type <code>T</code>  
	 * @param generic type <code>T</code> value to be found 	 
	 * @returns boolean flag indicating presence of element
	 */    
	public static <T> boolean arrayContains(T[] array, T value) {
    		return Arrays.stream(array).anyMatch(value::equals);
	}
	
	/**
	 * This method prints a given <code>Map</code> as a string.  
	 * 
	 * @param map accepts a <code>Map<String, String></code> as input to be formatted as a <code>String</code> 
	 * @returns The string <code>String</code> representation of the Set data type
	 */    
	public static String printMap(@NotNull final Map<String, String> map) {
		return Arrays.toString(map.entrySet().toArray());
	}

	/**
	 * Stream API implementation of filter for a given source list collection <code>List</code> of input values.
	 * (Generalized Target-Type Inference <T> is used)
	 * 
	 * @param predicate <code>Predicate</code> specifying filter conditions
	 * @param source data list <code>List</code> to be filtered based on predicate <code>Predicate</code>
	 * @return <code>List</code> resulting list of values that satisfy predicate conditions 
	 */
	public static <T> List<T> filter(@NotNull final Predicate<T> predicate, @NotNull final List<T> source) {
		return source.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * This method prints a given <code>List</code> as a string.  
	 * 
	 * @param list accepts a <code>List<?></code> as input to be formatted as a <code>String</code> 
	 * @returns The string <code>String</code> representation of the Set data type
	 */	
	public static String printList(@NotNull final List<?> list) {
		return Arrays.toString(list.toArray());
	}

	/**
	 * This method prints a given <code>Set</code> as a string.  
	 * 
	 * @param set accepts a <code>Set</code> as input to be formatted as a <code>String</code> 
	 * @returns The string <code>String</code> representation of the Set data type
	 */	
	public static String printSet(@NotNull final Set<?> set) {
		return Arrays.toString(set.toArray());
	}

	/**
	 * Stream API implementation of Utility function that performs the map transform operation on a list List<T> of one type of
	 * objects to a list List<R> of another unrelated type. (Generalized Target-Type Inference <T,R> is used)
	 * 
	 * <p>
	 * <i>usage: </i> map(myObject::getField, myHashtable)
	 * <i>usage: </i> map(() -> myObject.getField(), myHashtable) 
	 * 
	 * @param function mapping function <code>Function<T,R></code> @see java.util.Function
	 * @param source <code>List<T></code> list
	 * @return transformed list <code>List<R></code>
	 */
	public static <T,R> List<R> map(@NotNull final Function<T,R> function, @NotNull final List<T> source){

		return source.stream().map(function).collect(Collectors.toList());
	}	
	
	/**
	 * This function binds the input data generic type T to the input function 
	 * <code>Function</code>
	 * 
	 * <p>
	 * <i>usage: </i> bind(myObject::getField, "message: ")
	 * 
	 * @param fn function <code>Function</code> defining the operation
	 * @param val input generic type value
	 * @return <code>Supplier</code> data bound function
	 */
	public static <T, R> Supplier<R> bind(@NotNull final Function<T,R> fn, @NotNull final T val) {
	    return () -> fn.apply(val);
	}
	
	/**
	 * Java Function that takes a function <code>Function</code> as predicate and 
	 * performs the operation defined by the parameter function on the 
	 * <code>Map</code> collection keySet. 
	 * 
	 * <p>
	 * <i>usage: </i> transformKey(myHashtable, String::toLowercase);<br> 
	 * <i>usage: </i> transformKey(myHashtable, () -> String.toLowercase()); 
	 * 
	 * @param input map <code>Map</code> collection 
	 * @param function <code>Function</code> defining the operation
	 * @return map <code>Map</code> with output values 
	 */
	public static <X, Y, Z> Map<Z, Y> transformKey(@NotNull final Map<? extends X, ? extends Y> input,
			@NotNull final Function<X, Z> function) {
	    return input.keySet().parallelStream()
	        .collect(Collectors.toMap(key -> function.apply(key),
	                                  key -> input.get(key)));
	}    	
	
	/**
	 * Java Function that takes a function <code>Function</code> as predicate and 
	 * performs the operation defined by the parameter function on the 
	 * <code>Map</code> collection Values. 
	 *
	 * <p>
	 * <i>usage: </i> transformValue(myHashtable, String::toLowercase); <br> 
	 * <i>usage: </i> transformValue(myHashtable, () -> String.toLowercase()); 
	 * 
	 * @param input map <code>Map</code> collection 
	 * @param function <code>Function</code> defining the operation
	 * @return map <code>Map</code> with output values 
	 */
	public static <X, Y, Z> Map<X, Z> transformValue(@NotNull final Map<? extends X, ? extends Y> input,
			@NotNull final Function<Y, Z> function) {
	    return input.keySet().parallelStream()
	        .collect(Collectors.toMap(Function.identity(),
	                                  key -> function.apply(input.get(key))));
	}
	
	/**
	 * Method useful for solving the age old problem of copying collection objects that java refuses to do 'naturally'.
	 * 
	 * @param list <code>List</code> of objects.
	 * @param targetCollection
	 */
    public static <T> void copyListElements(final @NotNull List<T> list, @NotNull Supplier<Collection<T>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }	
	
    /**
     * Allows bulk cast conversion of list of related objects
     * 
     * @param list input <code>List</code> of objects
     * @param klass Target class to bulk convert list classes to.
     * @return list <code>List</code> of converted elements
     */
    public static <T,S> List<T> convertModelList(@NotNull final List<S> list, @NotNull final Class<T> klass) {
    	
    	return FunctionFacade.map(s -> klass.cast(s), list);
    }
    
    /**
     * Initializes a List collection of any type. 
     * 
     * @param list <code>List</code> initialized of type <T> generic
     */
    public static <T> void initializeModelList(@NotNull List<T> list) {
    	
        copyListElements(list, () -> new ArrayList<T>());
    }
    
    /**
     * Sorts a given list <code>List</code> by a parameter provided sort function
     * 
	 * <p>
	 * <i>usage: </i> sortListByFunction([myArrayList<Attribute>], Attribute::getFirst_name); <br> 
     *
     * @param models <code>List</code> objects of any type
     * @param function <code>Function</code> defining the rule operation to apply in order to sort the data 
     * @return list <code>List</code> collection of sorted objects
     */
	public static <T> List<T> sortListByFunction(@NotNull final List<T> list, @NotNull final Function<T, String> function) {	
		
    	return list.stream().sorted(Comparator.comparing(function)).collect(Collectors.toList());
	}    
}
