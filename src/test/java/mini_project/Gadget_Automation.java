package mini_project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
public class Gadget_Automation {


	    WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        driver = new EdgeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        System.out.println("Setup completed");
	    }

	    @Test(priority = 1)
	    public void openSnapdealHomePage() {
	        driver.get("https://www.snapdeal.com/");
	        System.out.println("Opened Snapdeal homepage");
	    }

	    @Test(priority = 2)
	    public void searchProduct() {
	        WebElement search = driver.findElement(By.id("inputValEnter"));
	        search.sendKeys("Bluetooth headphone");
	        driver.findElement(By.xpath("//span[@class='searchTextSpan']")).click();
	        System.out.println("Searched for product");
	    }

	    @Test(priority = 3)
	    public void sortAndFilter() {
	        driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
	        driver.findElement(By.xpath("//li[@data-sorttype='plrty']")).click();

	        WebElement low = driver.findElement(By.xpath("//input[@name='fromVal']"));
	        low.clear();
	        low.sendKeys("700");

	        WebElement high = driver.findElement(By.xpath("//input[@name='toVal']"));
	        high.clear();
	        high.sendKeys("1400");

	        driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
	        System.out.println("Applied filters");
	    }

	    @Test(priority = 4)
	    public void printProductDetails() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("clear-filter-pill")));

	        List<WebElement> productNames = driver.findElements(By.className("product-title"));
	        List<WebElement> productPrices = driver.findElements(By.className("product-price"));

	        for (int i = 0; i < 5; i++) {
	            System.out.println("Name: " + productNames.get(i).getText() + ", Price: " + productPrices.get(i).getText());
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	        System.out.println("Browser closed");
	    }
	}

