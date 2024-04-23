package pages;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EditProfilePage extends BasePage {

    @FindBy(xpath = "//h2")
    private WebElement header;

    @FindBy(xpath = "//label[@for='username']")
    private WebElement usernameLabel;

    @FindBy(name = "username")
    private WebElement userNameInput;

    @FindBy(xpath = "/input[@id='username']/following-sibling::span[contains(@class, 'edit_errMes')]")
    private WebElement userNameError;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailLabel;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='email']/following-sibling::span[contains(@class, 'edit_errMes')]")
    private WebElement emailError;

    @FindBy(xpath = "//label[@for='birthday']")
    private WebElement birthdayLabel;

    @FindBy(name = "birthday")
    private WebElement birthdayInput;

    @FindBy(xpath = "//input[@id='birthday']/following-sibling::span[contains(@class, 'edit_errMes')]")
    private WebElement birthdayError;

    @FindBy(xpath = "//label[@for='gender']")
    private WebElement genderLabel;

    @FindBy(css = ".GenderSelector_customSelect__dogzn")
    private WebElement genderDropdown;

    @FindBy(css = ".GenderSelector_options2__NepGw")
    private List<WebElement> genderOptions;

    @FindBy(xpath = "//label[@for='language']")
    private WebElement langLabel;

    @FindBy(css = "LangSelector_customSelect__1wKKn")
    private WebElement langDropdown;

    @FindBy(css = ".LangSelector_options2__T1T5T")
    private List<WebElement> langOptions;

    @FindBy(xpath = "//label[@for='about']")
    private WebElement aboutLabel;

    @FindBy(css = "#about")
    private WebElement aboutInput;

    @FindBy(xpath = "//textarea[@id='about']/following-sibling::span[contains(@class, 'edit_errMes')]")
    private WebElement aboutError;

    public EditProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {return header.getText();}

    public String getUserNameLabel() {return usernameLabel.getText();}

    public void setNewUsername(String newUserName) {
        userNameInput.clear();
        userNameInput.sendKeys(newUserName);}

    public String getUserNameError(){ return userNameError.getText();}

    public String getEmailLabel() {return emailLabel.getText();}

    public void setNewEmail(String newEmail) {
        emailInput.clear();
        emailInput.sendKeys(newEmail);
    }

    public String getEmailError() {return emailError.getText();}

    public String getBirthdayLabel() {return birthdayLabel.getText();}

    public void setBirthday(String birthday) {
        birthdayInput.clear();
        birthdayInput.sendKeys(birthday);
    }

    public String getBirthdayError() {return birthdayError.getText();}

    public String getGenderLabelText() {return genderLabel.getText();}

    public void chooseGender(String neededGender) {
        genderDropdown.click();
        for (WebElement option : genderOptions) {
            if(option.getText().equals(neededGender)) {
                option.click();
                return;
            }
        }
    }

    public String getLangLabelText() {return langLabel.getText();}
    private void chooseLanguage(String neededLanguage) {
        langDropdown.click();;
        for(WebElement lang : langOptions) {
            if(lang.getText().equals(neededLanguage)) {
                lang.click();
                return;
            }
        }
    }
}
