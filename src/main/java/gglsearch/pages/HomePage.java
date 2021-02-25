package gglsearch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Vaishali
 *
 */
public class HomePage {
	
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	/**
	 * This googleSearchWithText method returns the page title for given search term
	 * 
	 * @param str
	 * @return
	 */
	public String googleSearchWithText(String str) {
		if (driver != null) {
			driver.findElement(By.name("q")).sendKeys(str);
			driver.findElement(By.name("btnK")).submit();
			return driver.getTitle();
		}
		return null;
	}

	/**
	 * This googleSearchWithText method returns the page title without search term
	 * 
	 * @return
	 */
	public String googleSearchWithoutText() {
		if (driver != null) {
			driver.findElement(By.name("btnK")).submit();
			return driver.getTitle();
		}
		return null;
	}

}
