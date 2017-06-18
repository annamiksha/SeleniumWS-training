import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class task14 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //Chrome
        driver = new ChromeDriver();
       wait = new WebDriverWait(driver, 2);
    }


    @Test
    public void newWindow(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> tableHeaders = new ArrayList<>();
        List<WebElement> extLinks = new ArrayList<>();

        AdminPanelLogin();
        openAdminBlock(3);

        WebElement countryTable = driver.findElement(By.className("dataTable"));
        tableHeaders = countryTable.findElements(By.cssSelector("th"));
        countryTable.findElements(By.cssSelector("td")).get(tableHeaders.size() - 1).click();

        extLinks = driver.findElements(By.className("fa-external-link"));
        openExternalLinks(extLinks);
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


    void openExternalLinks(List<WebElement> list){
        String mainHandle = driver.getWindowHandle();
        WebElement litecartLogotype;
        Set<String> windows;

        for (int i = 0; i < list.size(); i++) {
            list.get(i).click();
            wait.until(numberOfWindowsToBe(2));
            windows = driver.getWindowHandles();
            windows.remove(mainHandle);
            litecartLogotype = driver.findElement(By.className("logotype"));

            for (String handle: windows) {
                driver.switchTo().window(handle);
                wait.until(stalenessOf(litecartLogotype));
                driver.close();
                driver.switchTo().window(mainHandle);
            }
        }
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
