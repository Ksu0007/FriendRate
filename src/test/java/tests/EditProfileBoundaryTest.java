package tests;

import com.github.javafaker.Faker;
import core.BaseTest;
import helpers.EditTestValues;
import helpers.TestValues;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class EditProfileBoundaryTest extends BaseTest {
    public Faker faker;

    @BeforeMethod
    public void login() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.authorization(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD);
    }

    @Test
    public void testUsernameMinLength() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateUsername(EditTestValues.validMinUsername());
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");

        editProfilePage.clickSave();
        ProfilePage profilePage = new ProfilePage();

        Assert.assertEquals(profilePage.getUrl(), EditTestValues.PROFILE_URL);
    }

    @Test
    public void testUsernameMaxLength() throws InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        String newUsername = EditTestValues.validMaxUsername();
        editProfilePage.updateUsername(newUsername);

        editProfilePage.clickSave();

        ProfilePage profilePage = new ProfilePage();
        Thread.sleep(1000);

        Assert.assertEquals(profilePage.getUrl(), EditTestValues.PROFILE_URL);
        Assert.assertEquals(profilePage.getUsername(), newUsername);
    }

    @Test(enabled = true)
    public void testUsernameUnderscore() throws InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        String newUsername = EditTestValues.validUsernameUnderscore();
        editProfilePage.updateUsername(newUsername);

        editProfilePage.clickSave();

        ProfilePage profilePage = new ProfilePage();
        Thread.sleep(1000);

        Assert.assertEquals(profilePage.getUrl(), EditTestValues.PROFILE_URL);
        Assert.assertEquals(profilePage.getUsername(), newUsername);
    }

    @Test(enabled = true)
    public void testUsernameDot() throws InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        String newUsername = EditTestValues.validUsernameDot();
        editProfilePage.updateUsername(newUsername);

        editProfilePage.clickSave();

        ProfilePage profilePage = new ProfilePage();
        Thread.sleep(1000);

        Assert.assertEquals(profilePage.getUrl(), EditTestValues.PROFILE_URL);
        Assert.assertEquals(profilePage.getUsername(), newUsername);
    }

    @Test(enabled = true)
    public void testUsernameAt() throws InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        String newUsername = EditTestValues.validUsernameAt();
        editProfilePage.updateUsername(newUsername);

        editProfilePage.clickSave();

        ProfilePage profilePage = new ProfilePage();
        Thread.sleep(1000);

        Assert.assertEquals(profilePage.getUrl(), EditTestValues.PROFILE_URL);
        Assert.assertEquals(profilePage.getUsername(), newUsername);
    }

    @Test(enabled = true)
    public void testUsernamePlus() throws InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        String newUsername = EditTestValues.validUsernamePlus();
        editProfilePage.updateUsername(newUsername);

        editProfilePage.clickSave();

        ProfilePage profilePage = new ProfilePage();
        Thread.sleep(1000);

        Assert.assertEquals(profilePage.getUrl(), EditTestValues.PROFILE_URL);
        Assert.assertEquals(profilePage.getUsername(), newUsername);
    }

    @Test(enabled = true)
    public void testUsernameHyphen() throws InterruptedException {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        String newUsername = EditTestValues.validUsernameHyphen();
        editProfilePage.updateUsername(newUsername);

        editProfilePage.clickSave();

        ProfilePage profilePage = new ProfilePage();
        Thread.sleep(1000);

        Assert.assertEquals(profilePage.getUrl(), EditTestValues.PROFILE_URL);
        Assert.assertEquals(profilePage.getUsername(), newUsername);
    }

    @Test
    public void testValidLongEmail() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.emailValidMaxLength());
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isEmailErrorDisplayed());
    }

    @Test
    public void testEmailDotInLocalPart() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.emailWithDotInLocalPart());
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isEmailErrorDisplayed());
    }

    @Test
    public void testValidMaxBirthday() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateBirthday(EditTestValues.VALID_MAX_DATE);
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isBirthdayErrorDisplayed());
    }

    @Test
    public void testValidMinBirthday() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateBirthday(EditTestValues.VALID_MIN_DATE);
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isBirthdayErrorDisplayed());
    }

    @Test
    public void testValidMinPassword() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.VALID_PASSWORD_8);
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isPasswordErrorDisplayed());
    }

    @Test
    public void testValidMaxPassword() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.MAX_LENGTH_PASSWORD);
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isPasswordErrorDisplayed());
    }

    @Test
    public void testMatchingPasswords() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.VALID_PASSWORD_8);
        editProfilePage.confirmPassword(EditTestValues.VALID_PASSWORD_8);
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isConfPassErrorDisplayed());
    }

    @Test
    public void testValidMinAbout() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateAboutInfo(EditTestValues.generateRandomString(10));
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isAboutErrorDisplayed());
    }

    @Test
    public void testValidMaxAbout() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateAboutInfo(EditTestValues.generateRandomString(255));
        editProfilePage.findError();

        Assert.assertFalse(editProfilePage.isAboutErrorDisplayed());
    }




