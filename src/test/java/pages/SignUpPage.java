package pages;

import core.BasePage;
import helpers.TestValues;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends BasePage {
    private MailSlurp tempMail;

    @FindBy(xpath = "//div[@class='SignUp_btnBox__Flxtc']/a[2]")
    private WebElement signInButton;

    @FindBy(css = ".SignUp_head__Ekskc")
    private WebElement header;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailFieldLabel;

    @FindBy(css = "#email")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@id='email']/following-sibling::span")
    private WebElement emailErrorMsg;

    @FindBy(xpath = "//label[@for='password']")
    private WebElement passwordFieldLabel;

    @FindBy(css = "#password")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@id='password']/following-sibling::span")
    private WebElement passwordErrorMsg;

    @FindBy(xpath = "/html/body/div[2]/form/label[2]/div/img")
    private WebElement passwordHideIcon;

    @FindBy(xpath = "//label[@for='passwordRepeat']")
    private WebElement confirmPassFieldLabel;

    @FindBy(css = "#passwordRepeat")
    private WebElement confirmPassInputField;

    @FindBy(xpath = " //input[@id='passwordRepeat']/following-sibling::span")
    private WebElement confirmPasswordErrorMsg;

    @FindBy(xpath = "//label[@for='passwordRepeat']//img[@alt='hide_icon']")
    private WebElement confirmPassHideIcon;

    @FindBy(xpath = "//img[@alt='Icon']")
    private WebElement termsCheckBox;

    @FindBy(css = ".SignUp_checkboxTxt__yFupN")
    private WebElement termsText;

    @FindBy(xpath = "//p[@class='SignUp_checkboxTxt__yFupN']/a[1]")
    private WebElement termsLink;

    @FindBy(xpath = "//p[@class='SignUp_checkboxTxt__yFupN']/a[2]")
    private WebElement privacyLink;

    @FindBy(css = ".SignUp_signupBtn__QaQ_R")
    private WebElement signUpSubmitButton;

    @FindBy(xpath = "//img[@alt='facebook']")
    private WebElement facebookButton;

    @FindBy(xpath = "//img[@alt='Google']")
    private WebElement googleButton;

    public SignUpPage(){
        PageFactory.initElements(driver, this);
        tempMail = new MailSlurp();
    }

    public String getHeaderText() {return header.getText();}

    public String getEmailLabelText() {return emailFieldLabel.getText();}
    public String getEmailErrorText() {return emailErrorMsg.getText();}

    public String getPasswordLabelText() {return passwordFieldLabel.getText();}
    public String getPasswordErrorText() {return passwordErrorMsg.getText();}

    public String getConfirmPassLabelText() {return confirmPassFieldLabel.getText();}
    public String getConfirmPassErrorText() {return confirmPasswordErrorMsg.getText();}

    public void invalidRegistration(String invEmail, String invPass, String invConfPass) {
        emailInputField.sendKeys(invEmail);
        passwordInputField.sendKeys(invPass);
        confirmPassInputField.sendKeys(invConfPass);
        termsCheckBox.click();
        signUpSubmitButton.click();
    }

    public void enterEmail() throws Exception {
        String temporaryEmail = tempMail.createEmail();
        emailInputField.sendKeys(temporaryEmail);
    }

    public void enterPasswords() {
        passwordInputField.sendKeys(TestValues.VALID_PASSWORD);
        confirmPassInputField.sendKeys(TestValues.VALID_PASSWORD);
        termsCheckBox.click();
        signUpSubmitButton.click();
    }


    public SecondRegistrationPage verifyEmail() throws Exception {

        String emailBody = tempMail.getLink();
        int startIndex = emailBody.indexOf("href=\"") + 6;
        int endIndex = emailBody.indexOf("\">", startIndex);
        String verificationLink = emailBody.substring(startIndex, endIndex);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.open('" + verificationLink + "')");

        String currentWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(2000);

        driver.close();
        driver.switchTo().window(currentWindowHandle);
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("https://friend-rate-front.vercel.app/en/information"));

        return new SecondRegistrationPage();
    }

    public SecondRegistrationPage completeRegistration() throws Exception {
        enterEmail();
        enterPasswords();
        verifyEmail();
        return new SecondRegistrationPage();
    }







}
