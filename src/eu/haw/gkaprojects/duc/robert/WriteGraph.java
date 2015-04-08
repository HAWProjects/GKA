package eu.haw.gkaprojects.duc.robert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jgrapht.*;

public class WriteGraph
{

	public WriteGraph(String filename, ArrayList<String> list)
	{
		File f = new File("../GKAProjects/outputfiles/" + filename + ".txt");

		try
		{
			FileWriter writer = new FileWriter(f);
			BufferedWriter bf = new BufferedWriter(writer);
			for (int i = 0; i < list.size(); ++i)
			{
				bf.write(list.get(i));
				bf.newLine();
			}

			bf.close();
			writer.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public WriteGraph(Graph<Vertex, CustomEdge> graph, String filename)
	{
		File f = new File("../GKAProjects/outputfiles/" + filename + ".graph");
		ArrayList<CustomEdge> list = new ArrayList<CustomEdge>();
		list.addAll(graph.edgeSet());
		try
		{
			FileWriter writer = new FileWriter(f);
			BufferedWriter bf = new BufferedWriter(writer);

			for (int i = 0; i < list.size(); ++i)
			{
				bf.write(graph.getEdgeSource(list.get(i)) + " == "
						+ graph.getEdgeTarget(list.get(i)));
				bf.newLine();
			}

			bf.close();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public <V, E> WriteGraph(UndirectedGraph<V, E> graph, String name)
	{

	}
}