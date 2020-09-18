package net.hackathon;

import java.util.Date;

public class Match {

    private String team1;
    private String team2;
    private String venue;
    private Date match_date;

    public Match(String team1, String team2, String venue, Date match_date) {
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
        this.match_date = match_date;
    }

    public Match() {

    }


    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getMatch_date() {
        return match_date;
    }

    public void setMatch_date(Date match_date) {
        this.match_date = match_date;
    }

}
