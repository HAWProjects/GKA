package haw.gkaprojects.duc.robert.EulerianCircuit;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
//import haw.gkaprojects.duc.robert.graph.Vertex;
//import haw.gkaprojects.duc.robert.GraphFileSaver;

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
            
            if (!isEulerianGraph((UndirectedGraph<V, E>) graph)) {
                  Map<V, Integer> degreesMap = new HashMap<V, Integer>();
                  
                  for (V v : graph.vertexSet()) {
                        degreesMap.put(v, ((UndirectedGraph<V, E>) graph).degreeOf(v));
                  }
                  
                  System.out.println(degreesMap);
                  
                  throw new IllegalArgumentException("Graph must be an eulerian graph!");
            }
            
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

            List<E> circuit = findAnCircuit(graph, current, ignoredEdges);

            updateDegree(degreesMap, circuit, graph);

            while (!isEulerianCircuit(graph, circuit)) {

                  int currentPosition = 0;

                  current = v0;
                  for (int i = 0; i < circuit.size(); i++) {
                        
                        if (degreesMap.get(current) > 0) {
                              
                              break;
                        }
                        
                        E e = circuit.get(i);
                        current = findOtherSide(graph, current, e);
                        currentPosition = i+1;
                       
                  }

                  List<E> oneMoreCycle = findAnCircuit(graph, current, ignoredEdges);

                  updateDegree(degreesMap, oneMoreCycle, graph);

                  for (int i = oneMoreCycle.size() - 1; i >= 0; i--) {

                        circuit.add(currentPosition, oneMoreCycle.get(i));

                  }

            }

            return circuit;
      }

      private void updateDegree(Map<V, Integer> degreesMap, List<E> circuit, Graph<V, E> graph) {

            for (E e : circuit) {
                  V v1 = graph.getEdgeSource(e);
                  V v2 = graph.getEdgeTarget(e);

                  degreesMap.put(v1, degreesMap.get(v1) - 1);
                  degreesMap.put(v2, degreesMap.get(v2) - 1);
            }

      }

      private List<E> findAnCircuit(Graph<V, E> graph, V v0, Set<E> ignoredEdges) {

            List<E> circuit = new ArrayList<>();

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
                              System.out.println(circuit);
                              for (E edge : circuit) {
                                    ((CustomEdge) edge).setColor("red");
                              }
                              Map<V, Integer> degreesMap = new HashMap<V, Integer>();
                              
                              for (V v : graph.vertexSet()) {
                                    degreesMap.put(v, ((UndirectedGraph<V, E>) graph).degreeOf(v));
                              }
                              throw new IllegalArgumentException("There's no more edge to go!!!");
                        }

                  } while (ignoredEdges.contains(nextEdge));
                  
                  
                  if (nextEdge == null) {
                        return null;
                  }

                  circuit.add(nextEdge);

                  ignoredEdges.add(nextEdge);

                  // The next vertex is the vertex on the other side of the edge
                  nextVertex = findOtherSide(graph, nextVertex, nextEdge);

            } while (!nextVertex.equals(v0));

            return circuit;
      }

      private V findOtherSide(Graph<V, E> graph, V oneSide, E edge) {

            V otherSide = graph.getEdgeSource(edge).equals(oneSide) ? graph.getEdgeTarget(edge)
                        : graph.getEdgeSource(edge);

            return otherSide;
      }

      public Graph<V, E> getOriginalGraph() {
            return originalGraph;
      }

      public List<E> getEulerianCircuit() {
            return this.eulerianCircuit;
      }

      private static <V, E> boolean isEulerianGraph(UndirectedGraph<V, E> graph) {
            return EulerianCircuit.isEulerian(graph);
      }

      private static <V, E> boolean isEulerianCircuit(Graph<V, E> graph, List<E> cycle) {

           return EulerUtil.isEulerianCircuit(graph, cycle); 

      }
}
