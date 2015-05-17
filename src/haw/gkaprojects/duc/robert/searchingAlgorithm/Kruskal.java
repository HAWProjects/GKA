package haw.gkaprojects.duc.robert.searchingAlgorithm;


import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.WeightedPseudograph;

public class Kruskal
{
	Graph<Vertex, CustomEdge> newGraph;
	double weightOfSpanningTree;
	
	public Kruskal(Graph<Vertex, CustomEdge> graph){
		if(graph instanceof WeightedPseudograph){
		
		// Gerüst aus graph erstellen
		createGraphFramework(graph);
		
		// Menge Minimalgerüst ausgeben
		
		//summe Gewicht von Minimalspannbaum
		calculateWeightOFSpanningTree();
		}
	}

	private void calculateWeightOFSpanningTree()
	{
		for(CustomEdge e :newGraph.edgeSet()){
			weightOfSpanningTree += newGraph.getEdgeWeight(e);
		}
	}

	private void createGraphFramework(Graph<Vertex, CustomEdge> graph)
	{
		//Knotenmenge 
		Set<Vertex> vertexSet = graph.vertexSet();
		// Tupellist
		List<CustomEdge> edgeList = new ArrayList<CustomEdge>();
			for(CustomEdge e :graph.edgeSet()){
				edgeList.add(e);
			}	

		
		//graph ohne kanten erzeugen aber mit allen Knoten
		newGraph = new WeightedPseudograph<>(CustomEdge.class);
		for(Vertex v: vertexSet){
			newGraph.addVertex(v);
		}
		//Kantenliste sortieren nach Kante mit kleinstem Gewicht
		Collections.sort(edgeList);
		//StartVertex bestimmen
		Iterator<Vertex> it = vertexSet.iterator();
		Vertex vStart = it.next();
		// Kanten hinzufügen, mit dem kleinsten Gewicht anfangen
		for(CustomEdge e : edgeList){
			Vertex vSource = newGraph.getEdgeSource(e);
			Vertex vTarget = newGraph.getEdgeTarget(e);
			
			//Wenn null zurückgegeben wird, also kein Pfad vorahnden füge Kante in Graphen
			if(DijkstraShortestPath.findPathBetween(newGraph,vStart, vTarget) == null){
				newGraph.addEdge(vSource, vTarget);
				//kantengewicht hinzufügen
				if(newGraph instanceof WeightedGraph){
					((WeightedGraph) newGraph).setEdgeWeight(newGraph.getEdge(vSource, vTarget), graph.getEdgeWeight(graph.getEdge(vSource, vTarget)));
				}
			}
		}
	}
	
	public Graph<Vertex, CustomEdge> getSpanningTree(){
		return newGraph;
	}
	
	public double getWeightOfSpanningTree(){
		return weightOfSpanningTree;
	}
	
}
