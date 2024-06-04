package com.example.demo.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.example.demo.databaseConnection.DatabaseConnection;
import com.example.demo.model.Player;
import com.example.demo.model.PlayerStatistics;

@RestController
public class FileExtractionController {
    
    public void executeSQLQueryFile(ArrayList<FileSystemResource> queryFileToExecute) {
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connection = db.connect();
            for(int i = 0; i < queryFileToExecute.size(); i++) {
                connection.setAutoCommit(false);
                ScriptUtils.executeSqlScript(connection, queryFileToExecute.get(i));
                connection.setAutoCommit(true);
            }
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPlayersIntoPlayerTable(HashMap<String, Player> playerMapReturned) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();
        connection.setAutoCommit(false);
        PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO player(" +
                "id, player_full_name, player_first_name, player_last_name" +
                ") VALUES (?, ?, ?, ?)");
        long id = 0;
        for (Entry<String, Player> entry : playerMapReturned.entrySet()) {
            prepStatement.setLong(1, id);
            prepStatement.setString(2, entry.getValue().getFullName());
            prepStatement.setString(3, entry.getValue().getFirstName());
            prepStatement.setString(4, entry.getValue().getLastName());
            ++id;
            prepStatement.addBatch();
        }
        prepStatement.executeBatch();
        connection.commit();
        connection.close();
    }

    public void insertPlayerStats(ArrayList<PlayerStatistics> arrayListOfPlayerStats) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();
        connection.setAutoCommit(false);
        PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO player_statistics(" +
                "id, season_played, season_start, season_end, player_full_name, player_first_name, player_last_name, " +
                "games_played, minutes_played, points, field_goals_made, field_goal_attempts, field_goal_percentage, " +
                "three_points_made, three_point_attempts, three_point_percentage, free_throws_made, free_throw_attempts, free_throw_percentage, " +
                "offensive_rebounds, defensive_rebounds, rebounds, assists, turnovers, steals, blocks, personal_fouls, " +
                "plus_minus, team_id, conference_id, division_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        PlayerStatistics eachPlayerStat = null;
        long id = 0;
        for(int i = 0; i < arrayListOfPlayerStats.size(); i++) {
            eachPlayerStat = arrayListOfPlayerStats.get(i);
            prepStatement.setLong(1, id);
            prepStatement.setString(2, eachPlayerStat.seasonPlayed);
            prepStatement.setInt(3, eachPlayerStat.seasonStart);
            prepStatement.setInt(4, eachPlayerStat.seasonEnd);
            prepStatement.setString(5, eachPlayerStat.playerFullName);
            prepStatement.setString(6, eachPlayerStat.playerFirstName);
            prepStatement.setString(7, eachPlayerStat.playerLastName);
            prepStatement.setInt(8, eachPlayerStat.gamesPlayed);
            prepStatement.setDouble(9, eachPlayerStat.minutesPlayed);
            prepStatement.setDouble(10, eachPlayerStat.points);
            prepStatement.setDouble(11, eachPlayerStat.fieldGoalsMade);
            prepStatement.setDouble(12, eachPlayerStat.fieldGoalAttempts);
            prepStatement.setDouble(13, eachPlayerStat.fieldGoalPercentage);
            prepStatement.setDouble(14, eachPlayerStat.threePointsMade);
            prepStatement.setDouble(15, eachPlayerStat.threePointAttempts);
            prepStatement.setDouble(16, eachPlayerStat.threePointPercentage);
            prepStatement.setDouble(17, eachPlayerStat.freeThrowsMade);
            prepStatement.setDouble(18, eachPlayerStat.freeThrowAttempts);
            prepStatement.setDouble(19, eachPlayerStat.freeThrowPercentage);
            prepStatement.setDouble(20, eachPlayerStat.offensiveRebounds);
            prepStatement.setDouble(21, eachPlayerStat.defensiveRebounds);
            prepStatement.setDouble(22, eachPlayerStat.rebounds);
            prepStatement.setDouble(23, eachPlayerStat.assists);
            prepStatement.setDouble(24, eachPlayerStat.turnovers);
            prepStatement.setDouble(25, eachPlayerStat.steals);
            prepStatement.setDouble(26, eachPlayerStat.blocks);
            prepStatement.setDouble(27, eachPlayerStat.personalFouls);
            prepStatement.setDouble(28, eachPlayerStat.plusMinus);
            prepStatement.setInt(29, eachPlayerStat.teamId);
            prepStatement.setInt(30, eachPlayerStat.conferenceId);
            prepStatement.setInt(31, eachPlayerStat.divisionId);
            ++id;
            prepStatement.addBatch();
        }
        prepStatement.executeBatch();
        connection.commit();
        connection.close();
    }
}
