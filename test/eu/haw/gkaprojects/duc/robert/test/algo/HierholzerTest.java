package eu.haw.gkaprojects.duc.robert.test.algo;

import haw.gkaprojects.duc.robert.UndirectedConnectedGraphConstructor;
import haw.gkaprojects.duc.robert.EulerianCircuit.HierholzerEulerianCircuit;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.List;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.junit.Test;

public class HierholzerTest {
	
	@Test
	public void test() {
		
	}

	@Test
	public void testSmallGraph() {
		int verticesAmount = 20;	
		int edgesAmount = 40;	
		UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedConnectedGraphConstructor.constructGraph(verticesAmount, edgesAmount);
		HierholzerEulerianCircuit<Vertex, CustomEdge> eulerGraph = new HierholzerEulerianCircuit<Vertex, CustomEdge>(graph);
		List<CustomEdge> eulerlist = eulerGraph.getEulerianCircuit();
		
		
//		EulerianCircuit.isEulerian(arg0)
	}
	
	
}
