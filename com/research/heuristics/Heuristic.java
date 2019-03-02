/**
 * 
 */
package com.research.heuristics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cgordon
 *
 * This class provides functionality to accept an input string viz. 'ebcaon' and perform a dictionary 
 * search to find combinations of letters that form words present in the English language.
 * 
 * Operation on the data is type agnostic and able to handle any data-type (<T>).
 * 
 */
public class Heuristic {

	public static void main(String arg[]){

		TreeSet<String> tree = load();
		System.out.printf("\n\nEnglish language Lexicon was loaded into memory | size: %d words. \n", treeSize(tree));

		String term = "aardvark";
		String search = "ebcaon";
		String filter = "lifestyle";

		System.out.printf("\nSearching for: '%s' | found: '%s' \n", term, find(tree, term));		

		System.out.printf("\nDeleting: '%s' | success: '%s' \n", term, delete(tree, term));		

		System.out.printf("\nSearching for: '%s' | found: '%s' \n", term, find(tree, term));

		System.out.printf("\nLexicon print: filtered: by ('%s') \n", filter);
		printSubTree(tree, filter);

		System.out.printf("\nLexicon print: unfiltered: \n", filter);		
		printTree(tree);		

		System.out.printf("\nSearching for all lexical combinations of '%s': \n", search);

		List<String> list = heuristic(tree, search);

		if(list.isEmpty())
			System.out.printf("No matches exist \n");
		else {
			System.out.printf("\nFound value(s) : ");        	
			for(String val : list) {
				System.out.printf(" '%s',", val);
			}
		}    
		System.out.println(".\n Search complete. \n");
	}

	/** This method performs a educated guess (heuristic) of possible valid words, given an
	 * input of characters. Operation is type agnostic and able to handle any data-type (<T>). 
	 *   
	 * @param binary search tree <code>TreeSet</code> with English language dictionary 
	 * @param <code>String</code> search term on which to perform operation
	 * 
	 * @return <code>List</code> collection of words that satisfies the characters input.
	 */	
	public static <T> List<T> heuristic(TreeSet<T> tree, String searchString) {

		List<T> list = null;

		@SuppressWarnings("unchecked")
		List<T> permutations = new ArrayList<T>((HashSet<T>)permute(searchString));

		for(T perm : permutations) {

			if(find(tree, perm)) {
				list = list==null? new ArrayList<T>() : list;
				list.add(perm);
			}
		}

		return list;
	}

	/** This method outputs the size of the binary search tree. Operation is type agnostic and 
	 * able to handle any data-type (<T>).
	 *   
	 * @param binary search tree <code>TreeSet</code> with English language dictionary 
	 * 
	 * @return integer primitive size of the abstract data type Tree collection.
	 */	
	public static <T> int treeSize(TreeSet<T> tree) {

		return tree==null? 0 : tree.size();
	}

	/** This method performs a logical find operation by counting the number of occurrences of
	 * the given term within the TreeSet collection.
	 *   
	 * @param binary search tree <code>TreeSet</code> with English language dictionary 
	 * @param <code>String</code> search term on which to perform operation
	 * 
	 * @return boolean primitive flag indicating the presence of the term in the Set. 
	 */	
	public static <T> boolean find(TreeSet<T> tree, T term) {

		return (Collections.frequency(tree, term)==0? false : true);
	}

	/** This method performs a delete operation on the TreeSet dictionary collection of words.
	 *   
	 * @param binary search tree <code>TreeSet</code> with English language dictionary 
	 * @param <code>String</code> search term on which to perform operation
	 * 
	 * @return boolean primitive flag indicating the success of the operation.
	 */	
	public static <T> boolean delete(TreeSet<T> tree, T term) {

		return (find(tree, term))?  tree.remove(term) : false;
	}

