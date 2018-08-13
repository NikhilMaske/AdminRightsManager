package com.adminrightsmanager.ui;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * The Class SwingFirstExample.
 */
public class SwingFirstExample {

	/** The host name input text. */
	static JTextField hostNameInputText = new JTextField(20);

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Rights Manager");

		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		int windowWidth = 350;
		int windowHeight = 250;
		// set position and size
		frame.setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2,
				windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);
		/*
		 * calling user defined method for adding components
		 * to the panel.
		 */
		placeComponents(panel);

		// Setting the frame visibility to true
		frame.setVisible(true);
	}

	/**
	 * Place components.
	 *
	 * @param panel
	 *            the panel
	 */
	private static void placeComponents(JPanel panel) {
		panel.setLayout(null);

		// Creating JLabel
		JLabel hostNameLabel = new JLabel("Host Name");
		hostNameLabel.setBounds(10, 20, 80, 25);
		panel.add(hostNameLabel);

		hostNameInputText.setBounds(100, 20, 170, 25);
		panel.add(hostNameInputText);

		// Same process for password label and text field.
		JLabel ipAddressLabel = new JLabel("IP Address");
		ipAddressLabel.setBounds(10, 50, 80, 25);
		panel.add(ipAddressLabel);

		JTextField ipAddressInputText1 = new JTextField("0", 3);
		ipAddressInputText1.setBounds(100, 50, 35, 25);
		panel.add(ipAddressInputText1);
		JTextField ipAddressInputText2 = new JTextField("0", 3);
		ipAddressInputText2.setBounds(145, 50, 35, 25);
		panel.add(ipAddressInputText2);
		JTextField ipAddressInputText3 = new JTextField("0", 3);
		ipAddressInputText3.setBounds(190, 50, 35, 25);
		panel.add(ipAddressInputText3);
		JTextField ipAddressInputText4 = new JTextField("0", 3);
		ipAddressInputText4.setBounds(235, 50, 35, 25);
		panel.add(ipAddressInputText4);

		JRadioButton option1 = new JRadioButton("jasjajas");
		JRadioButton option2 = new JRadioButton("asasaaa");
		ButtonGroup group = new ButtonGroup();
		group.add(option1);
		group.add(option2);

		option1.setBounds(10, 90, 35, 25);
		option1.setVisible(true);
		panel.add(option1);
		option2.setBounds(70, 90, 35, 25);
		option1.setVisible(true);
		panel.add(option2);

		JButton addRightsButton = new JButton("Add Rights");

		addRightsButton.setBounds(10, 150, 100, 25);
		panel.add(addRightsButton);

		JButton removeRightsButton = new JButton("Remove Rights");

		removeRightsButton.setBounds(120, 150, 120, 25);
		panel.add(removeRightsButton);

		JButton checkRightsButton = new JButton("Check Rights");

		checkRightsButton.setBounds(240, 150, 100, 25);
		panel.add(checkRightsButton);

		addRightsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Rights Added for " + hostNameInputText.getText() + "",
						"Rights Added", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		removeRightsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Rights Removed for " + hostNameInputText.getText() + "",
						"Rights Removed", JOptionPane.INFORMATION_MESSAGE);

			}
		});
	}
}