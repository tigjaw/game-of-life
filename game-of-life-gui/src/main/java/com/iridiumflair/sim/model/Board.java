package com.iridiumflair.sim.model;

import java.util.ArrayList;

/**
 * The {@code Board} class contains the board array, the {@code Rule}s, the
 * neighbour range, and the logic behind the game of life simulation.
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class Board {
	private int[][] board;
	private ArrayList<Rule> rules;
	private int neighbourRange = 1;

	/**
	 * Constructor for the {@code Board} class.<br>
	 * An empty board initialised to 0.<br>
	 * Applies the default game of life {@code Rule}s
	 * 
	 * @param rows    the number of rows in the 2d grid
	 * @param columns the number of columns in the 2d grid
	 */
	public Board(int[][] board) {
		this.board = board;
	}

	/**
	 * Constructor for the {@code Board} class.<br>
	 * Creates an empty board initialised to 0.<br>
	 * Sets the board width, height, and the rules.
	 * 
	 * @param rows    the number of rows in the 2d grid
	 * @param columns the number of columns in the 2d grid
	 */
	public Board(int rows, int columns) {
		this(new int[rows][columns]);
	}

	/**
	 * The {@linkplain #advanceBoard(Rules))} method creates a new board, recalculates
	 * board values by evaluating the current board with the provided {@code Rules}
	 * and finally overwrites the old board with the new board.
	 * 
	 * @see #getRows()
	 * @see #getColumns()
	 * @see #countAliveNeighbours(int, int)
	 * @see #cellIsAlive(int, int)
	 * @see Rule
	 * @param rules to apply to the game
	 */
	public void advanceBoard(Rules rules) {
		int[][] newBoard = new int[getColumns()][getRows()];

		for (int y = 0; y < getRows(); y++) {
			for (int x = 0; x < getColumns(); x++) {

				int aliveNeighbours = countAliveNeighbours(x, y);

				for (Rule rule : rules.getRules()) {
					if (rule.isApplicable(cellIsAlive(x, y), aliveNeighbours)) {
						newBoard[x][y] = rule.applyResult();
					}
				}

			}
		}
		board = newBoard;
	}

	/**
	 * The {@linkplain #countAliveNeighbours(int, int)} method takes the x and y
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
	 * The {@linkplain #cellIsAlive(int, int)} method checks if the cell at the
	 * specified x and y coordinates is alive or dead. If the cell state at x and y
	 * is 1, then it is alive, else it is dead.
	 * 
	 * @see #cellState(int, int)
	 * 
	 * @param x - the x coordinate of the cell to be evaluated
	 * @param y - the y coordinate of the cell to be evaluated
	 * @return the state of the cell (alive == true)
	 */
	public boolean cellIsAlive(int x, int y) {
		if (cellState(x, y) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The {@linkplain #cellState(int, int)} method returns the value held in the
	 * cell at the specified x and y coordinates.<br>
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
			return 0;
		}

		if (y < 0 || y >= getRows()) {
			return 0;
		}

		return board[x][y];
	}

	/**
	 * The {@linkplain #birthCell(int, int)} method sets the cell at the specified
	 * coordinates to 1 (alive).
	 * 
	 * @param x - the x coordinate of the cell
	 * @param y - the y coordinate of the cell
	 */
	public void birthCell(int x, int y) {
		this.board[x][y] = 1;
	}

	/**
	 * The {@linkplain #killCell(int, int)} method sets the cell at the specified
	 * coordinates to 0 (dead).
	 * 
	 * @param x - the x coordinate of the cell
	 * @param y - the y coordinate of the cell
	 */
	public void killCell(int x, int y) {
		this.board[x][y] = 0;
	}

	/**
	 * The {@linkplain #clear()} method clears the board by looping through it and
	 * setting each cell to 0.<br>
	 */
	public void clear() {
		for (int x = 0; x < getRows(); x++) {
			for (int y = 0; y < getColumns(); y++) {
				board[x][y] = 0;
			}
		}
	}

	/**
	 * {@linkplain #printBoard()} prints the board to the console.<br>
	 * Dead cells are repsresented by a period, and live cells are represented by an
	 * asterix.
	 */
	public void printBoard() {
		System.out.println("----------");
		for (int y = 0; y < getRows(); y++) {
			String line = "|";
			for (int x = 0; x < getColumns(); x++) {
				if (this.board[x][y] == 0) {
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
	 * The {@linkplain #getBoard()} method returns the board array
	 * 
	 * @return the board array
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * The {@linkplain #setBoard(int[][])} method sets the board array
	 * 
	 * @param board the board array to set
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}

	/**
	 * The {@linkplain #getRules()} method gets the rules of the game.
	 * 
	 * @return the {@code Rule}s to get
	 */
	public ArrayList<Rule> getRules() {
		return rules;
	}

	/**
	 * The {@linkplain #setRules(ArrayList)} method sets the rules of the game.
	 * 
	 * @param rules the {@code Rule}s to set
	 */
	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * The {@linkplain #getRows()} method returns the number of rows in the array
	 * 
	 * @return the rows (height) of the array
	 */
	public int getRows() {
		return board.length;
	}

	/**
	 * The {@linkplain #getColumns()} method returns the number of columns in the
	 * array
	 * 
	 * @return the columns (width) of the array
	 */
	public int getColumns() {
		return board[0].length;
	}

}