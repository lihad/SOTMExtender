package lihad.SOTMExtender.GUI.ViewGamePane;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class ViewGamePaneVillianLine extends JPanel{

	private static final long serialVersionUID = 4522118005183264428L;

	ViewGamePaneVillianLine(Game game){
		this.add(new ViewGamePaneTableEntityCard(game.getVillian(), null, game.getCompletedGameData().getHitpointsAtEnd(game.getVillian()), false));
		this.add(new ViewGamePaneTableEntityCard(game.getEnvironment(), null, game.getCompletedGameData().getHitpointsAtEnd(game.getEnvironment()), false));
	}
}