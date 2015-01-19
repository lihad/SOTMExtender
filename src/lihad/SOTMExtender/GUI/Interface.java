package lihad.SOTMExtender.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import lihad.SOTMExtender.GUI.CloseGamePane.CloseGamePane;
import lihad.SOTMExtender.GUI.EntityCreationPane.EnvironmentCreationPane;
import lihad.SOTMExtender.GUI.EntityCreationPane.HeroCreationPane;
import lihad.SOTMExtender.GUI.EntityCreationPane.PlayerCreationPane;
import lihad.SOTMExtender.GUI.EntityCreationPane.VillianCreationPane;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.GUI.ViewGamePane.ViewGamePane;

public class Interface extends JFrame{

	private static final long serialVersionUID = 5228372618960861427L;
	
	private MenuPane menu_pane;
	private HeroCreationPane hero_pane;
	private VillianCreationPane villian_pane;
	private EnvironmentCreationPane environment_pane;
	private PlayerCreationPane player_pane;
	private CloseGamePane close_game_pane;
	private ViewGamePane view_game_pane;
	private NewGamePane new_game_pane;
	
	public Interface(){
		this.setSize(600,400);
		this.setPreferredSize(new Dimension(600,400));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		menu_pane = new MenuPane();
		
		this.setJMenuBar(menu_pane);

		this.setVisible(true);
		pack();
		repaint();
	}
	
	public MenuPane getMenuPane(){
		return this.menu_pane;
	}
	
	public void loadNewGamePane(){
		removeAllPanes();
		new_game_pane = new NewGamePane();
		loadPaneCenter(new_game_pane);
	}
	
	public void loadCloseGamePane(){
		removeAllPanes();
		close_game_pane = new CloseGamePane();
		loadPaneNorth(close_game_pane);
	}

	public void loadViewGamePane(){
		removeAllPanes();
		view_game_pane = new ViewGamePane();
		loadPaneCenter(view_game_pane);
	}
	
	public void loadPlayerCreationPane(){
		removeAllPanes();
		player_pane = new PlayerCreationPane();
		loadPaneCenter(player_pane);
	}
	
	public void loadHeroCreationPane(){
		removeAllPanes();
		hero_pane = new HeroCreationPane();
		loadPaneCenter(hero_pane);
	}
	
	public void loadVillianCreationPane(){
		removeAllPanes();
		villian_pane = new VillianCreationPane();
		loadPaneCenter(villian_pane);
	}
	
	public void loadEnvironmentCreationPane(){
		removeAllPanes();
		environment_pane = new EnvironmentCreationPane();
		loadPaneCenter(environment_pane);
	}
	
	public void removeAllPanes(){
		if(player_pane != null)this.remove(player_pane);
		if(hero_pane != null)this.remove(hero_pane);
		if(villian_pane != null)this.remove(villian_pane);
		if(environment_pane != null)this.remove(environment_pane);
		if(new_game_pane != null)this.remove(new_game_pane);
		if(close_game_pane != null)this.remove(close_game_pane);
		if(view_game_pane != null)this.remove(view_game_pane);
	}
	
	private void loadPaneCenter(JPanel pane){
		this.add(pane, BorderLayout.CENTER);
		this.pack();
		this.repaint();
	}

	private void loadPaneNorth(JPanel pane){
		this.add(pane, BorderLayout.NORTH);
		this.pack();
		this.repaint();
	}
}
