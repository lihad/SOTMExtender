package lihad.SOTMExtender.GUI.NewGamePane;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Player;

public class NewGamePaneInitialGroupInformation extends JPanel{
	
	private static final long serialVersionUID = -8186256133017188282L;

	private NewGamePane initial;
	private JLabel gold;
	
	public NewGamePaneInitialGroupInformation(NewGamePane initial){
		super(new FlowLayout(FlowLayout.LEFT, 1, 1));
		this.initial = initial;
		
		gold = new JLabel("0G");
		gold.setBorder(BorderFactory.createLoweredBevelBorder());
		gold.setPreferredSize(new Dimension(100,20));
		
		this.add(gold);
		
	}
	
	protected void updateGoldLabel(){
		int i = 0;
		for(Player p : this.initial.getSelectedPlayers()){
			i += p.getGold();
		}
		gold.setText(i+"G");
	}
}
