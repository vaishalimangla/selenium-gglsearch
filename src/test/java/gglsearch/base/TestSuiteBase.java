package gglsearch.base;

import static gglsearch.base.Constants.MAC;
import static gglsearch.base.Constants.PATH_DRIVERS;
import static gglsearch.base.Constants.WAIT_TIME;
import static gglsearch.base.Constants.WEBDRIVER_CHROME_DRIVER;
import static gglsearch.base.Constants.WEBDRIVER_EDGE_DRIVER;
import static gglsearch.base.Constants.WIN;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import gglsearch.pages.HomePage;

/**
 * @author Vaishali
 *
 */
public class TestSuiteBase {

	protected Properties prop;
	protected HomePage homeLogin;
	private WebDriver driver;

	/**
	 * The loadConfig() method is to load the Config Properties
	 */
	public void loadConfig() {
		// Load Properties only first time
		if (prop == null) {
			prop = new Properties();
			try (InputStream ip = getClass().getResourceAsStream("/config.properties");) {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The setup() method is to initialize the driver based on input browser and
	 * running OS (MAC or Windows).
	 * 
	 * @param browser
	 */
	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		String os = System.getProperty("os.name").toLowerCase();

		// MS Edge Browser
		if ("msedge".equalsIgnoreCase(browser)) {
			setUpEdgeDriver(os);
		}
		// Chrome Browser
		else if ("chrome".equalsIgnoreCase(browser)) {
			setUpChromeDriver(os);
		}

		loadConfig();
		defineDriver();
		homeLogin = new HomePage(driver);
	}

	private void setUpChromeDriver(String os) {
		if (os.contains(WIN)) {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, PATH_DRIVERS + "windows/chromedriver.exe");
		} else if (os.contains(MAC)) {
			System.setProperty(WEBDRIVER_CHROME_DRIVER, PATH_DRIVERS + "mac/chromedriver");
		}

		driver = new ChromeDriver();
	}

	private void setUpEdgeDriver(String os) {
		if (os.contains(WIN)) {
			System.setProperty(WEBDRIVER_EDGE_DRIVER, PATH_DRIVERS + "windows/msedgedriver.exe");
		} else if (os.contains(MAC)) {
			System.setProperty(WEBDRIVER_EDGE_DRIVER, PATH_DRIVERS + "mac/msedgedriver");
		}

		driver = new EdgeDriver();
	}

	private void defineDriver() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

	/**
	 * This method is to close the browser.
	 */
	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
