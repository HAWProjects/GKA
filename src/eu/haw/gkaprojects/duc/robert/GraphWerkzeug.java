package eu.haw.gkaprojects.duc.robert;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;

public class GraphWerkzeug {
	
	private GraphWerkzeugUI _ui;
	
	private Graph<Vertex, CustomEdge> _graph;
	
	public GraphWerkzeug(String pathToFolder) {
		
		String[] fileNames = readFilesInFolder(pathToFolder);
		
		_ui = new GraphWerkzeugUI(fileNames, null);
		
		registiereUIAktionen();
	}
	
	
	private void registiereUIAktionen() {
		_ui.getGraphBox().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String path = e.getItem().toString();
			}
		});
	}


	//http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
	private String[] readFilesInFolder(String pathToFolder) {
		final File folder = new File(pathToFolder);
		
		List<String> listOfFile = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if(!fileEntry.isDirectory()){
				listOfFile.add(fileEntry.getName());
			}
		}
		
		return (String[]) listOfFile.toArray();
	}
}
