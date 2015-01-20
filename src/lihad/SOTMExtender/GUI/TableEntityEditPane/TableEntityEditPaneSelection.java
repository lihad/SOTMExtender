package lihad.SOTMExtender.GUI.TableEntityEditPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.Renders.TableEntityComboRender;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Villian;

public class TableEntityEditPaneSelection extends JPanel{

	private static final long serialVersionUID = -6472725785332326549L;

	private JComboBox<TableEntity> te_combo;
	private TableEntityEditPane pane;

	TableEntityEditPaneSelection(TableEntityEditPane pane, TableEntityEditPaneType type){
		super(new BorderLayout());
		this.pane = pane;

		te_combo = new JComboBox<TableEntity>();
		te_combo.setRenderer(new TableEntityComboRender());

		if(type == TableEntityEditPaneType.VILLIAN){
			for(Villian villian : Extender.getVillians()){
				te_combo.addItem(villian);
			}
		}
		else if(type == TableEntityEditPaneType.HERO){
			for(Hero hero : Extender.getHeroes()){
				te_combo.addItem(hero);
			}
		}
		else if(type == TableEntityEditPaneType.ENVIRONMENT){
			for(Environment environment : Extender.getEnvironments()){
				te_combo.addItem(environment);
			}
		}

		te_combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TableEntityEditPaneSelection.this.pane.loadTableEntityEditPaneInformation(te_combo.getItemAt(te_combo.getSelectedIndex()));
			}
		});
		this.add(te_combo, BorderLayout.WEST);
	}

	public TableEntity getSelectedTableEntity(){
		return this.te_combo.getItemAt(this.te_combo.getSelectedIndex());
	}
}