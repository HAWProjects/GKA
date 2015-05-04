package haw.gkaprojects.duc.robert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.WeightedPseudograph;

import haw.gkaprojects.duc.robert.searchingAlgorithm.ShortestPathOfDijkstras;
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
			Set<Vertex> setOfVertex = new HashSet<>(graph.vertexSet());
			Random rand = new Random();

			if (setOfVertex.contains(target)) {
				for (Vertex vertex : setOfVertex) {
					if (vertex.equals(target)) {
						target = vertex;
						break;
					}
				}

				target.setAttribut(0);
				setOfVertex.remove(target);

				for (Vertex vertex : setOfVertex) {
					double shortestDistanceToTarget = (new ShortestPathOfDijkstras(
							graph, vertex, target)).getShortestPathLength();
					if (shortestDistanceToTarget == 0) {
						vertex.setAttribut(rand.nextInt());
					} else {
						int bound = (int) Math
								.ceil(shortestDistanceToTarget / 10);
						int heuristic = rand.nextInt(bound)
								+ (int) (shortestDistanceToTarget * 0.9);
						vertex.setAttribut(heuristic);
					}
				}

			} else {
				throw new IllegalArgumentException(
						"Graph does not contain target node");
			}
		}
	}

	public static Graph<Vertex, CustomEdge> contructAGraphFile(
			int verticesAmount, int edgesAmount, String FilePathToWrite) {
		Graph<Vertex, CustomEdge> graph = constructGraph(verticesAmount,
				edgesAmount);
		GraphFileSaver.saveGraphToFile(FilePathToWrite, graph);
		return graph;
	}

	private static Graph<Vertex, CustomEdge> constructGraph(int verticesAmount,
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
		if (edgesAmount < 200) {
			boundOfWeight = 200;
		}

		WeightGenerator wg = new WeightGenerator(rand.nextInt(), boundOfWeight);

		for (int i = 0; i < edgesAmount; i++) {
			Vertex v1 = listOfVertex.get(rand.nextInt(listOfVertex.size()));
			Vertex v2 = listOfVertex.get(rand.nextInt(listOfVertex.size()));

			CustomEdge edge = graph.addEdge(v1, v2);
			((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(edge,
					wg.getWeight());
			// ((AbstractBaseGraph<Vertex, CustomEdge>)
			// graph).setEdgeWeight(edge,
			// 10);
		}

		HeuristicGenerator.setHeuristicForGraph(graph,
				listOfVertex.get(rand.nextInt(listOfVertex.size())));
		// HeuristicGenerator.setHeuristicForGraph(graph, listOfVertex.get(49));
	}
}
