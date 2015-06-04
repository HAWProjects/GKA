package haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue;

import haw.gkaprojects.duc.robert.graph.CustomEdge;

public interface IPriorityQueue {

      /**
       * Return and Removes the smallest element from the Queue.
       * 
       * @return the smallest element from the Queue
       */
      public CustomEdge extractMin();

      /**
       * Decreases the key value for a element, given the new value to take on.
       * 
       * @param v
       *              The given element, whose key should be decreased
       * @param newKey
       *              new key value
       *              
       * @require CustomEdge v is already in the PriorityQueue && newKeyValue < v oldKeyvalue
       * 
       */
      public void decreaseKey(CustomEdge v, double newKeyValue);

      /**
       * Returns the smallest element in the Queue.
       * 
       * @return the smallest element in the Queue
       */
      public CustomEdge findMin();

      /**
       * Inserts a new data element into the Queue.
       * 
       * @param v
       *              new data element
       */
      public void insert(CustomEdge v);
      
//      /**
//       * Remove the give CustomEdge from the priorityQueue
//       * 
//       * @param v
//       */
//      public boolean remove(CustomEdge v);

      /**
       * Tests if the queue is empty or not.
       * 
       * @return
       */
      public boolean isEmpty();

      /**
       * Returns the size of the Queue which is measured in the number of
       * elements contained in the Queue.
       * 
       * @return the size of the Queue
       */
      public int size();

      /**
       * Creates a String representation
       * 
       * @return String of this
       */
      public String toString();
      
      /**
       * Check whether the given CustomEdge already in the PriorityQueue
       * 
       * @param v
       */
      public boolean contains(CustomEdge v);
     
      
      
      

}
