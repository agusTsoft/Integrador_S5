package Integrador.Pages;

import Integrador.Utils.BaseClass;
import Integrador.Utils.DataDriven;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class HomePage extends BaseClass {
    By byTramitesYServicios = By.xpath("//a[@href='/inicio/tramites-y-servicios/45']");//a[text()='Crear cuenta'
    By byBibliotecas = By.xpath("//a[@href='/tramites-y-servicios/49']");
    By byAsosiarse = By.xpath("//h3[text()='Asociarse a la red de Bibliotecas públicas de la Ciudad']");
    By byLinkRegister = By.xpath("//a[@href='https://portalinscripciones.scp.buenosaires.gob.ar/#/actividad/8711']");
    By byInscriptionBtn = By.xpath("//button[text()='Inscribite']");
    By byName = By.xpath("//input[@id='field-11453']");
    By byLastName = By.xpath("//input[@id='field-11454']");
    By byDocType = By.xpath("//select[@id='field-11455']");
    By byDocNum = By.xpath("//input[@id='field-11463']");
    By byGender = By.xpath("//select[@id='field-11456']");
    By byBirthDate = By.xpath("//input[@id='field-11457']");
    By byEmailField = By.xpath("//input[@id='field-11459']");
    By byConfirm = By.xpath("//input[@id='field-11458']");
    By byProvince = By.xpath("//select[@id='field-11460']");
    By byLocality = By.xpath("//select[@id='field-11462']");
    By byAdress = By.xpath("//input[@id='field-11461']");
    By byPhoneNumer = By.xpath("//input[@id='field-11465']");
    By byNationality = By.xpath("//select[@id='field-11464']");
    By byComuna = By.xpath("//input[@id='field-11466']");
    By bySendBtn = By.xpath("//button[text()='Enviar']");


    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void register() throws InterruptedException {
        DataDriven colector = new DataDriven();
        ArrayList<String> registerData = colector.conexionDataSet("CP001");

        wait.until(ExpectedConditions.presenceOfElementLocated(byTramitesYServicios)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byBibliotecas)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(byAsosiarse)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byLinkRegister)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byInscriptionBtn)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(byName));
        if (buscarWebElement(byName).isDisplayed()){
            buscarWebElement(byName).sendKeys(registerData.get(1));
            buscarWebElement(byLastName).sendKeys(registerData.get(2));
            seleccionarDDLPorTexto(buscarWebElement(byDocType), registerData.get(3));
            buscarWebElement(byDocNum).sendKeys(registerData.get(4));
            seleccionarDDLPorTexto(buscarWebElement(byGender), registerData.get(5));
            buscarWebElement(byBirthDate).sendKeys(registerData.get(6));
            buscarWebElement(byEmailField).sendKeys(registerData.get(7));
            buscarWebElement(byConfirm).sendKeys(registerData.get(8));
            seleccionarDDLPorTexto(buscarWebElement(byProvince), registerData.get(9));
            buscarWebElement(byLocality).sendKeys(registerData.get(10));
            buscarWebElement(byAdress).sendKeys(registerData.get(11));
            buscarWebElement(byPhoneNumer).sendKeys(registerData.get(12));
            seleccionarDDLPorTexto(buscarWebElement(byNationality), registerData.get(13));
            buscarWebElement(byComuna).sendKeys(registerData.get(14));
            click(buscarWebElement(bySendBtn));
        }
    }

}
