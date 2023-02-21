package com.iridiumflair.sim.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.iridiumflair.sim.control.SimController;

/**
 * currently unused. will be utilised to provide additional options to the user.
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class OptionsDialog extends JDialog {
	private MainView mainView;
	private SimController simCtrl;
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
		this.simCtrl = mainView.getSimCtrl();
		createComponents();
		addComponents();
		addActions();
		display();
	}

	private void createComponents() {
		// width and height fields
		widthLabel = new JLabel("width: ");
		widthField = new NumberField(simCtrl.getColumns());
		widthField.setToolTipText("width of board");
		heightLabel = new JLabel("height: ");
		heightField = new NumberField(simCtrl.getRows());
		heightField.setToolTipText("height of board");
		// interval field
		intervalLabel = new JLabel("interval: ");
		intervalField = new NumberField(simCtrl.getSimInterval());
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

	public JLabel getWidthLabel() {
		return widthLabel;
	}

	public void setWidthLabel(JLabel widthLabel) {
		this.widthLabel = widthLabel;
	}

	public JLabel getHeightLabel() {
		return heightLabel;
	}

	public void setHeightLabel(JLabel heightLabel) {
		this.heightLabel = heightLabel;
	}

	public JTextArea getWidthField() {
		return widthField;
	}

	public void setWidthField(JTextArea widthField) {
		this.widthField = widthField;
	}

	public JTextArea getHeightField() {
		return heightField;
	}

	public void setHeightField(JTextArea heightField) {
		this.heightField = heightField;
	}

	public JLabel getIntervalLabel() {
		return intervalLabel;
	}

	public void setIntervalLabel(JLabel intervalLabel) {
		this.intervalLabel = intervalLabel;
	}

	public JTextArea getIntervalField() {
		return intervalField;
	}

	public void setIntervalField(JTextArea intervalField) {
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