package eu.haw.gkaprojects.duc.robert.test.algo;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import haw.gkaprojects.duc.robert.GraphMaker_withScanner2;
import haw.gkaprojects.duc.robert.searchingAlgorithm.AStarShortestPath;
import haw.gkaprojects.duc.robert.searchingAlgorithm.ShortestPathOfDijkstras;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

public class AStarAlgorithmTest {

	private static final String PATH3 = "../GKA/res/files/bspGraphen/bsp3.graph";
	public static final double DELTA = 1e-15;
	
	private Graph<Vertex, CustomEdge> _graph3;
	
	@Before
	public void setUp() throws Exception {
		File file = new File(PATH3);
		GraphMaker_withScanner2 scanner3 = new GraphMaker_withScanner2(file);
		 _graph3 = scanner3.getGraph();
	}
	
	@Test
	public void testFromEveryWhereToHamburg() {
		Set<Vertex> setOfVertexs = _graph3.vertexSet();
		
		for (Vertex v1 : setOfVertexs) {
				Vertex hamburg = new VertexImpl("Hamburg");
				double shortestpath1 = (new AStarShortestPath(_graph3, v1, hamburg)).getShortestPathLength();
				double shortestpath3 = 0;
				List<CustomEdge> listb = DijkstraShortestPath.findPathBetween(_graph3,v1, hamburg);
				for (CustomEdge customEdge : listb) {
					shortestpath3 += _graph3.getEdgeWeight(customEdge);
				}
				assertEquals(shortestpath1, shortestpath3, DELTA);
		}
	}
	
	@Test
	public void testFromEveryWhereToHamburgAgainstDijkstraAlgorithm() {
		Set<Vertex> setOfVertexs = _graph3.vertexSet();
		
		for (Vertex v1 : setOfVertexs) {
				Vertex hamburg = new VertexImpl("Hamburg");
				double shortestpath1 = (new AStarShortestPath(_graph3, v1, hamburg)).getShortestPathLength();
				double shortestpath2 = (new ShortestPathOfDijkstras(_graph3, v1, hamburg )).getShortestPathLength();
				assertEquals(shortestpath1, shortestpath2, DELTA);
		}
	}
	
	
	
	@Test
	public void testVonMindenNachHamburg() {
		Vertex hamburg = new VertexImpl("Hamburg");
		Vertex v1 = new VertexImpl("Minden");
		double shortestpath1 = (new AStarShortestPath(_graph3, v1, hamburg)).getShortestPathLength();
		double shortestpath3 = 0;
		List<CustomEdge> listb = DijkstraShortestPath.findPathBetween(_graph3,v1, hamburg);
		for (CustomEdge customEdge : listb) {
			shortestpath3 += _graph3.getEdgeWeight(customEdge);
		}
		assertEquals(shortestpath1, shortestpath3, DELTA);
	}
	
	@Test
	public void testVonMuensterNachHamburg() {
		Vertex hamburg = new VertexImpl("Hamburg");
		Vertex v1 = new VertexImpl("Muenster");
		double shortestpath1 = (new AStarShortestPath(_graph3, v1, hamburg)).getShortestPathLength();
		double shortestpath3 = 0;
		List<CustomEdge> listb = DijkstraShortestPath.findPathBetween(_graph3,v1, hamburg);
		for (CustomEdge customEdge : listb) {
			shortestpath3 += _graph3.getEdgeWeight(customEdge);
		}
		assertEquals(shortestpath1, shortestpath3, DELTA);
	}
	
	@Test
	public void testVonHusumNachHamburg() {
		Vertex hamburg = new VertexImpl("Hamburg");
		Vertex v1 = new VertexImpl("Husum");
		double shortestpath1 = (new AStarShortestPath(_graph3, v1, hamburg)).getShortestPathLength();
		double shortestpath3 = 0;
		List<CustomEdge> listb = DijkstraShortestPath.findPathBetween(_graph3,v1, hamburg);
		for (CustomEdge customEdge : listb) {
			shortestpath3 += _graph3.getEdgeWeight(customEdge);
		}
		assertEquals(shortestpath1, shortestpath3, DELTA);
	}
	
	@Test(expected = IllegalArgumentException.class )
	public void testVertextNotInGraph1() {
		(new AStarShortestPath(_graph3, new VertexImpl("Paris"), new VertexImpl("Hannover"))).getShortestPath();
	}
	
	@Test(expected = IllegalArgumentException.class )
	public void testVertextNotInGraph2() {
		(new AStarShortestPath(_graph3, new VertexImpl("Kairo"), new VertexImpl("Hanoi"))).getShortestPath();
	}
	
	@Test(expected = IllegalArgumentException.class )
	public void testVertextNotInGraph3() {
		(new AStarShortestPath(_graph3, new VertexImpl("New York"), new VertexImpl("Beijing"))).getShortestPath();
	}
	
}
