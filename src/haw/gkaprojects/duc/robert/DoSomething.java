package haw.gkaprojects.duc.robert;


import haw.gkaprojects.duc.robert.EulerianCircuit.EulerUtil;
import haw.gkaprojects.duc.robert.EulerianCircuit.FleuryEulerian;
import haw.gkaprojects.duc.robert.EulerianCircuit.HierholzerEulerianCircuit;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.Pseudograph;



public class DoSomething {
      public static void main(String[] args) throws Exception {
            
          
          Graph<Vertex, CustomEdge> g = new Pseudograph<>(CustomEdge.class);
          Vertex v1 = new VertexImpl("1");
          g.addVertex(v1);
          Vertex v2 = new VertexImpl("2");
          g.addVertex(v2);
          
          System.out.println((new DijkstraShortestPath<Vertex, CustomEdge>(g, v1, v2)).getPath());
            
          UndirectedGraph<Vertex, CustomEdge> graph = UndirectedEulerianGraphConstructor.constructGraph(150 , 600);
//          System.out.println(EulerianCircuit.isEulerian(graph));
//          System.out.println(graph.vertexSet().size()+ "  " + graph.edgeSet().size());
//          
//          Vertex v0 = graph.vertexSet().iterator().next();
//          
//          Graph<Vertex, CustomEdge> graph = (new GraphMaker("/Users/DucNguyenMinh/git/GKA_/res/files/bspGraphen/failedGraph.graph")).getGraph();
          Set<CustomEdge> ignoredEdges = new HashSet<>();
          Map<Vertex, Integer> degreesMap = new HashMap<Vertex, Integer>();
          
          
          FleuryEulerian<Vertex, CustomEdge> cir = new FleuryEulerian<>(graph);
          List<CustomEdge> cirle = cir.getEulerianCircuit();
        
          
          for (CustomEdge customEdge : cirle) {
                customEdge.setColor("red");
          }
          
          boolean isEurlerCircuit = EulerUtil.isEulerianCircuit(graph, cirle);
          System.out.println(isEurlerCircuit);
//          GraphVisualiser.exportGraphToDotFile(graph);
      }
     
      
      public static <V,E> boolean isEulerianCircuit(Graph<V, E> graph, List<E> cycle) {
            
            if(cycle == null) return false;
            
            Set<V> s = new HashSet<V>();
            
            if(cycle.size() != graph.edgeSet().size()){
                  return false;
            }
            
            for(int i=0; i<cycle.size(); i++){
                  E edge = cycle.get(i);
                  
                  V v1 = graph.getEdgeSource(edge);
                  V v2 = graph.getEdgeTarget(edge);
                  
                  if(!s.contains(v1)){
                        s.add(v1);
                  }else {
                        s.remove(v1);
                  }
                  
                  if(!s.contains(v2)){
                        s.add(v2);
                  }else {
                        s.remove(v2);
                  }
                
                  if (s.size()>2) {
                        return false;
                  }
            }
            
            
            return s.isEmpty();
            
      }
      
      private static <V,E> List<E> findCircle(Graph<V, E> graph, V v0, Set<E> ignoredEdges) {

            List<E> cycle = new ArrayList<>();

            Set<E> incidentalEdges = null;


            V nextVertex = v0;
           
            // 
            do {
                  // Find all incident edges of the next vertex
                  incidentalEdges = new HashSet<>(graph.edgesOf(nextVertex));
                  E nextEdge = null;
                  
                  Iterator<E> iter = incidentalEdges.iterator();
                  // Find next edge, which not yet been used and the vertex on
                  // other side of the edge hasn't been in the circle yet.
                  do {
                        try {
                              nextEdge = iter.next();
                              iter.remove();
                        } catch (Exception e) {
                              System.out.println(incidentalEdges);
                              System.out.println(cycle);
                              for (E edge : cycle) {
                                   ((CustomEdge)edge).setColor("red");
                              }
                              GraphVisualiser.exportGraphToDotFile((Graph<Vertex, CustomEdge>) graph);
                              throw new IllegalArgumentException("Shit");
                        }
                        

                  } while ( ignoredEdges.contains(nextEdge)
                              /*|| goneThroughVertices.contains(findOtherSide(graph, nextVertex,
                                          nextEdge))*/);
                  if(nextEdge == null) { return null;}
                  
                  cycle.add(nextEdge);

                  ignoredEdges.add(nextEdge);

                  
                  // The next vertex is the vertex on the other side of the edge
                  nextVertex = findOtherSide(graph, nextVertex, nextEdge);
                  
//                  goneThroughVertices.add(nextVertex);
                  System.out.println(cycle.size());
            } while (!nextVertex.equals(v0));

            return cycle;
      }
      
      
      private static<V,E> V findOtherSide(Graph<V, E> graph, V oneSide, E edge) {

            V otherSide = graph.getEdgeSource(edge).equals(oneSide) ? graph.getEdgeTarget(edge)
                        : graph.getEdgeSource(edge);

            return otherSide;
      }
}
