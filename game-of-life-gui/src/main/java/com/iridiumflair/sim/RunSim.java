package com.iridiumflair.sim;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.iridiumflair.sim.control.CanvasController;
import com.iridiumflair.sim.control.SimController;
import com.iridiumflair.sim.model.Board;
import com.iridiumflair.sim.view.MainView;

/**
 * A Java implementation of Conway's Game of Life Utilising Swing API
 * 
 * @see SimController
 * @see CanvasController
 * @see Board
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class RunSim implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunSim());
	}

	@Override
	public void run() {
		Board board = new Board(500, 500);
		SimController ctrl = new SimController(board);
		new MainView(new JFrame("Conway's Game of Life"), ctrl);
	}

}