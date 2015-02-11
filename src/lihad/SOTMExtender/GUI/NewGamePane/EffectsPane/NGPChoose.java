package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.ComboBoxModels.TableEntityModel;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.GUI.Renders.TableEntityComboRender;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Villain;

public class NGPChoose extends JPanel{

	private static final long serialVersionUID = 3171181151871806688L;
	private Player player;
	private JComboBox<TableEntity> combo;
	protected JButton button;

	public NGPChoose(NewGamePane ngp, Player player, Potion potion, String string){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.player = player;
		this.button = new JButton("next");
		
		JLabel label = new JLabel("player ["+player.getName()+"] uses ["+potion.getName()+"], "+string);
		this.add(label);
		
		if(potion == Potion.CHOOSE_ENVIRONMENT || potion == Potion.CHOOSE_HERO || potion == Potion.CHOOSE_VILLAIN){
			combo = new JComboBox<TableEntity>();
			combo.setRenderer(new TableEntityComboRender());
			combo.setModel(new TableEntityModel());
			switch(potion){
			case CHOOSE_ENVIRONMENT:
				for(Environment v : Extender.getEnvironments())combo.addItem(v);
				break;
			case CHOOSE_VILLAIN:
				for(Villain v : Extender.getVillains())combo.addItem(v);
				break;
			case CHOOSE_HERO:
				for(Hero v : Extender.getHeroes())combo.addItem(v);
				break;				
			default: break;
			}
			this.add(combo);
		}
		
		this.add(button);
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	protected JComboBox<TableEntity> getCombo(){
		return this.combo;
	}
}
