package Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {
    private WebDriver driver;
    private WebDriverWait wait;
    private ProductPage productPage = new ProductPage();
    private Basket basket = new Basket();

    public void open(){
        driver = new ChromeDriver();
        wait  = new WebDriverWait(driver, 3);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
    }

    public void openProductsByCount(int n){
        List<WebElement> products = driver.findElements(By.cssSelector("ul.listing-wrapper .image-wrapper"));
        products.get(n).click();
    }

    public void addToBasket(int productsCount){
        for (int i = 0; i < productsCount ; i++) {
            openProductsByCount(i);
            productPage.getProduct(driver,wait,i);
        }
    }

    public void clearBasket(){
        basket.open(driver).removeAll(driver,wait);
    }

    public void quit(){
        driver.quit();
        driver = null;
    }

}
