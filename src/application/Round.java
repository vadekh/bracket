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
		this.matchupWinners = new ArrayList<Competitor>();
	}
	
	
	public void setMatchups() {
		Collections.sort(this.roundCompetitors, new Comparator<Competitor>() {
			@Override
		    public int compare(Competitor c1, Competitor c2) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		        return c1.getSeed() > c2.getSeed() ? -1 : (c1.getSeed() < c2.getSeed()) ? 1 : 0;
		    }
		});
			
		for(int j = 0; j < this.totalTeamNumber / 2; j++) {
            this.roundMatchups.add(new Matchup(this.roundCompetitors.get(j), 
            		this.roundCompetitors.get((this.totalTeamNumber / 2) - 1 - j)));
		}
	}
	
	public ArrayList<Matchup> getMatchups() throws Exception {
		if(this.roundMatchups != null) {
			return this.roundMatchups;
		}
		
		else {
			throw new Exception("BAD");
		}
	}
	
	public ArrayList<Competitor> calculateMatchupWinners() {
		for(int i = 0; i < this.roundMatchups.size(); i++) {
			this.matchupWinners.add(this.roundMatchups.get(i).getWinner());
		}
		
		return this.matchupWinners;
	}
	
}
