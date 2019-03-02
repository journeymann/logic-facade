/**
 * 
 */
package com.research.functions.primes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author cgordon
 *
 */
public final class LargePrimeSeed {

	/**
	 * 
	 *  #  | 2p-1 			|	Digits	| Date Discovered	| Discovered By					| Method / Hardware	| Perfect Number
	 *  29 | 2110,503-1	  	|	33,265	| 1988 Jan 28 		| Walter Colquitt & Luke Welsh 	| L-L / NEC SX-2 	| 2110,502 · (2110,503-1)
	 * 
	 * https://www.mersenne.org/primes/
	 *
	 */
	private static final String source = "./LargePrimeSeed.DAT";

	public static final BigInteger getPrimeSeed(){
		
		if(!(new File(source)).exists()) {
			System.out.printf("ERROR:: filename (%s) entered does not exist. \n", source);
			return BigInteger.valueOf(0);
		}
		
		LargePrimeSeed p = new LargePrimeSeed();
		
		String raw_text = "";
		
		try{
			raw_text = p.readFile(source);	
		}catch(IOException e){
			System.out.printf("ERROR:: ", e.getMessage());
		}		
		
		return new BigInteger(raw_text);
	};
	
	/**
	 * Java method accepts input text filename and reads file from disk. The string value of the text is returned from method.  
	 * 
	 * @param String <String> filename.
	 * @return <String> contents of file.
	 * @throws IOException
	 */
	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}	
}
