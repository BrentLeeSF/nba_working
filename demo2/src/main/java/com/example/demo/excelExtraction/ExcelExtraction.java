package com.example.demo.excelExtraction;

import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.example.demo.model.Player;
import com.example.demo.model.PlayerStatistics;

public class ExcelExtraction {

    // https://metamug.com/article/java/jdbc/batch-insert.html
    private HashMap<String, Player> playerMap;

    public ExcelExtraction() {
        this.playerMap = new HashMap<String, Player>();
    }

    public HashMap<String, Player> getPlayerMap() {
        return playerMap;
    }

    public ArrayList<PlayerStatistics> extractExcelFile(
        String playerStatsFile,
        int startIndex,
        ArrayList<PlayerStatistics> playerStatistics
    ) {
        try {
            FileInputStream fileStream = new FileInputStream(new File(playerStatsFile));
            XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(int i = startIndex; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                Iterator<Cell> cellIterator = row.cellIterator();
                int columnIndex = 0;
                Cell cell;
                PlayerStatistics newPlayer = new PlayerStatistics();
                while(cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    Double doubleValue = 0.0;
                    int intValue = 0;
                    if (columnIndex > 4 && columnIndex < 23) {
                        if (cell.getCellType() == CellType.STRING) {
                            doubleValue = Double.parseDouble(cell.getStringCellValue());
                        }
                        else {
                            doubleValue = cell.getNumericCellValue();
                        }
                    }
                    if ((columnIndex > 22 && columnIndex < 26) || columnIndex == 2) {
                        intValue = (int)cell.getNumericCellValue();
                    }
                    if (columnIndex == 0) {
                        String[] startYearAndEndYear = cell.getStringCellValue().split("-");
                        newPlayer.seasonPlayed = cell.getStringCellValue();
                        newPlayer.seasonStart = Integer.parseInt(startYearAndEndYear[0]);
                        newPlayer.seasonEnd = Integer.parseInt(startYearAndEndYear[1]);
                    }
                    else if (columnIndex == 1) {
                        String[] firstAndLastSplit = cell.getStringCellValue().split(" ",2);
                        newPlayer.playerFullName = cell.getStringCellValue();
                        newPlayer.playerFirstName = firstAndLastSplit[0];
                        newPlayer.playerLastName = firstAndLastSplit[1];
                        if(!playerMap.containsKey(cell.getStringCellValue())) {
                            playerMap.put(cell.getStringCellValue(), new Player(cell.getStringCellValue()));
                        }
                    }
                    else if (columnIndex == 2) {
                        newPlayer.gamesPlayed = intValue;
                    }
                    else if (columnIndex == 3) {
                        newPlayer.minutesPlayed = cell.getNumericCellValue();
                    }
                    else if (columnIndex == 4) {
                        newPlayer.points = cell.getNumericCellValue();
                    }
                    else if (columnIndex == 5) {
                        newPlayer.fieldGoalsMade = doubleValue;
                    }
                    else if (columnIndex == 6) {
                        newPlayer.fieldGoalAttempts = doubleValue;
                    }
                    else if (columnIndex == 7) {
                        newPlayer.fieldGoalPercentage = doubleValue;
                    }
                    else if (columnIndex == 8) {
                        newPlayer.threePointsMade = doubleValue;
                    }
                    else if (columnIndex == 9) {
                        newPlayer.threePointAttempts = doubleValue;
                    }
                    else if (columnIndex == 10) {
                        newPlayer.threePointPercentage = doubleValue;
                    }
                    else if (columnIndex == 11) {
                        newPlayer.freeThrowsMade = doubleValue;
                    }
                    else if (columnIndex == 12) {
                        newPlayer.freeThrowAttempts = doubleValue;
                    }
                    else if (columnIndex == 13) {
                        newPlayer.freeThrowPercentage = doubleValue;
                    }
                    else if (columnIndex == 14) {
                        newPlayer.offensiveRebounds = doubleValue;
                    }
                    else if (columnIndex == 15) {
                        newPlayer.defensiveRebounds = doubleValue;
                    }
                    else if (columnIndex == 16) {
                        newPlayer.rebounds = doubleValue;
                    }
                    else if (columnIndex == 17) {
                        newPlayer.assists = doubleValue;
                    }
                    else if (columnIndex == 18) {
                        newPlayer.turnovers = doubleValue;
                    }
                    else if (columnIndex == 19) {
                        newPlayer.steals = doubleValue;
                    }
                    else if (columnIndex == 20) {
                        newPlayer.blocks = doubleValue;
                    }
                    else if (columnIndex == 21) {
                        newPlayer.personalFouls = doubleValue;
                    }
                    else if (columnIndex == 22) {
                        newPlayer.plusMinus = doubleValue;
                    }
                    else if (columnIndex == 23) {
                        newPlayer.teamId = intValue;
                    }
                    else if (columnIndex == 24) {
                        newPlayer.conferenceId = intValue;
                    }
                    else if (columnIndex == 25) {
                        newPlayer.divisionId = intValue;
                    }
                    columnIndex++;
                }
                playerStatistics.add(newPlayer);
            }
            fileStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return playerStatistics;
    }
}
