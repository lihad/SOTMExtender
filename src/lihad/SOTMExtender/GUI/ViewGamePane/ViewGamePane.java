package lihad.SOTMExtender.GUI.ViewGamePane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.ComboBoxModels.GameModel;
import lihad.SOTMExtender.GUI.Renders.GameComboRender;
import lihad.SOTMExtender.Objects.Game;

public class ViewGamePane extends JPanel{

	private static final long serialVersionUID = -8266323636492809770L;

	private JComboBox<Game> game_combo;
	private ViewGamePaneInformation view_info;

	public ViewGamePane(Game g){
		this.setLayout(new BorderLayout());

		game_combo = new JComboBox<Game>();
		game_combo.setRenderer(new GameComboRender());
		game_combo.setModel(new GameModel());

		for(Game game : Extender.getGames()){
			if(game.isCompleted()) game_combo.addItem(game);
		}

		game_combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadViewPaneInformation(game_combo.getItemAt(game_combo.getSelectedIndex()));
			}
		});

		this.add(game_combo, BorderLayout.NORTH);

		if(game_combo.getItemCount() > 0){
			if(g != null) game_combo.setSelectedItem(g);
			loadViewPaneInformation(game_combo.getItemAt(game_combo.getSelectedIndex()));
		}
	}	

	private void loadViewPaneInformation(Game game){
		if(view_info != null)this.remove(view_info);
		view_info = new ViewGamePaneInformation(game);
		this.add(view_info, BorderLayout.CENTER);
		Extender.getGUI().pack();
		this.repaint();
	}

}
