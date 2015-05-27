package haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue;

import haw.gkaprojects.duc.robert.graph.Vertex;

public interface IPriorityQueueForPrimAlg {

      /**
       * Return and Removes the smallest element from the Queue.
       * 
       * @return the smallest element from the Queue
       */
      public Vertex extractMin();

      /**
       * Decreases the key value for a element, given the new value to take on.
       * 
       * @param v
       *              The given element, whose key should be decreased
       * @param newKey
       *              new key value
       *              
       * @require vertex v is already in the PriorityQueue && newKeyValue < v oldKeyvalue
       * 
       */
      public void decreaseKey(Vertex v, double newKeyValue);

      /**
       * Returns the smallest element in the Queue.
       * 
       * @return the smallest element in the Queue
       */
      public Vertex findMin();

      /**
       * Inserts a new data element into the Queue.
       * 
       * @param v
       *              new data element
       */
      public void insert(Vertex v, double keyValue);
      
//      /**
//       * Remove the give vertex from the priorityQueue
//       * 
//       * @param v
//       */
//      public boolean remove(Vertex v);

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
       * Check whether the given vertex already in the PriorityQueue
       * 
       * @param v
       */
      public boolean contains(Vertex v);
      
      /**
       * Get the key value of the given vertex
       * 
       * @param v
       */
      public Double getVertexKeyValue(Vertex v);
      
}
