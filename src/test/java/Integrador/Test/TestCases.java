package Integrador.Test;

import Integrador.Pages.HomePage;
import Integrador.Pages.RegisterPage;
import Integrador.Utils.BaseClass;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;


public class TestCases {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static HomePage homePage;
    private static RegisterPage registerPage;
    private static BaseClass baseClass;
    private static String urlDriver = "C:\\IntegradorS5\\src\\test\\resources\\Drivers\\chromedriver.exe";
    private static String property = "webdriver.chrome.driver";
    private static String browser = "chrome";


    @Test
    @Order(1)
    public void TC001_SearchAnything() throws InterruptedException {
        homePage.SearchAnything();
        assertTrue(homePage.buscarWebElement(By.xpath("//p[text()=' No se encontraron resultados. ']")).isDisplayed());
    }

    @Test
    @Order(2)
    public void TC002_SendEmpty() throws InterruptedException{
        homePage.goToRegisterPage();
        registerPage.SendForm();
        assertEquals("https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711", registerPage.getDriver().getCurrentUrl());
    }


    @Test
    @Order(3)
    public void TC003_registerOK() throws InterruptedException {
        homePage.goToRegisterPage();
        registerPage.fill("CP003");
        assertTrue(homePage.buscarWebElement(By.xpath("//h4[text()]")).getText().contains("Felicidades"));
    }

    @Test
    @Order(4)
    public void TC004_RegisterNOK() throws InterruptedException{
        homePage.goToRegisterPage();
        registerPage.fill("CP004");

        assertEquals("rgb(231, 96, 86)", driver.findElement(By.cssSelector(".has-error .form-control")).getCssValue("border-color"), "El borde no es rojo como se espera");
    }

    @Test
    @Order(5)
    public void TC005_AgeBelow18() throws InterruptedException{
        homePage.goToRegisterPage();
        registerPage.fill("CP005");
        assertEquals("Sección Adulto/Tutor Responsable", registerPage.buscarWebElement(By.xpath("//h2[text()='Sección Adulto/Tutor Responsable']")).getText());
    }

    @BeforeEach
    public void preConditions() throws FileNotFoundException {
        baseClass = new BaseClass(driver, wait);
        driver = baseClass.conexionDriver(browser, urlDriver, property);
        driver.get("https://buenosaires.gob.ar/inicio/");
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
