package main;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Init
{
	private static JFrame frmJumpnnStartPanel;
	private static JSpinner numPlayers;
	private static JSpinner topPlayers;
	private static JSpinner minLayers;
	private static JSpinner maxLayers;
	private static JSpinner minNodes;
	private static JSpinner maxNodes;
	
	
	public static void main(String[] args)
	{
		frmJumpnnStartPanel = new JFrame();
		frmJumpnnStartPanel.setTitle("JumpNN Start Panel");
		frmJumpnnStartPanel.getContentPane().setLayout(null);
		frmJumpnnStartPanel.setPreferredSize(new Dimension(350, 320));
		frmJumpnnStartPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 324, 195);
		frmJumpnnStartPanel.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblNumberOfPlayers = new JLabel("Number Of Players");
		GridBagConstraints gbc_lblNumberOfPlayers = new GridBagConstraints();
		gbc_lblNumberOfPlayers.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfPlayers.gridx = 0;
		gbc_lblNumberOfPlayers.gridy = 0;
		panel.add(lblNumberOfPlayers, gbc_lblNumberOfPlayers);
		lblNumberOfPlayers.setHorizontalAlignment(SwingConstants.LEFT);
		
		topPlayers = new JSpinner();
		SpinnerNumberModel tmp = new SpinnerNumberModel(10, 5, 10, 1);
		topPlayers.setModel(new SpinnerNumberModel(new Integer(10), new Integer(5), null, new Integer(1)));
		
		numPlayers = new JSpinner();
		numPlayers.setModel(new SpinnerNumberModel(100, 10, 10000, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panel.add(numPlayers, gbc_spinner);
		numPlayers.addChangeListener(new ChangeListener() 
		{
	        @Override
	        public void stateChanged(ChangeEvent e) 
	        {
	            tmp.setMaximum((int) numPlayers.getValue()/2);
	            if ((int) numPlayers.getValue() > 10) topPlayers.setValue(10);
	            else topPlayers.setValue(5);
	        }
	    });
		
		JLabel lblNumberOfTop = new JLabel("Number of Top Players");
		GridBagConstraints gbc_lblNumberOfTop = new GridBagConstraints();
		gbc_lblNumberOfTop.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfTop.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfTop.gridx = 0;
		gbc_lblNumberOfTop.gridy = 1;
		panel.add(lblNumberOfTop, gbc_lblNumberOfTop);
		lblNumberOfTop.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		//JSpinner spinner_1 = new JSpinner();
		//spinner_1.setModel(new SpinnerNumberModel(10, 5, 10, 1));
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 1;
		panel.add(topPlayers, gbc_spinner_1);
		
		
		
		JLabel lblMinimumHiddenLayers = new JLabel("Minimum Number Hidden Layers");
		GridBagConstraints gbc_lblMinimumHiddenLayers = new GridBagConstraints();
		gbc_lblMinimumHiddenLayers.anchor = GridBagConstraints.WEST;
		gbc_lblMinimumHiddenLayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinimumHiddenLayers.gridx = 0;
		gbc_lblMinimumHiddenLayers.gridy = 2;
		panel.add(lblMinimumHiddenLayers, gbc_lblMinimumHiddenLayers);
		
		minLayers = new JSpinner();
		minLayers.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 2;
		panel.add(minLayers, gbc_spinner_2);
		
		JLabel lblMaximumNumberOf = new JLabel("Maximum Number of Hidden Layers");
		GridBagConstraints gbc_lblMaximumNumberOf = new GridBagConstraints();
		gbc_lblMaximumNumberOf.anchor = GridBagConstraints.WEST;
		gbc_lblMaximumNumberOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaximumNumberOf.gridx = 0;
		gbc_lblMaximumNumberOf.gridy = 3;
		panel.add(lblMaximumNumberOf, gbc_lblMaximumNumberOf);
		
		maxLayers = new JSpinner();
		maxLayers.setModel(new SpinnerNumberModel(new Integer(3), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
		gbc_spinner_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_3.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_3.gridx = 1;
		gbc_spinner_3.gridy = 3;
		panel.add(maxLayers, gbc_spinner_3);
		
		JLabel lblMinimumNumberOf = new JLabel("Minimum Number of Nodes Per Layer");
		GridBagConstraints gbc_lblMinimumNumberOf = new GridBagConstraints();
		gbc_lblMinimumNumberOf.anchor = GridBagConstraints.WEST;
		gbc_lblMinimumNumberOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinimumNumberOf.gridx = 0;
		gbc_lblMinimumNumberOf.gridy = 4;
		panel.add(lblMinimumNumberOf, gbc_lblMinimumNumberOf);
		
		minNodes = new JSpinner();
		minNodes.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spinner_5 = new GridBagConstraints();
		gbc_spinner_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_5.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_5.gridx = 1;
		gbc_spinner_5.gridy = 4;
		panel.add(minNodes, gbc_spinner_5);
		
		JLabel lblMaximumNumberOf_1 = new JLabel("Maximum Number Of Nodes Per Layer");
		GridBagConstraints gbc_lblMaximumNumberOf_1 = new GridBagConstraints();
		gbc_lblMaximumNumberOf_1.anchor = GridBagConstraints.WEST;
		gbc_lblMaximumNumberOf_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaximumNumberOf_1.gridx = 0;
		gbc_lblMaximumNumberOf_1.gridy = 5;
		panel.add(lblMaximumNumberOf_1, gbc_lblMaximumNumberOf_1);
		
		maxNodes = new JSpinner();
		maxNodes.setModel(new SpinnerNumberModel(new Integer(3), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spinner_4 = new GridBagConstraints();
		gbc_spinner_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_4.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_4.gridx = 1;
		gbc_spinner_4.gridy = 5;
		panel.add(maxNodes, gbc_spinner_4);
		
		JLabel lblMinimumFramesBetween = new JLabel("Minimum Frames Between Obstacles");
		GridBagConstraints gbc_lblMinimumFramesBetween = new GridBagConstraints();
		gbc_lblMinimumFramesBetween.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinimumFramesBetween.gridx = 0;
		gbc_lblMinimumFramesBetween.gridy = 6;
		panel.add(lblMinimumFramesBetween, gbc_lblMinimumFramesBetween);
		
		minNext = new JSpinner();
		minNext.setModel(new SpinnerNumberModel(new Integer(30), new Integer(10), null, new Integer(1)));
		//GridBagConstraints gbc_spinner_12;
		gbc_minNext = new GridBagConstraints();
		gbc_minNext.fill = GridBagConstraints.HORIZONTAL;
		gbc_minNext.insets = new Insets(0, 0, 5, 0);
		gbc_minNext.gridx = 1;
		gbc_minNext.gridy = 6;
		panel.add(minNext, gbc_minNext);
		
		JLabel lblMaximumFramesBetween = new JLabel("Maximum Frames Between Obstacles");
		GridBagConstraints gbc_lblMaximumFramesBetween = new GridBagConstraints();
		gbc_lblMaximumFramesBetween.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaximumFramesBetween.gridx = 0;
		gbc_lblMaximumFramesBetween.gridy = 7;
		panel.add(lblMaximumFramesBetween, gbc_lblMaximumFramesBetween);
		
		maxNext = new JSpinner();
		maxNext.setModel(new SpinnerNumberModel(new Integer(60), new Integer(10), null, new Integer(1)));
		//GridBagConstraints gbc_spinner2;
		gbc_maxNext = new GridBagConstraints();
		gbc_maxNext.fill = GridBagConstraints.HORIZONTAL;
		gbc_maxNext.gridx = 1;
		gbc_maxNext.gridy = 7;
		panel.add(maxNext, gbc_maxNext);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 206, 310, 78);
		frmJumpnnStartPanel.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnNewButton = new JButton("Begin");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(86, 21, 128, 35);
		panel_1.add(btnNewButton);
		
		errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(0, 0, 300, 14);
		panel_1.add(errorLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(216, 150, 46, 14);
		frmJumpnnStartPanel.getContentPane().add(label);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				begin();
			}
		});
		
		frmJumpnnStartPanel.pack();
		frmJumpnnStartPanel.setVisible(true);
		
		
	}
	private static JLabel errorLabel;
	
	private static JButton btnNewButton;
	private static GridBagConstraints gbc_minNext;
	private static GridBagConstraints gbc_maxNext;
	static JSpinner minNext;
	static JSpinner maxNext;
	
	
	
	private static void begin()
	{
		errorLabel.setText("");
		try
		{
			boolean error = false;
			Main.numPlayers = (int) numPlayers.getValue();
			Main.topPlayers = (int) topPlayers.getValue();
			if (Main.topPlayers >= Main.numPlayers) 
			{
				numPlayers.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				topPlayers.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				error = true;
			}
			else
			{
				numPlayers.getEditor().getComponent(0).setBackground(new Color(255,255,255));
				topPlayers.getEditor().getComponent(0).setBackground(new Color(255,255,255));
			}
			
			
			Main.minLayers = (int) minLayers.getValue();
			Main.maxLayers = (int) maxLayers.getValue();
			if (Main.minLayers > Main.maxLayers) 
			{
				minLayers.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				maxLayers.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				error = true;
			}
			else
			{
				minLayers.getEditor().getComponent(0).setBackground(new Color(255,255,255));
				maxLayers.getEditor().getComponent(0).setBackground(new Color(255,255,255));
			}
			
			Main.minNodes = (int) minNodes.getValue();
			Main.maxNodes = (int) maxNodes.getValue();
			if (Main.minNodes > Main.maxNodes) 
			{
				minNodes.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				maxNodes.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				error = true;
			}
			else
			{
				minNodes.getEditor().getComponent(0).setBackground(new Color(255,255,255));
				maxNodes.getEditor().getComponent(0).setBackground(new Color(255,255,255));
			}
			
			Main.minNext = (int) minNext.getValue();
			Main.maxNext = (int) maxNext.getValue();
			if (Main.minNext > Main.maxNext) 
			{
				minNext.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				maxNext.getEditor().getComponent(0).setBackground(new Color(255,100,100));
				error = true;
			}
			else
			{
				minNext.getEditor().getComponent(0).setBackground(new Color(255,255,255));
				maxNext.getEditor().getComponent(0).setBackground(new Color(255,255,255));
			}
			
			
			if (error)
			{
				errorLabel.setText("Error");
				return;
			}
			
			frmJumpnnStartPanel.dispose();
			run();	
		}
		catch (NumberFormatException nfe) 
		{
			errorLabel.setText("Error");
            nfe.printStackTrace();
        }
		
		
		
	}
	
	private static void run()
	{
		Main main = new Main();
		main.run();
	}
}
