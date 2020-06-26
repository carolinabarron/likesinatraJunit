package com.likesinatra;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

public class StepDefs {

    public WebDriver driver;
    public WebDriverWait wait;
    public WebElement likeBtn;
    public int numLikes;


    @Before
    public void before() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        String driverPath = System.getProperty("user.dir") + "/src/test/java/selenium/webDrivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().setSize(new Dimension(900, 550));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDownTest() {
        driver.quit();
    }

    @Given("I navigate to Sinatra song page")
    public void iNavigateToSinatraSongPage() {
        navegarHomePage();
        navegarSongsPage();
        navegarPrimerCancion();
    }

    @When("I click on like song")
    public void iClickOnLikeSong() {

        likeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[value='Like']")));
        String songsLikes = driver.findElement(By.cssSelector("div p")).getText();
        numLikes = Integer.valueOf(songsLikes.replaceAll("[^0-9]", ""));
        System.out.println("La cantidad de likes de la canción son: " + numLikes);
        likeBtn.click();
    }

    @Then("song like counter is incremented")
    public void songLikeCounterIsIncremented() {

        String likeAgregado = driver.findElement(By.cssSelector("div p")).getText();
        int numLikesAgregado = Integer.valueOf(likeAgregado.replaceAll("[^0-9]", ""));

        if (numLikesAgregado > numLikes) {
            System.out.println("Tu like a la canción fue agregado");
            System.out.println("La cantidad de likes de la canción son: " + numLikesAgregado);
        } else {
            System.out.println("Número de likes a la canción no quedó actualizado");
        }
    }


    public void navegarHomePage() {
        driver.get("https://evening-bastion-49392.herokuapp.com");
        //Validar la página principal
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section p"))).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[src='/images/sinatra.jpg']"))).isDisplayed());
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href='/songs']"))).isDisplayed());
    }

    public void navegarSongsPage() {
        driver.findElement(By.cssSelector("[href='/songs']")).click();
        assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section h1"))).getText(), "Songs");
        System.out.println("La página Songs se cargó correctamente");
    }

    public void navegarPrimerCancion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[@id='songs']/li/a)[1]"))).click();
    }
}
