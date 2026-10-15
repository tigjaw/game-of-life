package com.iridiumflair.sim.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iridiumflair.sim.control.BoardController;
import com.iridiumflair.sim.control.SimController;
import com.iridiumflair.sim.model.LFStrings;
import com.iridiumflair.sim.model.SimState;

/**
 * MainView is the main GUI interface containing the basic controls and the
 * CanvasPanel. This class creates the main components and their actions,
 * sending inputs to the CanvasPanal and SimController.
 * 
 * @see SimController
 * @see JFrame
 * @see CanvasPanel
 * @see JPanel
 * @see JButton
 * @see JLabel
 * @see Timer
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class MainView {
	private SimController simCtrl;
	private JFrame frame;
	private JPanel mainPanel, controlPanel;
	private JButton playBtn, slowDownBtn, speedUpBtn, newBtn;
	private JLabel speedLabel;
	private CanvasPanel canvas;
	private Timer refreshTimer;
	private int margin = 100;

	/**
	 * The constructor for MainView<br>
	 * Initialises the frame and simulation controller, initialises the simulation's
	 * timer which refreshes the simulation every refresh, and finally, calls a
	 * method to create and show the interface.
	 * 
	 * @see SimController#getFramerate()
	 * @see #refresh()
	 * @see #createAndShowGUI()
	 * 
	 * @param frame      - the {@link JFrame} to set
	 * @param controller - the {@link SimController} to set
	 */
	public MainView(JFrame frame, SimController simCtrl) {
		this.frame = new JFrame("Conway's Game of Life");
		this.simCtrl = simCtrl;
		this.refreshTimer = new Timer(simCtrl.getFramerate(), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		createAndShowGUI();
	}

	/**
	 * This method simply contains method calls to incrementally build the GUI.
	 * 
	 * @see MainView#manageUI()
	 * @see MainView#createComponents()
	 * @see MainView#addComponents()
	 * @see MainView#addActions()
	 * @see MainView#showFrame()
	 * 
	 */
	private void createAndShowGUI() {
		// System.out.println("MainView.createAndShowGUI()");
		manageUI();
		createComponents();
		addComponents();
		addActions();
		showFrame();
	}

	/**
	 * This method sets the look and feel for the GUI using the various style
	 * strings set in LFStrings.
	 * 
	 * @see LFStrings
	 * @see UIManager#setLookAndFeel(String)
	 */
	private void manageUI() {
		// System.out.println("MainView.manageUI()");
		
		// set look and feel
		try {
			UIManager.setLookAndFeel(LFStrings.WINDOWS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Turn off metal's use of bold fonts
		// UIManager.put("swing.boldMetal", Boolean.FALSE);
	}

	/**
	 * This method initialises each JComponent in the view.
	 * 
	 * @see CanvasPanel
	 * @see SimController#getBoardCtrl()
	 * @see SimController#getSpeedMultiplier()
	 */
	private void createComponents() {
		// System.out.println("MainView.createComponents()");
		
		// setup panels
		mainPanel = new JPanel(new BorderLayout());
		controlPanel = new JPanel();
		canvas = new CanvasPanel(simCtrl.getBoardCtrl());
		// setup buttons and labels
		newBtn = new JButton("new");
		newBtn.setToolTipText("new board");
		playBtn = new JButton("play");
		playBtn.setToolTipText("play/pause simulation");
		slowDownBtn = new JButton("<<");
		slowDownBtn.setToolTipText("slow down simulation speed");
		speedUpBtn = new JButton(">>");
		speedUpBtn.setToolTipText("speed up simulation speed");
		speedLabel = new JLabel("speed: " + simCtrl.getSpeedMultiplier());
	}

	/**
	 * This method adds each JComponent to its parent JPanel.
	 */
	private void addComponents() {
		// System.out.println("MainView.addComponents()");
		
		controlPanel.add(newBtn);
		controlPanel.add(playBtn);
		controlPanel.add(slowDownBtn);
		controlPanel.add(speedUpBtn);
		controlPanel.add(speedLabel);

		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		mainPanel.add(new JScrollPane(canvas), BorderLayout.CENTER);
	}

	/**
	 * This method adds an ActionListener to each of the GUI buttons. Each button
	 * updates the SimState with a value according to the respective action.
	 * 
	 * @see #update(SimState)
	 * @see SimState
	 */
	private void addActions() {
		// System.out.println("MainView.addActions()");

		newBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				update(SimState.NEW);
			}
		});

		playBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				update(SimState.PLAYPAUSE);
			}
		});

		slowDownBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				update(SimState.SLOWDOWN);
			}
		});

		speedUpBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				update(SimState.SPEEDUP);
			}
		});
	}

	/**
	 * This method is called by the JButtons in this class and updates the view
	 * accordingly.<br>
	 * MainView does not modify the Board or CanvasPanel directly.<br>
	 * This method is responsible for:<br>
	 * - pausing/playing the simulation<br>
	 * - starting a new simulation.<br>
	 * - changing the speed of the simulation.<br>
	 * 
	 * @see SimController#playPauseSimulation()
	 * @see MainView#playPause()
	 * @see SettingsPanel#SettingsPanel(MainView, String)
	 * @see MainView#changeSpeed(SimState)
	 * 
	 * @param state the {@link SimState} to compare
	 */
	private void update(SimState state) {
		// System.out.println("MainView.update(SimState state)");
		// System.out.println("SimState = " + SimState);

		switch (state) {
		case PLAYPAUSE:
			simCtrl.playPauseSimulation();
			playPause();
			break;
		case NEW:
			new SettingsPanel(this, "new simulation options");
			playPause();
			break;
		default:
			changeSpeed(state);
		}
	}

	/**
	 * This method plays/pauses the simulation timer.<br>
	 * If the Simulation is running:<br>
	 * - sets play button text to "pause"<br>
	 * - starts the refresh timer.<br>
	 * If the Simulation is not running:<br>
	 * - sets play button text to "play"<br>
	 * - stops the refresh timer.<br>
	 * 
	 * @see SimController#isRunning()
	 * @see MainView#refreshTimer
	 * @see MainView#playBtn
	 */
	private void playPause() {
		// System.out.println("MainView.playPause()");
		// System.out.println("Simulation is Running = " + simCtrl.isRunning());

		if (simCtrl.isRunning()) {
			// System.out.println("Playing Simulation");
			playBtn.setText("pause");
			refreshTimer.start();
		} else {
			// System.out.println("Pausing Simulation");
			playBtn.setText("play");
			refreshTimer.stop();
		}
	}

	/**
	 * This method changes the speed of the simulation depending on the SimSate
	 * parameter.<br>
	 * The refreshTimer delay is then set with the new frame-rate<br>
	 * and the speedLabel text is updated to display the current speed.
	 * 
	 * @param state the {@link SimState} to compare
	 * @see SimController#speedUpSimulation()
	 * @see SimController#slowDownSimulation()
	 * @see MainView#refreshTimer
	 * @see SimController#getFramerate()
	 * @see MainView#speedLabel
	 * @see SimController#getSpeedMultiplier()
	 */
	private void changeSpeed(SimState state) {
		// System.out.println("MainView.changeSpeed(SimState state)");
		// System.out.println("SimState = " + state);

		switch (state) {
		case SPEEDUP:
			simCtrl.speedUpSimulation();
			break;
		case SLOWDOWN:
			simCtrl.slowDownSimulation();
			break;
		default:// will never be called
			break;
		}

		refreshTimer.setDelay(simCtrl.getFramerate());
		speedLabel.setText("speed: " + simCtrl.getSpeedMultiplier());
	}

	/**
	 * This method is called by refreshTimer's ActionListener while the simulation
	 * is running. Each refresh tells the SimController to advance the simulation
	 * and the CanvasPanel to repaint itself.
	 * 
	 * @see SimController#advanceSimulation()
	 * @see CanvasPanel#drawBoard()
	 */
	private void refresh() {
		// System.out.println("MainView.refresh()");
		simCtrl.advanceSimulation();
		canvas.repaint();
	}

	/**
	 * This method adds the mainPanel to the frame and displays the GUI.
	 */
	private void showFrame() {
		// System.out.println("MainView.showFrame()");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);
		frame.setPreferredSize(new Dimension(simCtrl.getWidth() + margin, simCtrl.getHeight() + margin));

		frame.setLocationRelativeTo(null);
		frame.revalidate();
		frame.repaint();
		frame.pack();
		frame.setVisible(true);
	}

	// GETTERS AND SETTERS

	public SimController getSimCtrl() {
		return simCtrl;
	}

	public void setSimCtrl(SimController simCtrl) {
		this.simCtrl = simCtrl;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setCanvasPanel(BoardController boardCtrl) {
		this.canvas = new CanvasPanel(boardCtrl);
	}

	public void setCanvasPanel(int width, int height) {
		this.canvas.setPreferredSize(width, height);
	}

	public CanvasPanel getCanvas() {
		return canvas;
	}

	public void setCanvas(CanvasPanel canvas) {
		this.canvas = canvas;
	}

	public Timer getRefreshTimer() {
		return refreshTimer;
	}

	public void setRefreshTimer(Timer refreshTimer) {
		this.refreshTimer = refreshTimer;
	}

}