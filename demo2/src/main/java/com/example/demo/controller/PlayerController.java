package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.excelExtraction.ExcelExtraction;
import com.example.demo.model.Player;
import com.example.demo.model.PlayerStatistics;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.PlayerStatisticsRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PlayerStatisticsRepository playerStatisticsRepository;

	@Value("file:./demo2/src/main/java/com/example/demo/queries/*.sql")
    private Resource[] dataScripts;

	@SuppressWarnings("null")
    @PostMapping("/players")
	public ResponseEntity<HashMap<String, Player>> createplayer() {
		FileExtractionController cont = new FileExtractionController();

		ArrayList<FileSystemResource> listOfQueriesToExecute = new ArrayList<FileSystemResource>();
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/createConferenceTable.sql"));
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/insertConferenceData.sql"));
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/createDivisionTable.sql"));
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/insertDivisionData.sql"));
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/createTeamTable.sql"));
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/insertTeamData.sql"));
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/createNBAPositionTable.sql"));
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/insertNBAPositionData.sql"));
		cont.executeSQLQueryFile(listOfQueriesToExecute);
		
		ExcelExtraction	ex = new ExcelExtraction();
		String Stats2022_2023File = "./demo2/2022-2023_Stats_copy.xlsx";
		ArrayList<PlayerStatistics> arrayListOfEastStats = new ArrayList<>();

		arrayListOfEastStats = ex.extractExcelFile(Stats2022_2023File, 4, arrayListOfEastStats);
		HashMap<String, Player> playerMapReturned = ex.getPlayerMap();
		
		try {
			cont.insertPlayersIntoPlayerTable(playerMapReturned);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			cont.insertPlayerStats(arrayListOfEastStats);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(playerMapReturned, HttpStatus.CREATED);
	}

    @GetMapping("/players")
	public ResponseEntity<List<Player>> getAllPlayers() {
		List<Player> playerData = playerRepository.findAll();
		if(playerData != null) {
			return new ResponseEntity<>(playerData, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/players/firstname")
	public ResponseEntity<Optional<Player>> getPlayerByFirstName(@PathVariable("firstName") String firstName) {
		Optional<Player> player = Optional.ofNullable(playerRepository.findByFirstName(firstName));
		if (player != null) {
			return new ResponseEntity<>(player, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @GetMapping("/players/{id}")
	public ResponseEntity<Optional<Player>> getPlayerById(@PathVariable("id") int id) {
		Optional<Player> player = playerRepository.findById((long) id);
		if (player != null) {
			return new ResponseEntity<>(player, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/player-statistics")
	public ResponseEntity<List<PlayerStatistics>> getAllPlayerStatistics() {
		List<PlayerStatistics> playerData = playerStatisticsRepository.findAll();
		if(playerData != null) {
			return new ResponseEntity<>(playerData, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/player-statistics/{id}")
	public ResponseEntity<Optional<PlayerStatistics>> getPlayerStatisticsById(@PathVariable("id") int id) {
		Optional<PlayerStatistics> playerStats = playerStatisticsRepository.findById((long) id);
		if (playerStats != null) {
			return new ResponseEntity<>(playerStats, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @GetMapping("/player-statistics/query")
    public ResponseEntity<Optional<Player>> search(@RequestParam HashMap<String, String> requestParams) {
        Optional<Player> player = null;
		StringBuilder sb = new StringBuilder();
		sb.append("tacos");
		for(int i = 0; i < dataScripts.length; i++) {
			try {
				File hey = dataScripts[i].getFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		}
		for (Entry<String, String> entry : requestParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key=" + key + ", Value=" + value);
        }
		return new ResponseEntity<>(player, HttpStatus.OK);
    }

	
	@DeleteMapping("/players")
	public ResponseEntity<Player> deleteAllPlayers() {
		FileExtractionController cont = new FileExtractionController();
		ArrayList<FileSystemResource> listOfQueriesToExecute = new ArrayList<FileSystemResource>();
		listOfQueriesToExecute.add(new FileSystemResource("./demo2/src/main/java/com/example/demo/queries/dropAllTables.sql"));
		cont.executeSQLQueryFile(listOfQueriesToExecute);
		return new ResponseEntity<>(new Player(), HttpStatus.OK);
    }
}
