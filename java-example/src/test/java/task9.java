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

public class task9 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //Chrome
         driver = new ChromeDriver();
         wait = new WebDriverWait(driver, 1);
    }

    @Test
    public void checkCountriesSorting() {
        List<WebElement> tableData = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<String>();
        int columns = 7;
        int countryColumn = 5;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        AdminPanelLogin();
        openAdminBlock(driver,3).click();
        WebElement countryTable = driver.findElement(By.className("data-table"));
        tableData = countryTable.findElements(By.tagName("td"));
        countries = getColumnData(tableData,columns,countryColumn,"textContent");
        assertTrue(isListOrdered(countries));

    }

    @Test
    public void checkCountryZones() {
        List<WebElement> tableData = new ArrayList<>();
        List<WebElement> zoneTableData = new ArrayList<>();
        ArrayList<String> zonesCount = new ArrayList<String>();
        ArrayList<String> zones = new ArrayList<String>();
        int columns = 7;
        int zonesColumns = 2; //count only .form-control
        int zoneNameColumn = 2; //count only .form-control
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        AdminPanelLogin();
        openAdminBlock(driver,3).click();
        WebElement countryTable = driver.findElement(By.className("data-table"));
        tableData = countryTable.findElements(By.tagName("td"));
        zonesCount = getColumnData(tableData,columns,columns - 1,"textContent");

        for (int i = 0; i < zonesCount.size(); i++) {
            if (zonesCount.get(i).compareTo("0") > 0){
                tableData.get(columns * (i + 1) - 1).click();
                WebElement zoneTable = driver.findElement(By.className("data-table"));
                zoneTableData = zoneTable.findElements(By.cssSelector("td .form-control"));
                zones = getColumnData(zoneTableData,zonesColumns,zoneNameColumn,"defaultValue");
                assertTrue(isListOrdered(zones));

                //Return to countries
                openAdminBlock(driver,3).click();
                countryTable = driver.findElement(By.className("data-table"));
                tableData = countryTable.findElements(By.tagName("td"));
            }
        }

    }

    @Test
    public void checkZonesSorting() {
        List<WebElement> tableData = new ArrayList<>();
        List<WebElement> zoneData = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<String>();
        int columns = 4;
        int zoneColumn = 3;
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        AdminPanelLogin();
        openAdminBlock(driver,6).click();
        WebElement countryTable = driver.findElement(By.className("data-table"));
        tableData = countryTable.findElements(By.tagName("td"));

        for (int i = columns; i < tableData.size(); i = i + columns + 1) {
            tableData.get(i).click(); //click the pencil icon

            WebElement zoneTable = driver.findElement(By.className("data-table"));
            zoneData = zoneTable.findElements(By.tagName("td"));
            countries = getColumnData(zoneData,columns,zoneColumn,"textContent");
            assertTrue(isListOrdered(countries));

            //Return to zones
            openAdminBlock(driver,6).click();
            countryTable = driver.findElement(By.className("data-table"));
            tableData = countryTable.findElements(By.tagName("td"));
        }

    }

    void AdminPanelLogin(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.className("btn")).click();
    }

    ArrayList<String> getColumnData(List<WebElement> data, int columnsCount, int column, String attribute){
        ArrayList<String> list = new ArrayList<String>();
        for (int i = column - 1; i < data.size() ; i = i + columnsCount) {
            list.add(data.get(i).getAttribute(attribute));
        }
        return list;
    }

    WebElement openAdminBlock(WebDriver driver, int order){
        return driver.findElement(By.cssSelector("#box-apps-menu > li:nth-child(" + order + ')'));
    }

    boolean isListOrdered( ArrayList<String> list){
        boolean isSorted = false;
        for (int i = 1; i < list.size() ; i++) {
           isSorted = list.get(i-1).compareTo(list.get(i)) <= 0;
        }
        return isSorted;
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
