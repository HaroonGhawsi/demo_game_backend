package com.demo.game.service;

import org.springframework.stereotype.Component;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;
import com.demo.game.dto.GameResponseDto;

public class PaperGameEngine {

  public static GameResponseDto checkPaperGameOptionWinner(GameOption opponentOption) {
    switch (opponentOption) {
      case PAPER:
        return GameResponseDto.from(opponentOption, GameResult.DRAW);
      case STONE:
        return GameResponseDto.from(opponentOption, GameResult.WON);
      case SCISSOR:
        return GameResponseDto.from(opponentOption, GameResult.LOST);
      default:
        throw new IllegalGameOptionException("Unknown Game Option");
    }
  }
}
