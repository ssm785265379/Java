package com.bittree;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.geometry.Pos;
import sun.net.www.http.PosterOutputStream;

import javax.xml.soap.Node;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class BinaryTreeNode {
    String data;
    BinaryTreeNode LChild;
    BinaryTreeNode RChild;

    BinaryTreeNode(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}

class BinaryTree {
    BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    void clear() {
        clear(root);
    }

    void clear(BinaryTreeNode node) {
        if (node != null) {
            clear(node.LChild);
            clear(node.RChild);
            node = null;
        }
    }

    Boolean isEmpty() {
        return root == null;
    }

    int getHeight(BinaryTreeNode node) {
        if (node == null) return 0;
        int l = getHeight(node.LChild) + 1;
        int r = getHeight(node.RChild) + 1;
        return l > r ? l : r;
    }

    int getHeight() {
        return getHeight(root);
    }

    int getSize(BinaryTreeNode node) {
        if (node == null) return 0;
        int l = getSize(node.LChild);
        int r = getSize(node.RChild);
        return l + r + 1;
    }

    int getSize() {
        return getSize(root);
    }

    BinaryTreeNode getParent(BinaryTreeNode subTree, BinaryTreeNode cur) {
        //从头开始遍历
        if (subTree == null) return null;
        if (subTree.LChild == cur || subTree.RChild == cur)
            return subTree;
        BinaryTreeNode p = null;
        if ((p = getParent(subTree.LChild, cur)) != null) {
            return p;
        } else return getParent(subTree.RChild, cur);
    }

    void PreOrder(BinaryTreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            PreOrder(node.LChild);
            PreOrder(node.RChild);
        }
    }
    void preOrderByStack(BinaryTreeNode node){
        Stack<BinaryTreeNode> stack=new Stack<>();
        while(node!=null || !stack.isEmpty()){
            while (node!=null){
                System.out.println(node.data);
                stack.push(node);
                node=node.LChild;
            }
            if(!stack.isEmpty()){
                node=stack.pop();   //左边没有了更改node指向
                node=node.RChild;
            }
        }
    }void postOrderByStack(BinaryTreeNode node){
        Stack<BinaryTreeNode> stack=new Stack<>();
        BinaryTreeNode pre=null;
        while(node!=null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node=node.LChild;
            }
            if(!stack.isEmpty()){
                BinaryTreeNode curRChild=stack.peek().RChild;
                if(curRChild==null||curRChild==pre){  //右子树已经遍历完
                    node=stack.pop();   //更改node指向
                    System.out.println(node.data);
                    pre=node;
                }
                node=curRChild;
            }
        }
    }
    void inOrderByStack(BinaryTreeNode node){
        Stack<BinaryTreeNode> stack=new Stack<>();
        while(node!=null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node=node.LChild;
            }
            if(!stack.isEmpty()){
                node=stack.pop();   //左边没有了更改node指向
                System.out.println(node.data);
                node=node.RChild;
            }
        }
    }

    void InOrder(BinaryTreeNode node) {
        if (node != null) {
            InOrder(node.LChild);
            System.out.println(node.data);
            InOrder(node.RChild);
        }
    }

    void PostOrder(BinaryTreeNode node) {
        if (node != null) {
            PostOrder(node.LChild);
            PostOrder(node.RChild);
            System.out.println(node.data);
        }
    }

    void levelOrder(BinaryTreeNode node){
        if(node==null) return;
        LinkedList<BinaryTreeNode>q=new LinkedList<>();
        q.offer(node);
        BinaryTreeNode cur=null;
        while(!q.isEmpty()){
            cur=q.poll();
            System.out.println(cur.data);
            if(cur.LChild!=null) q.offer(cur.LChild);
            if(cur.RChild!=null) q.offer(cur.RChild);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree=new BinaryTree(new BinaryTreeNode("1"));
        BinaryTreeNode root=tree.root;
        BinaryTreeNode son=new BinaryTreeNode("2");
        root.LChild=son;
        root.RChild=new BinaryTreeNode("3");
        son.LChild=new BinaryTreeNode("4");

//        tree.PreOrder(root);
//        tree.preOrderByStack(root);
        tree.levelOrder(root);
    }
}

