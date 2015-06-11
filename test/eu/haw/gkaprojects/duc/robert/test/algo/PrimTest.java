package eu.haw.gkaprojects.duc.robert.test.algo;

import static org.junit.Assert.*;
import haw.gkaprojects.duc.robert.GraphMaker_withScanner2;
import haw.gkaprojects.duc.robert.UndirectedConnectedGraphConstructor;
import haw.gkaprojects.duc.robert.SpanningTree.OwnPrimMinimumSpanningTree;
import haw.gkaprojects.duc.robert.SpanningTree.OwnPrimMinimumSpanningTree.DataStructure;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.io.File;

import org.jgrapht.Graph;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.junit.BeforeClass;
import org.junit.Test;

public class PrimTest {
	
	private static final String PATH4 = "../GKA/res/files/bspGraphen/bsp4.graph";
	private static final String PATH3 = "../GKA/res/files/bspGraphen/bsp3.graph";
	private static  Graph<Vertex, CustomEdge> _graph4;
	private static Graph<Vertex, CustomEdge> _graph3;
	private static Graph<Vertex, CustomEdge> rondomGraph1;
	private static Graph<Vertex, CustomEdge> rondomGraph2;
	private static Graph<Vertex, CustomEdge> rondomGraph3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file4 = new File(PATH4);
		File file3 = new File(PATH3);
		GraphMaker_withScanner2 scanner4 = new GraphMaker_withScanner2(file4);
		GraphMaker_withScanner2 scanner5 = new GraphMaker_withScanner2(file3);
		 _graph4 = scanner4.getGraph();
		 _graph3 = scanner5.getGraph();
		 
		 rondomGraph1 = UndirectedConnectedGraphConstructor.constructGraph(3000, 5000);
		 rondomGraph2 = UndirectedConnectedGraphConstructor.constructGraph(30000, 50000);
		 rondomGraph3 = UndirectedConnectedGraphConstructor.constructGraph(80000, 100000);
	}

	@Test
	public void testPrimBsp3Queue() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(_graph3, DataStructure.PRIORITYQUEUE);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(_graph3);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimBsp3Fib() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(_graph3, DataStructure.FIBONACCI_HEAP);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(_graph3);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimBsp4Queue() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(_graph4, DataStructure.PRIORITYQUEUE);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(_graph4);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimBsp4Fib() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(_graph4, DataStructure.FIBONACCI_HEAP);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(_graph4);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimRandom1Queue() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph1, DataStructure.PRIORITYQUEUE);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph1);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimRandom1Fib() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph1, DataStructure.FIBONACCI_HEAP);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph1);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimRandom1EdgeSetQueue() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph1, DataStructure.PRIORITYQUEUE);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph1);
		assertEquals(primJGraph.getMinimumSpanningTreeEdgeSet(), spanningTree.getminimumSpanningTree());
	}
	
	@Test
	public void testPrimRandom1EdgeSetFib() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph1, DataStructure.FIBONACCI_HEAP);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph1);
		assertEquals(primJGraph.getMinimumSpanningTreeEdgeSet(), spanningTree.getminimumSpanningTree());
	}
	
	
	@Test
	public void testPrimRandom2Queue() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph2, DataStructure.PRIORITYQUEUE);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph2);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimRandom2Fib() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph2, DataStructure.FIBONACCI_HEAP);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph2);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimRandom2EdgeSetQueue() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph2, DataStructure.PRIORITYQUEUE);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph2);
		assertEquals(primJGraph.getMinimumSpanningTreeEdgeSet(), spanningTree.getminimumSpanningTree());
	}
	
	@Test
	public void testPrimRandom2EdgeSetFib() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph2, DataStructure.FIBONACCI_HEAP);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph2);
		assertEquals(primJGraph.getMinimumSpanningTreeEdgeSet(), spanningTree.getminimumSpanningTree());
	}
	
	@Test
	public void testPrimRandom3Queue() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph3, DataStructure.PRIORITYQUEUE);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph3);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}
	
	@Test
	public void testPrimRandom3Fib() throws Exception {
		OwnPrimMinimumSpanningTree spanningTree = new OwnPrimMinimumSpanningTree(rondomGraph3, DataStructure.FIBONACCI_HEAP);
		PrimMinimumSpanningTree<Vertex, CustomEdge> primJGraph = new PrimMinimumSpanningTree<>(rondomGraph3);
		assertEquals(primJGraph.getMinimumSpanningTreeTotalWeight(), spanningTree.getTotalWeight(),0);
	}

}
