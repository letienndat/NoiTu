package com.pracspringboot.noi_tu.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.pracspringboot.noi_tu.entity.Dictionary;

@Configuration
public class DictionaryConfig {
  private final String firstDir = System.getProperty("user.dir");

  @Autowired
  private Gson gson;

  @Bean("dataList")
  public List<Dictionary> createDictionary() {
    List<Dictionary> dicList = new LinkedList<>();

    try (FileInputStream fis = new FileInputStream(firstDir + "/src/main/resources/dictionary/words.txt");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr)
    ) {
      while (br.ready()) {
        Dictionary dictionary = gson.fromJson(br.readLine(), Dictionary.class);
        dictionary.setText(dictionary.getText().toLowerCase().replaceAll("-", " "));
        if (dictionary.getText().split(" ").length != 2) continue;
        dicList.add(dictionary);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return dicList;
  }

  @Bean("dataNull")
  public List<Dictionary> createDic() {
    return new LinkedList<>();
  }
}
