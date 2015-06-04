package haw.gkaprojects.duc.robert.SpanningTree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.WeightedPseudograph;

import haw.gkaprojects.duc.robert.SpanningTree.OwnPrimMinimumSpanningTree.DataStructure;
import haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue.IPriorityQueue;
import haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue.PriorityQueueWithOutFib;
import haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue.PriorityWithFibonacciHeap;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

public class OwnPrimMinimumSpanningTree {
	public enum DataStructure{
		PRIORITYQUEUE,
		FIBONACCI_HEAP;
		
	}
      // The original Graph
      private Graph<Vertex, CustomEdge> originalGraph;
      // The smallest spanning tree of orginal graph
      private Set<CustomEdge>           minimunSpanningTreeEdgeSet;
      //
      private double                    totalWeight;

      /**
       * Create PrimAlgorithm object to find spanning tree from the given graph
       * 
       * @param graph
       *              the graph, for which spanning tree should be found
       * @throws IllegalAccessException
       * 
       * @require isUndirected(graph) && isConnectedComponnent(graph)
       */
      public OwnPrimMinimumSpanningTree(Graph<Vertex, CustomEdge> graph,
                  DataStructure typeOfDataStruture) throws IllegalAccessException {

            //
            if (!isUndirected(graph)) {
                  throw new IllegalArgumentException("the given graph was not an undirected graph!");
            }

            this.originalGraph = graph;

            this.minimunSpanningTreeEdgeSet = findSpanningTree(graph, typeOfDataStruture);

            findTotalWeight();
      }
      
      /**
       * Get total weight of the minimum spanning tree
       * 
       * @return
       */
      public double getTotalWeight() {
            return this.totalWeight;
      }

      /**
       * Check whether the given graph is an undirected graph
       * 
       * @param graph
       *              the graph
       * @return true if graph is an undirected graph, otherwise false
       */
      public boolean isUndirected(Graph<Vertex, CustomEdge> graph) {

            if (graph instanceof UndirectedGraph) {
                  return true;
            }

            return false;

      }
      
      
      /**
       * 
       * @param graph
       * @param typeOfDataStruture
       * @return
       */
      private Set<CustomEdge> findSpanningTree(Graph<Vertex, CustomEdge> graph,
                  DataStructure typeOfDataStruture) {
            
            //
            Set<CustomEdge> minSpanningTreeEdgeSet = new HashSet<CustomEdge>();

            // Set of unspanned Vertices
            Set<Vertex> unspanned = new HashSet<Vertex>(graph.vertexSet());

            //
            Iterator<Vertex> vertexIterator = unspanned.iterator();
            
            // Start vertex can bee any vertex from the graph
            Vertex exploring = vertexIterator.next();

            vertexIterator.remove();
            
            // 
            IPriorityQueue queue = getPriorityQueue(graph, DataStructure.FIBONACCI_HEAP);
            
            
            for (CustomEdge edge : graph.edgesOf(exploring)) {
                  queue.insert(edge);
            }

            CustomEdge safeEdge = queue.extractMin();
            
            // While there's still edge in the queue
            while (safeEdge != null) {
                  
                  // Vertex v is supposed to be not yet in the Spanning tree
                  Vertex v = graph.getEdgeTarget(safeEdge);
             
                  if(!unspanned.contains(v)){
                        v = graph.getEdgeSource(safeEdge);
                  }
                 
                  
                  if (unspanned.contains(v)) {
                       
                        // insert every edge, which's still not yet in the Spanning tree 
                        for (CustomEdge edge : graph.edgesOf(v)) {

                              Vertex otherSideVertex = graph.getEdgeSource(edge);

                              if (v.equals(otherSideVertex)) {
                                    otherSideVertex = graph.getEdgeTarget(edge);
                              }
                              
                              if (unspanned.contains(otherSideVertex)) {
                                    queue.insert(edge);
                              }
                        }
                        
                        // Add safe edge to spanning Tree
                        minSpanningTreeEdgeSet.add(safeEdge);
                        // And also vertex v
                        unspanned.remove(v);
                         
                      
                  }
                     
                  safeEdge = queue.extractMin();
            }

            return minSpanningTreeEdgeSet;
      }

      private IPriorityQueue getPriorityQueue(Graph<Vertex, CustomEdge> graph, DataStructure type) {

            IPriorityQueue queue = null;

            switch (type) {
                  case PRIORITYQUEUE:
                        queue = new PriorityQueueWithOutFib(new Comparator<CustomEdge>() {

                              @Override
                              public int compare(CustomEdge o1, CustomEdge o2) {
                                    return Double.valueOf(graph.getEdgeWeight(o1)).compareTo(
                                                graph.getEdgeWeight(o2));
                              }
                        });

                        break;

                  case FIBONACCI_HEAP:
                        Map<CustomEdge, Double> edgeMap = new HashMap<CustomEdge, Double>();

                        for (CustomEdge customEdge : graph.edgeSet()) {
                              edgeMap.put(customEdge, graph.getEdgeWeight(customEdge));
                        }

                        queue = new PriorityWithFibonacciHeap(edgeMap);
                        break;
                  default:
                        break;
            }

            return queue;
      }

     

      private void findTotalWeight() {

            if (this.minimunSpanningTreeEdgeSet == null) {
                  this.totalWeight = 0;
            }

            for (CustomEdge customEdge : this.minimunSpanningTreeEdgeSet) {
                  totalWeight += this.originalGraph.getEdgeWeight(customEdge);
            }

      }
      
      public Set<CustomEdge> getminimumSpanningTree(){
   
    	  return minimunSpanningTreeEdgeSet;
      }
      
      /**
      * creates and returns the Spanningtree
      * @return SpanningTree
      */
      public Graph<Vertex, CustomEdge> getSpanningTree(){
      Graph<Vertex, CustomEdge> newSpanningGraph = new WeightedPseudograph<>(CustomEdge.class);
      for(CustomEdge e : this.minimunSpanningTreeEdgeSet){
      Vertex v1 = originalGraph.getEdgeSource(e);
      Vertex v2 = originalGraph.getEdgeTarget(e);
      newSpanningGraph.addVertex(v1);
      newSpanningGraph.addVertex(v2);
      newSpanningGraph.addEdge(v1, v2);
      ((WeightedGraph<Vertex, CustomEdge>)newSpanningGraph).setEdgeWeight(newSpanningGraph.getEdge(v1, v2), originalGraph.getEdgeWeight(e));
      }
      return newSpanningGraph;
      }


}
