package project.java.projectdisastermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.java.projectdisastermanagement.model.Disaster;
import project.java.projectdisastermanagement.repository.DisasterRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DisasterService {

    @Autowired
    private DisasterRepository disasterRepository;

    public Disaster saveDisaster(Disaster disaster) {
        return disasterRepository.save(disaster);
    }

    public List<Disaster> getAllDisasters() {
        return disasterRepository.findAll();
    }

    public Disaster getDisasterById(Long id) {
        return disasterRepository.findById(id).orElse(null);
    }


    public void deleteDisaster(Long id) {
        disasterRepository.deleteById(id);
    }

    public long countTotalReports() {
        return disasterRepository.count();
    }

    public long countReportsLastMonth() {
        return disasterRepository.countReportsLastMonth();
    }

    public List<Map<String, Object>> fetchDisastersByType() {
        List<Object[]> data = disasterRepository.countByDisasterType();
        return data.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("type", obj[0]);
            map.put("count", obj[1]);
            return map;
        }).collect(Collectors.toList());
    }

    public List<Map<String, Object>> fetchReportsByStatus() {
        List<Object[]> data = disasterRepository.countByStatus();
        return data.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("status", obj[0]);
            map.put("count", obj[1]);
            return map;
        }).collect(Collectors.toList());
    }

    public List<Disaster> getLatestReports() {
        return disasterRepository.findTop5ByOrderByCreatedAtDesc();
    }

    public List<Disaster> getDisastersByUsername(String username) {
        return disasterRepository.findByUsername(username);
    }

}

