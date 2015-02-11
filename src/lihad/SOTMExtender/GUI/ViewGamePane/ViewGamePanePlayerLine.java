package lihad.SOTMExtender.GUI.ViewGamePane;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Player;

public class ViewGamePanePlayerLine extends JPanel{

	private static final long serialVersionUID = -7906758366113882463L;
	
	ViewGamePanePlayerLine(Game game){
		for(Player p :game.getPlayers()){
			this.add(new ViewGamePaneTableEntityCard(game, game.getHero(p), p, (game.getHero(p).equals(game.getCompletedGameData().getAwardRecipient()) ? game.getCompletedGameData().getAward() : null)));
		}
	}

}
