package com.iridiumflair.sim;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.iridiumflair.sim.control.SimController;
import com.iridiumflair.sim.model.SimModel;
import com.iridiumflair.sim.view.MainView;

public class RunSim implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunSim());
	}

	@Override
	public void run() {
		SimModel model = new SimModel(10, 10);
		SimController ctrl = new SimController(model);
		new MainView(new JFrame("Conway's Game of Life"), ctrl);
	}

}