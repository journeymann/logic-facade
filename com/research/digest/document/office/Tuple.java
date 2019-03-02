/**
 * 
 */
package com.research.digest.document.office;

/**
 * @author cgordon
 * 
 * General purpose Tuple implementation of key/value data-type for use with collection data structures such as List/Maps etc 
 *
 */
public class Tuple<K, V> 
{
	  private final K k;
	  private final V v;
	  
	  public Tuple(K key, V value) {
	    k = key;
	    v = value;
	  }
	  
	  public K getKey(){
		  return k;
	  }
	  
	  public V getValue(){
		  return v;
	  }
	  
	  public String toString() {
	    return String.format("KEY: '%s', VALUE: '%s'", k, v);
	  }

	  /**
	   * Static initialization implement 'of' create design pattern. Creates object instance of Tuple class. 
	   * 
	   * @param k generic key
	   * @param v generic value
	   * @return Object instance
	   */
	  public static <K,V> Tuple<K,V> of(K k, V v){
	     return new Tuple<K,V>(k, v);
	  }
	  
	  @Override
	  public boolean equals(Object o) {
	     if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;

	     Tuple<?, ?> tuple = (Tuple<?, ?>) o;
	     if (!k.equals(tuple.k)) return false;
	     return v.equals(tuple.v);
	  }

	  @Override
	  public int hashCode() {
	     int result = k.hashCode();
	     result = 31 * result + k.hashCode();
	     return result;
	  }	  
}
