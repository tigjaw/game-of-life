package com.iridiumflair.sim.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.iridiumflair.sim.control.ConfigController;
import com.iridiumflair.sim.control.SimController;

/**
 * currently unused. will be utilised to provide additional options to the user.
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class ConfigDialog extends JDialog {
	private MainView mainView;
	private SimController simCtrl;
	private ConfigController configCtrl;
	// options
	private JPanel mainPanel, settingsPanel, rulesPanel, btnPanel;
	private NumberField widthField, heightField;
	private NumberField intervalField;
	// rules
	private ArrayList<RulePanel> rulePanels;
	// buttons
	private JButton acceptBtn, cancelBtn;

	public ConfigDialog(MainView mainView, String title) {
		super(mainView.getFrame(), title);
		this.mainView = mainView;
		this.simCtrl = mainView.getSimCtrl();
		createComponents();
		addComponents();
		addActions();
		display();
	}

	private void createComponents() {
		// containers
		mainPanel = new JPanel(new BorderLayout());
		settingsPanel = new JPanel(new GridBagLayout());
		rulesPanel = new JPanel(new GridBagLayout());
		btnPanel = new JPanel(new BorderLayout());
		// basic settings - width label and field
		widthField = new NumberField(simCtrl.getColumns(), 4);
		widthField.setToolTipText("width of board");
		// basic settings - height label and field
		heightField = new NumberField(simCtrl.getRows(), 4);
		heightField.setToolTipText("height of board");
		// basic settings - interval label and field
		intervalField = new NumberField(simCtrl.getSimInterval(), 4);
		intervalField.setToolTipText("lower is faster");
		// rules fields
		// for each rule add a rule panel
		// buttons
		acceptBtn = new JButton("accept");
		acceptBtn.setToolTipText("create simulation with these options");
		cancelBtn = new JButton("cancel");
		cancelBtn.setToolTipText("cancel simulation creation");
	}

	private void addComponents() {
		// add panels
		mainPanel.add(settingsPanel, BorderLayout.NORTH);
		mainPanel.add(rulesPanel, BorderLayout.CENTER);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		// add settings labels and fields
		settingsPanel.add(label("BASIC SETTINGS"), createGBC(0, 0));
		settingsPanel.add(label("Width"), createGBC(0, 1));
		settingsPanel.add(widthField, createGBC(1, 1));
		settingsPanel.add(label("height"), createGBC(0, 2));
		settingsPanel.add(heightField, createGBC(1, 2));
		settingsPanel.add(label("interval:"), createGBC(0, 3));
		settingsPanel.add(intervalField, createGBC(1, 3));
		// add rules fields
		rulesPanel.add(label("SIMULATION RULES"), createGBC(0, 0));
		// add buttons
		btnPanel.add(acceptBtn, BorderLayout.EAST);
		btnPanel.add(cancelBtn, BorderLayout.WEST);
	}

	public JLabel label(String text) {
		return new JLabel(text);
	}

	private GridBagConstraints createGBC(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		return gbc;
	}

	private void addActions() {
		acceptBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acceptSettings(true);
			}
		});

		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				acceptSettings(false);
			}
		});
	}

	private void acceptSettings(boolean accept) {
		if (accept) {
			int height = heightField.getNumber();
			int width = widthField.getNumber();
			// use config controller to create new board
			simCtrl.setSimInterval(intervalField.getNumber());
			simCtrl.restartSimulation();
			CanvasPanel canvas = mainView.getCanvas();
			canvas.clear();
			canvas.setSize(new Dimension(width, height));
			// update sim with:
			// new board dimensions
			// new canvas size
			// new sim interval and rules
		}
		setVisible(false);
	}

	private void display() {
		add(mainPanel);
		setLocationRelativeTo(null);
		pack();
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}

	@Override
	public Dimension getPreferredSize() {
		int maxWidth = 512;
		int maxHeight = 512;
		Dimension dim = super.getPreferredSize();
		if (dim.width > maxWidth)
			dim.width = maxWidth;
		if (dim.height > maxHeight)
			dim.height = maxHeight;
		return dim;
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public SimController getController() {
		return simCtrl;
	}

	public void setController(SimController controller) {
		this.simCtrl = controller;
	}

	public JTextArea getWidthField() {
		return widthField;
	}

	public void setWidthField(NumberField widthField) {
		this.widthField = widthField;
	}

	public JTextArea getHeightField() {
		return heightField;
	}

	public void setHeightField(NumberField heightField) {
		this.heightField = heightField;
	}

	public JTextArea getIntervalField() {
		return intervalField;
	}

	public void setIntervalField(NumberField intervalField) {
		this.intervalField = intervalField;
	}

	public JButton getAcceptBtn() {
		return acceptBtn;
	}

	public void setAcceptBtn(JButton acceptBtn) {
		this.acceptBtn = acceptBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

}