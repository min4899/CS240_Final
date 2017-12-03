import java.util.EmptyStackException;

/**
   A linked data stack that implements StackInterface.
   @author Minwoo Soh
*/
public final class LinkedDataStack<T> implements StackInterface<T>
{
  private Node previousNode;
  private int numberOfEntries;

  /** Creates an empty LinkedData stack. */
  public LinkedDataStack()
  {
    previousNode = null;
    numberOfEntries = 0;
  } // end default constructor

  /** Adds a new entry to the top of this stack.
      @param newEntry  An object to be added to the stack. */
  public void push(T newEntry)
  {
    Node newNode = new Node(newEntry); // Save newEntry as data into new node.
    newNode.prev = previousNode; // Make new node reference.
    previousNode = newNode; // New node is at end of stack.
    numberOfEntries++;
  } // end push

  /** Removes and returns this stack's top entry.
      @return  The object at the top of the stack.
      @throws  EmptyStackException if the stack is empty before the operation. */
  public T pop()
  {
    if(isEmpty())
    {
      throw new EmptyStackException();
    }
    else
    {
      T item = previousNode.data; // Sets item inside temp.
      previousNode = previousNode.prev; // Sets end node as previous node.
      numberOfEntries--;
      return item;
    } // end if
  } // end pop

  /** Retrieves this stack's top entry.
      @return  The object at the top of the stack.
      @throws  EmptyStackException if the stack is empty. */
  public T peek()
  {
    if(isEmpty())
    {
      throw new EmptyStackException();
    }
    else
    {
      T item = previousNode.data;
      return item;
    } // end if
  } // end peek

  /** Detects whether this stack is empty.
      @return  True if the stack is empty. */
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  } // end isEmpty

  /** Removes all entries from this stack. */
  public void clear()
  {
    while(!isEmpty())
    {
      pop();
    } // end while
  } // end clear

  /** Returns all entries in an organized string.
      @return String of all entries. */
  public String toString()
  {
    String result = "[";

    // Create an array that stores all entries in order.
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[numberOfEntries];
    Node currentNode = previousNode;
    for(int i = numberOfEntries - 1; i >= 0; i--)
    {
      temp[i] = currentNode.data;
      currentNode = currentNode.prev;
    } // end for

    // Puts array of entries in a string.
    for(int i = 0; i < numberOfEntries; i++)
    {
      result += temp[i] + " "; // Concat next list element.
    } // end for

    result += "]";
    return result;
  } // end toString

  /** Private inner class Node */
  private class Node
  {
    private T data;
    private Node prev; // Link to previous node.
    /** Creates a node that points to null.
        @param dataPortion  The data that will be stored in the node. */
    private Node(T dataPortion)
    {
      this(dataPortion, null);
    } // end constructor

    /** Creates a node that points to another node.
        @param dataPortion  The data that will be stored in the node.
        @param prevNode  The previous node that will be referenced. */
    private Node(T dataPortion, Node prevNode)
    {
      data = dataPortion;
      prev = prevNode;
    } // end constructor
  } // end of Node
} // end of LinkedData
