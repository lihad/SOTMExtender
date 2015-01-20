package lihad.SOTMExtender.GUI.CloseGamePane;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Objects.Game;

public class CloseGamePaneStatistics extends JPanel{
	
	private static final long serialVersionUID = 389640744895133946L;
	
	JTextField rounds_field;
	JCheckBox victory_box;
	
	CloseGamePaneStatistics(Game game){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel victory_pane = new JPanel(), difficulty_pane = new JPanel(), round_pane = new JPanel();

		victory_pane.add(new JLabel("Victory? "));
		victory_box = new JCheckBox();
		victory_pane.add(victory_box);

		difficulty_pane.add(new JLabel("Difficulty: "+game.getDifficultyType().getTitle()));
		
		round_pane.add(new JLabel("Rounds: "));
		rounds_field = new JTextField(3);
		round_pane.add(rounds_field);
		
		this.add(victory_pane);
		this.add(difficulty_pane);
		this.add(round_pane);
	}
	
	public int getRounds(){
		return Integer.parseInt(this.rounds_field.getText());
	}
	
	public boolean isVictorious(){
		return this.victory_box.isSelected();
	}
}
