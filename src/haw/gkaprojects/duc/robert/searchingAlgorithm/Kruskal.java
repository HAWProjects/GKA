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
//			 createGraphFramework(graph);
			createSpanningtree(graph);
		}
	}

	/*
	 * creates spanning tree 
	 * @param graph
	 */
	private void createSpanningtree(Graph<Vertex, CustomEdge> graph)
	{
		// Knotenmenge ausgangsgraph
		Set<Vertex> vertexSet = graph.vertexSet();
				
		// Kanten ausgangsgraph sortiert
		PriorityQueue<CustomEdge> edgeQueue = new PriorityQueue<>(graph.edgeSet());

		// StartVertex/Graph bestimmen
		Iterator<Vertex> it = vertexSet.iterator();
		Vertex vStart = it.next();

		// graph ohne kanten erzeugen mit einem Start Knoten
		newGraph = new WeightedPseudograph<>(CustomEdge.class);
		newGraph.addVertex(vStart);

		// solange noch nicht alle Knoten drin sind
		while (!edgeQueue.isEmpty())
		{
			CustomEdge e = edgeQueue.poll();
			Vertex vSource = newGraph.getEdgeSource(e);
			Vertex vTarget = newGraph.getEdgeTarget(e);
			//if no circle add Edge
			if (!checkforCircle(graph, e))
			{
				newGraph.addVertex(vSource);
				newGraph.addVertex(vTarget);
				newGraph.addEdge(vSource, vTarget);
				// kantengewicht hinzufügen
				((WeightedGraph<Vertex, CustomEdge>) newGraph).setEdgeWeight(newGraph.getEdge(vSource, vTarget), graph.getEdgeWeight(e));
				weightOfSpanningTree += newGraph.getEdgeWeight(e);
			}
		}
	}

	private boolean checkforCircle(Graph<Vertex, CustomEdge> graph, CustomEdge e)
	{
		if (newGraph.containsVertex(graph.getEdgeSource(e)) && newGraph.containsVertex(graph.getEdgeTarget(e)))
		{
			DijkstraShortestPath<Vertex, CustomEdge> dijkstra = new DijkstraShortestPath<Vertex, CustomEdge>(newGraph, graph.getEdgeSource(e),
					graph.getEdgeTarget(e));
			return dijkstra.getPath() != null;
		}
		return false;
	}

	
	/**
	 * returns the spanningtree
	 * @return spanningtree(Graph<Vertex, CustomEdge>)
	 */
	public Graph<Vertex, CustomEdge> getSpanningTree()
	{
		return newGraph;
	}
	
	/**
	 * returns the weigth of the spanningtree
	 * @return double
	 */
	public double getWeightOfSpanningTree()
	{
		return weightOfSpanningTree;
	}

}
