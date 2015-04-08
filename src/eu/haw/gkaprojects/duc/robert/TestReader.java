package eu.haw.gkaprojects.duc.robert;

import org.jgrapht.Graph;

public class TestReader {

	private static final String PATH = "/Users/DucNguyenMinh/Documents/workspace/GKAP/.git/GKAProjects/GKAProjects/files/bspGraphen/bsp4.graph";

	public static void main(String[] args) {
		GraphReader reader = new GraphReader(PATH);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
//		System.out.println(graph.toString());
		GraphVisualizer.exportGraphToDotFile(graph);
	}
}
