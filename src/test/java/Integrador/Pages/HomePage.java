package Integrador.Pages;

import Integrador.Utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BaseClass {
    By byTramitesYServicios = By.xpath("//a[@href='/inicio/tramites-y-servicios/45']");//a[text()='Crear cuenta'
    By byBibliotecas = By.xpath("//a[@href='/tramites-y-servicios/49']");
    By byAsosiarse = By.xpath("//h3[text()='Asociarse a la red de Bibliotecas públicas de la Ciudad']");
    By byLinkRegister = By.xpath("//a[@href='https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711']");
    By byBuscador = By.xpath("//input[@id='search-home']");
    By byBuscar = By.xpath("//button[text()='Buscar']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void goToRegisterPage() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(byTramitesYServicios)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byBibliotecas)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(byAsosiarse)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byLinkRegister)).click();
    }

    public void SearchAnything() throws InterruptedException{
        wait.until(ExpectedConditions.presenceOfElementLocated(byBuscador)).sendKeys("Cualquier cosa");
        click(buscarWebElement(byBuscar));
    }
}

