package com.iridiumflair.sim.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iridiumflair.sim.control.SimController;

/**
 * {@code MainView} is the main GUI interface containing the basic controls and
 * the canvas {@code CanvasPanel}. This class creates the main components and
 * their actions, sending inputs to the {@code CanvasPanal} and
 * {@code SimController}
 * 
 * @see SimController
 * @see CanvasPanel
 * @see JFrame
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

	/**
	 * The constructor for {@code MainView}<br>
	 * Initialises the {@code frame}, {@code controller}, {@code canvasRows}, and
	 * {@code canvasColumns}.<br>
	 * Initialises the {@code refreshTimer} using the {@code
	 * SimController.getFramerate()} method as the timer delay between each
	 * {@code refresh()}.<br>
	 * Finally, the constructor calls the {code createAndShowGUI()} method.
	 * 
	 * @see SimController#getFramerate()
	 * @see #refresh()
	 * @see #createAndShowGUI()
	 * 
	 * @param frame      the {@link JFrame} to set
	 * @param controller the {@link SimController} to set
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
	 * The {@code createAndShowGUI()} method simply contains method calls to
	 * incrementally build the GUI.
	 * 
	 * @see MainView#manageUI()
	 * @see MainView#createComponents()
	 * @see MainView#addComponents()
	 * @see MainView#addActions()
	 * @see MainView#showFrame()
	 * 
	 */
	private void createAndShowGUI() {
		// System.out.println("MainView.createAndShowGUI");
		manageUI();
		createComponents();
		addComponents();
		addActions();
		showFrame();
	}

	/**
	 * The {@code manageUi()} method sets the look and feel for the GUI.
	 * 
	 * @see LFStrings
	 * @see UIManager#setLookAndFeel(String)
	 */
	private void manageUI() {
		// System.out.println("MainView.manageUI");
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
	 * The {@code createComponents()} method initialises each of the
	 * {@code JComponent}s
	 */
	private void createComponents() {
		// System.out.println("MainView.createComponents");
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
	 * The {@code addComponents()} method adds each JComponent to its parent
	 * {@code JPanel}.
	 */
	private void addComponents() {
		// System.out.println("MainView.addComponents");
		controlPanel.add(newBtn);
		controlPanel.add(playBtn);
		controlPanel.add(slowDownBtn);
		controlPanel.add(speedUpBtn);
		controlPanel.add(speedLabel);

		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		mainPanel.add(canvas, BorderLayout.CENTER);
	}

	/**
	 * The {@code addActions()} method adds an {@code ActionListener} to each of the
	 * GUI buttons. Each button updates the {@code SimState} with an enum value
	 * according to the respective action.
	 * 
	 * @see #update(SimState)
	 * @see SimState
	 * @see JButton#addActionListener(ActionListener)
	 * @see ActionListener
	 */
	private void addActions() {

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
	 * The {@code update(SimState)} method is called by the {@code JButton}s in this
	 * class and updates the view accordingly. {@code MainView} does not modify the
	 * {@code Board} or {@code CanvasPanel} directly.<br>
	 * This method is responsible for:<br>
	 * - pausing/playing the simulation<br>
	 * - restarting the simulation.<br>
	 * - speeding up or slowing down the simulation.<br>
	 * - modifying the play/pause button and speedLabel text to account for the
	 * above actions.<br>
	 * 
	 * @see SimState
	 * @see SimController#playPauseSimulation()
	 * @see SimController#restartSimulation()
	 * @see SimController#speedUpSimulation()
	 * @see SimController#slowDownSimulation()
	 * @see JComponent#setText(String)
	 * @see Timer#start()
	 * @see Timer#stop()
	 * @see Timer#setDelay(int)
	 * 
	 * @param state the {@code SimState} to compare
	 */
	private void update(SimState state) {
		// System.out.println("MainView.update");
		switch (state) {
		case PLAYPAUSE:
			playPause();
			break;
		case NEW:
			new SettingsPanel(this, "new simulation options");
			break;
		default:
			changeSpeed(state);
		}
	}

	private void playPause() {
		// System.out.println("MainView.playPause");
		simCtrl.playPauseSimulation();
		if (simCtrl.isRunning()) {
			playBtn.setText("pause");
			refreshTimer.start();
		} else {
			playBtn.setText("play");
			refreshTimer.stop();
		}
	}

	private void changeSpeed(SimState state) {
		// System.out.println("MainView.changeSpeed");
		
		switch (state) {
		case SPEEDUP:
			simCtrl.speedUpSimulation();
			break;
		case SLOWDOWN:
			simCtrl.slowDownSimulation();
			break;
		default:
			// System.out.println("MainView.changeSpeed: state must be SPEEDUP or SLOWDOWN");
			break;
		}

		refreshTimer.setDelay(simCtrl.getFramerate());
		speedLabel.setText("speed: " + simCtrl.getSpeedMultiplier());
	}

	/**
	 * The {@code refresh()} method is called by {@code refreshTimer}'s
	 * {@code ActionListener} while the simulation is running. Each refresh tells
	 * the {@code CanvasPanel} to clear itself, calls the next simulation step, and
	 * tells {@code CanvasPanel} to redraw the updated board.
	 * 
	 * @see SimController#advanceSimulation()
	 * @see CanvasPanel#drawBoard()
	 */
	private void refresh() {
		// System.out.println("MainView.refresh");
		simCtrl.advanceSimulation();
		canvas.repaint();
	}

	/**
	 * The {@code showFrame()} method adds the mainPanel to the frame and displays
	 * the GUI
	 * 
	 * @see JFrame#setLocationRelativeTo(java.awt.Component)
	 * @see JFrame#revalidate()
	 * @see JFrame#repaint()
	 * @see JFrame#pack()
	 */
	private void showFrame() {
		// System.out.println("MainView.showFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);

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
	
	public void setCanvasPanel(int width, int height) {
		// System.out.println("MainView.setCanvasPanel: " + width + ", " + height);
		// canvas.clear();
		canvas.setSize(new Dimension(width, height));
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