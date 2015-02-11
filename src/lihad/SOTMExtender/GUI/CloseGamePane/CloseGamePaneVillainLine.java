package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class CloseGamePaneVillainLine extends JPanel{

	private static final long serialVersionUID = 4522118005183264428L;
	
	private CloseGamePaneTableEntityCard villain_card, environment_card;

	CloseGamePaneVillainLine(Game game){
		super(new FlowLayout(FlowLayout.CENTER, 1, 1));
		
		villain_card = new CloseGamePaneTableEntityCard(game.getVillain(), null);
		environment_card = new CloseGamePaneTableEntityCard(game.getEnvironment(), null);
		
		this.add(villain_card);
		this.add(environment_card);
	}
	
	public CloseGamePaneTableEntityCard getVillainCard(){
		return this.villain_card;
	}
	
	public CloseGamePaneTableEntityCard getEnvironmentCard(){
		return this.environment_card;
	}
}