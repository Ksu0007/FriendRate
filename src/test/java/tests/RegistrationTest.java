package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignUpPage;
import pages.StartPage;

public class RegistrationTest extends BaseTest {
    @Test
    public void testSignUpButton() {
        StartPage startPage = new StartPage();
        startPage.signUp();
        SignUpPage signUpPage = new SignUpPage();
        Assert.assertEquals(signUpPage.getHeaderText(), "Create new account");
    }
}
