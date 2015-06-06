package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.List;

public class OwnEulerianCircuit<V,E> {
      
      private List<V> eulerianCircuit;
      private double totalWeight;
      
      public OwnEulerianCircuit() {
      }

      public List<V> getEulerianCircuit() {
            return eulerianCircuit;
      }


      public double getTotalWeight() {
            return totalWeight;
      }

      
}
