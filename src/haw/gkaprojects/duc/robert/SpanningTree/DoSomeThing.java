package haw.gkaprojects.duc.robert.SpanningTree;

import haw.gkaprojects.duc.robert.UndirectedConnectedGraphConstructor;
import haw.gkaprojects.duc.robert.SpanningTree.OwnPrimMinimumSpanningTree.DataStructure;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import org.jgrapht.Graph;
import org.jgrapht.alg.PrimMinimumSpanningTree;

public class DoSomeThing {
	public static void main(String[] args) throws IllegalAccessException {
		
		Graph<Vertex, CustomEdge> graph = UndirectedConnectedGraphConstructor.constructGraph(30000, 50000);
		
		long start = System.currentTimeMillis();
		OwnPrimMinimumSpanningTree prim = new OwnPrimMinimumSpanningTree(graph, DataStructure.PRIORITYQUEUE);
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		System.out.println(prim.getTotalWeight());
		
		start = System.currentTimeMillis();
		double erg = (new PrimMinimumSpanningTree<>(graph).getMinimumSpanningTreeTotalWeight());
		end = System.currentTimeMillis();
		System.out.println(erg);
		System.out.println(end - start);
	}
}
