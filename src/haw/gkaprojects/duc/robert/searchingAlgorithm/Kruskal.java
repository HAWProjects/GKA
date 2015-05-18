package haw.gkaprojects.duc.robert.searchingAlgorithm;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.WeightedPseudograph;

public class Kruskal
{
	Graph<Vertex, CustomEdge> newGraph;
	double weightOfSpanningTree;

	public Kruskal(Graph<Vertex, CustomEdge> graph)
	{
		if (graph instanceof WeightedPseudograph)
		{

			// Gerüst aus graph erstellen
			// createGraphFramework(graph);
			createGraphFramework2(graph);

			// Menge Minimalgerüst ausgeben

			// summe Gewicht von Minimalspannbaum
			calculateWeightOFSpanningTree();
		}
	}

	private void createGraphFramework2(Graph<Vertex, CustomEdge> graph)
	{
		// Knotenmenge
		Set<Vertex> vertexSet = graph.vertexSet();
		// Tupellist
		List<CustomEdge> edgeList = new ArrayList<CustomEdge>();
		for (CustomEdge e : graph.edgeSet())
		{
			edgeList.add(e);
		}

		// Kantenliste sortieren nach Kante mit kleinstem Gewicht
		Collections.sort(edgeList);

		PriorityQueue<CustomEdge> edgeQueue = new PriorityQueue<>();
		edgeQueue.addAll(edgeList);

		// StartVertex bestimmen
		Iterator<Vertex> it = vertexSet.iterator();
		Vertex vStart = it.next();

		// graph ohne kanten erzeugen mit einem Start Knoten
		newGraph = new WeightedPseudograph<>(CustomEdge.class);
		newGraph.addVertex(vStart);

		// Stertvertex zum erreichbar Set hinzufügen
		HashSet<Vertex> newVertexSet = new HashSet<Vertex>();
		newVertexSet.add(vStart);

		// solange noch nicht alle Knoten drin sind
		while (!edgeQueue.isEmpty())
		{
			CustomEdge e = edgeQueue.poll();

			Vertex vSource = newGraph.getEdgeSource(e);
			Vertex vTarget = newGraph.getEdgeTarget(e);

			if(!checkforCircle(newGraph, graph, e)){
				newGraph.addVertex(vSource);
				newGraph.addVertex(vTarget);
				newGraph.addEdge(vSource, vTarget);
				// kantengewicht hinzufügen
				((WeightedGraph<Vertex, CustomEdge>) newGraph).setEdgeWeight(newGraph.getEdge(vSource, vTarget),
						graph.getEdgeWeight(e));
			}
		}
	}

	private boolean checkforCircle(Graph<Vertex, CustomEdge> newGraph, Graph<Vertex, CustomEdge> graph, CustomEdge e)
	{
		if(newGraph.containsVertex(graph.getEdgeSource(e))&& newGraph.containsVertex(graph.getEdgeTarget(e))){
			DijkstraShortestPath<Vertex, CustomEdge> dijkstra = new DijkstraShortestPath<Vertex, CustomEdge>(newGraph, graph.getEdgeSource(e), graph.getEdgeTarget(e));
			return dijkstra.getPath() != null;
		}
		return false;
	}

//	
//	private void createGraphFramework(Graph<Vertex, CustomEdge> graph)
//	{
//		// Knotenmenge
//		Set<Vertex> vertexSet = graph.vertexSet();
//		// Tupellist
//		List<CustomEdge> edgeList = new ArrayList<CustomEdge>();
//		for (CustomEdge e : graph.edgeSet())
//		{
//			edgeList.add(e);
//		}
//
//		// graph ohne kanten erzeugen aber mit allen Knoten
//		newGraph = new WeightedPseudograph<>(CustomEdge.class);
//		for (Vertex v : vertexSet)
//		{
//			newGraph.addVertex(v);
//		}
//		// Kantenliste sortieren nach Kante mit kleinstem Gewicht
//		Collections.sort(edgeList);
//		System.out.println(edgeList);
//		// StartVertex bestimmen
//		Iterator<Vertex> it = vertexSet.iterator();
//		Vertex vStart = it.next();
//		System.out.println(vStart);
//
//		// Kanten hinzufügen, mit dem kleinsten Gewicht anfangen
//		for (CustomEdge e : edgeList)
//		{
//			Vertex vSource = newGraph.getEdgeSource(e);
//			Vertex vTarget = newGraph.getEdgeTarget(e);
//
//			// Wenn null zurückgegeben wird, also kein Pfad vorahnden füge Kante
//			// in Graphen
//			if (DijkstraShortestPath.findPathBetween(newGraph, vStart, vTarget) == null|| DijkstraShortestPath.findPathBetween(newGraph, vStart, vSource) == null)
//			{
//				newGraph.addEdge(vSource, vTarget);
//				// kantengewicht hinzufügen
//				((WeightedGraph<Vertex, CustomEdge>) newGraph).setEdgeWeight(newGraph.getEdge(vSource, vTarget),
//						graph.getEdgeWeight(graph.getEdge(vSource, vTarget)));
//			}
//		}
//	}

	private void calculateWeightOFSpanningTree()
	{
		for (CustomEdge e : newGraph.edgeSet())
		{
			weightOfSpanningTree += newGraph.getEdgeWeight(e);
		}
	}

	public Graph<Vertex, CustomEdge> getSpanningTree()
	{
		return newGraph;
	}

	public double getWeightOfSpanningTree()
	{
		return weightOfSpanningTree;
	}

}
