package haw.gkaprojects.duc.robert.SpanningTree;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MinimumSpanningTree;
import org.jgrapht.alg.util.UnionFind;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

public class KrusKalvonDuc implements MinimumSpanningTree<Vertex, CustomEdge> {

      private Set<CustomEdge>           minimumSpanningTreeEdgeSet;
      private double                    totalWeigt;
      private Graph<Vertex, CustomEdge> originalGraph;

      public KrusKalvonDuc(Graph<Vertex, CustomEdge> graph) {
            this.originalGraph = graph;
            minimumSpanningTreeEdgeSet = findMinimumSpanningTreeSet();
            computeTotalweight();

      }

      private void computeTotalweight() {

            if (this.minimumSpanningTreeEdgeSet == null) {
                  totalWeigt = 0;
            }

            for (CustomEdge customEdge : this.minimumSpanningTreeEdgeSet) {
                  totalWeigt += this.originalGraph.getEdgeWeight(customEdge);
            }

      }

      private Set<CustomEdge> findMinimumSpanningTreeSet() {
            
            // 
            Set<CustomEdge> spanningEdgeSet = new HashSet<CustomEdge>(); 
            
            // 
            Set<CustomEdge> setOfEdges = this.originalGraph.edgeSet();
            
            // 
            List<CustomEdge> queue = new LinkedList<CustomEdge>();
            
            for (CustomEdge customEdge : setOfEdges) {
                  queue.add(customEdge);
            }
            
            // 
            queue.sort(new Comparator<CustomEdge>() {

                  @Override
                  public int compare(CustomEdge o1, CustomEdge o2) {
                        if(originalGraph.getEdgeWeight(o1) > originalGraph.getEdgeWeight(o2)){
                              return 1;
                        } else if(originalGraph.getEdgeWeight(o1) < originalGraph.getEdgeWeight(o2)){
                              return -1;
                        }
                        return 0;
                  }
            });
            
            // 
            UnionFind<Vertex> unifind = new UnionFind<Vertex>(this.originalGraph.vertexSet());
            
            //
            
            while(!queue.isEmpty()){
                  
                 CustomEdge exploringEgde = queue.remove(0); 
                 
                 Vertex v1 = this.originalGraph.getEdgeSource(exploringEgde);
                 Vertex v2 = this.originalGraph.getEdgeTarget(exploringEgde);
                 
                 if(!unifind.find(v1).equals(unifind.find(v2))){
                       spanningEdgeSet.add(exploringEgde);
                       unifind.union(v1, v2);
                 }
                
            }
            
            return spanningEdgeSet;
      }

      @Override
      public Set<CustomEdge> getMinimumSpanningTreeEdgeSet() {

            return minimumSpanningTreeEdgeSet;
      }

      @Override
      public double getMinimumSpanningTreeTotalWeight() {

            return totalWeigt;
      }

}
