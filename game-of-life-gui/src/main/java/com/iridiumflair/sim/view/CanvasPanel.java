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
	private Image image;
	Graphics2D g2d;

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
			System.out.println("Dragged: " + x + " , " + y);

			if (g2d != null) {
				g2d.drawRect(x, y, 1, 1);
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

	protected void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			g2d = (Graphics2D) image.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, 0, 0, null);
	}

	public void clear() {
		g2d.setPaint(Color.white);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		g2d.setPaint(Color.black);
		repaint();
	}

	private void redraw() {
		// graphics
	}

}