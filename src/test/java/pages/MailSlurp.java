package pages;

import com.mailslurp.models.InboxDto;
import org.openqa.selenium.WebDriver;
import com.mailslurp.models.Email;
import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.clients.ApiClient;


import static org.testng.AssertJUnit.assertNotNull;

public class MailSlurp {
    private WebDriver driver;
    private static final String YOUR_API_KEY = "d89538848b9f783553714be5b29e2c6b34b7201110c8812eff4790d746aa29ed";
    private static final Boolean UNREAD_ONLY = true;
    private static final Long TIMEOUT_MILLIS = 30000L;
    private static InboxDto inbox;
    private static Email email;
    private static String confirmationCode;
    private static ApiClient mailslurpClient;

    public String  createEmail() throws ApiException {
        assertNotNull(YOUR_API_KEY);

        // setup mailslurp
        mailslurpClient = Configuration.getDefaultApiClient();
        mailslurpClient.setApiKey(YOUR_API_KEY);
        mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());
        InboxControllerApi inboxControllerApi = new InboxControllerApi(mailslurpClient);
        inbox = inboxControllerApi.createInboxWithDefaults();

        return inbox.getEmailAddress();
    }

    public String getLink() throws ApiException {
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
        email = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS, UNREAD_ONLY,
                null, null, null, null);
        return email.getBody();
    }
}


