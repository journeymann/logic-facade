package com.research.types.common;

import java.util.Date;

/**
 * @author cgordon
 *
 * Type definition for inner (Data) class. This is essentially generic and can be adapted to produce the 
 * desired solution.
 */
public class Data implements Comparable<Data>{
	
	long id;
	String name;
	int age;
	Date date; 
	char grade;
	
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;		
	
	@Override
	public String toString(){
		
		return String.format("Data: [id: %d, name: %s, age: %d, date:%s, grade:%s] \n", id, name,age,date,grade);
	}

	@Override
	public boolean equals(Object other) {
	    
		if (!(other instanceof Data)) {
	    	return false;
	    }
	    
	    Data that = (Data) other;
	    
	    return compareTo(that)==EQUAL ;

	    /**return this.name.equals(that.name)
	        && this.date.equals(that.date)
	        && this.id == that.id
			&& this.age == that.age		        
	        && this.grade == that.grade;*/
	}

	@Override
	public int compareTo(Data that) {
		
	    //this optimization is usually worthwhile, and can always be added
	    if (this == that) return EQUAL;	//memory operation (pointer to same address)    

	    //primitive numbers follow this form
	    if (this.id < that.id) return BEFORE;
	    if (this.id > that.id) return AFTER;

	    //all comparisons have yielded equality
	    //verify that compareTo is consistent with equals (optional)
	    assert this.equals(that) : "compareTo inconsistent with equals.";	    
		return EQUAL;
	}		
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the grade
	 */
	public char getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}

}
