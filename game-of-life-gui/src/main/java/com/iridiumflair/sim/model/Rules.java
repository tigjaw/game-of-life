package com.iridiumflair.sim.model;

import java.util.ArrayList;

public class Rules {
	private ArrayList<Rule> rules;
	
	public Rules() {
		this.rules = getDefaultRules();
	}

	public Rules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * The {@linkplain #getDefaultRules()} method applies the default game of life
	 * rules to the board.<br>
	 * 
	 * @return
	 */
	public ArrayList<Rule> getDefaultRules() {
		ArrayList<Rule> dr = new ArrayList<>();
		Rule rule1 = new Rule();
		rule1.liveCell().dies().ifLiveNeighbours(Condition.LESSTHAN).thresholdOf(2);
		Rule rule2a = new Rule();
		rule2a.liveCell().lives().ifLiveNeighbours(Condition.EQUAL).thresholdOf(2);
		Rule rule2b = new Rule();
		rule2b.liveCell().lives().ifLiveNeighbours(Condition.EQUAL).thresholdOf(3);
		Rule rule3 = new Rule();
		rule3.liveCell().dies().ifLiveNeighbours(Condition.MORETHAN).thresholdOf(3);
		Rule rule4 = new Rule();
		rule4.deadCell().lives().ifLiveNeighbours(Condition.EQUAL).thresholdOf(3);
		dr.add(rule1);
		dr.add(rule2a);
		dr.add(rule2b);
		dr.add(rule3);
		dr.add(rule4);
		return dr;
	}
	
	// GETTERS AND SETTERS

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}
}