package eu.haw.gkaprojects.duc.robert.test;


import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import haw.gkaprojects.duc.robert.GraphMaker;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

public class ReaderTest {

	
	private static final String PATH_01 = "../GKAProjects_01/res/files/bspGraphen/bsp1.graph";
	private static final String PATH_02 = "../GKAProjects_01/res/files/bspGraphen/bsp2.graph";
	private static final String PATH_03 = "../GKAProjects_01/res/files/bspGraphen/bsp3.graph";
	private static final String PATH_04 = "../GKAProjects_01/res/files/bspGraphen/bsp4.graph";
	private static final String PATH_05 = "../GKAProjects_01/res/files/bspGraphen/bsp5.graph";
	private static final String PATH_06 = "../GKAProjects_01/res/files/bspGraphen/bsp6.graph";
	private static final String PATH_99 = "../GKAProjects_01/res/files/bspGraphen/bsp9999.graph";
	
	private Graph<Vertex, CustomEdge> _graph_01;
	private Graph<Vertex, CustomEdge> _graph_02;
	private Graph<Vertex, CustomEdge> _graph_03;
	private Graph<Vertex, CustomEdge> _graph_04;
	private Graph<Vertex, CustomEdge> _graph_05;
	private Graph<Vertex, CustomEdge> _graph_06;

	@Before
	public void setUp() {
		GraphMaker reader = new GraphMaker(PATH_01);
		_graph_01 = reader.getGraph();
		
		GraphMaker reader2 = new GraphMaker(PATH_02);
		_graph_02 = reader2.getGraph();
		
		GraphMaker reader3 = new GraphMaker(PATH_03);
		_graph_03 = reader3.getGraph();
		
		GraphMaker reader4 = new GraphMaker(PATH_04);
		_graph_04 = reader4.getGraph();
		
		GraphMaker reader5 = new GraphMaker(PATH_05);
		_graph_05 = reader5.getGraph();
		
		GraphMaker reader6 = new GraphMaker(PATH_06);
		_graph_06 = reader6.getGraph();

	}
	
	
	@Test 
	public void testAllVertexExists1()
	{
		assertTrue(_graph_01.containsVertex(new VertexImpl("a")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("b")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("c")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("d")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("e")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("f")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("g")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("h")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("i")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("j")));
		assertTrue(_graph_01.containsVertex(new VertexImpl("k")));
		
		assertEquals(11, (_graph_01.vertexSet().size()));
		
	}
	
	@Test 
	public void testAllVertexExists2()
	{
		assertTrue(_graph_02.containsVertex(new VertexImpl("a")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("b")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("c")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("d")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("e")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("f")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("g")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("h")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("i")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("j")));
		assertTrue(_graph_02.containsVertex(new VertexImpl("k")));
		
		assertEquals(11, (_graph_02.vertexSet().size()));
		
	}
	
