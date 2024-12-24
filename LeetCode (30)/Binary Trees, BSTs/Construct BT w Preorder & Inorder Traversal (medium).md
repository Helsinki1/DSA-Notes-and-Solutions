```java
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0) return null;
        if(preorder.length==1) return new TreeNode(preorder[0]);

        int rootVal = preorder[0];
        int index = 0;
        while(inorder[index] != rootVal) index++;

        int leftLength = index;
        int rightLength = inorder.length - index - 1;
        int[] leftSubTreePreorder = new int[leftLength];
        int[] rightSubTreePreorder = new int[rightLength];
        int[] leftSubTreeInorder = new int[leftLength];
        int[] rightSubTreeInorder = new int[rightLength];
        for(int i=0; i<leftLength; i++){
            leftSubTreeInorder[i] = inorder[i];
        }
        for(int i=index+1; i<inorder.length; i++){
            rightSubTreeInorder[i-index-1] = inorder[i];
        }
        for(int i=1; i<leftLength+1; i++){
            leftSubTreePreorder[i-1] = preorder[i];
        }
        for(int i=leftLength+1; i<preorder.length; i++){
            rightSubTreePreorder[i-leftLength-1] = preorder[i];
        }

        return new TreeNode(rootVal, buildTree(leftSubTreePreorder,leftSubTreeInorder), buildTree(rightSubTreePreorder,rightSubTreeInorder));
    }
}
```
* Basically, use first element of preorder to identify the root/subroot, then search inorder for the position of the root/subroot, then divide inorder into a left subtree & right subtree portion using the root/subroot's index, then apply recursion
* Account for the base cases (leaves & null nodes) where recursion should terminate
* TreeNode() constructor creates the node of the root/subroot, then calls on recursion to create the left and right subtrees

Note: `Arrays.copyOfRange(array, startIndex, endIndex non-inclusive)`
- This makes transferring data a little faster, also cleaner than for loops

Transferring data into four new int arrays for every recursive call is way too inefficient, a better solution would be to
- Use a hash table for index lookup
- Instead of passing down new int arrays for each recursive call, you pass down start/end indices and the original preorder and inorder arrays using a helper method
```java
private TreeNode buildTreeHelper(int[] preorder, int preorderStart, int preorderEnd, HashMap<Integer, Integer> inorderIndexMap, int inorderStart) {
        // Base case: no elements to construct the tree
        if (preorderStart > preorderEnd) {
            return null;
        }

        // The first element in the preorder array is the root
        int rootValue = preorder[preorderStart];
        TreeNode node = new TreeNode(rootValue);
        
        // Get the index of the root in the inorder array
        int nodeIndex = inorderIndexMap.get(rootValue);
        
        // Calculate the length of the left subtree
        int leftLength = nodeIndex - inorderStart;

        // Recursively build the left and right subtrees
        node.left = buildTreeHelper(preorder, preorderStart + 1, preorderStart + leftLength, inorderIndexMap, inorderStart);
        node.right = buildTreeHelper(preorder, preorderStart + leftLength + 1, preorderEnd, inorderIndexMap, nodeIndex + 1);
        
        return node;
    }
```