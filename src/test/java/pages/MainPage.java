package pages;

import core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {
    @FindBy(css = ".Main_mainLogo__14csC")
    private WebElement logo;

    @FindBy(css = ".Main_mainImage__L6Onk")
    private WebElement image;

    @FindBy(css = ".Main_mainTxt__i2bj9")
    private WebElement pageDescription;

    @FindBy(xpath = "//label[@for='gender']")
    private WebElement genderLabel;

    @FindBy(xpath = "//div[@class='GenderSelector_customSelect__dogzn']/span")
    private WebElement chosenGender;

    @FindBy(css = ".GenderSelector_customSelect__dogzn")
    private WebElement genderDropdown;

    @FindBy(css = ".GenderSelector_options2__NepGw")
    private List<WebElement> genderOptions;

    @FindBy(xpath = "//label[@for='language']")
    private WebElement langLabel;

    @FindBy(css = ".LangSelector_customSelect__1wKKn")
    private WebElement langDropdown;

    @FindBy(css = ".LangSelector_options__z2RmK")
    private WebElement chosenLang;

    @FindBy(css = ".LangSelector_options2__T1T5T")
    private List<WebElement> langOptions;

    @FindBy(xpath = "//div[@class='Main_inputContainer__1mNzT']/input[1]")
    private WebElement leftSliderInput;

    @FindBy(xpath = "//div[@class='Main_inputContainer__1mNzT']/input[2]")
    private WebElement rightSliderInput;

    @FindBy(css = ".Main_buttonOK__imUHe")
    private WebElement sliderOkButton;

    @FindBy(xpath = "//div[@class='navbar_navbar__V5MzF']/a[4]")
    private WebElement profileButton;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public String getUrlMainPage() {
        return driver.getCurrentUrl();
    }

    public boolean logoIsDisplayed() {
        return logo.isDisplayed();
    }

    public boolean imageIsDisplayed() {
        return image.isDisplayed();
    }

    public String getPageDescrition() {
        return pageDescription.getText();
    }

    public String getGenderLabelText() {return genderLabel.getText();}

    public String getChosenGender() {return chosenGender.getText();}


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

    public String getChosenLang() {return chosenLang.getText();}
    public void chooseLanguage(String neededLanguage) {
        langDropdown.click();;
        for(WebElement lang : langOptions) {
            if(lang.getText().equals(neededLanguage)) {
                lang.click();
                return;
            }
        }
    }

    public void setSliderRange(int min, int max) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", leftSliderInput, min);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", rightSliderInput, max);

    }


    public String getLeftSliderValue() {return leftSliderInput.getAttribute("value");}
    public String getRightSliderVaue() {return  rightSliderInput.getAttribute("value");}


    public void setSliderRange2(int min, int max) {
        leftSliderInput.clear();

        leftSliderInput.sendKeys(Integer.toString(min));
        rightSliderInput.clear();
        rightSliderInput.sendKeys(Integer.toString(max));
        sliderOkButton.click();
    }

    public ProfilePage openProfile() {
        profileButton.click();
        return new ProfilePage();
    }


}
