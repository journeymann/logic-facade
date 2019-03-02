/**
 * 
 */
package com.research.types.queue;

import com.research.types.common.DataHelper;
import com.research.types.common.Data;

/**
 * @author cgordon
 *
 *
 */
public class QueueTester {

	
	/**
	 *  main method for testing purposes.
	 */
	public static void main(String[] args){
		Queue A = new Queue("A");
		Queue B = new Queue("B");
		//Queue.Data data = new Queue().new Data(); //fancy way to instantiate inner class inside a static method!!

		DataHelper helper = new DataHelper();
		
		boolean stop = false; int i = 0;
		
		while(!stop){
			A.Enqueue(helper.getRandomData());
			B.Enqueue(helper.getRandomData());  
			
			if(i++ > 5) stop=true;
		}
		
		System.out.println();
		System.out.printf("Print Queue: %s | data: %s \n",A.getLabel(), A);
		System.out.printf("Print Queue: %s  | data: %s \n",B.getLabel(), B);
		System.out.printf("Queue Parity T0: Q1: %s, Q2: %s, equals: %s \n",A.getLabel(), B.getLabel(), A.equals(B));
		
		stop = false; i = 0;
		while(!stop){
			A.Enqueue(B.Dequeue());
			
			if(i++ > 2) stop=true;
		}
		System.out.println();
		System.out.printf("Print Queue: %s  | data: %s \n",A.getLabel(), A);
		System.out.printf("Print Queue: %s  | data: %s \n",B.getLabel(), B);

		A.clear();
		B.clear();
		stop = false; i = 0;
		while(!stop){
			
			Data d = helper.getRandomData();
			
			A.Enqueue(d);
			B.Enqueue(d);  
			
			if(i++ > 3) stop=true;
		}
		
		System.out.println();
		System.out.printf("Queue Parity T1: Q1: %s, Q2: %s, equals: %s \n",A.getLabel(), B.getLabel(), A.equals(B));
		
		B.Enqueue(helper.getRandomData());
		System.out.printf("Queue Parity T2: Q1: %s, Q2: %s, equals: %s \n",A.getLabel(), B.getLabel(), A.equals(B));
	}
	
}
