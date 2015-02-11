package lihad.SOTMExtender.GUI.Renders;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Villain;

public class TableEntityComboRender extends JLabel implements ListCellRenderer<TableEntity> {

	private static final long serialVersionUID = 1705600596221429514L;

	@Override
	public Component getListCellRendererComponent(JList<? extends TableEntity> arg0, TableEntity entity,
			int arg2, boolean arg3, boolean arg4) {
		if(entity != null)this.setText(entity.getName()+" ("+entity.getDifficulty()+")"+((entity instanceof Villain) ? "("+((Villain)entity).getAdvancedDifficulty()+")" : ""));
		else this.setText(" - new - ");
		return this;
	}

}
