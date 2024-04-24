package tests;

import core.BaseTest;
import helpers.TestValues;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignUpPage;
import pages.StartPage;

public class RegistrationSadPathTest extends BaseTest {
    @Test
    public void testEmptyEmailInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration("", TestValues.VALID_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getEmailErrorText(), "Required");
    }

    @Test
    public void testInvalidEmailInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration("emaildomain.com", TestValues.VALID_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getEmailErrorText(), TestValues.SIGNUP_EMAIL_ERROR);
    }

    @Test
    public void testEmailWithoutDomainInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration("email@", TestValues.VALID_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getEmailErrorText(), TestValues.SIGNUP_EMAIL_ERROR);
    }

    @Test(enabled = false)
    public void testEmailWithoutComInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration("email@test", TestValues.VALID_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getEmailErrorText(), TestValues.SIGNUP_EMAIL_ERROR);
    }

    @Test(enabled = false)
    public void testUsedEmailInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getEmailErrorText(), TestValues.SIGNUP_USED_EMAIL_ERROR);
    }

    @Test
    public void testShortPasswordInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL, TestValues.SHORT_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getPasswordErrorText(), TestValues.SIGNUP_PASSWORD_ERROR);
    }
    @Test
    public void testLongPasswordInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL, TestValues.LONG_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getPasswordErrorText(), TestValues.SIGNUP_PASSWORD_ERROR);
    }

    @Test
    public void testNoLettersPasswordInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL,
                TestValues.NO_LETTERS_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getPasswordErrorText(), TestValues.SIGNUP_PASSWORD_ERROR);
    }

    @Test
    public void testNoNumbersPasswordInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL,
                TestValues.NO_NUMBERS_PASSWORD, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getPasswordErrorText(), TestValues.SIGNUP_PASSWORD_ERROR);
    }

    @Test
    public void testNoSpecialCharsPasswordInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL, TestValues.NO_SPECIAL_CHARS, TestValues.VALID_PASSWORD);
        Assert.assertEquals(signUpPage.getPasswordErrorText(), TestValues.SIGNUP_PASSWORD_ERROR);
    }

    @Test
    public void testEmptyConfPasswordInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD, "");
        Assert.assertEquals(signUpPage.getConfirmPassErrorText(), "Required");
    }

    @Test
    public void testMismatchingPasswordInput() {
        SignUpPage signUpPage = new StartPage().signUp();
        signUpPage.invalidRegistration(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD, "QWE");
        Assert.assertEquals(signUpPage.getConfirmPassErrorText(), TestValues.SIGNUP_CONFPASS_ERROR);
    }

}
