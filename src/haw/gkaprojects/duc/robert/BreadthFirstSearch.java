package haw.gkaprojects.duc.robert;

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
 * This Class provides search mechanism to find the shortest path in given graph
 * @author Robert Scheffel, Minh Duc Nguyen
 *
 */
public class BreadthFirstSearch {
	
//	private static int _steps = 0;
	/**
	 * search for shortest path 
	 * @param graph : given graph 
	 * @param start : start Vertex / Source Vertex
	 * @param target : target Vertex
	 * @return path in the form of a list of Vertex
	 */
	public static List<Vertex> searchForTheShortestPath(
			Graph<Vertex, CustomEdge> graph, Vertex start,
			Vertex target) {
		
		// find the actual source and target vertex in the graph
		start = findVertexInGraph(graph,start);
		target = findVertexInGraph(graph,target);
		
		List<Vertex> shortestPathVertexList = new ArrayList<>();
		List<CustomEdge> shortestPathEdgeList = new ArrayList<>();
		
		// 
		if(start.equals(target)){
			for (Vertex vertex : graph.vertexSet()) {
				if(vertex.equals(start)){
					shortestPathVertexList.add(vertex);
					setColorForVertex(shortestPathVertexList, graph);
					return shortestPathVertexList;
				}
			} 
		}
		
		//crate an adjazent-list for searching algorithm
		Map<Vertex, List<Vertex>> adjMapOfVertices = createADJMap(graph);
		shortestPathVertexList = findShortestPathVertexList(adjMapOfVertices, start, target);
		shortestPathEdgeList = findShortestPathEdgeList(graph, shortestPathVertexList);
		
		setColorForVertex(shortestPathVertexList,graph);
		setColorForShortestPath(shortestPathEdgeList);
		
		return shortestPathVertexList;
	}
	
	/*
	 * colourise all Vertex of the shortest path red
	 */
	private static void setColorForVertex(List<Vertex> shortestPathVertexList, Graph<Vertex, CustomEdge> graph) {
		
		Set<Vertex> setOfVertices = graph.vertexSet();
		for (Vertex vertexInTheList : shortestPathVertexList) {
			for (Vertex vertexOfGraph : setOfVertices) {
				if(vertexInTheList.equals(vertexOfGraph)){
					vertexOfGraph.setColor("red");
					break;
				}
			}
		}
	}
	
	/*
	 * colourise the shortest path red
	 */
	private static void setColorForShortestPath(
			List<CustomEdge> shortestPathEdgeList) {
		for (CustomEdge edge : shortestPathEdgeList) {
			edge.setColor("red");
		}
	}

	/**
	 * convert path in form of a list of edges into a list of vertices 
	 * @param graph
	 * @param shortestPathVertexList shortest-path list in form of a list of edges
	 * @return Vertices-list (shortest path)
	 */
	public static List<CustomEdge> findShortestPathEdgeList(
			Graph<Vertex, CustomEdge> graph,
			List<Vertex> shortestPathVertexList) {
		
		List<CustomEdge> pathlist = new ArrayList<>();
		
		for(int i=0; i< shortestPathVertexList.size() -1; i++){
			Vertex v1 = shortestPathVertexList.get(i);
			Vertex v2 = shortestPathVertexList.get(i+1);
//			graph.getEdge(v1, v2).setColor("red");
			CustomEdge edge = graph.getEdge(v1, v2);
			pathlist.add(edge);
		}
		
		return pathlist;
	}

	
/**
 * perform bread-first-search-algorithm to find the shortest way
 * @param adjMapOfVertices adjacency-list of the graph
 * @param start : source-vertex
 * @param target : target-vertex
 * @return shortest path in form of a vertex-list
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

	private static Vertex findVertexInGraph(
			Graph<Vertex, CustomEdge> graph, Vertex v ) {
		
		for (Vertex vertex : graph.vertexSet()) {
			if (v.equals(vertex)) {
				return vertex;
			}
		}
		
		return v;
	}

	/**
	 * fill vertex into the pathlist of the shortest way
	 * @param shortestPath vertex list
	 * @param start : source vertex
	 * @param target : target vertex
	 */
	private static void buildPathVertexList(List<Vertex> shortestPath, Vertex start, Vertex target) {
		
		Vertex predecessor = target;
		while(predecessor != null){
			shortestPath.add(0,predecessor);
			predecessor = predecessor.getPredecessor();
		}
	}

	/**
	 * creates the adjacency-list for the given graph
	 * @param graph 
	 * @return adjacency-list in form of a map
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
