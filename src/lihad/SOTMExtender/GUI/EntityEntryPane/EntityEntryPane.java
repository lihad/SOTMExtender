package lihad.SOTMExtender.GUI.EntityEntryPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.Entity;

public class EntityEntryPane extends JPanel{

	private static final long serialVersionUID = -8457699509670235099L;
	
	private EntityEntryPaneSelection teep_selection;
	private EntityEntryPaneSave teep_save;
	private EntityEntryPaneInformation teep_information;

	public EntityEntryPane(EntityEntryPaneType class_type){
		super(new BorderLayout());
		
		teep_selection = new EntityEntryPaneSelection(this, class_type);
		loadEntityEditPaneInformation(teep_selection.getSelectedEntity(), class_type);
		
		this.add(teep_selection, BorderLayout.NORTH);
		this.add(teep_information, BorderLayout.CENTER);
		this.add(teep_save, BorderLayout.SOUTH);
	}
	
	public void loadEntityEditPaneInformation(Entity entity, EntityEntryPaneType type){
		if(teep_information != null)this.remove(teep_information);
		if(teep_save != null)this.remove(teep_save);
		
		teep_information = new EntityEntryPaneInformation(entity, type);
		teep_save = new EntityEntryPaneSave(teep_information, type);
		
		this.add(teep_information, BorderLayout.CENTER);
		this.add(teep_save, BorderLayout.SOUTH);
		Extender.getGUI().pack();
		this.repaint();
	}
}