	/** This method loads the dictionary into the performance optimized binary 
	 * tree <code>TreeSet</code> data structure.   
	 * 
	 * @return binary search tree <code>TreeSet</code> with English language dictionary
	 */	
	private static <T> TreeSet<T> load() {

		TreeSet<T> tree = null;

		String filename = "./resource/lexicon.txt";

		System.out.printf("loading datafile: %s \n", filename);		
		
		try {
			tree = loadTreeSet(filename);
		}catch(Exception e) {
			System.out.printf("\n\nError loading search tree into memory. error: %s \n ", e.getMessage());
		}

		return tree;
	}

	/** This method loads the dictionary into the performance optimized binary 
	 * tree <code>TreeSet</code> data structure.   
	 * 
	 * @param <code>String</code> file name/location of dictionary to load 
	 * @return binary search tree <code>TreeSet</code> with English language dictionary
	 */	
	@SuppressWarnings("unchecked")
	private static <T> TreeSet<T> loadTreeSet(String filename) {

		TreeSet<T>  tree =  null;

		try {

			tree = (TreeSet<T>)buildTreeSet(Files.readAllLines(Paths.get(filename))); 			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tree;
	}

	/** This method loads the dictionary for a given file name into the performance optimized binary 
	 * tree <code>TreeSet</code> data structure.   
	 * 
	 * @param <code>List</code> dictionary of words in an unstructured format. 
	 * @return binary search tree organized for performance optimize <code>TreeSet</code> with English language dictionary
	 */	
	private static <V extends Comparable<V>> TreeSet<V> buildTreeSet(List<V> list) {

		return new TreeSet<>(list);
	}

	/** This method performs an in-order tree traversal to print the contents of
	 *  a binary tree <code>TreeSet</code>
	 *   
	 * @param binary search tree <code>TreeSet</code> with English language dictionary 
	 */	
	public static <T> void printTree(TreeSet<T> tree) {

		//tree.forEach((v)->System.out.printf("TreeSet: %s \n", v));
		System.out.printf("TreeSet: too large to print! over 350,000 words.. \n");
	}

	/** This method performs an in-order tree traversal to print a filtered sub set 
	 * of the contents of the provided binary tree <code>TreeSet</code>. Utilizes 
	 * java 1.8 streams for parallel processing performance optimization.
	 *   
	 * @param binary search tree <code>TreeSet</code> with English language dictionary 
	 * @param <code>String</code> search term on which to perform operation
	 * 
	 */	
	public static <T> void printSubTree(TreeSet<T> tree, T key) {

		AtomicInteger atomicInteger = new AtomicInteger(0);

		tree.stream()
		   .filter(item -> item.toString().contains(key.toString()))
		   .forEach(item -> {
			   atomicInteger.getAndIncrement();
			   System.out.printf("TreeSet: index: %s | value: %s \n", atomicInteger, item);
		   });
	}

	/** This method performs a logical operation of concatenating a <code>String</code> to a 
	 * <code>HashSet</code> collections.
	 *   
	 * @param <code>String</code> to add to Set collection
	 * @param target <code>Set</code> on which to perform the concat operation
	 * @return boolean primitive flag indicating the success of the operation.
	 */	
	private static Set<String> concat(String c, Set<String> first) {
		
		HashSet<String> result = new HashSet<String>();
		
		for(String s: first) {
			result.add(c.concat(s));
		}
		
		return result;
	}

	/** This method performs the operation of iterating (tail recursion) over all possible combinations of the input 
	 * <code>String</code> and then adding them to a <code>HashSet</code> of possible permutations of the input.  
	 *   
	 * @param <code>String</code> input search term   
	 * @return <code>HashSet</code> set of possible permutations of characters present inn the input string. 
	 */	
	private static HashSet<String> permute(String input) {
		HashSet<String> set = new HashSet<String>();
		if(input.length() == 1) {
			set.add(input);
		} else {
			for(int i=0; i < input.length(); i++) {
				set.addAll(concat(String.valueOf(input.charAt(i)), permute(input.substring(0, i).concat(input.substring(i+1, input.length())))));
			}
		}
		return set;
	}	
} 
