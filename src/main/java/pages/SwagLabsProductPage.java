package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwagLabsProductPage {

	WebDriver driver;

	By item = By.cssSelector(".inventory_item");
	By cart = By.cssSelector("#shopping_cart_container > a > svg > path");

	public SwagLabsProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(item));
	}

	public int getItemCount() {
		List<WebElement> _divs = driver.findElements(item);
		int noOfDivs = _divs.size();
		return noOfDivs;
	}

	public void clickCart() {
		driver.findElement(cart).click();
	}

}
