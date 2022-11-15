package com.example.houseofprayerlogistics.Trees;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class BinaryTrees {

  @Test
  public void getPairs(){
    Map<Byte, Byte> numberMap = new LinkedHashMap<>();
    byte [] pairs = {0};
    byte target = 8;
    Arrays.asList(1, 1, 0, 7, 8, 9, 3).forEach(value -> {
      byte diff = (byte) (target -  value);
      if(numberMap.containsKey(diff)){
        pairs[0]++;
      }else{
        numberMap.put( Byte.valueOf(value.toString()), Byte.valueOf(value.toString()));
      }
    });

    Assert.assertEquals(2, pairs[0] );
  }

  /**
   * Tree traversal methods are:
   * 1, InOrder ( Left -> Root -> Right node(s) )
   * 2, PreOrder ( Root ->  Left -> Right node(s) )
   * 3, PostOrder ( Left -> Right -> Root node(s) )
   */
  @Test
  public void testTreeTrasversalMethods(){
    //Tree traversal methods include

    BinaryTree tree = new BinaryTree();
    tree.root = new Node(1);
    tree.root.left = new Node(12);
    tree.root.right = new Node(9);
    tree.root.left.left = new Node(5);
    tree.root.left.right = new Node(6);

    System.out.println("Inorder traversal");
    tree.traverseInorder(tree.root);

    System.out.println();
    System.out.println("Preorder traversal");
    tree.traversePreorder(tree.root);

    System.out.println();
    System.out.println("Postorder traversal");
    tree.traversePostorder(tree.root);

    if (tree.isFullBinaryTree(tree.root)) {
      System.out.println("Full Binary Tree");
    }
    else{
      System.out.println("Tree is not a full binary tree");
    }
  }

  @Test
  public void checkForFullBinaryTree(){

  }

  /**
   * Working with tree based structure
   */
  class Node{

    int data;
    Node left, right;

    Node(int data){
      this.data = data;
      left = right = null;
    }
  }

  class BinaryTree{

    Node root;

    BinaryTree(){
      root  = null;
    }

    void traverseInorder(Node node){
      if(node == null)
        return;
      traverseInorder(node.left);
      System.out.print(node.data + " -> ");
      traverseInorder(node.right);
    }

    void traversePreorder(Node node){
      if(node == null)
        return;
      System.out.print(node.data + " -> ");
      traversePreorder(node.left);
      traversePreorder(node.right);
    }

    void traversePostorder(Node node){
      if(node == null)
        return;
      traversePostorder(node.left);
      traversePostorder(node.right);
      System.out.print(node.data + " -> ");
    }

    boolean isFullBinaryTree(Node root){
      if(root == null)
        return true;

      if(root.left == null && root.right == null)
        return true;

      if(root.left != null && root.right != null)
        return isFullBinaryTree(root.left) && isFullBinaryTree(root.right);

      return false;
    }

  }
}
