package com.marcelo.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilitário para captura de tela (evidência de testes).
 */
public class ScreenshotHelper {

    private static final String PASTA_SCREENSHOTS = "target/screenshots/";

    public static void capturar(WebDriver driver, String nomeDoTeste) {
        if (driver instanceof TakesScreenshot takesScreenshot) {
            File arquivoOrigem = takesScreenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path pastaDestino = Paths.get(PASTA_SCREENSHOTS);
            Path arquivoDestino = pastaDestino.resolve(nomeDoTeste + "_" + timestamp + ".png");

            try {
                Files.createDirectories(pastaDestino);
                Files.copy(arquivoOrigem.toPath(), arquivoDestino);
                System.out.println("📸 Evidência salva em: " + arquivoDestino.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Erro ao salvar screenshot: " + e.getMessage());
            }
        }
    }
}
