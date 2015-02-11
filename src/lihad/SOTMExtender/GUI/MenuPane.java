package lihad.SOTMExtender.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.EntityEntryPane.EntityEntryPaneType;

public class MenuPane extends JMenuBar{


	private static final long serialVersionUID = -7914187768188543191L;

	private JMenu extender, game, statistics, entry_menu;
	private JMenuItem playerEditItem, heroEditItem, villainEditItem, environmentEditItem, exitItem, addGameItem, closeGameItem, viewGameItem,
	generalStatisticItem, playerStatisticItem;

	MenuPane(){

		//the 'Extender menu options

		extender = new JMenu("Extender");
		this.add(extender);
		
		////////////////////////
		
		entry_menu = new JMenu("Entity Entry");

		playerEditItem = new JMenuItem("Player");
		playerEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadEntityEditPane(EntityEntryPaneType.PLAYER);
			}
		});
		entry_menu.add(playerEditItem);

		entry_menu.addSeparator();

		heroEditItem = new JMenuItem("Hero");
		heroEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadEntityEditPane(EntityEntryPaneType.HERO);
			}
		});
		entry_menu.add(heroEditItem);

		villainEditItem = new JMenuItem("Villain");
		villainEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadEntityEditPane(EntityEntryPaneType.VILLAIN);
			}
		});
		entry_menu.add(villainEditItem);

		environmentEditItem = new JMenuItem("Environment");
		environmentEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadEntityEditPane(EntityEntryPaneType.ENVIRONMENT);
			}
		});
		entry_menu.add(environmentEditItem);

		extender.add(entry_menu);
		
		
		
		extender.addSeparator();

		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		extender.add(exitItem);

		// the 'Game' menu options

		game = new JMenu("Game");
		this.add(game);

		addGameItem = new JMenuItem("New Game");
		addGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadNewGamePane();
			}
		});
		game.add(addGameItem);

		closeGameItem = new JMenuItem("Close Game");
		closeGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadCloseGamePane(null);
			}
		});
		game.add(closeGameItem);
		
		game.addSeparator();
		
		viewGameItem = new JMenuItem("View Game");
		viewGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadViewGamePane(null);
			}
		});
		game.add(viewGameItem);
		
		statistics = new JMenu("Statistics");
		this.add(statistics);
		
		generalStatisticItem = new JMenuItem("General Stats");
		generalStatisticItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Extender.getGUI().loadPlayerCreationPane();
			}
		});
		statistics.add(generalStatisticItem);
		
		statistics.addSeparator();
		
		playerStatisticItem = new JMenuItem("Player Stats");
		playerStatisticItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Extender.getGUI().loadPlayerCreationPane();
			}
		});
		statistics.add(playerStatisticItem);
		
	}
}
