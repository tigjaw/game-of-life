package com.iridiumflair.sim.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JLabel widthLabel, heightLabel;
	private JTextArea widthField, heightField;
	private JButton playBtn, slowDownBtn, speedUpBtn, newBtn;
	private JLabel speedLabel;
	private int canvasRows, canvasColumns;
	private CanvasPanel canvas;

	public MainView(JFrame jFrame, SimController controller) {
		frame = new JFrame(SimStrings.TITLE);
		this.controller = controller;
		this.canvasRows = controller.getRows();
		this.canvasColumns = controller.getColumns();
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
		canvas = new CanvasPanel(canvasRows, canvasColumns);
		// setup buttons and labels
		widthLabel = new JLabel("width: ");
		widthField = new NumberField("100");
		heightLabel = new JLabel("height: ");
		heightField = new NumberField("100");
		newBtn = new JButton("new");
		newBtn.setToolTipText("new board");
		playBtn = new JButton("play");
		playBtn.setToolTipText("play/pause simulation");
		slowDownBtn = new JButton("<<");
		slowDownBtn.setToolTipText("slow down simulation speed");
		speedUpBtn = new JButton(">>");
		speedUpBtn.setToolTipText("speed up simulation speed");
		speedLabel = new JLabel("speed: " + controller.getSpeedMultiplier());
	}

	private void addComponents() {
		controlPanel.add(newBtn);
		controlPanel.add(widthLabel);
		controlPanel.add(widthField);
		controlPanel.add(heightLabel);
		controlPanel.add(heightField);
		controlPanel.add(playBtn);
		controlPanel.add(slowDownBtn);
		controlPanel.add(speedUpBtn);
		controlPanel.add(speedLabel);

		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		mainPanel.add(canvas, BorderLayout.CENTER);
	}

	private void addActions() {

		newBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update(SimState.NEW);
			}
		});

		playBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update(SimState.PLAYPAUSE);
			}
		});

		slowDownBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update(SimState.SLOWDOWN);
			}
		});

		speedUpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update(SimState.SPEEDUP);
			}
		});
	}

	private void update(SimState state) {
		switch (state) {
		case PLAYPAUSE:
			controller.playPauseSimulation();
			break;
		case NEW:
			controller.restartSimulation();
			canvas.clear();
			widthField.setEnabled(true);
			heightField.setEnabled(true);
			break;
		case SPEEDUP:
			controller.speedUpSimulation();
			break;
		case SLOWDOWN:
			controller.slowDownSimulation();			
			break;
		}
		if (controller.isRunning()) {
			playBtn.setText("pause");
			widthField.setEnabled(false);
			heightField.setEnabled(false);
		} else {
			playBtn.setText("play");
		}
		speedLabel.setText("speed: " + controller.getSpeedMultiplier());
	}

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
	
	public SimController getController() {
		return controller;
	}

	public void setController(SimController controller) {
		this.controller = controller;
	}

	public JFrame getFrame() {
		return frame;
	}

}