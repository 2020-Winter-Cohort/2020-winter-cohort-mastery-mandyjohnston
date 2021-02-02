package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorageJpaImpl;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class HashtagController {

    @Resource
    HashtagStorage hashtagStorage;
    PostStorage postStorage;

    public HashtagController(HashtagStorage hashtagStorage, PostStorage postStorage) {
        this.hashtagStorage = hashtagStorage;
        this.postStorage = postStorage;
    }

    @RequestMapping("/hashtags")
        public String displayAllHashtags(Model model) {
        model.addAttribute("hashtags", hashtagStorage.getAllHashtags());
        return "all-hashtags-template";
    }

    @RequestMapping("hashtag/{id}")
    public String displaySingleHashtag(Model model, @PathVariable Long id) {
        model.addAttribute("hashtag", hashtagStorage.getSingleHashtag(id));
        return "single-hashtag-template";
    }

    @PostMapping("/addHashtag")
    public String addUserHashtag(@RequestParam String id, @RequestParam String name) {
        Long postId = Long.parseLong(id);
        Post updatedPost = postStorage.retrievePostById(postId);
        Hashtag addedHashtag = new Hashtag(name);
        hashtagStorage.addHashtag(addedHashtag);
        updatedPost.addHashtag(addedHashtag);
        postStorage.save(updatedPost);
        return "redirect:/posts/" + postId;
    }

}
