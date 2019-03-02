package com.research.digest.validate;
/**
 * Summary of Regular Expression Constructs (Source: Oracle Website)
 *
 * Characters
 * 
 * x	The character x
 * \\	The backslash character
 * \0n	The character with octal value 0n (0 <= n <= 7)
 * \0nn	The character with octal value 0nn (0 <= n <= 7)
 * \0mnn	The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7)
 * \xhh	The character with hexadecimal value 0xhh
 * \\uhhhh	The character with hexadecimal value 0xhhhh (value escaped for javadoc)
 * \x{h…h}	The character with hexadecimal value 0xh…h (Character.MIN_CODE_POINT <= 0xh…h <= Character.MAX_CODE_POINT)
 * \t	The tab character (‘\u0009’)
 * \n	The newline (line feed) character (‘\u000A’)
 * \r	The carriage-return character (‘\u000D’)
 * \f	The form-feed character (‘\u000C’)
 * \a	The alert (bell) character (‘\u0007’)
 * \e	The escape character (‘\u001B’)
 * \cx	The control character corresponding to x
 *  
 * Character classes
 * [abc]	a, b, or c (simple class)
 * [^abc]	Any character except a, b, or c (negation)
 * [a-zA-Z]	a through z or A through Z, inclusive (range)
 * [a-d[m-p]]	a through d, or m through p: [a-dm-p] (union)
 * [a-z&&[def]]	d, e, or f (intersection)
 * [a-z&&[^bc]]	a through z, except for b and c: [ad-z] (subtraction)
 * [a-z&&[^m-p]]	a through z, and not m through p: [a-lq-z](subtraction)
 *  
 * Predefined character classes
 * .	Any character (may or may not match line terminators)
 * \d	A digit: [0-9]
 * \D	A non-digit: [^0-9]
 * \s	A whitespace character: [ \t\n\x0B\f\r]
 * \S	A non-whitespace character: [^\s]
 * \w	A word character: [a-zA-Z_0-9]
 * \W	A non-word character: [^\w]
 *  
 * Boundary matchers
 * ^	The beginning of a line
 * $	The end of a line
 * \b	A word boundary
 * \B	A non-word boundary
 * \A	The beginning of the input
 * \G	The end of the previous match
 * \Z	The end of the input but for the final terminator, if any
 * \z	The end of the input
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * @version 1.0<br>
 * {@literal @}created 04-13-2017<br><br> 
 *  
 * Purpose of method is to highlight the various ways that a regular expression can be evaluated.<br>
 *
 * Compare String.matches(String regex) VS Pattern.matches(String regex, CharSequence input)<br><br>
 * Implementation of String.matches(String regex):
 *  <pre>
 *  {@code 
 * 	public boolean matches(String regex) {
 *     		return Pattern.matches(regex, this);
 * 	}
 *  }
 *  </pre>
 *  <p>
 *  Implementation of Pattern.matches(String regex, CharSequence input): 
 *  <pre>
 *  {@code 
 * 	public static boolean matches(String regex, CharSequence input) {
 *     		Pattern p = Pattern.compile(regex);
 *     		Matcher m = p.matcher(input);
 *     		return m.matches();
 * 	}
 *  }
 *  </pre>
 *  <p>
 *  Conclusion: str.matches(regex) is exactly the same as Pattern.compile(regex).matcher(str).matches().<br>
 *  Note: Same as matches(), not same as find().<br>
 *  Using Pattern.compile() is better/required if:<br><br>
 * 		You need access to the Matcher.<br>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;E.g. you need the result of capture groups.<br>
 * 		You do the same matches(regex) call many times. Only compiling the regex pattern once improves performance.<br>
 *  <br>
 */

public class RegexMatches
{
    /** Common regular expressions used to validate email addresses. */
    private final String REGEXP_EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern mypattern = Pattern.compile(REGEXP_EMAIL);

    public static void main( String args[] ){
    	
    	RegexMatches regexMatches = new RegexMatches();
    	
        String valEmail1 = "testemail@domain.com";
        String invalEmail1 = "....@domain.com";
        String invalEmail2 = ".$$%%@domain.com";
        String valEmail2 = "test.email@domain.com";
		
	System.out.printf("Type 1:\n");
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_1(valEmail1));
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_1(invalEmail1));
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_1(invalEmail2));
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_1(valEmail2));
	System.out.printf("\nType 2:\n");
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_2(valEmail1));
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_2(invalEmail1));
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_2(invalEmail2));
        System.out.printf("Is Email ID1 valid? %s \n", regexMatches.validateEMailID_2(valEmail2));
    }

	/** This method uses the matches() function of the Pattern regex class/util to evaluate the regular expression 
	 * 
	 * @param emailID to validate
	 * @return boolean flag validation success
	 */
    public boolean validateEMailID_1(String emailID) {
        Matcher mtch = mypattern.matcher(emailID);
        if(mtch.matches()){
            return true;
        }
        return false;
    }  

	/** This method uses the java.lang String implementation of matches() function to evaluate the regular expression
	 * 
	 * @param emailID to validate
	 * @return boolean flag validation success
	 */
    public boolean validateEMailID_2(String emailID) {
        
        if(emailID != null && emailID.matches(REGEXP_EMAIL)){
            return true;
        }
        return false;
    }  
}		
