package eu.haw.gkaprojects.duc.robert.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import haw.gkaprojects.duc.robert.GraphMaker_withScanner;
import haw.gkaprojects.duc.robert.searchingAlgorithm.ShortestPathOfDijkstras;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

public class DijkstrasAlgorithmTest {

	private static final String PATH1 = "../GKA/res/files/bspGraphen/bsp1.graph";
	private static final String PATH2 = "../GKA/res/files/bspGraphen/bsp2.graph";
	private static final String PATH3 = "../GKA/res/files/bspGraphen/bsp3.graph";
	private static final String PATH4 = "../GKA/res/files/bspGraphen/bsp4.graph";
	private static final String PATH5 = "../GKA/res/files/bspGraphen/bsp5.graph";
	private static final String PATH6 = "../GKA/res/files/bspGraphen/bsp6.graph";
	public static final double DELTA = 1e-15;
	
	private Graph<Vertex, CustomEdge> _graph3;
	private Graph<Vertex, CustomEdge> _graph4;
	private Graph<Vertex, CustomEdge> _graph5;
	
	@Before
	public void setUp() throws Exception {
		File file1 = new File(PATH3);
		File file2 = new File(PATH4);
		File file3 = new File(PATH5);
		GraphMaker_withScanner scanner3 = new GraphMaker_withScanner(file1);
		GraphMaker_withScanner scanner4 = new GraphMaker_withScanner(file2);
		GraphMaker_withScanner scanner5 = new GraphMaker_withScanner(file3);
		
		 _graph3 = scanner3.getGraph();
		 _graph4 = scanner4.getGraph();
		 _graph5 = scanner5.getGraph();
	}
	
	@Test
	public void testOfAllShortestPathOfUndirectedGraph1() {
		Set<Vertex> setOfVertexs = _graph3.vertexSet();
		
		for (Vertex v1 : setOfVertexs) {
			for (Vertex v2 : setOfVertexs) {
				double shortestpath1 = (new ShortestPathOfDijkstras(_graph3, v1, v2)).getShortestPathLength();
				double shortestpath2 = 0;
				List<CustomEdge> listb = DijkstraShortestPath.findPathBetween(_graph3,v1, v2);
				for (CustomEdge customEdge : listb) {
					shortestpath2 += _graph3.getEdgeWeight(customEdge);
				}
				assertEquals(shortestpath1, shortestpath2, DELTA);
			}
		}
		
	}
	
	@Test
	public void testOfAllShortestPathOfUndirectedGraphWayAround1() {
		Set<Vertex> setOfVertexs = _graph3.vertexSet();
		
		for (Vertex v1 : setOfVertexs) {
			for (Vertex v2 : setOfVertexs) {
				double shortestpath1 = (new ShortestPathOfDijkstras(_graph3, v1, v2)).getShortestPathLength();
				double shortestpath2 = (new ShortestPathOfDijkstras(_graph3, v2, v1)).getShortestPathLength();
				
				assertEquals(shortestpath1, shortestpath2, DELTA);
			}
		}
	}
	
	@Test
	public void testOfAllShortestPathOfUndirectedGraph2() {
		Set<Vertex> setOfVertexs = _graph4.vertexSet();
		
		for (Vertex v1 : setOfVertexs) {
			for (Vertex v2 : setOfVertexs) {
				double shortestpath1 = (new ShortestPathOfDijkstras(_graph4, v1, v2)).getShortestPathLength();
				double shortestpath2 = 0;
				List<CustomEdge> listb = DijkstraShortestPath.findPathBetween(_graph4,v1, v2);
				for (CustomEdge customEdge : listb) {
					shortestpath2 += _graph4.getEdgeWeight(customEdge);
				}
				
				assertEquals(shortestpath1, shortestpath2, DELTA);
			}
		}
		
	}
	
	@Test
	public void testOfAllShortestPathOfUndirectedGraphWayAround2() {
		Set<Vertex> setOfVertexs = _graph4.vertexSet();
		
		for (Vertex v1 : setOfVertexs) {
			for (Vertex v2 : setOfVertexs) {
				double shortestpath1 = (new ShortestPathOfDijkstras(_graph4, v1, v2)).getShortestPathLength();
				double shortestpath2 = 0;
				List<CustomEdge> listb = DijkstraShortestPath.findPathBetween(_graph4,v1, v2);
				for (CustomEdge customEdge : listb) {
					shortestpath2 += _graph4.getEdgeWeight(customEdge);
				}
				assertEquals(shortestpath1, shortestpath2, DELTA);
			}
		}
		
	}
	
	
	@Test(expected = IllegalArgumentException.class )
	public void testVertextNotInGraph1() {
		(new ShortestPathOfDijkstras(_graph3, new VertexImpl("Paris"), new VertexImpl("Hannover"))).getShortestPath();
	}
	
	@Test(expected = IllegalArgumentException.class )
	public void testVertextNotInGraph2() {
		(new ShortestPathOfDijkstras(_graph3, new VertexImpl("Kairo"), new VertexImpl("Hanoi"))).getShortestPath();
	}
	
	@Test(expected = IllegalArgumentException.class )
	public void testVertextNotInGraph3() {
		(new ShortestPathOfDijkstras(_graph3, new VertexImpl("New York"), new VertexImpl("Beijing"))).getShortestPath();
	}
	
	
	@Test(expected = IllegalArgumentException.class )
	public void testNegativeWeight() {
		(new ShortestPathOfDijkstras(_graph5, new VertexImpl("h"), new VertexImpl("f"))).getShortestPath();
	}


}
