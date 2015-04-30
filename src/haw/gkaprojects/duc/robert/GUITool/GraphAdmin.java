package haw.gkaprojects.duc.robert.GUITool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

import haw.gkaprojects.duc.robert.GraphMaker;
import haw.gkaprojects.duc.robert.GraphVisualiser;
import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;
import haw.gkaprojects.duc.robert.searchingAlgorithm.BreadthFirstSearch;

public class GraphAdmin {

	private GraphAdminUI _ui;

	private Graph<Vertex, CustomEdge> _graph;

	private String _pathToFolder;

	public GraphAdmin(String pathToFolder) {

		_pathToFolder = pathToFolder;

		String[] fileNames = readFilesInFolder(pathToFolder);
		// String[] vertexNames = new String[0];
		_ui = new GraphAdminUI(fileNames, new String[0]);

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
				// GraphVisualizer.resetGraph(_graph);
				performFindshortestPath();
			}
		});
	}

	private void refreshTextArea(List<Vertex> shortestPath) {
		int countOfEdges = shortestPath.size() - 1;
		// System.out.println(shortestPath.size());
		String pathInString = "";
		if (shortestPath.size() > 1) {
			pathInString = "For the way from \""
					+ shortestPath.get(0).getLabel() + "\" " + "to \""
					+ shortestPath.get(countOfEdges) + "\" \n"
					+ "you have to go through at least " + countOfEdges + " Edge(s): \n\n";
			pathInString += shortestPath.get(0).getLabel();
			for (Vertex vertex : shortestPath) {
				if (vertex != shortestPath.get(0)) {
					pathInString += " -> " + vertex.getLabel();
				}
			}

			pathInString += "\n\n You're welcome! ;) ";
		} else if (shortestPath.size() == 1) {
			pathInString = "You are right there where you want to get to! \n"
					+ "There no need to go anywhere!";
		} else {
			pathInString = "Sorry I cannot help you this time :(\n"
					+ "There's no way to get from " 
					+ _ui.getSourceBox().getSelectedItem()
					+ " to "
					+ _ui.getTargetBox().getSelectedItem();
		}
		
		_ui.getTextArea().setText(pathInString);
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

			vertexNames = namelist.toArray(new String[namelist.size()]);

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
			_graph = (new GraphMaker(pathToGraph)).getGraph();

			List<Vertex> shortestPath = BreadthFirstSearch
					.searchForTheShortestPath(_graph,
							new VertexImpl(sourceName), new VertexImpl(tarName));
			GraphVisualiser.exportGraphToDotFile(_graph);
			refreshTextArea(shortestPath);
		} else {
			_ui.getTextArea().setText("Please choose a graph so I can tell you the shortest path!");
		}
	}

	private void reloadGraph(String graphName) {
		if (!graphName.equals("Please choose a graph...")) {

			String pathToGraph = _pathToFolder + graphName;
			_graph = (new GraphMaker(pathToGraph)).getGraph();
			GraphVisualiser.exportGraphToDotFile(_graph);
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
