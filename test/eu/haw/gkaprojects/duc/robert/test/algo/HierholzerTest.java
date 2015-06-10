package eu.haw.gkaprojects.duc.robert.test.algo;

import haw.gkaprojects.duc.robert.UndirectedConnectedGraphConstructor;
import haw.gkaprojects.duc.robert.EulerianCircuit.HierholzerEulerianCircuit;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import static org.junit.Assert.*;

import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.junit.Test;

import eu.haw.gkaprojects.duc.robert.test.CheckCurcuitCorrectness;

public class HierholzerTest {

      @Test
      public void test() {

      }

      @Test
      public void testSmallGraph() {
            int verticesAmount = 20;
            int edgesAmount = 40;
            for (int i = 0; i < 100; i++) {
                 
                  UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedConnectedGraphConstructor
                              .constructGraph(verticesAmount, edgesAmount);

                  HierholzerEulerianCircuit<Vertex, CustomEdge> eulerGraph = new HierholzerEulerianCircuit<Vertex, CustomEdge>(
                              graph);

                  List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();

                  if (HierholzerEulerianCircuit.isEulerian(graph)) {
                        assertTrue(CheckCurcuitCorrectness.isEulerianCircuit(graph, circuit));
                  } else {
                        assertTrue(circuit == null);
                  }
            }
      }
      
      @Test
      public void testRandomizedEulerianGraph() {
           
            int verticesAmount = 20;
            int edgesAmount = 40;
            for (int i = 0; i < 100; i++) {
                 
                  UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedConnectedGraphConstructor
                              .constructGraph(verticesAmount, edgesAmount);

                  HierholzerEulerianCircuit<Vertex, CustomEdge> eulerGraph = new HierholzerEulerianCircuit<Vertex, CustomEdge>(
                              graph);

                  List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();

                  if (HierholzerEulerianCircuit.isEulerian(graph)) {
                        assertTrue(CheckCurcuitCorrectness.isEulerianCircuit(graph, circuit));
                  } else {
                        assertTrue(circuit == null);
                  }
            }
      }

}
