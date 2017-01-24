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
 * @author <green6>
 * @author <insert the name of your Scribe here>
 */

public class  SinglyLinkedList <T> implements List<T> {
  
  class Node {
    T data;
    Node next;
    
    Node(T data) {
      this(data, null);
    }
    
    Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }
  }
  
  Node head;
  int n;
  
  public void add(T x) {
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
  public T remove(int i) {
    if (i < 0 || i >= n) {
      // i is outside of the avaliable index
      throw new IndexOutOfBoundsException(); 
    }
    Node p = head;
    T ans;
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
  
  public T get(int i) {
    if (i < 0 || i >= size())
      throw new IndexOutOfBoundsException();
    Node p = head;
    while (i > 0) {
      p = p.next;
      i--;
    }
    return p.data;
  }
  
  public boolean contains(T x) {
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
  
  public static void main(String... args6) {
    List<Integer> xs = new SinglyLinkedList<Integer>();
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
    
    
    List<String> bs = new SinglyLinkedList<String>();
    String[] b = new String[] {"7","4","6","9","2"};
    for (String x : b)
      bs.add(x);
    assert "(7 4 6 9 2)".equals(bs.toString());
    for (String x : b)
      assert bs.contains(x);
    for (int i = 0; i < bs.size(); i++)
      assert b[i] == bs.get(i);
    assert "(7 4 6 9 2)".equals(bs.toString());
    bs.remove(3);
    assert "(7 4 6 2)".equals(bs.toString());
    while (!bs.isEmpty())
      bs.remove(0);
    assert "()".equals(bs.toString());
    
    List<Boolean> cs = new SinglyLinkedList<Boolean>();
    boolean[] c = new boolean[] {true, true, false, true};
    for (Boolean x: c) 
      cs.add(x);
    for (Boolean x: c) 
      assert cs.contains(x);
    for (int i = 0; i < cs.size(); i++) {
      assert c[i] == cs.get(i);
    }
    assert "(true true false true)".equals(cs.toString());
    cs.remove(2);
    assert "(true true true)".equals(cs.toString());
    while (!cs.isEmpty()) {
      cs.remove(0);
    }
    assert cs.toString().equals("()");
  }
}

interface List<T> {
  void add(T x);
  T remove(int i);
  T get(int i);
  boolean contains(T x);
  int size();
  boolean isEmpty();
}