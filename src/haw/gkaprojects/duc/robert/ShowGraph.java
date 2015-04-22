package haw.gkaprojects.duc.robert;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.jgraph.JGraph;

public class ShowGraph
{
	
	JFrame _frame;
	
	public ShowGraph(){
		_frame = new JFrame();
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		JMenuItem miExit = new JMenuItem("Exit");
		
		
		menu.add(miOpenFile);
		menu.add(miSaveFile);
		menu.add(miExit);
		
		JMenu menuAl = new JMenu("Algrorithmus");
		
		JMenuItem bfs = new JMenuItem("bfs");
		
		menuAl.add(bfs);
		
		mb.add(menu);
		mb.add(menuAl);
		
		_frame.setJMenuBar(mb);		
		
		_frame.pack();
		_frame.setVisible(true);
	}
	
	public void setGraph(JGraph graph){
		_frame.getContentPane().add(graph);
	}
	
	private void openFilePopUp()
	{
		System.out.println("klicked");
		
	}
}
