package eu.haw.gkaprojects.duc.robert.test.algo;

import haw.gkaprojects.duc.robert.UndirectedEulerianGraphConstructor;
import haw.gkaprojects.duc.robert.EulerianCircuit.EulerUtil;
import haw.gkaprojects.duc.robert.EulerianCircuit.HierholzerEulerianCircuit;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.junit.Test;

public class HierholzerTest {

      private int testcount      = 100;
      private int minVertexcount = 50;

      @Test
      public void testSmallGraph() {
            int verticesAmount = 15;
            int edgesAmount = 30;
            for (int i = 0; i < testcount; i++) {

                  Random rand = new Random();

                  do {

                        verticesAmount = rand.nextInt(100) + minVertexcount;
                        edgesAmount = rand.nextInt(300);

                  } while (!UndirectedEulerianGraphConstructor.isArgumentStatisfied(verticesAmount,
                              edgesAmount));

                  Graph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedEulerianGraphConstructor
                              .constructGraph(verticesAmount, edgesAmount);

                  HierholzerEulerianCircuit<Vertex, CustomEdge> eulerGraph = new HierholzerEulerianCircuit<Vertex, CustomEdge>(
                              graph);

                  List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();

                  assertTrue(EulerUtil.isEulerianCircuit(graph, circuit));
            }
      }

      @Test
      public void testRandomizedEulerianGraph() {

            int verticesAmount = 0;
            int edgesAmount = 0;
            for (int i = 0; i < testcount; i++) {

                  Random rand = new Random();

                  do {

                        verticesAmount = rand.nextInt(10000) + minVertexcount;
                        edgesAmount = rand.nextInt(30000);

                  } while (!UndirectedEulerianGraphConstructor.isArgumentStatisfied(verticesAmount,
                              edgesAmount));

                  UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedEulerianGraphConstructor
                              .constructGraph(verticesAmount, edgesAmount);

                  HierholzerEulerianCircuit<Vertex, CustomEdge> eulerGraph = new HierholzerEulerianCircuit<Vertex, CustomEdge>(
                              graph);

                  List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();

                  if (EulerUtil.isEulerianGraph(graph)) {
                        assertTrue(EulerUtil.isEulerianCircuit(graph, circuit));
                  } else {
                        assertTrue(circuit == null);
                  }
            }
      }
}
