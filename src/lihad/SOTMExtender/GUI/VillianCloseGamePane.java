package lihad.SOTMExtender.GUI;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Objects.Villian;

public class VillianCloseGamePane extends JPanel{

	private static final long serialVersionUID = -8123858354750660495L;
	
	private JCheckBox living;
	private JTextField hp_end, dmg_dealt, dmg_mitigated, hp_res, kills;

	VillianCloseGamePane(Villian villian){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(new JLabel("villian: "+villian.getName()));

		living = new JCheckBox("Living?");
		living.setSelected(true);
		this.add(living);

		this.add(new JLabel("HP: "));
		hp_end = new JTextField(3);
		this.add(hp_end);

		this.add(new JLabel("DMG Dealt: "));
		dmg_dealt = new JTextField(3);
		this.add(dmg_dealt);

		this.add(new JLabel("DMG Mitigated: "));
		dmg_mitigated = new JTextField(3);
		this.add(dmg_mitigated);

		this.add(new JLabel("HP Restored: "));
		hp_res = new JTextField(3);
		this.add(hp_res);

		this.add(new JLabel("Kills: "));
		kills = new JTextField(3);
		this.add(kills);
	}
	
	public boolean isLiving(){
		return this.living.isSelected();
	}

	public int getHP(){
		return Integer.parseInt(this.hp_end.getText());
	}

	public int getDamageDealt(){
		return Integer.parseInt(this.dmg_dealt.getText());
	}

	public int getDamageMitigated(){
		return Integer.parseInt(this.dmg_mitigated.getText());
	}

	public int getHPRestored(){
		return Integer.parseInt(this.hp_res.getText());
	}
	
	public int getKills(){
		return Integer.parseInt(this.kills.getText());
	}
}
