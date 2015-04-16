package haw.gkaprojects.duc.robert;

import java.util.List;
import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

/**
 * This GraphReader reads automatically the .graph file from given path 
 * @author DucNguyenMinh
 *
 */
public class GraphMaker {

	public static final int DIRECTED = 1;
	public static final int ATTRIBUTED = 3;
	public static final int WEIGHTED = 5;
	public static final boolean attributed = true;
	public static final boolean weighted = true;

	private Graph<Vertex, CustomEdge> _graph;

	/**
	 * Constructor will create the graph by reading .graph file from given path
	 *
	 * @param Path path to file
	 */
	public GraphMaker(String Path) {
		_graph = readgraph(Path);
	}
	
	/**
	 * Read and give back a graph 
	 * 
	 * @param path path to file
	 * @return a graph of type Graph<Vertex,CustomEdge>
	 */
	private Graph<Vertex, CustomEdge> readgraph(String path) {
		String[] strData_with_graphtype = GraphFilereader.readFile(path);

		String strData = strData_with_graphtype[0];
		int graphType = Integer.valueOf(strData_with_graphtype[1]);

		String[] edgesArray = strData.split(System.lineSeparator());

		return createGraph(edgesArray, graphType);
	}

	/**
	 * Create a graph of type Graph<Vertex,CustomEdge> with specified type
	 * 
	 * @param edgesArray an array of edges stored in String format	
	 * @param graphType Type of graph
	 * @return
	 */
	private Graph<Vertex, CustomEdge> createGraph(String[] edgesArray,
			int graphType) {
		// Trim edges
		List<String> tempList = new ArrayList<String>();
		for (String edge : edgesArray) {
			if (!edge.equals("")) {
				tempList.add(edge);
			}
		}

		String[] tempStrings = new String[tempList.size()];
		tempStrings = tempList.toArray(tempStrings);
		edgesArray = tempStrings;

		Graph<Vertex, CustomEdge> graph = null;
		
		switch (graphType) {
		case 0:
			graph = new Pseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, !attributed,
					!weighted);
			break;
		case DIRECTED:
			graph = new DirectedPseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, !attributed,
					!weighted);
			break;
		case ATTRIBUTED:
			graph = new Pseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, attributed,
					!weighted);
			break;
		case DIRECTED + ATTRIBUTED:
			graph = new DirectedPseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, attributed,
					!weighted);
			break;
		case WEIGHTED:
			graph = new WeightedPseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, !attributed,
					weighted);
			break;
		case DIRECTED + WEIGHTED:
			graph = new DirectedWeightedPseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, !attributed,
					weighted);
			break;
		case ATTRIBUTED + WEIGHTED:
			graph = new WeightedPseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, attributed, weighted);
			;
			break;
		case DIRECTED + ATTRIBUTED + WEIGHTED:
			graph = new DirectedWeightedPseudograph<>(CustomEdge.class);
			addVerticesAndEdgesforGraph(graph, edgesArray, attributed, weighted);
			break;
		default:
			System.out.println("Cannot define type of Graph!");
		}
		System.out.println("createGraph done: ");
		return graph;
	}

	/**
	 * Add all Vertices and Edges into the given graph
	 * 
	 * @param graph graph to store Vertices and Edges
	 * @param edgesArray an array of edges stored in String format	
	 * @param attr whether the vertices comes with attributes
	 * @param weighted whether the edges are weighted 
	 */
	private void addVerticesAndEdgesforGraph(Graph<Vertex, CustomEdge> graph,
			String[] edgesArray, boolean attr, boolean weighted) {
		
		for (String edge : edgesArray) {
			
			String[] verticesOfEdge = edge.split(",|::|:");
			
			//If there just one Vertex, add to it to graph
			if ((!attr && verticesOfEdge.length < 2)
					|| (attr && verticesOfEdge.length < 4)) {
				Vertex v = new VertexImpl(verticesOfEdge[0].replaceAll(" ", ""));
				graph.addVertex(v);
				continue;
			}
			
			//add Vertices and Edges 
			Vertex v1 = null;
			Vertex v2 = null;
			if (!attr) {
				v1 = new VertexImpl(verticesOfEdge[0].replaceAll(" ", ""));
				v2 = new VertexImpl(verticesOfEdge[1].replaceAll(" ", ""));
			} else {
				v1 = new VertexImpl(verticesOfEdge[0].replaceAll(" ", ""),
						Integer.valueOf(verticesOfEdge[1].replaceAll(" ", "")));
				v2 = new VertexImpl(verticesOfEdge[2].replaceAll(" ", ""),
						Integer.valueOf(verticesOfEdge[3].replaceAll(" ", "")));
			}

			graph.addVertex(v1);
			graph.addVertex(v2);
			
			//add weight for edges if weighted
			CustomEdge addedEdge = graph.addEdge(v1, v2);
			if (weighted) {
				((AbstractBaseGraph<Vertex, CustomEdge>) graph)
						.setEdgeWeight(addedEdge,
								Double.valueOf(verticesOfEdge[attr ? 4 : 2]));
			}
		}
	}

	/**
	 * Get the graph which made by the GraphReader
	 * @return a graph of type Graph<Vertex, CustomEdge> 
	 */
	public Graph<Vertex, CustomEdge> getGraph() {
		return _graph;
	}
}
