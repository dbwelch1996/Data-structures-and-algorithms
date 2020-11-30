package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable  {

	protected Node<E> root;
	
	
	
	public BinaryTree() {
		root = null;
	}
	public BinaryTree(Node<E> root) {
		this.root = root;
	}
	public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
		root = new Node<E>(data);
		if (left != null) {
			root.left = left.root;
		}else {
			root.left = null;
		}
		
		if (right != null) {
			root.right = right.root;
		}else {
			root.right = null;
		}
		
	}

	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left !=null) {
			return new BinaryTree<E>(root.left);
		}else {
			return null;
		}
	}
	
	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right !=null) {
			return new BinaryTree<E>(root.right);
		}else {
			return null;
		}
	}
	
	public E getData() {
		return root.data;
	}
	
	public boolean isLeaf() {
		if(root == null) {
			throw new NullPointerException();
		}
		 return (root.left == null && root.right == null);
	}
	
	public String toString() {
		 StringBuilder sb = new StringBuilder();
		 toString(root, 1, sb);
		 return sb.toString();
		}
	

	private void toString(Node<E> node, int depth, StringBuilder sb) {
			 for (int i = 1; i < depth; i++) {
			 sb.append(" ");
			 }
			 if (node == null) {
			 sb.append("null\n");
			 } else {
			 sb.append(node.data);
			 sb.append("\n");
			 toString(node.left, depth + 1, sb);
			 toString(node.right, depth + 1, sb);
			 }
			}
	
	
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
		 String data = scan.nextLine().trim();
		 if (data.equals("null")) {
		 return null;
		 } else {
		 BinaryTree<String> leftTree = readBinaryTree(scan); 
		 BinaryTree<String> rightTree = readBinaryTree(scan);
		 return new BinaryTree<>(data, leftTree, rightTree);
		 }
	}
	
	 // Inner Classes
	  protected static class Node<E>
	  {    
		  protected E data;
	      protected Node<E> left = null;
	      protected Node<E> right = null;

	      protected Node(E dataItem)  //constructor
	       { 
	    	  data = dataItem;  
	       }
	      
	  } 
	
}
