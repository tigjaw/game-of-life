package com.iridiumflair.sim.model;

/**
 * The Board class contains the board array and the logic behind the game of
 * life simulation.
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
	 * @param width  the number of columns in the 2d grid
	 * @param height the number of rows in the 2d grid
	 */
	public Board(int width, int height) {
		this.board = new int[width][height];
	}

	/**
	 * This method creates a new board, recalculates board values by evaluating the
	 * current board, and finally overwrites the old board with the new board.
	 * 
	 * @see #getWidth()
	 * @see #getHeight()
	 * @see #countAliveNeighbours(int, int)
	 * @see #cellIsAlive(int, int)
	 */
	public void advanceBoard() {
		// System.out.println("board.advanceBoard()");

		int[][] newBoard = new int[getWidth()][getHeight()];

		for (int w = 0; w < getWidth(); w++) {
			for (int h = 0; h < getHeight(); h++) {

				int aliveNeighbours = countAliveNeighbours(w, h);

				if (cellIsAlive(w, h)) {
					if (aliveNeighbours < dieCondition1) {
						newBoard[w][h] = dead;
					} else if (aliveNeighbours == surviveCondition1 || aliveNeighbours == surviveCondition2) {
						newBoard[w][h] = live;
					} else if (aliveNeighbours > dieCondition2) {
						newBoard[w][h] = dead;
					}
				} else {
					if (aliveNeighbours == birthCondition) {
						newBoard[w][h] = live;
					}
				}

			}
		}
		board = newBoard;
	}

	/**
	 * This method takes the x and y coordinates of a cell and counts its living
	 * neighbours in all directions.<br>
	 * Only cells within one cell distances are considered neighbours.<br>
	 * The cell state at x,y is not evaluated as it is the cell being compared.
	 * 
	 * @see #cellState(int, int)
	 * 
	 * @param width  - the y coordinate of the cell to be evaluated
	 * @param height - the x coordinate of the cell to be evaluated
	 * @return the number of live cells
	 */
	public int countAliveNeighbours(int width, int height) {
		// System.out.println("Board.countAliveNeighbours(int, int)");
		// System.out.println("width = " + width + ", height = " + height);

		int count = 0;
		count += cellState(width - neighbourRange, height - neighbourRange);
		count += cellState(width - neighbourRange, height + neighbourRange);

		count += cellState(width + neighbourRange, height - neighbourRange);
		count += cellState(width + neighbourRange, height + neighbourRange);

		count += cellState(width - neighbourRange, height);
		count += cellState(width + neighbourRange, height);
		count += cellState(width, height - neighbourRange);
		count += cellState(width, height + neighbourRange);
		return count;
	}

	/**
	 * This method checks if the cell at the specified x and y coordinates is alive
	 * or dead. If the cell state at x and y is 1, then it is alive, else it is
	 * dead.
	 * 
	 * @see #cellState(int, int)
	 * 
	 * @param width  - the x coordinate of the cell to be evaluated
	 * @param height - the y coordinate of the cell to be evaluated
	 * @return the state of the cell (alive == true)
	 */
	public boolean cellIsAlive(int width, int height) {
		// System.out.println("Board.cellIsAlive(int, int)");
		// System.out.println("width = " + width + ", height = " + height);

		if (cellState(width, height) == live) {
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
	 * @see #getWidth()
	 * @see #getHeight()
	 * 
	 * @param width  - the x coordinate of the cell to be evaluated
	 * @param height - the y coordinate of the cell to be evaluated
	 * @return the value held in the cell at x and y
	 */
	public int cellState(int width, int height) {
		// System.out.println("Board.cellState(int, int)");
		// System.out.println("width = " + width + ", height = " + height);

		if (width < 0 || width >= getWidth()) {
			return dead;
		}

		if (height < 0 || height >= getHeight()) {
			return dead;
		}

		return board[width][height];
	}

	/**
	 * The {@code birthCell(int, int)} method sets the cell at the specified
	 * coordinates to 1 (alive).
	 * 
	 * @param width  - the x coordinate of the cell
	 * @param height - the y coordinate of the cell
	 */
	public void birthCell(int width, int height) {
		// System.out.println("Board.birthCell(int width, int height)");
		// System.out.println("width = " + width + ", height = " + height);

		this.board[width][height] = live;

		@SuppressWarnings("unused")
		int alive = 0; // for debugging
		for (int w = 0; w < getWidth(); w++) {
			for (int h = 0; h < getHeight(); h++) {
				if (board[w][h] == 1) {
					alive++;
				}
			}
		}
		// System.out.println("alive cells: " + alive);
	}

	/**
	 * This method sets the cell at the specified coordinates to 0 (dead).
	 * 
	 * @param width  - the y coordinate of the cell
	 * @param height - the x coordinate of the cell
	 */
	public void killCell(int width, int height) {
		// System.out.println("Board.killCell(int width, int height)");
		// System.out.println("width = " + width + ", height = " + height);
		
		this.board[width][height] = dead;
	}

	/**
	 * This method clears the board by looping through it and setting each cell to
	 * 0.
	 */
	public void clear() {
		// System.out.println("Board.clear()");

		for (int w = 0; w < getWidth(); w++) {
			for (int h = 0; h < getHeight(); h++) {
				this.board[w][h] = dead;
			}
		}
	}

	/**
	 * This method prints the board to the console.
	 */
	public void printBoard() {
		System.out.println("----------");
		for (int w = 0; w < getWidth(); w++) {
			String line = "|";
			for (int h = 0; h < getHeight(); h++) {
				if (this.board[w][h] == dead) {
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
		for (int w = 0; w < getWidth(); w++) {
			for (int h = 0; h < getHeight(); h++) {
				result += board[w][h];
			}
			result += "\n";
		}
		return result;
	}

	// GETTERS AND SETTERS

	/**
	 * @return the board array
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board array to set
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}

	/**
	 * @return the width of the array
	 */
	public int getWidth() {
		return board.length;
	}

	/**
	 * @return the height of the array
	 */
	public int getHeight() {
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