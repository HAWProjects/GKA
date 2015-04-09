package eu.haw.gkaprojects.duc.robert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

public class GraphWerkzeug {

	private GraphWerkzeugUI _ui;

	private Graph<Vertex, CustomEdge> _graph;

	private String _pathToFolder;

	public GraphWerkzeug(String pathToFolder) {

		_pathToFolder = pathToFolder;

		String[] fileNames = readFilesInFolder(pathToFolder);
		// String[] vertexNames = new String[0];
		_ui = new GraphWerkzeugUI(fileNames, new String[0]);

		registiereUIAktionen();
	}

	private void registiereUIAktionen() {
		_ui.getGraphBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String graphName = (String) _ui.getGraphBox().getSelectedItem();
				reloadGraph(graphName);
				reloadVertexName();
			}
		});

		_ui.getFindButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				GraphVisualizer.resetGraph(_graph);
				performFindshortestPath();
			}
		});
	}

	private void reloadVertexName() {

		String graphName = (String) _ui.getGraphBox().getSelectedItem();
		String[] vertexNames = {};
		if (!graphName.equals("Please choose a graph...")) {

			Set<Vertex> setOfVertices = _graph.vertexSet();
			List<String> namelist = new ArrayList<String>();

			for (Vertex vertex : setOfVertices) {
				namelist.add(vertex.getLabel());
			}

			vertexNames = namelist
					.toArray(new String[namelist.size()]);

			Arrays.sort(vertexNames, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
		}
		_ui.resetSoureBox(vertexNames);
		_ui.resetTargetBox(vertexNames);

	}

	private void performFindshortestPath() {
		String sourceName = (String) _ui.getSourceBox().getSelectedItem();
		String tarName = (String) _ui.getTargetBox().getSelectedItem();
		String graphName = (String) _ui.getGraphBox().getSelectedItem();

		if (!graphName.equals("Please choose a graph...")) {

			String pathToGraph = _pathToFolder + graphName;
			_graph = (new GraphReader(pathToGraph)).getGraph();

			BreadthFirstSearch.searchForTheShortestPath(_graph, new VertexImpl(
					sourceName), new VertexImpl(tarName));
			GraphVisualizer.exportGraphToDotFile(_graph);
		}
	}

	private void reloadGraph(String graphName) {
		if (!graphName.equals("Please choose a graph...")) {

			String pathToGraph = _pathToFolder + graphName;
			_graph = (new GraphReader(pathToGraph)).getGraph();
			GraphVisualizer.exportGraphToDotFile(_graph);
		}
	}

	// http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
	private String[] readFilesInFolder(String pathToFolder) {
		final File folder = new File(pathToFolder);

		List<String> listOfFile = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()
					&& fileEntry.getName().charAt(0) != '.') {
				listOfFile.add(fileEntry.getName());
			}
		}
		String[] fileNames = new String[listOfFile.size()];

		return listOfFile.toArray(fileNames);
	}
}
