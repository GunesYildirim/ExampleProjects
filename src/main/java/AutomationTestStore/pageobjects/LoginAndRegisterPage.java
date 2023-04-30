package AutomationTestStore.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;

public class LoginAndRegisterPage {
	public WebDriver driver;

	public LoginAndRegisterPage(WebDriver driver) {

		this.driver = driver;
		// PageFactory.initElements(driver, this);
	}

	public void GoToLoginPage() {
		driver.findElement(By.linkText("Login or register")).click();
	}

	// 1a Register
	public void Register(String firstname, String lastname, String email, String adress, String city, String country,
			String region, String zipcode, String loginname, String password, String passwordconfirm) {
		WebElement firstName = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement lastName = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement emailAdress = driver.findElement(By.id("AccountFrm_email"));
		WebElement adressUser = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement cityUser = driver.findElement(By.id("AccountFrm_city"));
		WebElement regionDropdown = driver.findElement(By.id("AccountFrm_zone_id"));
		WebElement zipcodeUser = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement countryDropdown = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement loginName = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement passwordUser = driver.findElement(By.id("AccountFrm_password"));
		WebElement passwordConfirm = driver.findElement(By.id("AccountFrm_confirm"));
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
		loginName.sendKeys(loginname);
		passwordUser.sendKeys(password);
		passwordConfirm.sendKeys(passwordconfirm);
		driver.findElement(By.id("AccountFrm_agree")).click();
		driver.findElement(By.cssSelector("button[title='Continue']")).click();
	}

	// 1b Successful Login

	public void SuccessfulLogin(String loginname, String password) {
		WebElement userLoginName = driver.findElement(By.id("loginFrm_loginname"));
		WebElement userPassword = driver.findElement(By.id("loginFrm_password"));
		userLoginName.sendKeys(loginname);
		userPassword.sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Login']")).click();
	}

	// 1c Unsuccessful Login
	public void UnsuccessfulLogin(String loginname, String password) throws UnsupportedEncodingException {
		WebElement userLoginName = driver.findElement(By.id("loginFrm_loginname"));
		WebElement userPassword = driver.findElement(By.id("loginFrm_password"));
		userLoginName.sendKeys(loginname);
		userPassword.sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Login']")).click();
		String errorMessage = driver.findElement(By.cssSelector(".alert.alert-error:not(.close)")).getText();
		errorMessage = errorMessage.replaceAll("�", "");
		errorMessage = errorMessage.replaceAll("\\P{Print}", "");
		String expectedErrorMessage = "Error: Incorrect login or password provided.";

		if (expectedErrorMessage.equalsIgnoreCase(errorMessage)) {
			System.out.println(errorMessage);
			Assert.assertEquals(expectedErrorMessage, errorMessage);
		}
	}

	// 1d Forgot your password? button check
	public void forgotPassword(String loginname, String email) {
		driver.findElement(By.linkText("Forgot your password?")).click();
		WebElement forgotLoginName = driver.findElement(By.id("forgottenFrm_loginname"));
		WebElement forgotEmail = driver.findElement(By.id("forgottenFrm_email"));
		forgotLoginName.sendKeys(loginname);
		forgotEmail.sendKeys(email);
		driver.findElement(By.cssSelector("button[title='Continue']")).click();
		String successMessage = driver.findElement(By.cssSelector(".alert-success")).getText();
		String successMessageExpected = "Success: Password reset link has been sent to your e-mail address.";
		successMessage = successMessage.replaceAll("�", "");
		successMessage = successMessage.replaceAll("\\P{Print}", "");

		if (successMessageExpected.equalsIgnoreCase(successMessage)) {
			System.out.println(successMessage);
			Assert.assertEquals(successMessageExpected, successMessage);
		}
	}

}
