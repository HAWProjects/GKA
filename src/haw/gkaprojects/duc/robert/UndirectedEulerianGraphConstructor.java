package haw.gkaprojects.duc.robert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.Pseudograph;

public class UndirectedEulerianGraphConstructor {

      /**
       * 
       * 
       * @param verticesAmount
       * @param edgesAmount
       * 
       * @return eulerianGraph
       * 
       * @required edgesAmount >= verticesAmount
       * @ensure org.jgrapht.alg.EulerianCircuit.isEulerian(eulerianGraph)
       *
       */
      public static UndirectedGraph<Vertex, CustomEdge> constructGraph(int verticesAmount,
                  int edgesAmount) {

            if (!isArgumentStatisfied(verticesAmount, edgesAmount))
                  throw new IllegalArgumentException(
                              "The amount if edges must be bigger or equal the amount of vertices");

            UndirectedGraph<Vertex, CustomEdge> graph = new Pseudograph<>(CustomEdge.class);

            addVertices(graph, verticesAmount);

            addEdges(graph, verticesAmount, edgesAmount);

            return graph;
      }

      private static void addEdges(UndirectedGraph<Vertex, CustomEdge> graph, int verticesAmount,
                  int edgesAmount) {

            int eAmount = edgesAmount;

            //
            LinkedList<Vertex> oddDegree = new LinkedList<Vertex>();

            //
            LinkedList<Vertex> evenDegree = new LinkedList<Vertex>();

            // if()
            //
            makeGraphConnected(graph, verticesAmount, edgesAmount);

            //
            eAmount = eAmount - graph.edgeSet().size();

            for (Vertex vertex : graph.vertexSet()) {

                  if (graph.degreeOf(vertex) % 2 == 1) {
                        oddDegree.addFirst(vertex);
                  } else {
                        evenDegree.addFirst(vertex);
                  }

            }

            //
            while (!oddDegree.isEmpty()) {

                  Collections.shuffle(oddDegree);

                  Vertex v1 = oddDegree.pollFirst();
                  Vertex v2 = oddDegree.pollFirst();

                  graph.addEdge(v1, v2);

                  evenDegree.addFirst(v1);
                  evenDegree.addFirst(v2);

                  eAmount--;

            }

            while (eAmount > 1) {

                  int k = Math.min(eAmount, verticesAmount);

                  Collections.shuffle(evenDegree);

                  for (int i = 0; i < k / 2; i++) {
                        Vertex v1 = evenDegree.pollFirst();
                        Vertex v2 = evenDegree.pollFirst();

                        graph.addEdge(v1, v2);

                        oddDegree.addFirst(v1);
                        oddDegree.addFirst(v2);

                        eAmount--;
                  }

                  Collections.shuffle(oddDegree);

                  while (!oddDegree.isEmpty()) {
                        Vertex v1 = oddDegree.pollFirst();
                        Vertex v2 = oddDegree.pollFirst();

                        graph.addEdge(v1, v2);

                        evenDegree.addFirst(v1);
                        evenDegree.addFirst(v2);

                        eAmount--;
                  }
            }

            if (eAmount == 1) {
                  Vertex v = evenDegree.getFirst();

                  graph.addEdge(v, v);
                  eAmount--;
            }

      }

      @SuppressWarnings("unchecked")
      private static void makeGraphConnected(UndirectedGraph<Vertex, CustomEdge> graph,
                  int verticesAmount, int edgesAmount) {

            Set<Vertex> setOfVertices = graph.vertexSet();

            if (verticesAmount <= edgesAmount
                        && edgesAmount < (verticesAmount + verticesAmount / 2)) {

                  List<Vertex> listOfVertex = new ArrayList<>(setOfVertices);

                  for (int i = 0; i < listOfVertex.size() - 1; i++) {
                        Vertex v1 = listOfVertex.get(i);
                        Vertex v2 = listOfVertex.get(i + 1);

                        graph.addEdge(v1, v2);
                  }
                  
                 graph.addEdge(listOfVertex.get(listOfVertex.size()-1), listOfVertex.get(0)); 

            } else {

                  List<Vertex> reachable = new ArrayList<Vertex>();
                  List<Vertex> unreached = new ArrayList<Vertex>(setOfVertices);

                  reachable.add(unreached.remove(0));

                  // Make graph connected
                  while (!unreached.isEmpty()) {

                        Vertex v1 = unreached.remove(0);
                        Vertex v2 = reachable.get((int) (Math.random() * reachable.size()));

                        double weight = (Integer.valueOf(v1.getLabel()) + Integer.valueOf(v2
                                    .getLabel())) / 2;

                        CustomEdge edge = graph.addEdge(v1, v2);
                        ((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(edge, weight);

                        reachable.add(v1);
                  }
            }

      }

      private static void addVertices(UndirectedGraph<Vertex, CustomEdge> graph, int verticesAmount) {
            for (int i = 0; i < verticesAmount; i++) {

                  Vertex v = new VertexImpl("" + i + 1);
                  graph.addVertex(v);

            }
      }

      public static boolean isArgumentStatisfied(int verticesAmount, int edgesAmount) {
            return edgesAmount >= verticesAmount;
      }
}