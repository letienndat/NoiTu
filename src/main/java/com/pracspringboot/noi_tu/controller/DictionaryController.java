package com.pracspringboot.noi_tu.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pracspringboot.noi_tu.entity.Dictionary;
import com.pracspringboot.noi_tu.service.DictionaryServiceImpl;

import lombok.Setter;

@Controller
@RequestMapping(path = "/api/noi-tu")
public class DictionaryController {
  @Autowired
  private DictionaryServiceImpl dictionaryServiceImpl;

  private String textDicClient = null;

  private boolean active = true;

  @Setter
  private static boolean complete = false;

  private String input = "";

  @GetMapping(path = {"/", ""})
  public String index(ModelMap modelMap) {
    if (textDicClient == null) {
      if (!active) {
        modelMap.addAttribute("dictionary", new Dictionary(textDicClient));
      } else {
        String str = dictionaryServiceImpl.repTextDictionary(textDicClient).getText();
        modelMap.addAttribute("dictionary", new Dictionary(str));
        textDicClient = str;
        input = textDicClient.split(" ")[1] + " ";
      }
    } else {
      modelMap.addAttribute("dictionary", new Dictionary(textDicClient));
    }

    modelMap.addAttribute("inp", input);

    modelMap.addAttribute("active", active);

    modelMap.addAttribute("complete", complete);

    return "index";
  }

  @PostMapping(path = {"/", ""})
  public String index(@RequestParam(name = "text") String text, ModelMap modelMap) {
    text = text.toLowerCase().trim();
    input = text;
    Dictionary dictionaryRes = dictionaryServiceImpl.repTextDictionary(text);

    if (dictionaryRes == null) {
      active = false;
      
      return "redirect:/api/noi-tu";
    }

    textDicClient = dictionaryRes.getText();
    input = textDicClient.split(" ")[1] + " ";
    active = true;

    return "redirect:/api/noi-tu";
  }

  @GetMapping(path = "/reload-data")
  public String reloadData() {
    input = "";
    textDicClient = null;
    active = true;
    complete = false;
    dictionaryServiceImpl.setCheckStr("");
    dictionaryServiceImpl.setDicListSelected(new LinkedList<>());

    return "redirect:/api/noi-tu";
  }
}
