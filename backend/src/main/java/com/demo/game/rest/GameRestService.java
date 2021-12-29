package com.demo.game.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.game.dao.GameOption;
import com.demo.game.dto.GameResponseDto;
import com.demo.game.service.GameService;
import com.demo.game.service.IllegalGameOptionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameRestService {

  @Autowired
  private GameService gameService;

  GameRestService() {
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<GameOption> getOptions() {
    return gameService.findAll();
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ExceptionHandler(IllegalGameOptionException.class)
  public ResponseEntity<GameResponseDto> processUserOption(@RequestBody GameOption userOption) {
    return new ResponseEntity<>(gameService.processGameResult(userOption), HttpStatus.CREATED);
  }


}
