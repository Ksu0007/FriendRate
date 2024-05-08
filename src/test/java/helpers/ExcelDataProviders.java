package helpers;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ExcelDataProviders {

    private int row;
    private int column;
    @DataProvider
    public Object[][] editProfileFields() throws Exception {
        String path = "src/test/resources/Fields.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTests();
    }


    @DataProvider
    public Object[][] errorsEditPage() throws Exception {
        String path = "src/test/resources/Fields.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTests("ErrorsEditPage");
    }
    @DataProvider
    public Object[][] columnDataProvider(int columnIndex) throws Exception {
        String path = "src/test/resources/Fields.xlsx";
        ExcelReader excelReader = new ExcelReader(path);

        String[] columnData = excelReader.getColumnDataFromSheet("Лист1", columnIndex);

        Object[][] data = new Object[columnData.length][1];
        for (int i = 0; i < columnData.length; i++) {
            data[i][0] = columnData[i];
        }

        return data;
    }
    @DataProvider
    public String getCellDataForTest() throws Exception {
        String path = "src/test/resources/Fields.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getCellDataForTest("Лист1", row, column);
    }

    @DataProvider
    public Object[][] profileFieldsLabelsEn() throws Exception {
        String path = "src/test/resources/Fields.xlsx";
        ExcelReader excelReader = new ExcelReader(path);

        // Предполагаем, что данные нужно получить из первой колонки (индекс 0) листа "ErrorsEditPage"
        int columnIndex = 1;
        String[] columnData = excelReader.getColumnDataFromSheet("Лист1", columnIndex);

        // Преобразуем массив строк в двумерный массив объектов
        Object[][] data = new Object[columnData.length][1];
        for (int i = 0; i < columnData.length; i++) {
            data[i][0] = columnData[i];
        }

        return data;
    }


}
