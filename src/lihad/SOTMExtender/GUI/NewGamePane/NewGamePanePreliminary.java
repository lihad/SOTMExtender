package lihad.SOTMExtender.GUI.NewGamePane;

import java.awt.FlowLayout;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Villain;

public class NewGamePanePreliminary extends JPanel{
	
	private static final long serialVersionUID = -6754183026272184269L;

	public NewGamePanePreliminary(Map<Player, Hero> hero_map, Villain villain, Environment environment){
		super(new FlowLayout(FlowLayout.LEFT, 1, 1));

		for(Entry<Player, Hero> entry : hero_map.entrySet()){
			this.add(new NewGamePanePreliminaryTableEntityCard(entry.getKey(), entry.getValue()));
		}
		
		this.add(new NewGamePanePreliminaryTableEntityCard(null, villain));
		this.add(new NewGamePanePreliminaryTableEntityCard(null, environment));
		this.add(new JLabel(villain.isAdvanced() ? "is ADV" : "is NML"));

	}

}
