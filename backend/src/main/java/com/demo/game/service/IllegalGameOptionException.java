package com.demo.game.service;

public class IllegalGameOptionException extends RuntimeException {

  IllegalGameOptionException(String message) {
    super(message);
  }
}