	@Test 
	public void testAllVertexExists3()
	{
		assertTrue(_graph_03.containsVertex(new VertexImpl("Oldenburg")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Cuxhaven")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Hannover")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Bremen")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Bremerhaven")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Rotenburg")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Soltau")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Minden")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Uelzen")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Leuneburg")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Buxtehude")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Hameln")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Muenster")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Celle")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Kiel")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Hamburg")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Walsrode")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Paderborn")));
		assertTrue(_graph_03.containsVertex(new VertexImpl("Husum")));
		
		assertEquals(23, (_graph_03.vertexSet().size()));
		
		
		assertFalse(_graph_03.containsVertex(new VertexImpl("Kassel")));		
		assertFalse(_graph_03.containsVertex(new VertexImpl("a")));		
		assertFalse(_graph_03.containsVertex(new VertexImpl("2")));		
	}
	
	@Test 
	public void testAllVertexExists4()
	{
		assertTrue(_graph_04.containsVertex(new VertexImpl("a")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("b")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("c")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("d")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("e")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("f")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("g")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("h")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("i")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("j")));
		assertTrue(_graph_04.containsVertex(new VertexImpl("k")));
		
		assertEquals(11, (_graph_04.vertexSet().size()));
		
	}
	
	@Test 
	public void testAllVertexExists5()
	{
		assertTrue(_graph_05.containsVertex(new VertexImpl("a")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("b")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("c")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("d")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("e")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("f")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("g")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("h")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("i")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("j")));
		assertTrue(_graph_05.containsVertex(new VertexImpl("k")));
		
		assertFalse(_graph_05.containsVertex(new VertexImpl("l")));
		assertFalse(_graph_05.containsVertex(new VertexImpl("m")));
		assertFalse(_graph_05.containsVertex(new VertexImpl("n")));
		assertFalse(_graph_05.containsVertex(new VertexImpl("o")));
		assertFalse(_graph_05.containsVertex(new VertexImpl("Hamburg")));
		assertFalse(_graph_05.containsVertex(new VertexImpl("Walsrode")));		
		assertFalse(_graph_05.containsVertex(new VertexImpl("1")));		
	}
	
	@Test 
	public void testAllVertexExists6()
	{
		assertTrue(_graph_06.containsVertex(new VertexImpl("1")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("2")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("3")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("4")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("5")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("6")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("7")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("8")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("9")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("10")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("11")));
		assertTrue(_graph_06.containsVertex(new VertexImpl("12")));
		
		assertFalse(_graph_06.containsVertex(new VertexImpl("l")));
		assertFalse(_graph_06.containsVertex(new VertexImpl("m")));
		assertFalse(_graph_06.containsVertex(new VertexImpl("n")));
		assertFalse(_graph_06.containsVertex(new VertexImpl("o")));
		assertFalse(_graph_06.containsVertex(new VertexImpl("Hamburg")));
		assertFalse(_graph_06.containsVertex(new VertexImpl("Walsrode")));		
	}
	
	@Test
	public void testFileReaderVertex()
	{
		GraphMaker reader = new GraphMaker(PATH_01);
		Graph<Vertex, CustomEdge> graph_01 = reader.getGraph();
		assertEquals(graph_01.vertexSet(), _graph_01.vertexSet());	
	}
	
	@Test
	public void testFileReaderVertex6()
	{
		GraphMaker reader = new GraphMaker(PATH_06);
		Graph<Vertex, CustomEdge> graph_02 = reader.getGraph();
		assertEquals(graph_02.vertexSet(), _graph_06.vertexSet());	
	}
	
	@Test
	public void testFileReaderEdge()
	{
		GraphMaker reader = new GraphMaker(PATH_01);
		Graph<Vertex, CustomEdge> graph_02 = reader.getGraph();
		assertEquals(graph_02.edgeSet(), _graph_01.edgeSet());	
	}
	
	
	@Test
	public void testEdgeWeighted5(){
		VertexImpl v1 = new VertexImpl("e");
		VertexImpl v2 = new VertexImpl("c");
		
		VertexImpl v3 = new VertexImpl("h");
		VertexImpl v4 = new VertexImpl("c");
		
		VertexImpl v5 = new VertexImpl("k");
		VertexImpl v6 = new VertexImpl("b");
		
		VertexImpl v7 = new VertexImpl("g");
		VertexImpl v8 = new VertexImpl("d");
		
		
		assertEquals(-7,_graph_05.getEdgeWeight(_graph_05.getEdge(v1, v2)),0);
		assertEquals(-2,_graph_05.getEdgeWeight(_graph_05.getEdge(v3, v4)),0);
		assertEquals(5.0,_graph_05.getEdgeWeight(_graph_05.getEdge(v5, v6)),0);
		assertEquals(4,_graph_05.getEdgeWeight(_graph_05.getEdge(v7, v8)),0);
		
	}
	
	@Test
	public void testEdgeWeighted3(){
		VertexImpl v1 = new VertexImpl("Lueneburg");
		VertexImpl v2 = new VertexImpl("Luebeck");
		
		VertexImpl v3 = new VertexImpl("Hameln");
		VertexImpl v4 = new VertexImpl("Walsrode");
		
		VertexImpl v5 = new VertexImpl("Soltau");
		VertexImpl v6 = new VertexImpl("Buxtehude");
		
		VertexImpl v7 = new VertexImpl("Norderstedt");
		VertexImpl v8 = new VertexImpl("Husum");
		
		
		assertEquals(115,_graph_03.getEdgeWeight(_graph_03.getEdge(v1, v2)),0);
		assertEquals(116,_graph_03.getEdgeWeight(_graph_03.getEdge(v3, v4)),0);
		assertEquals(60,_graph_03.getEdgeWeight(_graph_03.getEdge(v5, v6)),0);
		assertEquals(145,_graph_03.getEdgeWeight(_graph_03.getEdge(v7, v8)),0);
		
	}
	
	@Test
	public void testEdgeWeighted4(){
		VertexImpl v1 = new VertexImpl("a");
		VertexImpl v2 = new VertexImpl("b");
		
		VertexImpl v3 = new VertexImpl("e");
		VertexImpl v4 = new VertexImpl("c");
		
		VertexImpl v5 = new VertexImpl("j");
		VertexImpl v6 = new VertexImpl("k");
		
		VertexImpl v7 = new VertexImpl("k");
		VertexImpl v8 = new VertexImpl("c");
		
		
		assertEquals(2,_graph_04.getEdgeWeight(_graph_04.getEdge(v1, v2)),0);
		assertEquals(7,_graph_04.getEdgeWeight(_graph_04.getEdge(v3, v4)),0);
		assertEquals(5,_graph_04.getEdgeWeight(_graph_04.getEdge(v5, v6)),0);
		assertEquals(3,_graph_04.getEdgeWeight(_graph_04.getEdge(v7, v8)),0);
		
	}
	
//	@Test(expected = FileNotFoundException.class)
//	public void testFileReaderFail()throws Exception{
//		GraphMaker reader = new GraphMaker(PATH_02);
//	}
	
}
