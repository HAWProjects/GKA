package eu.haw.gkaprojects.duc.robert.test;

import static org.junit.Assert.*;

import java.util.List;

import org.jgrapht.Graph;

import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

import eu.haw.gkaprojects.duc.robert.BreadthFirstSearch;
import eu.haw.gkaprojects.duc.robert.CustomEdge;
import eu.haw.gkaprojects.duc.robert.GraphReader;
import eu.haw.gkaprojects.duc.robert.Vertex;
import eu.haw.gkaprojects.duc.robert.VertexImpl;


public class ShortstPathTest {
	
	
	private static final String PATH1 = "../GKAProjects_01/res/files/bspGraphen/bsp3.graph";
	private static final String PATH2 = "../GKAProjects_01/res/files/bspGraphen/bsp1.graph";
	private static final String PATH3 = "../GKAProjects_01/res/files/bspGraphen/bsp6.graph";
	
	private Graph<Vertex, CustomEdge> _graph;
	private Graph<Vertex, CustomEdge> _testGraph1;
	private Graph<Vertex, CustomEdge> _testGraph2;
	private Graph<Vertex, CustomEdge> _testGraph3;
	
	@Before
	public void setUp()
	{
		GraphReader reader = new GraphReader(PATH1);
		_graph = reader.getGraph();
		
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
		
	}
	
	@Test
	public void testFindShortestPath1()
	{
		 List<CustomEdge> list = BreadthFirstSearch.searchForTheShortestPath(_graph, new VertexImpl("Uelzen"), new VertexImpl("Detmold"));
		 List<CustomEdge> listb = BreadthFirstSearch.searchForTheShortestPath(_testGraph1, new VertexImpl("Uelzen"), new VertexImpl("Detmold"));
		 assertArrayEquals(listb.toArray() ,list.toArray());
	}
	
	@Test
	public void testFindShortestPath2()
	{
		GraphReader reader = new GraphReader(PATH2);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(_testGraph2, new VertexImpl("h"), new VertexImpl("g"));
		List<CustomEdge> listB = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("h"), new VertexImpl("g"));
		assertArrayEquals(listA.toArray() ,listB.toArray());
	}
	
	@Test
	public void testFindShortestPath3()
	{
		GraphReader reader = new GraphReader(PATH3);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		List<CustomEdge> listA = BreadthFirstSearch.searchForTheShortestPath(_testGraph3, new VertexImpl("12"), new VertexImpl("12"));
		List<CustomEdge> listB = BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("12"), new VertexImpl("12"));
		assertArrayEquals(listA.toArray(), listB.toArray());
	}


}
