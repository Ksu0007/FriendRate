package tests;

import core.BaseTest;
import helpers.TestValues;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.SignInPage;
import pages.StartPage;

public class MainPageTest extends BaseTest {
    @BeforeMethod
    public void login() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.authorization(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD);
    }

    @Test
    public void checkLogo(){
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.logoIsDisplayed());
    }

    @Test
    public void checkImage() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.imageIsDisplayed());
    }

    @Test
    public void checkDescription() {
        MainPage mainPage = new MainPage();
        Assert.assertEquals(mainPage.getPageDescrition(), TestValues.MAINPAGE_DESC);
    }

    @Test
    public void checkGenderLabel() {
        MainPage mainPage = new MainPage();
        Assert.assertEquals(mainPage.getGenderLabelText(), "Gender");
    }

    @Test
    public void checkGenderDropdown() {
        MainPage mainPage = new MainPage();
        mainPage.chooseGender("Female");
        Assert.assertEquals(mainPage.getChosenGender(), "Female");
    }

    @Test
    public void checkLanguageDropdown() {
        MainPage mainPage = new MainPage();
        mainPage.chooseLanguage("ENG");
        Assert.assertEquals(mainPage.getChosenLang(), "ENG");
    }

    @Test
    public void checkAgeRangeSetup() {
        MainPage mainPage = new MainPage();
        mainPage.setSliderRange(15, 25);
        Assert.assertEquals(mainPage.getLeftSliderValue(), "15");
        Assert.assertEquals(mainPage.getRightSliderVaue(), "25");
    }

}
