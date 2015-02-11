package lihad.SOTMExtender.GUI.ViewGamePane;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class ViewGamePaneStatistics extends JPanel{
	
	private static final long serialVersionUID = 389640744895133946L;
	
	ViewGamePaneStatistics(Game game){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel victory_pane = new JPanel(), difficulty_pane = new JPanel(), award_pane = new JPanel();

		victory_pane.add(new JLabel("Victory? "));
		victory_pane.add(game.getCompletedGameData().isVictorious() ? new JLabel("YES") : new JLabel("NO"));

		difficulty_pane.add(new JLabel("Difficulty: "));
		difficulty_pane.add(new JLabel(game.getDifficultyType().getTitle()));
		
		JLabel label = new JLabel(game.getCompletedGameData().getAward().getName());
		label.setForeground(Color.MAGENTA);
		
		award_pane.add(label);
				
		this.add(victory_pane);
		this.add(difficulty_pane);
		this.add(award_pane);
	}
}
