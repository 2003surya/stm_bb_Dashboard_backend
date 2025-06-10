package com.surya.BlackBoxDashboard_Backend.Service;

import com.surya.BlackBoxDashboard_Backend.Model.data_bb;
import com.surya.BlackBoxDashboard_Backend.Repository.data_bb_repository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService{

    @Autowired
    data_bb_repository dataBbRepository;


    public ResponseEntity<List<data_bb>> getTodayReport(){
        try{
            List<data_bb> todayList = dataBbRepository.findByDate(LocalDate.now());
            return ResponseEntity.ok(todayList);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public ResponseEntity<byte[]> downloadTodayReport() {
        try {
            List<data_bb> todayList = dataBbRepository.findByDate(LocalDate.now());

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Today Report");

            // Header row
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Gx", "Gy", "Gz", "Latitude", "Longitude", "Timestamp"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Data rows
            int rowNum = 1;
            for (data_bb item : todayList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(item.getGx());
                row.createCell(1).setCellValue(item.getGy());
                row.createCell(2).setCellValue(item.getGz());
                row.createCell(3).setCellValue(item.getLat() != null ? item.getLat() : "Null");
                row.createCell(4).setCellValue(item.getLon() != null ? item.getLon() : "Null");
                row.createCell(5).setCellValue(item.getTimestamp().toString());
            }

            // Resize columns
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            byte[] excelBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"TodayReport.xlsx\"");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<List<data_bb>> getSpecificDateEntry(LocalDate date) {
        try{
           return ResponseEntity.ok(dataBbRepository.findByDate(date));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}