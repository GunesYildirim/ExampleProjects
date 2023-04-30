package AutomationTestStore.pageobjects;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationTestStore {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Güneş\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://automationteststore.com/");
		
		
		
		//Login or Register
		LoginAndRegisterPage loginandregister = new LoginAndRegisterPage(driver);
		loginandregister.GoToLoginPage();
//		driver.findElement(By.cssSelector("[title='Continue']")).click();
//		loginandregister.Register("melisa", "melisa", "melisa@gmail.com", "123.street 123", "Ankara", "Turkey",
//				"Ankara", "56780", "melisaa", "melisa1234", "melisa1234");
		loginandregister.GoToLoginPage();
		loginandregister.SuccessfulLogin("melisaa", "melisa1234");
//		driver.findElement(By.linkText("Logoff")).click();
//		loginandregister.GoToLoginPage();
//		loginandregister.UnsuccessfulLogin("melisa", "melisa1234");
//		loginandregister.GoToLoginPage();
//		loginandregister.forgotPassword("melisaa", "melisa@gmail.com");
		
		
		
		// Select a category from category menu and add product to cart
		HomePage homepage = new HomePage(driver);
		homepage.GoToHomePage();
//		homepage.selectCategory("Makeup");
		homepage.SelectSubCategory("Makeup", "Cheeks");
		homepage.SortBySelection("Price Low > High");
		homepage.pageSelection("30");
		homepage.AddProductsToCart("Skinsheen Bronzer Stick"); //homepage.AddProductsToCart("Benefit Bella Bamba");
	
		
		//Go To Shopping Cart
		ShoppingCartPage shoppingcartpage = new ShoppingCartPage(driver);
		shoppingcartpage.GoToCart();
		shoppingcartpage.ChangeQuantity("3");
		shoppingcartpage.RemoveProduct("Skinsheen Bronzer Stick");
		shoppingcartpage.CheckoutButton2("Turkey", "Ankara", "56780");
	
		//Checkout Page
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.GoToCheckout();
//		loginandregister.SuccessfulLogin("melisaa", "melisa1234");
		checkoutpage.GuestCheckout("melisa", "melisa", "melisa@gmail.com", "123.street 123", "Ankara", "Turkey", "Ankara","56780");
		checkoutpage.CheckTotalPrice();
//		checkoutpage.EditCart();
		checkoutpage.ConfirmationMessage();
		
	}

}
