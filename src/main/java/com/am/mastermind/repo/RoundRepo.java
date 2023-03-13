/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.am.mastermind.repo;

import com.am.mastermind.dto.Game;
import com.am.mastermind.dto.Round;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ahmad
 */
@Repository
public interface RoundRepo extends JpaRepository<Round, Integer> {
    List<Round> getRoundsByGameId(int game_id);
}
