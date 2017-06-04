import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class test2_login {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //Chrome
        //driver = new ChromeDriver();
       //wait = new WebDriverWait(driver, 10);

        //Firefox old schema
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(FirefoxDriver.MARIONETTE, false);
//        driver = new FirefoxDriver(caps);
//        wait = new WebDriverWait(driver, 10);

        //Firefox Nightly
        DesiredCapabilities caps = new DesiredCapabilities();
        driver = new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files (x86)\\Nightly\\firefox.exe")), new FirefoxProfile(), caps);
        wait = new WebDriverWait(driver, 10);

        //IE
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
//        WebDriver driver = new InternetExplorerDriver(caps);


    }

    @Test
    public void test1(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("login")).click(); //click with no data input
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click(); //click with correct login data
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
