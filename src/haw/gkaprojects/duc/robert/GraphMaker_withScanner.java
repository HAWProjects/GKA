package haw.gkaprojects.duc.robert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

public class GraphMaker_withScanner {
	private Graph<Vertex, CustomEdge> _graph;
	public static final int DIRECTED = 1;
	public static final int WEIGHTED = 5;
	public static final boolean weighted = true;

	public GraphMaker_withScanner(String path) throws Exception {
		_graph = readGraphString(path);
	}

	private Graph<Vertex, CustomEdge> readGraphString(String path)
			throws Exception {
		Graph<Vertex, CustomEdge> graph = null;

		try (Scanner sc = new Scanner(new File(path))) {

			// read header them choose the right type for the graph
			String header = sc.nextLine();
			int type = chooseType(header);

			switch (type) {
			case 0:
				graph = new Pseudograph<>(CustomEdge.class);
				Scanner scnew = new Scanner(new File(path));
				fillgraph(graph, scnew);
				break;
			case DIRECTED:
				graph = new DirectedPseudograph<>(CustomEdge.class);
				fillgraph(graph, sc);
				break;
			case WEIGHTED:
				graph = new WeightedPseudograph<>(CustomEdge.class);
				fillgraph(graph, sc);
				break;
			case DIRECTED + WEIGHTED:
				graph = new DirectedWeightedPseudograph<>(CustomEdge.class);
				fillgraph(graph, sc);
				break;
			default:
				throw new Exception("Type not found!");
			}

			fillgraph(graph, sc);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(graph);
		return graph;
	}

	private void fillgraph(Graph<Vertex, CustomEdge> graph, Scanner sc) {
		
		//node1[":"attribute1][","node2[":"attribute2]["::"weight]]
		Pattern patternForWeightedgraph = Pattern
				.compile("(\\w+)(:(-?[0-9]+))?(,(\\w+)(:(-?[0-9]+))?(::(-?[0-9]+))?)?");

		while (sc.hasNextLine()) {
			Vertex v1 = null;
			Vertex v2 = null;
			Scanner lineSc = new Scanner(sc.nextLine().replaceAll(" ", ""));
			String line = lineSc.findInLine(patternForWeightedgraph);
			if (line != null) {
				MatchResult result = lineSc.match();
				
				String node1 = result.group(1);
				boolean node1HasAttribute = result.group(2) != null;
				String attribute1 = result.group(3);
				boolean hasTowNodes = result.group(4) != null;
				String node2 = result.group(5);
				boolean node2HasAttribute = result.group(6) != null;
				String attribute2 = result.group(7);
				boolean weighted = result.group(8) != null;
				String weight = result.group(9);

				if (node1 != null) {
					v1 = new VertexImpl(node1);
					if (node1HasAttribute) {
						v1.setAttribut(Integer.valueOf(attribute1));
					}
					graph.addVertex(v1);
				}

				if (hasTowNodes) {
					v2 = new VertexImpl(node2);
					if (node2HasAttribute) {
						v2.setAttribut(Integer.valueOf(attribute2));
					}
					graph.addVertex(v2);
					CustomEdge edge = graph.addEdge(v1, v2);
					if (weighted) {
						((AbstractBaseGraph<Vertex, CustomEdge>) graph)
								.setEdgeWeight(edge, Double.valueOf(weight));
					}
				}
			}
			
			lineSc.close();
		}
	}

	private int chooseType(String header) throws FileNotFoundException {
		Scanner sc = new Scanner(header);
		int graphType = 0;

		while (sc.hasNext()) {
			if (sc.hasNext("#directed")) {
				graphType += DIRECTED;
			} else if (sc.hasNext("#weighted")) {
				graphType += WEIGHTED;
			}
			sc.next();
		}
		sc.close();

		return graphType;
	}
	
	public Graph<Vertex, CustomEdge> getGraph() {
		return _graph;
	}
}
