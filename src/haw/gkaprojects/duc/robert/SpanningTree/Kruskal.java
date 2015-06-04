package haw.gkaprojects.duc.robert.SpanningTree;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.searchingAlgorithm.BreadthFirstSearch;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.WeightedPseudograph;
import org.jgrapht.traverse.DepthFirstIterator;

public class Kruskal
{
	// spanningtree
	Graph<Vertex, CustomEdge> newGraph;
	double weightOfSpanningTree;

	public Kruskal(Graph<Vertex, CustomEdge> graph) throws Exception
	{
		if (graph instanceof WeightedPseudograph)
		{
			createSpanningtree(graph);
		}else{
			throw new Exception("Graph ist kein WeightedPseudograph!");
		}
	}

	/*
	 * creates spanning tree 
	 * @param graph
	 */
	private void createSpanningtree(Graph<Vertex, CustomEdge> graph)
	{
		// all Vertex of the original graph
		Set<Vertex> vertexSet = graph.vertexSet();
				
		//sort Edges
		PriorityQueue<CustomEdge> edgeQueue = new PriorityQueue<>(graph.edgeSet());

		// init startVertex/Graph 
		Iterator<Vertex> it = vertexSet.iterator();
		Vertex vStart = it.next();

		// create new Graph composed of only one Vertex 
		newGraph = new WeightedPseudograph<>(CustomEdge.class);
		newGraph.addVertex(vStart);

		//if queue not empty
		while (!edgeQueue.isEmpty())
		{
			CustomEdge e = edgeQueue.poll();
			Vertex vSource = graph.getEdgeSource(e);
			Vertex vTarget = graph.getEdgeTarget(e);
			//if no circle add Edge
			if (!checkforCircle(graph, e))
			{
				newGraph.addVertex(vSource);
				newGraph.addVertex(vTarget);
				newGraph.addEdge(vSource, vTarget);
				// add weight of the edge
				((WeightedGraph<Vertex, CustomEdge>) newGraph).setEdgeWeight(newGraph.getEdge(vSource, vTarget), graph.getEdgeWeight(e));
				// add weight to total weight
				weightOfSpanningTree += newGraph.getEdgeWeight(e);
			}
		}
	}
	
	/*
	 * checks for circle 
	 * @param graph
	 * @param e Edge
	 * @return boolean
	 */
	private boolean checkforCircle(Graph<Vertex, CustomEdge> graph, CustomEdge e)
	{
		if (newGraph.containsVertex(graph.getEdgeSource(e)) && newGraph.containsVertex(graph.getEdgeTarget(e)))
		{
			
			DijkstraShortestPath<Vertex, CustomEdge> dijkstra = new DijkstraShortestPath<Vertex, CustomEdge>(newGraph, graph.getEdgeSource(e),					
					graph.getEdgeTarget(e));
			return dijkstra.getPath() != null;
			
//			return BreadthFirstSearch.searchForTheShortestPath(newGraph, graph.getEdgeSource(e), graph.getEdgeTarget(e)) != null;
			
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
	 * @return Weigth of Spanningtree (double)
	 */
	public double getWeightOfSpanningTree()
	{
		return weightOfSpanningTree;
	}

}
