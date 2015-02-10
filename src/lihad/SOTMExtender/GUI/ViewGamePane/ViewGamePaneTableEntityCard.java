package lihad.SOTMExtender.GUI.ViewGamePane;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;




import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Character;
import lihad.SOTMExtender.Objects.Villain;
import lihad.SOTMExtender.Util.Utility;

public class ViewGamePaneTableEntityCard extends JPanel{

	private static final long serialVersionUID = -6570443222371954512L;

	ViewGamePaneTableEntityCard(TableEntity entity, Player player, int hp_remaining, boolean isMVP){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		try {
			this.add(new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(entity.getImageFile()),76,113))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(player != null){
			JLabel player_name = new JLabel(player.getName());
			if(isMVP) player_name.setForeground(Color.MAGENTA);
			this.add(player_name);
		}
		
		else if(entity instanceof Villain){
			this.add(new JLabel((((Villain)entity).isAdvanced() ? "(*)" : "")+""+entity.getName()));
		}
		
		else this.add(new JLabel(entity.getName()));
		
		if(entity instanceof Character){
			JLabel health_label = new JLabel(""+hp_remaining+"/"+(((Character)entity).getHealth()));
			double percent = ((double)hp_remaining)/((double)((Character)entity).getHealth());
			if(percent < .2)health_label.setForeground(Color.RED);
			else if(percent < .4)health_label.setForeground(Color.ORANGE);
			else if(percent < .6)health_label.setForeground(Color.BLACK);
			else if(percent < .8)health_label.setForeground(Color.GREEN);
			else health_label.setForeground(Color.BLUE);
			
			this.add(health_label);
		}
		else this.add(new JLabel(" "));
	}
}
