package com.pracspringboot.noi_tu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pracspringboot.noi_tu.entity.Dictionary;

@Service
public interface DictionaryService {
  public List<Dictionary> findAll();

  public Dictionary repTextDictionary(String src);
}
