package lihad.SOTMExtender.GUI.CloseGamePane;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class CloseGamePaneVillianLine extends JPanel{

	private static final long serialVersionUID = 4522118005183264428L;
	
	private CloseGamePaneTableEntityCard villian_card, environment_card;

	CloseGamePaneVillianLine(Game game){
		
		villian_card = new CloseGamePaneTableEntityCard(game.getVillian(), null);
		environment_card = new CloseGamePaneTableEntityCard(game.getEnvironment(), null);
		
		this.add(villian_card);
		this.add(environment_card);
	}
	
	public CloseGamePaneTableEntityCard getVillianCard(){
		return this.villian_card;
	}
	
	public CloseGamePaneTableEntityCard getEnvironmentCard(){
		return this.environment_card;
	}
}