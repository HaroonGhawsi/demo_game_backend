package com.demo.game.service;

import org.springframework.stereotype.Component;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;
import com.demo.game.dto.GameResponseDto;

public class StoneGameEngine {

  public static GameResponseDto checkStoneOptionWinner(GameOption opponentChoice) {
    switch (opponentChoice) {
      case STONE:
        return GameResponseDto.from(opponentChoice, GameResult.DRAW);
      case SCISSOR:
        return GameResponseDto.from(opponentChoice, GameResult.WON);
      case PAPER:
        return GameResponseDto.from(opponentChoice, GameResult.LOST);
      default:
        throw new IllegalGameOptionException("Unknown Game Option");
    }
  }
}
