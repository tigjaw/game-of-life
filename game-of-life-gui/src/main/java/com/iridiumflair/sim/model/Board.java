package com.iridiumflair.sim.model;

public class Board {
	private int[][] board;

	/**
	 * Constructor for the Board class
	 * An empty board initialised to 0
	 * 
	 * @param rows the number of rows in the 2d grid
	 * @param columns the number of columns in the 2d grid
	 */
	public Board(int rows, int columns) {
		board = new int[rows][columns];
	}

	/**
	 * The set method is used to set the value for the location at the specified
	 * x,y location.
	 * 
	 * @param x the row of the grid
	 * @param y the column of the grid
	 * @param value the value to stored at x,y
	 */
	public void set(int x, int y, int value) {
		board[x][y] = value;
	}

	/**
	 * The get method is used to get the value for the location at the specified
	 * x,y location.
	 * 
	 * @param x the row of the grid
	 * @param y the column of the grid
	 */
	public int get(int x, int y) {
		return board[x][y];
	}

	/**
	 * The getRows method returns the number of rows in the grid
	 * 
	 * @return the rows (height) of the grid
	 */
	public int getRows() {
		return board.length;
	}

	/**
	 * The getColumns method returns the number of columns in the grid
	 * 
	 * @return the columns (width) of the grid
	 */
	public int getColumns() {
		return board[0].length;
	}

	@Override
	public String toString() {
		String result = "";
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getColumns(); c++) {
				result += board[r][c];
			}
			result += "\n";
		}
		return result;
	}

}