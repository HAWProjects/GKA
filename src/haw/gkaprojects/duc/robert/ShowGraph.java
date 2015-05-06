package haw.gkaprojects.duc.robert;

import haw.gkaprojects.duc.robert.graph.CustomEdge;
import haw.gkaprojects.duc.robert.graph.Vertex;
import haw.gkaprojects.duc.robert.graph.VertexImpl;
import haw.gkaprojects.duc.robert.guiPopUps.ErrorPopUp;
import haw.gkaprojects.duc.robert.guiPopUps.ResultPopUp;
import haw.gkaprojects.duc.robert.searchingAlgorithm.AStarShortestPath;
import haw.gkaprojects.duc.robert.searchingAlgorithm.BreadthFirstSearch;
import haw.gkaprojects.duc.robert.searchingAlgorithm.OwnDijkstra;
import haw.gkaprojects.duc.robert.searchingAlgorithm.ShortestPathOfDijkstras;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.MenuItem;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jgraph.JGraph;
import org.jgraph.graph.CellViewFactory;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphSelectionModel;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.graph.JGraphSimpleLayout;
import com.jgraph.layout.hierarchical.JGraphLongestPathLayering;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.view.mxGraph;
/**
 * 
 * @author Robert
 *
 */
public class ShowGraph
{

	JFrame _frame;
	FileReader _reader;
	
	JPanel content;
	JScrollPane scrPane;

	Graph<Vertex, CustomEdge> _jGraphT;
	JGraph _jgraph;

	public ShowGraph()
	{
		_frame = new JFrame();
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_frame.setBounds(0, 0, 1200, 800);
		_frame.setLocationRelativeTo(null);
		_frame.getContentPane().setLayout(new BorderLayout());
		
		

		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Menue");
		JMenuItem miOpenFile = new JMenuItem("Open File");
		JMenu menuAl = new JMenu("Algrorithmus");

		JMenu bfs = new JMenu("bfs");

		JPanel searchPanel1 = new JPanel();
		JPanel searchPanel2 = new JPanel();
		JPanel searchPanel3 = new JPanel();
		searchPanel1.setLayout(new FlowLayout());

		JButton buttonFind1 = new JButton("Search");
		buttonFind1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Vertex[] vArr = getSelectedVertex();
				resultPopUp(BreadthFirstSearch.searchForTheShortestPath(
						_jGraphT, vArr[0], vArr[1]), 0);
			}
		});
		JButton buttonFind2 = new JButton("Search");
		buttonFind2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Vertex[] vArr = getSelectedVertex();

				ShortestPathOfDijkstras sPoD = new ShortestPathOfDijkstras(
						_jGraphT, vArr[0], vArr[1]);
				resultPopUp(sPoD.getShortestPath(), sPoD.getZugriffCounter());

//				 OwnDijkstra od = new OwnDijkstra(_jGraphT, vArr[0], vArr[1]);
//				 resultPopUp(od.getShortestPathVertexList(),0);
			}
		});
		JButton buttonfindAStern = new JButton("Search");
		buttonfindAStern.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Vertex[] vArr = getSelectedVertex();
				AStarShortestPath aStar = new AStarShortestPath(_jGraphT,
						vArr[0], vArr[1]);
				resultPopUp(aStar.getShortestPath(), 0);
			}
		});

		searchPanel1.add(buttonFind1);
		searchPanel2.add(buttonFind2);
		searchPanel3.add(buttonfindAStern);

		JMenu aStern = new JMenu("AStern");
		JMenu dijkstra = new JMenu("Dijkstra");
		bfs.add(searchPanel1);
		dijkstra.add(searchPanel2);
		aStern.add(searchPanel3);

		menuAl.add(bfs);
		menuAl.add(dijkstra);
		menuAl.add(aStern);

		miOpenFile.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				openFilePopUp();
			}
		});

		JMenu miSaveFile = new JMenu("Save File");
		JPanel saveMenu = new JPanel();
		saveMenu.setLayout(new FlowLayout());
		JLabel nameOfSave = new JLabel("Filename: ");
		JTextField getSaveFileName = new JTextField(12);
		JButton saveFile = new JButton("Save Graph");
		saveFile.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GraphFileSaver.saveGraphToFile("../GKA/result/"
						+ getSaveFileName.getText() + ".graph", _jGraphT);
			}
		});

		saveMenu.add(nameOfSave);
		saveMenu.add(getSaveFileName);
		saveMenu.add(saveFile);
		miSaveFile.add(saveMenu);

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

		JMenu gG = new JMenu("Graph Generator");
		JMenu createGraph = new JMenu("Open Graph Generator");
		createGraph.add(createGraphGenerator());
		gG.add(createGraph);

		mb.add(menu);
		mb.add(menuAl);
		mb.add(gG);
		
		content = new JPanel();
		scrPane = new JScrollPane(content);
	
		scrPane.setPreferredSize(new Dimension(1200,800));
