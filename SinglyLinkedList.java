/**
 * Here is the List interface and SinglyLinkedList class from
 * Lecture 3a. Do the following:
 * 
 * (1) Add appropriate Javadoc style comments to all methods.
 * (2) Implement the remove() method.
 * (3) Implement the toString() method.
 * (4) Turn List into a generic interface and SinglyLinkedList into
 *     a generic class so that the structure can be used to hold
 *     any type T of data.
 * (5) Test thoroughly in main() with a variety of data types.
 *
 * We affirm that all members of our team contributed to this solution.
 * @author <insert your team name here (e.g., red1, red2, ..., green1, ...)>
 * @author <insert the name of your Scribe here>
 */

public class SinglyLinkedList implements List {

  class Node {
    int data;
    Node next;
    
    Node(int data) {
      this(data, null);
    }
    
    Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }
  }
  
  Node head;
  int n;
  
  public void add(int x) {
    n++;
    if (head == null) 
      head = new Node(x);
    else {
      Node p = head;
      while (p.next != null)
        p = p.next;
      p.next = new Node(x);
    }
  }
  
  /*
  * removes the node at a specified index
  * @return returns the data at a specified node
  * @parameter i index to be removed
  */
  public int remove(int i) {
    if (i < 0 || i >= n) {
      // i is outside of the avaliable index
     throw new IndexOutOfBoundsException(); 
    }
    Node p = head;
    int ans;
    if (i == 0) {
      // Since i == 0, we need to move the head down.
      ans = head.data;
      head = head.next;
    } else {
      // cycle through the nodes until you are 1 away from the one to be deleted
      for (int j = 1; j < i; j++) {
        p = p.next;
      }
      ans = p.next.data;
      if (i == n-1) {
        // removing the last index in the array, so no node after to replace with
        p.next = null;
      } else {
        p.next = p.next.next;
      }
    }
    n--;
    return ans;
  }
  
  public int get(int i) {
    if (i < 0 || i >= size())
      throw new IndexOutOfBoundsException();
    Node p = head;
    while (i > 0) {
      p = p.next;
      i--;
    }
    return p.data;
  }

  public boolean contains(int x) {
    Node p = head;
    while (p != null)
      if (p.data == x)
        return true;
      else
        p = p.next;
    return false;
  }
  
  public int size() {
    return n;
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }
  
  /* Returns the list of nodes as a String
  * @return the list of Nodes as a String
  */
  
  public String toString() {
    if (head == null) {  
     // head is null so return default value  
     return "()";  
   }
   Node p = head;  
   String ans = "(";  
   while (p.next != null) {
    // cycle through p adding the data from p into the string  
    ans += p.data + " ";  
    // increment p to the next pointer  
    p = p.next;  
    }  
    ans += p.data + ")";
    return ans;
  }
  
  public static void main(String... args) {
    List xs = new SinglyLinkedList();
    assert "()".equals(xs.toString());
    int[] a = new int[] { 7, 4, 6, 9, 2 };
    for (int x : a)
      xs.add(x);
    assert "(7 4 6 9 2)".equals(xs.toString());
    for (int x : a)
      assert xs.contains(x);
    for (int i = 0; i < xs.size(); i++)
      assert a[i] == xs.get(i);
    assert "(7 4 6 9 2)".equals(xs.toString());
    xs.remove(3);
    assert "(7 4 6 2)".equals(xs.toString());
    while (!xs.isEmpty())
      xs.remove(0);
    assert "()".equals(xs.toString());
    System.out.println("it worked");
  }
}

interface List {
  void add(int x);
  int remove(int i);
  int get(int i);
  boolean contains(int x);
  int size();
  boolean isEmpty();
}