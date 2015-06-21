package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.AbstractPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Olha_Yeremenko on 21-Jun-15.
 */
public class SpamPage extends AbstractPage {

    private static final String NEW_LETTER_SUBJECT = "//table//tbody//tr[1]/td[6]//span[1]";
    private static final String NEW_LETTER_CONTENT = "//table//tbody//tr[1]/td[6]//span[2]";

    @FindBy(xpath = NEW_LETTER_SUBJECT)
    private WebElement letterSubject;

    @FindBy(xpath = NEW_LETTER_CONTENT)
    private WebElement letterContent;

    public SpamPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MailPage assertThatLetterInSpam(String subject, String content) {

        assertThat(letterSubject.getText(), containsString(subject));
        assertThat(letterContent.getText(), containsString(content));

        return new MailPage(driver);
    }
}
