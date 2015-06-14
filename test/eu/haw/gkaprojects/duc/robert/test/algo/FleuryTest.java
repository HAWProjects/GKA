package eu.haw.gkaprojects.duc.robert.test.algo;

import static org.junit.Assert.*;
import haw.gkaprojects.duc.robert.UndirectedEulerianGraphConstructor;
import haw.gkaprojects.duc.robert.EulerianCircuit.EulerUtil;
import haw.gkaprojects.duc.robert.EulerianCircuit.FleuryEulerian;
import haw.gkaprojects.duc.robert.EulerianCircuit.HierholzerEulerianCircuit;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.util.List;
import java.util.Random;

import org.jgrapht.UndirectedGraph;
import org.junit.Test;

public class FleuryTest
{
	
	private int testcount = 200;
	private int minVertexcount = 50;
	
	@Test
	public void testSmallGraph()
	{
		
		int verticesAmount = 15;
		int edgesAmount = 30;
		for(int i = 0; i < testcount; i++)
		{
			
			Random rand = new Random();
			
			do
			{
				
				verticesAmount = rand.nextInt(100) + minVertexcount;
				edgesAmount = rand.nextInt(300);
			}
			while(!UndirectedEulerianGraphConstructor.isArgumentStatisfied(verticesAmount, edgesAmount));
			
			UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedEulerianGraphConstructor.constructGraph(
					verticesAmount, edgesAmount);
			FleuryEulerian<Vertex, CustomEdge> eulerGraph = new FleuryEulerian<Vertex, CustomEdge>(graph);
			List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();
			assertTrue(EulerUtil.isEulerianCircuit(graph, circuit));
			
		}
	}
	
	@Test
	public void testSmallGraphFleuryVSHierholzer()
	{
		
		int verticesAmount = 15;
		int edgesAmount = 30;
		for(int i = 0; i < 100; i++)
		{
			Random rand = new Random();
			
			do
			{
				verticesAmount = rand.nextInt(50) + minVertexcount;
				edgesAmount = rand.nextInt(150);
			}
			while(!UndirectedEulerianGraphConstructor.isArgumentStatisfied(verticesAmount, edgesAmount));
			
			UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedEulerianGraphConstructor.constructGraph(
					verticesAmount, edgesAmount);
			
			FleuryEulerian<Vertex, CustomEdge> eulerGraph = new FleuryEulerian<Vertex, CustomEdge>(graph);
			List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();
			
			HierholzerEulerianCircuit<Vertex, CustomEdge> hhgraph = new HierholzerEulerianCircuit<Vertex, CustomEdge>(graph);
			List<CustomEdge> hhCircuit = hhgraph.getEulerianCircuit();
			
			assertEquals(hhCircuit.size(), circuit.size());
		}
	}
	
	@Test
	public void testRandomizedEulerianGraph()
	{
		int verticesAmount = 0;
		int edgesAmount = 0;
		for(int i = 0; i < 50; i++)
		{
			Random rand = new Random();
			do
			{
				verticesAmount = rand.nextInt(500) + minVertexcount;
				edgesAmount = rand.nextInt(1500);
				
			}
			while(!UndirectedEulerianGraphConstructor.isArgumentStatisfied(verticesAmount, edgesAmount));
			
			UndirectedGraph<Vertex, CustomEdge> graph = (UndirectedGraph<Vertex, CustomEdge>) UndirectedEulerianGraphConstructor.constructGraph(
					verticesAmount, edgesAmount);
			FleuryEulerian<Vertex, CustomEdge> eulerGraph = new FleuryEulerian<Vertex, CustomEdge>(graph);
			List<CustomEdge> circuit = eulerGraph.getEulerianCircuit();
			
			assertTrue(EulerUtil.isEulerianCircuit(graph, circuit));
			
		}
	}
	
}
