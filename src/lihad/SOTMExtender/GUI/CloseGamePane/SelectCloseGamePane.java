package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.Renders.GameComboRender;
import lihad.SOTMExtender.Objects.Game;

public class SelectCloseGamePane extends JPanel{
	
	private static final long serialVersionUID = -4275310916619812494L;
	
	private JComboBox<Game> game_combo;
	private JCheckBox won;
	private JTextField rounds;
	CloseGamePane cg_pane;

	SelectCloseGamePane(CloseGamePane cg_pane){
		this.cg_pane = cg_pane;
		game_combo = new JComboBox<Game>();
		game_combo.setRenderer(new GameComboRender());
		
		for(Game game : Extender.getGames()){
			if(!game.isCompleted()) game_combo.addItem(game);
		}
		
		game_combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectCloseGamePane.this.cg_pane.loadAuxillaryPanes(getGame());
			}
		});
		
		this.add(game_combo);
		
		won = new JCheckBox("Victorious?");
		this.add(won);
		
		rounds = new JTextField(3);
		rounds.setEditable(true);
		this.add(new JLabel("Rounds: "));
		this.add(rounds);
	}
	
	public Game getGame(){
		return this.game_combo.getItemAt(this.game_combo.getSelectedIndex());
	}
	
	public boolean hasWon(){
		return this.won.isSelected();
	}
	
	public int getRounds(){
		return Integer.parseInt(this.rounds.getText());
	}

}
