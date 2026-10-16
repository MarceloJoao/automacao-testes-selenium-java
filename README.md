# 🧪 Automação de Testes Web com Selenium WebDriver e Java (JUnit 5)

Este projeto implementa uma suíte de testes funcionais automatizados para a interface web de autenticação, aplicando padrões de projeto de mercado e boas práticas de Engenharia de Qualidade de Software (QA).

---

## 🚀 Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Framework de Testes:** JUnit 5 (Jupiter)
- **Automação Web:** Selenium WebDriver 4
- **Gerenciador de Dependências & Build:** Maven
- **Padrão Arquitetural:** Page Object Model (POM)

---

## 🎯 Diferenciais Técnicos e Conceitos de QA Aplicados

1. **Page Object Model (POM):**
   - Desacoplamento completo entre os seletores da página (`LoginPage.java`) e a camada de regras de negócio/validações (`LoginTest.java`).
2. **Estratégia de Sincronização com Espera Explícita (`WebDriverWait`):**
   - Uso de `wait.until(ExpectedConditions.visibilityOfElementLocated(...))` para eliminar testes instáveis (*flaky tests*) sem necessidade de bloqueios fixos de tempo (`Thread.sleep`).
3. **Captura Automática de Evidências (Screenshots):**
   - Implementação da classe `ScreenshotHelper.java` que fotografa automaticamente a tela a cada execução de cenário, salvando com carimbo de data/hora em `target/screenshots/`.
4. **Técnicas de Caixa-Preta (Partição de Equivalência):**
   - **Classe Válida:** Autenticação com credenciais legítimas (`tomsmith`).
   - **Classes Inválidas:** Tentativa com senha incorreta e usuário inexistente, validando os alertas defensivos da aplicação.

---

## 💻 Como Executar

### Pré-requisitos
- JDK 17+ instalado
- Apache Maven configurado no PATH
- Google Chrome instalado

### Execução via Terminal
```bash
# Compilar e executar todos os testes da suíte
mvn test
```

As evidências visuais em imagem (.png) serão geradas na pasta `target/screenshots/`.
