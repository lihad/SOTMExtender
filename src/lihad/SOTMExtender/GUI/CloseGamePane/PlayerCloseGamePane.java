package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Player;

public class PlayerCloseGamePane extends JPanel{

	private static final long serialVersionUID = 3849156532399404613L;

	private Player player;
	private JCheckBox living;
	private JTextField hp_end;

	PlayerCloseGamePane(Game game, Player player){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.player = player;
		
		this.add(new JLabel("player: "+this.player.getName()+" | hero: "+game.getHero(this.player).getName()));

		living = new JCheckBox("Living?");
		living.setSelected(true);
		this.add(living);

		this.add(new JLabel("HP: "));
		hp_end = new JTextField(3);
		this.add(hp_end);

	}

	public boolean isLiving(){
		return this.living.isSelected();
	}

	public int getHP(){
		return Integer.parseInt(this.hp_end.getText());
	}
	
	public Player getPlayer(){
		return this.player;
	}
}
