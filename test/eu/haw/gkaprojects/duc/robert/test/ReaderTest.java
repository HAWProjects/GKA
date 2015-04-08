package eu.haw.gkaprojects.duc.robert.test;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import eu.haw.gkaprojects.duc.robert.CustomEdge;
import eu.haw.gkaprojects.duc.robert.GraphReader;
import eu.haw.gkaprojects.duc.robert.Vertex;

public class ReaderTest {

	
	private static final String PATH_01 = "../GKAProjects_01/res/files/bspGraphen/bsp6.graph";
	private static final String PATH_02 = "..\\GKAProjects_01\\res\files\\bspGraphen\\bsp9999.graph";
	private Graph<Vertex, CustomEdge> _graph;

	@Before
	public void setUp() {
		GraphReader reader = new GraphReader(PATH_01);
		_graph = reader.getGraph();
//		System.out.println(graph.toString());
//		GraphVisualizer.exportGraphToDotFile(_graph);
	}
	
	@Test
	public void testFileReaderVertex()
	{
		GraphReader reader = new GraphReader(PATH_01);
		Graph<Vertex, CustomEdge> graph_02 = reader.getGraph();
		assertEquals(graph_02.vertexSet(), _graph.vertexSet());	
	}
	
	@Test
	public void testFileReaderEdge()
	{
		GraphReader reader = new GraphReader(PATH_01);
		Graph<Vertex, CustomEdge> graph_02 = reader.getGraph();
		assertEquals(graph_02.edgeSet(), _graph.edgeSet());	
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testFileReaderFail()
	{
		GraphReader reader = new GraphReader(PATH_02);
		reader.getGraph();
		
	}
	
}
