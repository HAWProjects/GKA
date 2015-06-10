package eu.haw.gkaprojects.duc.robert.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;

public class CheckCurcuitCorrectness {
     
      public static <V,E> boolean isEulerianCircuit(UndirectedGraph<V,E> graph, List<E> circuit) {
            
            
            Set<V> s = new HashSet<V>();
            
            if(s.size() != graph.edgeSet().size()){
                  return false;
            }
            
            for(int i=0; i<circuit.size(); i++){
                  E edge = circuit.get(i);
                  
                  V v1 = graph.getEdgeSource(edge);
                  V v2 = graph.getEdgeTarget(edge);
                  
                  if(!circuit.contains(v1)){
                        s.add(v1);
                  }else {
                        s.remove(v1);
                  }
                  
                  if(!circuit.contains(v2)){
                        s.add(v2);
                  }else {
                        s.remove(v2);
                  }
            }
            return s.isEmpty();
            
      }

      

}
