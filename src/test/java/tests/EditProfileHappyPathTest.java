package tests;

import com.github.javafaker.Faker;
import core.BaseTest;
import helpers.TestValues;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditProfileHappyPathTest extends BaseTest {
    public Faker faker;

    @BeforeMethod
    public void login() {
        SignInPage signInPage = new StartPage().signIn();
        signInPage.authorization(TestValues.VALID_EMAIL, TestValues.VALID_PASSWORD);
    }

    @Test
    public void testOpenEditProfilePage() {
        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        Assert.assertEquals(editProfilePage.getHeaderText(), "Edit");
    }

    @Test
    public void testEditProfile() throws InterruptedException {
        faker = new Faker();
        PastDateGenerator dateGenerator = new PastDateGenerator();

        String newUsername = faker.name().firstName();
        String newBirthDate = String.valueOf(dateGenerator.getPastDate(Integer.parseInt(TestValues.NEW_AGE)));
        System.out.println("Date: " + newBirthDate);
        String newAbout = faker.friends().quote();

        EditProfilePage editProfilePage = new MainPage().openProfile().openEdit();
        editProfilePage.updateAndSaveProfile(newUsername, newBirthDate, newAbout);
        ProfilePage profilePage = new ProfilePage();
        Thread.sleep(1000);

        Assert.assertEquals(profilePage.getUsername(), newUsername);
        Assert.assertEquals(profilePage.getAboutText(), newAbout);
        Assert.assertEquals(profilePage.getAge(), TestValues.NEW_AGE);

    }


    public class PastDateGenerator {
        public String getPastDate(int yearsAgo) {
            LocalDate currentDate = LocalDate.now();
            LocalDate pastDate = currentDate.minusYears(yearsAgo);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            return pastDate.format(formatter);
        }
    }
}
