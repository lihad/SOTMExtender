package lihad.SOTMExtender.GUI.ComboBoxModels;

import javax.swing.DefaultComboBoxModel;

import lihad.SOTMExtender.Objects.Player;

public class PlayerModel extends DefaultComboBoxModel<Player>{

	private static final long serialVersionUID = -6312619229902745733L;

	@Override
	public void addElement(Player item) {
		if(this.getSize() == 0) super.insertElementAt(null, 0);
		
		for(int i = 1; i < this.getSize(); i++){
			if(item.getName().compareTo(this.getElementAt(i).getName()) < 0){
				super.insertElementAt(item, i); return;
			}
		}
		super.addElement(item);
    }

}
