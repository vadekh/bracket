package application;

public class Competitor {
	private int seed;
	private String name;
	
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

	Competitor(int seed, String name) {
		this.seed = seed;
		this.name = name;
	}
	
	
}
