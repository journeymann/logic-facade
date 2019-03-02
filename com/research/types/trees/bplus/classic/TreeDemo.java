package com.research.types.trees.bplus.classic;

public class TreeDemo {

	public int Start() {
		Tree root;
		boolean bool;
		int nti;

		root = new Tree();
		bool = root.Init(16);
		bool = root.Print();
		System.out.println(100);
		bool = root.Print();
		System.out.println("After Inserting data is! ");
		bool = root.Insert(8);
		bool = root.Insert(24);
		bool = root.Insert(40);
		bool = root.Insert(12);
		bool = root.Insert(20);
		bool = root.Insert(28);
		bool = root.Insert(14);
		bool = root.Insert(25);
		bool = root.Insert(25);
		bool = root.Print();

		System.out.println("Search value is: " + root.Search(24));
		System.out.println("Search value is: " + root.Search(12));
		System.out.println("Search value is: " + root.Search(16));
		System.out.println("Search value is: " + root.Search(50));
		System.out.println("Search value is: " + root.Search(12));
		System.out.println("Search value is: " + root.Search(25));
		System.out.println("After deleting data! ");
		bool = root.Delete(12);
		bool = root.Delete(25);
		bool = root.Print();
		System.out.println(root.Search(12));
		System.out.println("Delete value is: " + root.Delete(12));
		System.out.println("Delete value is: " + root.Delete(25));
		return 0;
	}

}
