package com.iridiumflair.sim.control;

/**
 * The SimController class contains the simulation logic and is utilised by the
 * view components to manipulate the simulation indirectly.<br>
 * To manipulate the Board, the SimController contains the BoardController and
 * does so via its public access methods. The SimController does not access the
 * Board directly. The SimController has no knowledge of the Board or of any of
 * the view components. Its sole purpose is to control and advance the
 * simulation.
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
	 * Parameterised constructor for SimController with only one parameter.<br>
	 * Calls the two-parameter constructor using default values for the sim interval
	 * and speed multiplier.
	 * 
	 * @see BoardController
	 * 
	 * @param boardCtrl - the {@link BoardController} to set
	 */
	public SimController(BoardController boardCtrl) {
		this(boardCtrl, 1000, 1);
	}

	/**
	 * Parameterised constructor for SimController with two parameters.<br>
	 * Calls the three-parameter constructor using default values for the speed
	 * multiplier.
	 * 
	 * @see BoardController
	 * 
	 * @param boardCtrl   - the {@link BoardController} to set
	 * @param simInterval - the int to set
	 */
	public SimController(BoardController boardCtrl, int simInterval) {
		this(boardCtrl, simInterval, 1);
	}

	/**
	 * Parameterised constructor for SimController with three parameters. The
	 * simulation is paused by default.
	 * 
	 * @see BoardController
	 * 
	 * @param boardCtrl       - the {@link BoardController} to set
	 * @param simInterval     - the int to set
	 * @param speedMultiplier - the int to set
	 */
	public SimController(BoardController boardCtrl, int simInterval, int speedMultiplier) {
		this.boardCtrl = boardCtrl;
		this.running = false;
		this.simInterval = simInterval;
		this.speedMultiplier = speedMultiplier;
	}

	/**
	 * This method reverses the value of {@link #running} which decides whether the
	 * simulation is running or paused, and returns that value.
	 * 
	 * @return running - the boolean to return
	 */
	public boolean playPauseSimulation() {
		// System.out.println("SimController.playPauseSimulation()");

		return running = !running;
	}

	/**
	 * This method advances the simulation:<br>
	 * - tells the BoardController to advance the Board to the next.<br>
	 * - increments the generation.
	 * 
	 * @see BoardController#advanceBoard()
	 */
	public void advanceSimulation() {
		// System.out.println("SimController.advanceSimulation:");

		boardCtrl.advanceBoard();
		generation++;
		// System.out.println("generation = " + generation);
	}

	/**
	 * This method resets the simulation:<br>
	 * - sets the generation to 0.<br>
	 * - pauses the simulation by setting the running value to false.<br>
	 */
	public void restartSimulation() {
		// System.out.println("SimController.restartSimulation()";

		generation = 0;
		running = false;

		// System.out.println("generation = " + generation);
		// System.out.println("running = " + running);
	}

	/**
	 * This method speeds up the simulation by multiplying the speedMultiplier by 2.
	 * During the simulation process, the speed (frame-rate) is derived by diving
	 * the timer delay by the speedMultiplier.
	 */
	public void speedUpSimulation() {
		// System.out.println("SimController.speedUpSimulation()";

		speedMultiplier = speedMultiplier * 2;

		// System.out.println("new speedMultiplier = " + speedMultiplier);
	}

	/**
	 * This method slows down the simulation by dividing the speedMultiplier by 2.
	 * During the simulation process, the speed (frame-rate) is derived by diving
	 * the timer delay by the speedMultiplier.
	 */
	public void slowDownSimulation() {
		// System.out.println("SimController.slowDownSimulation()";

		if (speedMultiplier > 1) {
			speedMultiplier = speedMultiplier / 2;
		}
		
		// System.out.println("new speedMultiplier = " + speedMultiplier);
	}

	// GETTERS AND SETTERS

	public int getWidth() {
		return boardCtrl.getWidth();
	}

	public int getHeight() {
		return boardCtrl.getHeight();
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