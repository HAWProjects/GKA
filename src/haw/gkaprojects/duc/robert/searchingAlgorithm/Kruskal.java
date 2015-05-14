package haw.gkaprojects.duc.robert.searchingAlgorithm;


import java.util.Set;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import org.jgrapht.Graph;

public class Kruskal
{

	
	public Kruskal(Graph<Vertex, CustomEdge> graph){
		// Gerüst aus graph erstellen
		createGraphFramework(graph);
		// minimalgerüst ermitteln
		// Menge Minimalgerüst ausgeben
	}

	private void createGraphFramework(Graph<Vertex, CustomEdge> graph)
	{
		//Knotenmenge 
		Set<Vertex> Vset = graph.vertexSet();
		// Tupellist
		
		
	}
}
