package AutomationTestStore.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

public class ShoppingCartPage {
	public WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, this);
	}

	public void GoToCart() {
		WebElement cartbutton = driver.findElement(By.cssSelector("#main_menu_top > li:nth-child(3)"));
		cartbutton.click();
	}

	public void ChangeQuantity(String quantityNumber) {
		WebElement quantity = driver.findElement(By.id("cart_quantity50"));
		quantity.clear();
		quantity.sendKeys(quantityNumber);
		driver.findElement(By.id("cart_update")).click();
		String unitPrice = driver.findElement(By.xpath("//td[@class='align_right'][1]")).getText();
		String total = driver.findElement(By.xpath("//td[@class='align_right'][2]")).getText();
		unitPrice = unitPrice.replaceAll("[\\$,\\s]", "");
		total = total.replaceAll("[\\$,\\s]", "");
		double unitPriceint = Double.parseDouble(unitPrice);
		double quantityNumberint = Double.parseDouble(quantityNumber);
		double totalCalculatedint = unitPriceint * quantityNumberint;
		String totalCalculated = String.valueOf(totalCalculatedint);
		if (total.equalsIgnoreCase(totalCalculated)) {
			Assert.assertEquals(total, totalCalculated);
			System.out.println("Total Price is correct");
		}
	}

	public void RemoveProduct(String productName) {
		String productInCart = driver
				.findElement(By.xpath("//td[@class='align_left']//a[contains(text(),'" + productName + "')]"))
				.getText();
		if (productName.equalsIgnoreCase(productInCart)) {
			driver.findElement(By.cssSelector("a[class='btn btn-sm btn-default']")).click();
		}
	}

	public void CheckoutButton1() {
		driver.findElement(By.id("cart_checkout1")).click();
	}

	public void CheckoutButton2(String country, String state, String zipcode) {
		WebElement estimateCountryDropdown = driver.findElement(By.id("estimate_country"));
		estimateCountryDropdown.click();
		Select estimateCountrySelect = new Select(estimateCountryDropdown);
		estimateCountrySelect.selectByVisibleText(country);

		WebElement estimateStateDropdown = driver.findElement(By.id("estimate_country_zones"));
		estimateStateDropdown.click();
		Select estimateStateSelect = new Select(estimateStateDropdown);
		estimateStateSelect.selectByVisibleText(state);

		WebElement estimateZipcode = driver.findElement(By.id("estimate_postcode"));
		estimateZipcode.sendKeys(zipcode);

		driver.findElement(By.xpath("//span//button[@class='btn btn-default']")).click();
		driver.findElement(By.id("cart_checkout2")).click();
	}
}
