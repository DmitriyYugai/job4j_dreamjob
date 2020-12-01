package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job"));
        store.save(new Post(0, "Java Job"));
        store.save(new Post(2, "Java Work"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        System.out.println(store.findPostById(2));
        System.out.println("----------------------------------------");
        store.save(new Candidate(0, "Java Candidate"));
        store.save(new Candidate(0, "Java Candidate"));
        store.save(new Candidate(2, "Java person"));
        for (Candidate can : store.findAllCandidates()) {
            System.out.println(can.getId() + " " + can.getName());
        }
        System.out.println(store.findCandidateById(2));
    }
}
