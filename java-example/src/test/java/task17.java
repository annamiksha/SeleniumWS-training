import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class task17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //Chrome
        driver = new ChromeDriver();
       wait = new WebDriverWait(driver, 2);
    }

    @Test
    public void test1(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        AdminPanelLogin();
        openAdminBlock(2);
        openProductsData();
    }

    void AdminPanelLogin(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    void openAdminBlock(int order){
        driver.findElement(By.cssSelector("#box-apps-menu > li:nth-child(" + order + ')')).click();
    }

    void openProductsData(){
        WebElement table = driver.findElement(By.className("dataTable"));
        List<WebElement> rows = table.findElements(By.cssSelector("tr.row"));
        for (int i = 1; i < rows.size()-1; i++) {
            rows.get(i).findElements(By.tagName("a")).get(0).click();

            //Check browser logs
            assertTrue(getLogs("browser").isEmpty());

            try {
                driver.findElement(By.name("cancel")).click();
            }
            catch (org.openqa.selenium.NoSuchElementException e){

            }
            table = driver.findElement(By.className("dataTable"));
            rows = table.findElements(By.tagName("tr"));
        }
    }

    List<LogEntry> getLogs(String option){
        return driver.manage().logs().get(option).getAll();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