//		scrPane.setVisible(true);
		_frame.getContentPane().add(scrPane, BorderLayout.CENTER);

		_frame.setJMenuBar(mb);
		
		_frame.pack();
		_frame.setVisible(true);

	}

	protected Vertex[] getSelectedVertex()
	{
		Vertex[] arr = new Vertex[2];
		GraphSelectionModel selectionModel = _jgraph.getSelectionModel();
		if (selectionModel.getSelectionCount() == 2)
		{
			Object[] e = selectionModel.getSelectionCells();
			arr[0] = (VertexImpl) ((DefaultGraphCell) e[0]).getUserObject();
			arr[1] = (VertexImpl) ((DefaultGraphCell) e[1]).getUserObject();

		}
		else
		{	new ErrorPopUp("Not enough Vertex selected!");
			throw new IllegalArgumentException("Not enough Vertex selected!");
		}
		return arr;
	}

	/*
	 * creates the PopUp wich displays the shortest path
	 * 
	 * @param searchForTheShortestPath: a List containing the Vertex of the
	 * shortest path
	 */
	private void resultPopUp(List<Vertex> searchForTheShortestPath, int counter)
	{
		new ResultPopUp(searchForTheShortestPath, counter);
	}

	public void setGraph(JGraph graph)
	{

		content.add(graph);
//		scrPane.setViewportView(graph);
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

		// final JLabel filenameLabel = new JLabel(" ");
		// filenameLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC,
		// 36));
		// contentPane.add(filenameLabel, BorderLayout.SOUTH);

		final JButton open = new JButton("Open File");
		contentPane.add(open, BorderLayout.SOUTH);

		JFileChooser fileChooser = new JFileChooser(
				"../GKA/res/files/bspGraphen/");
		fileChooser.setControlButtonsAreShown(false);
		contentPane.add(fileChooser, BorderLayout.CENTER);

		ActionListener actionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				JFileChooser theFileChooser = (JFileChooser) actionEvent
						.getSource();
				String command = actionEvent.getActionCommand();
				if (command.equals(JFileChooser.APPROVE_SELECTION))
				{
					_frame.getContentPane().removeAll(); // Test
					File selectedFile = theFileChooser.getSelectedFile();
					directoryLabel.setText(selectedFile.getParent());
//					_reader = new FileReader();
//					try
//					{
//						_reader.read(selectedFile);
//					}
//					catch (IOException e)
//					{
//						new ErrorPopUp("This File could not be loaded!");
//						e.printStackTrace();
//					}
//					CreateGraph createGraph = new CreateGraph(
//							_reader.getRowList());
//					_jGraphT = createGraph.getGraph();
					
					// ##################### FileReader 2 ###############################
					try
					{
						GraphMaker_withScanner gMwS = new GraphMaker_withScanner(
								selectedFile);
						_jGraphT = gMwS.getGraph();
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// ##################### FileReader 2 ###############################

					_jgraph = new JGraph(new JGraphModelAdapter<>(_jGraphT));
					_jgraph.setEnabled(_jGraphT != null);
					
					if(_jgraph != null){
						JGraphFacade facade = new JGraphFacade(_jgraph);
						JGraphLayout layout = new JGraphSimpleLayout(JGraphSimpleLayout.TYPE_CIRCLE);
						layout.run(facade);
						_jgraph.getGraphLayoutCache().edit(facade.createNestedMap(true, true));
					}
					
					setGraph(_jgraph);

					popup.dispose();

					// filenameLabel.setText(selectedFile.getName());
				}
				else if (command.equals(JFileChooser.CANCEL_SELECTION))
				{
					directoryLabel.setText(" ");
					// filenameLabel.setText(" ");
				}
			}
		};

		fileChooser.addActionListener(actionListener);

		popup.pack();
		popup.setVisible(true);
	}

	private JPanel createGraphGenerator()
	{
		JPanel content = new JPanel();
		content.setSize(200, 150);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		JPanel typeMenu = new JPanel();
		typeMenu.setLayout(new FlowLayout());
		JRadioButton weighted = new JRadioButton("Weighted", false);
		JRadioButton attributed = new JRadioButton("Attributed", false);
		JRadioButton directed = new JRadioButton("Directed", false);

		typeMenu.add(weighted);
		typeMenu.add(attributed);
		typeMenu.add(directed);

		JPanel graphmenu = new JPanel();
		JLabel generatingtype = new JLabel("Modus: ");
		String[] generatortyptxt =
		{ "Random" };
		JComboBox<String> generatortype = new JComboBox<String>(generatortyptxt);

		graphmenu.add(generatingtype);
		graphmenu.add(generatortype);

		JPanel vertexMenu = new JPanel();
		JLabel vertexNumber = new JLabel("Number of Vertex: ");
		JTextField vertexField = new JTextField(10);
		vertexMenu.add(vertexNumber);
		vertexMenu.add(vertexField);

		JPanel edgeMenu = new JPanel();
		JLabel edgeNumber = new JLabel("Number of Edges: ");
		JTextField edgeField = new JTextField(10);
		edgeMenu.add(edgeNumber);
		edgeMenu.add(edgeField);

		JButton buttonGenerate = new JButton("generate Graph");
		buttonGenerate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				generateGraph(weighted.isSelected(), attributed.isSelected(),
						directed.isSelected(), generatortype.getSelectedItem(),
						vertexField.getText(), edgeField.getText());
			}
		});

		JButton buttonInstantGraph = new JButton("Heuristik Graph");
		buttonInstantGraph.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Graph<Vertex, CustomEdge> GRAPH = UndirectedGraphContructor.constructGraph(Integer.parseInt(vertexField.getText()), Integer.parseInt(edgeField.getText()));
				GraphVisualiser.exportGraphToDotFile(GRAPH);
				
				JGraph jgraph = new JGraph(new JGraphModelAdapter<>(GRAPH));
				setGraph(jgraph);
			}
		});

		content.add(typeMenu);
		content.add(graphmenu);
		content.add(vertexMenu);
		content.add(edgeMenu);
		content.add(buttonGenerate);
		content.add(buttonInstantGraph);

		return content;
	}

	/**
	 * generates a new graph
	 * 
	 * @param weighted
	 * @param attributed
	 * @param directed
	 * @param selectedItem
	 * @param countVertex
	 * @param countEdge
	 */
	protected void generateGraph(boolean weighted, boolean attributed,
			boolean directed, Object selectedItem, String countVertex,
			String countEdge)
	{
		// TODO Auto-generated method stub
		int vertexCount = Integer.parseInt(countVertex);
		int edgeCount = Integer.parseInt(countEdge);
		ArrayList<String> vertexArr = new ArrayList<>();

		ArrayList<ArrayList<String>> rowlist = new ArrayList<>();
		ArrayList<String> columlistHeader = new ArrayList<>();

		if (weighted)
		{
			columlistHeader.add("weighted");
		}
		if (attributed)
		{
			columlistHeader.add("attributed");
		}
		if (directed)
		{
			columlistHeader.add("directed");
		}
		rowlist.add(columlistHeader);

		if (selectedItem.equals("Random"))
		{

			for (int i = 0; i < vertexCount; i++)
			{
				vertexArr.add("Vertex: " + (i + 1));
			}
			for (int j = 0; j < edgeCount; j++)
			{
				int first = new Random().nextInt(vertexCount);
				int second = new Random().nextInt(vertexCount);
				ArrayList<String> columList = new ArrayList<>();
				if (attributed)
				{
					columList.add(vertexArr.get(first));
					columList.add("some Attribut");

					columList.add(vertexArr.get(second));
					columList.add("some Attribut");
				}
				else
				{
					columList.add(vertexArr.get(first));
					columList.add(vertexArr.get(second));
				}
				if (weighted)
				{
					Integer weight = new Random().nextInt(1000);
					columList.add(weight.toString());
				}
				rowlist.add(columList);
			}

			System.out.println(rowlist);
		}

		_frame.getContentPane().removeAll();
		CreateGraph createGraph = new CreateGraph((List)rowlist);
		_jGraphT = createGraph.getGraph();
		_jgraph = new JGraph(new JGraphModelAdapter<>(_jGraphT));
		
//		if(_jgraph != null){
//			
//			JGraphFacade facade = new JGraphFacade(_jgraph);
//			JGraphLayout layout = new JGraphSimpleLayout(JGraphSimpleLayout.TYPE_CIRCLE);
//			layout.run(facade);
//			_jgraph.getGraphLayoutCache().edit(facade.createNestedMap(true, true));
//		}
		setGraph(_jgraph);
		
	}

	/*
	 * updates the contentpane of the jframe
	 */
	private void update()
	{
//		content.repaint();
		_frame.getContentPane().repaint();
//		scrPane.repaint();
		_frame.setVisible(true);
	}
}
