package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class HashtagStorage {
    @Resource
    HashtagRepository hashtagRepo;

    public HashtagStorage(HashtagRepository hashtagRepo) {
        this.hashtagRepo = hashtagRepo;
    }


    public void addHashtag(Hashtag inHashtag) {
        hashtagRepo.save(inHashtag);
    }

    public Iterable<Hashtag> getAllHashtags() {
        return hashtagRepo.findAll();
    }

    public Hashtag getSingleHashtag(Long id) {
        Optional<Hashtag> retrievedHashtagOptional = hashtagRepo.findById(id);
        if (retrievedHashtagOptional.isPresent()) {
            Hashtag foundHashtag = retrievedHashtagOptional.get();
            return foundHashtag;
        }
        return null;
    }
}

   /* public Collection<Hashtag> getPostHashtags(Long id) {
        Collection<Hashtag> hashtags = new ArrayList<>();
        for (Hashtag i : getAllHashtags()) {
            if (i.getPosts().getId() == id)
                hashtags.add(i);
        }
        return hashtags;
    }
}*/
