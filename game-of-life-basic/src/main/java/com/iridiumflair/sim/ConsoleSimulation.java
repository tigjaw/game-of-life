package com.iridiumflair.sim;

// a very basic Conway's Game of Life Simulation

public class ConsoleSimulation {
	private int width, height;
	private int[][] board;
	
	public static void main(String[] args) {
		ConsoleSimulation sim = new ConsoleSimulation(8,5);
		sim.birthCell(2, 2);
		sim.birthCell(3, 2);
		sim.birthCell(4, 2);
		for (int i = 0; i <10; i++) {
			sim.printBoard();
			sim.step();
			//sim.printBoard();
		    try {
		        Thread.sleep(300);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
	}
	
	public ConsoleSimulation(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.board = new int[width][height];
	}
	
	public void birthCell(int x, int y) {
		this.board[x][y] = 1;
	}
	
	public void killCell(int x, int y) {
		this.board[x][y] = 0;
	}
	
	public void printBoard() {
		System.out.println("----------");
		for(int y = 0; y < height; y++) {
			String line = "|";
			for (int x = 0; x < width; x++) {
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

	public void step() {
		int[][] newBoard = new int[width][height];
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
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
		this.board = newBoard;
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
		if (x < 0 || x >= width) {
			return 0;
		}
		
		if (y < 0 || y >= height) {
			return 0;
		}
		
		return this.board[x][y];
	}
	
	// GETTERS AND SETTERS
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int[][] getBoard() {
		return board;
	}
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	
}