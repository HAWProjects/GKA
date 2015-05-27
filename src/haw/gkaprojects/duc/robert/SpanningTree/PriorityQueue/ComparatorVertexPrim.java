package haw.gkaprojects.duc.robert.SpanningTree.PriorityQueue;

import java.util.Comparator;
import java.util.Map;

import haw.gkaprojects.duc.robert.graph.Vertex;

public class ComparatorVertexPrim implements Comparator<Vertex> {
      // 
      Map<Vertex, Double> vertexEnf;
      
      /**
       * 
       * @param vertexEnf
       */
      public ComparatorVertexPrim(Map<Vertex, Double> vertexEnf) {
            this.vertexEnf = vertexEnf;
      }
      
      
      @Override
      public int compare(Vertex o1, Vertex o2) {
           
            return Double.compare(vertexEnf.get(o1), vertexEnf.get(o2));
            
      }


}
