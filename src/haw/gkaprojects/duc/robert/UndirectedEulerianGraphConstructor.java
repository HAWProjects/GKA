package haw.gkaprojects.duc.robert;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import org.jgrapht.UndirectedGraph;

public class UndirectedEulerianGraphConstructor {
      
      /**
       * 
       * 
       * @param verticesAmount
       * @param edgesAmount
       * 
       * @return eulerianGraph
       * 
       * @required edgesAmount >= 2(verticesAmount -1) && edgesAmount%2 == 0
       * @ensure org.jgrapht.alg.EulerianCircuit.isEulerian(eulerianGraph)
       *
       */
      public static UndirectedGraph<Vertex, CustomEdge> constructGraph(int verticesAmount, int edgesAmount) {
           return  null;
      }
      
      public static boolean isArgumentStatisfied(int verticesAmount, int edgesAmount) {
            return edgesAmount >= 2*(verticesAmount -1) && edgesAmount%2 == 0;
      }
}