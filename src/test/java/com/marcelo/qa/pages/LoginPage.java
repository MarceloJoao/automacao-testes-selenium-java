package com.marcelo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Padrão Page Object Model (POM) com Espera Explícita (WebDriverWait).
 * Encapsula os elementos da interface e as interações com a página de Login.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Seletores (Locators) dos elementos da página
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.cssSelector("button[type='submit']");
    private final By flashAlert    = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void abrirPagina() {
        driver.get("https://the-internet.herokuapp.com/login");
        // Garante que a página e o campo estejam prontos antes de qualquer ação
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
    }

    public void preencherUsuario(String usuario) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(senha);
    }

    public void clicarEntrar() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
    }

    /**
     * Ação de alto nível que combina o fluxo completo de login.
     */
    public void realizarLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarEntrar();
    }

    /**
     * Aguarda o alerta ficar visível e retorna o texto exibido na tela.
     */
    public String obterMensagemAlerta() {
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(flashAlert));
        return alert.getText();
    }
}
