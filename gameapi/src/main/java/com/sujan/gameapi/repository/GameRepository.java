package com.sujan.gameapi.repository;

import com.sujan.gameapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository Add GameRepository to manage Game entities extends JpaRepository<Game, Long> {
}
