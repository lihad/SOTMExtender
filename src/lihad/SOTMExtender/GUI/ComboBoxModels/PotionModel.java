package lihad.SOTMExtender.GUI.ComboBoxModels;

import javax.swing.DefaultComboBoxModel;

import lihad.SOTMExtender.Objects.Potion;

public class PotionModel  extends DefaultComboBoxModel<Potion>{

	private static final long serialVersionUID = -6312619229902745733L;

	@Override
	public void addElement(Potion potion) {
		for(int i = 0; i < this.getSize(); i++){
			if(potion.getName().compareTo(this.getElementAt(i).getName()) < 0){
				super.insertElementAt(potion, i); return;
			}
		}
		super.addElement(potion);
    }
}
