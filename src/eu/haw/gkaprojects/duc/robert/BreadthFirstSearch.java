package eu.haw.gkaprojects.duc.robert;

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


public class BreadthFirstSearch {
	
	private static int _steps = 0;
	
	public static List<CustomEdge> searchForTheShortestPath(
			Graph<Vertex, CustomEdge> graph, Vertex start,
			Vertex target) {

		start = findVertexInGraph(graph,start);
		target = findVertexInGraph(graph,target);
		
		List<Vertex> shortestPathVertexList = new ArrayList<>();
		List<CustomEdge> shortestPathEdgeList = new ArrayList<>();
		
		if(start.equals(target)){
			for (Vertex vertex : graph.vertexSet()) {
				if(vertex.equals(start)){
					shortestPathVertexList.add(vertex);
					setColorForVertex(shortestPathVertexList, graph);
					return shortestPathEdgeList;
				}
			} 
		}
		
		Map<Vertex, List<Vertex>> adjMapOfVertices = createADJMap(graph);
		shortestPathVertexList = findShortestPathVertexList(adjMapOfVertices, start, target);
		shortestPathEdgeList = findShortestPathEdgeList(graph, shortestPathVertexList);
		
		setColorForVertex(shortestPathVertexList,graph);
		setColorForShortestPath(shortestPathEdgeList);
		
		return shortestPathEdgeList;
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
	 * colourise the shortes path red
	 */
	private static void setColorForShortestPath(
			List<CustomEdge> shortestPathEdgeList) {
		for (CustomEdge edge : shortestPathEdgeList) {
			edge.setColor("red");
		}
	}

	private static List<CustomEdge> findShortestPathEdgeList(
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

	private static void resetVertex(Set<Vertex> vertexSet) {
		for (Vertex vertex : vertexSet) {
			vertex.setLevel(Integer.MAX_VALUE);
			vertex.setSearchStatus(Vertex.UNEXPLORED);
			vertex.setPredecessor(null);
		}
	}

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
					fillPathVertexList(shortestPath,start, vertex);
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

	private static void fillPathVertexList(List<Vertex> shortestPath, Vertex start, Vertex target) {
		
		Vertex predecessor = target;
		while(predecessor != null){
			shortestPath.add(0,predecessor);
			predecessor = predecessor.getPredecessor();
		}
	}

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
