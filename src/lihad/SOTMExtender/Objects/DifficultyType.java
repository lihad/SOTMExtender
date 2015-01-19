package lihad.SOTMExtender.Objects;

public enum DifficultyType{
	
	EXTREMELY_EASY("Extremely Easy", -999,-200, .05),
	VERY_EASY("Very Easy", -199, -125, .10),
	EASY("Easy", -124, -65, .16),
	TYPICAL("Typical", -64, 0, .27),
	CHALLENGING("Challenging", 1, 65, .41),
	DIFFICULT("Difficult", 66, 100, .49),
	VERY_HARD("Very Hard", 101, 200, .73),
	EXTREMELY_HARD("Extremely Hard", 201, 275, .85),
	IMPOSSIBLE("Impossible", 276, 999, 1.00);
	
	private int top, bottom;
	private double exp_mod;
	
	DifficultyType(String string, int bottom, int top, double exp_mod){
		this.top = top;
		this.bottom = bottom;
		this.exp_mod = exp_mod;
	}

	public int getTopRange(){
		return this.top;
	}
	
	public int getBottomRange(){
		return this.bottom;
	}
	
	public double getExperienceModifier(){
		return this.exp_mod;
	}
	
}
