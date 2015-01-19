package lihad.SOTMExtender.GUI;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VillianCreationPane extends CreationPane{

	private static final long serialVersionUID = -2234961254579140779L;
	
	private JCheckBox toggle;
	protected JTextField health;
	
	VillianCreationPane() {
		super(CreationPaneType.VILLIAN);
		
		JPanel v_pane = new JPanel();
		
		toggle = new JCheckBox("Advanced");
		toggle.setSelected(false);
		
		health = new JTextField(3);
		
		v_pane.add(new JLabel("Health:"));
		v_pane.add(health);
		v_pane.add(toggle);
		
		west_pane.add(v_pane);
	
	}
	
	public boolean isAdvanced(){
		return this.toggle.isSelected();
	}
	
	public int getHealth(){
		return Integer.parseInt(this.health.getText());
	}
}
