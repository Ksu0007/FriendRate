package pages;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.WatchEvent;

public class StartPage extends BasePage {
    @FindBy(xpath = "//img[@alt='Happy palce']")
    private WebElement image;

    @FindBy(xpath = "//div[@class='styles_logo__54svt']/img[1]")
    private WebElement micLogo;

    @FindBy(xpath = "//div[@class='styles_logo__54svt']/img[1]")
    private WebElement wordsLogo;

    @FindBy(css = ".styles_title__2vKex")
    private WebElement motto;

    @FindBy(css = ".styles_description__A2FMr")
    private WebElement buttonsDescription;

    @FindBy(css = ".styles_button__YwkS0")
    private WebElement signUpButton;

    @FindBy(css = ".styles_button-in__zmO5b")
    private WebElement signInButton;

    @FindBy(css = ".styles_none-registration__LPZql")
    private WebElement tryWithoutTextLink;

    public StartPage() {
        PageFactory.initElements(driver, this);
        driver.get("https://friend-rate-front.vercel.app/en/");
    }

    public String getMottoText() {return motto.getText();}

    public String getDescriptionText() {return buttonsDescription.getText();}

    public SignUpPage signUp() {
        signUpButton.click();
        return new SignUpPage();
    }
    public SignInPage signIn() {
        signInButton.click();
        return new SignInPage();
    }




}
