package com.pracspringboot.noi_tu.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pracspringboot.noi_tu.exception.NotFoundDictionaryException;

@ControllerAdvice
public class DictionaryExceptionController {
  @ExceptionHandler(value = NotFoundDictionaryException.class)
  public String exception(NotFoundDictionaryException e) {
    
    DictionaryController.setComplete(true);

    return "redirect:/api/noi-tu";
  }
}
