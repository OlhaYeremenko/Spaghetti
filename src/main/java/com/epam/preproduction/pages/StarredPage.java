package com.epam.preproduction.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.epam.preproduction.template.AbstractPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class StarredPage extends AbstractPage {


    public StarredPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//    private static final String NEW_LETTER_SUBJECT = "//table//tbody//tr[1]/td[6]//span[1]";
//    private static final String NEW_LETTER_CONTENT = "//table//tbody//tr[1]/td[6]//span[2]";
//    private static final String BACK_TO_INBOX = "//div[@role='button'and @act='8']";
//
//
//    @FindBy(xpath = BACK_TO_INBOX)
//    private WebElement backToInboxBtn;
//
//    @FindBy(xpath = NEW_LETTER_SUBJECT)
//    private WebElement letterSubject;
//
//    @FindBy(xpath = NEW_LETTER_CONTENT)
//    private WebElement letterContent;
//
//    public MailPage assertThatLetterInStarred(String subject, String content) {
//
//        assertThat(letterSubject.getText(), containsString(subject));
//        assertThat(letterContent.getText(), containsString(content));
//
//        return new MailPage(driver);
//    }
//
//    public MailPage backToInbocx() {
//        return new MailPage(driver);
//    }

}
