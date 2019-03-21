package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwagLabsLoginPage {

	WebDriver driver;
	By username = By.id("user-name");
	By password = By.id("password");
	By titleText = By.className("barone");
	By loginButton = By.className("btn_action");

	public SwagLabsLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserName(String strUserName) {
		driver.findElement(username).sendKeys(strUserName);
	}

	public void enterPassword(String strPassword) {
		driver.findElement(password).sendKeys(strPassword);
	}

	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}

	public String getLoginPageTitle() {
		return driver.findElement(titleText).getText();
	}

	public void loginUser(String strUserName, String strPasword) {
		this.enterUserName(strUserName);
		this.enterPassword(strPasword);
		this.clickLoginButton();
	}
}
