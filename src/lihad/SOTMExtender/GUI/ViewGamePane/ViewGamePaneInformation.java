package lihad.SOTMExtender.GUI.ViewGamePane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class ViewGamePaneInformation extends JPanel{

	private static final long serialVersionUID = 163452145232119486L;
	
	ViewGamePaneInformation(Game game){
		super(new BorderLayout());
		
		this.add(new ViewGamePanePlayerLine(game), BorderLayout.CENTER);
		this.add(new ViewGamePaneVillainLine(game), BorderLayout.SOUTH);
		this.add(new ViewGamePaneStatistics(game), BorderLayout.EAST);

	}
}
