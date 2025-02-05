public class LowestCommonAncestorInBST {
    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * Solved
     * Medium
     * Topics
     * Companies
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * Output: 6
     * Explanation: The LCA of nodes 2 and 8 is 6.
     * Example 2:
     *
     *
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * Output: 2
     * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
     * Example 3:
     *
     * Input: root = [2,1], p = 2, q = 1
     * Output: 2
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 105].
     * -109 <= Node.val <= 109
     * All Node.val are unique.
     * p != q
     * p and q will exist in the BST.
     *
     *
     *
     * solution reference:
     *
     *
     * Approach
     * This question is not difficult if you understand the binary search tree data structure. In a binary search tree, nodes with values smaller than the parent node are gathered on the left, while nodes with larger values are gathered on the right.
     *
     * In other words, if both p and q are smaller than the parent, you move to the left. If both p and q are larger than the parent, you move to the right. At some point, if only one of the nodes is larger, that will be the lowest common ancestor.
     *
     * The conditions can be summarized as follows:
     *
     * if p and q < r → go left
     * if p and q > r → go right
     * if p < r < q or p > r > q → r is lowest common ancestor
     *
     * r is root(parent)
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // if(p.val < root.val && q.val < root.val) {
            //     root = root.left;
            //     lowestCommonAncestor(root, p, q);
            // }
            // if(p.val > root.val && q.val > root.val) {
            //     root = root.right;
            //     lowestCommonAncestor(root, p, q);
            // }
            // if((p.val <= root.val && root.val <= q.val) || (p.val >= root.val && root.val >= q.val)) {
            //     return root;
            // }
            // return null;

            while(root != null) {
                if(p.val < root.val && q.val < root.val) {
                    root = root.left;
                    lowestCommonAncestor(root, p, q);
                }
                if(p.val > root.val && q.val > root.val) {
                    root = root.right;
                    lowestCommonAncestor(root, p, q);
                }
                if((p.val <= root.val && root.val <= q.val) || (p.val >= root.val && root.val >= q.val)) {
                    return root;
                }
            }
            return null;

            // while (root != null) {
            //     if(p.val < root.val && q.val < root.val) {
            //         root = root.left;
            //         lowestCommonAncestor(root, p, q);
            //     }
            //     else if(p.val > root.val && q.val > root.val) {
            //         root = root.right;
            //         lowestCommonAncestor(root, p, q);
            //     }
            //     else return root;
            // }
            // return null;
        }
    }
}
