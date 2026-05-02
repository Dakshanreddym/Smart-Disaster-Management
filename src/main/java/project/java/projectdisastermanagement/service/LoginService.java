package project.java.projectdisastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.java.projectdisastermanagement.model.Admin;
import project.java.projectdisastermanagement.model.User;
import project.java.projectdisastermanagement.repository.AdminRepository;
import project.java.projectdisastermanagement.repository.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;


    public LoginService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public String login(String username, String password) {

        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            System.out.println("Admin found: " + admin.getUsername());
            if (password.equals(admin.getPassword())) {
                return "ADMIN";
            } else {
                System.out.println("Incorrect password for admin: " + username);
            }
        }

        User user = userRepository.findByUsername(username);
        if (user != null) {
            System.out.println("User found: " + user.getUsername());
            if (password.equals(user.getPassword())) {
                return "USER";
            } else {
                System.out.println("Incorrect password for user: " + username);
            }
        }

        System.out.println("Invalid username or password: " + username);
        return "INVALID";
    }

}
