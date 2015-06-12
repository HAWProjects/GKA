package haw.gkaprojects.duc.robert.EulerianCircuit;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.Pseudograph;

public class FleuryEulerian<V, E>
{

	private List<E> eulerianCircuit;

	public FleuryEulerian(UndirectedGraph<V, E> graph)
	{
		if (isEulerian(graph))
		{
			UndirectedGraph<V, E> newGraph = (UndirectedGraph<V, E>) ((AbstractBaseGraph<V, E>) graph).clone();
			createEuler(newGraph);
		}
	}

	private void createEuler(UndirectedGraph<V, E> newgraph)
	{
		// bei einem Knoten starten
		Set<E> edgeSet = newgraph.edgeSet();

		// waehle einen kante
		Set<V> vertexSet = newgraph.vertexSet();
		Iterator<V> iterVset = vertexSet.iterator();
		V startV = null;
		if (iterVset.hasNext())
		{
			startV = iterVset.next();
		}

		while (!edgeSet.isEmpty())
		{
			V currentV = startV;
			// wähle nächsten Knoten (Kante darf keine SchnittKante sein)
			Set<E> currentEdgeSet = newgraph.edgesOf(currentV);
			Iterator<E> itCurrentEdgeSet = currentEdgeSet.iterator();
			while (itCurrentEdgeSet.hasNext())
			{
				E currentEdge = itCurrentEdgeSet.next();
				V sourceOfCurrentVertex = newgraph.getEdgeSource(currentEdge);
				V targetOfCurrentVertex = newgraph.getEdgeTarget(currentEdge);
				if (!isACuttingEdge(newgraph, currentEdge, sourceOfCurrentVertex, targetOfCurrentVertex))
				{
					// add Edge to resultcircle
					eulerianCircuit.add(currentEdge);
					newgraph.removeEdge(sourceOfCurrentVertex, targetOfCurrentVertex);
					break;
				}
			}

			// wenn G keine Kanten mehr enthält ende

			// wenn endpunkt = startpunkt dann eulerkreis

			// aktualisiere edgeset
			edgeSet = newgraph.edgeSet();
		}

	}

	private boolean isACuttingEdge(UndirectedGraph<V, E> newgraph, E currentEdge, V sourceOfCurrentVertex, V targetOfCurrentVertex)
	{

		newgraph.removeEdge(currentEdge);
		DijkstraShortestPath<V, E> dijkstra = new DijkstraShortestPath<V, E>(newgraph, sourceOfCurrentVertex, targetOfCurrentVertex);
		// wenn kein Weg gefunden dann schnittkante
		if (dijkstra.getPathEdgeList() != null)
		{
			newgraph.addEdge(sourceOfCurrentVertex, targetOfCurrentVertex);
			return false;
		}
		else
		{
			newgraph.addEdge(sourceOfCurrentVertex, targetOfCurrentVertex);
			return true;
		}
	}

	/**
	 * returns the eulerian path
	 * 
	 * @return List of Edges - The eulerian path
	 */
	public List<E> getEulerianCircuit()
	{
		return eulerianCircuit;
	}

	/**
	 * Checks if graph is an eulerian graph
	 * 
	 * @param graph
	 * @return boolean
	 */
	public boolean isEulerian(UndirectedGraph<V, E> graph)
	{
		return EulerianCircuit.isEulerian(graph);
	}

}
