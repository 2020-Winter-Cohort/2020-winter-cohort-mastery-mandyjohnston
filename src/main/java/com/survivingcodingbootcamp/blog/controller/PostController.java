package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.TopicStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostStorage postStorage;
    private HashtagStorage hashtagStorage;
    private TopicStorage topicStorage;

    public PostController(PostStorage postStorage, HashtagStorage hashtagStorage, TopicStorage topicStorage) {
           this.postStorage = postStorage;
           this.hashtagStorage = hashtagStorage;
           this.topicStorage = topicStorage;
      }

@RequestMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postStorage.retrievePostById(id));
        return "single-post-template";
    }


    @PostMapping("/newPost")
    public String updatePosts(@RequestParam String title, @RequestParam String author, @RequestParam String topic, @RequestParam String content, @RequestParam String hashtag1, @RequestParam String hashtag2) {

        Long topicId = Long.parseLong(topic);

        Hashtag addedTag1 = new Hashtag(hashtag1);
        Hashtag addedTag2 = new Hashtag(hashtag2);

        /*for(Hashtag i: hashtags){
            if(i.getName().equals(hashtag1))
                addedTag1 = i;
            if(i.getName().equals(hashtag2))
                addedTag2 = i;
        }
        if(addedTag1 == null && !hashtag1.equals("")) {
            addedTag1 = new Hashtag(hashtag1);
            hashtagStorage.addHashtag(addedTag1);
        }
        if(addedTag2 == null && !hashtag1.equals("")) {
            addedTag2 = new Hashtag(hashtag2);
            hashtagStorage.addHashtag(addedTag2);
        }*/

        Post addedPost = new Post(title, author, topicStorage.retrieveSingleTopic(topicId), content, addedTag1, addedTag2);
        hashtagStorage.addHashtag(addedTag1);
        hashtagStorage.addHashtag(addedTag2);
        postStorage.save(addedPost);
        return "redirect:/posts/" + addedPost.getId();
    }

}
