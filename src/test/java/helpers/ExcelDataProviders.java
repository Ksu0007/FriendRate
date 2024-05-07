package helpers;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ExcelDataProviders {

    private int row;
    private int element;
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
    public String getCellDataForTest() throws Exception {
        String path = "src/test/resources/Fields.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getDataToCompare(row, element);
    }


}
