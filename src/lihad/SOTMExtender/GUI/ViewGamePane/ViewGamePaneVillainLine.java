package lihad.SOTMExtender.GUI.ViewGamePane;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class ViewGamePaneVillainLine extends JPanel{

	private static final long serialVersionUID = 4522118005183264428L;

	ViewGamePaneVillainLine(Game game){
		this.add(new ViewGamePaneTableEntityCard(game.getVillain(), null, game.getCompletedGameData().getHitpointsAtEnd(game.getVillain()), false));
		this.add(new ViewGamePaneTableEntityCard(game.getEnvironment(), null, game.getCompletedGameData().getHitpointsAtEnd(game.getEnvironment()), false));
	}
}