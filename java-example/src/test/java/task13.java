import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;


public class task13 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
    }

    @Test
    public void basket(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
        WebElement basketContent;

        for (int i = 0; i < 3 ; i++) {
            addToBasket(i);
            basketContent = driver.findElement(By.cssSelector("#cart .quantity"));
            wait.until(attributeToBe(basketContent,"innerText", Integer.toString(i+1)));
            driver.findElement(By.className("general-0")).click();
        }

        driver.findElement(By.cssSelector("#cart a.link")).click();
        WebElement removeButton;
        WebElement paymentDue;

        for (int i = 0; i < 3; i++) {
            try {
                paymentDue = driver.findElement(By.cssSelector(".footer"));
            }
            catch (org.openqa.selenium.NoSuchElementException e){
                break;
            }
            removeButton =  driver.findElement(By.name("remove_cart_item"));
            removeButton.click();
            wait.until(stalenessOf(paymentDue));
        }
    }

    void addToBasket(int n){
        List<WebElement> products;
        products = driver.findElements(By.cssSelector("ul.listing-wrapper .image-wrapper"));
        products.get(n).click();

        try {
            WebElement size = driver.findElement(By.name("options[Size]"));
            size.click();
            size.findElement(By.cssSelector("[value = Small]")).click();
            driver.findElement(By.name("add_cart_product")).click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            driver.findElement(By.name("add_cart_product")).click();
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
}
}
