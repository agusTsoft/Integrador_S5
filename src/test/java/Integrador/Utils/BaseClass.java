package Integrador.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseClass(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //acciones genericas wrapper de la libreria de selenium
    public void cargarSitio(String url){
        this.driver.get(url);
    }

    public WebElement buscarWebElement(By localizador){
        return this.driver.findElement(localizador);
    }

    public List<WebElement> buscarElementosWeb(By localizador){
        return this.driver.findElements(localizador);
    }

    public void click(WebElement elemento){
        elemento.click();
    }

    public void esperarXSegundos(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public WebElement esperaExplicita(By localizador, int segundos){
        wait = new WebDriverWait(this.driver, segundos);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    public void agregarTexto(By localizador, String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }
    public void agregarTexto(WebElement elemento, String texto){
        elemento.sendKeys(texto);
    }

    public void maximizarBrowser(){
        this.driver.manage().window().maximize();
    }

    public void seleccionarDDLPorTexto(WebElement elemento, String texto){
        Select select = new Select(elemento);
        select.selectByVisibleText(texto);
    }

    public void irAFrameXElementoWeb(WebElement elemento){
        this.driver.switchTo().frame(elemento);
    }

    public WebDriver conexionDriver(String browser, String ruta, String property){
        if(browser.equalsIgnoreCase("Chrome")){
            System.setProperty(property, ruta);
            this.driver = new ChromeDriver();
        }
        if(browser.equalsIgnoreCase("FireFox")){
            System.setProperty(property, ruta);
            this.driver = new FirefoxDriver();
        }
        return this.driver;
    }

    public boolean estaDesplegado(WebElement elemento){
        try{
            return elemento.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public String obtenerTEXTOaTRIBUTO(WebElement elemento, String texto){
        return elemento.getAttribute(texto);
    }
}
