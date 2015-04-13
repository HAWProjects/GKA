package eu.haw.gkaprojects.duc.robert.test;


import static org.junit.Assert.*;
import haw.gkaprojects.duc.robert.CustomEdge;
import haw.gkaprojects.duc.robert.GraphReader;
import haw.gkaprojects.duc.robert.Vertex;
import haw.gkaprojects.duc.robert.VertexImpl;

import java.io.FileNotFoundException;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.rules.ExpectedException;

public class ReaderTest {

	
	private static final String PATH_01 = "../GKA/res/files/bspGraphen/bsp1.graph";
	private static final String PATH_03 = "../GKA/res/files/bspGraphen/bsp3.graph";
	private static final String PATH_02 = "..\\GKA\\res\\files\\bspGraphen\\bsp9999.graph";
	private static final String PATH_05 = "..\\GKA\\res\\files\\bspGraphen\\bsp5.graph";
	private Graph<Vertex, CustomEdge> _graph_01;
	private Graph<Vertex, CustomEdge> _graph_03;
	private Graph<Vertex, CustomEdge> _graph_05;

	@Before
	public void setUp() {
		GraphReader reader = new GraphReader(PATH_01);
		_graph_01 = reader.getGraph();
		
		GraphReader reader2 = new GraphReader(PATH_03);
		_graph_03 = reader2.getGraph();
		
		GraphReader reader5 = new GraphReader(PATH_05);
		_graph_05 = reader5.getGraph();

	}
	
	
	@Test 
	public void testAllVertexexists()
	{
//		assertTrue(_graph_01.containsVertex(new VertexImpl("a")));
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
		
	}
	
	@Test 
	public void testAllVertexexists2()
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
		
		
		assertFalse(_graph_03.containsVertex(new VertexImpl("Kassel")));		
	}
	
	@Test 
	public void testAllVertexexists5()
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
	}
	
	@Test
	public void testFileReaderVertex()
	{
		GraphReader reader = new GraphReader(PATH_01);
		Graph<Vertex, CustomEdge> graph_02 = reader.getGraph();
		assertEquals(graph_02.vertexSet(), _graph_01.vertexSet());	
	}
	
	@Test
	public void testFileReaderEdge()
	{
		GraphReader reader = new GraphReader(PATH_01);
		Graph<Vertex, CustomEdge> graph_02 = reader.getGraph();
		assertEquals(graph_02.edgeSet(), _graph_01.edgeSet());	
	}
	
	
	
	@Test(expected = FileNotFoundException.class)
	public void testFileReaderFail()throws Exception{
		GraphReader reader = new GraphReader(PATH_02);

		
	}
	
}
