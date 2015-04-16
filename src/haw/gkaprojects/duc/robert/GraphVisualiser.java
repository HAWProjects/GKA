package haw.gkaprojects.duc.robert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.StringEdgeNameProvider;
import org.jgrapht.ext.StringNameProvider;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

public class GraphVisualiser {
	public static void exportGraphToDotFile(
			Graph<Vertex, CustomEdge> graph) {
		//Check whether the graph is Weighted
		boolean graphIsWeighted = graph instanceof WeightedGraph ;
		
		//Create a new Attribute Provider for Edges
		ComponentAttributeProvider<CustomEdge> EdgeWeightProvider = new ComponentAttributeProvider<CustomEdge>() {
			public Map<String, String> getComponentAttributes(
					CustomEdge e) {
				
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("label", Double.toString(graph.getEdgeWeight(e)));
				map.put("weight", Double.toString(graph.getEdgeWeight(e)));
				map.put("color", e.getColor());
				return map;
			}
		};
		
		ComponentAttributeProvider<CustomEdge> EdgeAttributeWithoutWeightProvider = new ComponentAttributeProvider<CustomEdge>() {
			public Map<String, String> getComponentAttributes(
					CustomEdge e) {
				
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("color", e.getColor());
				
				return map;
			}
		};
		
		//Create a new Attribute Provider for Edges
		ComponentAttributeProvider<Vertex> VertexAttributeProvider = new ComponentAttributeProvider<Vertex>() {

			@Override
			public Map<String, String> getComponentAttributes(Vertex v) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("distortion", Integer.toString(v.getAttribut()));
				map.put("color", v.getColor());
				return map;
			}
		};
		
		//Create DotExporter which matches type of the Graph
		DOTExporter<Vertex, CustomEdge> exporter1 = null;
		if(graphIsWeighted){
			 exporter1 = new DOTExporter<>(
				new IntegerNameProvider<>(), new StringNameProvider<>(),
				null, VertexAttributeProvider, EdgeWeightProvider);
		}else {
			
			exporter1 = new DOTExporter<>(
					new IntegerNameProvider<>(), new StringNameProvider<>(),
					new StringEdgeNameProvider<>(), VertexAttributeProvider, EdgeAttributeWithoutWeightProvider);
		}
//		exporter1 = new DOTExporter<Vertex, CustomEdge>();
		//File Directory
		String targetDirectory = "../GKAProjects_01/result/";
		
		//Export Dot File
		new File(targetDirectory).mkdirs();
		try {

			exporter1.export(
					new FileWriter(targetDirectory + "result.gv"), graph);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
