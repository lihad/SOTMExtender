package lihad.SOTMExtender.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lihad.SOTMExtender.Extender;

public class MenuPane extends JMenuBar{


	private static final long serialVersionUID = -7914187768188543191L;

	private JMenu extender, game, create_menu;
	private JMenuItem playerItem, heroItem, villianItem, environmentItem, exitItem, addGameItem, closeGameItem;

	MenuPane(){

		//the 'Extender menu options

		extender = new JMenu("Extender");
		this.add(extender);

		create_menu = new JMenu("Create ...");
		create_menu.setMnemonic(KeyEvent.VK_C);

		playerItem = new JMenuItem("Player");
		playerItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadPlayerCreationPane();
			}
		});
		create_menu.add(playerItem);

		create_menu.addSeparator();

		heroItem = new JMenuItem("Hero");
		heroItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadHeroCreationPane();
			}
		});
		create_menu.add(heroItem);

		villianItem = new JMenuItem("Villian");
		villianItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadVillianCreationPane();
			}
		});
		create_menu.add(villianItem);

		environmentItem = new JMenuItem("Environment");
		environmentItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Extender.getGUI().loadEnvironmentCreationPane();
			}
		});
		create_menu.add(environmentItem);

		extender.add(create_menu);
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
				Extender.getGUI().loadCloseGamePane();
			}
		});
		game.add(closeGameItem);

	}
}
