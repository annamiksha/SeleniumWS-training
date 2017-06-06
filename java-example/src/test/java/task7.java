import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class task7 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //Chrome
         driver = new ChromeDriver();
         wait = new WebDriverWait(driver, 1);
    }

    @Test
    public void clickLeftMenu(){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        ArrayList<WebElement> level1 = new ArrayList<>();
        ArrayList<WebElement> level2 = new ArrayList<>();
        level1 = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#box-apps-menu li"));

        for (int i = 1; i <= level1.size() ; i++) {
            WebElement element1 = driver.findElement(By.cssSelector("#box-apps-menu > li:nth-child(" + i + ')'));
            element1.click();
            driver.findElement(By.tagName("h1"));

            level2 = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".docs li"));
            for (int j = 2; j <= level2.size(); j++) {
                WebElement element2 = driver.findElement(By.cssSelector(".docs li:nth-child(" + j + ')'));
                element2.click();
                driver.findElement(By.tagName("h1"));
            }
        }
    }
    
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
