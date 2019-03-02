package com.research.digest.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author cgordon
 * 
 * This is a Java class intended to showcase common low level bit manipulation operations in Java (Java 1.8).
 * The program accepts a text file as input then performs a random bitwise overlay of each character in the file. 
 * Specific overlays are defined for alphabet characters as compared to integer digits. non alphanumeric values remain unchanged.
 * The overall effect is that the output file looks very similar in structure to the input file but the characters are all different. 
 *
 * This class and it operation may be a very useful tool for scrubbing personal identifiable information (PII)
 * data and data files for use in functional test verification and quality assurance testing situations.  
 *
 */
public class DataScrub {
	
	public static void main(String[] args){

		if (args.length == 0) {
			System.out.printf("Usage: program [filename]. \n");
			System.out.printf("Please enter a valid file name. \n");	
			return;
		}
		
		String source = args[0];
		
		if(!(new File(source)).exists()) {
			System.out.printf("ERROR:: filename (%s) entered does not exist. \n", source);
			return;
		}
		
		String plaintext = "\u200B";
		DataScrub dataScrub = new DataScrub();
		
		try{
			plaintext = dataScrub.readFile(source);	
		}catch(IOException e){
			System.out.printf("ERROR:: ", e.getMessage());
		}
		
		String obscure = dataScrub.blend(plaintext);
		System.out.printf("CHARSIZE::\n %d \nINPUT::\n %s \n\nCYPHER::\n %s \n\n", Character.BYTES,plaintext,obscure);
	
		String output = source.substring(0, source.lastIndexOf(".")) + ".cfr";
		dataScrub.writeFile(output, obscure);
	}

	
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
	
	/**
	 * Java method writes data provided to the specified file name passed.  
	 * 
	 * @param String <String> filename.
	 * @param <String> data with contents to be written to disc.
	 * @return boolean primitive flag of write success.
	 * @throws none
	 */
	private boolean writeFile(String filename, String data){
		
		Writer writer = null;
		boolean success = true;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
		    writer.write(data);
		} catch (IOException e) {
			System.out.printf("ERROR:: ", e.getMessage());
			success = false;
		} finally {
		   try {
			   writer.close();
		   } catch (Exception e) {
			   System.out.printf("ERROR:: ", e.getMessage());
		   }
		}
		   return success;		
	}

	/**
	 * Java method writes data provided to the specified file name passed.  
	 * 
	 * @param String <String> input text data to be scrubbed / obfuscate.
	 * @return <String> contents of file after obfuscation.. 
	 * @throws none
	 */
	private String blend(String input){

		StringBuffer output = new StringBuffer();
		
		for(char chr: input.toCharArray()){

			char rc = (char) (regexp(chr, "^[a-zA-Z-]+$")? _scrub(chr, _CH_TOKEN) & 0xff :  _scrub(chr, _INT_TOKEN) & 0xff);
				
			output.append(rc);			
		}
		
		return output.toString();
	}

	/**
	 * Java method accepts a char value and generates a random byte value and then performs a logical XOR (Exclusive OR) bitwise operation 
	 * to produce a arbitrary value replacement. The integer precision type numbers are not affected by this operation.      
	 * 
	 * @param char (primitive) input value to be processed.
	 * @param byte value representing type of data operation to use for scrub operation. 
	 * @return byte (primitive) value of char (primitive) after bitwise operation o input 
	 * @throws none
	 */
	private byte _scrub(char chr, byte token){
		
		if(token == _INT_TOKEN && !regexp(chr, "^[0-9]+$")) return (byte)chr;
		byte overlay = (byte)((Math.random() * (token)) + _BASE);
		byte unsigned = (byte)((((byte) chr) << 24) >>> 24); //convert signed int (char) to unsigned.
		byte signed = (byte)(((overlay ^ unsigned) << 24) >> 24); //reverse operation
	
		return signed;
	}
	
	/**
	 * Java method purpose is to process a param provided regular expression against the given string value.   
	 * 
	 * @param character to be evaluated.
	 * @param Regular expression <String> to be evaluated. 
	 * @return boolean primitive flagging results of regular expression execution. 
	 * @throws none
	 */
	private boolean regexp(char chr, String expression){
		
		return String.valueOf(chr).matches(expression);
	}

	private final byte _INT_TOKEN = 0x02;
	private final byte _CH_TOKEN = 0x08;
	private final byte _BASE = 0x00;
	
}
