package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.EulerianCircuit;

public class FleuryEulerianCircuit<V, E>
{
	private List<E> eulerianCircuit;
	private double totalWeight;

	public FleuryEulerianCircuit(UndirectedGraph<V, E> graph)
	{
		if (isEulerian(graph))
		{
			createEuler(graph);
		}
	}

	public List<E> getEulerianCircuit()
	{
		return eulerianCircuit;
	}

	public double getTotalWeight()
	{
		return totalWeight;
	}

	public boolean isEulerian(UndirectedGraph<V, E> graph)
	{
		return EulerianCircuit.isEulerian(graph);
	}

	private void createEuler(UndirectedGraph<V, E> graph)
	{
		// all Vertex of the original graph
		Set<Vertex> vertexSet = (Set<Vertex>) graph.vertexSet();
		// EdgeSet
		Set<?> edgeList = graph.edgeSet();
		// EdgeQueue
		Queue<?> edgeQueue = new ConcurrentLinkedQueue<>(graph.edgeSet());

		// Ergebnis Liste
		List<CustomEdge> resultlist = new ArrayList();

		// deleteListe
		List<CustomEdge> deletedEdgeList = new LinkedList<CustomEdge>();

		// Startpunkt
		Iterator<Vertex> itV = vertexSet.iterator();
		Vertex start = itV.next();

		// benachbarte Knoten
		while (!edgeQueue.isEmpty())
		{
			Vertex currentVertex = start;

			Set<?> neighbourEdgeset = (Set<CustomEdge>) graph.edgesOf((V) currentVertex);
			Iterator<CustomEdge> itNeighbor = (Iterator<CustomEdge>) neighbourEdgeset.iterator();

			// mehr als eine alsgehende kante
			if (neighbourEdgeset.size() >= 2)
			{
				while (itNeighbor.hasNext())
				{
					CustomEdge currentEdge = itNeighbor.next();
					if (isEdgeNotABridge(currentEdge, graph))
					{
						resultlist.add(currentEdge);
						currentVertex = (Vertex)graph.getEdgeTarget((E)currentEdge);
						break;
					}
			
				}
				// nur eine ausgehende kante
			}
			else
			{
				CustomEdge currentEdge = itNeighbor.next();
				resultlist.add(currentEdge);
				currentVertex = (Vertex)graph.getEdgeTarget((E)currentEdge);
				graph.removeEdge((V)graph.getEdgeSource((E)currentEdge), (V)graph.getEdgeTarget((E)currentEdge));
				// kante in ergebnis liste einfuegen
			}

			// update edelist
			edgeList = graph.edgeSet();
		}
	}

	private CustomEdge getNextEdge(Set<?> edgeList)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isEdgeNotABridge(CustomEdge e, UndirectedGraph<V, E> graph)
	{
		Vertex source = (Vertex) graph.getEdgeSource((E) e);
		Vertex target = (Vertex) graph.getEdgeTarget((E) e);
		graph.removeEdge((E) e);

		if (graph.containsVertex((V) source) && graph.containsVertex((V) target))
		{
			DijkstraShortestPath<Vertex, CustomEdge> dijkstra = new DijkstraShortestPath<Vertex, CustomEdge>((Graph<Vertex, CustomEdge>) graph,
					source, target);
			graph.addEdge((V) source, (V) target);
			return dijkstra.getPath() != null;
		}
		graph.addEdge((V) source, (V) target);
		return false;
	}

	/*
	 * checks for circle
	 * 
	 * @param graph
	 * 
	 * @param e Edge
	 * 
	 * @return boolean
	 */
	private boolean checkforCircle(UndirectedGraph<V, E> graph, Vertex source, Vertex target)
	{
		if (graph.containsVertex((V) source) && graph.containsVertex((V) target))
		{
			DijkstraShortestPath<Vertex, CustomEdge> dijkstra = new DijkstraShortestPath<Vertex, CustomEdge>((Graph<Vertex, CustomEdge>) graph,
					source, target);
			return dijkstra.getPath() != null;
		}
		return false;
	}
}
