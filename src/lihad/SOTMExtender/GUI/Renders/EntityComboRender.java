package lihad.SOTMExtender.GUI.Renders;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import lihad.SOTMExtender.Objects.Entity;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Villain;

public class EntityComboRender extends JLabel implements ListCellRenderer<Entity> {

	private static final long serialVersionUID = -8566813419503137774L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Entity> arg0, Entity entity,
			int arg2, boolean arg3, boolean arg4) {
		if(entity != null && entity instanceof TableEntity)this.setText(entity.getName()+" ("+((TableEntity)entity).getDifficulty()+")"+((entity instanceof Villain) ? "("+((Villain)entity).getAdvancedDifficulty()+")" : ""));
		else if(entity != null && entity instanceof Player)this.setText(entity.getName()+" ("+((Player)entity).getName()+")");
		else this.setText(" - new - ");
		return this;
	}

}
