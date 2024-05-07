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

public class EditProfileUITest extends BaseTest {

    @BeforeMethod
    public void login() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.authorization(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD);
    }

    @Test
    public void testENLabels() throws Exception {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        ExcelReader excelReader = new ExcelReader("src/test/resources/Fields.xlsx");
        Assert.assertEquals(editProfilePage.getEmailLabel(),
                excelReader.getCellDataForTest("Лист1", 2, 1));
    }
}
