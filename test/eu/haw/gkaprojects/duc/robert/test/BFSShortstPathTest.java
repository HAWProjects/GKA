package eu.haw.gkaprojects.duc.robert.test;

import static org.junit.Assert.*;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

import haw.gkaprojects.duc.robert.GraphMaker;
import haw.gkaprojects.duc.robert.searchingAlgorithm.BreadthFirstSearch;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;


public class BFSShortstPathTest {
	
	private static final String PATH1 = "../GKA/res/files/bspGraphen/bsp1.graph";
	private static final String PATH2 = "../GKA/res/files/bspGraphen/bsp2.graph";
	private static final String PATH3 = "../GKA/res/files/bspGraphen/bsp3.graph";
	private static final String PATH4 = "../GKA/res/files/bspGraphen/bsp4.graph";
	private static final String PATH5 = "../GKA/res/files/bspGraphen/bsp5.graph";
	private static final String PATH6 = "../GKA/res/files/bspGraphen/bsp6.graph";
	
	private Graph<Vertex, CustomEdge> _graph3;
	private Graph<Vertex, CustomEdge> _testGraph1;
	private Graph<Vertex, CustomEdge> _testGraph2;
	private Graph<Vertex, CustomEdge> _testGraph3;
	private Graph<Vertex, CustomEdge> _testGraph4;
	
	@Before
	public void setUp()
	{
		GraphMaker reader = new GraphMaker(PATH3);
		_graph3 = reader.getGraph();
		
		_testGraph1 = new Pseudograph<>(CustomEdge.class);
		 VertexImpl vertex1 = new VertexImpl("Uelzen");
		 VertexImpl vertex2 = new VertexImpl("Hameln");
		 VertexImpl vertex3 = new VertexImpl("Detmold");
		 _testGraph1.addVertex(vertex1);
		 _testGraph1.addVertex(vertex2);
		 _testGraph1.addVertex(vertex3);
		 _testGraph1.addEdge(vertex1, vertex2);
		 _testGraph1.addEdge(vertex2, vertex3);
		 
		 
		 _testGraph2 = new Pseudograph<>(CustomEdge.class);
		 VertexImpl h = new VertexImpl("h"); 
		 VertexImpl b = new VertexImpl("b"); 
		 VertexImpl k = new VertexImpl("k"); 
		 VertexImpl g = new VertexImpl("g"); 
		 _testGraph2.addVertex(h);
		 _testGraph2.addVertex(b);
		 _testGraph2.addVertex(k);
		 _testGraph2.addVertex(g);
		 _testGraph2.addEdge(h, b);
		 _testGraph2.addEdge(b, k);
		 _testGraph2.addEdge(k, g);
		 
		 _testGraph3 = new Pseudograph<>(CustomEdge.class);
		 VertexImpl vertex12 = new VertexImpl("12"); 
		 _testGraph3.addVertex(vertex12);
		 _testGraph3.addEdge(vertex12, vertex12);
		 
		 
		 _testGraph4 = new Pseudograph<>(CustomEdge.class);
		 VertexImpl g2a = new VertexImpl("a"); 
		 VertexImpl g2c = new VertexImpl("c"); 
		 VertexImpl g2d = new VertexImpl("d"); 
		 _testGraph4.addVertex(g2a);
		 _testGraph4.addVertex(g2c);
		 _testGraph4.addVertex(g2d);
		 _testGraph4.addEdge(g2a, g2c);
		 _testGraph4.addEdge(g2c, g2d);		
	}
	
	@Test
	public void testFindShortestPath()
	{
		 List<Vertex> list = BreadthFirstSearch.searchForTheShortestPath(_graph3, new VertexImpl("Uelzen"), new VertexImpl("Detmold"));
		 List<Vertex> listb = BreadthFirstSearch.searchForTheShortestPath(_testGraph1, new VertexImpl("Uelzen"), new VertexImpl("Detmold"));
		 assertArrayEquals(listb.toArray() ,list.toArray());
	}
	
