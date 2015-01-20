package lihad.SOTMExtender.GUI.TableEntityEditPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.TableEntity;

public class TableEntityEditPane extends JPanel{

	private static final long serialVersionUID = -8457699509670235099L;
	
	private TableEntityEditPaneSelection teep_selection;
	private TableEntityEditPaneSave teep_save;
	private TableEntityEditPaneInformation teep_information;

	public TableEntityEditPane(TableEntityEditPaneType class_type){
		super(new BorderLayout());
		
		teep_selection = new TableEntityEditPaneSelection(this, class_type);
		loadTableEntityEditPaneInformation(teep_selection.getSelectedTableEntity());
		
		this.add(teep_selection, BorderLayout.NORTH);
		this.add(teep_information, BorderLayout.CENTER);
		this.add(teep_save, BorderLayout.SOUTH);
	}
	
	public void loadTableEntityEditPaneInformation(TableEntity tableentity){
		if(teep_information != null)this.remove(teep_information);
		if(teep_save != null)this.remove(teep_save);
		
		teep_information = new TableEntityEditPaneInformation(tableentity);
		teep_save = new TableEntityEditPaneSave(teep_information);
		
		this.add(teep_information, BorderLayout.CENTER);
		this.add(teep_save, BorderLayout.SOUTH);
		Extender.getGUI().pack();
		this.repaint();
	}
}
