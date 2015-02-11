package lihad.SOTMExtender.GUI.EntityEntryPane.PlayerEntryPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.ComboBoxModels.PlayerModel;
import lihad.SOTMExtender.GUI.ComboBoxModels.TableEntityModel;
import lihad.SOTMExtender.GUI.EntityEntryPane.EntityEntryPaneType;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePaneInitialPlayerLine;
import lihad.SOTMExtender.GUI.Renders.PlayerComboRender;
import lihad.SOTMExtender.GUI.Renders.TableEntityComboRender;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Character;
import lihad.SOTMExtender.Objects.Villain;

public class PlayerEntryPaneStatistics extends JPanel{

	private static final long serialVersionUID = 4773075611292511244L;

	JTextField name_field, fullname_field, gold_field, experience_field, level_field;
	JComboBox<TableEntity> hero_combo;

	public PlayerEntryPaneStatistics(final Player player, EntityEntryPaneType type){
		super(new BorderLayout());
		JPanel shove_north = new JPanel(), name_pane = new JPanel(new FlowLayout(FlowLayout.LEFT)), 
				fullname_pane = new JPanel(new FlowLayout(FlowLayout.LEFT)), 
				gold_pane = new JPanel(new FlowLayout(FlowLayout.LEFT)),
				experience_pane = new JPanel(new FlowLayout(FlowLayout.LEFT,1,1));
		shove_north.setLayout(new BoxLayout(shove_north, BoxLayout.Y_AXIS));

		name_pane.add(new JLabel("name: "));
		name_field = new JTextField(9);
		name_field.setEditable(false);
		name_field.setText(player != null ? player.getName() : "");
		name_pane.add(name_field);
		shove_north.add(name_pane);

		fullname_pane.add(new JLabel("fullname: "));
		fullname_field = new JTextField(9);
		fullname_field.setEditable(true);
		fullname_field.setText(player != null ? player.getFullname() : "");
		fullname_pane.add(fullname_field);
		shove_north.add(fullname_pane);

		gold_pane.add(new JLabel("G: "));
		gold_field = new JTextField(5);
		gold_field.setEditable(true);
		gold_field.setText(player != null ? player.getGold()+"" : "");
		gold_pane.add(gold_field);
		shove_north.add(gold_pane);

		hero_combo = new JComboBox<TableEntity>();
		hero_combo.setRenderer(new TableEntityComboRender());
		hero_combo.setModel(new TableEntityModel());
		hero_combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(player != null && experience_field != null){
					experience_field.setText((player.hasExperience((Hero)hero_combo.getSelectedItem())) ? player.getExperience((Hero)hero_combo.getSelectedItem())+"" : "0");
					level_field.setText((player.hasExperience((Hero)hero_combo.getSelectedItem())) ? player.getLevel((Hero)hero_combo.getSelectedItem())+"" : "0");
				}
			}

		});
		for(Hero hero : Extender.getHeroes())hero_combo.addItem(hero);
		experience_pane.add(hero_combo);

		experience_pane.add(new JLabel("exp: "));
		experience_field = new JTextField(4);
		experience_field.setEditable(true);
		experience_pane.add(experience_field);

		experience_pane.add(new JLabel("lvl: "));
		level_field = new JTextField(2);
		level_field.setEditable(false);
		experience_pane.add(level_field);

		shove_north.add(experience_pane);

		this.add(shove_north, BorderLayout.NORTH);
	}

	public String getName(){
		return this.name_field.getText();
	}

	public String getFullname(){
		return this.fullname_field.getText();
	}

	public int getGold(){
		return Integer.parseInt(this.gold_field.getText());
	}

}