//Negatives
    @Test
    public void testShortUsername() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateUsername(EditTestValues.invalidMinUsername());

        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getUserNameError(), EditTestValues.ERROR_INVALID_USERNAME);
    }

    @Test
    public void testLongUsername() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateUsername(EditTestValues.invalidMaxUsername());

        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getUserNameError(), EditTestValues.ERROR_INVALID_USERNAME);
    }

    @Test
    public void testUsernameInvalidSymbols() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateUsername(EditTestValues.invalidUsernameSymbol());

        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getUserNameError(), EditTestValues.ERROR_INVALID_USERNAME);
    }

    @Test
    public void testEmptyUsername() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateUsername("");
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getUserNameError(), EditTestValues.ERROR_INVALID_USERNAME);
    }

    @Test
    public void testEmptyEmail() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail("");
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getEmailError(), EditTestValues.ERROR_EMPTY_EMAIL);
    }

    @Test
    public void testLongEmail() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.emailInvalidMaxLength());

        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getEmailError(), EditTestValues.ERROR_LONG_EMAIL);
    }

    @Test
    public void testEmptyLocalPart() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.EMPTY_LOCAL_PART);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getEmailError(), EditTestValues.ERROR_INVALID_EMAIL);
    }

    @Test
    public void testInvalidSymbolsInLocalPart() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.emailInvalidLocalPartSymbols());
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getEmailError(), EditTestValues.ERROR_INVALID_EMAIL);
    }

    @Test
    public void testEmailWithoutAt() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.emailWithoutAtSymbol());
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getEmailError(), EditTestValues.ERROR_INVALID_EMAIL);
    }

    @Test
    public void testEmailWithoutDot() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.emailWithoutDotInDomainPart());
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getEmailError(), EditTestValues.ERROR_INVALID_EMAIL);
    }

    @Test
    public void testEmailWithSymbolInDomain() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateEmail(EditTestValues.emailWithSymbolInDomainPart());
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getEmailError(), EditTestValues.ERROR_INVALID_EMAIL);
    }

    @Test
    public void testBirthdayInvalidDay() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateBirthday(EditTestValues.INVALID_DAY);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getBirthdayError(), EditTestValues.ERROR_INVALID_BIRTHDAY);
    }

    @Test
    public void testBirthdayInvalidMonth() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateBirthday(EditTestValues.INVALID_MONTH);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getBirthdayError(), EditTestValues.ERROR_INVALID_BIRTHDAY);
    }

    @Test
    public void testBirthdayInvalidYearPast() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateBirthday(EditTestValues.INVALID_YEAR_PAST);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getBirthdayError(), EditTestValues.ERROR_INVALID_BIRTHDAY);
    }

    @Test
    public void testBirthdayInvalidYearFuture() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateBirthday(EditTestValues.INVALID_YEAR_FUTURE);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getBirthdayError(), EditTestValues.ERROR_INVALID_BIRTHDAY);
    }

    @Test
    public void testInvalidShortPassword() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.INVALID_SHORT_PASSWORD);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getPasswordError(), EditTestValues.ERROR_INVALID_PASSWORD);
    }

    @Test
    public void testInvalidLongPassword() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.INVALID_LONG_PASSWORD);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getPasswordError(), EditTestValues.ERROR_INVALID_PASSWORD);
    }

    @Test
    public void testInvalidLongPasswordNoLetters() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.PASSWORD_WITHOUT_LETTERS);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getPasswordError(), EditTestValues.ERROR_INVALID_PASSWORD);
    }

    @Test
    public void testInvalidLongPasswordNoNumbers() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.PASSWORD_WITHOUT_NUMBERS);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getPasswordError(), EditTestValues.ERROR_INVALID_PASSWORD);
    }

    @Test
    public void testInvalidLongPasswordNoSymbols() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.PASSWORD_WITHOUT_SPECIAL_CHARACTERS);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getPasswordError(), EditTestValues.ERROR_INVALID_PASSWORD);
    }

    @Test
    public void testMismatchingPasswords() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updatePassword(EditTestValues.VALID_PASSWORD_29);
        editProfilePage.confirmPassword(EditTestValues.VALID_PASSWORD_8);
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getConfPassError(), EditTestValues.ERROR_CONFIRM_PASSWORD);
    }

    @Test
    public void testShortAbout() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateAboutInfo(EditTestValues.generateRandomString(9));
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getAboutError(), EditTestValues.ERROR_SHORT_ABOUT);
    }

    @Test
    public void testLongAbout() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateAboutInfo(EditTestValues.generateRandomString(256));
        editProfilePage.findError();

        Assert.assertEquals(editProfilePage.getAboutError(), EditTestValues.ERROR_LONG_ABOUT);
    }



}
