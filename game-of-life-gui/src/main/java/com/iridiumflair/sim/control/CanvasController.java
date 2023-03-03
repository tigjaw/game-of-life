package com.iridiumflair.sim.control;

import com.iridiumflair.sim.model.Board;

/**
 * The {@code CanvasController} is used by {@code CanvasPanel} to control the
 * Board indirectly.<br>
 * The {@code CanvasController} has no knowledge of the various view components.
 * Its sole purpose is to control the {@code Board}.
 * 
 * @see Board
 * @see SimController
 * @see CanvasPanel
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class CanvasController {
	private Board board;

	/**
	 * Parameterised constructor for {@code CanvasController}.<br>
	 * 
	 * @param board to set
	 */
	public CanvasController(Board board) {
		this.board = board;
	}

	/**
	 * Parameterised constructor for {@code CanvasController}.<br>
	 * Takes {@code SimController} as parameter, gets the board from it, and passes
	 * it to {@linkplain #CanvasController(Board)}
	 * 
	 * @param simCtrl to get board from
	 */
	public CanvasController(SimController simCtrl) {
		this(simCtrl.getBoard());
	}

	/**
	 * The {@linkplain #cellIsAlive(int, int)} method checks if the cell at the
	 * specified x and y coordinates is alive by calling the
	 * {@linkplain Board#cellIsAlive(int, int)} method using the x and y parameters.
	 * 
	 * @param x - the x coordinate of the cell to evaluate
	 * @param y - the y coordinate of the cell to evaluate
	 * @return true if alive, false if dead
	 */
	public boolean cellIsAlive(int x, int y) {
		return board.cellIsAlive(x, y);
	}

	/**
	 * The {@linkplain #birthCell(int, int)} method creates a live cell at the
	 * specified x and y coordinates by calling the
	 * {@linkplain Board#birthCell(int, int)} method using the x and y parameters.
	 * 
	 * @param x - the x coordinate of the cell to birth
	 * @param y - the y coordinate of the cell to birth
	 */
	public void birthCell(int x, int y) {
		board.birthCell(x, y);
	}

	/**
	 * The {@linkplain #killCell(int, int)} method creates a dead cell at the
	 * specified x and y coordinates by calling the
	 * {@linkplain Board#killCell(int, int)} method using the x and y parameters.
	 * 
	 * @param x - the x coordinate of the cell to kill
	 * @param y - the y coordinate of the cell to kill
	 */
	public void killCell(int x, int y) {
		board.killCell(x, y);
	}

	/**
	 * The {@linkplain #clear()} method creates an empty board by calling the
	 * {@linkplain Board#clear()} method.
	 */
	public void clear() {
		board.clear();
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
}