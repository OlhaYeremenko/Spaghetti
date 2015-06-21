package pages;


import helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.AbstractPage;

public class SentMailPage extends AbstractPage {

	public static final String COMPOSE_BTN_XPATH = "//div[@role='button' and @gh and contains(@class, 'T')]";
	public static final String SENT_MAIL_TAB_XPATH = ".//a[contains(@href,'#sent')]";
	public static final String DRAFTS_TAB_XPATH = "//a[contains(@href, '#drafts')]";
	public static final String FORM_SAVE_AND_QUITBTN_XPATH = ".//img[3]";
	public static final String USER_LOGO_PIC_XPATH = "//a[contains(@title,'�������')]";
	public static final String LOGOUT_XPATH = "//div/a[@href='https://mail.google.com/mail/logout?hl=ru']";


	@FindBy(xpath = COMPOSE_BTN_XPATH)
	private WebElement composeBTN;

	@FindBy(xpath = SENT_MAIL_TAB_XPATH)
	private WebElement sentMenuItem;

	@FindBy(xpath = LOGOUT_XPATH)
	private WebElement logOutBTN;

	@FindBy(xpath = USER_LOGO_PIC_XPATH)
	private WebElement logPicTitle;

	public SentMailPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public MessageFrom composeMailBtnClick() {
		composeBTN.click();
		return new MessageFrom(driver);
	}


	public SentMailPage sentMenuItemClick() {
		sentMenuItem.click();
		return new SentMailPage(driver);
	}


    public SentMailPage checkThatLetterSend() {


    return this;
}
//	@Override
//	public GmailLoginPage logOutBtnClick() {
//
//		logOutBTN.click();
//		return new GmailLoginPage(driver);
//	}

}
