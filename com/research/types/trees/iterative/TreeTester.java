/**
 * 
 */
package com.research.types.trees.iterative;

import com.research.types.common.Data;
import com.research.types.common.DataHelper;

/**
 * @author cgordon
 *
 */
public class TreeTester {

	private static int MAX = 10;
	
	public static void main(String arg[]){
		BinarySearchTree b = new BinarySearchTree();
		DataHelper helper = new DataHelper();	
		int[] keys = new int[MAX];
		
		for(int i=0; i < MAX;i++) {
			Data d = helper.getRandomData();
			keys[i] = (int)d.getId();
			b.insert(d);
		}
		
		System.out.println("Original Tree : ");
		b.display(b.root);		
		System.out.println("");
		System.out.printf("Check whether Node with key: %s exists:  flag: %s  \n", keys[4], b.find(keys[4]));
		System.out.printf("Peek: key: %s | [%s]\n",keys[2], b.peek(keys[2]));
		
		System.out.printf("Delete Node with no children ( key: %s) | data: %s \n", keys[2],  b.delete(b.peek(keys[2])));		
		b.display(b.root);
		System.out.printf("\n Delete Node with one child ( key: %s) | data: %s \n", keys[4], b.delete(b.peek(keys[4])));		
		b.display(b.root);
		System.out.printf("\n Delete Node with Two children ( key: %s) | data: %s \n", keys[9], b.delete(b.peek(keys[9])));		
		b.display(b.root);
	}
	
	
}
