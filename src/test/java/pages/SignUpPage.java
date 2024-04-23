package pages;

import core.BasePage;
import helpers.TestValues;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends BasePage {
    private TempMail tempMail;

    @FindBy(xpath = "//div[@class='SignUp_btnBox__Flxtc']/a[2]")
    private WebElement signInButton;

    @FindBy(css = ".SignUp_head__Ekskc")
    private WebElement header;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailFieldLabel;

    @FindBy(css = "#email")
    private WebElement emailInputField;

    @FindBy(xpath = "/html/body/div[2]/form/span[1]")
    private WebElement emailErrorMsg;

    @FindBy(xpath = "//label[@for='password']")
    private WebElement passwordFieldLabel;

    @FindBy(css = "#password")
    private WebElement passwordInputField;

    @FindBy(xpath = "/html/body/div[2]/form/span[2]")
    private WebElement passwordErrorMsg;

    @FindBy(xpath = "/html/body/div[2]/form/label[2]/div/img")
    private WebElement passwordHideIcon;

    @FindBy(xpath = "//label[@for='passwordRepeat']")
    private WebElement confirmPassFieldLabel;

    @FindBy(css = "#passwordRepeat")
    private WebElement confirmPassInputField;

    @FindBy(xpath = "/html/body/div[2]/form/span[3]")
    private WebElement confirmPasswordErrorMsg;

    @FindBy(xpath = "//label[@for='passwordRepeat']//img[@alt='hide_icon']")
    private WebElement confirmPassHideIcon;

    @FindBy(xpath = "//img[@alt='Icon']")
    private WebElement termsCheckBox;

    @FindBy(css = "..SignUp_signupBtn__QaQ_R")
    private WebElement signUpSubmitButton;

    @FindBy(xpath = "//img[@alt='facebook']")
    private WebElement facebookButton;

    @FindBy(xpath = "//img[@alt='Google']")
    private WebElement googleButton;

    public SignUpPage(){
        PageFactory.initElements(driver, this);
        this.tempMail = new TempMail(driver);
    }

    public String getHeaderText() {
         String headerText = header.getText();
         return headerText;
    }

    public void enterEmail() throws Exception {
        String temporaryEmail = tempMail.getTempMail();
        emailInputField.sendKeys(temporaryEmail);
    }

    public void enterPasswords() {
        passwordInputField.sendKeys(TestValues.VALID_PASSWORD);
        confirmPassInputField.sendKeys(TestValues.VALID_PASSWORD);

    }

    public VerifyPage submitRegistration() {
        signUpSubmitButton.click();
        return new VerifyPage();
    }

    public SecondRegistrationPage emailVerification() {
        tempMail.verifyEmail();
        return new SecondRegistrationPage();
    }

}
