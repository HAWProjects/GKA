package haw.gkaprojects.duc.robert;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

public class CreateGraph
{

	private boolean _attributed;
	private boolean _weighted;
	private boolean _directed;
	private boolean _undirected;

	private Graph<Vertex, CustomEdge> _graph;

	public CreateGraph(List<List<String>> rowList)
	{
		_attributed = false;
		_weighted = false;
		_directed = false;
		_undirected = false;

		_graph = new Pseudograph<>(CustomEdge.class);

		List<String> headerlist = rowList.get(0);
		checkGraphType(headerlist);
		rowList.remove(0);

		List<List<String>> graphList = rowList;

		System.out.println(rowList);

		createGraph(graphList);

	}

		/**
		 * 
		 * @param graphList
		 */
	private void createGraph(List<List<String>> graphList)
	{
		if (_directed && _weighted && _attributed)
		{
			_graph = new DirectedWeightedPseudograph<>(CustomEdge.class);
			addVerticAndEdges(graphList);
		}
		else if (_directed && _attributed && !_weighted)
		{
			_graph = new DirectedPseudograph<>(CustomEdge.class);
		}
		else if (_directed && _weighted && !_attributed)
		{
			_graph = new DirectedWeightedPseudograph<>(CustomEdge.class);
		}
		else if (_directed)
		{
			_graph = new DirectedPseudograph<>(CustomEdge.class);
			addVerticAndEdges(graphList);
		}
		else if (_undirected && _weighted && _attributed)
		{
			_graph = new WeightedPseudograph<>(CustomEdge.class);
		}
		else if (_undirected && _weighted)
		{
			_graph = new WeightedPseudograph<>(CustomEdge.class);
		}
		else if (_undirected)
		{
			_graph = new Pseudograph<>(CustomEdge.class);
		}

	}

	private void addVerticAndEdges(List<List<String>> graphList)
	{


		for (List<String> l : graphList)
		{			
			Vertex v1 = new VertexImpl(l.get(0));
			Vertex v2 = new VertexImpl(l.get(1));
			_graph.addVertex(v1);
			_graph.addVertex(v2);
			_graph.addEdge(v1, v2);
		}
		System.out.println(_graph.edgeSet());

	}

	/**
	 * defines graph-type based on the given headerlist
	 * 
	 * @param headerlist
	 *            of the given Graph
	 */
	private void checkGraphType(List<String> headerlist)
	{
		if (headerlist.contains("attributed"))
		{
			_attributed = true;
		}
		if (headerlist.contains("weighted"))
		{
			_weighted = true;
		}

		if (headerlist.contains("directed"))
		{
			_directed = true;
			_undirected = false;
		}
		else
		{
			_undirected = true;
			_directed = false;
		}
		System.out.println(_attributed + " " + _weighted + " " + _directed);

	}
	
	/**
	 * returns the createt graph
	 * @return Graph<Vertex, CustomEdge> Graph
	 */
	public Graph<Vertex, CustomEdge> getGraph()
	{

		return _graph;
	}
}
