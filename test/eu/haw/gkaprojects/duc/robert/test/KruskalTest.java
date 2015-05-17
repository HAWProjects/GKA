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
	private Graph<Vertex, CustomEdge> _graph1;
	@Before
	public void setUp() throws Exception {
		File file1 = new File(PATH1);
		GraphMaker_withScanner scanner3 = new GraphMaker_withScanner(file1);
		 _graph1 = scanner3.getGraph();
		 System.out.println(_graph1);
	}
	@Test
	public void testTruskal()
	{
		Kruskal krusk = new Kruskal(_graph1);
	assertEquals(4, krusk.getSpanningTree().edgeSet().size());	
	
	}

}
