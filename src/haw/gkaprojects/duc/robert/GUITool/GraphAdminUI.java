package haw.gkaprojects.duc.robert.GUITool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class GraphAdminUI {
	
	private static final String TITLE = "Breadth-First-Search";
	private static final String BENUTZUNG = "Choose one of these graph in graph box! \n"
			+ "Then choose the Source and Target \n"
			+ "And I will find shortest Path for you ;) ";
	
	//main Frame of the UI
	JFrame _frame;
	JLabel _choosegraphLabel;
	JComboBox<String> _choosegraphCombobox;
	JComboBox<String> _chooseSourceCombobox;
	JComboBox<String> _chooseTargetCombobox;
	JButton _findShortestPathButton;
	JTextArea _shortestPathtextArea;
	
	public GraphAdminUI(String[] graphsNames, String[] VertexNames) {
		_frame = new JFrame(TITLE);
		_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(new BorderLayout());
		
		JComponent chooseGraph = createChooseGraphComponent(graphsNames);
		JComponent chooseSourceAndTaget = createChooseSourceAndTagetComponent(VertexNames);
		JComponent findShortestPath = createFindShortestPahtComponent();
		
		_frame.getContentPane().add(chooseGraph, BorderLayout.NORTH);
		_frame.getContentPane().add(chooseSourceAndTaget, BorderLayout.CENTER);
		_frame.getContentPane().add(findShortestPath, BorderLayout.SOUTH);
		
		drawWindow();
	}

	
	private JComponent createFindShortestPahtComponent() {
		
		JPanel findShortestPathPanel = new JPanel(new BorderLayout());
		JPanel buttonpanel = new JPanel(new FlowLayout());
		
		_findShortestPathButton = new JButton("Find Path");
		buttonpanel.add(_findShortestPathButton);
		_shortestPathtextArea = new JTextArea(BENUTZUNG);
		_shortestPathtextArea.setEditable(false);
		_shortestPathtextArea.setBackground(Color.WHITE);
		
		
		findShortestPathPanel.add(buttonpanel, BorderLayout.NORTH);
		findShortestPathPanel.add(_shortestPathtextArea, BorderLayout.CENTER);
		return findShortestPathPanel;
	}

	private JComponent createChooseSourceAndTagetComponent(String[] vertexNames) {
		
		JPanel chooseSourceAndTargetPanel = new JPanel(new GridLayout(1,2));
		
		_chooseSourceCombobox = new JComboBox<String>(new DefaultComboBoxModel<String>(vertexNames));
		_chooseSourceCombobox.setBorder(new TitledBorder("From"));
		_chooseTargetCombobox = new JComboBox<String>(new DefaultComboBoxModel<String>(vertexNames));
		_chooseTargetCombobox.setBorder(new TitledBorder("To"));
		
		chooseSourceAndTargetPanel.add(_chooseSourceCombobox);
		chooseSourceAndTargetPanel.add(_chooseTargetCombobox);
		
		return chooseSourceAndTargetPanel;
	}

	private JComponent createChooseGraphComponent(String[] graphsNames) {
		
		JPanel chooseGraphPanel = new JPanel(new GridLayout(2,1));
		
		_choosegraphLabel = new JLabel("CHOOSE ONE GRAPH");
		_choosegraphLabel.setHorizontalAlignment(JLabel.CENTER);
		Font font = _choosegraphLabel.getFont().deriveFont(20);
		_choosegraphLabel.setFont(font);
		
		_choosegraphCombobox = new JComboBox<>();
		_choosegraphCombobox.addItem("Please choose a graph...");
		for (String item : graphsNames) {
			_choosegraphCombobox.addItem(item);
		}
		_choosegraphCombobox.setBorder(new TitledBorder("graph"));
		
		chooseGraphPanel.add(_choosegraphLabel);
		chooseGraphPanel.add(_choosegraphCombobox);
		
		return chooseGraphPanel;
	}
	
	public void drawWindow() {
		_frame.setSize(new Dimension(370, 300));;
//		_frame.setResizable(false);
		_frame.setVisible(true);
	}
	
	public void closeWindow() {
		_frame.dispose();
	}
	
	public JComboBox<String> getGraphBox(){
		return _choosegraphCombobox;
	}
	
	public JComboBox<String> getTargetBox(){
		return _chooseTargetCombobox;
	}
	
	public JComboBox<String> getSourceBox(){
		return _chooseSourceCombobox;
	}
	
	public JButton getFindButton() {
		return _findShortestPathButton;
	}

	public JTextArea getTextArea() {
		return _shortestPathtextArea;
	}

	public void resetSoureBox(String[] vertexNames) {
		_chooseSourceCombobox.setModel(new DefaultComboBoxModel<String>(vertexNames));
	}


	public void resetTargetBox(String[] vertexNames) {
		_chooseTargetCombobox.setModel(new DefaultComboBoxModel<String>(vertexNames));
	}

}
