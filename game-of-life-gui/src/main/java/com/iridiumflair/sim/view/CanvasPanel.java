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

import com.iridiumflair.sim.control.SimController;

/**
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {
	private SimController controller;
	private Image canvas;
	Graphics2D graphics;

	public CanvasPanel(SimController controller, int width, int height) {
		this.controller = controller;
		setPreferredSize(new Dimension(width, height));
		setDoubleBuffered(false);
		addActions();
	}

	private void addActions() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				draw(e.getX(), e.getY());
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				draw(e.getX(), e.getY());
			}
		});
	}

	public void drawBoard() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (controller.cellIsAlive(x, y)) {
					draw(x, y);
				} else {
				}
			}
		}
	}

	protected void draw(int x, int y) {
		if (isValidPosition(x, y)) {
			if (graphics != null) {
				System.out.println("drawing at: " + x + ", " + y);
				graphics.drawRect(x, y, 1, 1);
				controller.birthCell(x, y);
				repaint();
			}
		}
	}

	private boolean isValidPosition(int x, int y) {
		boolean parses = true;
		if (x < 0 || x >= getSize().width) {
			parses = false;
		}
		if (y < 0 || y >= getSize().height) {
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

}