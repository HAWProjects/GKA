package haw.gkaprojects.duc.robert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import org.jgrapht.Graph;

/**
 * This class finds the shortest way between two given Vertex in a given Graph.
 * Based on the Dijkstra Algorithmus.
 * 
 * @author Robert
 *
 */
public class OwnDijkstra {

	private ArrayList<CustomEdge> shortestWayEdgeList;
	private ArrayList<Vertex> shortestWayVertexList;
	private HashMap<Vertex, Double> vertexEntf;
	private HashMap<Vertex, Vertex> vertexVorg;

	public OwnDijkstra(Graph<Vertex, CustomEdge> graph, Vertex start,
			Vertex target) {

		shortestWayEdgeList = new ArrayList<>();
		shortestWayVertexList = new ArrayList<>();
		vertexEntf = new HashMap<>();
		vertexVorg = new HashMap<>();

		if (graph.containsVertex(start) && graph.containsVertex(target)
				&& checkEdgeWeightPositiv(graph)) {
			setInitSearch(graph, start);
			searchForShortestPath(graph, start, target);

		} else {
			System.out
					.println("Kann keinen Pfad finden weil: Kantengewicht negativ || Vertex nicht vorhanden || Kein Weg vorhanden");
		}
	}

	/**
	 * 
	 * @param graph
	 * @param start
	 * @param target
	 */
	private void searchForShortestPath(Graph<Vertex, CustomEdge> graph,
			Vertex source, Vertex target) {
		// Suche Vh mit kleinsten Entf und ok = false
		// set für alle Nachbarn

		// while not alle Knoten besucht
		while (!allVertexExplored(graph)) {

			if (source == target) {
				shortestWayVertexList.add(source);
			} else {
				HashSet<Vertex> successorVertex = new HashSet<>();

				// set von edges die am startpunkt dran hängen
				graph.edgesOf(source);
				for (CustomEdge e : graph.edgesOf(source)) {

					if (graph.getEdgeTarget(e).getSearchStatus() == Vertex.UNEXPLORED) { // /??????????????????
						successorVertex.add(graph.getEdgeTarget(e));
						vertexVorg.put(graph.getEdgeTarget(e), source);
						vertexEntf.put(graph.getEdgeTarget(e),
								graph.getEdgeWeight(e));
					}
					// vertexEntf
					searchForShortestPath(graph, source, target);
				}
			}
		}

	}

	private boolean allVertexExplored(Graph<Vertex, CustomEdge> graph) {
		for(Vertex v : graph.vertexSet()){
			if(v.getSearchStatus() == Vertex.UNEXPLORED){
			return false;}
		}
		return true;
	}

	/**
	 * 
	 * @param graph
	 * @param start
	 */
	private void setInitSearch(Graph<Vertex, CustomEdge> graph, Vertex start) {
		for (Vertex v : graph.vertexSet()) {
			vertexEntf.put(v, Double.MAX_VALUE);
			v.setSearchStatus(Vertex.UNEXPLORED);
		}
		vertexEntf.put(start, 0.0);
		vertexVorg.put(start, start);
	}

	/*
	 * checks if edgeweight positiv returns boolean
	 */
	private boolean checkEdgeWeightPositiv(Graph<Vertex, CustomEdge> graph) {
		for (CustomEdge e : graph.edgeSet()) {

			if (graph.getEdgeWeight(e) < 0) {
				return false;
			}
		}
		return true;
	}

	// List<Vertex> getShortestPath()
	// double getShortestLength()
}
