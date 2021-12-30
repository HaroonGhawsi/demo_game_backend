package com.demo.game.service;

import org.springframework.stereotype.Component;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;
import com.demo.game.dto.GameResponseDto;

public class ScissorGameEngine {

  public static GameResponseDto checkScissorGameOptionWinner(GameOption opponentChoice) {

    switch (opponentChoice) {
      case SCISSOR:
        return GameResponseDto.from(opponentChoice, GameResult.DRAW);
      case STONE:
        return GameResponseDto.from(opponentChoice, GameResult.LOST);
      case PAPER:
        return GameResponseDto.from(opponentChoice, GameResult.WON);
      default:
        throw new IllegalGameOptionException("Unknow Game Option");
    }
  }
}
