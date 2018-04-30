package application;
import java.util.ArrayList;
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
		Collections.sort(this.roundCompetitors, new Comparator<Competitor>() {
			@Override
		    public int compare(Competitor c1, Competitor c2) {
		        return c1.getSeed() < c2.getSeed() ? -1 : (c1.getSeed() > c2.getSeed()) ? 1 : 0;
		    }
		});
		for(int j = 0; j < this.totalTeamNumber / 2; j++) {
            this.roundMatchups.add(new Matchup(this.roundCompetitors.get(j), 
            		this.roundCompetitors.get(this.roundCompetitors.size() - 1 - j)));
		}
		
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
		for(int i = 0; i < this.roundMatchups.size(); i++) {
			this.roundMatchups.get(i).setScore1(50);
			this.roundMatchups.get(i).setScore2(75);
			this.matchupWinners.add(this.roundMatchups.get(i).getWinner());
		}
		return this.matchupWinners;
	}
	
}
