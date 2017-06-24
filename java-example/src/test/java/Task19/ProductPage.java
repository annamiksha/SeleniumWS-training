package Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;

public class ProductPage {

    public void getProduct(WebDriver driver,WebDriverWait wait,int n){
        addToBasket(driver);
        WebElement basketContent = driver.findElement(By.cssSelector("#cart .quantity"));
        wait.until(attributeToBe(basketContent,"innerText", Integer.toString(n+1)));
        driver.findElement(By.className("general-0")).click();
    }

    public void addToBasket(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            WebElement size = driver.findElement(By.name("options[Size]"));
            size.click();
            size.findElement(By.cssSelector("[value = Small]")).click();
            clickBuy(driver);
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            clickBuy(driver);
        }
    }

    public void clickBuy(WebDriver driver){
        driver.findElement(By.name("add_cart_product")).click();
    }
}
