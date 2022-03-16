package ro.info.iasi.fiipractic.service;

import ro.info.iasi.fiipractic.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void registerUser(User user);

    User getUserById(String id);
}
