package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SwagLabsLoginPage;
import pages.SwagLabsProductPage;

public class SwagLabsTest {
	WebDriver driver;
	SwagLabsLoginPage login;
	SwagLabsProductPage productsPage;

	String url = "https://www.saucedemo.com/";
	String username = "standard_user";
	String password = "secret_sauce";
	String item1 = "Sauce Labs Onesie";
	String item2 = "Sauce Labs Bike Light";
	String addToCartBtnLabel = "ADD TO CART";

	@Test
	public void testAddToCart() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);

		login = new SwagLabsLoginPage(driver);
		login.loginUser(username, password);

		productsPage = new SwagLabsProductPage(driver);
		productsPage.waitForElementToLoad();
		int itemCount = productsPage.getItemCount();

		addToCart(itemCount);
		verifyItemsInCart();
		driver.quit();
	}

	private void addToCart(int itemCount) {
		for (int i = 1; i <= itemCount; i++) {
			By label = By.cssSelector(".inventory_item:nth-of-type(" + i + ") div.inventory_item_label");
			String text = driver.findElement(label).getText();
			if (text.contains(item1) || text.contains(item2)) {
				By button = By.cssSelector(".inventory_item:nth-of-type(" + i + ") button");
				String buttonText = driver.findElement(button).getText();
				if (buttonText.contains(addToCartBtnLabel)) {
					driver.findElement(button).click();
				}
			}
		}
		productsPage.clickCart();
	}

	private void verifyItemsInCart() {
		for (int i = 3; i <= 4; i++) {
			By label = By.cssSelector(".cart_list div:nth-child(" + i + ") div.cart_item_label");
			String text = driver.findElement(label).getText();
			if (!(text.contains(item1) || text.contains(item2))) {
				Assert.fail("Cart does not contain expected items");
			}
		}
	}
}
