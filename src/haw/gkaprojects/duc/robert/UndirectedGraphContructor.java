package haw.gkaprojects.duc.robert;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.WeightedPseudograph;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

/**
 * 
 * @author DucNguyenMinh
 *
 */
public class UndirectedGraphContructor {

	/**
	 * 
	 * @author DucNguyenMinh
	 *
	 */
	static class WeightGenerator {
		private Random random;
		private int bound;
		Set<Integer> alreadyUsed;

		WeightGenerator(int seed, int bound) {
			this.random = new Random();
			this.bound = bound;
			alreadyUsed = new HashSet<Integer>();
			this.random.setSeed(seed);
		}

		int getWeight() {
			int weight = this.random.nextInt(this.bound);
			while (alreadyUsed.contains(weight)) {
				weight = this.random.nextInt(this.bound);
			}
			alreadyUsed.add(weight);
			return weight;
		}

		void setSeed(int seed) {
			this.random.setSeed(seed);
		}
	}

	public static class HeuristicGenerator {
		public static void setHeuristicForGraph(
				Graph<Vertex, CustomEdge> graph, Vertex target) {
			Set<Vertex> setOfVertex = graph.vertexSet();
//			Random rand = new Random();

			if (setOfVertex.contains(target)) {

				for (Vertex vertex : setOfVertex) {
					if (vertex.equals(target)) {
						target = vertex;
						break;
					}
				}

				Queue<Vertex> exploringQueue = new ArrayDeque<Vertex>();
				Set<Vertex> explored = new HashSet<Vertex>();
				Set<Vertex> neighbor = null;

				// initialize target
				exploringQueue.add(target);
				target.setAttribut(0);
				while (!exploringQueue.isEmpty()) {
					Vertex v = exploringQueue.poll();
					int bound = 0;
					List<Vertex> comparingList = new ArrayList<Vertex>();

					neighbor = findNeighborOf(v, graph, setOfVertex);
					for (Vertex nb : neighbor) {
						if (explored.contains(nb)) {
							comparingList.add(nb);
						} else {
							if (!exploringQueue.contains(nb)) {
								exploringQueue.add(nb);
							}
						}
					}

					Collections.sort(comparingList, new Comparator<Vertex>() {

						@Override
						public int compare(Vertex v1, Vertex v2) {
							CustomEdge edge1 = findShortestEgdes(graph, v, v1);
							CustomEdge edge2 = findShortestEgdes(graph, v, v2);
							
							double cfromv1 = graph.getEdgeWeight(edge1) + v1.getAttribut();
							double cfromv2 = graph.getEdgeWeight(edge2) + v2.getAttribut();

							if ((cfromv1 < cfromv2)) {
								return -1;
							} else if (cfromv1 > cfromv2) {
								return 1;
							} else {
								return 0;
							}
						}
					});
//					System.out.println(v);
//					System.out.println(comparingList);
					if (!comparingList.isEmpty()) {
						Vertex closestNB = comparingList.get(0);
						CustomEdge edge = findShortestEgdes(graph, v, closestNB);
//						System.out.println("edge: "+edge);
						int ew = (int)graph.getEdgeWeight(edge);
						bound = closestNB.getAttribut() + ew;
						
						int heuristic = (int)Math.ceil(ew*0.1) + closestNB.getAttribut();

//						System.out.println("heuristic: "+heuristic);
						v.setAttribut(heuristic);
//					System.out.println("bound: "+bound);
//					System.out.println("vertex: " +v);
					} else {
						v.setAttribut(bound);
					}
					explored.add(v);
//					System.out.println(explored);
//					System.out.println();
				}

			} else {
				throw new IllegalArgumentException(
						"Graph does not contain target node");
			}
		}
	}

	private static CustomEdge findShortestEgdes(Graph<Vertex, CustomEdge> graph, Vertex v1, Vertex v2){
		Set<CustomEdge> edges = graph.getAllEdges(v1, v2);
		CustomEdge shortestEdge = null;
		double minlength = Double.MAX_VALUE;
//		System.out.println("edges: "+edges);
		for (CustomEdge customEdge : edges) {
			 double edgeLength = graph.getEdgeWeight(customEdge);
			if(edgeLength < minlength){
				shortestEdge = customEdge;
				minlength = edgeLength;
			}
		}
//		System.out.println(shortestEdge + ":"+ graph.getEdgeWeight(shortestEdge));
		return shortestEdge;
	}
	public static Set<Vertex> findNeighborOf(Vertex vertex,
			Graph<Vertex, CustomEdge> graph, Set<Vertex> setOfVertex) {
		Set<Vertex> neighborOfVertex = new HashSet<Vertex>();

		for (Vertex v : graph.vertexSet()) {
			for (CustomEdge edge : graph.getAllEdges(v, vertex)) {

				Vertex source = graph.getEdgeSource(edge);
				Vertex target = graph.getEdgeTarget(edge);
				
				if (vertex.equals(target) && vertex.equals(source)) {
					continue;
				}
				
//				if (vertex.equals(source)) {
					neighborOfVertex.add(v);
//				} else if vertex {
//					neighborOfVertex.add(source);
//				}
			}
		}

		return neighborOfVertex;
	}

	public static Graph<Vertex, CustomEdge> constructGraph(
			int verticesAmount, int edgesAmount, String FilePathToWrite) {
		Graph<Vertex, CustomEdge> graph = constructGraph(verticesAmount,
				edgesAmount);
		GraphFileSaver.saveGraphToFile(FilePathToWrite, graph);
		return graph;
	}

	public static Graph<Vertex, CustomEdge> constructGraph(int verticesAmount,
			int edgesAmount) {
		Graph<Vertex, CustomEdge> graph = new WeightedPseudograph<Vertex, CustomEdge>(
				CustomEdge.class);
		List<Vertex> listOfVertex = new ArrayList<Vertex>();

		// add vertices
		addVertexToGraph(graph, listOfVertex, verticesAmount);

		// add edges
		addEdgesToGraph(graph, listOfVertex, edgesAmount);

		return graph;
	}

	private static void addVertexToGraph(Graph<Vertex, CustomEdge> graph,
			List<Vertex> listOfVertex, int verticesAmount) {

		for (int i = 0; i < verticesAmount; i++) {
			Vertex v = new VertexImpl(i + "");
			listOfVertex.add(v);
			graph.addVertex(v);
		}
	}

	private static void addEdgesToGraph(Graph<Vertex, CustomEdge> graph,
			List<Vertex> listOfVertex, int edgesAmount) {

		Random rand = new Random();
		int boundOfWeight = edgesAmount;
		//
		if (edgesAmount < 100) {
			boundOfWeight = 100;
		}

		WeightGenerator wg = new WeightGenerator(rand.nextInt(), boundOfWeight);

		for (int i = 0; i < edgesAmount; i++) {
			Vertex v1 = listOfVertex.get(rand.nextInt(listOfVertex.size()));
			Vertex v2 = listOfVertex.get(rand.nextInt(listOfVertex.size()));

			CustomEdge edge = graph.addEdge(v1, v2);
			((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(edge,
					wg.getWeight());
		}

//		HeuristicGenerator.setHeuristicForGraph(graph,
//				listOfVertex.get(rand.nextInt(listOfVertex.size())));
	}
}
