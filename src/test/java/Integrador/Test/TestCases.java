package Integrador.Test;

import Integrador.Pages.HomePage;
import Integrador.Utils.BaseClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;


public class TestCases {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private BaseClass baseClass;
    private static String urlDriver = "C:\\IntegradorS5\\src\\test\\resources\\Drivers\\chromedriver.exe";
    private static String property = "webdriver.chrome.driver";
    private static String browser = "chrome";

    @Test
    public void TC001_createAccountBtn() throws InterruptedException {
        homePage.register();
        assertTrue(homePage.buscarWebElement(By.xpath("//h4[text()]")).getText().contains("Felicidades"));
    }


    @BeforeEach
    public void preConditions() throws FileNotFoundException {
        baseClass = new BaseClass(driver, wait);
        driver = baseClass.conexionDriver(browser, urlDriver, property);
        wait = new WebDriverWait(driver,20);
        homePage = new HomePage(driver, wait);
        driver.get("https://buenosaires.gob.ar/inicio/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void postConditions(){
        //driver.close()
    }

}
