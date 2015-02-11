package lihad.SOTMExtender.GUI.EntityEntryPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.Entity;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Character;
import lihad.SOTMExtender.Objects.Villain;

public class EntityEntryPaneSave extends JPanel{

	private static final long serialVersionUID = -3802952604862627369L;

	private EntityEntryPaneInformation info_pane;

	private JLabel save_text;
	private EntityEntryPaneType type;

	EntityEntryPaneSave(EntityEntryPaneInformation info_pane, EntityEntryPaneType type){
		super(new BorderLayout());

		this.info_pane = info_pane; this.type = type;

		JButton save_button = new JButton("save");
		save_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EntityEntryPaneInformation pane = EntityEntryPaneSave.this.info_pane;
				Entity entity = pane.getEntity();

				if(EntityEntryPaneSave.this.type != EntityEntryPaneType.PLAYER){
					TableEntity tableentity = (TableEntity)entity;

					if(tableentity != null){
						tableentity.setDifficulty(pane.getTableEntityStatisticsPane().getDifficulty());
						tableentity.setName(pane.getTableEntityStatisticsPane().getName());
						tableentity.setImageFile(pane.getImagePane().getImageFile());
						if(tableentity instanceof Character){
							Character character = (Character) tableentity;
							character.setHealth(pane.getTableEntityStatisticsPane().getHealth());
							if(tableentity instanceof Villain){
								Villain villain = (Villain) character;
								villain.setAdvancedDifficulty(pane.getTableEntityStatisticsPane().getAdvancedDifficulty());
							}
						}

						Extender.saveTableEntityData(tableentity);
						save_text.setText("updated ["+tableentity.getName()+"]");
					}else{
						if(EntityEntryPaneSave.this.type == EntityEntryPaneType.VILLAIN){
							Villain villain = new Villain(pane.getTableEntityStatisticsPane().getName(), pane.getTableEntityStatisticsPane().getDifficulty(), pane.getTableEntityStatisticsPane().getHealth(), pane.getImagePane().getImageFile(), pane.getTableEntityStatisticsPane().getAdvancedDifficulty());
							Extender.addVillain(villain);
							tableentity = villain;
						}
						else if(EntityEntryPaneSave.this.type == EntityEntryPaneType.HERO){
							Hero hero = new Hero(pane.getTableEntityStatisticsPane().getName(), pane.getTableEntityStatisticsPane().getDifficulty(), pane.getTableEntityStatisticsPane().getHealth(), pane.getImagePane().getImageFile());
							Extender.addHero(hero);
							tableentity = hero;
						}
						else if(EntityEntryPaneSave.this.type == EntityEntryPaneType.ENVIRONMENT){
							Environment environment = new Environment(pane.getTableEntityStatisticsPane().getName(), pane.getTableEntityStatisticsPane().getDifficulty(), pane.getImagePane().getImageFile());
							Extender.addEnvironment(environment);
							tableentity = environment;
						}
						Extender.saveTableEntityData(tableentity);
						save_text.setText("created ["+tableentity.getName()+"]");
					}
				}else{
					//TODO: save player stuff here
					
					
				}
			}
		});
		this.add(save_button, BorderLayout.EAST);

		save_text = new JLabel("");
		this.add(save_text, BorderLayout.WEST);
	}
}
