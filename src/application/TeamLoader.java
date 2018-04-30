package application;

import java.io.*;

public class TeamLoader {
    private String[] teams;
    private int numTeams;

    public TeamLoader(String filePath) {
        // read file and load into memory
        loadTeams(filePath);

    }

    /**
     * Loads data from file and parses
     * it into data structures to be held in memory
     */
    private void loadTeams(String path) {

    }

    /**
     * Get number of teams
     */
    public int getNumTeams() {
        return this.numTeams;
    }

    /**
     * Get the teams
     */
    public String[] getTeams() {
        return this.teams;
    }
}