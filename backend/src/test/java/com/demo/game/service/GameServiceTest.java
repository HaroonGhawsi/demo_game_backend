package com.demo.game.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;
import com.demo.game.dto.GameResponseDto;
import com.demo.game.repository.GameRepository;

class GameServiceTest {

  @InjectMocks
  private GameService service;

  @Mock
  private GameRepository repository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindAll() {

    //when
    doReturn(Arrays.asList(GameOption.STONE, GameOption.PAPER, GameOption.SCISSOR)).when(repository).findAll();

    //action
    List<GameOption> result = service.findAll();

    //assert
    assertThat(result.size(), equalTo(3));
    assertEquals(result, Arrays.asList(GameOption.STONE, GameOption.PAPER, GameOption.SCISSOR));

  }

  @Test
  void test_stone_draws_over_stone() {


    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.STONE);

    //action
    GameResponseDto result = service.processGameResult(GameOption.STONE);

    //Assert
    assertEquals(result.getOption(), GameOption.STONE);
    assertEquals(result.getResult(), GameResult.DRAW);
  }

  @Test
  void test_stone_wins_over_scissor() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.SCISSOR);

    //action
    GameResponseDto result = service.processGameResult(GameOption.STONE);

    //Assert
    assertEquals(result.getOption(), GameOption.SCISSOR);
    assertEquals(result.getResult(), GameResult.WON);
  }

  @Test
  void test_stone_loses_over_paper() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.PAPER);

    //action
    GameResponseDto result =  service.processGameResult(GameOption.STONE);

    //Assert
    assertEquals(result.getOption(), GameOption.PAPER);
    assertEquals(result.getResult(), GameResult.LOST);
  }

  @Test
  void test_scissor_loses_over_stone() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.STONE);

    //action
    GameResponseDto result = service.processGameResult(GameOption.SCISSOR);

    //Assert
    assertEquals(result.getOption(), GameOption.STONE);
    assertEquals(result.getResult(), GameResult.LOST);
  }

  @Test
  void test_scissor_draws_over_scissor() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.SCISSOR);

    //action
    GameResponseDto result = service.processGameResult(GameOption.SCISSOR);

    //Assert
    assertEquals(result.getOption(), GameOption.SCISSOR);
    assertEquals(result.getResult(), GameResult.DRAW);
  }

  @Test
  void test_scissor_wins_over_paper() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.PAPER);

    //action
    GameResponseDto result = service.processGameResult(GameOption.SCISSOR);

    //Assert
    assertEquals(result.getOption(), GameOption.PAPER);
    assertEquals(result.getResult(), GameResult.WON);
  }

  @Test
  void test_paper_loses_to_scissors() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.SCISSOR);

    //action
    GameResponseDto result = service.processGameResult(GameOption.PAPER);

    //Assert
    assertEquals(result.getOption(), GameOption.SCISSOR);
    assertEquals(result.getResult(), GameResult.LOST);
  }

  @Test
  void test_paper_draws_to_paper() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.PAPER);

    //action
    GameResponseDto result = service.processGameResult(GameOption.PAPER);

    //Assert
    assertEquals(result.getOption(), GameOption.PAPER);
    assertEquals(result.getResult(), GameResult.DRAW);
  }

  @Test
  void test_paper_wins_over_stone() {

    //arrange
    when(service.getOpponentChoice()).thenReturn(GameOption.STONE);

    //action
    GameResponseDto result = service.processGameResult(GameOption.PAPER);

    //Assert
    assertEquals(result.getOption(), GameOption.STONE);
    assertEquals(result.getResult(), GameResult.WON);
  }
}
