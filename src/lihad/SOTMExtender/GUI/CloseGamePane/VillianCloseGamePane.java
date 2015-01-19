package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Objects.Villian;

public class VillianCloseGamePane extends JPanel{

	private static final long serialVersionUID = -8123858354750660495L;
	
	private JCheckBox living;
	private JTextField hp_end;

	VillianCloseGamePane(Villian villian){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(new JLabel("villian: "+villian.getName()));

		living = new JCheckBox("Living?");
		living.setSelected(true);
		this.add(living);

		this.add(new JLabel("HP: "));
		hp_end = new JTextField(3);
		this.add(hp_end);
	}
	
	public boolean isLiving(){
		return this.living.isSelected();
	}

	public int getHP(){
		return Integer.parseInt(this.hp_end.getText());
	}
}
