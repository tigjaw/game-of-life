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

import com.iridiumflair.sim.control.BoardController;

/**
 * The {@code CanvasPanel} class extends {@code JPanel}, providing an area for
 * the user to draw on, and contains all of the functionality for doing so,
 * using {@code Image} and {@code Graphics2D}, and {@code MouseAdapter}s to
 * process mouse input.<br>
 * {@code CanvasPanel} contains the {@code BoardController} through which it
 * updates the {@code Board} indirectly.<br>
 * The {@code CanvasPanel} has no direct knowledge of the {@code SimController},
 * {@code Board}, or any other view components.
 * 
 * @see BoardController
 * @see Image
 * @see Graphics2D
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {
	private BoardController boardCtrl;

	/**
	 * The Constructor for {@code CanvasPanel}:<br>
	 * - takes {@code BoardController} as a parameter, and applies it to the
	 * {@code boardCtrl}.<br>
	 * - sets the dimensions of the {@code CanvasPanel}, using the values returned
	 * by {@code BoardController#getColumns()} and
	 * {@code BoardController#getRows()}.<br>
	 * - calls {@code addActions()}.
	 * 
	 * @param boardCtrl
	 */
	public CanvasPanel(BoardController boardCtrl) {
		this.boardCtrl = boardCtrl;
		setPreferredSize(new Dimension(boardCtrl.getColumns(), boardCtrl.getRows()));
		setBackground(Color.GRAY);
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
				mouseAction(e);
				// draw(e.getX(), e.getY());
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				mouseAction(e);
				// draw(e.getX(), e.getY());
			}
		});
	}
	
	private void mouseAction(MouseEvent e) {
		// System.out.println("CanvasPanel.mouseAction");
		if (isValidPosition(e.getX(), e.getY())) {
			boardCtrl.birthCell(e.getX(), e.getY());
			repaint();
		}
	}

	/**
	 * The {@code drawBoard()} method iterates through each cell of the panel,
	 * checks if the cell at the x and y coordinates is alive, and draws on the
	 * panel accordingly.
	 * 
	 * @see CanvasPanel#draw(int, int)
	 */
	public void drawBoard(Graphics2D g) {
		// System.out.println("CanvasPanel.drawBoard");
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (boardCtrl.cellIsAlive(x, y)) {
					// System.out.println("CanvasPanel.drawBoard: " + x + ", " + y + " is alive");
					g.setPaint(Color.BLACK);
					g.drawRect(x, y, 1, 1);
				} else {
					g.setPaint(Color.WHITE);
					g.drawRect(x, y, 1, 1);
				}
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
		if (x < 0 || x >= boardCtrl.getColumns()) {
			parses = false;
			// System.out.println("x out of bounds");
		}
		if (y < 0 || y >= boardCtrl.getRows()) {
			parses = false;
			// System.out.println("y out of bounds");
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
		// System.out.println("CanvasPanel.paintComponent");
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    // Draw background for the whole panel
	    g2.setColor(Color.WHITE);
	    g2.fillRect(0, 0, boardCtrl.getColumns(), boardCtrl.getRows());
	    
		drawBoard((Graphics2D) g2);
	}

	/**
	 * @return the boardCtrl
	 */
	public BoardController getBoardCtrl() {
		return boardCtrl;
	}

	/**
	 * @param boardCtrl the boardCtrl to set
	 */
	public void setBoardCtrl(BoardController boardCtrl) {
		this.boardCtrl = boardCtrl;
	}

}