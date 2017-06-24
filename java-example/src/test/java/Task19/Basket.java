package Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Basket {
    private WebDriver driver;
    private WebDriverWait wait;

    public Basket open(WebDriver driver){
        driver.findElement(By.cssSelector("#cart a.link")).click();
        return this;
    }

    public void removeAll(WebDriver driver,WebDriverWait wait){
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
}
