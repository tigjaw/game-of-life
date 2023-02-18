package com.iridiumflair.sim.model;

public class Board {
	private int[][] board;
	private int generation;

	/**
	 * Constructor for the Board class An empty board initialised to 0
	 * 
	 * @param rows    the number of rows in the 2d grid
	 * @param columns the number of columns in the 2d grid
	 */
	public Board(int rows, int columns) {
		board = new int[rows][columns];
		this.generation = 0;
	}
	
	public void birthCell(int x, int y) {
		this.board[x][y] = 1;
	}
	
	public void killCell(int x, int y) {
		this.board[x][y] = 0;
	}

	/**
	 * The get method is used to get the value for the location at the specified x,y
	 * location.
	 * 
	 * @param x the row of the grid
	 * @param y the column of the grid
	 */
	public int get(int x, int y) {
		return board[x][y];
	}

	/**
	 * The set method is used to set the value for the location at the specified x,y
	 * location.
	 * 
	 * @param x     the row of the grid
	 * @param y     the column of the grid
	 * @param value the value to stored at x,y
	 */
	public void set(int x, int y, int value) {
		board[x][y] = value;
	}
	
	public void step() {
		int[][] newBoard = new int[getColumns()][getRows()];
		for(int y = 0; y < getRows(); y++) {
			for (int x = 0; x < getColumns(); x++) {
				int aliveNeighbours = countAliveNeighbours(x, y);
				if (isAlive(x, y)) {
					if (aliveNeighbours < 2) {
						newBoard[x][y] = 0;
					} else if (aliveNeighbours == 2 || aliveNeighbours == 3){
						newBoard[x][y] = 1;
					} else {
						newBoard[x][y] = 0;
					}
				} else {
					if (aliveNeighbours == 3) {
						newBoard[x][y] = 1;
					}
				}
			}
		}
		generation++;
		board = newBoard;
	}

	public int countAliveNeighbours(int x, int y) {
		int count = 0;
		count += cellState(x-1,y-1);
		count += cellState(x, y-1);
		count += cellState(x+1, y-1);
		
		count += cellState(x-1,y);
		// count += cellState(x, y); this is the current cell
		count += cellState(x+1, y);
		
		count += cellState(x-1,y+1);
		count += cellState(x, y+1);
		count += cellState(x+1, y+1);
		return count;
	}

	public boolean isAlive(int x, int y) {
		if (cellState(x, y) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public int cellState(int x, int y) {
		if (x < 0 || x >= getColumns()) {
			return 0;
		}
		
		if (y < 0 || y >= getRows()) {
			return 0;
		}
		
		return get(x, y);
	}
	
	public void clear() {
		for (int x = 0; x < getRows(); x++) {
			for (int y = 0; y < getColumns(); y++) {
				set(x, y, 0);
			}
		}
		generation = 0;
	}

	/**
	 * The getBoard method returns the board array
	 * 
	 * @return the board array
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * The setBoard method sets the board array
	 * 
	 * @param board the board array to set
	 */
	public void setBoard(int[][] board) {
		this.board = board;
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
	
	public void printBoard() {
		System.out.println("----------");
		for(int y = 0; y < getRows(); y++) {
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

}