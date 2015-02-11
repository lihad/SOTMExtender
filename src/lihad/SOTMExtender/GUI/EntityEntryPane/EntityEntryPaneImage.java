package lihad.SOTMExtender.GUI.EntityEntryPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Util.Utility;

public class EntityEntryPaneImage extends JPanel{

	private static final long serialVersionUID = 4293551032895620773L;
	
	private JLabel image_label;
	private JFileChooser fc;
	private File file;

	EntityEntryPaneImage(TableEntity tableentity){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		try {
			this.image_label = new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(tableentity.getImageFile()),152,228)));
			this.file = tableentity.getImageFile();
		} catch (IOException e) {
			Extender.getLogger().warning(EntityEntryPaneImage.class, "unable to load image for "+tableentity.getName());
		} catch (NullPointerException re) {
			Extender.getLogger().warning(EntityEntryPaneImage.class, "the image is null");
		} catch(IllegalArgumentException re){
			Extender.getLogger().warning(EntityEntryPaneImage.class, "the image for "+tableentity.getName()+" is corrupt");
		}
		
		if(image_label != null)this.add(image_label);
		
		JButton image_button = new JButton("change image");
		fc = new JFileChooser();
		
		image_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(EntityEntryPaneImage.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						Extender.getLogger().debug(EntityEntryPaneImage.class, "file is null");
						if(image_label != null)EntityEntryPaneImage.this.remove(image_label);
						image_label = new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(file),152,228)));
						EntityEntryPaneImage.this.add(image_label);
						Extender.getGUI().pack();
						EntityEntryPaneImage.this.repaint();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		this.add(image_button);
	}
	
	public File getImageFile(){
		return this.file;
	}
}
