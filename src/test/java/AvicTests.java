import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class AvicTests {

    private WebDriver driver;

    @BeforeTest
    public void profileSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avic.ua");
    }

    @Test
    public void checkTitle(){
        String actualTitle = driver.getTitle();
        String expectedTitle = "AVIC™ - удобный интернет-магазин бытовой техники и электроники в Украине. | Avic";
        assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void checkEqualsSearchInputAndValue(){
        driver.findElement(By.xpath("//input[@id='input_search']"))
                .sendKeys("test");
        driver.findElement(By.xpath("//button[contains(@class, 'button-reset')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        assertEquals(driver.findElement(By.xpath("//input[contains(@id, 'input_search')]"))
                .getAttribute("value"), "test");
    }

    @Test
    public void checkAmountOfDiscountedProducts(){
        driver.findElement(By.xpath("(//a[@href='/discount'])[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> links = driver.findElements(By.xpath("//img[@title='Уценка']"));
        int count = links.size();
        assertTrue(count==5);
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}