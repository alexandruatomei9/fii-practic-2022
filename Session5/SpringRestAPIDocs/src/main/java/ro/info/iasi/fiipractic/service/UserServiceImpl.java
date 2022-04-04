package ro.info.iasi.fiipractic.service;

import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.info.iasi.fiipractic.model.User;
import ro.info.iasi.fiipractic.repository.UserDAO;

import java.util.List;
import java.util.Map;

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
    public void updateUser(Integer id, User user) {
        userRepository.updateUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), id);
    }

    @Override
    public void patchUser(Integer id, Map<String, String> partialUser) {
        User user = userRepository.getUserById(id);

        patchUser(partialUser, user);

        userRepository.updateUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), id);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    private void patchUser(Map<String, String> partialUser, User user) {
        String firstName = partialUser.get("firstName");
        String lastName = partialUser.get("lastName");
        String email = partialUser.get("email");
        String password = partialUser.get("password");
        if(!StringUtils.isNullOrEmpty(firstName)) {
            user.setFirstName(firstName);
        }
        if(!StringUtils.isNullOrEmpty(lastName)) {
            user.setLastName(lastName);
        }
        if(!StringUtils.isNullOrEmpty(email)) {
            user.setEmail(email);
        }
        if(!StringUtils.isNullOrEmpty(password)) {
            user.setPassword(password);
        }
    }

}
