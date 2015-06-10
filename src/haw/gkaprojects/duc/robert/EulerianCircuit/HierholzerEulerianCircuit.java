package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.graph.CustomEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;


public class HierholzerEulerianCircuit<V, E> {

      private List<E>     eulerianCircuit;

      private Graph<V, E> originalGraph;

      public HierholzerEulerianCircuit(Graph<V, E> graph) {
            this.originalGraph = graph;
            this.eulerianCircuit = findEulerianCircuit(graph);
      }

      private List<E> findEulerianCircuit(Graph<V, E> graph) {


            Map<V, Integer> degreesMap = new HashMap<V, Integer>();

            for (V v : graph.vertexSet()) {
                  degreesMap.put(v, ((UndirectedGraph<V, E>) graph).degreeOf(v));
            }


            Set<V> vertexSet = new HashSet<V>(graph.vertexSet());

            Set<E> ignoredEdges = new HashSet<>();

            Iterator<V> iter = vertexSet.iterator();

            V v0 = iter.next();

            V current = v0;

            List<E> cycle = findCircle(graph, current, ignoredEdges);

            updateDegree(degreesMap, cycle, graph);

            while (!isEulerianCircuit(graph, cycle)) {

                  int currentPosition = 0;

                  for (int i = 0; i < cycle.size(); i++) {

                        E e = cycle.get(i);
                        current = findOtherSide(graph, current, e);

                        if (degreesMap.get(current) > 0) {
                              currentPosition = i + 1;
                              break;
                        }
                  }

                  List<E> oneMoreCycle = findCircle(graph, current, ignoredEdges);

                  updateDegree(degreesMap, oneMoreCycle, graph);

                  for (int i = oneMoreCycle.size() - 1; i >= 0; i--) {

                        cycle.add(currentPosition, oneMoreCycle.get(i));

                  }

            }
          

            return cycle;
      }



 
      private void updateDegree(Map<V, Integer> degreesMap, List<E> cycle, Graph<V, E> graph) {

            for (E e : cycle) {
                  V v1 = graph.getEdgeSource(e);
                  V v2 = graph.getEdgeTarget(e);

                  degreesMap.put(v1, degreesMap.get(v1) - 1);
                  degreesMap.put(v2, degreesMap.get(v2) - 1);
            }

      }

      private List<E> findCircle(Graph<V, E> graph, V v0, Set<E> ignoredEdges) {

            List<E> cycle = new ArrayList<>();

            Set<E> incidentalEdges = null;

            V nextVertex = v0;

            //
            do {
                  // Find all incident edges of the next vertex
                  incidentalEdges = new HashSet<>(graph.edgesOf(nextVertex));
                  E nextEdge = null;

                  Iterator<E> iter = incidentalEdges.iterator();
                  // Find next edge, which not yet been used and the vertex on
                  // other side of the edge hasn't been in the circle yet.
                  do {
                        try {
                              nextEdge = iter.next();
                              iter.remove();
                        } catch (Exception e) {
                              System.out.println(incidentalEdges);
                              System.out.println(cycle);
                              for (E edge : cycle) {
                                    ((CustomEdge) edge).setColor("red");
                              }
                              throw new IllegalArgumentException("Shit");
                        }

                  } while (ignoredEdges.contains(nextEdge)
                  /*
                   * || goneThroughVertices.contains(findOtherSide(graph,
                   * nextVertex, nextEdge))
                   */);
                  if (nextEdge == null) {
                        return null;
                  }

                  cycle.add(nextEdge);

                  ignoredEdges.add(nextEdge);

                  // The next vertex is the vertex on the other side of the edge
                  nextVertex = findOtherSide(graph, nextVertex, nextEdge);

                  // goneThroughVertices.add(nextVertex);
            } while (!nextVertex.equals(v0));

            return cycle;
      }

      public List<E> getEulerianCircuit() {
            return eulerianCircuit;
      }

      public static <V, E> boolean isEulerian(UndirectedGraph<V, E> graph) {
            return EulerianCircuit.isEulerian(graph);
      }

      private V findOtherSide(Graph<V, E> graph, V oneSide, E edge) {

            V otherSide = graph.getEdgeSource(edge).equals(oneSide) ? graph.getEdgeTarget(edge)
                        : graph.getEdgeSource(edge);

            return otherSide;
      }

      public static <V, E> boolean isEulerianCircuit(Graph<V, E> graph, List<E> cycle) {

            if (cycle == null)
                  return false;

            Set<V> s = new HashSet<V>();

            if (cycle.size() != graph.edgeSet().size()) {
                  return false;
            }

            for (int i = 0; i < cycle.size(); i++) {
                  E edge = cycle.get(i);

                  V v1 = graph.getEdgeSource(edge);
                  V v2 = graph.getEdgeTarget(edge);

                  if (!s.contains(v1)) {
                        s.add(v1);
                  } else {
                        s.remove(v1);
                  }

                  if (!s.contains(v2)) {
                        s.add(v2);
                  } else {
                        s.remove(v2);
                  }

            }

            return s.isEmpty();

      }
}
