package ro.info.iasi.fiipractic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ro.info.iasi.fiipractic.model.User;
import ro.info.iasi.fiipractic.repository.UserDAO;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void registerUser(User user) {
        userRepository.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }
}
