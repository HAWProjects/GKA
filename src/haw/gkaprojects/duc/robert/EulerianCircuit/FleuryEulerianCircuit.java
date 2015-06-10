package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;




import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;

public class FleuryEulerianCircuit<V, E> {
      private List<E> eulerianCircuit;
      private double  totalWeight;

      public FleuryEulerianCircuit(UndirectedGraph<V, E> graph) {
    	  createEuler(graph);
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
      
      private void createEuler(UndirectedGraph<V, E> graph)
	{
    	// all Vertex of the original graph
  		Set<Vertex> vertexSet = (Set<Vertex>) graph.vertexSet();
  		// EdgeSet 
		Set<?> edgeList = graph.edgeSet();
		//EdgeQueue
//		PriorityQueue<?> edgeQueue = new PriorityQueue<>(edgeList);
		
		//Startpunkt
		Iterator<Vertex> itV = vertexSet.iterator();
		Vertex start = itV.next();
		
				
				
				
			List<CustomEdge> resultlist = new ArrayList();
			
			while(!edgeList.isEmpty()){
				
				CustomEdge e = getNextEdge(edgeList);
				if(isEdgeABridge( e, graph )){
					
				}
				
			}
		
		
	}



	private CustomEdge getNextEdge(Set<?> edgeList)
	{
		// TODO Auto-generated method stub
		return null;
	}



	private boolean isEdgeABridge(CustomEdge e, UndirectedGraph<V,E> graph)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
