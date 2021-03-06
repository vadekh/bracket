package application;

import java.io.*;
import java.util.ArrayList;

public class TeamLoader {
    private String[] teams;

    public TeamLoader(String filePath) {
        // read file and load into memory
        try {
            loadTeams(filePath);
            System.out.println("Loaded teams: ");
            for(String team : teams) {
                System.out.println(team);
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Loads data from file and parses
     * it into data structures to be held in memory
     */
    private void loadTeams(String path) throws IOException {
        FileReader reader = new FileReader(path);
        BufferedReader buff = new BufferedReader(reader);
        ArrayList<String> list = new ArrayList<>();
        String line = null;
        while((line = buff.readLine()) != null) {
            list.add(line);
        }
        buff.close();
        this.teams = new String[list.size()];
        for(int i = 0; i < this.teams.length; i++)
        {
            teams[i] = list.get(i);
        }
    }

    /**
     * Get number of teams
     */
    public int getNumTeams() {
        return this.teams.length;
    }

    /**
     * Get the teams
     */
    public String[] getTeams() {
        return this.teams;
    }
}