package com.sujan.gameapi.controller;

import com.sujan.gameapi.model.Game;
import com.sujan.gameapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    //all games
    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    //new game
    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }
}
