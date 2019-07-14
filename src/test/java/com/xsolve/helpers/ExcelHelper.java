package com.xsolve.helpers;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelHelper {

    public static Object[][] readExcelFile(File file) throws IOException {

        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNumber = sheet.getLastRowNum();
        int colNumber = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowNumber][colNumber];

        for (int i = 0; i < rowNumber; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                /*if (row.getCell(j).getCellType().equals(CellType.NUMERIC)) {
                    //System.out.println(row.getCell(j).getNumericCellValue());
                    data[i][j] = row.getCell(j).getNumericCellValue();
                } else {*/
                    data[i][j] = row.getCell(j).getStringCellValue();
                //}
                //System.out.println(data[i][j]);
            }
        }
        return data;
    }
}
