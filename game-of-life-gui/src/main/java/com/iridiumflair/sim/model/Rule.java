package com.iridiumflair.sim.model;

public class Rule {
	private boolean ruleForLiveCells;
	private Result result;
	private Condition condition;
	private int threshold;
	
	public Rule() {
		this.ruleForLiveCells = true;
		this.result = Result.DIES;
		this.condition = Condition.LESSTHAN;
		this.threshold = 2;
	}

	public Rule liveCell() {
		this.ruleForLiveCells = true;
		return this;
	}
	
	public Rule deadCell() {
		this.ruleForLiveCells = false;
		return this;
	}
	
	public Rule lives() {
		this.result = Result.LIVES;
		return this;
	}
	
	public Rule dies() {
		this.result = Result.DIES;
		return this;
	}
	
	public Rule ifLiveNeighbours(Condition condition) {
		this.condition = condition;
		return this;
	}
	
	public Rule thresholdOf(int threshold) {
		this.threshold = threshold;
		return this;
	}

	public boolean isApplicable(boolean cellStatus, int liveNeighbours) {
		return isValid(cellStatus) && meetsCondition(liveNeighbours);
	}
	
	public boolean isValid(boolean cellStatus) {
		if (ruleForLiveCells == cellStatus) {
			return true;
		} else {
			return false;
		}
	}

	public boolean meetsCondition(int liveNeigbbours) {
		boolean parses = false;
		switch (condition) {
		case EQUAL:
			if (liveNeigbbours == threshold) {
				parses = true;
			}
			break;
		case NOTEQUAL:
			if (liveNeigbbours != threshold) {
				parses = true;
			}
			break;
		case LESSOREQUAL:
			if (liveNeigbbours <= threshold) {
				parses = true;
			}
			break;
		case MOREOREQUAL:
			if (liveNeigbbours >= threshold) {
				parses = true;
			}
			break;
		case LESSTHAN:
			if (liveNeigbbours < threshold) {
				parses = true;
			}
			break;
		case MORETHAN:
			if (liveNeigbbours > threshold) {
				parses = true;
			}
			break;
		}
		return parses;
	}
	
	public int applyResult() {
		return result.ordinal();
	}
	
	// GETTERS AND SETTERS

	public boolean isRuleForLiveCells() {
		return ruleForLiveCells;
	}

	public void setRuleForLiveCells(boolean ruleForLiveCells) {
		this.ruleForLiveCells = ruleForLiveCells;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

}