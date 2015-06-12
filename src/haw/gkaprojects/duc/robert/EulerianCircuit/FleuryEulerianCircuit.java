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
		Set<V> vertexSet = graph.vertexSet();
		// EdgeSet
		Set<?> edgeList = graph.edgeSet();
		// EdgeQueue
		Queue<?> edgeQueue = new ConcurrentLinkedQueue<>(graph.edgeSet());

		// deleteListe
		List<CustomEdge> deletedEdgeList = new LinkedList<CustomEdge>();

		// Startpunkt
		Iterator<V> itV = vertexSet.iterator();
		V start = itV.next();

		// benachbarte Knoten
		while (!edgeQueue.isEmpty())
		{
			V currentVertex = start;

			Set<?> neighbourEdgeset = graph.edgesOf(currentVertex);
			Iterator<E> itNeighbor = (Iterator<E>) neighbourEdgeset.iterator();

			// mehr als eine alsgehende kante
			if (neighbourEdgeset.size() >= 2)
			{
				while (itNeighbor.hasNext())
				{
					E currentEdge = itNeighbor.next();
					if (isEdgeNotABridge(currentEdge, graph))
					{
						eulerianCircuit.add(currentEdge);
						currentVertex = graph.getEdgeTarget(currentEdge);
						graph.removeEdge(graph.getEdgeSource(currentEdge), graph.getEdgeTarget(currentEdge));
						break;
					}
				}
			}
			else // nur eine ausgehende kante
			{
				E currentEdge = itNeighbor.next();
				// kante in ergebnis liste einfuegen
				eulerianCircuit.add(currentEdge);
				currentVertex = graph.getEdgeTarget((E)currentEdge);
				//kante entfernen
				graph.removeEdge((V)graph.getEdgeSource((E)currentEdge), (V)graph.getEdgeTarget((E)currentEdge));
			}
			// update edelist
			edgeList = graph.edgeSet();
		}
	}

	private boolean isEdgeNotABridge(E e, UndirectedGraph<V, E> graph)
	{
		V source = graph.getEdgeSource(e);
		V target = graph.getEdgeTarget(e);
		graph.removeEdge((E) e);

		if (graph.containsVertex((V) source) && graph.containsVertex((V) target))
		{
			DijkstraShortestPath<Vertex, CustomEdge> dijkstra = new DijkstraShortestPath<Vertex, CustomEdge>((Graph<Vertex, CustomEdge>) graph,
					(Vertex)source, (Vertex)target);
			graph.addEdge((V) source, (V) target);
			return dijkstra.getPath() != null;
		}
		graph.addEdge((V) source, (V) target);
		return false;
	}

	/*
	 * checks for circle
	 * @param graph
	 * @param e Edge
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
