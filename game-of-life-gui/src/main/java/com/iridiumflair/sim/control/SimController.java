package com.iridiumflair.sim.control;

/**
 * The {@code SimController} class contains the simulation logic and is utilised
 * by the view components to manipulate the simulation indirectly.<br>
 * To manipulate the {@code Board}, the {@code SimController} contains the
 * {@code BoardController} and does so via its public access methods. The
 * {@code SimController} does not access the {@code Board} directly. The
 * {@code SimController} has no knowledge of the {@code Board} or of any of the
 * view components. Its sole purpose is to control and advance the simulation.
 * 
 * @see BoardController
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class SimController {
	private BoardController boardCtrl;
	private boolean running;
	private int simInterval;
	private int speedMultiplier;
	private int generation;

	/**
	 * Parameterised constructor for {@code SimController} with only one
	 * parameter.<br>
	 * Calls the {@code SimController#SimController(BoardController, int, int)}
	 * constructor using default values for the sim interval and speed multiplier.
	 * 
	 * @see BoardController
	 * 
	 * @param boardCtrl to set
	 */
	public SimController(BoardController boardCtrl) {
		this(boardCtrl, 1000, 1);
	}

	/**
	 * Parameterised constructor for {@code SimController} with three parameters.
	 * Sets {@code running} to false by default.
	 * 
	 * @param boardCtrl       the {@code BoardController} to set
	 * @param simInterval     the {@code simInterval} to set
	 * @param speedMultiplier the {@code speedMultiplier} to set
	 */
	public SimController(BoardController boardCtrl, int simInterval, int speedMultiplier) {
		this.boardCtrl = boardCtrl;
		this.running = false;
		this.simInterval = simInterval;
		this.speedMultiplier = speedMultiplier;
	}

	/**
	 * The {@code playPauseSimulation()} reverses the value of {@code running},
	 * which is used by the {@code MainView}
	 * 
	 * @return
	 */
	public boolean playPauseSimulation() {
		return running = !running;
	}

	/**
	 * The {@code advanceSimulation()} method advances the simulation:<br>
	 * - tells the {@code BoardController} to advance the {@code Board} to the next
	 * generation, by calling {@code BoardController.advanceBoard()}.<br>
	 * - increments the generation.
	 * 
	 * @see BoardController#advanceBoard()
	 * 
	 */
	public void advanceSimulation() {
		boardCtrl.advanceBoard();
		generation++;
		System.out.println(generation);
	}

	/**
	 * The {@code restartSimulation()} method resets the simulation:<br>
	 * - tells the {@code BoardController} to clear the board, by calling
	 * {@code BoardController.clear()}.<br>
	 * - sets the generation to 0.<br>
	 * - sets running to false.<br>
	 */
	public void restartSimulation() {
		boardCtrl.clear();
		generation = 0;
		running = false;
	}

	/**
	 * The {@code speedUpSimulation} method speeds up the simulation by multiplying
	 * the {@code speedMultiplier} by 2.<br>
	 * During the simulation process, the speed (framerate) is derived by diving the
	 * timer delay by the speedMultiplier.
	 */
	public void speedUpSimulation() {
		speedMultiplier = speedMultiplier * 2;
	}

	/**
	 * The {@code slowDownSimulation} method speeds up the simulation by dividing
	 * the {@code speedMultiplier} by 2.<br>
	 * During the simulation process, the speed (framerate) is derived by diving the
	 * timer delay by the speedMultiplier.
	 */
	public void slowDownSimulation() {
		if (speedMultiplier > 1) {
			speedMultiplier = speedMultiplier / 2;
		}
	}

	// GETTERS AND SETTERS

	public int getRows() {
		return boardCtrl.getRows();
	}

	public int getColumns() {
		return boardCtrl.getColumns();
	}

	public BoardController getBoardCtrl() {
		return boardCtrl;
	}

	public void setBoardCtrl(BoardController boardCtrl) {
		this.boardCtrl = boardCtrl;
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