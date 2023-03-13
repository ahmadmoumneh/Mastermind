/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.am.mastermind.service;

import com.am.mastermind.dto.Game;
import com.am.mastermind.dto.Round;
import java.util.List;

/**
 *
 * @author ahmad
 */
public interface MastermindService {
    Game start(Game game) throws Exception;
    
    Round addRound(Round round) throws Exception;
    
    List<Game> getAllGames();
    
    Game getGameById(int id) throws Exception;
    
    List<Round> getRoundsByGameId(int gameId);
}
