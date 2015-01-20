package lihad.SOTMExtender.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.TableEntityEditPane.TableEntityEditPaneType;
import lihad.SOTMExtender.Objects.Hero;

public class MenuPane extends JMenuBar{


	private static final long serialVersionUID = -7914187768188543191L;

	private JMenu extender, game, create_menu, edit_menu;
	private JMenuItem playerCreateItem, heroCreateItem, villianCreateItem, environmentCreateItem,
	playerEditItem, heroEditItem, villianEditItem, environmentEditItem, exitItem, addGameItem, closeGameItem, viewGameItem;

	MenuPane(){

		//the 'Extender menu options

		extender = new JMenu("Extender");
		this.add(extender);

		create_menu = new JMenu("Create ...");
		create_menu.setMnemonic(KeyEvent.VK_C);

		playerCreateItem = new JMenuItem("Player");
		playerCreateItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadPlayerCreationPane();
			}
		});
		create_menu.add(playerCreateItem);

		create_menu.addSeparator();

		heroCreateItem = new JMenuItem("Hero");
		heroCreateItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadHeroCreationPane();
			}
		});
		create_menu.add(heroCreateItem);

		villianCreateItem = new JMenuItem("Villian");
		villianCreateItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadVillianCreationPane();
			}
		});
		create_menu.add(villianCreateItem);

		environmentCreateItem = new JMenuItem("Environment");
		environmentCreateItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadEnvironmentCreationPane();
			}
		});
		create_menu.add(environmentCreateItem);

		extender.add(create_menu);
		
		////////////////////////
		
		edit_menu = new JMenu("Edit ...");

		playerEditItem = new JMenuItem("Player");
		playerEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Extender.getGUI().loadPlayerEditPane();
			}
		});
		edit_menu.add(playerEditItem);

		edit_menu.addSeparator();

		heroEditItem = new JMenuItem("Hero");
		heroEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadTableEntityEditPane(TableEntityEditPaneType.HERO);
			}
		});
		edit_menu.add(heroEditItem);

		villianEditItem = new JMenuItem("Villian");
		villianEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadTableEntityEditPane(TableEntityEditPaneType.VILLIAN);
			}
		});
		edit_menu.add(villianEditItem);

		environmentEditItem = new JMenuItem("Environment");
		environmentEditItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadTableEntityEditPane(TableEntityEditPaneType.ENVIRONMENT);
			}
		});
		edit_menu.add(environmentEditItem);

		extender.add(edit_menu);
		
		
		
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


	}
}
