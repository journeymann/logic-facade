/**
 * 
 */
package com.research.types.trees.bplus.modern;

import com.research.types.common.Data;
import com.research.types.common.DataHelper;

import org.junit.Test;
import org.junit.Before;

/**
 * @author cgordon
 *
 */
/** A JUnit test class for the B+ tree-based dictionary. */
public class BPTreeTest extends junit.framework.TestCase
{
	private static int MAX = 10;
	private Dictionary<Integer, BPNode<Integer, Data>> D1;
	private DataHelper helper = new DataHelper();

	/**
	 * This method is automatically called once before each test case
	 * method, so that all the variables are cleanly initialized for
	 * each test.
	 */
	@Before
	public void setUp()
	{
		D1 = new BPTree<Integer, BPNode<Integer, Data>>();

		for(int i=0; i < MAX;i++) {
			Integer key = i;
			Data data = helper.getRandomData();

			BPLeaf<Integer, Data> node = new BPLeaf<Integer, Data>(key, data);
			//System.out.printf("Insert: %s, ", data);
			System.out.printf("Node: %s, leaf? : %s \n", node, node.isLeaf());
			D1.insert(key, node);
		}

	}

	@Test
	public void testDict() {
		setUp();
		D1.print();	
		D1.printTree();
	}
}