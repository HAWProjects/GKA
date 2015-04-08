package eu.haw.gkaprojects.duc.robert;

import java.nio.file.Path;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
//import org.jgrapht.graph.AbstractBaseGraph;

public class Dosomething {
	

	private static final String PATH = "../GKAProjects_01/res/files/bspGraphen/bsp3.graph";

	public static void main(String[] args) {
		
		System.out.println("Name of file which sould be read?");
		Scanner scanner = new Scanner(System.in);
		String dateiname = scanner.nextLine();
		String pfad = "../GKAProjects_01/res/files/bspGraphen/"+ dateiname +".graph";

		
		GraphReader reader = new GraphReader(pfad);
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
//		System.out.println(graph.edgesOf(new VertexImpl("a")));
//		System.out.println(((AbstractBaseGraph<Vertex, DefaultWeightedEdge>) graph).outgoingEdgesOf(new VertexImpl("a")));
//		System.out.println(BreadthFirstSearch.createADJMap(graph));

//		Testklassen
//		System.out.println(BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("h"), new VertexImpl("g")));
//		System.out.println(BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("12"), new VertexImpl("12")));
//		System.out.println(BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("Uelzen"), new VertexImpl("Detmold")));
		
		
		
		GraphVisualizer.exportGraphToDotFile(graph);
		
		String[] graphNames = {"graph1", "graph2", "graph3"};
		String[] vertexNames = {};
		new GraphWerkzeugUI(graphNames, vertexNames);
//		System.out.println(DijkstraShortestPath.findPathBetween(graph,new VertexImpl("Paderborn"), new VertexImpl("Hannover")));
		
	}
}
