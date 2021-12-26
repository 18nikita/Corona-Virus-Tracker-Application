package com.example.coronavirustracker.controller;

import com.example.coronavirustracker.models.LocationStats;
import com.example.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrivious()).sum();
        model.addAttribute("locationStats",allStats );
        model.addAttribute("totalCases",totalCases );
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }

}
