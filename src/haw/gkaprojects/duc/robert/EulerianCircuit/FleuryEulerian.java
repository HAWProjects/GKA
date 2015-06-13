package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.GraphVisualiser;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.searchingAlgorithm.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
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
			GraphVisualiser.exportGraphToDotFile((Graph<Vertex, CustomEdge>) newGraph);
			this.eulerianCircuit = new ArrayList<E>();
			createEuler(newGraph);
		}else{
			throw new IllegalArgumentException("Graph ist kein Eulergraph!");
		}
	}

	private void createEuler(UndirectedGraph<V, E> newgraph)
	{
		// bei einem Knoten starten
		Set<E> edgeSet = newgraph.edgeSet();

		// waehle einen kante
		Set<V> vertexSet = newgraph.vertexSet();
		Iterator<V> iterVset = vertexSet.iterator();
		
		V startV = iterVset.hasNext()? iterVset.next() : null;
		
		V currentV = startV;
		
		while (!edgeSet.isEmpty())
		{
			
			// wähle nächsten Knoten (Kante darf keine SchnittKante sein)
			Set<E> currentEdgeSet = newgraph.edgesOf(currentV);
			Iterator<E> itCurrentEdgeSet = currentEdgeSet.iterator();
			while (itCurrentEdgeSet.hasNext())
			{
			     E currentEdge = null;
			    
			     try{
			           currentEdge = itCurrentEdgeSet.next();
			     } catch(Exception e) {
			           currentEdge = (new ArrayList<>(currentEdgeSet)).get(0);
			     }
			     
				V sourceOfCurrentVertex = newgraph.getEdgeSource(currentEdge);
				V targetOfCurrentVertex = newgraph.getEdgeTarget(currentEdge);
				
				if ( currentEdgeSet.size() == 1 || !isACuttingEdge(newgraph, currentEdge, sourceOfCurrentVertex, targetOfCurrentVertex))
				{
					// add Edge to resultcircle
					eulerianCircuit.add(currentEdge);
					currentV  = findOtherSide(newgraph, currentV, currentEdge);
					newgraph.removeEdge(sourceOfCurrentVertex, targetOfCurrentVertex);
					
//					newgraph.removeEdge(currentEdge);
					break;
				}
			}

			// wenn G keine Kanten mehr enthält ende

			// wenn endpunkt = startpunkt dann eulerkreis

			// aktualisiere edgeset
			GraphVisualiser.exportGraphToDotFile((Graph<Vertex, CustomEdge>) newgraph);
			edgeSet = newgraph.edgeSet();
		}

	}

	private V findOtherSide(Graph<V, E> graph, V oneSide, E edge) {

           V otherSide = graph.getEdgeSource(edge).equals(oneSide) ? graph.getEdgeTarget(edge)
                       : graph.getEdgeSource(edge);

           return otherSide;
     }
	
	
	private boolean isACuttingEdge(Graph<V, E> newgraph, E currentEdge, V sourceOfCurrentVertex, V targetOfCurrentVertex)
	{
	      
		newgraph.removeEdge(currentEdge);
		
		@SuppressWarnings("unchecked")
            List<Vertex> path =  BreadthFirstSearch.searchForTheShortestPath((Graph<Vertex, CustomEdge>)newgraph, (Vertex)sourceOfCurrentVertex, (Vertex)targetOfCurrentVertex); ;
	
		newgraph.addEdge(sourceOfCurrentVertex, targetOfCurrentVertex);
		
		return path.isEmpty();
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
