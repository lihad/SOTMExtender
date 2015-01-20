package lihad.SOTMExtender.GUI.TableEntityEditPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.CloseGamePane.CloseGamePaneTableEntityCard;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Character;
import lihad.SOTMExtender.Objects.Villian;

public class TableEntityEditPaneSave extends JPanel{

	private static final long serialVersionUID = -3802952604862627369L;
	
	private TableEntityEditPaneInformation info_pane;
	
	private JLabel save_text;
	
	TableEntityEditPaneSave(TableEntityEditPaneInformation info_pane){
		super(new BorderLayout());
		
		this.info_pane = info_pane;
		
		JButton save_button = new JButton("save");
		save_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TableEntityEditPaneInformation pane = TableEntityEditPaneSave.this.info_pane;
				TableEntity tableentity = pane.getTableEntity();
				tableentity.setDifficulty(pane.getStatisticsPane().getDifficulty());
				tableentity.setName(pane.getStatisticsPane().getName());
				tableentity.setImageFile(pane.getImagePane().getImageFile());
				if(tableentity instanceof Character){
					Character character = (Character) tableentity;
					character.setHealth(pane.getStatisticsPane().getHealth());
					if(character instanceof Villian){
						Villian villian = (Villian) character;
						villian.setAdvanced(pane.getStatisticsPane().isAdvanced());
					}
				}

				Extender.saveTableEntityData(tableentity);
				save_text.setText("saved ["+tableentity.getName()+"]");
			}
		});
		this.add(save_button, BorderLayout.EAST);
		
		save_text = new JLabel("");
		this.add(save_text, BorderLayout.WEST);
	}
}
