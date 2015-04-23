package haw.gkaprojects.duc.robert;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.hierarchical.JGraphLongestPathLayering;

public class ShowGraph
{
	
	JFrame _frame;
	FileReader _reader;

	Graph<Vertex, CustomEdge> _jGraphT;
	
	public ShowGraph(){
		_frame = new JFrame();
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_frame.setBounds(0, 0, 1200, 800);
		_frame.setLocationRelativeTo(null);
		
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Menue");
		JMenuItem miOpenFile = new JMenuItem("Open File");
		miOpenFile.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			openFilePopUp();
			}
		});
		JMenuItem miSaveFile = new JMenuItem("Save File");
		miSaveFile.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GraphFileSaver.saveGraphToFile( "G:\\Git\\GKA\\result", _jGraphT);
			}


		});
		
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_frame.dispose();
			}
		});
		
		
		menu.add(miOpenFile);
		menu.add(miSaveFile);
		menu.add(miExit);
		
		JMenu menuAl = new JMenu("Algrorithmus");
		
		JMenu bfs = new JMenu("bfs");
		
		String[] a = {"Lade einen Graphen!"};
		JComboBox<String> box = new JComboBox<>(a);
		
		bfs.add(box);
		
		JMenu fWay = new JMenu("Find Way");
		
		menuAl.add(bfs);
		
		mb.add(menu);
		mb.add(menuAl);
		mb.add(fWay);
		
		_frame.setJMenuBar(mb);		
		
//		_frame.pack();
		_frame.setVisible(true);
		

	}
	
	private String[] vertexSetToString()
	{
		String[] a = new String[_jGraphT.vertexSet().size()];
		
		for( Vertex v :_jGraphT.vertexSet())
		{
			v.toString();
		}
		return a;
	}

	public void setGraph(JGraph graph){
		_frame.getContentPane().add(graph);
		update();
		
	}
	
	private void openFilePopUp()
	{
		System.out.println("klicked");
		JFrame popup = new JFrame("Choose File");
		popup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Container contentPane = popup.getContentPane();
		
		
	    final JLabel directoryLabel = new JLabel(" ");
	    directoryLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 36));
	    contentPane.add(directoryLabel, BorderLayout.NORTH);

//	    final JLabel filenameLabel = new JLabel(" ");
//	    filenameLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 36));
//	    contentPane.add(filenameLabel, BorderLayout.SOUTH);
	    
	    final JButton open = new JButton("Open File");
	    contentPane.add(open, BorderLayout.SOUTH);

	    JFileChooser fileChooser = new JFileChooser("../GKA/res/files/bspGraphen/");
	    fileChooser.setControlButtonsAreShown(false);
	    contentPane.add(fileChooser, BorderLayout.CENTER);

	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        JFileChooser theFileChooser = (JFileChooser) actionEvent
	            .getSource();
	        String command = actionEvent.getActionCommand();
	        if (command.equals(JFileChooser.APPROVE_SELECTION)) {
	        	_frame.getContentPane().removeAll();    //Test
	          File selectedFile = theFileChooser.getSelectedFile();
	          directoryLabel.setText(selectedFile.getParent());
	          _reader = new FileReader();
	          try
			{
				_reader.read(selectedFile);
			}
			catch (IOException e)
			{
//				_frame.getContentPane().add("This File could not be loaded!");
				e.printStackTrace();
			}
	  		CreateGraph createGraph = new CreateGraph(_reader.getRowList());
			_jGraphT = createGraph.getGraph();
			JGraph _jgraph = new JGraph(new JGraphModelAdapter<>(_jGraphT));
	
			setGraph(_jgraph);
			
			popup.dispose();
			
	          System.out.println(selectedFile);

//	         filenameLabel.setText(selectedFile.getName());
	        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
	          directoryLabel.setText(" ");
	      
//	          filenameLabel.setText(" ");
	        }
	      }
	    };
	    
	    
	    fileChooser.addActionListener(actionListener);
	   

	    popup.pack();
	    popup.setVisible(true);
	}
	
	/*
	 * updates the contentpane of the jframe
	 */
	private void update()
	{
	_frame.getContentPane().repaint();
	_frame.setVisible(true);
	System.out.println("tata");
		
	}
}
