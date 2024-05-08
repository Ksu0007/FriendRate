package tests;

import core.BaseTest;
import helpers.ExcelDataProviders;
import helpers.ExcelReader;
import helpers.TestValues;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EditProfilePage;
import pages.MainPage;
import pages.SignInPage;
import pages.StartPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EditProfileUITest extends BaseTest {

    @BeforeMethod
    public void login() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.authorization(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD);
    }

    @Test
    public void testENLabelsEmail() throws Exception {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        ExcelReader excelReader = new ExcelReader("src/test/resources/Fields.xlsx");
        Assert.assertEquals(editProfilePage.getEmailLabel(),
                excelReader.getCellDataForTest("Лист1", 2, 1));
    }

    @Test(dataProvider = "profileFieldsLabelsEn", dataProviderClass = ExcelDataProviders.class)
    public void testEnLabels(String expectedLabel) throws IOException, InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        Thread.sleep(5000);

        String[] actualLabels = editProfilePage.getFieldsText();
        boolean labelFound = false;
        for (String actualLabel : actualLabels) {
            if (actualLabel.equals(expectedLabel)) {
                labelFound = true;
                break;
            }
        }
        Assert.assertTrue(labelFound, "Метка '" + expectedLabel + "' не найдена на веб-странице");

    }

    @Test
    public void testEnLabels1() throws InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        Thread.sleep(5000);
        String[] fields = editProfilePage.getFieldsText();
        System.out.println("Fields from page:");
        for (String field : fields) {
            System.out.println(field);
        }

    }
}
