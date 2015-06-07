package haw.gkaprojects.duc.robert.EulerianCircuit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;

public class HierholzerEulerianCircuit<V, E> {

      private List<E> eulerianCircuit;
      private double  totalWeight;

      public HierholzerEulerianCircuit(UndirectedGraph<V, E> graph) {
      }

      public List<E> getEulerianCircuit() {
            return eulerianCircuit;
      }

      public double getTotalWeight() {
            return totalWeight;
      }

      public static <V,E> boolean isEulerian(UndirectedGraph<V, E> graph) {
            return EulerianCircuit.isEulerian(graph);
      }

     
}
