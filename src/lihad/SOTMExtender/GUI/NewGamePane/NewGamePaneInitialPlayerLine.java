package lihad.SOTMExtender.GUI.NewGamePane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.ComboBoxModels.PlayerModel;
import lihad.SOTMExtender.GUI.ComboBoxModels.PotionModel;
import lihad.SOTMExtender.GUI.Renders.PlayerComboRender;
import lihad.SOTMExtender.GUI.Renders.PotionComboRender;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;
import lihad.SOTMExtender.Objects.Potion.PotionRound;

public class NewGamePaneInitialPlayerLine  extends JPanel{

	private static final long serialVersionUID = -1608479186862092059L;
	
	private NewGamePane initial;
	private JComboBox<Player> player_combo;
	private JComboBox<Potion> potion_combo;
	JLabel gold;

	public NewGamePaneInitialPlayerLine(NewGamePane initial){
		super(new FlowLayout(FlowLayout.LEFT, 1, 1));
		this.initial = initial;
		
		player_combo = new JComboBox<Player>();
		player_combo.setRenderer(new PlayerComboRender());
		player_combo.setModel(new PlayerModel());
		player_combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewGamePaneInitialPlayerLine.this.initial.updateGoldLabels();
				if(player_combo.getSelectedItem() == null)setPotionLock(true);
				else setPotionLock(false);
			}
		});
		for(Player player : Extender.getPlayers())player_combo.addItem(player);
		this.add(player_combo);
		
		gold = new JLabel(this.getPlayer() != null ? (this.getPlayer().getGold()+"G") : "N/A");
		gold.setBorder(BorderFactory.createLoweredBevelBorder());
		gold.setPreferredSize(new Dimension(100,20));

		this.add(gold);
		
		potion_combo = new JComboBox<Potion>();
		potion_combo.setRenderer(new PotionComboRender());
		potion_combo.setModel(new PotionModel());
		updateComboBox(PotionRound.ONE);
		potion_combo.setSelectedIndex(0);
		potion_combo.setEnabled(false);
		this.add(potion_combo);		
	}
	
	public void setPotionLock(boolean isLocked){
		this.potion_combo.setEnabled(!isLocked);
	}
	
	public Player getPlayer(){
		return player_combo.getItemAt(player_combo.getSelectedIndex());
	}
	
	public Potion getPotion(){
		return potion_combo.getItemAt(potion_combo.getSelectedIndex());
	}
	
	public JComboBox<Player> getPlayerCombo(){
		return player_combo;
	}
	
	public JComboBox<Potion> getPotionCombo(){
		return potion_combo;
	}
	
	protected void updateGoldLabel(){
		gold.setText(this.getPlayer() != null ? (this.getPlayer().getGold()+"G") : "N/A");
	}
	
	protected void updateComboBox(PotionRound round){
		potion_combo.removeAllItems();
		for(Potion potion : Potion.values()) if(potion.getPotionRound() == round || potion.getPotionRound() == PotionRound.ALL)potion_combo.addItem(potion);
		potion_combo.setSelectedIndex(0);
	}

}
