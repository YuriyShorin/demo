package ru.tbank.seminar1.solid.srp;

import java.util.ArrayList;
import java.util.List;

/** Зона персистентности, вынесена в отдельный класс. */
public class UserRepository {

    private final List<User> storage = new ArrayList<>();

    public void save(User user) {
        storage.add(user);
    }

    public List<User> findAll() {
        return List.copyOf(storage);
    }
}