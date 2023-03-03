package com.iridiumflair.sim.view;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.iridiumflair.sim.model.Rule;

@SuppressWarnings("serial")
public class RulePanel extends JPanel {
	private JComboBox<String> cellStatus, result, condition;
	private NumberField threshold;

	public RulePanel(Rule rule) {
		
	}
}