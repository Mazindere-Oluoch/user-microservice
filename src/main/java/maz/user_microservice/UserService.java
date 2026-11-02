package maz.user_microservice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);

    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createNewUser(User newUser) {
        newUser.setPassword(newUser.getPassword());
        return userRepository.save(newUser);
    }

    public User updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getId()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        BeanUtils.copyProperties(updatedUser, user); //todo check this also
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
