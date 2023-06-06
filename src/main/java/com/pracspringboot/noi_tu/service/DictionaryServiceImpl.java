package com.pracspringboot.noi_tu.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pracspringboot.noi_tu.entity.Dictionary;
import com.pracspringboot.noi_tu.exception.NotFoundDictionaryException;

import lombok.Setter;

@Service
public class DictionaryServiceImpl implements DictionaryService {
  @Autowired
  @Qualifier("dataList")
  private List<Dictionary> dicList;

  @Autowired
  @Qualifier("dataNull")
  @Setter
  private List<Dictionary> dicListSelected;

  @Setter
  private String checkStr;

  @Override
  public List<Dictionary> findAll() {
    return dicList;
  }

  @Override
  public Dictionary repTextDictionary(String src) {
    if (src == null) {
      Random generator = new Random();
      Object[] values = findAll().toArray();
      Dictionary randomValue = (Dictionary) values[generator.nextInt(values.length)];

      dicListSelected.add(randomValue);
      checkStr = randomValue.getText();

      return randomValue;
    } else if (src.equals("") || src.split(" ").length != 2) {
      if (dicListSelected.contains(new Dictionary(src))) {
        return null;
      }
      return null;
    } else {
      if (checkStr.split(" ").length == 2) {
        if (checkStr.split(" ")[0].equals(src.split(" ")[1]) &&
            checkStr.split(" ")[1].equals(src.split(" ")[0])) {
          return null;
        }
      }

      List<Dictionary> listDic = findAll();

      if (listDic.contains(new Dictionary(src))) {
        dicListSelected.add(new Dictionary(src));
        for (Dictionary i : listDic) {
          if (dicListSelected.contains(i)) {
            continue;
          }
          if (i.getText().split(" ")[0].equals(src.split(" ")[1]) &&
              i.getText().split(" ")[1].equals(src.split(" ")[0])) {
            continue;
          }
          if (i.getText().split(" ")[0].equals(src.split(" ")[1])) {
            dicListSelected.add(i);
            checkStr = i.getText();
            return i;
          }
        }
      } else {
        return null;
      }

      throw new NotFoundDictionaryException();
    }
  }
}
