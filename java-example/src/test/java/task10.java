import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class task10 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //Chrome
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        //Firefox old schema
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(FirefoxDriver.MARIONETTE, false);
//        driver = new FirefoxDriver(caps);
//        wait = new WebDriverWait(driver, 20);


        //IE
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
//        WebDriver driver = new InternetExplorerDriver(caps);

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void compareTitles(){
        List<WebElement> ducksList = new ArrayList<>();
        ducksList = driver.findElements(By.cssSelector(".info"));
        WebElement firstDuck = ducksList.get(0);

        String mainPageName = firstDuck.findElement(By.className("name")).getAttribute("innerText");
        firstDuck.click();
        String productPageName = driver.findElement(By.cssSelector("div#box-product .title")).getAttribute("innerText");
        assertTrue(mainPageName.equals(productPageName));
    }

    @Test
    public void comparePrices(){
        List<WebElement> ducksList = new ArrayList<>();
        ducksList = driver.findElements(By.cssSelector(".info"));
        WebElement firstDuck = ducksList.get(0);

        String mainPageRegularPrice = firstDuck.findElement(By.className("regular-price")).getAttribute("innerText");
        String mainPageCampaignPrice = firstDuck.findElement(By.className("campaign-price")).getAttribute("innerText");
        firstDuck.click();
        String productRegularPrice = driver.findElement(By.cssSelector("div#box-product .regular-price")).getAttribute("innerText");
        String productCampaignPrice = driver.findElement(By.cssSelector("div#box-product .campaign-price")).getAttribute("innerText");
        assertTrue(mainPageRegularPrice.equals(productRegularPrice));
        assertTrue(mainPageCampaignPrice.equals(productCampaignPrice));
    }

    @Test
    public void checkRegularPriceStyle(){
        List<WebElement> ducksList = new ArrayList<>();
        ducksList = driver.findElements(By.cssSelector(".info"));
        WebElement firstDuck = ducksList.get(0);

        String mainPageRegularPriceColor = firstDuck.findElement(By.className("regular-price")).getCssValue("color");
        String mainPageRegularPriceText = firstDuck.findElement(By.className("regular-price")).getCssValue("text-decoration-line");
        System.out.println(mainPageRegularPriceText);
        firstDuck.click();
        String productRegularPriceColor = driver.findElement(By.cssSelector("div#box-product .regular-price")).getCssValue("color");
        String productRegularPriceText = driver.findElement(By.cssSelector("div#box-product .regular-price")).getCssValue("text-decoration-line");

        assertTrue(isGray(mainPageRegularPriceColor));
        assertTrue(mainPageRegularPriceText.equals("line-through"));
        assertTrue(isGray(productRegularPriceColor));
        assertTrue(productRegularPriceText.equals("line-through"));
    }

    @Test
    public void checkCampaignPriceStyle(){
        List<WebElement> ducksList = new ArrayList<>();
        ducksList = driver.findElements(By.cssSelector(".info"));
        WebElement firstDuck = ducksList.get(0);

        String mainPageCampaignPriceColor = firstDuck.findElement(By.className("campaign-price")).getCssValue("color");
        firstDuck.click();
        String productCampaignPriceColor = driver.findElement(By.cssSelector("div#box-product .campaign-price")).getCssValue("color");

        assertTrue(isRed(mainPageCampaignPriceColor));
        assertTrue(isRed(productCampaignPriceColor));
    }

        @Test
    public void checkSize(){
        List<WebElement> ducksList = new ArrayList<>();
        ducksList = driver.findElements(By.cssSelector(".info"));
        WebElement firstDuck = ducksList.get(0);

        String mainPageRegularPriceSize = firstDuck.findElement(By.className("regular-price")).getCssValue("font-size");
        String mainPageCampaignPriceSize = firstDuck.findElement(By.className("campaign-price")).getCssValue("font-size");
        firstDuck.click();
        String productRegularPriceSize = driver.findElement(By.cssSelector("div#box-product .regular-price")).getCssValue("font-size");
        String productCampaignPriceSize = driver.findElement(By.cssSelector("div#box-product .campaign-price")).getCssValue("font-size");

        assertTrue(mainPageRegularPriceSize.compareTo(mainPageCampaignPriceSize) < 0);
        assertTrue(productRegularPriceSize.compareTo(productCampaignPriceSize) < 0);
    }


    boolean isGray(String rgba){
        String color = rgba.substring(5,18).replace(" ","");
        String[] values = color.split(",");
        return ((values[0].equals(values[1])) && (values[1].equals(values[2])));
    }

    boolean isRed(String rgba){
        String color = rgba.substring(5,18).replace(" ","");
        String[] values = color.split(",");
        return ((values[1].equals("0")) && ((values[2].equals("0"))));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
