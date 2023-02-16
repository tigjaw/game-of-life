package com.iridiumflair.sim.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.iridiumflair.sim.control.SimController;

public class OptionsDialog extends JDialog {
	private MainView mainView;
	private SimController controller;
	// options
	private JLabel widthLabel, heightLabel;
	private JTextArea widthField, heightField;
	private JLabel intervalLabel;
	private JTextArea intervalField;
	// rules
	// buttons
	private JButton acceptBtn, cancelBtn;
	
	public OptionsDialog(MainView mainView, String title) {
		super(mainView.getFrame(), title);
		this.mainView = mainView;
		this.controller = mainView.getController();
		createComponents();
		addComponents();
		addActions();
		display();
	}

	private void createComponents() {
		// width and height fields
		widthLabel = new JLabel("width: ");
		widthField = new NumberField(controller.getColumns());
		widthField.setToolTipText("width of board");
		heightLabel = new JLabel("height: ");
		heightField = new NumberField(controller.getRows());
		heightField.setToolTipText("height of field");
		// interval field
		intervalLabel = new JLabel("interval: ");
		intervalField = new NumberField(controller.getSimInterval());
		intervalField.setToolTipText("lower is faster");
		// rules fields
		// buttons
		acceptBtn = new JButton("accept");
		acceptBtn.setToolTipText("create simulation with these options");
		cancelBtn = new JButton("cancel");
		cancelBtn.setToolTipText("cancel simulation creation");
	}
	
	private void addComponents() {
		// TODO Auto-generated method stub
		
	}
	
	private void addActions() {
		acceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow(true);
			}
		});
		
		acceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow(false);
			}
		});
	}
	
	private void closeWindow(boolean accept) {
		if (accept) {
			
		}
		setVisible(false);
	}
	
	private void display() {
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	@Override 
	public Dimension getPreferredSize() {    
	  int maxWidth = 500;    
	  int maxHeight = 500;    
	  Dimension dim = super.getPreferredSize();    
	  if (dim.width > maxWidth) dim.width = maxWidth;    
	  if (dim.height > maxHeight) dim.height = maxHeight;    
	  return dim; 
	}
	
}