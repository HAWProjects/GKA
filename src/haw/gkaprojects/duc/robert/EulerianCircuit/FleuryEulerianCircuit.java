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

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.Pseudograph;

public class FleuryEulerianCircuit<V, E> {
	private List<E> eulerianCircuit;
	private double totalWeight;

	public FleuryEulerianCircuit(UndirectedGraph<V, E> graph) {
		if (isEulerian(graph)) {
			createEuler(graph);
		}
	}

	public List<E> getEulerianCircuit() {
		return eulerianCircuit;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public boolean isEulerian(UndirectedGraph<V, E> graph) {
		return EulerianCircuit.isEulerian(graph);
	}

	private void createEuler(UndirectedGraph<V, E> graph) {
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
		while (!edgeQueue.isEmpty()) {

			Vertex currentVertex = start;

			Set<?> neighbourEdgeset = (Set<CustomEdge>) graph.edgesOf((V) currentVertex);
			Iterator<CustomEdge> itNeighbor = (Iterator<CustomEdge>) neighbourEdgeset.iterator();

			

				if (neighbourEdgeset.size() >= 2 && !isEdgeABridge(itNeighbor.next(), graph, resultlist)) {

				}else{
					// kante in ergebnis liste einfügen
					
				}

			

			// update edelist
			edgeList = graph.edgeSet();
		}

	}

	private CustomEdge getNextEdge(Set<?> edgeList) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isEdgeABridge(CustomEdge e, UndirectedGraph<V, E> graph, List<CustomEdge> resultlist) {

//		UndirectedGraph<V, E> g2 = (UndirectedGraph<V, E>)graph.clone();
//		g2.removeEdge(e);
//		for (CustomEdge e : resultlist) {
//			g2.removeEdge(e);
//		}
//		SearchResult result = Breadth_First_Search.searchShortestPath((AbstractBaseGraph<String, DefaultEdge>) g2, (String) graph.getEdgeSource(edge),
//				(String) graph.getEdgeTarget(edge));
//		if (result.getPath() != null)
//			return true;
//		return false;

		return false;
	}
}
