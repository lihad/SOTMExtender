package lihad.SOTMExtender.GUI.ComboBoxModels;

import javax.swing.DefaultComboBoxModel;

import lihad.SOTMExtender.Objects.TableEntity;

public class TableEntityModel extends DefaultComboBoxModel<TableEntity>{

	private static final long serialVersionUID = -6312619229902745733L;

	@Override
	public void addElement(TableEntity item) {
		for(int i = 0; i < this.getSize(); i++){
			if(item.getName().compareTo(this.getElementAt(i).getName()) < 0){
				super.insertElementAt(item, i); return;
			}
		}
		super.addElement(item);
    }
}
