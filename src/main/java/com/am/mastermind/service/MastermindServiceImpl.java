/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.am.mastermind.service;

import com.am.mastermind.dto.Game;
import com.am.mastermind.dto.Round;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.am.mastermind.repo.RoundRepo;
import com.am.mastermind.repo.GameRepo;

/**
 *
 * @author ahmad
 */
@Service
public class MastermindServiceImpl implements MastermindService {
    
    @Autowired GameRepo gameRepo;
    @Autowired RoundRepo roundRepo;
    
    public static final String WIN = "e:4:p:0";
    
    @Override
    public Game start(Game game) throws Exception {
        game.setAnswer(generateAnswer());
        this.gameRepo.save(game);
        return game;
    }
    
    @Override
    public Round addRound(Round round) throws Exception {
        round.setGuessTime(getTime());
        
        Game game = getGameById(round.getGame().getId());
        
        if (!game.isInProgress())
            throw new Exception("Game is finished.");
        
        game.setInProgress(calculateResults(round));
        
        this.roundRepo.save(round);
        
        if (!game.isInProgress())
            this.gameRepo.save(game);
        
        return round;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @Override
    public Game getGameById(int id) throws Exception {
        return gameRepo.findById(id).get();
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        return roundRepo.getRoundsByGameId(gameId);
    }
    
    private LocalDateTime getTime() {
        return LocalDateTime.now();
    }
    
    private String generateAnswer() {
        Set<Integer> set = new LinkedHashSet<>();
        
        while (set.size() < 5) {
            set.add(new Random().nextInt(10));
        }
        
        return String.format("%d%d%d%d", 
                set.toArray());
    }

    private boolean calculateResults(Round round) throws Exception {
        Game game = getGameById(round.getGame().getId());
        
        String answer = game.getAnswer();
        String guess = round.getGuess();
        
        if (guess.equals(answer)) {
            round.setGuessResult(WIN);
            return false;
        }
            
        else {
            int exactMatches = 0, partialMatches = 0;
            
            char[] digits = guess.toCharArray();
            
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] == answer.charAt(i)) {
                    exactMatches++;
                }
                
                else if (answer.indexOf(digits[i]) != -1) {
                    partialMatches++;
                }
            }
            
            round.setGuessResult(
                String.format(
                    "e:%d:p:%d", 
                    exactMatches, partialMatches
                )
            );
        }
       
        return true;
    }
}
