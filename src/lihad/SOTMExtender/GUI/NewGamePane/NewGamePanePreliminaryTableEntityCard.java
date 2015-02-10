package lihad.SOTMExtender.GUI.NewGamePane;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Util.Utility;

public class NewGamePanePreliminaryTableEntityCard extends JPanel{

	private static final long serialVersionUID = 1200866207922895636L;

	public NewGamePanePreliminaryTableEntityCard(Player player, TableEntity entity){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if(player != null)this.add(new JLabel(player.getName()));
		try {
			this.add(new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(entity.getImageFile()),76,113))));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
