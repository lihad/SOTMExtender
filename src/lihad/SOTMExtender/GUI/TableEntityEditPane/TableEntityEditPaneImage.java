package lihad.SOTMExtender.GUI.TableEntityEditPane;

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

public class TableEntityEditPaneImage extends JPanel{

	private static final long serialVersionUID = 4293551032895620773L;
	
	private JLabel image_label;
	private JFileChooser fc;
	private File file;

	TableEntityEditPaneImage(TableEntity tableentity){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		try {
			image_label = new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(tableentity.getImageFile()),152,228)));
		} catch (IOException e) {
			Extender.getLogger().warning(TableEntityEditPaneImage.class, "unable to load image for "+tableentity.getName());
		}
		
		if(image_label != null)this.add(image_label);
		
		JButton image_button = new JButton("change image");
		fc = new JFileChooser();
		
		image_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(TableEntityEditPaneImage.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						if(image_label != null)TableEntityEditPaneImage.this.remove(image_label);
						image_label = new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(file),152,228)));
						TableEntityEditPaneImage.this.add(image_label);
						Extender.getGUI().pack();
						TableEntityEditPaneImage.this.repaint();
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
