package lihad.SOTMExtender.GUI.CloseGamePane;

import java.awt.FlowLayout;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.ComboBoxModels.TableEntityModel;
import lihad.SOTMExtender.GUI.Renders.TableEntityComboRender;
import lihad.SOTMExtender.Objects.Award;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.TableEntity;

public class CloseGamePaneAward extends JPanel{

	private static final long serialVersionUID = -2569903350034786559L;
	
	private Award award;
	private JComboBox<TableEntity> combo;
	
	public CloseGamePaneAward(Game game){
		super(new FlowLayout(FlowLayout.LEFT, 1, 1));
		
		award = Award.values()[new Random().nextInt(Award.values().length)];
		
		this.add(new JLabel("Select the Hero who deserves the ["+award.getName()+"] award! "));
		
		combo = new JComboBox<TableEntity>();
		
		combo.setRenderer(new TableEntityComboRender());
		combo.setModel(new TableEntityModel());
		
		if(game == null)Extender.getLogger().debug(CloseGamePaneAward.class, "GAME IS NULL");
		if( game.getHeroes() == null)Extender.getLogger().debug(CloseGamePaneAward.class, "HERO IS NULL");
		
		for(Hero hero : game.getHeroes()) combo.addItem(hero);
		this.add(combo);
	}
	
	public Award getAward(){
		return this.award;
	}
	
	public Hero getRecipient(){
		return (Hero)this.combo.getItemAt(this.combo.getSelectedIndex());
	}

}
