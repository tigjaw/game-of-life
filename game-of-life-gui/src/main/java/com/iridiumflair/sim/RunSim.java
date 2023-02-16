package com.iridiumflair.sim;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.iridiumflair.sim.control.SimController;
import com.iridiumflair.sim.model.Board;
import com.iridiumflair.sim.view.MainView;

public class RunSim implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunSim());
	}

	@Override
	public void run() {
		Board model = new Board(500, 500);
		SimController ctrl = new SimController(model);
		new MainView(new JFrame("Conway's Game of Life"), ctrl);
	}

}