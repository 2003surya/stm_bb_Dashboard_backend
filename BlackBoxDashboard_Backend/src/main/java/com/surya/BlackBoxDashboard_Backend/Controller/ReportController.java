package com.surya.BlackBoxDashboard_Backend.Controller;

import com.surya.BlackBoxDashboard_Backend.Model.data_bb;
import com.surya.BlackBoxDashboard_Backend.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/Report")
public class ReportController{

    @Autowired
    ReportService reportService;

    @GetMapping("/TodayReport")
    public ResponseEntity<List<data_bb>> getTodayReport(){
        return reportService.getTodayReport();
    }

    @GetMapping("/SpecificEntry")
    public ResponseEntity<List<data_bb>> getSpecificDateEntry(@RequestParam LocalDate date){
        return reportService.getSpecificDateEntry(date);
    }

    @GetMapping("/DownloadTodayReport")
    public ResponseEntity<byte[]> downloadTodayReport(){
        return reportService.downloadTodayReport();
    }
}
