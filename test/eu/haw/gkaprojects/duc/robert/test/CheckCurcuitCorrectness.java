package eu.haw.gkaprojects.duc.robert.test;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;

public class CheckCurcuitCorrectness {
     
      public  boolean isEulerCircuit(UndirectedGraph<Vertex, CustomEdge> graph, List<CustomEdge> circuit) {
            
            
            Set<CustomEdge> circuitSet = new HashSet<CustomEdge>(circuit);
            
            if(circuitSet.size() != graph.edgeSet().size()){
                  return false;
            }
            
            if (circuit.size() == 1) {
                  return true;
            }
            
            Vertex  start = findStart(graph, circuit);
            Vertex end   = findEnd(graph, circuit);
            
//            if(iSstartAndEndVertexMatch())
            
            
            return false;
            
      }

      private Vertex findEnd(UndirectedGraph<Vertex, CustomEdge> graph, List<CustomEdge> circuit) {
            
            CustomEdge first = circuit.get(0);
            CustomEdge second = circuit.get(1);
            
            List<Vertex> l = new ArrayList<Vertex>();
            
            l.add(graph.getEdgeSource(first));
            l.add(graph.getEdgeTarget(first));
            
            l.remove(graph.getEdgeSource(second));
            l.remove(graph.getEdgeTarget(second));
            
            Vertex start = l.get(0);
            
            return start;
      }

      private Vertex findStart(UndirectedGraph<Vertex, CustomEdge> graph, List<CustomEdge> circuit) {
            // TODO Auto-generated method stub
            return null;
      }

}
