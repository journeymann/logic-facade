/**
 * 
 */
package com.research.functions.primes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cgordon
 *
 * heuristic note on prime numbers is that all primes are odd numbers (except 2), so short cut by logic: num/2 % 0 == 0 is not a prime!
 * this segments the candidate number set by half!   
 * since 2 is identity of the 'even number set/group' then only use odd numbers to test primality. - heuristic
 * since the factor of any number must be at the very most half its value. - heuristic
 * also if exists x such that x>1 && x<prime then not prime - heuristic
 * 
 * additional capability added: java stream api privides parallel / batch processing of collections. blocks of potential primes are harvested within
 * the user defined range of numbers using the BigInteger .isPrimeCandidate() method. The reduced dataset is then passed into the stream api filter 
 * method which concurrently called a test method intended to verify blocks of potential prime numbers.   
 *  
 */
public class ProximaPrime {

	
	// simplification constants
	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO =  new BigInteger("2");
	private BigInteger BLOCKSIZE = BigInteger.valueOf(100);
	//private static final AtomicInteger counter = new AtomicInteger(0);

	public static void main(String[] args) {

		ProximaPrime prime = new ProximaPrime();
		BigInteger seed = new BigInteger("100000");//LargePrimeSeed.getPrimeSeed();

		System.out.printf("Start::\n");
		/**
		 * Since the only even prime number is 2 then add heuristic to ignore even numbers by starting
		 * with odd (existing non 2 prime) and increment by two.
		 */
		List<BigInteger> values = prime.getPrimesInRange(seed);
		if(!values.isEmpty()) {
			System.out.printf("found! size: %s \n", values.size());
			System.out.printf("values %s \n", prime.print(values));
		}else{
			System.out.printf("Not found! start: %s \n", seed);
		}
	}

	/**
	 * This method is intended to produce a list of prime numbers based on the starting point up to a user defined ceiling.
	 *  
	 * @param start is the <code>BigInteger</code>starting point from which to determines primes
	 * @return Collection <code>List</code> [large] prime numbers
	 */
	public List<BigInteger> getPrimesInRange(BigInteger start) {

		BigInteger ceiling = start.add(BigInteger.valueOf(100000));//start.multiply(TWO); //artificial ceiling
		
		List<BigInteger> rangeValues, results = Collections.synchronizedList(new ArrayList<BigInteger>());
		
		/** initialize */
		BigInteger min = start;
		BigInteger max = start.add(BLOCKSIZE);
		rangeValues = this.narrow(min, max);
		
		/** processing */
		do {
			rangeValues.stream()
			.filter(val -> this.verify(val))
            .collect(Collectors.toList()); // heuristic here take advantage of stream api batch/parallel processing
			
			if(!rangeValues.isEmpty()) { 
				//System.out.printf("Prime(s) found!:: size: %d \n", rangeValues.size());
				synchronized (results) { results.addAll(rangeValues);};
			}
			
			min= max;
			max = max.add(BLOCKSIZE);
			rangeValues = this.narrow(min, max);
			
		}while(max.compareTo(ceiling) < 0);
		
		return results;
	}

	/**
	 * This method is intended to verify if the parameter passed is a prime number.
	 *  
	 * @param value is the <code>BigInteger</code> that is to be tested for primality
	 * @return boolean flag if number is a prime
	 */
	public boolean verify(BigInteger value) {
		
		if(value.mod(TWO).equals(ZERO)) return false; // heuristic here primes are odd numbers (except 2)
		
		BigInteger half = value.divide(TWO); // heuristic here max numerical factor necessarily less than half candidate number
		
		for (BigInteger i = TWO; i.compareTo(half) < 0 ; i = i.add(ONE)) {
			
			if (value.mod(i).equals(ZERO)) {
				return false;
			}	
		}	

		return true;
	}
	/**
	 * This method is used to narrow the list of possible prime numbers, in keeping with the strategy of
	 * an intelligent heuristic to determine prime numbers.  
	 *  
	 * @param start is the <code>BigInteger</code>starting point from which to determines primes
	 * @param max is the <code>BigInteger</code> value that defines the upper limit of primes to test/verify.  
	 * @return Collection <code>List</code> candidates of [large] prime numbers
	 */
	public List<BigInteger> narrow(BigInteger start, BigInteger max) {
		
		List<BigInteger> list = new ArrayList<BigInteger>();
		
		for (BigInteger i = start; i.compareTo(max) < 0 ; i = i.add(ONE)) {
			
			if(i.mod(TWO).equals(ZERO)) continue; // heuristic here primes are odd numbers (except 2)
			
			if(i.isProbablePrime(1)) list.add(i);
		}	

		return list;
	}
	
	/**
	 * This method is used to print the <code>List</code> collection as a newline delimited string  
	 *  
	 * @param List array collection <code>List</code> of values to print to string
	 * @return String <code>String</code> representation of contents of the input collection
	 */
	private String print(List<BigInteger> values) {
		
		StringBuffer output= new StringBuffer(""); // for speed and memory performance optimize
		
		for(BigInteger value : values) {
			
			output.append(value).append("\n");
		}
		
		return output.toString();
	}
	
}
