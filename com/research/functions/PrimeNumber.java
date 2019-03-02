/**
 * 
 */
package com.research.functions;

/**
 * @author cgordon
 * 
 * 
 * heuristic note on prime numbers is that all primes are odd numbers (except 2), so short cut by logic: num/2 % 0 == 0 is not a prime!
 * this segments the candidate number set by half!   
 * since 2 is identity of the 'even number set/group' then only use odd numbers to test primality.
 * since the factor of any number must be at the very most half its value. 
 *
 */
public class PrimeNumber {

	public boolean isPrime(final Long input) {
		
		long max = input / 2; //evaluate once 
		if(input%2==0) return false;
		
		for (long i = 3; i < max; i+=2) {
			if (input % i == 0) {
				return false;
			}
		}
		return true;
	}
}
