package com.sujan.gameapi.controller;

import com.sujan.gameapi.dto.SearchRequest;
import com.sujan.gameapi.model.Game;
import com.sujan.gameapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //new game
    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
        return gameRepository.findById(id)
                .map(game -> {
                    game.setTitle(updatedGame.getTitle());
                    game.setGenre(updatedGame.getGenre());
                    gameRepository.save(game);
                    return ResponseEntity.ok(game);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        return gameRepository.findById(id)
                .map(game -> {
                    gameRepository.delete(game);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/search")
    public List<Game> searchGames(@RequestBody SearchRequest searchRequest){
        String title = searchRequest.getTitle() != null ? searchRequest.getTitle() : "";
        String genre = searchRequest.getGenre() != null ? searchRequest.getGenre() : "";
        return gameRepository.findByTitleContainingIgnoreCaseOrGenreContainingIgnoreCase(title, genre);
    }

}
