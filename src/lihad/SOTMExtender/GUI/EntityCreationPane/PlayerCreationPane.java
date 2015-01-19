package lihad.SOTMExtender.GUI.EntityCreationPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.Player;

public class PlayerCreationPane extends JPanel{
	
	private static final long serialVersionUID = -4980146037251264183L;

	private JTextField name, fullname;
	private JButton createbutton;
	private JLabel notifysuccess;
	protected JPanel east_pane, west_pane;
	
	public PlayerCreationPane(){
		super(new BorderLayout());

		setPreferredSize(new Dimension(100,100));
		setSize(new Dimension(100,100));

		east_pane = new JPanel(new BorderLayout());
		west_pane = new JPanel(new BorderLayout());

		west_pane.setPreferredSize(new Dimension(350,60));

		this.setBorder(BorderFactory.createLineBorder(Color.black));

		name = new JTextField(9);
		name.setEditable(true);
		
		fullname = new JTextField(9);
		fullname.setEditable(true);

		createbutton = new JButton("create");

		createbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Player player = new Player(PlayerCreationPane.this.name.getText(), PlayerCreationPane.this.fullname.getText());
				Extender.addPlayer(player);
				PlayerCreationPane.this.notifysuccess.setText("created "+PlayerCreationPane.this.name.getText());
				Extender.savePlayerData(player);
				PlayerCreationPane.this.name.setText(null);
			}
		});		

		notifysuccess = new JLabel();

		JPanel name_west_pane = new JPanel();

		name_west_pane.add(new JLabel("Name:"));
		name_west_pane.add(name);
		
		name_west_pane.add(new JLabel("Full Name:"));
		name_west_pane.add(fullname);
		
		east_pane.add(notifysuccess, BorderLayout.NORTH);
		east_pane.add(createbutton, BorderLayout.SOUTH);

		east_pane.setPreferredSize(new Dimension(200,200));

		west_pane.add(name_west_pane);

		JPanel dumb_west_pane = new JPanel();
		dumb_west_pane.add(west_pane);

		this.add(dumb_west_pane, BorderLayout.WEST);
		this.add(east_pane, BorderLayout.EAST);
		this.setVisible(true);

		repaint();
	}

}
