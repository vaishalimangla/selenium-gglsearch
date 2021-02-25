package gglsearch.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import gglsearch.base.TestSuiteBase;

/**
 * @author Vaishali
 *
 */
public class HomePageTest extends TestSuiteBase {

	/**
	 * The homePageTestWithTerm() test method is to test the landing page title with search term.
	 * 
	 */
	@Test(priority = 1)
	public void homePageTestWithTerm() {
		String title = homeLogin.googleSearchWithText(prop.getProperty("search_text"));
		Assert.assertEquals(title, "JAVA - Google Search");
	}

	/**
	 * The homePageTestWithoutTerm() test method is to test the landing page title without search term.
	 * 
	 */
	@Test(priority = 2)
	public void homePageTestWithoutTerm() {
		String title = homeLogin.googleSearchWithoutText();
		Assert.assertEquals(title, "Google");
	}

}
