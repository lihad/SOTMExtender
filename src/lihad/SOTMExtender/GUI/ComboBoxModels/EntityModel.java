package lihad.SOTMExtender.GUI.ComboBoxModels;

import javax.swing.DefaultComboBoxModel;

import lihad.SOTMExtender.Objects.Entity;

public class EntityModel extends DefaultComboBoxModel<Entity>{

	private static final long serialVersionUID = -6751879639567722298L;

	@Override
	public void addElement(Entity item) {
		for(int i = 1; i < this.getSize(); i++){
			if(item.getName().compareTo(this.getElementAt(i).getName()) < 0){
				super.insertElementAt(item, i); return;
			}
		}
		super.addElement(item);
    }
}
