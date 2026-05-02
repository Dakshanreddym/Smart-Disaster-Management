package project.java.projectdisastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.java.projectdisastermanagement.model.Disaster;
import project.java.projectdisastermanagement.model.User;
import project.java.projectdisastermanagement.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user) throws Exception{
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new Exception("User already exists");
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new Exception("Email already exists");
        }
        return userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public long countTotalUsers() {
        return userRepository.count();
    }

    public long countUsersLastMonth() {
        return userRepository.countUsersLastMonth();
    }


}
