package application;
import java.util.Random;

public class Matchup {
	Competitor c1;
	Competitor c2;
	int score1;
	int score2;
	
	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}
	public void setCompetitor1(Competitor c1)
	{
	    this.c1 = c1;
	}
	public void setCompetitor2(Competitor c2)
    {
        this.c2 = c2;
    }
		@Override
	public String toString() {
		return this.c1.toString() + " vs " + this.c2.toString();
	}
	
	Matchup(Competitor c1, Competitor c2) {
		this.c1 = c1;
		this.c2 = c2;
		this.score1 = 0;
		this.score2 = 0;
	}
	
	
	public Competitor getWinner() {
		if(this.score1 > this.score2) {
			return this.c1;
		}
		
		else if(this.score1 < this.score2) {
			return this.c2;
		}
		
		else {
			Random rand = new Random();
			
			if(rand.nextInt(10)<5) return this.c1;

			return this.c2;
			
		}
	}
}
