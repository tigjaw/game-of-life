package com.iridiumflair.sim.model;

/**
 * The {@code Board} class contains the board array and the logic behind the
 * game of life simulation.
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class Board {
	private int[][] board;
	private int dieCondition1 = 2;
	private int surviveCondition1 = 2;
	private int surviveCondition2 = 3;
	private int dieCondition2 = 3;
	private int birthCondition = 3;
	private int neighbourRange = 1;
	private int dead = 0;
	private int live = 1;

	/**
	 * Constructor for the Board class An empty board initialised to 0
	 * 
	 * @param rows    the number of rows in the 2d grid
	 * @param columns the number of columns in the 2d grid
	 */
	public Board(int rows, int columns) {
		board = new int[rows][columns];
	}

	/**
	 * The {@code advanceBoard()} method creates a new board, recalculates board
	 * values by evaluating the current board, and finally overwrites the old board
	 * with the new board.
	 * 
	 * @see #getRows()
	 * @see #getColumns()
	 * @see #countAliveNeighbours(int, int)
	 * @see #cellIsAlive(int, int)
	 */
	public void advanceBoard() {
		int[][] newBoard = new int[getColumns()][getRows()];

		for (int y = 0; y < getRows(); y++) {
			for (int x = 0; x < getColumns(); x++) {

				int aliveNeighbours = countAliveNeighbours(x, y);

				if (cellIsAlive(x, y)) {
					if (aliveNeighbours < dieCondition1) {
						newBoard[x][y] = dead;
					} else if (aliveNeighbours == surviveCondition1 || aliveNeighbours == surviveCondition2) {
						newBoard[x][y] = live;
					} else if (aliveNeighbours > dieCondition2){
						newBoard[x][y] = dead;
					}
				} else {
					if (aliveNeighbours == birthCondition) {
						newBoard[x][y] = live;
					}
				}

			}
		}
		board = newBoard;
	}

	/**
	 * The {@code countAliveNeighbours(int, int)} method takes the x and y
	 * coordinates of a cell and counts its living neighbours in all directions.<br>
	 * Only cells within one cell distances are considered neighbours.<br>
	 * The cell state at x,y is not evaluated as it is the cell being compared.
	 * 
	 * @see #cellState(int, int)
	 * 
	 * @param x - the x coordinate of the cell to be evaluated
	 * @param y - the y coordinate of the cell to be evaluated
	 * @return the number of live cells
	 */
	public int countAliveNeighbours(int x, int y) {
		int count = 0;
		count += cellState(x - neighbourRange, y - neighbourRange);
		count += cellState(x, y - neighbourRange);
		count += cellState(x + neighbourRange, y - neighbourRange);

		count += cellState(x - neighbourRange, y);
		// count += cellState(x, y); this is the current cell
		count += cellState(x + neighbourRange, y);

		count += cellState(x - neighbourRange, y + neighbourRange);
		count += cellState(x, y + neighbourRange);
		count += cellState(x + neighbourRange, y + neighbourRange);
		return count;
	}

	/**
	 * The {@code cellIsAlive(int, int)} method checks if the cell at the specified
	 * x and y coordinates is alive or dead. If the cell state at x and y is 1, then
	 * it is alive, else it is dead.
	 * 
	 * @see #cellState(int, int)
	 * 
	 * @param x - the x coordinate of the cell to be evaluated
	 * @param y - the y coordinate of the cell to be evaluated
	 * @return the state of the cell (alive == true)
	 */
	public boolean cellIsAlive(int x, int y) {
		if (cellState(x, y) == live) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The {@code cellState(int, int)} method returns the value held in the cell at
	 * the specified x and y coordinates.<br>
	 * The method first checks if the coordinates are out of the array bounds, and
	 * if so, returns a 0.
	 * 
	 * @see #getColumns()
	 * @see #getRows()
	 * 
	 * @param x - the x coordinate of the cell to be evaluated
	 * @param y - the y coordinate of the cell to be evaluated
	 * @return the value held in the cell at x and y
	 */
	public int cellState(int x, int y) {
		if (x < 0 || x >= getColumns()) {
			return dead;
		}

		if (y < 0 || y >= getRows()) {
			return dead;
		}

		return board[x][y];
	}

	/**
	 * The {@code birthCell(int, int)} method sets the cell at the specified
	 * coordinates to 1 (alive).
	 * 
	 * @param x - the x coordinate of the cell
	 * @param y - the y coordinate of the cell
	 */
	public void birthCell(int x, int y) {
		this.board[x][y] = live;
	}

	/**
	 * The {@code killCell(int, int)} method sets the cell at the specified
	 * coordinates to 0 (dead).
	 * 
	 * @param x - the x coordinate of the cell
	 * @param y - the y coordinate of the cell
	 */
	public void killCell(int x, int y) {
		this.board[x][y] = dead;
	}

	/**
	 * The {@code clear()} method clears the board by looping through it and setting
	 * each cell to 0.<br>
	 * Also resets the {@code generation} to 0.
	 */
	public void clear() {
		for (int x = 0; x < getRows(); x++) {
			for (int y = 0; y < getColumns(); y++) {
				board[x][y] = dead;
			}
		}
	}

	/**
	 * {@code printBoard()} prints the board to the console.
	 */
	public void printBoard() {
		System.out.println("----------");
		for (int y = 0; y < getRows(); y++) {
			String line = "|";
			for (int x = 0; x < getColumns(); x++) {
				if (this.board[x][y] == dead) {
					line += ".";
				} else {
					line += "*";
				}
			}
			line += "|";
			System.out.println(line);
		}
		System.out.println("----------\n");
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int x = 0; x < getRows(); x++) {
			for (int y = 0; y < getColumns(); y++) {
				result += board[x][y];
			}
			result += "\n";
		}
		return result;
	}

	// GETTERS AND SETTERS

	/**
	 * The {@code getBoard()} method returns the board array
	 * 
	 * @return the board array
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * The {@code setBoard(int[][])} method sets the board array
	 * 
	 * @param board the board array to set
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}

	/**
	 * The {@code getRows()} method returns the number of rows in the array
	 * 
	 * @return the rows (height) of the array
	 */
	public int getRows() {
		return board.length;
	}

	/**
	 * The {@code getColumns()} method returns the number of columns in the array
	 * 
	 * @return the columns (width) of the array
	 */
	public int getColumns() {
		return board[0].length;
	}

	public int getDieCondition1() {
		return dieCondition1;
	}

	public void setDieCondition1(int dieCondition) {
		this.dieCondition1 = dieCondition;
	}

	public int getSurviveCondition1() {
		return surviveCondition1;
	}

	public void setSurviveCondition1(int surviveCondition1) {
		this.surviveCondition1 = surviveCondition1;
	}

	public int getSurviveCondition2() {
		return surviveCondition2;
	}

	public void setSurviveCondition2(int surviveCondition2) {
		this.surviveCondition2 = surviveCondition2;
	}

	public int getDieCondition2() {
		return dieCondition2;
	}

	public void setDieCondition2(int dieCondition2) {
		this.dieCondition2 = dieCondition2;
	}

	public int getBirthCondition() {
		return birthCondition;
	}

	public void setBirthCondition(int birthCondition) {
		this.birthCondition = birthCondition;
	}

	public int getNeighbourRange() {
		return neighbourRange;
	}

	public void setNeighbourRange(int neighbourRange) {
		this.neighbourRange = neighbourRange;
	}

	public int getDead() {
		return dead;
	}

	public void setDead(int dead) {
		this.dead = dead;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

}