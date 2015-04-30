package haw.gkaprojects.duc.robert;

import haw.gkaprojects.duc.robert.graph.Vertex;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPopUp
{
	JDialog _dialog;
	
	public ResultPopUp(){		
		this(new ArrayList());
	}
	
	public ResultPopUp(List<Vertex> shortestPathVertexList){
		_dialog = new JDialog();
		_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		_dialog.setBounds(0, 0, 400, 200);
		_dialog.setLocationRelativeTo(null);
		_dialog.setLayout(new FlowLayout());
		_dialog.add(new JLabel("Der kürzeste Weg lautet: "));
		_dialog.add(createShortestpath(shortestPathVertexList));
		_dialog.add(createSteps(shortestPathVertexList));
		
		_dialog.setVisible(true);
	}
	
	private JLabel createSteps(List<Vertex> shortestPathVertexList)
	{	JLabel l = new JLabel();		
		l.setText("Step Count: " +String.valueOf(shortestPathVertexList.size()));
		return l;
	}

	private JLabel createShortestpath(List<Vertex> shortestPathVertexList)
	{
		JLabel l = new JLabel();
		String s = "";
		for(Vertex v: shortestPathVertexList)
		{
			s = s + v.getLabel() + " -> ";
		}
		l.setText(s);
		return l;
	}
}
