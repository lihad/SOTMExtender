package lihad.SOTMExtender.GUI.Renders;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import lihad.SOTMExtender.Objects.Game;

public class GameComboRender extends JLabel implements ListCellRenderer<Game> {

	private static final long serialVersionUID = 2076889362678371256L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Game> arg0,
			Game game, int arg2, boolean arg3, boolean arg4) {
		if(game != null)this.setText(game.getTimestamp()+" ( Players: "+game.getPlayers().size()+" | Villain: "+game.getVillain().getName()+" | Environment: "+game.getEnvironment().getName()+")");
		return this;
	}

}
