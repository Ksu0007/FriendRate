package helpers;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReader {
    private  String fieldsExcelPath;
    private XSSFSheet sheet;
    private XSSFWorkbook book;

    public ExcelReader(String fieldsExcelPath) throws IOException {
        this.fieldsExcelPath = fieldsExcelPath;
        File file = new File(fieldsExcelPath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet("Лист1");
        } catch (IOException e) {
            throw new IOException("Wrong format");
        }
    }

    public ExcelReader(String fieldsExcelPath,  String sheetName) throws IOException {
        this.fieldsExcelPath = fieldsExcelPath;
        File file = new File(fieldsExcelPath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet(sheetName);
        } catch (IOException e) {
            throw new IOException("Wrong format");
        }
    }

    private String cellToString(XSSFCell cell) throws Exception {
        Object result = null;
        CellType type = cell.getCellType();
        switch (type) {
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case  STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "";
                break;

            default: throw new Exception("Error to read");
        }
        return result.toString();
    }

    private int fileCountColumn() {
        return sheet.getRow(0).getLastCellNum();
    }

    private int fileCountRows() {
        return sheet.getLastRowNum() + 1;
    }

    public String[][] getSheetDataForTests() throws Exception {
        File file = new File(fieldsExcelPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet("Лист1");
        int numberOfColumns = fileCountColumn();
        int numberOfRows = fileCountRows();
        String[][] data = new String[numberOfRows - 1][numberOfColumns];
        for (int i = 1; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i - 1][j] = value;
                if (value == null) {
                    System.out.println("Empty cells");
                }

            }
        }
        return data;
    }

    public String[][] getSheetDataForTests(String sheetName) throws Exception {
        File file = new File(fieldsExcelPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numberOfColumns = fileCountColumn();
        int numberOfRows = fileCountRows();
        String[][] data = new String[numberOfRows - 1][numberOfColumns];
        for (int i = 1; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i - 1][j] = value;
                if (value == null) {
                    System.out.println("Empty cells");
                }
            }
        }
        return data;
    }

    public String[] getColumnDataFromSheet(String sheetName, int columnIndex) throws Exception {
        File file = new File(fieldsExcelPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook book = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = book.getSheet(sheetName);

        int numberOfRows = sheet.getPhysicalNumberOfRows();
        String[] columnData = new String[numberOfRows - 1];

        for (int i = 0; i < numberOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(columnIndex);
            String value = cellToString(cell);
            columnData[i] = value;
            if (value == null) {
                System.out.println("Empty cell at row: " + (i+1) + ", column: " + (columnIndex+1));
            }
        }

        book.close();
        fileInputStream.close();

        return columnData;
    }


    public String getCellDataForTest(String sheetName, int rowNum, int columnNum) throws Exception {
        File file = new File(fieldsExcelPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numberOfColumns = fileCountColumn();
        int numberOfRows = fileCountRows();
        String value = "";

        for (int i = 1; i < numberOfRows; i++) {
            if (i == rowNum) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (j == columnNum) {
                        XSSFRow row = sheet.getRow(i);
                        XSSFCell cell = row.getCell(j);
                        value = cellToString(cell);
                    }
                }
            }
        }
        return value;
    }
}
