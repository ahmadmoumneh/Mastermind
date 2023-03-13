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
import java.util.Objects;

/**
 *
 * @author ahmad
 */
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_id;
    
    @Column(nullable = false, columnDefinition = "char(4)")
    private String answer;
    
    @Column(nullable = false, columnDefinition = "boolean")
    private boolean inProgress;
    
    public Game() {}
    
    public Game(boolean inProgress) {
        this.inProgress = inProgress;
    }
    
    public int getId() {
        return game_id;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setId(int id) {
        this.game_id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.game_id;
        hash = 79 * hash + Objects.hashCode(this.answer);
        hash = 79 * hash + (this.inProgress ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.game_id != other.game_id) {
            return false;
        }
        if (this.inProgress != other.inProgress) {
            return false;
        }
        return Objects.equals(this.answer, other.answer);
    }
}
