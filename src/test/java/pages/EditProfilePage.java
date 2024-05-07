package pages;

import core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class EditProfilePage extends BasePage {

    @FindBy(css = ".edit_editHeaad__VWV74")
    private WebElement header;

    @FindBy(xpath = "//label[@for='username']")
    private WebElement usernameLabel;

    @FindBy(name = "username")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@id='username']/following-sibling::span[contains(@class, 'edit_errMes')]")
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

    @FindBy(xpath = "//label[@for='password']")
    private WebElement passwordLabel;
    
    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='password']/following-sibling::span[contains(@class, 'edit_errMes')]")
    private WebElement passwordError;

    @FindBy(xpath = "//label[@for='passwordRepeat']")
    private WebElement confirmPasswordLabel;

    @FindBy(id = "passwordRepeat")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@id='passwordRepeat']/following-sibling::span[contains(@class, 'edit_errMes')]")
    private WebElement confirmPasswordError;



    @FindBy(xpath = "//label[@for='about']")
    private WebElement aboutLabel;

    @FindBy(css = "#about")
    private WebElement aboutInput;

    @FindBy(xpath = "//textarea[@id='about']/following-sibling::span[contains(@class, 'edit_errMes')]")
    private WebElement aboutError;

    @FindBy(css = ".edit_saveBtn__7jwAq")
    private WebElement saveButton;

    @FindBy(css = ".edit_inputForm__dq15i")
    private WebElement otherElement;

    //JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    public EditProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {return header.getText();}

    //Username
    public String getUserNameLabel() {return usernameLabel.getText();}

    public String getUsernameValue() {return userNameInput.getAttribute("value");}

    public void updateUsername (String newUserName) {
        // clear the field (the only methods which is working here)
        while (!userNameInput.getAttribute("value").isEmpty()) {
            userNameInput.sendKeys(Keys.BACK_SPACE);
        }
        userNameInput.sendKeys(newUserName);
    }


    public String getUserNameError(){ return userNameError.getText();}

    //Email

    public String getEmailLabel() {return emailLabel.getText();}
    public String getEmailValue() {return emailInput.getAttribute("value");}

    public void updateEmail(String newEmail) {
       while (!emailInput.getAttribute("value").isEmpty()) {
           emailInput.sendKeys(Keys.BACK_SPACE);
       }
       emailInput.sendKeys(newEmail);
    }

    public String getEmailError() {return emailError.getText();}

    public boolean isEmailErrorDisplayed() {
        try {
            getWait5().until(ExpectedConditions.invisibilityOf(emailError));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    //Birthday

    public String getBirthdayLabel() {return birthdayLabel.getText();}
    public String getBirthdateValue() {return birthdayInput.getAttribute("value");}

    public void updateBirthday(String birthday) {
       while (!birthdayInput.getAttribute("value").isEmpty()){
           birthdayInput.sendKeys(Keys.BACK_SPACE);
       }
        birthdayInput.sendKeys(birthday);
    }

    public String getBirthdayError() {return birthdayError.getText();}
    public boolean isBirthdayErrorDisplayed() {
        try {
            getWait5().until(ExpectedConditions.invisibilityOf(birthdayError));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    //Gender
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


   //Language
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
    // Password
    public String getPasswordLabelText() {return passwordLabel.getText();}
    public String getPasswordPlaceholder() {return passwordInput.getAttribute("placeholder");}
    public String getPasswordError() {return passwordError.getText();}

    public boolean isPasswordErrorDisplayed() {
        try {
            getWait5().until(ExpectedConditions.invisibilityOf(passwordError));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    public void updatePassword(String newPassword) {
        passwordInput.sendKeys(newPassword);
    }

    //Confirm Password
    public String getConfPassLabelText() {return confirmPasswordLabel.getText();}
    public String getConfPassPlaceholder() {return confirmPasswordInput.getAttribute("placeholder");}
    public String getConfPassError() {return confirmPasswordError.getText();}

    public boolean isConfPassErrorDisplayed() {
        try {
            getWait5().until(ExpectedConditions.invisibilityOf(confirmPasswordError));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    public void confirmPassword(String confirmPass) {
        jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
        confirmPasswordInput.sendKeys(confirmPass);
    }

    //About

    public String getAboutLabel() {return aboutLabel.getText();}
    public String getAboutValue() {return aboutInput.getAttribute("value");}
    public String getAboutPalceholder() {return aboutInput.getAttribute("placeholder");}

    public String getAboutError() {return aboutError.getText();}
    public boolean isAboutErrorDisplayed() {
        try {
            getWait5().until(ExpectedConditions.invisibilityOf(aboutError));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }


    public void updateAboutInfo(String about) {
        while (!aboutInput.getAttribute("value").isEmpty()){
            aboutInput.sendKeys(Keys.BACK_SPACE);
        }
        aboutInput.sendKeys(about);
    }

    public ProfilePage updateAndSaveProfile(String newUsername, String newBirthday, String newAbout) {
        updateUsername(newUsername);
        updateBirthday(newBirthday);
        updateAboutInfo(newAbout);
        jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
        saveButton.click();
        return new ProfilePage();
    }

    public void clickSave() {
        jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
        saveButton.click();
    }

    public void findError() {
        otherElement.click();
    }

}
