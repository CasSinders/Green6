/**
 * Tree interface and BinarySearchTree class from lec4b. ^^^^^^
 * 
 * TODO: Pre-Lecture Exercise for lec5a. Note: This is a team effort. Every
 * member of your team is expected to make non-trivial contributions towards
 * your solution.
 * 
 * Make the following modifications: (1) Add javadoc style comments to all
 * methods. (2) Implement BinarySearchTree.contains() so that it runs in O(h)
 * time, where h is the height of the tree. (3) Modify Tree and BinarySearchTree
 * so that they are generic for any Comparable type object. (4) [challenge] Try
 * to implement a sensible BinarySearchTree.toString() method. Recall that an
 * inorder traversal of a BST yields a sorted sequence. (5) Test thoroughly in
 * main(). Be sure to include tests on non-integer data.
 * 
 * @author <green6>
 * @author <insert the name of your Scribe here>
 */

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	class Node {
		T data;
		Node left, right;

		Node(T key) {
			this(key, null, null);
		}

		Node(T data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		boolean isLeaf() {
			return left == null && right == null;
		}
	}

	Node root;
	int n;

	/*
	 * Inserts a new node with value Key into the tree
	 * 
	 * @parameter key object to be inserted into tree
	 */
	public void insert(T key) {
		n++;
		root = insertHelper(key, root);
	}

	/*
	 * private helper method for insert, recursively search through nodes to
	 * find where to enter new one
	 * 
	 * @parameter key key to be inserted
	 * 
	 * @parameter p node that is being recursively checked
	 */
	private Node insertHelper(T key, Node p) {
		if (p == null)
			return new Node(key);
		int compare = key.compareTo(p.data);
		if (compare < 0)
			p.left = insertHelper(key, p.left);
		else
			p.right = insertHelper(key, p.right);
		return p;
	}

	/*
	 * return true iff the tree contains the specificed key
	 * 
	 * @parameter key key to be searched for
	 */
	public boolean contains(T key) {
		return containsHelper(key, root);
	}

	/*
	 * private helper method for contains, recursively search through nodes to
	 * try and find the key
	 * 
	 * @parameter key key that is being searched for
	 * 
	 * @parameter p node that is being recursively searched
	 */
	private boolean containsHelper(T key, Node p) {
		if (p == null)
			return false;
		int compare = key.compareTo(p.data);
		if (compare == 0)
			return true;
		if (compare < 0) {
			return containsHelper(key, p.left);
		} else {
			return containsHelper(key, p.right);
		}
	}

	/*
	 * return the total number of nodes inside the tree
	 */
	public int size() {
		return n;
	}

	/*
	 * return the tree as a string in the form of { data1 data2 }
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "{" + toStringHelper(root) + "}";
	}

	/*
	 * private recursive helper for the toString method. recursively visit each
	 * Node and append them to the string
	 */
	public String toStringHelper(Node p) {
		if (p == null) {
			return "";
		}
		return toStringHelper(p.left) + " " + p.data + " " + toStringHelper(p.right);
	}

	public static void main(String... args) {
		int[] a = new int[] { 3, 9, 7, 2, 1, 5, 6, 4, 8 };
		Tree<Integer> bst = new BinarySearchTree<>();
		assert bst.isEmpty();
		for (int key : a)
			bst.insert(key);
		/**
		 * 3 / \ 2 9 / / 1 7 / \ 5 8 / \ 4 6
		 */
		assert !bst.isEmpty();
		assert bst.size() == a.length;
		for (int key : a)
			assert bst.contains(key);
		assert (bst.toString().equals("{ 1  2  3  4  5  6  7  8  9 }"));

		String[] b = new String[] { "b", "c", "d", "e", "a" };
		Tree<String> bst2 = new BinarySearchTree<>();
		assert bst2.isEmpty();

		for (String key : b)
			bst2.insert(key);
		assert (bst2.toString().equals("{ a  b  c  d  e }"));
	}

}

interface Tree<T extends Comparable<T>> {
	void insert(T key);

	default void remove(T key) {
		throw new UnsupportedOperationException();
	}

	boolean contains(T key);

	int size();

	default boolean isEmpty() {
		return size() == 0;
	}
}