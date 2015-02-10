package lihad.SOTMExtender.GUI.ComboBoxModels;

import javax.swing.DefaultComboBoxModel;

import lihad.SOTMExtender.Objects.Game;

public class GameModel extends DefaultComboBoxModel<Game>{

	private static final long serialVersionUID = -6312619229902745733L;

	@Override
	public void addElement(Game item) {
		for(int i = 0; i < this.getSize(); i++){
			if(item.getTimestamp() >= this.getElementAt(i).getTimestamp()){
				super.insertElementAt(item, i); return;
			}
		}
		super.addElement(item);
    }

}