package hua.lee.test;

public class BinaryTest {
    public static void main(String[] args) {

    }
    private static void invertTree(TreeNode node){
        if (node != null){
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            invertTree(node.left);
            invertTree(node.right);
        }
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
/**
  * Definition for a binary tree node.
  * public class TreeNode {
  *     int val;
  *     TreeNode left;
  *     TreeNode right;
  *     TreeNode() {}
  *     TreeNode(int val) { this.val = val; }
  *     TreeNode(int val, TreeNode left, TreeNode right) {
  *         this.val = val;
  *         this.left = left;
  *         this.right = right;
  *     }
  * }
  */
//class Solution {
//public TreeNode invertTree(TreeNode root) {
//
//        }
//}

