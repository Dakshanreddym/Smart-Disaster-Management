package project.java.projectdisastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.java.projectdisastermanagement.model.Admin;
import project.java.projectdisastermanagement.model.Disaster;
import project.java.projectdisastermanagement.repository.AdminRepository;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public Admin addAdmin(Admin admin) throws Exception{
        if(adminRepository.findByUsername(admin.getUsername()) != null){
            throw new Exception("User already exists");
        }
        if(adminRepository.findByEmail(admin.getEmail()) != null){
            throw new Exception("Email already exists");
        }
        return adminRepository.save(admin);
    }

    public Admin findByUsername(String username){
        return adminRepository.findByUsername(username);
    }

    public String getAdminNameByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null ? admin.getName() : null;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
