package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Character;
import lihad.SOTMExtender.Util.Utility;

public class CloseGamePaneTableEntityCard extends JPanel{

	private static final long serialVersionUID = 1605497083623318621L;

	private TableEntity tableentity;
	private Player player;
	private JTextField health_field;

	CloseGamePaneTableEntityCard(TableEntity tableentity, Player player){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.tableentity = tableentity;
		this.player = player;

		JPanel health_pane = new JPanel();

		try {
			this.add(new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(tableentity.getImageFile()),60,90))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(tableentity instanceof Character){
			health_field = new JTextField(2);
			health_field.isEditable();
			health_pane.add(health_field);

			health_pane.add(new JLabel("/"+((Character)tableentity).getHealth()));
			this.add(health_pane);
		}
		else this.add(new JLabel(" "));
	}

	public TableEntity getTableEntity(){
		return this.tableentity;
	}

	public Player getPlayer(){
		return this.player;
	}

	public int getHealth(){
		return Integer.parseInt(this.health_field.getText());
	}
	
	public boolean isLiving(){
		if(Integer.parseInt(this.health_field.getText()) <= 0) return false;
		else return true;
	}
}
