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
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class task8 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //Chrome
         driver = new ChromeDriver();
         wait = new WebDriverWait(driver, 1);
    }

    @Test
    public void findStickers() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
        List<WebElement> ducksList = new ArrayList<>();
        ducksList = driver.findElements(By.cssSelector("ul.listing-wrapper .image-wrapper"));

        for (int i = 0; i < ducksList.size(); i++) {
            assertTrue(isStickerSingle(driver, ducksList.get(i)));
        }
    }

    boolean isStickerSingle(WebDriver driver, WebElement duck){
            try {
                List<WebElement> stickersList = new ArrayList<>();
                stickersList = duck.findElements(By.cssSelector("div.image-wrapper .sticker"));
                return (stickersList.size() == 1);
            }
            catch (org.openqa.selenium.NoSuchElementException ex){
                return false;
            }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
