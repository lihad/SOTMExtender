package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;

public class CloseGamePane extends JPanel{

	private static final long serialVersionUID = 7067502334671920593L;

	SelectCloseGamePane select_pane;
	List<PlayerCloseGamePane> player_close_panes;
	VillianCloseGamePane villian_close_pane;
	protected JButton button;

	public CloseGamePane(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		player_close_panes = new LinkedList<PlayerCloseGamePane>();

		select_pane = new SelectCloseGamePane(this);
		this.add(select_pane);
		
		loadAuxillaryPanes(select_pane.getGame());

		button = new JButton("close");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Map<TableEntity, Object[]> data_map = new HashMap<TableEntity, Object[]>();
				
				for(PlayerCloseGamePane p_p : player_close_panes) data_map.put(select_pane.getGame().getHero(p_p.getPlayer()), new Object[]{p_p.isLiving(), p_p.getHP()});
				data_map.put(select_pane.getGame().getVillian(), new Object[]{villian_close_pane.isLiving(), villian_close_pane.getHP()});
				data_map.put(select_pane.getGame().getEnvironment(), new Object[]{false, 0});

				select_pane.getGame().complete(select_pane.hasWon(), select_pane.getRounds(), data_map);
				Extender.saveGameData(select_pane.getGame());
				CloseGamePane.this.remove(CloseGamePane.this.button);
			}
		});
		this.add(button, BorderLayout.SOUTH);
	}
	
	protected void loadAuxillaryPanes(Game game){
		for(PlayerCloseGamePane pane : player_close_panes){
			this.remove(pane);
		}
		
		if(villian_close_pane != null) this.remove(villian_close_pane);
		
		player_close_panes.clear();
		
		for(Player player : game.getPlayers()){
			PlayerCloseGamePane pcgpane = new PlayerCloseGamePane(game, player);
			player_close_panes.add(pcgpane);
			this.add(pcgpane);
		}
		
		villian_close_pane = new VillianCloseGamePane(game.getVillian());		
		this.add(villian_close_pane);
		
		if(button != null){this.remove(button); this.add(button);}
		
		Extender.getGUI().pack();
		this.repaint();
	}
}
