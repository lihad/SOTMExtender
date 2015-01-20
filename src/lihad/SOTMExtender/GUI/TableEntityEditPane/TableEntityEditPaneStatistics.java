package lihad.SOTMExtender.GUI.TableEntityEditPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Character;
import lihad.SOTMExtender.Objects.Villian;

public class TableEntityEditPaneStatistics extends JPanel{

	private static final long serialVersionUID = 4773075611292511244L;

	JTextField name_field, difficulty_field, health_field;
	JCheckBox advanced_box;

	TableEntityEditPaneStatistics(TableEntity entity){
		super(new BorderLayout());
		JPanel shove_north = new JPanel(), name_pane = new JPanel(new FlowLayout(FlowLayout.LEFT)), 
				health_pane = new JPanel(new FlowLayout(FlowLayout.LEFT)), 
				difficulty_pane = new JPanel(new FlowLayout(FlowLayout.LEFT)), 
				advanced_pane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		shove_north.setLayout(new BoxLayout(shove_north, BoxLayout.Y_AXIS));

		name_pane.add(new JLabel("name: "));
		name_field = new JTextField(9);
		name_field.setEditable(true);
		name_field.setText(entity.getName());
		name_pane.add(name_field);
		shove_north.add(name_pane);

		difficulty_pane.add(new JLabel("difficulty: "));
		difficulty_field = new JTextField(3);
		difficulty_field.setEditable(true);
		difficulty_field.setText(entity.getDifficulty()+"");
		difficulty_pane.add(difficulty_field);
		shove_north.add(difficulty_pane);

		if(entity instanceof Character){
			health_pane.add(new JLabel("health: "));
			health_field = new JTextField(3);
			health_field.setEditable(true);
			health_field.setText(((Character)entity).getHealth()+"");
			health_pane.add(health_field);
			shove_north.add(health_pane);
		}
		
		this.add(shove_north, BorderLayout.NORTH);
	}

	public String getName(){
		return this.name_field.getText();
	}

	public int getDifficulty(){
		return Integer.parseInt(this.difficulty_field.getText());
	}

	public int getHealth(){
		return Integer.parseInt(this.health_field.getText());
	}

	public boolean isAdvanced(){
		return this.advanced_box.isSelected();
	}

}
