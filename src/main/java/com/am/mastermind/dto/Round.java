/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.am.mastermind.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;
import com.am.mastermind.common.CommonValues;

/**
 *
 * @author ahmad
 */
@Entity
public class Round implements CommonValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roundId;
    
    @Column(nullable = false, columnDefinition = "char(4)")
    private String guess;
    
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime guessTime;
    
    @Column(nullable = false, columnDefinition = "char(7)")
    private String guessResult;
    
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    
    public int getRoundId() {
        return roundId;
    }

    public Game getGame() {
        return game;
    }

    public String getGuess() {
        return guess;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public void setGame(Game game_id) {
        this.game = game_id;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setGuessTime(LocalDateTime time) {
        this.guessTime = time;
    }
    
    public void setGuessTime(String time) {
        this.guessTime = LocalDateTime.parse(
                time, DATE_TIME_FORMATTER);
    }

    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.roundId;
        hash = 67 * hash + Objects.hashCode(this.guess);
        hash = 67 * hash + Objects.hashCode(this.guessTime);
        hash = 67 * hash + Objects.hashCode(this.guessResult);
        hash = 67 * hash + Objects.hashCode(this.game);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.guessResult, other.guessResult)) {
            return false;
        }
        return Objects.equals(this.guessTime, other.guessTime);
    }
}
