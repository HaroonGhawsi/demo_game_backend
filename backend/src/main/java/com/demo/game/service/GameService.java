package com.demo.game.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;
import com.demo.game.repository.GameRepository;

@Service
public class GameService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

  @Autowired
  private GameRepository repository;

  public List<GameOption> findAll() {
    LOGGER.info("Received Request for getting Game Options...");
    return repository.findAll();
  }

  public GameResult processGameResult(GameOption userOption, GameOption systemOption) {

    LOGGER.info("Processing the final Result of the Game...");

    if (userOption == null || systemOption == null) {
      throw new IllegalArgumentException();
    }
    if (userOption.equals(systemOption)) {
      return GameResult.LOST;
    }
    return GameResult.WON;
  }


  public GameOption getOpponentChoice() {
    LOGGER.info("System started selecting Game Options");
    return repository.generateOptionForSystem();
  }
}
