/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.am.mastermind.controller;

import com.am.mastermind.dto.Game;
import com.am.mastermind.dto.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.am.mastermind.service.MastermindService;

/**
 *
 * @author ahmad
 */
@ControllerAdvice
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/mastermind")
public class Controller {
    @Autowired
    private MastermindService service;
    
    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game begin() throws Exception {
        Game game = new Game(true);
        return this.service.start(game);
    }
    
    @PostMapping("/round")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guess(@RequestBody Round round) throws Exception {
        return this.service.addRound(round);
    }
    
    @GetMapping("/games")
    public List<Game> getGames() {
        return this.service.getAllGames();
    }
    
    @GetMapping("/game")
    public Game getGame(@RequestParam int gameId) throws Exception {
        return this.service.getGameById(gameId);
    }
    
    @GetMapping("/rounds")
    public List<Round> getRounds(@RequestParam int gameId) {
        return this.service.getRoundsByGameId(gameId);
    }
}
