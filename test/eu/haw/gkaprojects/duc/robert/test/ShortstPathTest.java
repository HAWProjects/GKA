package eu.haw.gkaprojects.duc.robert.test;

import static org.junit.Assert.*;
import haw.gkaprojects.duc.robert.BreadthFirstSearch;
import haw.gkaprojects.duc.robert.CustomEdge;
import haw.gkaprojects.duc.robert.GraphReader;
import haw.gkaprojects.duc.robert.Vertex;
import haw.gkaprojects.duc.robert.VertexImpl;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;


public class ShortstPathTest {
	
	private static final String PATH1 = "../GKAProjects_01/res/files/bspGraphen/bsp1.graph";
	private static final String PATH2 = "../GKAProjects_01/res/files/bspGraphen/bsp2.graph";
	private static final String PATH3 = "../GKAProjects_01/res/files/bspGraphen/bsp3.graph";
	private static final String PATH4 = "../GKAProjects_01/res/files/bspGraphen/bsp4.graph";
	private static final String PATH5 = "../GKAProjects_01/res/files/bspGraphen/bsp5.graph";
	private static final String PATH6 = "../GKAProjects_01/res/files/bspGraphen/bsp6.graph";
	
	private Graph<Vertex, CustomEdge> _graph3;
	private Graph<Vertex, CustomEdge> _testGraph1;
	private Graph<Vertex, CustomEdge> _testGraph2;
	private Graph<Vertex, CustomEdge> _testGraph3;
	private Graph<Vertex, CustomEdge> _testGraph4;
	
	@Before
	public void setUp()
	{
		GraphReader reader = new GraphReader(PATH3);
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
		 List<CustomEdge> list = BreadthFirstSearch.searchForTheShortestPath(_graph3, new VertexImpl("Uelzen"), new VertexImpl("Detmold"));
		 List<CustomEdge> listb = BreadthFirstSearch.searchForTheShortestPath(_testGraph1, new VertexImpl("Uelzen"), new VertexImpl("Detmold"));
		 assertArrayEquals(listb.toArray() ,list.toArray());
	}
	
	@Test
	public void testFindShortestPath1()
	{
		GraphReader reader = new GraphReader(PATH1);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(_testGraph2, new VertexImpl("h"), new VertexImpl("g"));
		List<CustomEdge> listB = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("h"), new VertexImpl("g"));
		assertArrayEquals(listA.toArray() ,listB.toArray());
	}
	
	@Test
	public void testFindShortestPath2()
	{
		GraphReader reader = new GraphReader(PATH2);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("c"));
		List<CustomEdge> listB = BreadthFirstSearch.searchForTheShortestPath(_testGraph4, new VertexImpl("a"), new VertexImpl("c"));
		assertArrayEquals(listA.toArray() ,listB.toArray());
	}
	
	@Test
	public void testFindShortestPath3()
	{
		GraphReader reader = new GraphReader(PATH3);
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
		
		
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		List<CustomEdge> listB = BreadthFirstSearch.searchForTheShortestPath(testGraph, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		assertArrayEquals(listA.toArray() ,listB.toArray());
	}
	
	@Test
	public void testFindShortestPath6()
	{
		GraphReader reader = new GraphReader(PATH6);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(_testGraph3, new VertexImpl("12"), new VertexImpl("12"));
		List<CustomEdge> listB = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("12"), new VertexImpl("12"));
		assertArrayEquals(listA.toArray(), listB.toArray());
	}
	
	@Test
	public void testSteps1(){
		GraphReader reader = new GraphReader(PATH1);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("e"));
		
		assertEquals(3, listA.size());
		assertEquals(3, BreadthFirstSearch.getCountStepsShortestWay());
	}
	
	@Test
	public void testSteps2()
	{
		GraphReader reader = new GraphReader(PATH2);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("d"));
		assertEquals(1, listA.size());
	}
	
	@Test
	public void testSteps3()
	{
		BreadthFirstSearch.searchForTheShortestPath(_graph3, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		assertEquals(4, BreadthFirstSearch.getCountStepsShortestWay());
	}
	
	@Test
	public void testSteps5()
	{
		GraphReader reader = new GraphReader(PATH5);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("b"), new VertexImpl("f"));
		assertEquals(5, BreadthFirstSearch.getCountStepsShortestWay());
	}
	
	
	@Test // Dijkstra 
	public void testShortestWayDijkstra3()
	{	
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(_graph3, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(_graph3, new VertexImpl("Bremerhaven"), new VertexImpl("Detmold"));		
		assertEquals(listA, testgraph.getPathEdgeList());
	}
	
	
	@Test // 
	public void testShortestWayDijkstra5(){
		GraphReader reader = new GraphReader(PATH5);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("b"), new VertexImpl("g"));
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("b"), new VertexImpl("g"));		
		assertEquals(listA, testgraph.getPathEdgeList());
	}
	
	@Test // 
	public void testShortestWayDijkstra6(){
		GraphReader reader = new GraphReader(PATH6);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("1"), new VertexImpl("8"));
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("1"), new VertexImpl("8"));		
		assertEquals(listA, testgraph.getPathEdgeList());
	}
	
	@Test // 
	public void testShortestWayDijkstra1(){
		GraphReader reader = new GraphReader(PATH1);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("a"), new VertexImpl("i"));
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("i"));		
		assertEquals(listA, testgraph.getPathEdgeList());
	}
	@Test // 
	public void testShortestWayDijkstra4(){
		GraphReader reader = new GraphReader(PATH4);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("a"), new VertexImpl("i"));
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("a"), new VertexImpl("i"));		
		assertEquals(listA, testgraph.getPathEdgeList());
	}
	
	@Test // 
	public void testShortestWayDijkstra2(){
		GraphReader reader = new GraphReader(PATH2);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		DijkstraShortestPath<Vertex, CustomEdge> testgraph = new DijkstraShortestPath<Vertex, CustomEdge>(graph, new VertexImpl("b"), new VertexImpl("i"));
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("b"), new VertexImpl("i"));		
		assertEquals(listA, testgraph.getPathEdgeList());
	}
}
