package haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import haw.gkaprojects.duc.robert.graph.CustomEdge;

public class PriorityWithFibonacciHeap implements IPriorityQueue {

      FibonacciHeap<CustomEdge>                      fibHeap;

      Map<CustomEdge, FibonacciHeapNode<CustomEdge>> nodeMap;

      Map<CustomEdge, Double>                        edgeWeight;

      public PriorityWithFibonacciHeap(Map<CustomEdge, Double> edgeWeightMap) {
            this.nodeMap = new HashMap<CustomEdge, FibonacciHeapNode<CustomEdge>>();
            this.edgeWeight = edgeWeightMap;
            this.fibHeap = new FibonacciHeap<CustomEdge>();
      }

      @Override
      public CustomEdge extractMin() {

            FibonacciHeapNode<CustomEdge> node  = this.fibHeap.removeMin();
            CustomEdge edge = node != null ? node.getData() : null;
            nodeMap.remove(edge);
            return edge;

      }

      @Override
      public void decreaseKey(CustomEdge v, double newKeyValue) {
            FibonacciHeapNode<CustomEdge> node = this.nodeMap.get(v);
            this.edgeWeight.put(v, newKeyValue);
            this.fibHeap.decreaseKey(node, newKeyValue);
      }

      @Override
      public CustomEdge findMin() {
            return fibHeap.min().getData();
      }

      @Override
      public void insert(CustomEdge v) {
            
            FibonacciHeapNode<CustomEdge> node = new FibonacciHeapNode<CustomEdge>(v);
//            this.nodeMap.put(v, node);
            this.fibHeap.insert(node, this.edgeWeight.get(v));
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
      public boolean contains(CustomEdge v) {
            return this.nodeMap.containsKey(v);
      }

}
