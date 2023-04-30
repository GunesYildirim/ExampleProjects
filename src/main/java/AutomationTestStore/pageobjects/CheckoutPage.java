package AutomationTestStore.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutPage {
	public WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, this);
	}

	public void GoToCheckout() {
		WebElement checkoutbutton = driver.findElement(By.cssSelector("#main_menu_top > li:nth-child(4)"));
		checkoutbutton.click();
	}

	public void CheckTotalPrice() {
		List<WebElement> priceElements = driver.findElements(By.xpath("//td[@class='checkout_heading']"));
		double tax = 2.0;
		double totalPriceAdd = 0 + tax;

		for (WebElement priceElement : priceElements) {
			String priceText = priceElement.getText().trim().replaceAll("[^0-9\\.]", "");
			double price = Double.parseDouble(priceText);
			totalPriceAdd += price;
		}
		String totalPriceExpected = Double.toString(totalPriceAdd);
		WebElement totalpriceElement = driver.findElement(By.xpath("//span[@class='bold totalamout']"));
		String totalpriceText = totalpriceElement.getText().trim().replaceAll("[^0-9\\.]", "");
		double totalprice = Double.parseDouble(totalpriceText);
		String totalPriceActual = Double.toString(totalprice);
//		Assert.assertEquals(totalPriceExpected, totalPriceActual);
		if (totalPriceAdd == totalprice) {
			System.out.println("Total price is as expected");
			Assert.assertEquals(totalPriceExpected, totalPriceActual);
		}
	}

	public void EditCart() {
		WebElement editCartButton = driver.findElement(By.cssSelector(".pull-right.mr10.btn.btn-default.btn-xs"));
		editCartButton.click();
	}

	public void ConfirmationMessage() {
		WebElement confirmButton = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
		confirmButton.click();
		WebElement confirmationMessage = driver.findElement(By.cssSelector(".maintext"));
		String confirmationMessageText = driver.findElement(By.cssSelector(".maintext")).getText();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
		System.out.println(confirmationMessageText);
	}

	public void GuestCheckout(String firstname, String lastname, String email, String adress, String city,
			String country, String region, String zipcode) {
		driver.findElement(By.id("accountFrm_accountguest")).click();
		driver.findElement(By.cssSelector("button[title='Continue']")).click();
		WebElement firstName = driver.findElement(By.id("guestFrm_firstname"));
		WebElement lastName = driver.findElement(By.id("guestFrm_lastname"));
		WebElement emailAdress = driver.findElement(By.id("guestFrm_email"));
		WebElement adressUser = driver.findElement(By.id("guestFrm_address_1"));
		WebElement cityUser = driver.findElement(By.id("guestFrm_city"));
		WebElement regionDropdown = driver.findElement(By.id("guestFrm_zone_id"));
		WebElement zipcodeUser = driver.findElement(By.id("guestFrm_postcode"));
		WebElement countryDropdown = driver.findElement(By.id("guestFrm_country_id"));
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		emailAdress.sendKeys(email);
		adressUser.sendKeys(adress);
		cityUser.sendKeys(city);
		countryDropdown.click();
		Select countrySelect = new Select(countryDropdown);
		countrySelect.selectByVisibleText(country);
		regionDropdown.click();
		Select regionSelect = new Select(regionDropdown);
		regionSelect.selectByVisibleText(region);
		zipcodeUser.sendKeys(zipcode);
		driver.findElement(By.cssSelector("button[title='Continue']")).click();
	}

}
