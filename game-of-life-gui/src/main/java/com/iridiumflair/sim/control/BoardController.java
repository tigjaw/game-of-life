package com.iridiumflair.sim.control;

import com.iridiumflair.sim.model.Board;

/**
 * currently unused
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class BoardController {
	private Board board;
	
	public BoardController(Board board) {
		this.board = board;
	}
	
	public void step() {
		board.step();
	}

	public boolean cellIsAlive(int x, int y) {
		return board.isAlive(x, y);
	}

	public void birthCell(int x, int y) {
		board.set(x, y, 1);
	}

	public void killCell(int x, int y) {
		board.set(x, y, 0);
	}

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