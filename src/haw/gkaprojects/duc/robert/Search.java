package haw.gkaprojects.duc.robert;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

public class Search
{

	
	public static List getSortestPath(Graph<Vertex, CustomEdge> graph, VertexImpl startVertex, VertexImpl endVertex){
		List<Vertex> list = new ArrayList();
		Set<CustomEdge> set = new HashSet();
		
		if(graph.containsVertex(startVertex) && graph.containsVertex(endVertex)){
			set.addAll(graph.edgesOf(startVertex));
			
			for(int i = 0; i < set.size();++i){
//				graph.getEdgeTarget(set.toArray()[i]);
				
			
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
		return list;
	}
}
