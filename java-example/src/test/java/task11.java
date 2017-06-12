import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class task11 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1);
    }

    @Test
    public void registration(){
        Random rn = new Random();
        String email = "test" + rn.nextInt(100000) + "@gmail.com";
        String tax = "tax";
        String company = "Hogwarts";
        String firstName = "James";
        String lastName = "Potter";
        String address1 = "addr1";
        String address2 = "addr2";
        String postcode = "12345";
        String phone = "1234567";
        String city = "Chicago";
        String pass = "Qwerty1";
        String passConfirm = "Qwerty1";

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("div#box-account-login p")).click();
        WebElement account = driver.findElement(By.cssSelector("div#box-create-account"));

        setUserData(account,"tax_id",tax);
        setUserData(account,"company",company);
        setUserData(account,"firstname",firstName);
        setUserData(account,"lastname",lastName);
        setUserData(account,"address1",address1);
        setUserData(account,"address2",address2);
        setUserData(account,"postcode",postcode);
        setUserData(account,"city",city);
        setUserData(account,"email",email);
        setUserData(account,"phone",phone);
        setUserData(account,"password",pass);
        setUserData(account,"confirmed_password",passConfirm);
        account.findElement(By.cssSelector("[name = country_code]")).click();
        account.findElement(By.cssSelector("[name = country_code] [value = US]")).click();
        account.findElement(By.cssSelector("[name = zone_code]")).click();
        account.findElement(By.cssSelector("[name = country_code] [value = AL]")).click();

        account.findElement(By.cssSelector("[name = create_account]")).click();
        logout(driver);

        WebElement login = driver.findElement(By.cssSelector("div#box-account-login"));
        setUserData(login,"email",email);
        setUserData(login,"password",pass);
        login.findElement(By.cssSelector("[type = submit]")).click();
        logout(driver);
    }

    void setUserData(WebElement element, String fieldName, String value){
        element.findElement(By.cssSelector("[name = " + fieldName + "]")).sendKeys(value);
    }

    void logout(WebDriver driver){
        driver.findElement(By.cssSelector(".account li:last-child a")).click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
