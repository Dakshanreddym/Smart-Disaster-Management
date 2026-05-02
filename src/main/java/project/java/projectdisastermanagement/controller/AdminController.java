package project.java.projectdisastermanagement.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.java.projectdisastermanagement.model.Admin;
import project.java.projectdisastermanagement.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

        String username = (String) session.getAttribute("admin");

        if (username == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";
        }
        String adminName = adminService.getAdminNameByUsername(username);
        model.addAttribute("adminName", adminName);
        return "admin/dashboard";
    }

    @RequestMapping("/register")
    public String showRegisterPage(HttpSession session, Model model, RedirectAttributes redirectAttributes){

        String username = (String) session.getAttribute("admin");

        if (username == null) {

            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";
        }
        String adminName = adminService.getAdminNameByUsername(username);
        model.addAttribute("adminName", adminName);
        model.addAttribute("admin", new Admin());
        return "admin/register";
    }

    @PostMapping("/save")
    public String saveAdmin(@Valid Admin admin, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "admin/register";
        }

        try {
            adminService.addAdmin(admin);
            redirectAttributes.addFlashAttribute("successMessage", "Admin Registered Successfully!");
            model.addAttribute("adminList", adminService.getAllAdmins());
            return "admin/admin-management";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "admin/register";
        }

    }

    @GetMapping("/admins")
    public String showAdminManagementPage(HttpSession session, Model model, RedirectAttributes redirectAttributes){

        String username = (String) session.getAttribute("admin");

        if (username == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";  
        }
        String adminName = adminService.getAdminNameByUsername(username);
        model.addAttribute("adminName", adminName);
        model.addAttribute("adminList", adminService.getAllAdmins());
        return "admin/admin-management";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  
        return "redirect:/user/login";
    }
}
