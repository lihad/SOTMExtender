package lihad.SOTMExtender.GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HeroCreationPane extends CreationPane{

	private static final long serialVersionUID = 5254233728526683107L;

	protected JTextField health;

	public HeroCreationPane(){
		super(CreationPaneType.HERO);

		JPanel v_pane = new JPanel();		
		health = new JTextField(3);

		v_pane.add(new JLabel("Health:"));
		v_pane.add(health);

		west_pane.add(v_pane);
	}
	
	public int getHealth(){
		return Integer.parseInt(this.health.getText());
	}
}
