package tests;

import core.BaseTest;
import helpers.TestValues;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.SignInPage;
import pages.StartPage;

public class LoginTest extends BaseTest {
    @Test(priority = 1)
    public void testHeaderText() {
        SignInPage signInPage = new StartPage().signIn();
        Assert.assertEquals(signInPage.getHeaderText(), TestValues.HEADER_SIGNIN);
    }

    @Test(priority = 1)
    public void testPageDescription() {
        SignInPage signInPage = new StartPage().signIn();
        Assert.assertEquals(signInPage.getHeaderText(), TestValues.DESC_SIGNIN);
    }

    @Test(priority = 1)
    public void checkEmailLabel() {
        SignInPage signInPage = new StartPage().signIn();
        Assert.assertEquals(signInPage.getEmailLabel(), "Email/Username");
    }

    @Test(priority = 1)
    public void checkEmailPlaceholder() {
        SignInPage signInPage = new StartPage().signIn();
        Assert.assertEquals(signInPage.getEmailPlaceholder(), "Email/Username");
    }

    @Test(priority = 1)
    public void checkPasswordLabel() {
        SignInPage signInPage = new StartPage().signIn();
        Assert.assertEquals(signInPage.getPasswordLabel(), "Password");
    }

    @Test(priority = 1)
    public void checkPasswordPlaceholder() {
        SignInPage signInPage = new StartPage().signIn();
        Assert.assertEquals(signInPage.getPasswordPlaceholder(), "Enter your password");
    }

    @Test(priority = 1)
    public void checkContinueButtonDisabled() {
        SignInPage signInPage = new StartPage().signIn();
        Assert.assertTrue(signInPage.isContinueButtonDisabled());
    }

    @Test(priority = 2)
    public void checkEmptyEmailValidation() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.fillInEmail("");
        signInPage.fillInPassword("123");
        Assert.assertEquals(signInPage.getEmailErrorMsg(), "Required");
    }

    @Test(priority = 2)
    public void checkInvalidEmailValidation() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.fillInEmail("email");
        signInPage.fillInPassword("");
        Assert.assertEquals(signInPage.getEmailErrorMsg(), "Please enter a valid email address.");
    }
    @Test(priority = 2)
    public void checkEmailWithoutDomain() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.fillInEmail("email@");
        signInPage.fillInPassword("");
        Assert.assertEquals(signInPage.getEmailErrorMsg(), "Please enter a valid email address.");
    }

    @Test(enabled = false, priority = 2)
    public void checkEmailWithoutCom() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.fillInEmail("email@example");
        signInPage.fillInPassword("");
        Assert.assertEquals(signInPage.getEmailErrorMsg(), "Please enter a valid email address.",
                "Validation for the email domain extension is missing");
    }
    @Test(priority = 2)
    public void checkEmailWithoutCom2() {
        try {
            SignInPage signInPage = new StartPage().signIn();
            signInPage.fillInEmail("email@example");
            signInPage.fillInPassword("");
            Assert.assertEquals(signInPage.getEmailErrorMsg(), "Please enter a valid email address.",
                    "Validation for the email domain extension is missing");
        } catch (NoSuchElementException e) {
            System.out.println("Validation for the email domain extension is missing");
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void checkPassword7chars() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.fillInPassword("Qwe$123");
        Assert.assertEquals(signInPage.getPasswordError(), TestValues.PASSWORD_ERROR);
    }

    @Test(priority = 2)
    public void checkPasswordWithoutNumbers() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.fillInPassword("Qwe$wert");
        Assert.assertEquals(signInPage.getPasswordError(), TestValues.PASSWORD_ERROR);
    }

    @Test(priority = 2)
    public void checkWrongPassword() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.authorization(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD + "2");
        Assert.assertEquals(signInPage.getPasswordError(), TestValues.PASSWORD_WRONG_ERROR);
    }

    @Test(priority = 3)
    public void checkValidLogin() {
        SignInPage signInPage = new StartPage().signIn();
        MainPage mainPage = signInPage.authorization(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD);
        Assert.assertEquals(mainPage.getUrlMainPage(), TestValues.MAINPAGE_URL);
    }

}
