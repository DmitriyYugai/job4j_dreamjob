package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Optional;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    void save(Candidate candidate);

    void save(Photo photo, int id);

    void save(User user);

    Post findPostById(int id);

    Candidate findCandidateById(int id);

    Photo findPhotoById(int id);

    Optional<User> findUserByEmail(String email);
}
