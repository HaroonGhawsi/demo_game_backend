package com.demo.game.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.demo.game.dao.GameOption;

@Repository
public class GameRepository {

  public List<GameOption> findAll() {
    return Arrays.asList(GameOption.STONE, GameOption.PAPER, GameOption.SCISSOR);
  }

  public GameOption generateOptionForSystem() {
    List<GameOption> gameOptionValues = List.of(GameOption.STONE, GameOption.PAPER, GameOption.SCISSOR);
    return gameOptionValues.get(new Random().nextInt(gameOptionValues.size()));
  }
}
