package com.iridiumflair.sim.control;

import com.iridiumflair.sim.model.Board;

/**
 * The {@code CanvasController} is used by the views and {@code SimController} to
 * control the Board indirectly.<br>
 * The {@code CanvasController} has no knowledge of the {@code SimController} or
 * of the various view components. Its sole purpose is to control the
 * {@code Board}.
 * 
 * @see Board
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
	 * Creates a new {@code Board} with the specified rows and columns.
	 * 
	 * @see #CanvasController(Board)
	 * 
	 * @param rows - the number of rows in the Board
	 * @param columns - the number of columns in the Board
	 */
	public CanvasController(int rows, int columns) {
		this(new Board(rows, columns));
	}

	public CanvasController(SimController simCtrl) {
		this(simCtrl.getBoard());
	}

	/**
	 * The {@code advanceBoard()} method calls the {@code Board.advanceBoard()}
	 * method, which instructs the board to reevaluate itself based on the game of
	 * life rules.
	 * 
	 * @see Board#advanceBoard()
	 */
	public void advanceBoard() {
		board.advanceBoard();
	}

	/**
	 * The {@code cellIsAlive(int, int)} method checks if the cell at the specified
	 * x and y coordinates is alive by calling the
	 * {@code Board.cellIsAlive(int, int)} method using the x and y parameters.
	 * 
	 * @see Board#cellIsAlive(int, int)
	 * 
	 * @param x - the x coordinate of the cell to evaluate
	 * @param y - the y coordinate of the cell to evaluate
	 * @return true if alive, false if dead
	 */
	public boolean cellIsAlive(int x, int y) {
		return board.cellIsAlive(x, y);
	}

	/**
	 * The {@code birthCell(int, int)} method creates a live cell at the specified x
	 * and y coordinates by calling the {@code Board.birthCell(int, int)} method
	 * using the x and y parameters.
	 * 
	 * @see Board#birthCell(int, int)
	 * 
	 * @param x - the x coordinate of the cell to birth
	 * @param y - the y coordinate of the cell to birth
	 */
	public void birthCell(int x, int y) {
		board.birthCell(x, y);
	}

	/**
	 * The {@code killCell(int, int)} method creates a dead cell at the specified x
	 * and y coordinates by calling the {@code Board.killCell(int, int)} method
	 * using the x and y parameters.
	 * 
	 * @see Board#killCell(int, int)
	 * 
	 * @param x - the x coordinate of the cell to kill
	 * @param y - the y coordinate of the cell to kill
	 */
	public void killCell(int x, int y) {
		board.killCell(x, y);
	}

	/**
	 * The {@code clear()} method creates an empty board by calling the
	 * {@code Board.clear()} method.
	 * 
	 * @see Board#clear()
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