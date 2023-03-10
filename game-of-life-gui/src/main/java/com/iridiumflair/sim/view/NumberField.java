package com.iridiumflair.sim.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;

/**
 * currently unused. Will be utilised to provide additional options to the user.
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class NumberField extends JTextArea {
	private int maxDigits = 0;

	public NumberField(String number) {
		super(number);
		validateInput();
	}

	public NumberField(int number, int maxDigits) {
		this(Integer.toString(number));
		this.maxDigits = maxDigits;
		setColumns(maxDigits);
	}

	private void validateInput() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (isValid(key.getKeyChar())) {
					setEditable(true);
				} else {
					setEditable(false);
				}
			}
		});
	}

	private boolean isValid(int key) {
		boolean isValid = false;
		if (key >= '0' && key <= '9') {
			if (getText().length() < maxDigits) {
				isValid = true;
			}
		}
		if (key == KeyEvent.VK_BACK_SPACE) {
			isValid = true;
		}
		return isValid;
	}
	
	public int getNumber() {
		String text = getText();
		int num = Integer.parseInt(text);
		return num;
	}

}