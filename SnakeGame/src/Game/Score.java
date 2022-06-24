package Game;

public class Score {
	
	// You do not need to edit this file
	
	private String name;
	private int score;
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	public void setName(String name) {
		if(name.length()<1) {
			name = "Player";
		}
		this.name = name;
	}
	public void setScore(int score) {
		if(score>1000||score<0) {
			score = 0;
		}
		this.score=score;
	}
	public Score(String name, int score) {
		setName(name);
		setScore(score);
	}
	
}
