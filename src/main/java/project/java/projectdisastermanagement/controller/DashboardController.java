package project.java.projectdisastermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.java.projectdisastermanagement.model.Disaster;
import project.java.projectdisastermanagement.service.DisasterService;
import project.java.projectdisastermanagement.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DisasterService disasterService;

    @Autowired
    private UserService userService;


    @GetMapping("/totalReports")
    public Map<String, Object> getTotalReports() {
        long totalReports = disasterService.countTotalReports();
        long lastMonthReports = disasterService.countReportsLastMonth();
        double percentageChange = calculatePercentageChange(lastMonthReports, totalReports);

        Map<String, Object> response = new HashMap<>();
        response.put("totalReports", totalReports);
        response.put("change", percentageChange);
        return response;
    }


    @GetMapping("/totalUsers")
    public Map<String, Object> getTotalUsers() {
        long totalUsers = userService.countTotalUsers();
        long lastMonthUsers = userService.countUsersLastMonth();
        double percentageChange = calculatePercentageChange(lastMonthUsers, totalUsers);

        Map<String, Object> response = new HashMap<>();
        response.put("totalUsers", totalUsers);
        response.put("change", percentageChange);
        return response;
    }
    @GetMapping("/disastersByType")
    public ResponseEntity<List<Map<String, Object>>> getDisastersByType() {
        return ResponseEntity.ok(disasterService.fetchDisastersByType());
    }

    @GetMapping("/reportsByStatus")
    public ResponseEntity<List<Map<String, Object>>> getReportsByStatus() {
        return ResponseEntity.ok(disasterService.fetchReportsByStatus());
    }


    private double calculatePercentageChange(long previous, long current) {
        if (previous == 0) return current > 0 ? 100 : 0;
        return ((double) (current - previous) / previous) * 100;
    }

    @GetMapping("/latestReports")
    public List<Disaster> getLatestReports() {
        return disasterService.getLatestReports();
    }

}
