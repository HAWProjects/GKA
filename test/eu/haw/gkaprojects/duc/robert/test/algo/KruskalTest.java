package eu.haw.gkaprojects.duc.robert.test.algo;

import static org.junit.Assert.assertEquals;
import haw.gkaprojects.duc.robert.GraphMaker_withScanner;
import haw.gkaprojects.duc.robert.UndirectedConnectedGraphConstructor;
import haw.gkaprojects.duc.robert.SpanningTree.Kruskal;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

import java.io.File;

import org.jgrapht.Graph;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.junit.BeforeClass;
import org.junit.Test;

public class KruskalTest
{
	private static final String PATH1 = "../GKA/test/eu/haw/gkaprojects/duc/robert/test/test/TestfileKruskal.graph";
	private static final String PATH4 = "../GKA/res/files/bspGraphen/bsp4.graph";
	private static final String PATH3 = "../GKA/res/files/bspGraphen/bsp3.graph";
	private static Graph<Vertex, CustomEdge> _graph1;
	private static Graph<Vertex, CustomEdge> _graph4;
	private static Graph<Vertex, CustomEdge> _graph3;
	private static Graph<Vertex, CustomEdge> rondomGraph1;
	private static Graph<Vertex, CustomEdge> rondomGraph2;
	private static Graph<Vertex, CustomEdge> rondomGraph3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file1 = new File(PATH1);
		File file4 = new File(PATH4);
		File file3 = new File(PATH3);
		GraphMaker_withScanner scanner3 = new GraphMaker_withScanner(file1);
		GraphMaker_withScanner scanner4 = new GraphMaker_withScanner(file4);
		GraphMaker_withScanner scanner5 = new GraphMaker_withScanner(file3);
		 _graph1 = scanner3.getGraph();
		 _graph4 = scanner4.getGraph();
		 _graph3 = scanner5.getGraph();
		 
		 rondomGraph1 = UndirectedConnectedGraphConstructor.constructGraph(3000, 5000);
		 rondomGraph2 = UndirectedConnectedGraphConstructor.constructGraph(8000, 15000);
		 rondomGraph3 = UndirectedConnectedGraphConstructor.constructGraph(15000, 60000);

		 
	}
	@Test
	public void testKruskal1() throws Exception
	{
		Kruskal krusk = new Kruskal(_graph1);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(_graph1);
		
		assertEquals(4, krusk.getSpanningTree().edgeSet().size());	
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeEdgeSet(),krusk.getSpanningTree().edgeSet());
	}
	
	@Test
	public void testKruskal1Weigth() throws Exception
	{
		Kruskal krusk = new Kruskal(_graph1);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(_graph1);
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeTotalWeight(),krusk.getWeightOfSpanningTree(),0);
	}
	
	
	
	@Test
	public void testKruskal2() throws Exception
	{
		Kruskal krusk = new Kruskal(_graph4);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(_graph4);
		
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeEdgeSet(),krusk.getSpanningTree().edgeSet());
		assertEquals(10, krusk.getSpanningTree().edgeSet().size());	
	}
	
	@Test
	public void testKruskal2Weigth() throws Exception
	{
		Kruskal krusk = new Kruskal(_graph4);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(_graph4);
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeTotalWeight(),krusk.getWeightOfSpanningTree(),0);
	}
	
	@Test
	public void testKruskal3() throws Exception
	{
		Kruskal krusk = new Kruskal(_graph3);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(_graph3);
		
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeEdgeSet(),krusk.getSpanningTree().edgeSet());
	}
	
	@Test
	public void testKruskal3Weigth() throws Exception
	{
		Kruskal krusk = new Kruskal(_graph3);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(_graph3);
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeTotalWeight(),krusk.getWeightOfSpanningTree(),0);
	}
	
	@Test
	public void testKruskalRandom1() throws Exception
	{
		Kruskal krusk = new Kruskal(rondomGraph1);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(rondomGraph1);
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeEdgeSet(),krusk.getSpanningTree().edgeSet());
	}
	
	@Test
	public void testKruskalRandom2() throws Exception
	{
		Kruskal krusk = new Kruskal(rondomGraph2);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(rondomGraph2);
		assertEquals( kruskalMinimumSpanningTree.getMinimumSpanningTreeEdgeSet(),krusk.getSpanningTree().edgeSet());
	}
	
//	@Test
//	public void testKruskalRandom3() throws Exception{
//		Kruskal krusk = new Kruskal(rondomGraph3);
//		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(rondomGraph3);
//		assertEquals( kruskalMinimumSpanningTree.getMinimumSpanningTreeEdgeSet(),krusk.getSpanningTree().edgeSet());
//	}
	
	@Test
	public void testKruskalRandom1Weigth() throws Exception
	{
		Kruskal krusk = new Kruskal(rondomGraph1);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(rondomGraph1);
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeTotalWeight(),krusk.getWeightOfSpanningTree(),0);
	}
	
	@Test
	public void testKruskalRandom2Weigth() throws Exception
	{
		Kruskal krusk = new Kruskal(rondomGraph2);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(rondomGraph2);
		assertEquals(kruskalMinimumSpanningTree.getMinimumSpanningTreeTotalWeight(),krusk.getWeightOfSpanningTree(),0);
	}
	
	@Test
	public void testKruskalRandom3Weigth() throws Exception
	{
		
		Kruskal krusk = new Kruskal(rondomGraph3);
		KruskalMinimumSpanningTree<Vertex, CustomEdge> kruskalMinimumSpanningTree = new KruskalMinimumSpanningTree<>(rondomGraph3);
		assertEquals( kruskalMinimumSpanningTree.getMinimumSpanningTreeTotalWeight(),krusk.getWeightOfSpanningTree(),0);
	}
	
}
