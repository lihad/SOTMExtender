package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;

public class NGPMakeAdvanced extends NGPChoose{

	private static final long serialVersionUID = -7069198067088899957L;
	
	private NewGamePane ngp;
	
	public NGPMakeAdvanced(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.MAKE_ADVANCED, "the game will be Advanced!  press 'Next' to continue.");
		
		this.ngp = ngp;
		
		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setAdvanced(true);
				ngp.step1();
			}
			
		});
	}

	public void setAdvanced(boolean is){
		ngp.setAdvanced(is);
		Extender.getLogger().info(NGPChoose.class, "the Villain will now be 'Advanced'!");
	}
}
