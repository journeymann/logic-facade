package com.research.types.trees.bplus.modern.test;

import org.junit.Test;
import com.research.types.common.Data;
import com.research.types.common.DataHelper;
import com.research.types.trees.bplus.modern.BPLeaf;
import com.research.types.trees.bplus.modern.BPNode;
import com.research.types.trees.bplus.modern.BPTree;
import com.research.types.trees.bplus.modern.Dictionary;

import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.TestCase;

public class BPTreeTestUnit1 extends TestCase {

	private static int MAX = 10;
	private Dictionary<Integer, BPNode<Integer, Data>> D1;
	private DataHelper helper = new DataHelper();
	private Data controlcase = null;
	private Integer controlRef = 3;
	private String label = this.getClass().getName();	


	@BeforeClass
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
		System.out.printf("@BeforeClass: setup complete");
	}

	@Before
	public void executedBeforeEach() {

		System.out.println("@Before: executedBeforeEach");
	}

	@Test
	public void testControlData() {	
		System.out.println("@Test:Inside testControlData()");    
		assertEquals(controlcase, D1.find(controlRef));     
	}

	public String getLabel() {
		return label;
	}

}