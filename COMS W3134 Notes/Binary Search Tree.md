* A binary search tree is a binary tree where each element in sorted from least to greatest --> if the number is less than current node, travel left; if more than current node, travel right; once you reach the end of the tree, append the number on the correct side
* Searching through the binary search trees relies on recursion, while insertion & deletion rely on while loops
* Each node on a binary search tree has four stored properties --> parent node, left node, right node, and current value
```java
public class node {
	private node left;
	private node right;
	private node parent;
	private int value;
	public node(){
		left = null;
		right = null;
		parent = null;
		key = 0;
	}

	public node treeSearchRecursive (node root, int key){
		if(root == null || key == root.key){
			return root;	
		}
		if(key < root.key){
			return treeSearchRecursive(root.left, key);
		} else {
			return treeSearchRecursive(root.right, key);
		}
	}
	public node treeSearchIterative (node root, int key){
		while(root!=null && key!=root.key){
			if(key < root.key) root = root.left;
			else root = root.right;
		}
		return root;
	}
	public void insertNode(node root, node insert){
		node y = null;
		node x = root;
		while(x != null){
			y = x;
			if(insert.key < x.key) x = x.left;
			else x = x.right;
		}
		insert.parent = y;
		if(y == null) root = insert; // if original tree is empty
		else if(root.key < y.key) y.left = insert;
		else y.right = insert;
	}

	public node treeMinimum(node root){ // finds min node of BST
		node x = root;
		while(x.left != null) x = x.left;
		return x;
	}
	public void transplant(node root, node a, node b){ // transplants one subtree component of the main tree with another subtree component using subroots a & b and the main tree's root
		if(a.parent == null) root = b;
		else if(a == a.parent.left) a.parent.left = b;
		else a.parent.right = b;
		if(b != null) b.parent = a.parent; // b's parent pointer now points to a's parent
	}
	public void deleteNode(node root, node delete){
		if(root.left == null) transplant(root, delete, delete.right);
		else if(root.right == null) transplant(root, delete, delete.left);
		else {
			y = treeMinimum(delete.right);
			if(y.parent != delete){
				transplant(root,y,y.right);
				y.right = delete.right;
				y.right.parent = y;
			}
			transplant(root, delete, y);
			y.left = delete.left;
			y.left.parent = y;
		}
	}
}
```
For Binary Trees:
- height = lg(n+1) - 1
- max # nodes = 2^(h+1) - 1
- num leaves = (n+1)/2
- binary search complexity: O(lg N)
- insertion time complexity: Theta(lg N) best case, Theta(N) worst case
- deletion time complexity: Theta(lg N) best case, Theta(N) worst case

Deletion from BST:
1. get the min from the deleted node's right subtree, we'll use this to replace the deleted node
	- getTreeMin --> keep traveling to the left children from the right subroot
2. after removal and transplant, replace the min of the right subtree with the min's right child

**Constructing a Binary Search Tree w/ Inorder & Preorder**
```java
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