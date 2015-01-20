package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.Renders.GameComboRender;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.TableEntity;

public class CloseGamePane extends JPanel{

	private static final long serialVersionUID = 7067502334671920593L;

	protected JButton button;
	private JComboBox<Game> game_combo;
	
	private CloseGamePaneInformation close_info;

	public CloseGamePane(Game g){
		this.setLayout(new BorderLayout());
		
		game_combo = new JComboBox<Game>();
		game_combo.setRenderer(new GameComboRender());

		for(Game game : Extender.getGames()){
			if(!game.isCompleted()) game_combo.addItem(game);
		}

		game_combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadClosePaneInformation(game_combo.getItemAt(game_combo.getSelectedIndex()));
			}
		});

		this.add(game_combo, BorderLayout.NORTH);

		if(game_combo.getItemCount() > 0){
			if(g != null) game_combo.setSelectedItem(g);
			loadClosePaneInformation(game_combo.getItemAt(game_combo.getSelectedIndex()));
		}

		button = new JButton("close");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Map<TableEntity, Object[]> data_map = new HashMap<TableEntity, Object[]>();
				
				for(CloseGamePaneTableEntityCard p_p : close_info.getPlayerLine().getPlayerCards()) data_map.put(close_info.getGame().getHero(p_p.getPlayer()), new Object[]{p_p.isLiving(), p_p.getHealth()});
				data_map.put(close_info.getGame().getVillian(), new Object[]{close_info.getVillianLine().getVillianCard().isLiving(), close_info.getVillianLine().getVillianCard().getHealth()});
				data_map.put(close_info.getGame().getEnvironment(), new Object[]{false, 0});

				close_info.getGame().complete(close_info.getStatisticsPane().isVictorious(), close_info.getStatisticsPane().getRounds(), data_map);
				Extender.saveGameData(close_info.getGame());
				Extender.getGUI().loadViewGamePane(close_info.getGame());
			}
		});
		this.add(button, BorderLayout.SOUTH);
	}
	
	private void loadClosePaneInformation(Game game){
		if(close_info != null)this.remove(close_info);
		close_info = new CloseGamePaneInformation(game);
		this.add(close_info, BorderLayout.CENTER);
		Extender.getGUI().pack();
		this.repaint();
	}
}
