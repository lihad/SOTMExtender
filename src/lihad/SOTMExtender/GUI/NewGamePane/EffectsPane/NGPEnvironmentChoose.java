package lihad.SOTMExtender.GUI.NewGamePane.EffectsPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.GUI.NewGamePane.NewGamePane;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.Potion;

public class NGPEnvironmentChoose extends NGPChoose{

	private static final long serialVersionUID = -7069198067088899957L;
	
	private NewGamePane ngp;
	
	public NGPEnvironmentChoose(final NewGamePane ngp, Player player){
		super(ngp, player, Potion.CHOOSE_ENVIRONMENT, "please choose an Environment");
		
		this.ngp = ngp;
		
		super.button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setEnvironment();
				ngp.step1();
			}
			
		});
	}

	public void setEnvironment(){
		ngp.setEnvironment((Environment)super.getCombo().getSelectedItem());
		Extender.getLogger().info(NGPChoose.class, "the Environment ["+((Environment)super.getCombo().getSelectedItem()).getName()+"] was set");
	}
}
