package eu.haw.gkaprojects.duc.robert.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import haw.gkaprojects.duc.robert.GraphFileSaver;
import haw.gkaprojects.duc.robert.UndirectedGraphContructor;
import haw.gkaprojects.duc.robert.GraphMaker_withScanner;
import haw.gkaprojects.duc.robert.searchingAlgorithm.AStarShortestPath;
import haw.gkaprojects.duc.robert.searchingAlgorithm.ShortestPathOfDijkstras;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;
import haw.gkaprojects.duc.robert.GraphVisualiser;

public class AlgorithmsTestWithGraphConstructor {

//	private static final String PATH = "../GKA/test/eu/haw/gkaprojects/duc/robert/test/test";
	private static final String PATH = "../testfile.graph";
//	private static final String PATH = "Z:\\git\\GKA\\test\\eu\\haw\\gkaprojects\\duc\\robert\\test\\test";
	public static final double DELTA = 1e-15;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAstarAgainstDijkstraSmall() {
		Random rand = new Random();
		Graph<Vertex, CustomEdge> graph = null;
		for(int i = 0; i < 100; i++){
			int verticesAmount  = rand.nextInt(10) + 10;
			int edgesAmount = rand.nextInt(20) + 20;
//			int verticesAmount = 8;
//			int edgesAmount = 13;
			graph = UndirectedGraphContructor.constructGraph(verticesAmount, edgesAmount, PATH);
			UndirectedGraphContructor.HeuristicGenerator.setHeuristicForGraph(graph, new VertexImpl("7"));
			for (Vertex v : graph.vertexSet()) {
				double shortestPathLength_dijksta1 = (new ShortestPathOfDijkstras(graph, new VertexImpl("1"), new VertexImpl("7"))).getShortestPathLength(); 
				double shortestPathLength_Astar1 = (new AStarShortestPath(graph, new VertexImpl("1"), new VertexImpl("7"))).getShortestPathLength(); 
				GraphFileSaver.saveGraphToFile(PATH, graph);
				GraphVisualiser.exportGraphToDotFile(graph);
				assertEquals(shortestPathLength_dijksta1, shortestPathLength_Astar1,DELTA);
			}
		}
	}
	
	@Test
	public void testAstarAgainstDijkstraMedium() {
		Random rand = new Random();
		Graph<Vertex, CustomEdge> graph = null;
		for(int i = 0; i < 100; i++){
			int verticesAmount  = rand.nextInt(50) + 50;
			int edgesAmount = rand.nextInt(200) + 200;
			graph = UndirectedGraphContructor.constructGraph(verticesAmount, edgesAmount, PATH);
			UndirectedGraphContructor.HeuristicGenerator.setHeuristicForGraph(graph, new VertexImpl("39"));
			double shortestPathLength_dijksta1 = (new ShortestPathOfDijkstras(graph, new VertexImpl("12"), new VertexImpl("39"))).getShortestPathLength(); 
			double shortestPathLength_Astar1 = (new AStarShortestPath(graph, new VertexImpl("12"), new VertexImpl("39"))).getShortestPathLength(); 
			assertEquals(shortestPathLength_dijksta1, shortestPathLength_Astar1,DELTA);
		}
	}

	@Test
	public void testBIGGraph(){
		int verticesAmount = 100;
		int edgesAmount = 6000;
		Graph<Vertex, CustomEdge> graph = UndirectedGraphContructor.constructGraph(verticesAmount, edgesAmount, PATH);
		UndirectedGraphContructor.HeuristicGenerator.setHeuristicForGraph(graph, new VertexImpl("69"));
		GraphFileSaver.saveGraphToFile(PATH, graph);
//		Vertex v = new VertexImpl("12");
		for (Vertex v : graph.vertexSet()) {
			double shortestPathLength_dijksta1 = (new ShortestPathOfDijkstras(graph,v , new VertexImpl("69"))).getShortestPathLength(); 
			double shortestPathLength_Astar1 = (new AStarShortestPath(graph, v, new VertexImpl("69"))).getShortestPathLength(); 
			assertEquals(shortestPathLength_dijksta1, shortestPathLength_Astar1,DELTA);
		}
	}
}
