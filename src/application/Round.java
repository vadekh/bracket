package application;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;

public class Round {
	int roundNumber;
	int totalTeamNumber;
	Competitor[] roundCompetitors;
	Matchup[] roundMatchups;
	Competitor[] matchupWinners;
	
	Round(int roundNumber, Competitor[] roundCompetitors) {
		this.roundNumber = roundNumber;
		this.totalTeamNumber = roundCompetitors.length;
		this.roundCompetitors = roundCompetitors;
		this.roundMatchups = new Matchup[totalTeamNumber/2];
		this.matchupWinners = new Competitor[totalTeamNumber/2];
	}
	
	
	public void setMatchups() {
        int j = 0;
		for(int i = 0; i < this.roundMatchups.length; i++)
		{
		    roundMatchups[i] = new Matchup(roundCompetitors[j], roundCompetitors[j+1]);
		    j+=2;
		}
	}
	
	public Matchup[] getMatchups() {
		if(this.roundMatchups != null) {
			return this.roundMatchups;
		}
		
		else {
			return null;
		}
	}
	
	public Competitor[] calculateMatchupWinners() {
		for(int i = 0; i < this.roundMatchups.length; i++) {
			this.matchupWinners[i] = roundMatchups[i].getWinner();
		}
		System.out.println("Winners: " + this.matchupWinners);
		return this.matchupWinners;
	}
}
