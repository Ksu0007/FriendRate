package pages;

import com.github.javafaker.Faker;
import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SecondRegistrationPage extends BasePage {

    @FindBy(xpath = "//h2")
    private WebElement header;

    @FindBy(xpath = "//label[@for='username']")
    private WebElement usernameLabel;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(xpath = "//form/span[1]")
    private WebElement usernameError;

    @FindBy(xpath = "//label[@for='birthday']")
    private WebElement birthdayLabel;

    @FindBy(id = "birthday")
    private WebElement birthdayInput;

    @FindBy(xpath = "//form/span[2]")
    private WebElement birthdayError;

    @FindBy(css = ".information_boxTxt__fSQO1")
    private WebElement genderLabel;

    @FindBy(css = ".information_genderItem__iE_wz")
    private List<WebElement> genders;

    @FindBy(xpath = "//ul/li[1]")
    private WebElement maleButton;

    @FindBy(xpath = "//ul/li[1]/p")
    private WebElement maleLabel;

    @FindBy(xpath = "//ul/li[2]")
    private WebElement femaleButton;

    @FindBy(xpath = "//ul/li[2]/p")
    private WebElement femaleLabel;

    @FindBy(xpath = "//ul/li[3]")
    private WebElement otherButton;

    @FindBy(xpath = "//ul/li[2]/p")
    private WebElement otherLabel;

    @FindBy(css = ".information_continueBtn__4pOvq")
    private WebElement createAccButton;

    @FindBy(css = ".MuiBox-root")
    private WebElement congratsPopup;

    @FindBy(xpath = "//h3")
    private WebElement congrats;

    @FindBy(css = ".CongratsModal_congratsTxt__En8TC")
    private WebElement congratsText;

    @FindBy(css = ".CongratsModal_congratsBtn__hXx7G")
    private WebElement okButton;

    public SecondRegistrationPage() {
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {return driver.getCurrentUrl();}

    public String getHeader() {return header.getText();}

    public String getUsernameLabel() {return usernameLabel.getText();}

    public String getBirthdayLabel() {return birthdayLabel.getText();}

    public String getGenderLabel() {return genderLabel.getText();}

    public String getUsernameError() {return usernameError.getText();}
    public String getBirthdayError() {return birthdayError.getText();}

    public void chooseGender(String neededGender) {
        for(WebElement gender : genders) {
            String genderText = gender.findElement(By.cssSelector(".information_genderTxt__j3rLB")).getText();
            if (genderText.equals(neededGender)){
                gender.click();
                break;
            }
        }
    }

    public void createAccount(String birthday, String gender) {
        usernameInput.sendKeys(faker.name().firstName());
        birthdayInput.sendKeys(birthday);
        chooseGender(gender);
        createAccButton.click();
    }

    public void invalidUsernameBirthdayInput(String username, String birthday, String gender) {
        usernameInput.sendKeys(username);
        birthdayInput.sendKeys(birthday);
        chooseGender(gender);
        createAccButton.click();
    }

    public boolean successPopupIsDisplayed() {
        return congratsPopup.isDisplayed();
    }

    public String getCongrats(){
        String congratsHeader = congrats.getText();
        String congratsWords = congratsText.getText();
        return  congratsHeader + "" + congratsWords;
    }

    public MainPage finishRegistration() {
        okButton.click();
        return new MainPage();
    }


}
