package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class Window
{
	JLabel livingLbl = new JLabel("60");
	JLabel genLbl = new JLabel("60");
	JLabel tfrLbl = new JLabel("60");
	JLabel afrLbl = new JLabel("60");

	public void setLabels(String living, String generation, String targetFr, String actFr)
	{
		livingLbl.setText(living);
		genLbl.setText(generation);
		tfrLbl.setText(targetFr);
		afrLbl.setText(actFr);
	}

	Window()
	{
		JFrame frame = new JFrame("JumpNN Control Panel");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JButton btnNewButton = new JButton("Show Neural Network");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Main.show = !Main.show;
				if (Main.show)
					btnNewButton.setText("Hide Neural Network");
				else
					btnNewButton.setText("Show Neural Network");
			}
		});

		JLabel lblLivingPlayers = new JLabel("Living players");
		GridBagConstraints gbc_lblLivingPlayers = new GridBagConstraints();
		gbc_lblLivingPlayers.anchor = GridBagConstraints.WEST;
		gbc_lblLivingPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblLivingPlayers.gridx = 1;
		gbc_lblLivingPlayers.gridy = 0;
		frame.getContentPane().add(lblLivingPlayers, gbc_lblLivingPlayers);

		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		frame.getContentPane().add(livingLbl, gbc_label);

		JButton btnNewButton_1 = new JButton("Raise Framerate by 10");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (Main.fr >= 300)
					Main.fr += 100;
				else
					Main.fr += 10;
				if (!Main.turbo)
					Functions.p.frameRate(Main.fr);
			}
		});

		JLabel lblCurrentGeneration = new JLabel("Current generation");
		GridBagConstraints gbc_lblCurrentGeneration = new GridBagConstraints();
		gbc_lblCurrentGeneration.anchor = GridBagConstraints.WEST;
		gbc_lblCurrentGeneration.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentGeneration.gridx = 1;
		gbc_lblCurrentGeneration.gridy = 1;
		frame.getContentPane().add(lblCurrentGeneration, gbc_lblCurrentGeneration);

		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		frame.getContentPane().add(genLbl, gbc_label_1);

		JButton btnNewButton_2 = new JButton("Lower Framerate by 10");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 2;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (Main.fr - 10 <= 0)
					return;
				if (Main.fr >= 300)
					Main.fr -= 100;
				else
					Main.fr -= 10;
				if (!Main.turbo)
					Functions.p.frameRate(Main.fr);
			}
		});

		JLabel lblTargetFramerate = new JLabel("Target framerate");
		GridBagConstraints gbc_lblTargetFramerate = new GridBagConstraints();
		gbc_lblTargetFramerate.anchor = GridBagConstraints.WEST;
		gbc_lblTargetFramerate.insets = new Insets(0, 0, 5, 5);
		gbc_lblTargetFramerate.gridx = 1;
		gbc_lblTargetFramerate.gridy = 2;
		frame.getContentPane().add(lblTargetFramerate, gbc_lblTargetFramerate);

		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 2;
		frame.getContentPane().add(tfrLbl, gbc_label_2);

		JButton btnNewButton_3 = new JButton("Enable turbo mode");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 3;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Main.turbo = !Main.turbo;
				if (Main.turbo)
				{
					Functions.p.frameRate(999999);
					btnNewButton_3.setText("Disable turbo mode");
				}
					
				else
				{
					Functions.p.frameRate(Main.fr);
					btnNewButton_3.setText("Enable turbo mode");
				}
					
			}
		});

		JLabel lblActualFramerate = new JLabel("Actual framerate");
		GridBagConstraints gbc_lblActualFramerate = new GridBagConstraints();
		gbc_lblActualFramerate.anchor = GridBagConstraints.WEST;
		gbc_lblActualFramerate.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualFramerate.gridx = 1;
		gbc_lblActualFramerate.gridy = 3;
		frame.getContentPane().add(lblActualFramerate, gbc_lblActualFramerate);

		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 2;
		gbc_label_3.gridy = 3;
		frame.getContentPane().add(afrLbl, gbc_label_3);

		JButton btnNewButton_4 = new JButton("Generate offspring");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 4;
		frame.getContentPane().add(btnNewButton_4, gbc_btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Main.doOffspring = true;
			}
		});

		JLabel label = new JLabel("          ");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.insets = new Insets(0, 0, 5, 0);
		gbc_label1.gridx = 2;
		gbc_label1.gridy = 4;
		frame.getContentPane().add(label, gbc_label1);

		JButton btnNewButton_5 = new JButton("Hide obstacles and players");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 0;
		gbc_btnNewButton_5.gridy = 5;
		frame.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Main.showOb = !Main.showOb;
				Main.showPl = !Main.showPl;
				if (Main.showPl)
					btnNewButton_5.setText("Hide obstacles and players");
				else
					btnNewButton_5.setText("Show obstacles and players");
			}
		});

		JButton btnNewButton_6 = new JButton("Enable slow mode");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 0;
		gbc_btnNewButton_6.gridy = 6;
		frame.getContentPane().add(btnNewButton_6, gbc_btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (Main.isSlow)
				{
					Main.isSlow = false;
					Functions.p.frameRate(Main.fr);
					btnNewButton_6.setText("Enable slow mode");

				}
				else
				{
					Main.isSlow = true;
					Functions.p.frameRate(1);
					btnNewButton_6.setText("Disable slow mode");
				}
			}
		});

		JButton btnNewButton_7 = new JButton("Make new generation");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 0;
		gbc_btnNewButton_7.gridy = 7;
		frame.getContentPane().add(btnNewButton_7, gbc_btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Main.doGraveyard = true;
			}
		});

		frame.setVisible(true);
	}

}
