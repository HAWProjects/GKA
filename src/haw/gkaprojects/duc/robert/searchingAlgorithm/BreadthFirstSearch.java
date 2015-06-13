package haw.gkaprojects.duc.robert.searchingAlgorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

/**
 * This class privides  search mechanism to find the shortest path
 * 
 * @author Robert Scheffel & DucNguyenMinh
 *
 */
public class BreadthFirstSearch {
	
//	private static int _steps = 0;
	
	/**
	 * Search for the shortest path
	 * 
	 * @param graph graph to find the shortest path
	 * @param start Source vertex
	 * @param target target vertex
	 * @return path in the from of a vertex-list
	 */
	public static List<Vertex> searchForTheShortestPath(
			Graph<Vertex, CustomEdge> graph, Vertex start,
			Vertex target) {
		
		//find the actual source and target vertices in the given graph
		checkIfVertexInGraph(graph,start);
		checkIfVertexInGraph(graph,target);
		
		List<Vertex> shortestPathVertexList = new ArrayList<>();
		
		//
		if(start.equals(target)){
			for (Vertex vertex : graph.vertexSet()) {
				if(vertex.equals(start)){
					shortestPathVertexList.add(vertex);
					return shortestPathVertexList;
				}
			} 
		}
		
		//create Adjacency-lists for searching algorithm 
		Map<Vertex, List<Vertex>> adjMapOfVertices = createADJMap(graph);
		shortestPathVertexList = findShortestPathVertexList(adjMapOfVertices, start, target);
		
		return shortestPathVertexList;
	}
	
	
	/**
	 * Convert path in the form of list of Edges into list of Vertices
	 * 
	 * @param graph 
	 * @param shortestPathVertexList path in form of Edges-list
	 * @return path in form of Vertices-list
	 */
	 public static List<CustomEdge> findShortestPathEdgeList(
			Graph<Vertex, CustomEdge> graph,
			List<Vertex> shortestPathVertexList) {
		
		List<CustomEdge> pathlist = new ArrayList<>();
		
		for(int i=0; i< shortestPathVertexList.size() -1; i++){
			Vertex v1 = shortestPathVertexList.get(i);
			Vertex v2 = shortestPathVertexList.get(i+1);
			CustomEdge edge = graph.getEdge(v1, v2);
			pathlist.add(edge);
		}
		
		return pathlist;
	}

	/**
	 * perform "breadth-first-search" - algorithm to find shortest path 
	 * 	 
	 * @param adjMapOfVertices Adjacency-lists of the graph
	 * @param start	Source vertex
	 * @param target Target vertex
	 * @return shortest path in form of vertices-list
	 */
	private static List<Vertex> findShortestPathVertexList(
			Map<Vertex, List<Vertex>> adjMapOfVertices, Vertex start, Vertex target) {
		
		//shortest Path save in list
		List<Vertex> shortestPath = new ArrayList<>();
		
		//Init every vertex in the graph 
		for (Map.Entry<Vertex, List<Vertex>> entry :adjMapOfVertices.entrySet()) {
			Vertex vertex= entry.getKey();
			
			//vertex belongs to G.V-{s}
			if(vertex.equals(start)) continue;
			vertex.setSearchStatus(Vertex.UNEXPLORED);
			vertex.setLevel(Integer.MAX_VALUE);
			vertex.setPredecessor(null);
		}
		
		//Init start vertex
		start.setLevel(0);
		start.setSearchStatus(Vertex.EXPLORING);
		start.setPredecessor(null);
		
		//searching queue
		Queue<Vertex> queue = new ArrayDeque<>();
		queue.add(start);
		
		boolean targetFound = false;
		
		//traversal loop
		while(!queue.isEmpty() && !targetFound){
			Vertex u = queue.poll();
			List<Vertex> adjlist = adjMapOfVertices.get(u);
			
			for (Vertex vertex : adjlist) {
				if(vertex.getSearchStatus() == Vertex.UNEXPLORED){
					vertex.setSearchStatus(Vertex.EXPLORING);
					vertex.setLevel(u.getLevel() + 1);
					vertex.setPredecessor(u);
					queue.add(vertex);
				}
				
				if(vertex.equals(target)) {
					buildPathVertexList(shortestPath,start, vertex);
					targetFound = true;
					break;
				}
			}
			u.setSearchStatus(Vertex.EXPLORED);	
		}
		
		return shortestPath; 
	}

	/**
	 * Find the actual vertex in the graph with the same name as th given one
	 * 
	 * @param graph the  graph	
	 * @param v	the given vertex to be found
	 * @return
	 */
	private static void checkIfVertexInGraph(
			Graph<Vertex, CustomEdge> graph, Vertex v ) {
		
		boolean vertexFound = false;
		for (Vertex vertex : graph.vertexSet()) {
			if (v.equals(vertex)) {
				v = vertex;
				vertexFound = true;
				break;
			}
		}
		
		if (!vertexFound) {
			throw new IllegalArgumentException("Graph must contain given Vertex");
		}
	}
	
	/**
	 * fill the path with vertices between Source and Target
	 *  
	 * @param shortestPath vertices-list to be filled in
	 * @param start	Source Vertex	
	 * @param target Target vertex
	 */
	private static void buildPathVertexList(List<Vertex> shortestPath, Vertex start, Vertex target) {
		
		Vertex predecessor = target;
		while(predecessor != null){
			shortestPath.add(0,predecessor);
			predecessor = predecessor.getPredecessor();
		}
	}

	/**
	 * 
	 * create the adjacency list for the given graph
	 * 
	 * @param graph graph to be convert into adjacency list
	 * @return adjacency list in form of a map
	 */
	private static Map<Vertex, List<Vertex>> createADJMap(
			Graph<Vertex, CustomEdge> graph) {
		//
		Map<Vertex, List<Vertex>> adjMapofVertices = new HashMap<>();

		//
		Set<Vertex> setOfVertices = graph.vertexSet();

		//
		Set<CustomEdge> setOfIntouchEdges = null;

		//
		for (Vertex vertex : setOfVertices) {
			List<Vertex> listOfNeighborVertex = new ArrayList<>();
		
			if (graph instanceof UndirectedGraph) {
				setOfIntouchEdges = graph.edgesOf(vertex);

				for (CustomEdge edge : setOfIntouchEdges) {
					if (vertex.equals( graph.getEdgeSource(edge))) {
	
						listOfNeighborVertex.add(graph.getEdgeTarget(edge));
					} else {
					
						listOfNeighborVertex.add(graph.getEdgeSource(edge));
					}
				}
			} else if (graph instanceof DirectedGraph) {

				setOfIntouchEdges = ((AbstractBaseGraph<Vertex, CustomEdge>) graph)
						.outgoingEdgesOf(vertex);

				for (CustomEdge edge : setOfIntouchEdges) {
					listOfNeighborVertex.add(graph.getEdgeTarget(edge));
				}
			}
			
			adjMapofVertices.put(vertex	, listOfNeighborVertex);
		}
		
		return adjMapofVertices;
	}
}
