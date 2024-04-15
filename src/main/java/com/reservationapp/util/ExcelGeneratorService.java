package com.reservationapp.util;

import com.reservationapp.Entity.Passenger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelGeneratorService {

    public byte[] generateExcel(List<Passenger> passengers) throws IOException {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Passenger Data");

            Row headerRow = sheet.createRow(0);
            String[] columns ={"ID","FIRST NAME","LAST NAME","EMAIL","MOBILE","BUS ID","ROUTE ID"};
            for(int i=0; i< columns.length;i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }
            // fill data rows
            int rowNum = 1;
            for(Passenger passenger : passengers){
                Row row  = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(passenger.getId());
                 row.createCell(1).setCellValue(passenger.getFirstName());
                 row.createCell(2).setCellValue(passenger.getLastName());
                 row.createCell(3).setCellValue(passenger.getEmail());
                 row.createCell(4).setCellValue(passenger.getMobile());
                 row.createCell(5).setCellValue(passenger.getBusId());
                 row.createCell(6).setCellValue(passenger.getRouteId());
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook .write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
