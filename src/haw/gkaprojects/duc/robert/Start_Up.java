package haw.gkaprojects.duc.robert;

import java.io.IOException;

import org.jgraph.JGraph;
import org.jgrapht.Graph;
//import org.jgrapht.alg.DijkstraShortestPath;
//import org.jgrapht.graph.AbstractBaseGraph;








import org.jgrapht.ext.JGraphModelAdapter;

import haw.gkaprojects.duc.robert.GUITool.GraphAdmin;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.GraphMaker;

public class Start_Up {
	



	public static void main(String[] args) {
		

		ShowGraph show =new ShowGraph();
//		CreateGraph create = new CreateGraph(reader.getRowList());
//		Graph<Vertex, CustomEdge> graph = create.getGraph();
//		JGraph jgraph = new JGraph(new JGraphModelAdapter<>(graph));
		
//		show.setGraph(jgraph);
		
		/*
		GraphMaker reader = new GraphMaker(PATH);
		@SuppressWarnings("unused")
		Graph<Vertex, CustomEdge> graph = reader.getGraph();
		
		new GraphAdmin("../GKA/res/files/bspGraphen/");
		*/
		
		
		
//		System.out.println(graph.edgesOf(new VertexImpl("a")));
//		System.out.println(((AbstractBaseGraph<Vertex, DefaultWeightedEdge>) graph).outgoingEdgesOf(new VertexImpl("a")));
//		System.out.println(BreadthFirstSearch.createADJMap(graph));

//		Testklassen
//		System.out.println(BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("h"), new VertexImpl("g")));
//		System.out.println(BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("12"), new VertexImpl("12")));
//		System.out.println(BreadthFirstSearch.searchForTheShortestPath(graph, new VertexImpl("Uelzen"), new VertexImpl("Detmold")));
		
	
		
//		GraphVisualizer.exportGraphToDotFile(graph);
//		
//		String[] graphNames = {"graph1", "graph2", "graph3"};
//		String[] vertexNames = {};
//		new GraphWerkzeugUI(graphNames, vertexNames);
//		System.out.println(DijkstraShortestPath.findPathBetween(graph,new VertexImpl("Paderborn"), new VertexImpl("Hannover")));
	}
}