package com.sujan.gameapi.repository;

import com.sujan.gameapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository  extends JpaRepository<Game, Long> {
    List<Game> findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(String title, String genre);

}
