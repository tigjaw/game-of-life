package com.iridiumflair.sim.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iridiumflair.sim.SimStrings;
import com.iridiumflair.sim.control.SimController;

public class MainView {
	private SimController controller;
	private JFrame frame;
	private JPanel mainPanel, controlPanel;
	private JButton playButton;
	private JLabel widthLabel, heightLabel;
	private JTextArea widthField, heightField;
	private JTextArea intervalField;
	private CanvasPanel canvas;

	public MainView(JFrame jFrame, SimController controller) {
		frame = new JFrame(SimStrings.TITLE);
		this.controller = controller;
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		manageUI();
		createComponents();
		addComponents();
		addActions();
		showFrame();
	}

	private void manageUI() {
		// set look and feel
		try {
			UIManager.setLookAndFeel(SimStrings.LF_WINDOWS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Turn off metal's use of bold fonts
		// UIManager.put("swing.boldMetal", Boolean.FALSE);
	}

	private void createComponents() {
		// setup panels
		mainPanel = new JPanel(new BorderLayout());
		controlPanel = new JPanel();
		canvas = new CanvasPanel(500, 500);
		// setup buttons and labels
		playButton = new JButton("play");
		widthLabel = new JLabel("width: ");
		widthField = new NumberField("100");
		heightLabel = new JLabel("height: ");
		heightField = new NumberField("100");
	}

	private void addComponents() {
		controlPanel.add(playButton);
		controlPanel.add(widthLabel);
		controlPanel.add(widthField);
		controlPanel.add(heightLabel);
		controlPanel.add(heightField);

		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		mainPanel.add(canvas, BorderLayout.CENTER);
	}

	private void addActions() {}

	private void showFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);

		frame.setLocationRelativeTo(null);
		repack();
		frame.setVisible(true);
	}

	private void repack() {
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

}