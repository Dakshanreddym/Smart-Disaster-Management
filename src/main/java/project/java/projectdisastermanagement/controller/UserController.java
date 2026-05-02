package project.java.projectdisastermanagement.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.java.projectdisastermanagement.model.User;
import project.java.projectdisastermanagement.service.AdminService;
import project.java.projectdisastermanagement.service.LoginService;
import project.java.projectdisastermanagement.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/register")
    public String showRegisterPage(HttpSession session, Model model){
        String username = (String) session.getAttribute("user");


        if (username != null) {
            model.addAttribute("userLoggedIn", true);
        } else {
            model.addAttribute("userLoggedIn", false);
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("userLoggedIn", false);
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "register";
        }

        try {
            userService.addUser(user);
            model.addAttribute("userLoggedIn", false );
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please log in.");
            return "redirect:/user/login";
        } catch (Exception e) {
            model.addAttribute("userLoggedIn", false);
            model.addAttribute("error", e.getMessage());
            return "register";
        }

    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("user");

        if (username != null) {
            model.addAttribute("userLoggedIn", true);
        } else {
            model.addAttribute("userLoggedIn", false);
        }
        return "login";
    }



    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        String role = loginService.login(username, password);

        if (role.equals("ADMIN")) {
            session.setAttribute("admin", username);
            redirectAttributes.addFlashAttribute("successMessage", "Login Successful!");
            return "redirect:/admin/dashboard";
        } else if (role.equals("USER")) {
            session.setAttribute("user", username);
            model.addAttribute("userLoggedIn", true);
            redirectAttributes.addFlashAttribute("successMessage", "Login Successful!");
            return "redirect:/disasters/report-incident-form";
        } else {
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("userLoggedIn", false);
            return "login";
        }
    }

    @GetMapping("/users")
    public String showUsersPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("admin");

        if (username == null) {

            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";
        }
        String adminName = adminService.getAdminNameByUsername(username);
        model.addAttribute("adminName", adminName);
        model.addAttribute("userList", userService.getAllUsers());
        return "admin/user-management";
    }



    @GetMapping("/index")
    public String showIndexPage(HttpSession session, Model model){
        String username = (String) session.getAttribute("user");

        if (username != null) {
            model.addAttribute("userLoggedIn", true);
        } else {
            model.addAttribute("userLoggedIn", false);
        }
        return "index";
    }

    @GetMapping("/incident-detail")
    public String showIncidentDetailPage(HttpSession session, Model model){
        String username = (String) session.getAttribute("user");
        model.addAttribute("userLoggedIn", username != null);
        return "user/incident-detail";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/index";
    }

}
