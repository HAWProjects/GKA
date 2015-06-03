package haw.gkaprojects.duc.robert.SpanningTree;

import haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue.IPriorityQueueForPrimAlg;
import haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue.PriorityQueueFibonacciBased;
import haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue.PriorityQueueWithoutFibonacciHeap;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.NeighborIndex;
import org.jgrapht.graph.WeightedPseudograph;

public class OwnPrimMinimumSpanningTree {

      /**
       * Define 2 acceptable data structures for Prim-algorithm
       * 
       * PRIORITYQUEUE and FIBONACCI_HEAP
       * 
       * @author DucNguyenMinh
       *
       */
      public enum DataStructure {

            PRIORITYQUEUE,

            FIBONACCI_HEAP

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
       * Find spanning tree from the given graph
       *
       * @param graph
       *              the graph, for which spanning tree should be found
       * @param heap
       * 
       * @require isUndirected(graph) && isConnectedComponnent(graph)
       * 
       * @return return spanning Tree of the original graph
       * @throws IllegalAccessException
       */
      private Set<CustomEdge> findSpanningTree(Graph<Vertex, CustomEdge> graph,
                  DataStructure typeOfDataStruture) throws IllegalAccessException {

            // A Set of all edges from the minimum spanning tree
            Set<CustomEdge> spanningTree = null;

            // Initialize search status for all vertices
            Set<Vertex> exploredVertices = new HashSet<Vertex>();

            // A set of all vertices from the original graph
            Set<Vertex> unspann = new HashSet<Vertex>(this.originalGraph.vertexSet());

            // A map which store neighbor-vertices of every vertices in the
            // graph
//            Map<Vertex, Set<Vertex>> neightborMap = getNeighborMap(graph);
            Map<Vertex, List<Vertex>> neightborMap = getNeighborMap(graph);
            // Initialize predecessor of all vertices, which is "nothing"
            Map<Vertex, Vertex> predecessors = initPredecesors();

            // Priority queue keeps track of the nearest vertex to the current
            // being-built spanning tree
            IPriorityQueueForPrimAlg priorityQueue = getPriorityQueue(graph, typeOfDataStruture);

            // Start vertex can be any of the graph's Vertices
            Vertex start = (new LinkedList<Vertex>(unspann)).get(0);

            // Initialize start vertex
            priorityQueue.decreaseKey(start, 0);
            predecessors.put(start, start);
            exploredVertices.add(start);

            // As long as there's vertex that is unexplored
            while (!unspann.isEmpty()) {

                  // Take the nearest vertex to current being-built spanning
                  // tree
                  Vertex exploringVertex = priorityQueue.extractMin();

                  // Set as explored
                  exploredVertices.add(exploringVertex);

                  unspann.remove(exploringVertex);
                  
                  List<Vertex> nei = neightborMap.get(exploringVertex);
                  // For every neighbor of the exploring vertex
                  for (Vertex vertex : nei) {
                	  	
                	  	if (!unspann.contains(vertex)) {continue;}
                	  
                        // If it hasn't been explored yet
                        if (!exploredVertices.contains(vertex)) {
                        	 
                              // Find the shortest edge between the neighbor and
                              // current exploring vertex
                              CustomEdge shortestEdge = findShortestEgdes(graph, exploringVertex,
                                          vertex);
                        	
                              double edgeweight = graph.getEdgeWeight(shortestEdge);

                              // If the weight of the edge smaller than the key
                              // already exist
                              if (priorityQueue.getVertexKeyValue(vertex) > edgeweight) {

                                    priorityQueue.decreaseKey(vertex, edgeweight);
                                    predecessors.put(vertex, exploringVertex);

                              }
                        }
                  }
            }

            // Built the spanning tree in form of a set of edges
            spanningTree = EdgeSetOfSpanningTree(graph, predecessors);

            return spanningTree;
      }

      /**
       * 
       * @return
       */
      private Map<Vertex, Vertex> initPredecesors() {

            Map<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();

            Set<Vertex> setOfVertices = this.originalGraph.vertexSet();

            for (Vertex vertex : setOfVertices) {
                  predecessors.put(vertex, null);
            }

            return predecessors;
      }

      /**
       * Built the spanning tree in form of a set of edges Built the spanning
       * tree in form of a set of edges
       * 
       * @param graph
       * @param predecessors
       * @return
       */
      private Set<CustomEdge> EdgeSetOfSpanningTree(Graph<Vertex, CustomEdge> graph,
                  Map<Vertex, Vertex> predecessors) {
            Set<CustomEdge> edgesOfSpanningTree = new HashSet<CustomEdge>();

            for (Vertex v : predecessors.keySet()) {

                  Vertex predecessor = predecessors.get(v);

                  CustomEdge edge = findShortestEgdes(graph, v, predecessor);
                  if (edge != null) {
                        //
                        edgesOfSpanningTree.add(edge);
                  }
            }

            return edgesOfSpanningTree;
      }

