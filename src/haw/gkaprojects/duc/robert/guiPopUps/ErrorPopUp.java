package haw.gkaprojects.duc.robert.guiPopUps;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ErrorPopUp
{
	JDialog _dialog;
	
	public ErrorPopUp(){
		this("Error!");
	}
	
	public ErrorPopUp(String message){
		_dialog = new JDialog();
		_dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		_dialog.setBounds(0, 0, 400, 200);
		_dialog.setLocationRelativeTo(null);
		JTextArea ta = new JTextArea(message);
		ta.setLineWrap(true);
		_dialog.add(ta);
		_dialog.setVisible(true);
	}
}
