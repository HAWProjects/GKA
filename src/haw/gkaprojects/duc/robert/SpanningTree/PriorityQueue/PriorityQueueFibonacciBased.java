package haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import haw.gkaprojects.duc.robert.graph.Vertex;

public class PriorityQueueFibonacciBased implements IPriorityQueueForPrimAlg {

      //
      FibonacciHeap<Vertex>                  fibHeap;
      //
      Map<Vertex, Double>                    vertexEnf;
      //
      Map<Vertex, FibonacciHeapNode<Vertex>> nodeMap;

      
      public PriorityQueueFibonacciBased(Map<Vertex, Double> vertexEnf) {

            this.vertexEnf = new HashMap<Vertex, Double>(vertexEnf);

            this.fibHeap = new FibonacciHeap<Vertex>();

            this.nodeMap = new HashMap<Vertex, FibonacciHeapNode<Vertex>>();
            
            
            for (Vertex vertex : vertexEnf.keySet()) {
                  FibonacciHeapNode<Vertex> node = new FibonacciHeapNode<Vertex>(vertex);

                  double keyValue = vertexEnf.get(vertex);

                  this.nodeMap.put(vertex, node);

                  this.fibHeap.insert(node, keyValue);
            }
      }

      @Override
      public Vertex extractMin() {

            Vertex vertex = this.fibHeap.removeMin().getData();
            this.nodeMap.remove(vertex);
            // vertexEnf.remove(vertex);

            return vertex;
      }

      @Override
      public void decreaseKey(Vertex v, double newKey) {

            FibonacciHeapNode<Vertex> node = nodeMap.get(v);

            this.vertexEnf.put(v, newKey);

            this.fibHeap.decreaseKey(node, newKey);

      }

      @Override
      public Vertex findMin() {

            return this.fibHeap.min().getData();

      }

      @Override
      public boolean isEmpty() {

            return this.fibHeap.isEmpty();
      }

      @Override
      public int size() {
            return this.fibHeap.size();
      }

      @Override
      public void insert(Vertex v, double keyValue) {
            
            FibonacciHeapNode<Vertex> node = new FibonacciHeapNode<Vertex>(v);
            
            this.vertexEnf.put(v,keyValue);
            this.nodeMap.put(v, node);
            this.fibHeap.insert(node, keyValue);
            
      }


      @Override
      public boolean contains(Vertex v) {
            
            return this.nodeMap.containsKey(v);
            
      }

      @Override
      public Double getVertexKeyValue(Vertex v) {
            
            return this.vertexEnf.get(v);
      
      }

}
