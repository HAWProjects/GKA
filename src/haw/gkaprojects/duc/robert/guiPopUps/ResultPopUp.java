package haw.gkaprojects.duc.robert.guiPopUps;

import haw.gkaprojects.duc.robert.graph.Vertex;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ResultPopUp
{
	JDialog _dialog;

	public ResultPopUp()
	{
		this(new ArrayList());
	}

	public ResultPopUp(List<Vertex> shortestPathVertexList)
	{
		_dialog = new JDialog();
		_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		_dialog.setBounds(0, 0, 400, 200);
		_dialog.setLocationRelativeTo(null);
		_dialog.setLayout(new BoxLayout(_dialog.getContentPane(),
				BoxLayout.Y_AXIS));
		_dialog.add(new JLabel("Der kürzeste Weg lautet: "));
		_dialog.add(createShortestpath(shortestPathVertexList));
		_dialog.add(createSteps(shortestPathVertexList));

		_dialog.setVisible(true);
	}

	private JLabel createSteps(List<Vertex> shortestPathVertexList)
	{
		JLabel l = new JLabel();
		l.setText("Step Count: "
				+ String.valueOf(shortestPathVertexList.size() - 1));
		return l;
	}

	private JTextArea createShortestpath(List<Vertex> shortestPathVertexList)
	{
		JTextArea ta = new JTextArea(300, 120);
		ta.setLineWrap(true);
		ta.setEditable(false);
		String s = "";
		for (Vertex v : shortestPathVertexList)
		{
			s = s + v.getLabel() + " -> ";
		}
		if (s.length() > 2)
		{
			s = s.substring(0, s.length() - 2);
			s = s.substring(0, s.length() - 2);
		}
		ta.setText(s);
		return ta;
	}
}
