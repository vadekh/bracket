package application;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;

public class Round {
	int roundNumber;
	int totalTeamNumber;
	ArrayList<Competitor> roundCompetitors;
	ArrayList<Matchup> roundMatchups;
	ArrayList<Competitor> matchupWinners;
	
	Round(int roundNumber, ArrayList<Competitor> roundCompetitors) {
		this.roundNumber = roundNumber;
		this.totalTeamNumber = roundCompetitors.size();
		this.roundCompetitors = roundCompetitors;
		this.roundMatchups = new ArrayList<Matchup>();
		this.matchupWinners = new ArrayList<Competitor>();
	}
	
	
	public void setMatchups() {
		
		if(this.roundNumber == 1) {
		Collections.sort(this.roundCompetitors, new Comparator<Competitor>() {
			@Override
		    public int compare(Competitor c1, Competitor c2) {
		        return c1.getSeed() < c2.getSeed() ? -1 : (c1.getSeed() > c2.getSeed()) ? 1 : 0;
		    }
		});  }
		
		for(int j = 0; j < this.totalTeamNumber / 2; j++) {
            this.roundMatchups.add(new Matchup(this.roundCompetitors.get(j), 
            		this.roundCompetitors.get(this.roundCompetitors.size() - 1 - j)));
		}
		System.out.println("Competitors: " + this.roundCompetitors);
		System.out.println("Matchups: " + this.roundMatchups);
	}
	
	public ArrayList<Matchup> getMatchups() {
		if(this.roundMatchups != null) {
			return this.roundMatchups;
		}
		
		else {
			return null;
		}
	}
	
	public ArrayList<Competitor> calculateMatchupWinners() {
		Random randGen = new Random();
		for(int i = 0; i < this.roundMatchups.size(); i++) {
			this.roundMatchups.get(i).setScore1(randGen.nextInt(1000));
			this.roundMatchups.get(i).setScore2(randGen.nextInt(1000));
			this.matchupWinners.add(this.roundMatchups.get(i).getWinner());
		}
		
		System.out.println("Winners: " + this.matchupWinners);
		return this.matchupWinners;
	}
}
