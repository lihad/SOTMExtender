package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Player;

public class CloseGamePanePlayerLine extends JPanel{

	private static final long serialVersionUID = -7906758366113882463L;
	
	Set<CloseGamePaneTableEntityCard> player_cards;
	
	CloseGamePanePlayerLine(Game game){
		super(new FlowLayout(FlowLayout.CENTER, 1, 1));

		player_cards = new HashSet<CloseGamePaneTableEntityCard>();
		for(Player p :game.getPlayers()){
			CloseGamePaneTableEntityCard c = new CloseGamePaneTableEntityCard(game.getHero(p), p);
			player_cards.add(c);
			this.add(c);
		}
	}
	
	public Set<CloseGamePaneTableEntityCard> getPlayerCards(){
		return this.player_cards;
	}

}
