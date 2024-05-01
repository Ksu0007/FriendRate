package pages;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    @FindBy(css = ".styles_profile__edit-profile__Uv9DW")
    private WebElement editButton;

    @FindBy(css = ".styles_title__2vKex")
    private WebElement header;

    @FindBy(xpath = "//ul//div[1]")
    private WebElement age;

    @FindBy(css = ".styles_user-name__8ACIt")
    private WebElement username;

    @FindBy(css = ".styles_about__description__8f0gj")
    private WebElement aboutInfo;

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {return driver.getCurrentUrl();}
    public String getHeaderText() {return header.getText();}

    public String getUsername() {return username.getText();}

    public String getAge() {return age.getText();}

    public String getAboutText() {return aboutInfo.getText();}


    public EditProfilePage openEdit() {
        editButton.click();
        return new EditProfilePage();
    }

}
