package com.iridiumflair.sim.model;

public enum CellStatus {
	LIVE_CELL(true), DEAD_CELL(false);
	
	private boolean value;

	CellStatus(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

}