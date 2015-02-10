package lihad.SOTMExtender.GUI.EntityCreationPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.Entity;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Villain;
import lihad.SOTMExtender.Util.Utility;

public class CreationPane extends JPanel{

	private static final long serialVersionUID = 4275368150135277044L;

	private JTextField name, difficulty;
	private JButton createbutton, addimagebutton;
	private JLabel notifysuccess, image;
	protected JPanel east_pane, west_pane;
	private JFileChooser fc;
	private File file;

	CreationPane(CreationPaneType type){
		super(new BorderLayout());

		setPreferredSize(new Dimension(100,100));
		setSize(new Dimension(100,100));

		east_pane = new JPanel(new BorderLayout());
		west_pane = new JPanel(new GridLayout(2,2,1,1));

		west_pane.setPreferredSize(new Dimension(350,60));

		this.setBorder(BorderFactory.createLineBorder(Color.black));

		name = new JTextField(9);
		name.setEditable(true);

		difficulty = new JTextField(3);
		difficulty.setEditable(true);

		fc = new JFileChooser();

		addimagebutton = new JButton("add image");
		addimagebutton.setPreferredSize(new Dimension(150,25));

		addimagebutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(CreationPane.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					try {
						if(image != null)east_pane.remove(image);
						image = new JLabel(new ImageIcon(Utility.getScaledImage(ImageIO.read(file),190,285)));
						east_pane.add(image, BorderLayout.CENTER);
						Extender.getGUI().pack();
						CreationPane.this.repaint();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		createbutton = new JButton("create");

		ActionListener listener = null;

		if(type == CreationPaneType.HERO) listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(CreationPane.this instanceof HeroCreationPane){
					Hero hero = new Hero(getName(), getDifficulty(), ((HeroCreationPane)CreationPane.this).getHealth(), getFile());
					Extender.addHero(hero);
					Extender.saveHeroData(hero);
					notifySuccessCreation(hero);				
					name.setText(null); difficulty.setText(null); ((HeroCreationPane)CreationPane.this).health.setText(null);
				}
			}
		};
		else if(type == CreationPaneType.VILLAIN) listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(CreationPane.this instanceof VillainCreationPane){
					Villain villain = new Villain(getName(), getDifficulty(), ((VillainCreationPane)CreationPane.this).getHealth(), getFile(), ((VillainCreationPane)CreationPane.this).isAdvanced());
					Extender.addVillain(villain);
					Extender.saveVillainData(villain);
					notifySuccessCreation(villain);
					name.setText(null); difficulty.setText(null); ((VillainCreationPane)CreationPane.this).health.setText(null);
				}	
			}
		};
		else if(type == CreationPaneType.ENVIRONMENT) listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Environment environment = new Environment(getName(), getDifficulty(), getFile());
				Extender.addEnvironment(environment);
				Extender.saveEnvironmentData(environment);
				notifySuccessCreation(environment);
				name.setText(null); difficulty.setText(null);
			}
		};

		createbutton.addActionListener(listener);		

		notifysuccess = new JLabel();

		JPanel name_west_pane = new JPanel(), diff_west_pane = new JPanel();

		name_west_pane.add(new JLabel("Name:"));
		name_west_pane.add(name);
		diff_west_pane.add(new JLabel("Difficulty:"));
		diff_west_pane.add(difficulty);
		//east_pane.add(notifysuccess, BorderLayout.);
		east_pane.add(createbutton, BorderLayout.SOUTH);

		east_pane.setPreferredSize(new Dimension(200,200));

		west_pane.add(name_west_pane);
		west_pane.add(diff_west_pane);

		JPanel dumb_image_pane = new JPanel();
		dumb_image_pane.add(addimagebutton);

		west_pane.add(dumb_image_pane);

		JPanel dumb_west_pane = new JPanel();
		dumb_west_pane.add(west_pane);

		this.add(dumb_west_pane, BorderLayout.WEST);
		this.add(east_pane, BorderLayout.EAST);
		this.setVisible(true);

		repaint();
	}

	private void notifySuccessCreation(Entity entity){
		notifysuccess.setText("created "+entity.getName());
		repaint();
	}

	public String getName(){
		return this.name.getText();
	}

	public int getDifficulty(){
		return Integer.parseInt(this.difficulty.getText());
	}

	public File getFile(){
		return this.file;
	}
}
