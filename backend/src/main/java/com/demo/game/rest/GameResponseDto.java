package com.demo.game.rest;

import java.io.Serializable;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;

public class GameResponseDto implements Serializable {

  private final GameOption option;
  private final GameResult result;

  public GameResponseDto(GameOption option, GameResult result) {
    this.option = option;
    this.result = result;
  }

  public static GameResponseDto from(GameOption systemOption, GameResult result) {
    return new GameResponseDto(systemOption, result);
  }

  public GameOption getOption() {
    return option;
  }

  public GameResult getResult() {
    return result;
  }
}
