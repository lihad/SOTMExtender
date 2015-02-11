package lihad.SOTMExtender.GUI.NewGamePane;

import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Player;

public class NewGamePaneInitialPlayerInformation extends JPanel{

	private static final long serialVersionUID = -1721802297312172057L;

	private Set<NewGamePaneInitialPlayerLine> player_lines;

	public NewGamePaneInitialPlayerInformation(NewGamePane initial){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		player_lines = new HashSet<NewGamePaneInitialPlayerLine>();

		for(int i = 0; i < 5; i++){ 
			NewGamePaneInitialPlayerLine playerline = new NewGamePaneInitialPlayerLine(initial);
			player_lines.add(playerline);
			this.add(playerline);
		}
	}
	
	public Set<Player> getSelectedPlayers(){
		Set<Player> players = new HashSet<Player>();
		for(NewGamePaneInitialPlayerLine line : player_lines){
			if(line.getPlayer() != null)players.add(line.getPlayer());
		}
		return players;
	}
	
	protected Set<NewGamePaneInitialPlayerLine> getPlayerLines(){
		return player_lines;
	}
	
	protected void updatePlayerGoldLabels(){
		for(NewGamePaneInitialPlayerLine t : player_lines){
			t.updateGoldLabel();
		}
	}
	
	protected void lockPlayerCombos(){
		for(NewGamePaneInitialPlayerLine line : player_lines){
			line.getPlayerCombo().setEnabled(false);
		}
	}
	
	protected void lockPotionCombos(){
		for(NewGamePaneInitialPlayerLine line : player_lines){
			line.getPotionCombo().setEnabled(false);
		}
	}
	
	protected void unlockPotionCombos(){
		for(NewGamePaneInitialPlayerLine line : player_lines){
			if(line.getPlayerCombo().getSelectedItem() != null) line.getPotionCombo().setEnabled(true);
		}
	}
}
