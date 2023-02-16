package com.iridiumflair.sim.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {
	private Image canvas;
	Graphics2D graphics;

	public CanvasPanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setDoubleBuffered(false);
		addActions();
	}

	private void addActions() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// save the location of the mouse press
				int x = e.getX();
				int y = e.getY();

				draw(x, y);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				draw(x, y);
			}
		});
	}

	private void draw(int x, int y) {
		if (isValidPosition(x, y)) {
			System.out.println("drawing at: " + x + " , " + y);

			if (graphics != null) {
				graphics.drawRect(x, y, 1, 1);
				repaint();
			}
		}
	}

	private boolean isValidPosition(int x, int y) {
		boolean parses = true;
		if (x < 0 || x > getSize().width) {
			parses = false;
		}
		if (y < 0 || y > getSize().height) {
			parses = false;
		}
		return parses;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (canvas == null) {
			canvas = createImage(getSize().width, getSize().height);
			graphics = (Graphics2D) canvas.getGraphics();
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(canvas, 0, 0, null);
	}

	public void clear() {
		graphics.setPaint(Color.white);
		graphics.fillRect(0, 0, getSize().width, getSize().height);
		graphics.setPaint(Color.black);
		repaint();
	}

	private void redraw() {
		// graphics
	}

}