/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Inorder_Traversal {

	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */

    public class Solution_optmize {
        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> list = new ArrayList<Integer>();
            if (root == null) {
                return list;
            }

            Stack<TreeNode> sk = new Stack<>();
            TreeNode current = root;

            while (!sk.isEmpty() || current != null) {

                while (current != null) {
                    sk.push(current);
                    current = current.left;
                } // @note: 一逼撸到最左边

                // @note: 没有push left的操作，就不会无限循环，也不需要mark是否visited
                TreeNode leftOrMiddle = sk.pop();
                list.add(leftOrMiddle.val);

                current = leftOrMiddle.right; // if right is null here, next time pop parent node
            }

            return list;
        }
    }

    class Solution_noStack { // but modifying original tree
        public List<Integer> inorderTraversal(TreeNode root) {

            List<Integer> result = new ArrayList<>();
            TreeNode current = root;
            TreeNode prev;

            while (current != null) {
                if (current.left == null) {
                    result.add(current.val);

                    // only handle right child
                    current = current.right; // move to next right node
                } else { // has a left subtree
                    prev = current.left;
                    while (prev.right != null) { // find rightmost
                        prev = prev.right;
                    }
                    prev.right = current; // put cur after the pre node
                    TreeNode temp = current; // store cur node
                    current = current.left; // move cur to the top of the new tree
                    temp.left = null; // original cur left be null, avoid infinite loops
                }
            }

            return result;
        }
    }

    public class Solution {

        List<Integer> list = new ArrayList<Integer>();

        public List<Integer> inorderTraversal(TreeNode root) {

            // mark if a node is visited already: true is visited. or, just use a Set
            HashSet<TreeNode> hs = new HashSet<>();

            Stack<TreeNode> sk = new Stack<>();
            sk.push(root);

            while (!sk.isEmpty()) {

                TreeNode current = sk.pop();

                if (current == null) {
                    continue;
                }

                // @note: careful to check left visited, or else infinite looping
                if (current.left != null && !hs.contains(current.left)) {
                    sk.push(current);
                    sk.push(current.left);

                } else {

                    if (current.right != null && !hs.contains(current.right)) {
                        sk.push(current.right);
                    }

                    hs.add(current);
                    list.add(current.val);
                }
            }

            return list;
        }
    }

    class Solution_recursion {

        List<Integer> result = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            dfs(root);
            return result;
        }

        public void dfs(TreeNode root) {
            if (root == null) {
                return;
            }

            dfs(root.left);
            result.add(root.val);
            dfs(root.right);
        }
    }
}

//////

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                ans.add(root.val);
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = root;
                    root = root.left;
                } else {
                    ans.add(root.val);
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return ans;
    }
}