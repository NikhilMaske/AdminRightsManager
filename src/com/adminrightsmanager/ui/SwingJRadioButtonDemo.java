package com.adminrightsmanager.ui;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * The Class SwingJRadioButtonDemo.
 */
@SuppressWarnings("serial")
public class SwingJRadioButtonDemo extends JFrame {

	/**
	 * Instantiates a new swing J radio button demo.
	 */
	public SwingJRadioButtonDemo() {
		super("Swing JRadioButton Demo");

		JRadioButton option1 = new JRadioButton("Linux");
		JRadioButton option2 = new JRadioButton("Windows");
		JRadioButton option3 = new JRadioButton("Macintosh");

		ButtonGroup group = new ButtonGroup();
		group.add(option1);
		group.add(option2);
		group.add(option3);

		setLayout(new FlowLayout());

		add(option1);
		add(option2);
		add(option3);

		pack();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SwingJRadioButtonDemo().setVisible(true);
			}
		});
	}
}