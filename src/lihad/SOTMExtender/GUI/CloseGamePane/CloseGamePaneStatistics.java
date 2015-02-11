package lihad.SOTMExtender.GUI.CloseGamePane;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class CloseGamePaneStatistics extends JPanel{
	
	private static final long serialVersionUID = 389640744895133946L;
	
	JCheckBox victory_box;
	
	CloseGamePaneStatistics(Game game){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel victory_pane = new JPanel(), difficulty_pane = new JPanel(), round_pane = new JPanel();

		victory_box = new JCheckBox("Victory? ");
		victory_pane.add(victory_box);

		difficulty_pane.add(new JLabel("Difficulty: "+game.getDifficultyType().getTitle()));
		
		this.add(victory_pane);
		this.add(difficulty_pane);
		this.add(round_pane);
	}
	
	public boolean isVictorious(){
		return this.victory_box.isSelected();
	}
}
