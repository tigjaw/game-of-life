package com.iridiumflair.sim.control;

import com.iridiumflair.sim.model.Board;

/**
 * The {@code BoardController} is used by the views and {@code SimController} to
 * control the Board indirectly.<br>
 * The {@code BoardController} has no knowledge of the {@code SimController} or
 * of the various view components. Its sole purpose is to control the
 * {@code Board}.
 * 
 * @see Board
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class BoardController {
	private Board board;

	/**
	 * Parameterised constructor for {@code BoardController}.<br>
	 * 
	 * @param board to set
	 */
	public BoardController(Board board) {
		this.board = board;
	}
	
	/**
	 * Parameterised constructor for {@code BoardController}.<br>
	 * Creates a new {@code Board} with the specified rows and columns.
	 * 
	 * @see #BoardController(Board)
	 * 
	 * @param width		-	the number of columns in the Board
	 * @param height	-	the number of rows in the Board
	 */
	public BoardController(int width, int height) {
		this(new Board(width, height));
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
	 * @param w - the x coordinate of the cell to evaluate
	 * @param h - the y coordinate of the cell to evaluate
	 * @return true if alive, false if dead
	 */
	public boolean cellIsAlive(int w, int h) {
		// System.out.println("BoardController.cellIsAlive: " + w + ", " + h);
		return board.cellIsAlive(w, h);
	}

	/**
	 * The {@code birthCell(int, int)} method creates a live cell at the specified x
	 * and y coordinates by calling the {@code Board.birthCell(int, int)} method
	 * using the x and y parameters.
	 * 
	 * @see Board#birthCell(int, int)
	 * 
	 * @param w - the x coordinate of the cell to birth
	 * @param h - the y coordinate of the cell to birth
	 */
	public void birthCell(int w, int h) {
		// System.out.println("BoardController.birthCell: " + w + ", " + h);
		board.birthCell(w, h);		
	}

	/**
	 * The {@code killCell(int, int)} method creates a dead cell at the specified x
	 * and y coordinates by calling the {@code Board.killCell(int, int)} method
	 * using the x and y parameters.
	 * 
	 * @see Board#killCell(int, int)
	 * 
	 * @param w - the x coordinate of the cell to kill
	 * @param h - the y coordinate of the cell to kill
	 */
	public void killCell(int w, int h) {
		// System.out.println("BoardController.cellIsAlive: " + w + ", " + h);
		board.killCell(w, h);
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

	public int getWidth() {
		return board.getWidth();
	}

	public int getHeight() {
		return board.getHeight();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}