package com.iridiumflair.sim.view;

/**
 * {@code LFStrings} provides easy access to Swing's various Look and Feel
 * settings
 * 
 * @author Joshua Woodyatt - <a href="https://github.com/tigjaw">GitHub</a>
 */
public class LFStrings {

	// GUI LOOK & FEEL STRINGS

	/** L&F for Linux and Solaris, if GTK+ 2.2 or later is installed */
	public final static String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	/** L&F for Linux and Solaris, if GTK+ 2.2 or later is not installed */
	public final static String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	/** L&F for Windows */
	public final static String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	/** L&F for cross-platform (Metal) */
	public final static String CROSS = "javax.swing.plaf.metal.MetalLookAndFeel";
	/** L&F native to the system it is running on */
	public final static String SYSTEM = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

}