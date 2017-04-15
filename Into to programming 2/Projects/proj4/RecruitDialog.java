/**
 * File: RecruitDialog.java
 * Author: J. Tang
 *
 * This file contains the recruit user interface.
 *
 * DO NOT edit this file.
 * DO NOT submit this file.
 *
 */
package proj4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class RecruitDialog extends JDialog {
    public RecruitDialog(GameInterface g) {
	game = g;

	setTitle("Ants Versus Zombies");
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    System.exit(0);
		}
	    });

	JLabel title = new JLabel("Recruit Ants", SwingConstants.CENTER);
	title.setFont(new Font("SansSerif", Font.BOLD, 24));
	title.setForeground(Color.BLUE);
	add(title, BorderLayout.NORTH);

	JPanel p = new JPanel(new GridBagLayout());
	add(p, BorderLayout.CENTER);
	GridBagConstraints c = new GridBagConstraints();

	JLabel roundLabel = new JLabel("Round: ", SwingConstants.RIGHT);
	roundLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
	c.gridx = 0;
	c.gridy = 0;
	c.weightx = 1;
	c.weighty = 0;
	c.ipadx = 4;
	c.ipady = 20;
	c.anchor = GridBagConstraints.EAST;
	p.add(roundLabel, c);

	JLabel roundNum = new JLabel("" + game.getRoundNumber(), SwingConstants.LEFT);
	roundNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
	c.gridx = 1;
	c.anchor = GridBagConstraints.WEST;
	p.add(roundNum, c);

	JLabel foodLabel = new JLabel("Food: ", SwingConstants.RIGHT);
	foodLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
	c.gridx = 2;
	c.anchor = GridBagConstraints.EAST;
	p.add(foodLabel, c);

	foodNum = new JLabel("" + game.getFood(), SwingConstants.LEFT);
	foodNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
	c.gridx = 3;
	c.anchor = GridBagConstraints.WEST;
	p.add(foodNum, c);

	final String antNames[] = game.getAntTypes();
	c.gridx = 0;
	c.gridy = 1;
	c.fill = GridBagConstraints.HORIZONTAL;
	c.anchor = GridBagConstraints.CENTER;
	c.weightx = 0;
	c.ipady = 0;
	for (final String s : antNames) {
	    int cost = game.getAntCost(s);
	    JButton b = new JButton(s + " - Cost: " + cost);
	    b.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			if (game.recruitAnt(s)) {
			    foodNum.setText("" + game.getFood());
			    colony.setText(game.getColonyDesc());
			}
		    }
		});
	    p.add(b, c);
	    c.gridy++;
	}

	colony = new JTextArea(10, 20);
	colony.setEditable(false);
	colony.setText(game.getColonyDesc());
	JScrollPane sp = new JScrollPane(colony);
	sp.setBorder(new TitledBorder("Colony"));
	c.gridx = 1;
	c.gridy = 1;
	c.weightx = 1;
	c.weighty = 1;
	c.ipadx = 10;
	c.ipady = 10;
	c.gridwidth = 3;
	c.gridheight = antNames.length;
	c.fill = GridBagConstraints.BOTH;
	c.anchor = GridBagConstraints.CENTER;
	p.add(sp, c);

	JButton b = new JButton("Done");
	b.setBackground(Color.GREEN);
	b.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });

	add(b, BorderLayout.SOUTH);

	setModal(true);
	pack();
	setVisible(true);
    }

    private final GameInterface game;
    private final JLabel foodNum;
    private final JTextArea colony;
}