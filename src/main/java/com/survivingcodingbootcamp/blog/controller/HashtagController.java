package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class HashtagController {

    @Resource
    HashtagStorage hashtagStorage;


    @RequestMapping("/hashtags")
        public String displayAllHashtags(Model model) {
        model.addAttribute("hashtags", hashtagStorage.getAllHashtags());
        return "all-hashtags-template";
    }

}
