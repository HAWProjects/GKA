package eu.haw.gkaprojects.duc.robert.test;

import static org.junit.Assert.*;
import haw.gkaprojects.duc.robert.GraphMaker_withScanner;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.searchingAlgorithm.Kruskal;

import java.io.File;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

public class KruskalTest
{
	private static final String PATH1 = "../GKA/test/eu/haw/gkaprojects/duc/robert/test/test/TestfileKruskal.graph";
	private static final String PATH4 = "../GKA/res/files/bspGraphen/bsp4.graph";
	private Graph<Vertex, CustomEdge> _graph1;
	private Graph<Vertex, CustomEdge> _graph4;
	
	@Before
	public void setUp() throws Exception {
		File file1 = new File(PATH1);
		File file4 = new File(PATH4);
		GraphMaker_withScanner scanner3 = new GraphMaker_withScanner(file1);
		GraphMaker_withScanner scanner4 = new GraphMaker_withScanner(file4);
		 _graph1 = scanner3.getGraph();
		 _graph4 = scanner4.getGraph();
		 
	}
	@Test
	public void testTruskal1()
	{
		Kruskal krusk = new Kruskal(_graph1);
		assertEquals(4, krusk.getSpanningTree().edgeSet().size());	
	}
	
	@Test
	public void testTruskal2()
	{
		Kruskal krusk = new Kruskal(_graph4);
		assertEquals(10, krusk.getSpanningTree().edgeSet().size());	
	}

}
