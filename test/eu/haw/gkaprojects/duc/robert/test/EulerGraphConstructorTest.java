package eu.haw.gkaprojects.duc.robert.test;

import static org.junit.Assert.assertTrue;
import haw.gkaprojects.duc.robert.UndirectedEulerianGraphConstructor;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.junit.Test;

public class EulerGraphConstructorTest {
	private int testcount = 500;
	private int minVertexcount = 50;

	@Test
	public void testGraphisEulergraph() {
		int verticesAmount = 0;
		int edgesAmount = 0;
		for (int i = 0; i < testcount; i++) {
			do {
				Random rand = new Random();
				verticesAmount = rand.nextInt(1000)+ minVertexcount;
				edgesAmount = rand.nextInt(3000);
			} while (!UndirectedEulerianGraphConstructor.isArgumentStatisfied(
					verticesAmount, edgesAmount));
			
			Graph<Vertex, CustomEdge> graph = UndirectedEulerianGraphConstructor
					.constructGraph(verticesAmount, edgesAmount);
			assertTrue(EulerianCircuit
					.isEulerian((UndirectedGraph<Vertex, CustomEdge>) graph));
		}
	}

	@Test
	public void testGraphisEulergraph2() {
		int verticesAmount = 0;
		int edgesAmount = 0;
		for (int i = 0; i < testcount; i++) {
			do {
				Random rand = new Random();
				verticesAmount = rand.nextInt(10000) + minVertexcount;
				edgesAmount = rand.nextInt(30000);
			} while (!UndirectedEulerianGraphConstructor.isArgumentStatisfied(
					verticesAmount, edgesAmount));
			
			Graph<Vertex, CustomEdge> graph = UndirectedEulerianGraphConstructor
					.constructGraph(verticesAmount, edgesAmount);
			assertTrue(EulerianCircuit
					.isEulerian((UndirectedGraph<Vertex, CustomEdge>) graph));
		}
	}

}
