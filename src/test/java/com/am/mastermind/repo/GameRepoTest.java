/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.am.mastermind.repo;

import com.am.mastermind.App;
import com.am.mastermind.dto.Game;
import com.am.mastermind.dto.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ahmad
 */
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = App.class)
public class GameRepoTest {
    
    @Autowired private GameRepo gameRepo;
    @Autowired private RoundRepo roundRepo;
    
    private static Game game; 
    private static Round round;
    
    
    @BeforeAll
    public void setUpClass() throws Exception {
        game = new Game(true);
        game.setAnswer("1234");
        gameRepo.save(game);
        
        round = new Round();
        round.setRoundId(1);
        round.setGame(game);
        round.setGuess("1234");
        round.setGuessResult("e:4:p:0");
        round.setGuessTime(LocalDateTime.now());
    }
    
    @AfterAll
    public void tearDownClass() throws Exception {
        roundRepo.deleteAll();
        gameRepo.deleteAll();
    }
    
    /**
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        
        
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        game.setInProgress(true);
    }
    
    /**
     * Test of createGame method, of class GuessTheNumberDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateGame() throws Exception {
        List<Game> gamesList = gameRepo.findAll();
        int originalListSize = gamesList.size();
        
        Game game2 = new Game(true);
        game2.setAnswer("1234");
        
        gameRepo.save(game2);
        
        List<Game> currentGamesList = gameRepo.findAll();
        int subsequentListSize = currentGamesList.size();
        
        assertEquals(originalListSize + 1, subsequentListSize);
        
        gameRepo.deleteById(game2.getId());
        
    }

    /**
     * Test of getAllGames method, of class GuessTheNumberDaoDB.
     */
    @Test
    public void testGetAllGames() {
         List<Game> currentGamesList = gameRepo.findAll();
         assertTrue(!currentGamesList.isEmpty());
    }

    /**
     * Test of getGameById method, of class GuessTheNumberDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetGameById() throws Exception {
        int id = game.getId();
        
        Game currentGame = gameRepo.findById(id).get();
        
        assertEquals(game, currentGame);
    }

    /**
     * Test of update method, of class GuessTheNumberDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdate() throws Exception {
        game.setInProgress(false);
        gameRepo.save(game);
        
        Game updatedGame = gameRepo.findById(game.getId()).get();
        
        assertEquals(false, updatedGame.isInProgress());
    }

    /**
     * Test of addRound method, of class GuessTheNumberDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddRound() throws Exception {
        List<Round> rounds = roundRepo.getRoundsByGameId(game.getId());
        
        Round addedRound = roundRepo.save(round);
        
        List<Round> currentRounds = roundRepo.getRoundsByGameId(game.getId());
        
        assertEquals(rounds.size() + 1, currentRounds.size());

        roundRepo.deleteById(addedRound.getRoundId());        
    }

    /**
     * Test of getRoundsByGameId method, of class GuessTheNumberDaoDB.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetRoundsByGameId() throws Exception {
        roundRepo.save(round);
        
        List<Round> currentRounds = roundRepo.getRoundsByGameId(1);
        
        assertEquals(1, currentRounds.size());
    }
}
