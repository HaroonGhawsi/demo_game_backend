package com.demo.game.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.game.dao.GameOption;
import com.demo.game.dao.GameResult;
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
  void testProcessGameResult() {

    //action
    GameResult gameResult = service.processGameResult(GameOption.STONE, GameOption.PAPER);

    //Assert
    assertEquals(gameResult, GameResult.WON);
  }

  @Test
  void testGenerateOptionForSystem() {

    //when
    doReturn(GameOption.STONE).when(repository).generateOptionForSystem();

    //action
    GameOption result = service.getOpponentChoice();

    //assert
    assertNotNull(result);
  }
}
