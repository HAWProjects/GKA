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

public class AStarShortestPath {
	
	private List<Vertex> shortestPath;
	private Graph<Vertex, CustomEdge> graph;
	
	public AStarShortestPath(Graph<Vertex, CustomEdge> graph, Vertex start, Vertex target){
		this.graph = graph;
		this.shortestPath = searchForShortestPath(graph, start, target);
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
	
	private List<Vertex> searchForShortestPath(Graph<Vertex, CustomEdge> graph, Vertex start, Vertex target){
		
	// check whether the graph contains start vertex and target vertex
		checkForExistenceOfVertex(graph, start);
		checkForExistenceOfVertex(graph, target);
		
		Set<Vertex> setOfVetex = graph.vertexSet();
	
	//
		Map<Vertex, Double> heuristicValueOfVertex = new HashMap<Vertex, Double>();
		
		for (Vertex vertex : setOfVetex) {
			heuristicValueOfVertex.put(vertex,(double) vertex.getAttribut());
		}
	// Initialize predecessor of all vertices, which is "nothing"
		Map<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();
		for (Vertex vertex : setOfVetex) {
			predecessors.put(vertex, null);
		}
		
	// Initialize search status for all vertices
		Set<Vertex> exploredVertices = new HashSet<Vertex>();
		
	// Initialize distances to start vertex, every vertex but start has distance MaxInt
		Map<Vertex, Double> distanceToStartVertex = new HashMap<Vertex, Double>();
		for (Vertex vertex : setOfVetex) {
			distanceToStartVertex.put(vertex, Double.MAX_VALUE);
		}
	
	// Initialize FibonacciHeap, this Heap is good for keepping track of the minimum-key
		FibonacciHeap<Vertex> unexploredVetices = new FibonacciHeap<Vertex>();
		
	//This map is needed for keeping track of the nodes inside FibonacciHeap
		Map<Vertex, FibonacciHeapNode<Vertex>> mapOfFibonacciNode = new HashMap<Vertex, FibonacciHeapNode<Vertex>>();
		
		for (Vertex vertex : setOfVetex) {
			
			FibonacciHeapNode<Vertex> node = new FibonacciHeapNode<Vertex>(vertex);
			double guessValue = Double.MAX_VALUE;
			
			mapOfFibonacciNode.put(vertex, node);
			unexploredVetices.insert(node, guessValue);
		}
		
	// Initialize start vertex
		distanceToStartVertex.put(start, 0.0);
		predecessors.put(start, start);
		exploredVertices.add(start);
		Double guessValue = distanceToStartVertex.get(start) + heuristicValueOfVertex.get(start);
		unexploredVetices.decreaseKey(mapOfFibonacciNode.get(start), guessValue);
	
		boolean TargetFound = false;
	//main loops
	//As long as there's vertex that is unexplored and Target not found
		while(!TargetFound && !unexploredVetices.isEmpty()){
			
		//Among all unexplored vertices, take the one that has lowest guessValue to start
			Vertex exlploringVertex = unexploredVetices.removeMin().getData();
			mapOfFibonacciNode.remove(exlploringVertex);
			
		//And mark it as explored
			exploredVertices.add(exlploringVertex);
			if (exploredVertices.contains(target)) {
				TargetFound = true;
				continue;
			}
			
			double distToStartVerFromExploringVertex = distanceToStartVertex.get(exlploringVertex);
			
		//For every vertices that are unexplored
			for (Vertex vertex : mapOfFibonacciNode.keySet()){
				
				CustomEdge edge = graph.getEdge(exlploringVertex, vertex); 
				if(edge != null){
					double distToStartVertex = distanceToStartVertex.get(vertex);
					double edgeLength = graph.getEdgeWeight(edge);
					
					if(edgeLength < 0){
						throw new IllegalArgumentException("negative edge weights not allowed");
					}
					
					if (distToStartVertex > (distToStartVerFromExploringVertex + edgeLength)) {
						
					//reduce distance for this vertex
						double newDistToStart = distToStartVerFromExploringVertex + edgeLength;
						distanceToStartVertex.put(vertex, newDistToStart);
						double newGuessValue = newDistToStart + heuristicValueOfVertex.get(vertex);
						unexploredVetices.decreaseKey(mapOfFibonacciNode.get(vertex),newGuessValue);
						
					//add exploringVertex as predecessor of this vertex	
						predecessors.put(vertex, exlploringVertex);
					}
				}
			}
		}
		
		if (!TargetFound) {
			return null;
		}
		
		return makeShortestPath(predecessors, start, target);
	}
	
	private List<Vertex> makeShortestPath(Map<Vertex, Vertex> predecessors, Vertex start, Vertex target) {
		List<Vertex> shortestPath = new ArrayList<Vertex>();
		
		Vertex predecessor = target;
		if (predecessors.get(target) == null) {
			return null;
		}
		
		while(!predecessor.equals(start)){
			shortestPath.add(0,predecessor);
			predecessor = predecessors.get(predecessor);
		}
		
		shortestPath.add(0,start);
		return shortestPath;
	}
	
	
	private boolean checkForExistenceOfVertex(
			Graph<Vertex, CustomEdge> graph, Vertex v) {

		boolean vertexFound = false;
		for (Vertex vertex : graph.vertexSet()) {
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
}
