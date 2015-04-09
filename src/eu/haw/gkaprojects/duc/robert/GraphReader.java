package eu.haw.gkaprojects.duc.robert;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

public class GraphReader {

	public static final int DIRECTED = 1;
	public static final int ATTRIBUTED = 3;
	public static final int WEIGHTED = 5;
	public static final boolean attributed = true;



	private Graph<Vertex, CustomEdge> _graph;

	/**
	 * 
	 * @param Path
	 *            Path to file
	 */
	public GraphReader(String Path) {
		_graph = readgraph(Path);
	}

	private Graph<Vertex,CustomEdge> readgraph(String path) {
		String strData = "";
		int graphType = 0;
		// Read Text File
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			// read header
			sb.append(line);
			String header =sb.toString().trim();
			graphType = chooseGraphType(header);

			// read graph
			sb = new StringBuilder();
			if (graphType == 0) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
				// sb.append(line);
			}
			strData = sb.toString();

		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Cannot find text data!!!" + path);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] edgesArray = strData.split(System.lineSeparator());
		return createGraph(edgesArray, graphType);
	}
	
	/*
	 * 
	 */
	private Graph<Vertex, CustomEdge> createGraph(String[] edgesArray,
			int graphType) {
		//Trim edges
		List<String> tempList = new ArrayList<String>();
		for (String edge : edgesArray) {
			if(!edge.equals("")){
				tempList.add(edge);
			}
		}
		String[] tempStrings = new String[tempList.size()];
		tempStrings= tempList.toArray(tempStrings);
		edgesArray = tempStrings;
		
		Graph<Vertex, CustomEdge> graph = null;

		switch (graphType) {
		case 0:
			graph = createGraph(edgesArray);
			break;
		case DIRECTED:
			graph = createDrirectedGraph(edgesArray);
			break;
		case ATTRIBUTED:
			graph = createGraphWithAttribute(edgesArray);
			break;
		case DIRECTED + ATTRIBUTED:
			graph = createDirectedGraphAttribute(edgesArray);
			break;
		case WEIGHTED:
			graph = createWeightedGraph(edgesArray);
			break;
		case DIRECTED + WEIGHTED:
			graph = createDirectedWeightedGraph(edgesArray);
			break;
		case ATTRIBUTED + WEIGHTED:
			graph = createWeightedGraphWithAttribute(edgesArray);
			;
			break;
		case DIRECTED + ATTRIBUTED + WEIGHTED:
			graph = createDirectedWeightedGraphWithAttribute(edgesArray);
			break;
		default:
			System.out.println("Cannot find type of Graph!");
		}
		System.out.println("createGraph done: ");
		return graph;
	}
	
	/*
	 * 
	 */
	private Graph<Vertex, CustomEdge> createDirectedWeightedGraphWithAttribute(
			String[] edgesArray) {
		Graph<Vertex, CustomEdge> graph = new DirectedWeightedPseudograph<>(
				CustomEdge.class);

		for (String StringEdge : edgesArray) {
			String trimedEdge = StringEdge.replaceAll("::", ",");
			trimedEdge = trimedEdge.replaceAll(" ", "");
			String[] verticesAndWeight = trimedEdge.split(",");
			String[] vertex1 = verticesAndWeight[0].split(":");
			String[] vertex2 = verticesAndWeight[1].split(":");
			
			Vertex v1 = new VertexImpl(vertex1[0],Integer.valueOf(vertex1[1]));
			Vertex v2 = new VertexImpl(vertex2[0],Integer.valueOf(vertex2[1]));
			graph.addVertex(v1);
			graph.addVertex(v2);
			CustomEdge egde = (CustomEdge) graph.addEdge(v1,v2);
			((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(egde, Double.valueOf(verticesAndWeight[2]));
		}
		
		System.out.println(edgesArray.length);
		return graph;
	}
	
	/*
	 * 
	 */
	//FIXME:sdkfh
	private Graph<Vertex, CustomEdge> createGraphWithAttribute(
			String[] edgesArray) {
		Graph<Vertex, CustomEdge> graph = new Pseudograph<>(CustomEdge.class);

		for (String edge : edgesArray) {
			String trimedEdge = edge.replaceAll(" ", "");
			String[] verticesAndWeight = trimedEdge.split(",");
			String[] vertex1 = verticesAndWeight[0].split(":");
			String[] vertex2 = verticesAndWeight[1].split(":");
			
			Vertex v1 = new VertexImpl(vertex1[0],Integer.valueOf(vertex1[1]));
			Vertex v2 = new VertexImpl(vertex2[0],Integer.valueOf(vertex2[1]));
			graph.addVertex(v2);
			graph.addEdge(v1, v2);
		}
		
		System.out.println(edgesArray.length);
		return graph;
	}

	private Graph<Vertex, CustomEdge> createDirectedGraphAttribute(
			String[] edgesArray) {

		Graph<Vertex, CustomEdge> graph = new DirectedPseudograph<>(
				CustomEdge.class);

		for (String edge : edgesArray) {
			String trimedEdge = edge.replaceAll(" ", "");
			String[] verticesAndWeight = trimedEdge.split(",");
			String[] vertex1 = verticesAndWeight[0].split(":");
			String[] vertex2 = verticesAndWeight[1].split(":");
			
			Vertex v1 = new VertexImpl(vertex1[0],Integer.valueOf(vertex1[1]));
			Vertex v2 = new VertexImpl(vertex2[0],Integer.valueOf(vertex2[1]));
			graph.addVertex(v1);
			graph.addVertex(v2);
			graph.addEdge(v1, v2);
		}

		System.out.println(edgesArray.length);
		return graph;
	}

	private Graph<Vertex, CustomEdge> createWeightedGraphWithAttribute(
			String[] edgesArray) {
		Graph<Vertex, CustomEdge> graph = new WeightedPseudograph<>(
				CustomEdge.class);

		for (String StringEdge : edgesArray) {
			String trimedEdge = StringEdge.replaceAll("::", ",");
			trimedEdge = trimedEdge.replaceAll(" ", "");
			String[] verticesAndWeight = trimedEdge.split(",");
			String[] vertex1 = verticesAndWeight[0].split(":");
			String[] vertex2 = verticesAndWeight[1].split(":");
			
			Vertex v1 = new VertexImpl(vertex1[0],Integer.valueOf(vertex1[1]));
			Vertex v2 = new VertexImpl(vertex2[0],Integer.valueOf(vertex2[1]));
			graph.addVertex(v1);
			graph.addVertex(v2);
			CustomEdge egde = (CustomEdge) graph.addEdge(v1,v2);
			((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(egde, Double.valueOf(verticesAndWeight[2]));
		}

//		System.out.println(edgesArray.length);
		return graph;
	}

	private Graph<Vertex, CustomEdge> createDirectedWeightedGraph(
			String[] edgesArray) {
		Graph<Vertex, CustomEdge> graph = new DirectedWeightedPseudograph<>(
				CustomEdge.class);

		for (String StringEdge : edgesArray) {
			String trimedEdge = StringEdge.replaceAll("::", ",");
			trimedEdge = trimedEdge.replaceAll(" ", "");
			String[] verticesAndWeight = trimedEdge.split(",");
		
			Vertex v1 = new VertexImpl(verticesAndWeight[0]);
			Vertex v2 = new VertexImpl(verticesAndWeight[1]);
			graph.addVertex(v1);
			graph.addVertex(v2);
			CustomEdge egde = (CustomEdge) graph.addEdge(v1,v2);
			((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(egde, Double.valueOf(verticesAndWeight[2]));
		}

		System.out.println(edgesArray.length);
		return graph;
	}

	private Graph<Vertex, CustomEdge> createWeightedGraph(
			String[] edgesArray) {
		Graph<Vertex, CustomEdge> graph = new WeightedPseudograph<>(
				CustomEdge.class);

		for (String StringEdge : edgesArray) {
			String trimedEdge = StringEdge.replaceAll("::", ",");
			trimedEdge = trimedEdge.replaceAll(" ", "");
			String[] verticesAndWeight = trimedEdge.split(",");
			
			Vertex v1 = new VertexImpl(verticesAndWeight[0]);
			Vertex v2 = new VertexImpl(verticesAndWeight[1]);
			graph.addVertex(v1);
			graph.addVertex(v2);
			CustomEdge egde = (CustomEdge) graph.addEdge(v1,v2);
			((AbstractBaseGraph<Vertex, CustomEdge>) graph).setEdgeWeight(egde, Double.valueOf(verticesAndWeight[2]));
		}

		System.out.println(edgesArray.length);
		return graph;
	}

	private Graph<Vertex, CustomEdge> createDrirectedGraph(
			String[] edgesArray) {

		Graph<Vertex, CustomEdge> graph = new DirectedPseudograph<>(
				CustomEdge.class);
		System.out.println(edgesArray.length);
		for (String edge : edgesArray) {
			String[] verticesOfEdge = edge.split(",");
			System.out.println(verticesOfEdge.length);
			if(verticesOfEdge.length < 2){
				Vertex v = new VertexImpl(verticesOfEdge[0].replaceAll(" ", ""));
				graph.addVertex(v);
				continue;
			}
			Vertex v1 = new VertexImpl(verticesOfEdge[0].replaceAll(" ", ""));
			Vertex v2 = new VertexImpl(verticesOfEdge[1].replaceAll(" ", ""));
			graph.addVertex(v1);
			graph.addVertex(v2);
			graph.addEdge(v1, v2);
		}

		
		return graph;
	}

	private Graph<Vertex, CustomEdge> createGraph(String[] edgesArray) {
		//pseudograph erlaubt multigraph und selfloops
		Graph<Vertex, CustomEdge> graph = new Pseudograph<>(CustomEdge.class); 

		for (String edge : edgesArray) {
			String[] verticesOfEdge = edge.split(",");
			
			if(verticesOfEdge.length < 2){
				Vertex v = new VertexImpl(verticesOfEdge[0].replaceAll(" ", ""));
				graph.addVertex(v);
				continue;
			}
			
			Vertex v1 = new VertexImpl(verticesOfEdge[0].replaceAll(" ", ""));
			Vertex v2 = new VertexImpl(verticesOfEdge[1].replaceAll(" ", ""));
			
			graph.addVertex(v1);
			graph.addVertex(v2);
			graph.addEdge(v1, v2);
		}
		System.out.println(edgesArray.length);
		return graph;
	}


	private int chooseGraphType(String header) {
		
		int graphType = 0;
		if(header == "" || header.charAt(0) != '#')return 0;
		
		String trimedheader = header.replaceAll(" ", "");
		String[] headerArr = trimedheader.split("#");
		
		for (String string : headerArr) {
			if (string.equals("directed")) {
				graphType += DIRECTED;
			} else if (string.equals("attributed")) {
				graphType += ATTRIBUTED;
			} else if (string.equals("weighted")) {
				graphType += WEIGHTED;
			}
		}

		return graphType;
	}
	
	public Graph<Vertex, CustomEdge> getGraph() {
		return _graph;
	}
}