	@Test
	public void testFindShortestPath1()
	{
		GraphMaker reader = new GraphMaker(PATH1);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(_testGraph2, new VertexImpl("h"), new VertexImpl("g"));
		List<Vertex> listB = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("h"), new VertexImpl("g"));
		assertArrayEquals(listA.toArray() ,listB.toArray());
	}
	
	@Test
	public void testFindShortestPath2()
	{
		GraphMaker reader = new GraphMaker(PATH2);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("c"));
		List<Vertex> listB = BreadthFirstSearch.searchForTheShortestPath(_testGraph4, new VertexImpl("a"), new VertexImpl("c"));
		assertArrayEquals(listA.toArray() ,listB.toArray());
	}
	
	@Test
	public void testFindShortestPath3()
	{
		GraphMaker reader = new GraphMaker(PATH3);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		Graph<Vertex, CustomEdge> testGraph = new Pseudograph<>(CustomEdge.class);
		VertexImpl a = new VertexImpl("Bremerhaven");
		VertexImpl b = new VertexImpl("Soltau");
		VertexImpl c = new VertexImpl("Hameln");
		VertexImpl d = new VertexImpl("Detmold");
		VertexImpl e = new VertexImpl("Rotenburg");
		testGraph.addVertex(a);
		testGraph.addVertex(b);
		testGraph.addVertex(c);
		testGraph.addVertex(d);
		testGraph.addVertex(e);
		testGraph.addEdge(a, e);
		testGraph.addEdge(e, b);
		testGraph.addEdge(c, b);
		testGraph.addEdge(c, d);
		
		
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		List<Vertex> listB = BreadthFirstSearch.searchForTheShortestPath(testGraph, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		assertArrayEquals(listA.toArray() ,listB.toArray());
	}
	
	@Test
	public void testFindShortestPath6()
	{
		GraphMaker reader = new GraphMaker(PATH6);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(_testGraph3, new VertexImpl("12"), new VertexImpl("12"));
		List<Vertex> listB = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("12"), new VertexImpl("12"));
		assertArrayEquals(listA.toArray(), listB.toArray());
	}
	
	@Test
	public void testSteps1(){
		GraphMaker reader = new GraphMaker(PATH1);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("e"));
		
		assertEquals(3, listA.size()-1);
	}
	
	@Test
	public void testSteps2()
	{
		GraphMaker reader = new GraphMaker(PATH2);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("d"));
		assertEquals(1, listA.size()-1);
	}
	
	@Test
	public void testSteps3()
	{
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(_graph3, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		assertEquals(4,listA.size() - 1);
	}
	
	@Test
	public void testSteps5()
	{
		GraphMaker reader = new GraphMaker(PATH5);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("b"), new VertexImpl("f"));
		assertEquals(5, listA.size() - 1);
	}
	
	@Test
	public void testSteps6()
	{
		GraphMaker reader = new GraphMaker(PATH6);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("1"), new VertexImpl("5"));
		assertEquals(2, listA.size() - 1);
		
		List<Vertex> listB = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("1"), new VertexImpl("12"));
		assertEquals(0, listB.size());
	}
	
	
	@Test // Dijkstra 
	public void testShortestWayDijkstra3()
	{	
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(_graph3, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(_graph3, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));		
		assertEquals(BreadthFirstSearch.findShortestPathEdgeList(_graph3, listA), testgraph.getPathEdgeList());
	}
	
	
	@Test // 
	public void testShortestWayDijkstra5(){
		GraphMaker reader = new GraphMaker(PATH5);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("b"), new VertexImpl("g"));
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("b"), new VertexImpl("g"));		
		assertEquals(BreadthFirstSearch.findShortestPathEdgeList(graph, listA), testgraph.getPathEdgeList());
	}
	
	@Test // 
	public void testShortestWayDijkstra6(){
		GraphMaker reader = new GraphMaker(PATH6);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("1"), new VertexImpl("8"));
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("1"), new VertexImpl("8"));		
		assertEquals(BreadthFirstSearch.findShortestPathEdgeList(graph, listA), testgraph.getPathEdgeList());
	}
	
	@Test // 
	public void testShortestWayDijkstra1(){
		GraphMaker reader = new GraphMaker(PATH1);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("a"), new VertexImpl("i"));
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("i"));		
		assertEquals(BreadthFirstSearch.findShortestPathEdgeList(graph, listA), testgraph.getPathEdgeList());
	}
	@Test // 
	public void testShortestWayDijkstra4(){
		GraphMaker reader = new GraphMaker(PATH4);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("a"), new VertexImpl("i"));
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("i"));		
		assertEquals(BreadthFirstSearch.findShortestPathEdgeList(graph, listA), testgraph.getPathEdgeList());
	}
	
	@Test // 
	public void testShortestWayDijkstra2(){
		GraphMaker reader = new GraphMaker(PATH2);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("b"), new VertexImpl("i"));
		List<Vertex> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("b"), new VertexImpl("i"));		
		assertEquals(BreadthFirstSearch.findShortestPathEdgeList(graph, listA), testgraph.getPathEdgeList());
	}
	
}
