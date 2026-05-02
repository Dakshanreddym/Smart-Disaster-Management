package project.java.projectdisastermanagement.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.java.projectdisastermanagement.model.Disaster;
import project.java.projectdisastermanagement.model.Disaster.Status;
import project.java.projectdisastermanagement.service.AdminService;
import project.java.projectdisastermanagement.service.DisasterService;

import jakarta.validation.Valid;

import java.util.List;


@Controller
@RequestMapping("/disasters")
public class DisasterController {

    @Autowired
    private DisasterService disasterService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/report-incident-form")
    public String showReportFormPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        String username = (String) session.getAttribute("user");

        if (username == null) {
            model.addAttribute("userLoggedIn", false);

            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";
        }
        model.addAttribute("userLoggedIn", true);
        model.addAttribute("disaster", new Disaster());
        return "user/report-incident";
    }

    @PostMapping("/report")
    public String createDisaster(@Valid Disaster disaster, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "user/report-incident";
        }
        String username = (String) session.getAttribute("user");
        if (username != null) {
            disaster.setUsername(username);
        }
        disasterService.saveDisaster(disaster);
        redirectAttributes.addFlashAttribute("successMessage", "Disaster reported successfully!");
        return "redirect:/disasters/my-reports";
    }

    @GetMapping("/reports")
    public String showReportsPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        String username = (String) session.getAttribute("admin");

        if (username == null) {

            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";
        }
        String adminName = adminService.getAdminNameByUsername(username);
        model.addAttribute("adminName", adminName);
        model.addAttribute("disasterList", disasterService.getAllDisasters());
        System.out.println("passing admin reports");
        return "admin/reports";
    }

    @GetMapping("/{id}")
    public String getDisasterById(HttpSession session, @PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        String username = (String) session.getAttribute("admin");

        if (username == null) {

            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";
        }
        String adminName = adminService.getAdminNameByUsername(username);
        model.addAttribute("adminName", adminName);
        Disaster disaster = disasterService.getDisasterById(id);
        if (disaster != null) {
            model.addAttribute("disaster", disaster);
            return "admin/incident-detail";
        }

        return "admin/reports";
    }

    @PostMapping("/updateStatus")
    public String updateDisasterStatus(@RequestParam("id") Long id, @RequestParam("status") String status, RedirectAttributes redirectAttributes) {
        Disaster disaster = disasterService.getDisasterById(id);
        if (disaster != null) {
            try {
                Status statusEnum = Status.valueOf(status);
                disaster.setStatus(statusEnum);
                disasterService.saveDisaster(disaster);
                redirectAttributes.addFlashAttribute("successMessage", "Status updated successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status value: " + status);
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid status selected.");
                return "redirect:/disasters/reports";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Disaster not found.");
        }
        return "redirect:/disasters/reports";
    }

    @GetMapping("/my-reports")
    public String viewMyReports(HttpSession session, Model model) {
        String username = (String) session.getAttribute("user");
        model.addAttribute("userLoggedIn", username != null);
        List<Disaster> reports = disasterService.getDisastersByUsername(username);
        model.addAttribute("disasterList", reports);
        return "user/my-reports";
    }

    @GetMapping("/user/{id}")
    public String getDisasterByIdUser(HttpSession session, @PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        String username = (String) session.getAttribute("user");

        if (username == null) {

            redirectAttributes.addFlashAttribute("errorMessage", "You must log in to access this page.");
            return "redirect:/user/login";
        }
        model.addAttribute("userLoggedIn", username != null);
        Disaster disaster = disasterService.getDisasterById(id);
        if (disaster != null) {
            model.addAttribute("disaster", disaster);
            return "user/incident-detail";
        }
        return "user/my-reports";
    }

}