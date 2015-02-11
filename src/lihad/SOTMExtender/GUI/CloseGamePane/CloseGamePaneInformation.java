package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Game;

public class CloseGamePaneInformation extends JPanel{

	private static final long serialVersionUID = 163452145232119486L;
	
	private CloseGamePanePlayerLine cgppl;
	private CloseGamePaneVillainLine cgpvl;
	private CloseGamePaneStatistics cgps;
	private Game game;
	
	CloseGamePaneInformation(Game game){
		super(new BorderLayout());
		
		this.game = game;
		
		cgppl = new CloseGamePanePlayerLine(game);
		cgpvl = new CloseGamePaneVillainLine(game);
		cgps = new CloseGamePaneStatistics(game);
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(cgppl);
		p.add(cgpvl);
		this.add(p, BorderLayout.CENTER);
		
		JPanel o = new JPanel(new BorderLayout());
		o.add(cgps, BorderLayout.NORTH);
		this.add(cgps, BorderLayout.EAST);
	}
	
	public CloseGamePanePlayerLine getPlayerLine(){
		return this.cgppl;
	}
	
	public CloseGamePaneVillainLine getVillainLine(){
		return this.cgpvl;
	}
	
	public CloseGamePaneStatistics getStatisticsPane(){
		return this.cgps;
	}
	
	public Game getGame(){
		return this.game;
	}
}
