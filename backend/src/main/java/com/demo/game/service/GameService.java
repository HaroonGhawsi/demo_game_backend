package com.demo.game.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;
import com.demo.game.dto.GameResponseDto;
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

  public GameResponseDto processGameResult(GameOption userOption) {

    LOGGER.info("Processing the final Result of the Game...");

    if (userOption == null) {
      throw new IllegalGameOptionException("User Choice should not be null");
    }

    GameOption opponentChoice = this.getOpponentChoice();

    if (opponentChoice == null) {
      throw new IllegalGameOptionException("Opponent Choice should not be null");
    }

    if (userOption == GameOption.STONE && opponentChoice == GameOption.STONE) {
      return GameResponseDto.from(opponentChoice, GameResult.DRAW);
    }

    if (userOption == GameOption.STONE && opponentChoice == GameOption.SCISSOR) {
      return GameResponseDto.from(opponentChoice, GameResult.WON);
    }

    if (userOption == GameOption.STONE && opponentChoice == GameOption.PAPER) {
      return GameResponseDto.from(opponentChoice, GameResult.LOST);
    }

    if (userOption == GameOption.SCISSOR && opponentChoice == GameOption.STONE) {
      return GameResponseDto.from(opponentChoice, GameResult.LOST);
    }

    if (userOption == GameOption.SCISSOR && opponentChoice == GameOption.SCISSOR) {
      return GameResponseDto.from(opponentChoice, GameResult.DRAW);
    }

    if (userOption == GameOption.SCISSOR && opponentChoice == GameOption.PAPER) {
      return GameResponseDto.from(opponentChoice, GameResult.WON);
    }

    if (userOption == GameOption.PAPER && opponentChoice == GameOption.STONE) {
      return GameResponseDto.from(opponentChoice, GameResult.WON);
    }

    if (userOption == GameOption.PAPER && opponentChoice == GameOption.SCISSOR) {
      return GameResponseDto.from(opponentChoice, GameResult.LOST);
    }

    if (userOption == GameOption.PAPER && opponentChoice == GameOption.PAPER) {
      return GameResponseDto.from(opponentChoice, GameResult.DRAW);
    }

    return GameResponseDto.from(opponentChoice, GameResult.WON);
  }

  protected GameOption getOpponentChoice() {
    LOGGER.info("System started selecting Game Options");
    return repository.generateOptionForSystem();
  }
}
