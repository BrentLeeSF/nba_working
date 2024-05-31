package com.example.demo.model;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "player_statistics")
public class PlayerStatistics {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

    @Column(name="assists")
    public double assists;

    @Column(name="blocks")
    public double blocks;

    @Column(name="conference_id")
    public int conferenceId;

    @Column(name="defensive_rebounds")
    public double defensiveRebounds;

    @Column(name="division_id")
    public int divisionId;

    @Column(name="field_goal_attempts")
    public double fieldGoalAttempts;

    @Column(name="field_goals_made")
    public double fieldGoalsMade;

    @Column(name="field_goal_percentage")
    public double fieldGoalPercentage;

    @Column(name="free_throw_attempts")
    public double freeThrowAttempts;

    @Column(name="free_throws_made")
    public double freeThrowsMade;

    @Column(name="free_throw_percentage")
    public double freeThrowPercentage;

    @Column(name="games_played")
    public int gamesPlayed;

    @Column(name="minutes_played")
    public double minutesPlayed;

    @Column(name="offensive_rebounds")
    public double offensiveRebounds;

    @Column(name="plus_minus")
    public double plusMinus;

    @Column(name="personal_fouls")
    public double personalFouls;

    @Column(name="player_first_name")
    public String playerFirstName;

    @Column(name="player_full_name")
    public String playerFullName;

    @Column(name="player_last_name")
    public String playerLastName;

    @Column(name="points")
    public double points;

    @Column(name="rebounds")
    public double rebounds;

    @Column(name="season_played")
    public String seasonPlayed;

    @Column(name="season_end")
    public int seasonEnd;

    @Column(name="season_start")
    public int seasonStart;

    @Column(name="steals")
    public double steals;

    @Column(name="team_id")
    public int teamId;

    @Column(name="three_point_attempts")
    public double threePointAttempts;

    @Column(name="three_points_made")
    public double threePointsMade;

    @Column(name="three_point_percentage")
    public double threePointPercentage;

    @Column(name="turnovers")
    public double turnovers;

    public PlayerStatistics() {
        this.assists = assists;
        this.blocks = blocks;
        this.conferenceId = conferenceId;
        this.defensiveRebounds = defensiveRebounds;
        this.divisionId = divisionId;
        this.fieldGoalAttempts = fieldGoalAttempts;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalPercentage = fieldGoalPercentage;
        this.freeThrowAttempts = freeThrowAttempts;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowPercentage = freeThrowPercentage;
        this.gamesPlayed = gamesPlayed;
        this.minutesPlayed = minutesPlayed;
        this.offensiveRebounds = offensiveRebounds;
        this.plusMinus = plusMinus;
        this.personalFouls = personalFouls;
        this.playerFirstName = playerFirstName;
        this.playerFullName = playerFullName;
        this.playerLastName = playerLastName;
        this.id = id;
        this.points = points;
        this.rebounds = rebounds;
        this.seasonPlayed = seasonPlayed;
        this.seasonEnd = seasonEnd;
        this.seasonStart = seasonStart;
        this.steals = steals;
        this.teamId = teamId;
        this.threePointAttempts = threePointAttempts;
        this.threePointsMade = threePointsMade;
        this.threePointPercentage = threePointPercentage;
        this.turnovers = turnovers;
    }
}
