package lihad.SOTMExtender.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.CompletedGameData.CGDataType;

public class CloseGamePane extends JPanel{
	
	private static final long serialVersionUID = 7067502334671920593L;
	
	JPanel primary;
	SelectCloseGamePane select_pane;
	List<PlayerCloseGamePane> player_close_panes;
	VillianCloseGamePane villian_close_pane;
	EnvironmentCloseGamePane environment_close_pane;
	protected JButton button;

	public CloseGamePane(){
		super(new BorderLayout());
		player_close_panes = new LinkedList<PlayerCloseGamePane>();
		
		select_pane = new SelectCloseGamePane();
		this.add(select_pane, BorderLayout.NORTH);
		
		primary = select_pane;
		
		button = new JButton("next");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(primary.equals(select_pane)){
					for(Player player : select_pane.getGame().getPlayers()) player_close_panes.add(new PlayerCloseGamePane(select_pane.getGame(), player));
					loadPane(player_close_panes.get(0));
				}
				else if(player_close_panes.contains(primary)){
					for(int i = 0; i < player_close_panes.size(); i++){
						if(player_close_panes.get(i).equals(primary) && i < player_close_panes.size()-1){
							loadPane(player_close_panes.get(i+1));
							break;
						}else if(player_close_panes.get(i).equals(primary) && i == player_close_panes.size()-1){
							villian_close_pane = new VillianCloseGamePane(select_pane.getGame().getVillian());
							loadPane(villian_close_pane);
						}
					}
				}else if(primary.equals(villian_close_pane)){
					environment_close_pane = new EnvironmentCloseGamePane(select_pane.getGame().getEnvironment());
					loadPane(environment_close_pane);
					button.setText("finish");
				}else if(primary.equals(environment_close_pane)){
					Map<TableEntity, Object[]> data_map = new HashMap<TableEntity, Object[]>();
					for(PlayerCloseGamePane p_p : player_close_panes){
						Extender.getLogger().debug(CloseGamePane.class, ""+p_p.isLiving());
						Object[] cg = new Object[6];
						cg[0] = (p_p.isLiving());
						cg[1] = (p_p.getHP());
						cg[2] = (p_p.getDamageDealt());
						cg[3] = (p_p.getDamageMitigated());
						cg[4] = (p_p.getHPRestored());
						cg[5] = (p_p.getKills());
						System.out.println(p_p.isLiving() + " "+ p_p.getHP());
						data_map.put(select_pane.getGame().getHero(p_p.getPlayer()), cg);
					}
					Object[] cg = new Object[6];
					System.out.println(villian_close_pane.isLiving() + " "+ villian_close_pane.getKills());
					cg[0] = (villian_close_pane.isLiving());
					cg[1] = (villian_close_pane.getHP());
					cg[2] = (villian_close_pane.getDamageDealt());
					cg[3] = (villian_close_pane.getDamageMitigated());
					cg[4] = (villian_close_pane.getHPRestored());
					cg[5] = (villian_close_pane.getKills());
					data_map.put(select_pane.getGame().getVillian(), cg);
					
					Object[] eg = new Object[6];
					eg[0] = (false);
					eg[1] = (0);
					eg[2] = (environment_close_pane.getDamageDealt());
					eg[3] = (environment_close_pane.getDamageMitigated());
					eg[4] = (environment_close_pane.getHPRestored());
					eg[5] = (environment_close_pane.getKills());
					data_map.put(select_pane.getGame().getEnvironment(), eg);
					
					select_pane.getGame().complete(select_pane.hasWon(), select_pane.getRounds(), data_map);
					select_pane.getGame().awardExperience();
					Extender.saveGameData(select_pane.getGame());
					CloseGamePane.this.remove(CloseGamePane.this.button);
				}
			}
			
		});
		this.add(button, BorderLayout.SOUTH);
		
		
	}
	
	
	public void loadPane(JPanel pane){
		this.remove(primary);
		this.add(pane, BorderLayout.NORTH);
		this.primary = pane;
		Extender.getGUI().pack();
		this.repaint();
	}

}
