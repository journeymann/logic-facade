/**
 * 
 */
package com.research.types.common;

import java.util.Map;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 * @author cgordon
 *
 */
public class DataHelper {
	
    private int MAX_AGE = 85;
    private int MAX_GRADE = 7;
    private long MAX_IDENTIFIER = 799999999;
    private long beginTime = Timestamp.valueOf("2010-01-01 00:00:00").getTime();
    private long endTime = Timestamp.valueOf("2017-12-31 00:58:00").getTime();
    
	@SuppressWarnings("serial")
	private Map<Integer, String> firstnames = new HashMap<Integer, String>() {
        {
        	put(1, "Nicolos");
        	put(2, "Junior");
        	put(3, "Sandaram");
        	put(4, "Nichole");
        	put(5, "Ahmed");
        	put(6, "Chris");
        	put(7, "Ron");	        	
        	put(8, "Katherine");	        	
    	    put(9, "Leslie");	        			
        	
        	};
    	};
        
        @SuppressWarnings("serial")
        private Map<Integer, String> lastnames = new HashMap<Integer, String>() {
            {

            	put(1, "Reid");
            	put(2, "Johnson");
            	put(3, "Gordon");
            	put(4, "Mistry");
            	put(5, "Sundaram");
            	put(6, "Gallagher");
            	put(7, "Baynes");
            	
            };
        };
        
	    
	    public String getName() {

	       	int rnd_fname = (int)Math.ceil(Math.random() * firstnames.size());
		    int rnd_lname = (int)Math.ceil(Math.random() * lastnames.size());

	    	return  (String)firstnames.get(rnd_fname).concat(" ").concat((String)lastnames.get(rnd_lname));
        }
	    
	    
	    public int getAge() {
	
		    return (int)Math.ceil(Math.random() * MAX_AGE);
        }

	    public char getGrade() {
	    	
	    	String grades = "ABCDEF";
	    	
		    return grades.charAt((int)Math.ceil(Math.random() * MAX_GRADE) % grades.length());
        }

	    public long getId() {
	    	
		    return (long)Math.ceil(Math.random() * MAX_IDENTIFIER);
        }

	    public Date getDate() {
	    	
	        long diff = endTime - beginTime + 1;
	    	
		    return new Date(beginTime + (long) (Math.random() * diff));
        }

	    public Data getRandomData(){
	    	
	    	Data data = new Data(); //fancy way to instantiate inner class inside a static method!!
	    	
	    	data.setId(this.getId());
	    	data.setName(this.getName());
	    	data.setAge(this.getAge());
	    	data.setDate(this.getDate());
	    	data.setGrade(this.getGrade());
	    	
	    	return data;
	    }
	    
}
