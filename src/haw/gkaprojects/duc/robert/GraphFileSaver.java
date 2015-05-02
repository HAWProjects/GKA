package haw.gkaprojects.duc.robert;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;

/**
 * This class saves a new graph-file based on the displayed graph. The the graph will be saved in the result folder named by the given path.
 * @author Duc, Robert
 *
 */
public class GraphFileSaver {
	public static void saveGraphToFile(String path, Graph<Vertex,CustomEdge> graph){
		
		
		try {
			OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(path));
			String graphContent = createGraphContent(graph);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(graphContent);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String createGraphContent(Graph<Vertex, CustomEdge> graph) {
		String graphContent = "";
		String header = "";
		
		boolean graphIsAttributed = false;
		boolean graphIsWeighted = false;
		if (graph instanceof WeightedGraph) {
			graphIsWeighted = true;
		}
		
		for (Vertex vertex : graph.vertexSet()) {
			if (vertex.isAttributed()) {
				graphIsAttributed = true;
				break;
			}
		}
		
		//make header
		if(graph instanceof DirectedGraph){
			header+="#directed ";
		}
		
		if (graphIsAttributed) {
			header += "#attributed ";
		}
		
		if (graphIsWeighted) {
			header +="#weighted";
		}
		
		if (!header.isEmpty()) {
			header+="\n";
		}
		
		//add edges		
		for (CustomEdge edge : graph.edgeSet()) {
			Vertex v1 = graph.getEdgeSource(edge);
			Vertex v2 = graph.getEdgeTarget(edge);
			String v1_label = v1.getLabel();
			String v1_attr = v1.isAttributed()? ":"+v1.getAttribut() : "";
			String v2_label = v2.getLabel();
			String v2_attr = v2.isAttributed()? ":"+v2.getAttribut() : "";
			String weight = graphIsWeighted? "::"+graph.getEdgeWeight(edge): "";
			
			graphContent += v1_label+v1_attr+","+v2_label+v2_attr+weight+"\n";
		}
		
		//add vertices without edges
		Set<Vertex> setofVertices = new HashSet<Vertex>(graph.vertexSet());
		for (CustomEdge edge : graph.edgeSet()) {
			setofVertices.remove(graph.getEdgeSource(edge));
			setofVertices.remove(graph.getEdgeTarget(edge));
		}
		
		for (Vertex vertex : setofVertices) {
			String v_label = vertex.getLabel();
			String v_attr = vertex.isAttributed()? ""+vertex.getAttribut():"";
			
			graphContent += v_label+v_attr+"\n";
		}
		
		return header+graphContent;
	}
}