      /**
       * If there are more than 1 edge between 2 vertices, find the shortest one
       * 
       * @param graph
       * @param v1
       * @param v2
       * @return
       */
      private CustomEdge findShortestEgdes(Graph<Vertex, CustomEdge> graph, Vertex v1, Vertex v2) {

            Set<CustomEdge> edges = graph.getAllEdges(v1, v2);

            CustomEdge shortestEdge = null;

            double minlength = Double.MAX_VALUE;

            if (edges != null) {
                  for (CustomEdge customEdge : edges) {

                        double edgeLength = graph.getEdgeWeight(customEdge);

                        if (edgeLength < minlength) {

                              shortestEdge = customEdge;
                              minlength = edgeLength;

                        }
                  }
            }
            return shortestEdge;
      }

      private void findTotalWeight() {

            if (this.minimunSpanningTreeEdgeSet == null) {
                  this.totalWeight = 0;
            }

            for (CustomEdge customEdge : this.minimunSpanningTreeEdgeSet) {
                  totalWeight += this.originalGraph.getEdgeWeight(customEdge);
            }

      }

      /**
       * Find all the neighbors of all vertices and store in a Map of vertices
       * 
       * @param graph
       * @return
       */
      private Map<Vertex, List<Vertex>> getNeighborMap(Graph<Vertex, CustomEdge> graph) {

            Map<Vertex, List<Vertex>> neighborMap = new HashMap<Vertex, List<Vertex>>();

            Set<Vertex> setOfVertex = graph.vertexSet();

            Set<CustomEdge> setOfEgde = graph.edgeSet();

            for (Vertex v : setOfVertex) {
                  neighborMap.put(v, new LinkedList<Vertex>());
            }

//            for (CustomEdge edge : setOfEgde) {

//                  Vertex source = graph.getEdgeSource(edge);
//                  Vertex target = graph.getEdgeTarget(edge);

//                  neighborMap.get(source).add(target);
//                  neighborMap.get(target).add(source);

//            }
            
            for (Vertex vertex : setOfVertex) {
				neighborMap.put(vertex, Graphs.neighborListOf(graph, vertex));
			}

            return neighborMap;
      }

      /**
       * Get the appropriate PriorityQueue for Prim-Algorithm
       * 
       * @param graph
       *              the given graph
       * @param typeOfDataStruture
       *              DataStructure for PriorityQueue -
       *              DataStructure.PRIORITYQUEUE for the basic priority queue -
       *              DataStructure.FIBONACCI_HEAP for fibonacci-heap-based
       *              priority queue
       * 
       * @return the appropriate PriorityQueue for Prim-Algorithm
       * @throws IllegalAccessException
       */
      private IPriorityQueueForPrimAlg getPriorityQueue(Graph<Vertex, CustomEdge> graph,
                  DataStructure typeOfDataStruture) throws IllegalAccessException {

            Map<Vertex, Double> vertexEnf = new HashMap<Vertex, Double>();
            for (Vertex vertex : graph.vertexSet()) {
                  vertexEnf.put(vertex, Double.POSITIVE_INFINITY);
            }

            IPriorityQueueForPrimAlg priorityQueue = null;

            switch (typeOfDataStruture) {
                  case PRIORITYQUEUE:
                        priorityQueue = new PriorityQueueWithoutFibonacciHeap(vertexEnf);
                        break;

                  case FIBONACCI_HEAP:
                        priorityQueue = new PriorityQueueFibonacciBased(vertexEnf);
                        break;

                  default:
                        throw new IllegalAccessException("The no such " + typeOfDataStruture
                                    + " type of priorty queue");
            }

            return priorityQueue;
      }

      /**
       * Get the original graph
       * 
       */
      public Graph<Vertex, CustomEdge> getOriginalGraph() {
            return this.originalGraph;
      }

      /**
       * Get the spanning tree
       */
      public Set<CustomEdge> getminimumSpanningTree() {
            return this.minimunSpanningTreeEdgeSet;
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
       * Check whether the graph is a connected component
       * 
       * @param graph
       *              the graph
       */
      public boolean isConnectedComponent(Graph<Vertex, CustomEdge> graph) {

            if (graph instanceof UndirectedGraph) {

                  return (new ConnectivityInspector<Vertex, CustomEdge>(
                              (UndirectedGraph<Vertex, CustomEdge>) graph)).isGraphConnected();

            } else {

                  return (new ConnectivityInspector<Vertex, CustomEdge>(
                              (DirectedGraph<Vertex, CustomEdge>) graph)).isGraphConnected();

            }
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
