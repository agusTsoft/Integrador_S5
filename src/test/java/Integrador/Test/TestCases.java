package Integrador.Test;

import Integrador.Pages.HomePage;
import Integrador.Pages.RegisterPage;
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
    private RegisterPage registerPage;
    private BaseClass baseClass;
    private static String urlDriver = "C:\\IntegradorS5\\src\\test\\resources\\Drivers\\chromedriver.exe";
    private static String property = "webdriver.chrome.driver";
    private static String browser = "chrome";


    @Test
    public void TC001_goToRegisterPage() throws InterruptedException {
        driver.get("https://buenosaires.gob.ar/inicio/");
        homePage.goToRegisterPage();
    }

    @Test
    public void TC002_SendEmpty() throws InterruptedException{
        driver.get("https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711");
        registerPage.SendForm();
        assertEquals("https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711", registerPage.getDriver().getPageSource());
    }

    @Test
    public void TC003_registerOK() throws InterruptedException {
        driver.get("https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711");
        registerPage.fill("CP002");
        assertTrue(homePage.buscarWebElement(By.xpath("//h4[text()]")).getText().contains("Felicidades"));
    }

    @Test
    public void TC004_RegisterNOK() throws InterruptedException{
        driver.get("https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711");
        registerPage.fill("CP003");
        assertTrue(homePage.buscarWebElement(By.id("field-11463-error")).getText().contains("Ya estás registrado/a en esta actividad"));
    }

    @Test
    public void TC005_AgeBelow18() throws InterruptedException{
        driver.get("https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711");
        registerPage.fill("CP005");
        assertEquals("Sección Adulto/Tutor Responsable", registerPage.buscarWebElement(By.xpath("//h2[text()='Sección Adulto/Tutor Responsable']")).getText());
    }

    @BeforeEach
    public void preConditions() throws FileNotFoundException {
        baseClass = new BaseClass(driver, wait);
        driver = baseClass.conexionDriver(browser, urlDriver, property);
        wait = new WebDriverWait(driver,20);
        homePage = new HomePage(driver, wait);
        registerPage  = new RegisterPage(driver, wait);
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
