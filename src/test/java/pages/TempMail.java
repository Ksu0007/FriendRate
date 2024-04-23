package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.time.Duration;
import java.time.Instant;

public class TempMail {
    private WebDriver driver;
    private String tempMailUrl = "https://temp-mail.org/";

    @FindBy(xpath = "//button[@class='btn-rds icon-btn bg-theme click-to-copy copyIconGreenBtn']//*[name()='svg']")
    private WebElement emailCopy;

    @FindBy(xpath = "//a[normalize-space()='Verify email']")
    private WebElement openEmailLink;

    @FindBy(xpath = "//a[text()='Click verify email']")
    private WebElement verificationLink;

    public TempMail(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        openTempMailPage();
    }

    private void openTempMailPage() {
        driver.get(tempMailUrl);
    }

    public String getTempMail() throws Exception {
        emailCopy.click();
        String email = getClipboardContent();
        return email;
    }

    private static String getClipboardContent() throws Exception {
        // Получаем системный буфер обмена
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // Получаем содержимое буфера обмена
        Transferable contents = clipboard.getContents(null);
        // Проверяем, содержит ли буфер обмена текст
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            // Получаем текст из буфера обмена
            return (String) contents.getTransferData(DataFlavor.stringFlavor);
        }
        // Если буфер обмена не содержит текст, возвращаем пустую строку
        return "";
    }

    public void verifyEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(openEmailLink));
        openEmailLink.click();
        verificationLink.click();
    }
}


