package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.GraphFileSaver;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;


public class EulerUtil {
     
      public static <V,E> boolean isEulerianCircuit(Graph<V, E> graph, List<E> cycle) {
           
            
            if(cycle == null) return false;
            
            Set<V> s = new HashSet<V>();
            
            Set<E> edgeSet = new HashSet<E>(cycle);

            
            if(edgeSet.size() != graph.edgeSet().size()){
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
                 
                  
                  if(s.size() > 2) {
                        return false;
                  }
            }
            
            
            return s.isEmpty();
            
      }

      public static <V, E> boolean isEulerianGraph(UndirectedGraph<V, E> graph) {
            return EulerianCircuit.isEulerian(graph);
      } 

}
