package haw.gkaprojects.duc.robert;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.WeightedPseudograph;

public class UndirectedConnectedGraphConstructor {

      public static Graph<Vertex, CustomEdge> constructGraph(int verticesAmount, int edgesAmount ) {
            
            //
            Graph<Vertex, CustomEdge> graph = new WeightedPseudograph<>(CustomEdge.class);
            
            //
            addVertices(graph, verticesAmount);
            
            //
            addEdges(graph, edgesAmount);
            
            
            return graph;
            
      }

      private static void addEdges(Graph<Vertex, CustomEdge> graph, int edgesAmount) {
            
            int eAmount = edgesAmount;
            
            Set<Vertex> setOfVertices = graph.vertexSet();
            
            List<Vertex> unreached = new ArrayList<Vertex>(setOfVertices);
            
            List<Vertex> reachable = new ArrayList<Vertex>();
           
            reachable.add(unreached.remove(0));
            
            // Make graph connected
            while(!unreached.isEmpty()){
                  
                  Vertex v1 = unreached.remove(0);
                  Vertex v2 = reachable.get((int)Math.random()*reachable.size());
                  
                  double weight = (Integer.valueOf(v1.getLabel()) + Integer.valueOf(v2.getLabel()))/2;

                  
                  CustomEdge edge = graph.addEdge(v1, v2);
                  ((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(edge, weight);
                  
                  reachable.add(v1);
                  eAmount--;
                  
            }
            
            // Add the rest of edges
            while (eAmount != 0){
                  
                  Vertex v1 = reachable.get((int)Math.random()*reachable.size());
                  Vertex v2 = reachable.get((int)Math.random()*reachable.size());
                  
//                  double weight = Math.random()*edgesAmount;
                  double weight = (Integer.valueOf(v1.getLabel()) + Integer.valueOf(v2.getLabel()))/2;
                  
                  CustomEdge edge = graph.addEdge(v1, v2);
                  ((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(edge, weight);
                  
                  eAmount--;
            }
            
            
      }

      private static void addVertices(Graph<Vertex, CustomEdge> graph, int verticesAmount) {
            
            for(int i=0; i < verticesAmount; i++) {
                  
                  Vertex v = new VertexImpl(""+i+1);
                  graph.addVertex(v);
                  
            }
      }
      
      
}
