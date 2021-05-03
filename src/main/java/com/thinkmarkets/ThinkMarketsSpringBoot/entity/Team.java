package com.thinkmarkets.ThinkMarketsSpringBoot.entity;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team {

    public Team () {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "total_matches")
    private long totalMatches;

    @Column(name = "total_wins")
    private long totalWins;

    public Team(String teamName, long totalWins) {
        this.teamName = teamName;
        this.totalWins = totalWins;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }

    public long getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(long totalWins) {
        this.totalWins = totalWins;
    }
}
