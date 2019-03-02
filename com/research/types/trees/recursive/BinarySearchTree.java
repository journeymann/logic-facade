/**
 * 
 */
package com.research.types.trees.recursive;

/**
 * @author cgordon
 * @version 1.0
 *
 * Recursive implementation of the Binary Search Tree Abstract Data Type
 */
import java.util.*;

public class BinarySearchTree <T extends Comparable<T>> implements Iterable<T>
{

   private Node<T> root;
   private Comparator<T> comparator;

   public BinarySearchTree()
   {
      root = null;
      comparator = null;
   }

   public BinarySearchTree(Comparator<T> comp)
   {
      root = null;
      comparator = comp;
   }

   private int compare(T x, T y)
   {
      if(comparator == null) return x.compareTo(y);
      else
      return comparator.compare(x,y);
   }

   public void insert(T data)
   {
      root = insert(root, data);
   }
   
   private Node<T> insert(Node<T> p, T toInsert)
   {
      if (p == null)
         return new Node<T>(toInsert);

      if (compare(toInsert, p.data) == 0)
      	return p;

      if (compare(toInsert, p.data) < 0)
         p.left = insert(p.left, toInsert);
      else
         p.right = insert(p.right, toInsert);

      return p;
   }

   public boolean search(T toSearch)
   {
      return search(root, toSearch);
   }
   
   private boolean search(Node<T> p, T toSearch)
   {
      if (p == null)
         return false;
      else
      if (compare(toSearch, p.data) == 0)
      	return true;
      else
      if (compare(toSearch, p.data) < 0)
         return search(p.left, toSearch);
      else
         return search(p.right, toSearch);
   }

   public void delete(T toDelete)
   {
      root = delete(root, toDelete);
   }
   
   private Node<T> delete(Node<T> p, T toDelete)
   {
      if (p == null)  throw new RuntimeException("cannot delete.");
      else
      if (compare(toDelete, p.data) < 0)
      p.left = delete (p.left, toDelete);
      else
      if (compare(toDelete, p.data)  > 0)
      p.right = delete (p.right, toDelete);
      else
      {
         if (p.left == null) return p.right;
         else
         if (p.right == null) return p.left;
         else
         {
        	/** get data from the rightmost node in the left subtree */
            p.data = retrieveData(p.left);
            /** delete the rightmost node in the left subtree */
            p.left =  delete(p.left, p.data) ;
         }
      }
      return p;
   }
   private T retrieveData(Node<T> p)
   {
      while (p.right != null) p = p.right;

      return p.data;
   }

   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      for(T data : this) sb.append(data.toString());

      return sb.toString();
   }

   public void preOrderTraversal()
   {
      preOrderHelper(root);
   }
   
   private void preOrderHelper(Node<T> r)
   {
      if (r != null)
      {
         System.out.print(r+" ");
         preOrderHelper(r.left);
         preOrderHelper(r.right);
      }
   }

   public void inOrderTraversal()
   {
      inOrderHelper(root);
   }
   
   private void inOrderHelper(Node<T> r)
   {
      if (r != null)
      {
         inOrderHelper(r.left);
         System.out.print(r+" ");
         inOrderHelper(r.right);
      }
   }

   public BinarySearchTree<T> clone()
   {
      BinarySearchTree<T> twin = null;

      if(comparator == null)
         twin = new BinarySearchTree<T>();
      else
         twin = new BinarySearchTree<T>(comparator);

      twin.root = cloneHelper(root);
      return twin;
   }
   
   private Node<T> cloneHelper(Node<T> p)
   {
      if(p == null)
         return null;
      else
         return new Node<T>(p.data, cloneHelper(p.left), cloneHelper(p.right));
   }

   public int height()
   {
      return height(root);
   }
   
   private int height(Node<T> p)
   {
      if(p == null) return -1;
      else
      return 1 + Math.max( height(p.left), height(p.right));
   }

   public int countLeaves()
   {
      return countLeaves(root);
   }
   
   private int countLeaves(Node<T> p)
   {
      if(p == null) return 0;
      else if(p.left == null && p.right == null) return 1;
      else
    	  return countLeaves(p.left) + countLeaves(p.right);
   }

   /**This method restores a BinarySearchTree given pre-order and in-order traversals*/
   public void restore(T[] pre, T[] in)
   {
      root = restore(pre, 0, pre.length-1, in, 0, in.length-1);
   }
   
   private Node<T> restore(T[] pre, int preL, int preR, T[] in, int inL, int inR)
   {
      if(preL <= preR)
      {
         int count = 0;

         while(pre[preL] != in[inL + count]) count++;

         Node<T> tmp = new Node<T>(pre[preL]);
         tmp.left = restore(pre, preL+1, preL + count, in, inL, inL +count-1);
         tmp.right = restore(pre, preL+count+1, preR, in, inL+count+1, inR);
         return tmp;
      }
      else
         return null;
   }


   /**The width of a binary tree is the maximum number of elements on one level of the tree.*/
   public int width()
   {
      int max = 0;
      for(int k = 0; k <= height(); k++)
      {
         int tmp = width(root, k);
         if(tmp > max) max = tmp;
      }
      return max;
   }
   
   /**returns the number of node on a given level*/
   public int width(Node<T> p, int depth)
   {
      if(p==null) return 0;
      else
      if(depth == 0) return 1;
      else
      return width(p.left, depth-1) + width(p.right, depth-1);
   }

   /**The diameter of a tree is the number of nodes
    * on the longest path between two leaves in the tree.
    */
   public int diameter()
   {
      return diameter(root);
   }
   
   private int diameter(Node<T> p)
   {
      if(p==null) return 0;

      int len1 = height(p.left) + height(p.right) +3;

      int len2 = Math.max(diameter(p.left), diameter(p.right));

      return Math.max(len1, len2);
   }

   public Iterator<T> iterator()
   {
      return new MyIterator();
   }
   
   private class MyIterator implements Iterator<T>
   {
      Stack<Node<T>> stack = new Stack<Node<T>>();

      public MyIterator()
      {
         if(root != null) stack.push(root);
      }
      
      public boolean hasNext()
      {
         return !stack.isEmpty();
      }

      public T next()
      {
         Node<T> cur = stack.peek();
         if(cur.left != null)
         {
            stack.push(cur.left);
         }
         else
         {
            Node<T> tmp = stack.pop();
            while( tmp.right == null )
            {
               if(stack.isEmpty()) return cur.data;
               tmp = stack.pop();
            }
            stack.push(tmp.right);
         }

         return cur.data;
      }

      public void remove()
      {

      }
   }

}

class MyComparator implements Comparator<Integer>
{
   public int compare(Integer x, Integer y)
   {
        return y-x;
   }
}