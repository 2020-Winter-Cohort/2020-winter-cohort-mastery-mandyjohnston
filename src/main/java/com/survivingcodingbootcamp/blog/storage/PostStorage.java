package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.repository.PostRepository;

import javax.annotation.Resource;
import java.util.Optional;

public interface PostStorage {

    Iterable<Post> retrieveAllPosts();

    Post retrievePostById(long l);

    void save(Post postToAdd);

    /*void addHashtag(Hashtag inHashtag);*/
}
