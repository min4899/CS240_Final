/**
    An Interface of an ADT LIST.
    @author Minwoo Soh
    @version 1.0
*/
 public interface ListInterface<T>
 {
   /** Add an entry to the end of the List.
       The list size will be increased by 1.
       Other item positions will be unaffected.
       @param item  The object to be added. */
   public void add(T item);

   /** Add an entry to the specificed position of the list.
       The list size will be increased by 1.
       Other item positions at or below specified position will be effected.
       @param item  The object to be added.
       @param position  The location the item should be placed in the list. Can choose between 1 - 10.
       @throws  IndexOutOfBoundException if either position < 1 or position > getLength() + 1. */
   public void add(int position, T item);

   /** Remove an entry at the specified position of the list.
       The list size will be decreased by 1.
       Other item positions at or below specified position will be effected.
       @param position  The location of item to be removed.
       @return  A reference to the removed entry.
       @throws  IndexOutOfBoundException if either position < 1 or position > getLength() + 1. */
   public T remove(int position);

   /** Removes all entries from list. */
   public void clear();

   /** Replaces the entry at the specificed position with a new entry.
       @param position  The location of item to be replaced.
       @param item  The new entry to replace old entry.
       @return  The original entry that was replaced.
       @throws  IndexOutOfBoundException if either position < 1 or position > getLength() + 1. */
   public T replace(int position, T item);

   /** Retrieves the entry at the specificed position of the list.
      @param position  The specified location of entry.
      @return  A reference to the indicated entry.
      @throws  IndexOutOfBoundException if either position < 1 or position > getLength() + 1. */
   public T view(int position);

   /** See whether this list contains a given entry.
       @param item  The object that is the desired entry.
       @return  True if the list contains the item, false if not. */
   public boolean contains(T item);

   /** Gets the length of the list.
       @return  The integer number of entries currently in the list. */
   public int getLength();

   /** Checks whether the list is empty.
       @return  True if the list is empty, false if not. */
   public boolean isEmpty();

   /** Retrieves all entries in the order in which they occur in the list.
       @return  A newly created array of all entries in the list. */
   public T[] toArray();
 } // end ListInterface
