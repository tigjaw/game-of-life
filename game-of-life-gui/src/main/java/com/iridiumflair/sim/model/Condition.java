package com.iridiumflair.sim.model;

public enum Condition {
	EQUAL("equal to"), LESSTHAN("less than"), MORETHAN("more than"), LESSOREQUAL("more than or equal to"), MOREOREQUAL("more than or equal to"), NOTEQUAL("is not equal to");
	
	private String text;

	Condition(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}