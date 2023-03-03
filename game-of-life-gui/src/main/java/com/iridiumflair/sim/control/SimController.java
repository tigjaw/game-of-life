package com.iridiumflair.sim.control;

import com.iridiumflair.sim.model.Board;
import com.iridiumflair.sim.model.Rules;

/**
 * The {@code SimController} class contains the simulation logic and is utilised
 * by the view components to manipulate the simulation indirectly.<br>
 * The {@code SimController} has no knowledge of any of the view components. Its
 * sole purpose is to control and advance the simulation.
 * 
 * @see Board
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class SimController {
	private Board board;
	private Rules rules;
	private boolean running;
	private int simInterval;
	private int speedMultiplier;
	private int generation;

	/**
	 * Parameterised constructor for {@code SimController} with only one
	 * parameter.<br>
	 * Calls the {@linkplain #SimController(Board, int, int)} constructor using
	 * default values for the sim interval and speed multiplier.
	 * 
	 * @see Board
	 * 
	 * @param board to set
	 */
	public SimController(Board board) {
		this(board, 1000, 1);
		this.rules = new Rules();
	}

	/**
	 * Parameterised constructor for {@code SimController} with three parameters.
	 * Sets {@code running} to false by default.
	 * 
	 * @param board           the {@code Board} to set
	 * @param simInterval     the {@code simInterval} to set
	 * @param speedMultiplier the {@code speedMultiplier} to set
	 */
	public SimController(Board board, int simInterval, int speedMultiplier) {
		this.board = board;
		this.running = false;
		this.simInterval = simInterval;
		this.speedMultiplier = speedMultiplier;
	}

	/**
	 * The {@linkplain #playPauseSimulation()} reverses the value of
	 * {@code running}, which is used by the {@code MainView}
	 * 
	 * @return
	 */
	public boolean playPauseSimulation() {
		return running = !running;
	}

	/**
	 * The {@linkplain #advanceSimulation()} method advances the simulation:<br>
	 * - advances the {@code Board} to the next generation, by calling
	 * {@linkplain Board#advanceBoard(Rules)}.<br>
	 * - increments the generation.
	 */
	public void advanceSimulation() {
		board.advanceBoard(rules);
		generation++;
		// System.out.println(generation);
	}

	/**
	 * The {@linkplain #restartSimulation()} method resets the simulation:<br>
	 * - clears the board, by calling {@linkplain Board#clear()}.<br>
	 * - sets the generation to 0.<br>
	 * - sets running to false.<br>
	 */
	public void restartSimulation() {
		board.clear();
		generation = 0;
		running = false;
	}

	/**
	 * The {@linkplain #speedUpSimulation} method speeds up the simulation by
	 * multiplying the {@code speedMultiplier} by 2.<br>
	 * During the simulation process, the speed (framerate) is derived by diving the
	 * timer delay by the speedMultiplier.
	 */
	public void speedUpSimulation() {
		speedMultiplier = speedMultiplier * 2;
	}

	/**
	 * The {@linkplain #slowDownSimulation} method speeds up the simulation by
	 * dividing the {@code speedMultiplier} by 2.<br>
	 * During the simulation process, the speed (framerate) is derived by diving the
	 * timer delay by the speedMultiplier.
	 */
	public void slowDownSimulation() {
		if (speedMultiplier > 1) {
			speedMultiplier = speedMultiplier / 2;
		}
	}

	// GETTERS AND SETTERS

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getRows() {
		return board.getRows();
	}

	public int getColumns() {
		return board.getColumns();
	}

	public Rules getRules() {
		return rules;
	}

	public void setRules(Rules rules) {
		this.rules = rules;
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

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

}