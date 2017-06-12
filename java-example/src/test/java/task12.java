import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class task12 {
    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test1(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        AdminPanelLogin();
        openAdminBlock(2);
        driver.findElement(By.cssSelector(".pull-right li:last-child")).click();
        WebElement tab = driver.findElement(By.cssSelector(".tab-content"));

        //General
        tab.findElement(By.cssSelector("[data-toggle = buttons] .btn-default")).click();
        tab.findElement(By.cssSelector("[data-name = Subcategory]")).click();
        tab.findElement(By.name("product_groups[]")).click();
        setData(tab,"date_valid_from", "21052017");
        setData(tab,"date_valid_to", "21052020");
        setData(tab,"code","product_code");
        tab.findElement(By.name("name[en]")).sendKeys("New Product");
        setData(tab,"sku","sku1");
        setData(tab,"gtin","gtin1");
        setData(tab,"taric","taric1");

        WebElement quantity = tab.findElement(By.name("quantity"));
        quantity.click();
        for (int i = 0; i < 3; i++) {
            quantity.sendKeys(Keys.ARROW_UP);
        }

        WebElement weight = tab.findElement(By.name("weight"));
        weight.click();
        for (int i = 0; i < 2; i++) {
            weight.sendKeys(Keys.ARROW_UP);
        }

        tab.findElement(By.name("weight_class")).click();
        tab.findElement(By.cssSelector("[title = Pounds]")).click();

        WebElement dim = tab.findElement(By.name("dim_x" ));
        dim.clear();
        dim.sendKeys("1");
        dim = tab.findElement(By.name("dim_y" ));
        dim.clear();
        dim.sendKeys("2");
        dim = tab.findElement(By.name("dim_z" ));
        dim.clear();
        dim.sendKeys("1");
        dim = tab.findElement(By.name("dim_class" ));
        dim.click();
        dim.findElement(By.cssSelector("[value = in]")).click();

        tab.findElement(By.name("delivery_status_id")).click();
        WebElement status = tab.findElement(By.name("sold_out_status_id"));
        status.click();
        status.findElements(By.tagName("option")).get(2).click();

        //Information
//        driver.findElement(By.cssSelector(".nav-tabs li:nth-child(2)")).click();
//        tab = driver.findElement(By.cssSelector(".tab-content"));
//        tab.findElement(By.name("manufacturer_id")).click();
//        tab.findElements(By.cssSelector("[name = manufacturer_id] option")).get(1).click();
//        setData(tab,"keywords","test");
//        tab.findElement(By.name("short_description[en]")).sendKeys("Short description is short");
//        tab.findElement(By.className("trumbowyg-editor")).click();
//        tab.findElement(By.className("trumbowyg-editor")).sendKeys("There was once a couple who had long in vain wished for a child. At length the woman hoped that God was about to grant her desire. They had a little window at the back of their house from which a splendid garden could be seen, which was full of beautiful flowers and herbs. It was, however, surrounded by a high wall, and no one dared to go into it because it belonged to an enchantress, who had great power and was dreaded by all the world.");
//        tab.findElement(By.name("attributes[en]")).sendKeys("attribute1, attribute2, attribute3");
//        tab.findElement(By.name("head_title[en]")).sendKeys("Head Title");
//        tab.findElement(By.name("meta_description[en]")).sendKeys("Meta Description");

        //Prices
//        driver.findElement(By.cssSelector(".nav-tabs li:nth-child(3)")).click();
//        tab = driver.findElement(By.cssSelector(".tab-content"));
//
//        WebElement purchase_price = tab.findElement(By.name("purchase_price"));
//        purchase_price.clear();
//        purchase_price.sendKeys("25");
//
//        WebElement currency_code = tab.findElement(By.name("purchase_price_currency_code"));
//        currency_code.click();
//        currency_code.findElement(By.cssSelector("[value = EUR]")).click();
//
//        WebElement price = tab.findElement(By.name("prices[USD]"));
//        price.clear();
//        price.sendKeys("12");
//        price = tab.findElement(By.name("prices[EUR]"));
//        price.clear();
//        price.sendKeys("10");


    }

    void AdminPanelLogin(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.className("btn")).click();
    }

    void openAdminBlock(int order){
        driver.findElement(By.cssSelector("#box-apps-menu > li:nth-child(" + order + ')')).click();
    }

    void setData(WebElement element, String fieldName, String value){
        element.findElement(By.cssSelector("[name = " + fieldName + "]")).sendKeys(value);
    }

    @After
    public void stop(){
//        driver.quit();
//        driver = null;
    }
}
