package lihad.SOTMExtender.GUI.EntityEntryPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.ComboBoxModels.EntityModel;
import lihad.SOTMExtender.GUI.Renders.EntityComboRender;
import lihad.SOTMExtender.Objects.Entity;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Villain;

public class EntityEntryPaneSelection extends JPanel{

	private static final long serialVersionUID = -6472725785332326549L;

	private JComboBox<Entity> te_combo;
	private EntityEntryPane pane;
	private EntityEntryPaneType type;

	EntityEntryPaneSelection(EntityEntryPane pane, EntityEntryPaneType type){
		super(new BorderLayout());
		this.pane = pane; this.type = type;
		
		te_combo = new JComboBox<Entity>();
		te_combo.setRenderer(new EntityComboRender());
		te_combo.setModel(new EntityModel());
		
		te_combo.addItem(null);
		
		if(type == EntityEntryPaneType.VILLAIN){
			for(Villain villain : Extender.getVillains()){
				te_combo.addItem(villain);
			}
		}
		else if(type == EntityEntryPaneType.HERO){
			for(Hero hero : Extender.getHeroes()){
				te_combo.addItem(hero);
			}
		}
		else if(type == EntityEntryPaneType.ENVIRONMENT){
			for(Environment environment : Extender.getEnvironments()){
				te_combo.addItem(environment);
			}
		}
		else if(type == EntityEntryPaneType.PLAYER){
			for(Player player : Extender.getPlayers()){
				te_combo.addItem(player);
			}
		}
		
		te_combo.setSelectedIndex(0);
		te_combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				EntityEntryPaneSelection.this.pane.loadEntityEditPaneInformation(te_combo.getItemAt(te_combo.getSelectedIndex()), EntityEntryPaneSelection.this.type);
			}
		});
		this.add(te_combo, BorderLayout.WEST);
	}

	public Entity getSelectedEntity(){
		return this.te_combo.getItemAt(this.te_combo.getSelectedIndex());
	}
}
