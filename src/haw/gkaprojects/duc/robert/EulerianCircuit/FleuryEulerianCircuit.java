package haw.gkaprojects.duc.robert.EulerianCircuit;

import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;

public class FleuryEulerianCircuit<V, E> {
      private List<E> eulerianCircuit;
      private double  totalWeight;

      public FleuryEulerianCircuit(UndirectedGraph<V, E> graph) {
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
}
