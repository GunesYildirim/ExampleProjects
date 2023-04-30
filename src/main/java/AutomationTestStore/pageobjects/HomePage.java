package AutomationTestStore.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	public WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		// PageFactory.initElements(driver, this);
	}

	public void GoToHomePage() {
		driver.get("https://automationteststore.com/");
	}

	// Select a category
	public void selectCategory(String categoryName) {
		WebElement category = driver
				.findElement(By.xpath("//div//section//nav//ul//li//a[contains(text(),'" + categoryName + "')]"));// correct
																													// the
																													// selector
		category.click();
	}

	public void SelectSubCategory(String categoryName, String subCategoryName) {
		WebElement category = driver
				.findElement(By.xpath("//div//section//nav//ul//li//a[contains(text(),'" + categoryName + "')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(category).build().perform();
		WebElement subCategory = driver
				.findElement(By.xpath("//div//section//nav//ul//li//a[contains(text(),'" + subCategoryName + "')]"));
		subCategory.click();
	}

	public void SortBySelection(String sortText) {
		WebElement sortby = driver.findElement(By.id("sort"));
		sortby.click();
		Select sortDropdown = new Select(sortby);
		sortDropdown.selectByVisibleText(sortText);
	}

	public void pageSelection(String perPage) {
		WebElement pageSelect = driver.findElement(By.id("limit"));
		pageSelect.click();
		Select pageDropdown = new Select(pageSelect);
		pageDropdown.selectByVisibleText(perPage);
	}

	public WebElement getProductNames(String productName) {
		List<WebElement> productList = driver.findElements(By.cssSelector("div[class='col-md-3 col-sm-6 col-xs-12']"));
		WebElement productSelected = productList.stream()
				.filter(product -> product
						.findElement(By.cssSelector("div[class='col-md-3 col-sm-6 col-xs-12'] .prdocutname")).getText()
						.equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return productSelected;
	}

	public void AddProductsToCart(String productName) {
		WebElement productSelected = getProductNames(productName);
		WebElement addToCartBtn = productSelected
				.findElement(By.cssSelector("div[class='col-md-3 col-sm-6 col-xs-12'] .productcart"));
		addToCartBtn.click();
	}

}
