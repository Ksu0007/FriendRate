package pages;

import core.BasePage;
import helpers.TestValues;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {
    @FindBy(xpath = "//h2")
    private WebElement header;

    @FindBy(xpath = "//div[@class='styles_description__lwxWZ']/p")
    private WebElement pageDescription;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailFieldLabel;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "/html/body/div[2]/div/div/form/div[1]/div")
    private WebElement emailErrorMsg;

    @FindBy(xpath = "//label[@for='password']")
    private WebElement passwordFieldLabel;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(xpath = "/html/body/div[2]/div/div/form/div[2]/div[2]")
    //textarea[@id='about']/following-sibling::span[contains(@class, 'edit_errMes')]
    private WebElement passwordErrorMsg;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement continueButton;

    public SignInPage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public String getDescriptionText() {
        return pageDescription.getText();
    }

    public String getEmailLabel() {
        return emailFieldLabel.getText();
    }

    public String getEmailPlaceholder() {
       return emailInput.getAttribute("placeholder");
    }

    public void fillInEmail(String email) {
        emailInput.sendKeys(email);
    }

    public String getEmailErrorMsg() {
        return emailErrorMsg.getText();
    }

    public String getPasswordLabel() {
        return passwordFieldLabel.getText();
    }

    public String getPasswordPlaceholder() {
        return passwordInput.getAttribute("placeholder");
    }

    public void fillInPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public String getPasswordError() {
        return passwordErrorMsg.getText();
    }

    public boolean isContinueButtonDisabled() {
        return continueButton.getAttribute("disabled") != null;
    }


    public MainPage authorization(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        continueButton.click();
        getWait10().until(ExpectedConditions.urlToBe(TestValues.MAINPAGE_URL));
        return new MainPage();
    }

}
