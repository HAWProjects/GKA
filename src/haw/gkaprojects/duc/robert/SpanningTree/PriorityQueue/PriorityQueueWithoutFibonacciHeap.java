package haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import haw.gkaprojects.duc.robert.graph.Vertex;

public class PriorityQueueWithoutFibonacciHeap implements IPriorityQueueForPrimAlg {

      Queue<Vertex>       priorityQueue;

      Map<Vertex, Double> vertexEnf;

      public PriorityQueueWithoutFibonacciHeap(Map<Vertex, Double> vertexEnf) {
            
            //
            this.vertexEnf = new HashMap<Vertex, Double>(vertexEnf);

            //
            this.priorityQueue = new PriorityQueue<Vertex>(new ComparatorVertexPrim(this.vertexEnf));
           
            //
            for (Vertex vertex : vertexEnf.keySet()) {
                  priorityQueue.add(vertex);
            }

      }

      @Override
      public Vertex extractMin() {

            //
            return priorityQueue.poll();

      }

      @Override
      public void decreaseKey(Vertex v, double newKeyValue) {
            
            // 
            vertexEnf.put(v, newKeyValue);
            
            //
            priorityQueue.remove(v);
            priorityQueue.offer(v);
            
      }

      @Override
      public Vertex findMin() {

            return priorityQueue.peek();

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
      public void insert(Vertex v, double keyValue) {

            this.vertexEnf.put(v, keyValue);
            this.priorityQueue.add(v);
            
      }

//      @Override
      public boolean remove(Vertex v) {

//            this.vertexEnf.remove(v);
            return this.priorityQueue.remove(v);

      }

      @Override
      public boolean contains(Vertex v) {
            
            return this.vertexEnf.containsKey(v);
            
      }

      @Override
      public Double getVertexKeyValue(Vertex v) {
            return this.vertexEnf.get(v);
      }
      
      @Override
      public String toString() {
            
            String[] str = new String[this.priorityQueue.size()];
            
            int i = 0;
            for (Vertex vertex : priorityQueue) {
                  str[i] = vertex.getLabel()+ "=>" + vertexEnf.get(vertex);
                  i++;
            }
            
            
            return  Arrays.toString(str); 
      }

}
