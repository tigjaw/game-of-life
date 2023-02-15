package com.iridiumflair.sim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel implements MouseListener {
	private Image canvas;
	Graphics canvasGraphics;

	public CanvasPanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		canvas = createImage(getWidth(), getHeight());
		// canvasGraphics = canvas.getGraphics();
	}

	private void redraw() {
		canvasGraphics.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}