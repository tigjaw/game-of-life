package com.iridiumflair.sim.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import com.iridiumflair.sim.control.CanvasController;

/**
 * The {@code CanvasPanel} class extends {@code JPanel}, providing an area for
 * the user to draw on, and contains all of the functionality for doing so,
 * using {@code Image} and {@code Graphics2D}, and {@code MouseAdapter}s to
 * process mouse input.<br>
 * {@code CanvasPanel} contains the {@code CanvasController} through which it
 * updates the {@code Board} indirectly.<br>
 * The {@code CanvasPanel} has no direct knowledge of the {@code SimController},
 * {@code Board}, or any other view components.
 * 
 * @see CanvasController
 * @see Image
 * @see Graphics2D
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {
	private CanvasController canvasCtrl;
	private Image canvas;
	Graphics2D graphics;

	/**
	 * The Constructor for {@code CanvasPanel}:<br>
	 * - takes {@code CanvasController} as a parameter, and applies it to the
	 * {@code canvasCtrl}.<br>
	 * - sets the dimensions of the {@code CanvasPanel}, using the values returned
	 * by {@code CanvasController#getColumns()} and
	 * {@code CanvasController#getRows()}.<br>
	 * - calls {@code addActions()}.
	 * 
	 * @param canvasCtrl
	 */
	public CanvasPanel(CanvasController canvasCtrl) {
		this.canvasCtrl = canvasCtrl;
		setPreferredSize(new Dimension(canvasCtrl.getColumns(), canvasCtrl.getRows()));
		// setDoubleBuffered(false);
		addActions();
	}

	/**
	 * The {@code addActions()} method adds {@code MouseListener}s to the panel:<br>
	 * - adds a {@code MouseListener} to the panel to listen for mouse clicks by
	 * calling {@code addMouseListener(MouseListener)} and defining a new
	 * {@code MouseAdapter}, which itself overrides the
	 * {@code MouseAdapter.mouseClicked(MouseEvent)} method.<br>
	 * - adds a {@code MouseMotionListener} to the panel to listen for mouse clicks
	 * by calling {@code addMouseMotionListener(MouseMotionAdapter)} and defining a
	 * new {@code MouseMotionAdapter}, which itself overrides the
	 * {@code MouseMotionAdapter.mouseDragged(MouseEvent)} method.<br>
	 * - both listeners call {@code CanvasPanel.draw(int, int)}, using
	 * {@code MouseEvent.getX()} and {@code MouseEvent.getX()} as parameters.
	 * 
	 * @see #addMouseListener(MouseListener)
	 * @see MouseAdapter
	 * @see MouseAdapter#mouseClicked(MouseEvent)
	 * @see #addMouseMotionListener(MouseMotionListener)
	 * @see MouseMotionAdapter
	 * @see MouseMotionAdapter#mouseDragged(MouseEvent)
	 * @see MouseEvent
	 * @see #draw(int, int)
	 */
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

	/**
	 * The {@code drawBoard()} method iterates through each cell of the panel,
	 * checks if the cell at the x and y coordinates is alive, and draws on the
	 * panel accordingly.
	 * 
	 * @see CanvasPanel#draw(int, int)
	 */
	public void drawBoard() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (canvasCtrl.cellIsAlive(x, y)) {
					draw(x, y);
				} else {
				}
			}
		}
	}

	/**
	 * The {@code draw(int, int)} method draws to the panel at the specified x and y
	 * coordinates:<br>
	 * - checks if the parameters are valid x and y coordinates<br>
	 * - then ensures {@code graphics} is not null before drawing the graphics.<br>
	 * - and if both checks are true, a rectangle is drawn at the coordinates, the
	 * {@code CanvasController} is told to update the {@code Board} at the specified
	 * coordinates, and the panel repaints itself.
	 * 
	 * @see CanvasPanel#isValidPosition(int, int)
	 * @see Graphics2D#drawRect(int, int, int, int)
	 * @see CanvasController#birthCell(int, int)
	 * @see CanvasPanel#repaint()
	 * 
	 * @param x - the x coordinate to draw at
	 * @param y - the y coordinate to draw at
	 */
	protected void draw(int x, int y) {
		if (isValidPosition(x, y)) {
			if (graphics != null) {
				// System.out.println("drawing at: " + x + ", " + y);
				graphics.drawRect(x, y, 1, 1);
				canvasCtrl.birthCell(x, y);
				repaint();
			}
		}
	}

	/**
	 * The {@code isValidPosition(int, int)} method checks if the parameters are
	 * valid x and y coordinates.<br>
	 * The coordinates are invalid if:<br>
	 * - x is less than zero or greater than the width of the canvas/board.<br>
	 * - y is less than zero or greater than the height of the canvas/board.<br>
	 * 
	 * @see #getSize()
	 * 
	 * @param x - the x coordinate to evaluate
	 * @param y - the y coordinate to evaluate
	 * @return true if position is valid
	 */
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

	/**
	 * The {@code paintComponent(Graphics)} method overrides the JPanel
	 * {@code paintComponent(Graphics)}.<br>
	 * This method draws the canvas {@code Image} to the {@code Graphics}. if the
	 * canvas {@code Image} is null:<br>
	 * - creates a new {@code Image} using the dimensions of the
	 * {@code CanvasPanel}.<br>
	 * - assigns this canvas {@code Image} to the graphics variable.<br>
	 * - tells the renderer to apply anti-aliasing.<br>
	 * if the canvas {@code Image} is not null:<br>
	 * - draws the image to the {@code Graphics}.<br>
	 * 
	 * @see Image
	 * @see Graphics2D
	 * @see #createImage(int, int)
	 * @see Image#getGraphics()
	 * @see Graphics2D#setRenderingHint(java.awt.RenderingHints.Key, Object)
	 * @see #clear()
	 * @see Graphics2D#drawImage(Image, int, int, java.awt.image.ImageObserver)
	 */
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

	/**
	 * The {@code clear()} method resets (clears) the board by redrawing over it
	 * with a white rectangle equal to the size of the canvas.<br>
	 * The method then resets the paint colour to black, ready for drawing more live
	 * cells in future generations/simulations.<br>
	 * 
	 * @see Graphics2D#setPaint(Paint)
	 * @see Graphics2D#fillRect(int, int, int, int)
	 * @see CanvasPanel#repaint()
	 */
	public void clear() {
		graphics.setPaint(Color.white);
		graphics.fillRect(0, 0, getSize().width, getSize().height);
		graphics.setPaint(Color.black);
		repaint();
	}

}