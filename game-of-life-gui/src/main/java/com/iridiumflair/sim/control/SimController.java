package com.iridiumflair.sim.control;

import com.iridiumflair.sim.model.Board;

public class SimController {
	private Board board;
	private boolean running;
	private int simInterval;
	private int speedMultiplier;

	public SimController(Board board) {
		this(board, 1000, 1);
	}

	public SimController(Board board, int simInterval, int speedMultiplier) {
		this.board = board;
		this.running = false;
		this.simInterval = simInterval;
		this.speedMultiplier = speedMultiplier;
	}
	
	public boolean playPauseSimulation() {
		return running = !running;
	}
	
	public void step() {
		board.step();
	}

	public boolean restartSimulation() {
		board.clear();
		return running = false;
	}

	public int speedUpSimulation() {
		speedMultiplier = speedMultiplier * 2;
		return speedMultiplier;
	}

	public int slowDownSimulation() {
		if (speedMultiplier > 1) {
			speedMultiplier = speedMultiplier / 2;
		}
		return speedMultiplier;
	}
	
	public boolean cellIsAlive(int x, int y) {
		return board.isAlive(x, y);
	}

	public void birthCell(int x, int y) {
		board.set(x, y, 1);
	}

	public void killCell(int x, int y) {
		board.set(x, y, 0);
	}
	
	// GETTERS AND SETTERS

	public int getRows() {
		return board.getRows();
	}

	public int getColumns() {
		return board.getColumns();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getSimInterval() {
		return simInterval;
	}

	public void setSimInterval(int simInterval) {
		this.simInterval = simInterval;
	}

	public int getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(int speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
	
	public int getFramerate() {
		return simInterval / speedMultiplier;
	}

}