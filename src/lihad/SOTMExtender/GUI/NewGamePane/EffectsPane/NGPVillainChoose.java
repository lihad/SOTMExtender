package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;
import lihad.SOTMExtender.Objects.Villain;

public class NGPVillainChoose extends NGPChoose{

	private static final long serialVersionUID = -7069198067088899957L;
	
	private NewGamePane ngp;
	
	public NGPVillainChoose(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.CHOOSE_VILLAIN, "please choose a Villain");
		
		this.ngp = ngp;
		
		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVillain();
				ngp.step1();
			}
			
		});
	}

	public void setVillain(){
		ngp.setVillain((Villain)super.getCombo().getSelectedItem());
		Extender.getLogger().info(NGPChoose.class, "the Villain ["+((Villain)super.getCombo().getSelectedItem()).getName()+"] was set");
	}
}
