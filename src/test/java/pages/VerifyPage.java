package pages;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyPage extends BasePage {
    @FindBy(xpath = "//h2")
    private WebElement verifyHeader;

    @FindBy(css = ".verify_subHead__uK4PC")
    private WebElement verifyText;

    @FindBy(xpath = "//button")
    private WebElement resentEmailButton;

    public VerifyPage() {
        PageFactory.initElements(driver, this);
    }
}
