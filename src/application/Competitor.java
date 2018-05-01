package application;

public class Competitor {
	private int seed;
	private String name;
	private int score;
	
	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(int score)
	{
	    this.score = score;
	}
	
	public int getScore()
	{
	    return this.score;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	Competitor(int seed, String name) {
		this.seed = seed;
		this.name = name;
		this.score = 0;
	}
}
