package lihad.SOTMExtender.GUI.EntityEntryPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import lihad.SOTMExtender.GUI.EntityEntryPane.PlayerEntryPane.PlayerEntryPaneStatistics;
import lihad.SOTMExtender.GUI.EntityEntryPane.TableEntityEntryPane.TableEntityEntryPaneImage;
import lihad.SOTMExtender.GUI.EntityEntryPane.TableEntityEntryPane.TableEntityEntryPaneStatistics;
import lihad.SOTMExtender.Objects.Entity;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;

public class EntityEntryPaneInformation extends JPanel{

	private static final long serialVersionUID = 1760360781406822853L;
	
	private TableEntityEntryPaneStatistics teeps;
	private TableEntityEntryPaneImage teepi;
	private PlayerEntryPaneStatistics peps;
	private Entity entity;

	public EntityEntryPaneInformation(Entity entity, EntityEntryPaneType type){
		super(new BorderLayout());
		this.entity = entity;
		
		if(type == EntityEntryPaneType.PLAYER){
			peps = new PlayerEntryPaneStatistics((Player)entity, type);
			this.add(peps, BorderLayout.CENTER);
		}else{
			teeps = new TableEntityEntryPaneStatistics((TableEntity)entity, type);
			teepi = new TableEntityEntryPaneImage((TableEntity)entity);
			this.add(teeps, BorderLayout.WEST);
			this.add(teepi, BorderLayout.EAST);
		}
	}
	
	public TableEntityEntryPaneStatistics getTableEntityStatisticsPane(){
		return this.teeps;
	}
	
	public TableEntityEntryPaneImage getImagePane(){
		return this.teepi;
	}
	
	public PlayerEntryPaneStatistics getPlayerStatisticsPane(){
		return this.peps;
	}
	
	public Entity getEntity(){
		return this.entity;
	}
}
