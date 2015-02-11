package lihad.SOTMExtender.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import lihad.SOTMExtender.GUI.CloseGamePane.CloseGamePane;
import lihad.SOTMExtender.GUI.EntityEntryPane.EntityEntryPane;
import lihad.SOTMExtender.GUI.EntityEntryPane.EntityEntryPaneType;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.GUI.ViewGamePane.ViewGamePane;
import lihad.SOTMExtender.Objects.Game;

public class Interface extends JFrame{

	private static final long serialVersionUID = 5228372618960861427L;
	
	private MenuPane menu_pane;
	private CloseGamePane close_game_pane;
	private ViewGamePane view_game_pane;
	private NewGamePane new_game_pane;
	private EntityEntryPane entity_edit_pane;
	
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
	
	public void loadCloseGamePane(Game game){
		removeAllPanes();
		close_game_pane = new CloseGamePane(game);
		loadPaneCenter(close_game_pane);
	}

	public void loadViewGamePane(Game game){
		removeAllPanes();
		view_game_pane = new ViewGamePane(game);
		loadPaneCenter(view_game_pane);
	}
	
	public void loadEntityEditPane(EntityEntryPaneType type){
		removeAllPanes();
		entity_edit_pane = new EntityEntryPane(type);
		loadPaneCenter(entity_edit_pane);
	}
	
	public void removeAllPanes(){
		if(new_game_pane != null)this.remove(new_game_pane);
		if(close_game_pane != null)this.remove(close_game_pane);
		if(view_game_pane != null)this.remove(view_game_pane);
		if(entity_edit_pane != null)this.remove(entity_edit_pane);
	}
	
	private void loadPaneCenter(JPanel pane){
		this.add(pane, BorderLayout.CENTER);
		this.pack();
		this.repaint();
	}
}
