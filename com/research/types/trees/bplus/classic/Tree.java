/**
 * 
 */
package com.research.types.trees.bplus.classic;

/**
 * @author cgordon
 *
 */
public class Tree {

	Tree left ;
	Tree right;
	int key ;
	boolean has_left ;
	boolean has_right ;
	Tree my_null ;

	// Initialize a node with a key value and no children
	public boolean Init(int v_key){
		key = v_key ;
		has_left = false ;
		has_right = false ;
		return true ;
	}

	// Update the right child with rn
	public boolean SetRight(Tree rn){
		right = rn ;
		return true ;
	}

	// Update the left child with ln
	public boolean SetLeft(Tree ln){
		left = ln ;
		return true ;
	}

	public Tree GetRight(){
		return right ;
	}

	public Tree GetLeft(){
		return left;
	}

	public int GetKey(){
		return key ;
	}

	public boolean SetKey(int v_key){
		key = v_key ;
		return true ;
	}

	public boolean GetHas_Right(){
		return has_right ;
	}

	public boolean GetHas_Left(){
		return has_left ;
	}

	public boolean SetHas_Left(boolean val){
		has_left = val ;
		return true ;
	}

	public boolean SetHas_Right(boolean val){
		has_right = val ;
		return true ;
	}

	// This method compares two integers and returns true if they are equal and false

	public boolean Compare(int num1 , int num2){
		boolean bool ;
		int nti ;

		bool = false ;
		nti = num2 + 1 ;
		if (num1 < num2) bool = false ;
		else if (!(num1 < nti)) bool = false ;
		else bool = true ;
		return bool ;
	}

	// Insert a new element in the tree
	public boolean Insert(int v_key){
		Tree new_node ;
		boolean bool = true;
		boolean cont ;
		int keyValue ;
		Tree cNode ;

		new_node = new Tree();
		bool = new_node.Init(v_key) ;
		cNode = this ;
		cont = true ;
		while (cont){
			keyValue = cNode.GetKey();
			if (v_key < keyValue){
				if (cNode.GetHas_Left())
					cNode = cNode.GetLeft() ;
				else {
					cont = false ;
					bool = cNode.SetHas_Left(true);
					bool = cNode.SetLeft(new_node);
				}
			}
			else{
				if (cNode.GetHas_Right())
					cNode = cNode.GetRight() ;
				else {
					cont = false ;
					bool = cNode.SetHas_Right(true);
					bool = cNode.SetRight(new_node);
				}
			}
		}
		return bool ;
	}

	// Delete an element from the tree

	public boolean Delete(int v_key){
		Tree cNode ;
		Tree parent_node ;
		boolean cont ;
		boolean found ;
		boolean is_root ;
		int keyValue ;
		boolean bool = true;

		cNode = this ;
		parent_node = this ;
		cont = true ;
		found = false ;
		is_root = true ;
		while (cont){
			keyValue = cNode.GetKey();
			if (v_key < keyValue)
				if (cNode.GetHas_Left()){
					parent_node = cNode ;
					cNode = cNode.GetLeft() ;
				}
				else cont = false ;
			else 
				if (keyValue < v_key)
					if (cNode.GetHas_Right()){
						parent_node = cNode ;
						cNode = cNode.GetRight() ;
					}
					else cont = false ;
				else { 
					if (is_root) 
						if ((!cNode.GetHas_Right()) && 
								(!cNode.GetHas_Left()) )
							bool = true ;
						else 
							bool = this.Remove(parent_node,cNode); 
					else bool = this.Remove(parent_node,cNode);
					found = true ;
					cont = false ;
				}
			is_root = false ;
		}
		return found && bool;
	}

	// Check if the element to be removed will use the, righ or left subtree if one exists

	public boolean Remove(Tree p_node, Tree c_node){
		boolean bool ;
		int auxkey1 ;
		int auxkey2 ;

		if (c_node.GetHas_Left()) 
			bool = this.RemoveLeft(p_node,c_node) ;
		else 
			if (c_node.GetHas_Right())
				bool = this.RemoveRight(p_node,c_node) ;
			else {
				auxkey1 = c_node.GetKey();
				auxkey2 = (p_node.GetLeft()).GetKey() ;
				if (this.Compare(auxkey1,auxkey2)) {
					bool = p_node.SetLeft(my_null);
					bool = p_node.SetHas_Left(false);
				}
				else {
					bool = p_node.SetRight(my_null);
					bool = p_node.SetHas_Right(false);
				}
			}
		return bool ;
	}

	public boolean RemoveRight(Tree p_node, Tree c_node){
		boolean bool ;

		while (c_node.GetHas_Right()){
			bool = c_node.SetKey((c_node.GetRight()).GetKey());
			p_node = c_node;
			c_node = c_node.GetRight() ;
		}
		bool = p_node.SetRight(my_null);
		bool = p_node.SetHas_Right(false);
		return bool ;
	}

	public boolean RemoveLeft(Tree p_node, Tree c_node){
		boolean bool;

		while (c_node.GetHas_Left()){
			bool = c_node.SetKey((c_node.GetLeft()).GetKey());
			p_node = c_node ;
			c_node = c_node.GetLeft() ;
		}
		bool = p_node.SetLeft(my_null);
		bool = p_node.SetHas_Left(false);
		return bool ;
	}		

	// Search for an elemnt in the tree
	public int Search(int v_key){
		boolean cont ;
		int ifound ;
		Tree cNode;
		int keyValue ;

		cNode = this ;
		cont = true ;
		ifound = 0 ;
		while(cont){
			keyValue = cNode.GetKey();
			if (v_key < keyValue)
				if (cNode.GetHas_Left())
					cNode = cNode.GetLeft() ;
				else cont = false ;
			else 
				if (keyValue < v_key)
					if (cNode.GetHas_Right())
						cNode = cNode.GetRight() ;
					else cont = false ;
				else { 
					ifound = 1 ;
					cont = false ;
				}
		}
		return ifound ;
	}

	// Invoke the method to really print the tree elements
	public boolean Print(){
		Tree cNode;
		boolean bool ;

		cNode = this ;
		bool = this.RecPrint(cNode);
		return bool ;
	}

	// Print the elements of the tree
	public boolean RecPrint(Tree node){
		boolean bool ;

		if (node.GetHas_Left()){
			bool = this.RecPrint(node.GetLeft());
		} else bool = true ;
		System.out.println(node.GetKey());
		if (node.GetHas_Right()){
			bool = this.RecPrint(node.GetRight());
		} 
		else bool = true ;
		return bool ;
	}

}
