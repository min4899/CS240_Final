/**
   A single linked data queue that implements QueueInterface.
   @author Minwoo Soh
*/
public final class SingleLinkedQueue<T> implements QueueInterface<T>
{
  private Node firstNode;
  private Node lastNode;
  private Node rootNode;
  private int numberOfEntries;

  /** Creates an empty SingleLinkedQueue object with no nodes. */
  public SingleLinkedQueue()
  {
    firstNode = null;
    lastNode = null;
    rootNode = null;
    numberOfEntries = 0;
  } // end default constructor

  /** Adds a new entry to the back of this queue.
      @param newEntry  An object to be added. */
  public void enqueue(T newEntry)
  {
    Node newNode = new Node(newEntry);

    if(isEmpty())
    {
      lastNode = newNode;
      firstNode = newNode;
      numberOfEntries++;
    }
    else
    {
      newNode.prev = lastNode;
      lastNode = newNode;
      numberOfEntries++;
    } // end if
  } // end enqueue

  /** Removes and returns the entry at the front of this queue.
      @return  The object at the front of the queue.
      @throws  EmptyQueueException if the queue is empty before the operation. */
  public T dequeue()
  {
    T item = null;

    try
    {
      if(isEmpty())
      {
        throw new EmptyQueueException();
      }
      else
      {
        if(numberOfEntries == 1) // Only 1 entry in queue.
        {
          item = firstNode.data;
          firstNode = null;
          lastNode = null;
          numberOfEntries--;
        }
        else if(numberOfEntries == 2)
        {
          item = firstNode.data;
          lastNode.prev = null; // 2nd node points to null.
          firstNode = lastNode; // Sets firstNode as the one after it.
          numberOfEntries--;
        }
        else
        {
          item = firstNode.data;
          Node currentNode = lastNode;
          // Goes through queue until currentNode is equal to the 2nd node.
          for(int i = 1; i < numberOfEntries - 1; i++)
          {
            // Goes through end to the 2nd item in the queue.
            currentNode = currentNode.prev;
          } // end for
          currentNode.prev = null; // 2nd node points to null.
          firstNode = currentNode; // Sets firstNode as the 2nd node.
          numberOfEntries--;
        } // end if
      } // end if
    } // end try
    catch(EmptyQueueException e)
    {
      System.out.println("Exception thrown: " + e);
    } // end catch

    return item;
  } // end dequeue

  /**  Retrieves the entry at the front of this queue.
      @return  The object at the front of the queue.
      @throws  EmptyQueueException if the queue is empty. */
  public T getFront()
  {
    T item = null;

    try
    {
      if(isEmpty())
      {
        throw new EmptyQueueException();
      }
      else
      {
        item = firstNode.data;
      } // end if
    } // end try
    catch(EmptyQueueException e)
    {
      System.out.println("Exception thrown: " + e);
    } // end catch

    return item;
  } // end getFront

  /** Detects whether this queue is empty.
      @return  True if the queue is empty, or false otherwise. */
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  } // end isEmpty

  /** Removes all entries from this queue. */
  public void clear()
  {
    if(!isEmpty())
    {
      dequeue();
    }
  } // end clear

  /** Returns all entries in an organized string.
      @return String of all entries. */
  public String toString()
  {
    String result = "[";

    // Create an array that stores all entries in order.
    @SuppressWarnings("unchecked")
    T[] temp = (T[])new Object[numberOfEntries];
    Node currentNode = lastNode;
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
} // end of SingleLinkedData
