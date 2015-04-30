package haw.gkaprojects.duc.robert.searchingAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import haw.gkaprojects.duc.robert.ErrorPopUp;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import org.jgrapht.Graph;

/**
 * This class finds the shortest way between two given Vertex in a given Graph.
 * Based on the Dijkstra Algorithmus.
 * 
 * @author Robert
 *
 */
public class OwnDijkstra
{

	private ArrayList<CustomEdge> shortestWayEdgeList;
	private LinkedList<Vertex> shortestWayVertexList;

	private HashMap<Vertex, Attribut> visitMap;
	
	private double shortestPathLengh;

	public OwnDijkstra(Graph<Vertex, CustomEdge> graph, Vertex start,
			Vertex target)
	{
		shortestPathLengh = 0.0;
		shortestWayEdgeList = new ArrayList<>();
		shortestWayVertexList = new LinkedList<>();

		visitMap = new HashMap<>();

		if (graph.containsVertex(start) && graph.containsVertex(target)
				&& isEdgeWeightPositiv(graph))
		{
			setInitSearch(graph, start);
			searchForShortestPath(graph, start, target);

		}
		else
		{
//			System.out.println("Kann keinen Pfad finden weil: Kantengewicht negativ || Vertex nicht vorhanden || Kein Weg vorhanden");
			 new ErrorPopUp("Kann keinen Pfad finden weil: Kantengewicht negativ || Vertex nicht vorhanden || Kein Weg vorhanden");
			throw new IllegalArgumentException(
					"Kann keinen Pfad finden weil: Kantengewicht negativ || Vertex nicht vorhanden || Kein Weg vorhanden");
		}
	}

	/**
	 * 
	 * @param graph
	 * @param start
	 * @param target
	 */
	private void searchForShortestPath(Graph<Vertex, CustomEdge> graph,
			Vertex source, Vertex target)
	{

		// while not all Vertex visit and Vertex-Status false
		while (!allVertexExplored())
		{

			if (source.equals(target))
			{
				shortestWayVertexList.add(source);
			}
			else
			{
				// finde unter allen Knoten mit status false den mit kleinster
				// entfernung
				Vertex currentVertex = searchForShortestDistance(graph);

				// setze ok auf true
				visitMap.get(currentVertex)._ok = true;

				// für alle Knoten v_j mit ok = false für die ne Kante existiert
				// von Vh nach Vj
				HashSet<Vertex> neighboursSet = new HashSet<>();
				for (CustomEdge e : graph.edgesOf(currentVertex))
				{ // all kanten an knoten
					if (visitMap.get(graph.getEdgeTarget(e))._ok == false){
						//neighboursSet.add(graph.getEdgeTarget(e));
						
						// falls entf_j größer entf_h + kantengewicht von h nach j
						if(visitMap.get(graph.getEdgeTarget(e))._distance > (visitMap.get(currentVertex)._distance + graph.getEdgeWeight(e))){
							// dann entf_j = entf_h + kantengewicht h nach j
							visitMap.get(graph.getEdgeTarget(e))._distance = visitMap.get(currentVertex)._distance + graph.getEdgeWeight(e);
							// setze vorgänger_j = v_h
							visitMap.get(graph.getEdgeTarget(e))._sucsessor = currentVertex;
						}
					}
				}
			}
		}
		createShortestPathList(graph,source,target);
		shortestPathLengh = visitMap.get(target)._distance;
		//TODO  liste des kürzesten Weges erstellen

	}
	
	/*
	 * creates the List of the shortest path based on the visitMap
	 */
	private void createShortestPathList(Graph<Vertex, CustomEdge> graph,Vertex source, Vertex target)
	{
		// TODO Auto-generated method stub
		Vertex currentVertex = target;
		shortestWayVertexList.addFirst(target);
		while(!currentVertex.equals(source)){
		shortestWayVertexList.addFirst(visitMap.get(currentVertex)._sucsessor);
		shortestWayEdgeList.add(graph.getEdge(visitMap.get(currentVertex)._sucsessor, currentVertex));
		currentVertex = visitMap.get(currentVertex)._sucsessor;
		}
	}

	/*
	 * returns the Vertex with the shortest distance and Attribut-Status false
	 * @param graph
	 * @return Vertex
	 */
	private Vertex searchForShortestDistance(Graph<Vertex, CustomEdge> graph)
	{
		// TODO Auto-generated method stub
		Double min = Double.MAX_VALUE;
		Vertex currentVertex = null;
		for (Vertex v : graph.vertexSet())
		{
			if (visitMap.get(v)._ok == false && visitMap.get(v)._distance < min)
			{
				currentVertex = v;
				min = visitMap.get(v)._distance;
			}
		}
		return currentVertex;
	}

	/*
	 * checks if all Vertex are visit and id the status of all Vertex is true
	 * 
	 * @return Bool
	 */
	private boolean allVertexExplored()
	{
		for (Attribut a : visitMap.values())
		{
			if (a._ok == false)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param graph
	 * @param start
	 */
	private void setInitSearch(Graph<Vertex, CustomEdge> graph, Vertex start)
	{
		for (Vertex v : graph.vertexSet())
		{
			if (!v.equals(start))
			{
				visitMap.put(v, new Attribut());
			}
			else
			{
				visitMap.put(v, new Attribut(v, 0.0, false));
			}
		}
	}

	/*
	 * checks if edgeweight positiv returns boolean
	 */
	private boolean isEdgeWeightPositiv(Graph<Vertex, CustomEdge> graph)
	{
		for (CustomEdge e : graph.edgeSet())
		{

			if (graph.getEdgeWeight(e) < 0)
			{
				return false;
			}
		}
		return true;
	}

	private class Attribut
	{
		Double _distance;
		Vertex _sucsessor;
		boolean _ok;

		private Attribut()
		{
			this(null, Double.MAX_VALUE, false);
		}

		private Attribut(Vertex sucsessor, double distance, boolean ok)
		{
			this._sucsessor = sucsessor;
			this._distance = distance;
			this._ok = ok;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<Vertex> getShortestPathVertexList(){
		return shortestWayVertexList;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<CustomEdge> getShortestPathEdgeList(){
		return shortestWayEdgeList;
	}
	/**
	 * 
	 * @return
	 */
	 public double getShortestLength(){
		 return shortestPathLengh;
	 }
}
