package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.SecondRegistrationPage;
import pages.SignUpPage;
import pages.StartPage;

public class RegistrationHappyPathTest extends BaseTest {
    @Test
    public void testSignUpButton() {
        StartPage startPage = new StartPage();
        startPage.signUp();
        SignUpPage signUpPage = new SignUpPage();
        Assert.assertEquals(signUpPage.getHeaderText(), "Create new account");
    }


    @Test(enabled = false)
    public void testSignUp() throws Exception {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.enterEmail();
        signUpPage.enterPasswords();
        signUpPage.verifyEmail();
        SecondRegistrationPage secondRegistrationPage = new SecondRegistrationPage();
        Assert.assertEquals(secondRegistrationPage.getUrl(),
                "https://friend-rate-front.vercel.app/en/information");
    }

    @Test(enabled = false)
    public void testEnterAdditionalInfo() throws Exception {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.completeRegistration();
        SecondRegistrationPage secondRegistrationPage = new SecondRegistrationPage();
        secondRegistrationPage.createAccount("01.02.2002","Male");

        Assert.assertTrue(secondRegistrationPage.successPopupIsDisplayed());

        secondRegistrationPage.finishRegistration();
        MainPage mainPage = new MainPage();

        Assert.assertEquals(mainPage.getUrlMainPage(), "https://friend-rate-front.vercel.app/en/main");
    }
}
