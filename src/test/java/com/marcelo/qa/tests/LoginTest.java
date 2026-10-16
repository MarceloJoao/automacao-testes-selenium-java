package com.marcelo.qa.tests;

import com.marcelo.qa.pages.LoginPage;
import com.marcelo.qa.utils.ScreenshotHelper;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Suíte de Testes Funcionais - Autenticação Web")
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        loginPage.abrirPagina();
    }

    @Test
    @DisplayName("Cenário 1: Login com credenciais válidas (Classe Válida)")
    public void deveAutenticarComSucesso() {
        loginPage.realizarLogin("tomsmith", "SuperSecretPassword!");

        String alerta = loginPage.obterMensagemAlerta();
        ScreenshotHelper.capturar(driver, "cenario_1_sucesso");
        assertTrue(alerta.contains("You logged into a secure area!"),
                "Deveria exibir mensagem de login com sucesso.");
    }

    @Test
    @DisplayName("Cenário 2: Login com senha inválida (Partição de Equivalência)")
    public void deveRecusarLoginComSenhaIncorreta() {
        loginPage.realizarLogin("tomsmith", "senhaErrada123");

        String alerta = loginPage.obterMensagemAlerta();
        ScreenshotHelper.capturar(driver, "cenario_2_senha_invalida");
        assertTrue(alerta.contains("Your password is invalid!"),
                "Deveria alertar sobre senha inválida.");
    }

    @Test
    @DisplayName("Cenário 3: Login com usuário inexistente (Partição de Equivalência)")
    public void deveRecusarLoginComUsuarioInexistente() {
        loginPage.realizarLogin("usuarioInvalido", "SuperSecretPassword!");

        String alerta = loginPage.obterMensagemAlerta();
        ScreenshotHelper.capturar(driver, "cenario_3_usuario_invalido");
        assertTrue(alerta.contains("Your username is invalid!"),
                "Deveria alertar sobre usuário inexistente.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
