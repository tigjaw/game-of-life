package com.iridiumflair.sim.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import com.iridiumflair.sim.control.BoardController;

/**
 * CanvasPanel extends JPanel, providing an area for the user to draw on, and
 * contains all of the functionality for doing so, using Graphics2D, and
 * MouseAdapters to process mouse input.<br>
 * CanvasPanel contains the BoardController through which it updates the Board
 * indirectly.<br>
 * The CanvasPanel has no direct knowledge of the SimController, Board, or any
 * other view components.
 * 
 * @see BoardController
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {
	private BoardController boardCtrl;

	/**
	 * The Constructor for {@code CanvasPanel}:<br>
	 * - initialises the Board Controller.<br>
	 * - sets the dimensions of the Panel, using the Board size.<br>
	 * - adds mouse actions
	 * 
	 * @see #setPreferredSize(int, int)
	 * @see #addActions()
	 * 
	 * @param boardCtrl - the {@link BoardController} to set.
	 */
	public CanvasPanel(BoardController boardCtrl) {
		this.boardCtrl = boardCtrl;
		setPreferredSize(boardCtrl.getWidth(), boardCtrl.getHeight());
		addActions();
	}

	/**
	 * This method adds MouseListeners to the panel:<br>
	 * - adds a Mouse Listeners to the panel to listen for mouse clicks.<br>
	 * - adds a Mouse Motion Listener to the panel to listen for mouse drags.<br>
	 * - both events call the same action method, sending the Mouse Event as a
	 * parameter.
	 * 
	 * @see #mouseAction(MouseEvent)
	 * 
	 */
	private void addActions() {
		// System.out.println("CanvasPanel.addActions()");
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				mouseAction(e);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				mouseAction(e);
			}
		});
	}

	/**
	 * This method is called whenever a mouse event occurs.<br>
	 * It checks if the cursor location is a valid location to draw, and if it is,
	 * it births a cell at that location. Then it tells the Canvas Panel to repaint
	 * itself.
	 * 
	 * @see #isValidPosition(int, int)
	 * @see BoardController#birthCell(int, int)
	 * @param e - the {@link MouseEvent} to set
	 */
	private void mouseAction(MouseEvent e) {
		// System.out.println("CanvasPanel.mouseAction(MouseEvent e)");
		
		if (isValidPosition(e.getX(), e.getY())) {
			boardCtrl.birthCell(e.getX(), e.getY());
			repaint();
		}
	}

	/**
	 * This method checks if the parameters are valid x and y coordinates.<br>
	 * The coordinates are invalid if:<br>
	 * - w is less than zero or greater than the width of the canvas/board.<br>
	 * - h is less than zero or greater than the height of the canvas/board.<br>
	 * 
	 * @see #getSize()
	 * 
	 * @param w - the x coordinate to evaluate
	 * @param h - the y coordinate to evaluate
	 * @return true if position is valid
	 */
	private boolean isValidPosition(int w, int h) {
		// System.out.println("CanvasPanel.isValidPosition(int w, int h)");
		// System.out.println("w = " + w + ", h = " + h);
		
		boolean parses = true;
		if (w < 0 || w >= boardCtrl.getWidth()) {
			parses = false;
			// System.out.println("w out of bounds");
		}
		if (h < 0 || h >= boardCtrl.getHeight()) {
			parses = false;
			// System.out.println("h out of bounds");
		}
		return parses;
	}

	/**
	 * This method iterates through each cell of the panel, checks if the cell at
	 * the w and h coordinates is alive, and draws on the panel accordingly.
	 * 
	 * @see BoardController#getWidth()
	 * @see BoardController#getHeight()
	 * @see BoardController#cellIsAlive(int, int)
	 * 
	 * @param g - the {@link Graphics2D} to draw on.
	 */
	private void drawBoard(Graphics2D g) {
		// System.out.println("CanvasPanel.drawBoard(Graphics2D g)");
		// System.out.println("CanvasPanel.drawBoard: " + w + ", " + h);
		
		
		for (int w = 0; w < boardCtrl.getWidth(); w++) {
			for (int h = 0; h < boardCtrl.getHeight(); h++) {
				if (boardCtrl.cellIsAlive(w, h)) {
					// System.out.println("cell is alive");
					g.setPaint(Color.BLACK);
					g.drawRect(w, h, 1, 1);
				} else {
					// System.out.println("cell is dead");
					g.setPaint(Color.WHITE);
					g.drawRect(w, h, 1, 1);
				}

			}
		}
	}

	/**
	 * This method overrides the JPanel method. This method draws the {@code Board}
	 * to the {@code Graphics}. tells the renderer to apply anti-aliasing, and sets
	 * the non-drawable background to gray.
	 * 
	 * @see #drawBoard(Graphics2D)
	 * 
	 * @param g - the {@link Graphics2D} to draw on.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// System.out.println("CanvasPanel.paintComponent(Graphics g)");
		
		super.paintComponent(g);
		// set background colour
		setBackground(Color.GRAY);
		// draw new graphics
		Graphics2D g2 = (Graphics2D) g;
		// add anti-aliasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Draw background for the whole panel
		g2.fillRect(0, 0, boardCtrl.getWidth(), boardCtrl.getHeight());
		// draw board
		drawBoard((Graphics2D) g2);
	}

	public void setPreferredSize(int width, int height) {
		// System.out.println("CanvasPanel.setPreferredSize(int width, int height");
		// System.out.println("width = " + width + ", height = " + height);
		
		int buffer = 0;
		this.setPreferredSize(new Dimension(width + buffer, height + buffer));
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