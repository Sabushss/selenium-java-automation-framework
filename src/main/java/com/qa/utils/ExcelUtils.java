package com.qa.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ExcelUtils - Utility for Data Driven Testing using Apache POI
 * Reads test data from Excel files
 * Author: Subash A | QA Automation Engineer
 */
public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void setExcelFile(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found in " + filePath);
        }
        fis.close();
    }

    public static String getCellData(int rowNum, int colNum) {
        try {
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            if (cell == null) return "";

            switch (cell.getCellType()) {
                case STRING:  return cell.getStringCellValue().trim();
                case NUMERIC: return String.valueOf((int) cell.getNumericCellValue());
                case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
                default:      return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    public static int getColumnCount(int rowNum) {
        return sheet.getRow(rowNum).getLastCellNum();
    }

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
        setExcelFile(filePath, sheetName);
        int rows = getRowCount();
        int cols = getColumnCount(1);
        Object[][] data = new Object[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }
}
