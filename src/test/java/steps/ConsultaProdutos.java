package steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ConsultaProdutos {
    String url;
    WebDriver driver;
    static String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        // variavel foto do tipo arquivo que recebe o resultado do tipo selenium da imagem atual
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // especializacao do Selenium tirar foto, fotografia com saida para um arquivo
        FileUtils.copyFile(foto,new File(pastaPrint + nomePrint + ".png")); // grava o print da tela no arquivo
    }

    @Before
    public void iniciar(){
        url = "https://www.cobasi.com.br";
        System.setProperty("webdriver.chrome.driver","drivers/chrome/100/chromedriver100.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
        System.out.println("Passo 0 - Preparou o setup");
    }

    @After
    public void finalizar(){
        driver.quit();
        System.out.println("Passo 6 - Fechou o Browser");
    }

    @Dado("^que acesso o site da Cobasi$")
    public void que_acesso_o_site_da_Cobasi() throws IOException {
        driver.get(url);
        System.out.println("Passo 1 - Acessou o Site Cobasi");
        tirarPrint("Print 1 - Acessou o Site Cobasi");
    }

    @Quando("^procuro por \"([^\"]*)\" e clico na Lupa$")
    public void procuro_por_e_clico_na_Lupa(String produto) throws InterruptedException, IOException {
        //driver.findElement(By.id("lgpd__button")).click();
        driver.findElement(By.cssSelector("input.fulltext-search-box.ui-autocomplete-input.neemu-search-field")).sendKeys(produto);
        driver.findElement(By.xpath("//header/div[1]/div[1]/div[3]/div[1]/fieldset[1]/input[3]")).click();
        Thread.sleep(1000);
        System.out.println("Passo 2 - Pesquisou por " + produto );
        tirarPrint("Print 2 - Pesquisou por " + produto);
    }

    @Entao("^exibe as opcoes relacionadas ao \"([^\"]*)\"$")
    public void exibe_as_opcoes_relacionadas_ao(String produto) throws IOException {
        assertEquals(produto + " - Cobasi", driver.getTitle());

        System.out.println("Passo 3 - Exibiu " + produto + " pesquisado");
        tirarPrint("Print 3 - Exibiu " + produto + " pesquisado");
    }

    @Quando("^seleciono a \"([^\"]*)\" da lista$")
    public void seleciono_a_da_lista(String descricaoProduto) throws IOException {
        driver.findElement(By.xpath("//a[contains(text(),'" + descricaoProduto + "')]")).click();

        System.out.println("Passo 4 - Selecionou " + descricaoProduto);
        tirarPrint("Print 4 - Selecionou " + descricaoProduto);
    }

    @Entao("^verifico a \"([^\"]*)\" a \"([^\"]*)\" com o \"([^\"]*)\"$")
    public void verificoAAComO(String marca, String opcao, String preco) throws IOException {
        assertEquals(marca, driver.findElement(By.cssSelector("a.styles__BrandLink-sc-1rue5eb-14.iEjUzW")).getText());
        assertEquals(opcao, driver.findElement(By.cssSelector("span.MuiChip-label")).getText());
        assertEquals(preco, driver.findElement(By.cssSelector("div.styles__WrapperProductPrice-sc-1nhsrae-7.gkMznb")).getText());

        System.out.println("Passo 5 - Verificou marca e valor");
        tirarPrint("Print 5 - Verificou marca e valor");
    }
}