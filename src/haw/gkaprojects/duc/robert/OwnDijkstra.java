package haw.gkaprojects.duc.robert;

import java.util.ArrayList;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import org.jgrapht.Graph;

/**
 * This class finds the shortest way between two Vertex. Based on the Dijkstra Algorithmus.
 * @author Robert
 *
 */
public class OwnDijkstra
{
	
	private ArrayList<CustomEdge> shortestWayEdgeList;
	private ArrayList<Vertex> shortestWayVertexList;
	public OwnDijkstra(Graph<Vertex, CustomEdge> graph, Vertex start,
			Vertex target){
		
		shortestWayEdgeList = new ArrayList<>();
		shortestWayVertexList = new ArrayList<>();
		
		if(graph.containsVertex(start) && graph.containsVertex(target)){
			
		}else{
			
		}
		
	}
}
