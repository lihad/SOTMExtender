package lihad.SOTMExtender.GUI.Renders;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import lihad.SOTMExtender.Objects.Player;

public class PlayerComboRender extends JLabel implements ListCellRenderer<Player> {

	private static final long serialVersionUID = 1705600596221429514L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Player> arg0, Player entity,
			int arg2, boolean arg3, boolean arg4) {
		if(entity != null)this.setText(entity.getName()+" ("+entity.getFullname()+")");
		return this;
	}
}
