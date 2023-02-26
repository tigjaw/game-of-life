package com.iridiumflair.sim.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.iridiumflair.sim.control.BoardController;
import com.iridiumflair.sim.control.SimController;
import com.iridiumflair.sim.model.Board;

/**
 * currently unused. will be utilised to provide additional options to the user.
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class SettingsPanel extends JDialog {
	private MainView mainView;
	private SimController simCtrl;
	private BoardController boardCtrl;
	// options
	private JPanel mainPanel, settingsPanel, rulesPanel, btnPanel;
	private NumberField widthField, heightField;
	private NumberField intervalField;
	// rules
	private NumberField dieRule1, surviveRule1, surviveRule2, birthRule, dieRule2;
	// buttons
	private JButton acceptBtn, cancelBtn;

	public SettingsPanel(MainView mainView, String title) {
		super(mainView.getFrame(), title);
		this.mainView = mainView;
		this.simCtrl = mainView.getSimCtrl();
		this.boardCtrl = simCtrl.getBoardCtrl();
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
		Board board = boardCtrl.getBoard();
		dieRule1 = new NumberField(board.getDieCondition1(), 1);
		surviveRule1 = new NumberField(board.getSurviveCondition1(), 1);
		surviveRule2 = new NumberField(board.getSurviveCondition2(), 1);
		dieRule2 = new NumberField(board.getDieCondition2(), 1);
		birthRule = new NumberField(board.getBirthCondition(), 1);
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
		// die rule 1
		JPanel rule1 = new JPanel();
		rule1.add(label("a live cell dies if its number of live neighbours is less than"));
		rule1.add(dieRule1);
		rulesPanel.add(rule1, createGBC(0, 1));
		// surival rule 1 and 2
		JPanel rule2 = new JPanel();
		rule2.add(label("a live cell survives if its number of live neighbours equals"));
		rule2.add(surviveRule1);
		rule2.add(label("or"));
		rule2.add(surviveRule2);
		rulesPanel.add(rule2, createGBC(0, 2));
		// die rule 2
		JPanel rule3 = new JPanel();
		rule3.add(label("a live cell dies if its number of live neighbours is greater than"));
		rule3.add(dieRule2);
		rulesPanel.add(rule3, createGBC(0, 3));
		// birth rule
		JPanel rule4 = new JPanel();
		rule4.add(label("a dead cell revives if its number of live neighbours is equal to"));
		rule4.add(birthRule);
		rulesPanel.add(rule4, createGBC(0, 4));
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
			Board board = new Board(height, width);
			board.setDieCondition1(dieRule1.getNumber());
			board.setSurviveCondition1(surviveRule1.getNumber());
			board.setSurviveCondition2(surviveRule2.getNumber());
			board.setDieCondition2(dieRule2.getNumber());
			board.setBirthCondition(birthRule.getNumber());
			board.setDieCondition2(birthRule.getNumber());
			boardCtrl.setBoard(board);
			simCtrl.setSimInterval(intervalField.getNumber());
			CanvasPanel canvas = mainView.getCanvas();
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