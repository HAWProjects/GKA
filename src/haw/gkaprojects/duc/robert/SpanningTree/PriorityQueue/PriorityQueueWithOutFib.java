package haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue;

import haw.gkaprojects.duc.robert.graph.CustomEdge;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueWithOutFib implements IPriorityQueue {

      Queue<CustomEdge>       priorityQueue;
      
      public PriorityQueueWithOutFib(Comparator<CustomEdge> edgeComparator) {
            //
            this.priorityQueue = new PriorityQueue<CustomEdge>(edgeComparator);
           
      }
      
      @Override
      public CustomEdge extractMin() {
            //
            return priorityQueue.poll();
      }

      @Override
      public void decreaseKey(CustomEdge v, double newKeyValue) {
            // TODO Auto-generated method stub
            
      }

      @Override
      public CustomEdge findMin() {
            return priorityQueue.peek();
      }

      @Override
      public void insert(CustomEdge v) {
            this.priorityQueue.add(v);
            
      }

      @Override
      public boolean isEmpty() {
            
            return priorityQueue.isEmpty();
            
      }

      @Override
      public int size() {

            return priorityQueue.size();

      }

      @Override
      public boolean contains(CustomEdge v) {
            return this.priorityQueue.contains(v);
            
      }

      

}
