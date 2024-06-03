package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(nativeQuery = true, value = "select * from player pl where pl.player_first_name ilike %?1")
    Player findByFirstName(String firstName);
}
