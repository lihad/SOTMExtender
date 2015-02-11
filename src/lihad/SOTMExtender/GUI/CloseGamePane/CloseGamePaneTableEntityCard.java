package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Villain;
import lihad.SOTMExtender.Util.Utility;

public class CloseGamePaneTableEntityCard extends JPanel{

	private static final long serialVersionUID = 1605497083623318621L;

	private TableEntity tableentity;
	private Player player;
	private JCheckBox alive;

	CloseGamePaneTableEntityCard(TableEntity tableentity, Player player){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.tableentity = tableentity;
		this.player = player;
		
		if(player != null)this.add(new JLabel(player.getName()));

		try {
			this.add(new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(tableentity.getImageFile()),65,97))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel pane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 1, 1));


		if(tableentity instanceof Hero){
			pane.add(new JLabel("lvl "+player.getLevel((Hero)tableentity)));
		}else if(tableentity instanceof Villain){
			pane.add(new JLabel(((Villain)tableentity).isAdvanced() ? "Advanced" : "Normal"));
		}
		else pane.add(new JLabel(" "));
		
		alive = new JCheckBox("alive");
		alive.setSelected(true);
		pane.add(alive);
		this.add(pane);
	}

	public TableEntity getTableEntity(){
		return this.tableentity;
	}

	public Player getPlayer(){
		return this.player;
	}
	
	public boolean isAlive(){
		return this.alive.isSelected();
	}
}
