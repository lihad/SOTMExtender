package lihad.SOTMExtender.GUI.ViewGamePane;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Award;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Villain;
import lihad.SOTMExtender.Util.Utility;

public class ViewGamePaneTableEntityCard extends JPanel{

	private static final long serialVersionUID = -6570443222371954512L;

	ViewGamePaneTableEntityCard(Game game, TableEntity entity, Player player, Award award){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		try {
			this.add(new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(entity.getImageFile()),76,113))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(player != null){
			JLabel player_name = new JLabel(player.getName()+(game.getCompletedGameData().isLiving(entity) ? "" : " (DEAD)"));
			if(award != null) player_name.setForeground(Color.MAGENTA);
			if(!game.getCompletedGameData().isLiving(entity)) player_name.setForeground(Color.RED);
			
			this.add(player_name);
		}
		
		else if(entity instanceof Villain){
			this.add(new JLabel((((Villain)entity).isAdvanced() ? "(*)" : "")+""+entity.getName()));
		}
		
		else this.add(new JLabel(entity.getName()));
		
	}
}
