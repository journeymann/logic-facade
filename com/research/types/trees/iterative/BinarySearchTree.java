/**
 * 
 */
package com.research.types.trees.iterative;

import com.research.types.common.Data;

/**
 * @author cgordon
 * @version 1.0
 *
 * Iterative implementation of the Binary Search Tree Abstract Data Type
 *
 * Oper­a­tions:
 * 
 * Insert(Data data) : Add a node the tree with value n. Its O(log n)
 * 
 * Find(Data data) : Find a node the tree with value n. Its O(log n)
 * 
 * Delete (Data data) : Delete a node the tree with value n. Its O(log n)
 * 
 * Dis­play(): Prints the entire tree in increas­ing order. O(n).
 * 
 */
public class BinarySearchTree {
	
	public Node root;
	
	public BinarySearchTree(){
		this.root = null;
	}
	
	public boolean find(Data data){
		Node current = root;
		while(current!=null){
			if(current.data.equals(data)){
				return true;
			}else if(current.data.compareTo(data) > 0){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}

	public boolean find(int id){
		Node current = root;
		while(current!=null){
			if(current.data.getId()==id){
				return true;
			}else if(current.data.getId() > id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}

	public Data peek(int id){
		
		Node current = root;
		Data data = null;
		boolean found = false;
		
		while(current!=null && !found){
			if(current.data.getId()==id){
				data = current.data; 
				found = true;
			}else if(current.data.getId() > id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		
		return found? data : new Data();
	}
	
	public boolean delete(Data data){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(!current.data.equals(data)){
			parent = current;
			if(current.data.compareTo(data) > 0){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		/** if i am here that means we have found the node
		 *Case 1: if node to be deleted has no children
		 */
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		/** Case 2 : if node to be deleted has only one child */
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			/**now we have found the minimum element in the right sub tree */
			Node successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		/** check if successor has the right child, it cannot have left child for sure
		 * if it does have the right child, add it to the left of successorParent.
		 */
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	
	public void insert(Data data){
		Node newNode = new Node(data);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(data.compareTo(current.data) < 0){				
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}
	
	public void display(Node root){
		if(root!=null){
			display(root.left);
			System.out.printf("Node: [%s]", root.data);
			display(root.right);
		}
	}
}