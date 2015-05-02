package haw.gkaprojects.duc.robert.searchingAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

public class ShortestPathOfDijkstras {

	private List<Vertex> shortestPath;
	private Graph<Vertex, CustomEdge> graph;
	private int zugriffCounter;
	
	public ShortestPathOfDijkstras(Graph<Vertex, CustomEdge> graph, Vertex start, Vertex target){
		this.graph = graph;
		this.shortestPath = searchForShortesPath(graph, start, target);
		this.zugriffCounter = 0;
	}
	
	public List<Vertex> getShortestPath() {
		return this.shortestPath;
	}

	public double getShortestPathLength() {
		
		double shortestPathLength = 0;
		
		for (int i = 0; i < this.shortestPath.size() - 1 ; i++) {
			Vertex v1 = this.shortestPath.get(i);
			Vertex v2 = this.shortestPath.get(i+1);
			CustomEdge edge = this.graph.getEdge(v1, v2);
			shortestPathLength += this.graph.getEdgeWeight(edge);
		}
		
		return shortestPathLength;
	}
	private List<Vertex> searchForShortesPath(
			Graph<Vertex, CustomEdge> graph, Vertex start, Vertex target) {

	// check whether the graph contains start vertex and target vertex
		checkForExistenceOfVertex(graph, start);
		checkForExistenceOfVertex(graph, target);

		Set<Vertex> setOfVetex = graph.vertexSet(); this.zugriffCounter++;
//		System.out.println(setOfVetex);

	// Initialize predecessor of all vertices, which is "nothing"
		Map<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();
		for (Vertex vertex : setOfVetex) {
			this.zugriffCounter++;
			predecessors.put(vertex, null);
		}

	// Initialize search status for all vertices
		Set<Vertex> exploredVertices = new HashSet<Vertex>();

	// Initialize distances to start vertex, every vertex but start has distance MaxInt
		Map<Vertex, Double> distanceToStartVertex = new HashMap<Vertex, Double>();
		for (Vertex vertex : setOfVetex) {
			this.zugriffCounter++;
			distanceToStartVertex.put(vertex, Double.MAX_VALUE);
		}

	// Initialize FibonacciHeap, this Heap is good for keepping track of the minimum-key
		FibonacciHeap<Vertex> unexploredVetices = new FibonacciHeap<Vertex>();
	
	//This map is needed for keeping track of the nodes inside FibonacciHeap
		Map<Vertex, FibonacciHeapNode<Vertex>> mapOfFibonacciNode = new HashMap<Vertex, FibonacciHeapNode<Vertex>>(); 
		
		for (Vertex vertex : setOfVetex) {
			this.zugriffCounter+=2;

			FibonacciHeapNode<Vertex> node = new FibonacciHeapNode<Vertex>(
					vertex);
			double distanceToStart = distanceToStartVertex.get(vertex);

			mapOfFibonacciNode.put(vertex, node);
			unexploredVetices.insert(node, distanceToStart);
		}

	// Initialize start vertex
		distanceToStartVertex.put(start, 0.0);
		predecessors.put(start, start);
		exploredVertices.add(start);
		unexploredVetices.decreaseKey(mapOfFibonacciNode.get(start),
				distanceToStartVertex.get(start));

	// main loops
	// As long as there's vertex that is unexplored
		while (!unexploredVetices.isEmpty()) {
			
		//Among all unexplored vertices, take the one that has shortest distance to start
			Vertex searchingVertex = unexploredVetices.removeMin().getData();
			mapOfFibonacciNode.remove(searchingVertex);
			
		//And mark it as explored
			exploredVertices.add(searchingVertex);
			this.zugriffCounter++;
			
			double distToStartOfSearchingVertex = distanceToStartVertex
					.get(searchingVertex);
		
		//For every vertices that are unexplored	
			for (Vertex vertex : mapOfFibonacciNode.keySet()) {
				
				CustomEdge edge = graph.getEdge(searchingVertex, vertex);
			this.zugriffCounter++;
				if (edge != null) {
					
					double distToStartOfVertex = distanceToStartVertex
							.get(vertex);
					double edgesLength = graph.getEdgeWeight(edge);
			this.zugriffCounter++;
				
					if (edgesLength < 0) {
						throw new IllegalArgumentException("negative edge weights not allowed");
					}
					
					if (distToStartOfVertex > (distToStartOfSearchingVertex + edgesLength)) {
						
					//reduce distance for this vertex 
						distanceToStartVertex.put(vertex,
								distToStartOfSearchingVertex + edgesLength);
						unexploredVetices.decreaseKey(mapOfFibonacciNode.get(vertex), distanceToStartVertex.get(vertex));
						
					//add searchingVertex as predecessor of this vertex
						predecessors.put(vertex, searchingVertex);
			this.zugriffCounter++;
					
					}
				}
			}
		}
		
		return makeShortestPath(predecessors, start,target);
	}

	private List<Vertex> makeShortestPath(Map<Vertex, Vertex> predecessors, Vertex start, Vertex target) {
		List<Vertex> shortestPath = new ArrayList<Vertex>();
		
		Vertex predecessor = target;
		if (predecessors.get(target) == null) {
			return null;
		}
		
		while(!predecessor.equals(start)){
			shortestPath.add(0,predecessor);
			this.zugriffCounter++;
			predecessor = predecessors.get(predecessor);
		}
		
		shortestPath.add(0,start);
		return shortestPath;
	}

	private boolean checkForExistenceOfVertex(
			Graph<Vertex, CustomEdge> graph, Vertex v) {

		boolean vertexFound = false;
		for (Vertex vertex : graph.vertexSet()) {
			this.zugriffCounter++;
			if (v.equals(vertex)) {
				vertexFound = true;
				break;
			}
		}

		if (!vertexFound) {
			throw new IllegalArgumentException(
					"Graph must contain given Vertex");
		}
		return vertexFound;
	}

	public int getZugriffCounter() {
		return this.zugriffCounter;
	}
}
