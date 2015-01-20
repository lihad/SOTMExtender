package lihad.SOTMExtender.GUI.TableEntityEditPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import lihad.SOTMExtender.Objects.TableEntity;

public class TableEntityEditPaneInformation extends JPanel{

	private static final long serialVersionUID = 1760360781406822853L;
	
	private TableEntityEditPaneStatistics teeps;
	private TableEntityEditPaneImage teepi;
	private TableEntity entity;

	TableEntityEditPaneInformation(TableEntity entity){
		super(new BorderLayout());
		this.entity = entity;
		
		teeps = new TableEntityEditPaneStatistics(entity);
		teepi = new TableEntityEditPaneImage(entity);
		
		this.add(teeps, BorderLayout.WEST);
		this.add(teepi, BorderLayout.EAST);
	}
	
	public TableEntityEditPaneStatistics getStatisticsPane(){
		return this.teeps;
	}
	
	public TableEntityEditPaneImage getImagePane(){
		return this.teepi;
	}
	
	public TableEntity getTableEntity(){
		return this.entity;
	}
}
