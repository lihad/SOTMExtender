package lihad.SOTMExtender.GUI.Renders;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import lihad.SOTMExtender.Objects.Potion;

public class PotionComboRender extends JLabel implements ListCellRenderer<Potion> {

	private static final long serialVersionUID = -1261889451504326438L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Potion> arg0, Potion potion,
			int arg2, boolean arg3, boolean arg4) {
		if(potion != null)this.setText(potion.getName()+" ["+potion.getCost()+"]");
		return this;
	}
}
