/*
 * A queue that is implemeneted with a SinglyLinkedList 
 * @author green6
 * @scribe ""
 */
import java.util.NoSuchElementException;
public class LinkedQueue<T> implements Queue<T> { 
  /*
   * The size of the queue
   */
  int size = 0;
  /*
   * The queue made from a SinglyLinkedList
   */
  SinglyLinkedList<T> linkedQ = new SinglyLinkedList<T>();
  
  /*
   * add to the front of the queue
   *@parameter x item to be added to front of queue
   */
  
  public void enqueue(T x) {
    linkedQ.add(x);
    size++;
  }
  
  /*
   * removes the frontmost item of the queue
   * @return the frontmost item of the queue
   */
  
  public T dequeue() {
    try {
      size--;
      return linkedQ.remove(0);
    } catch (Exception e) {
      throw new NoSuchElementException();
    }
  }
  
  /*
   * looks at frontmost item in queue
   * @return the frontmost item in the queue
   */
  public T front() {
    try { 
      return linkedQ.get(0);
    } catch (Exception e) {
      throw new NoSuchElementException();
    }
  }
  
  /*
   * returns the last item in the queue
   * @return the last item in the queue
   */
  public T back() {
    try {
      return linkedQ.get(linkedQ.size()-1);
    } catch (Exception e) {
      throw new NoSuchElementException();
    }
  }
  
  /*
   * size of queue
   * @return the size of the queue
   */
  public int size() {
    return size;
  }
  
  /*
   * return if the size is empty
   * @return true if the size is empty.
   */
  public boolean isEmpty() {
    return size == 0;
  }
  
  /*
   * Convert the queue to a string in the form of "( item1 item2 ... itemN )"
   * @return the queue as a string
   */
  public String toString() {
    StringBuilder ans = new StringBuilder();
    ans.append("( ");
    for (int i = 0; i < size; i++) {
      ans.append(linkedQ.get(i));
      ans.append(" ");
    }
    ans.append(")");
    return ans.substring(0);
  }
  
  public static void main(String... args6) { 
    // Tests with String
    Queue<String> listQ = new LinkedQueue<>();
    listQ.enqueue("0");
    listQ.enqueue("1");
    listQ.enqueue("2");
    listQ.enqueue("3");
    listQ.enqueue("4");
    listQ.enqueue("5");
    assert listQ.toString().equals("( 0 1 2 3 4 5 )");
    listQ.dequeue();
    assert listQ.toString().equals("( 1 2 3 4 5 )");
    while (!listQ.isEmpty()) {
      listQ.dequeue();
    }
    assert listQ.toString().equals("( )");
    Queue<Integer> qInt = new LinkedQueue<>();
    for (int i = 0; i < 20; i++) {
      qInt.enqueue(i);
    }
    assert qInt.front() == 0;
    assert qInt.back() == 19;
    assert qInt.size() == 20;
    for (int i = 0; i < 20; i++) {
      assert i == qInt.dequeue();
    }
  }
}

interface Queue<T> {
  void enqueue(T x);
  T dequeue();
  T front();
  // reference to last element in the queue
  T back();
  int size();
  boolean isEmpty();
}