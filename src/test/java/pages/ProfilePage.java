package pages;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    @FindBy(css = ".styles_profile__edit-profile__Uv9DW")
    private WebElement editButton;

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public EditProfilePage openEdit() {
        editButton.click();
        return new EditProfilePage();
    }
}
