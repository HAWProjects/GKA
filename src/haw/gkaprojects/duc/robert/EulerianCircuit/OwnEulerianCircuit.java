package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;

public class OwnEulerianCircuit<V,E> {
      
      private List<V> eulerianCircuit;
      private double totalWeight;
      
      public OwnEulerianCircuit(UndirectedGraph<V, E> graph) {
      }

      public List<V> getEulerianCircuit() {
            return eulerianCircuit;
      }


      public double getTotalWeight() {
            return totalWeight;
      }
      
      public boolean isEulerian(UndirectedGraph<V, E> graph) {
            return EulerianCircuit.isEulerian(graph);
      }
      
}
