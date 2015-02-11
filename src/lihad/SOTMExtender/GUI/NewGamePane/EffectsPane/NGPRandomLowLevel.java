package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;

public class NGPRandomLowLevel extends NGPChoose{

	private static final long serialVersionUID = -7069198067088899957L;
	
	private NewGamePane ngp;
	
	public NGPRandomLowLevel(final NewGamePane ngp, final Player player){
		super(ngp, player, Potion.RANDOM_LOW_LEVEL, ".  a random low-level will be chosen. press 'Next' to continue.");
		
		this.ngp = ngp;
		
		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				randomLL(player);
				ngp.step1();
			}
			
		});
	}

	public void randomLL(Player player){
		ngp.randomLL(player);
		Extender.getLogger().info(NGPChoose.class, "["+player.getName()+"] is being assigned a random low-level hero.");
	}
}
