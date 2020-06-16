package com.astrazeneca;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LikeSinatraParent {

    public WebDriver driver;
    public WebDriverWait wait;
    public WebElement likeBtn;
    public int numLikes;

    public  void navegarSitio(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }



    public  void validarHomePage() {

       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section p")));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[src='/images/sinatra.jpg']")));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href='/songs']")));

    }

    public  void navegarSongsPage(String currentUrl) {
        driver.findElement(By.cssSelector("[href='/songs']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href='/songs/new']")));

        if(currentUrl.equals(driver.getCurrentUrl())){
            System.out.println("La página Songs se cargó correctamente");
        }
        else {
            System.out.println("No se cargó correctamente la página Songs");
            cerrarBrowser();
            System.exit(-1);
        }

     }

    public void navegarPrimerCancion() {
       driver.findElement(By.cssSelector("[href='/songs/1']")).click();


        likeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[value='Like']")));
        String songsLikes = driver.findElement(By.cssSelector("div p")).getText();
        numLikes = Integer.valueOf(songsLikes.replaceAll("[^0-9]", ""));
        System.out.println("La cantidad de likes de la canción son: "+ numLikes);


    }

    public void validarLikeAgregado() {
        likeBtn.click();
        String likeAgregado = driver.findElement(By.cssSelector("div p")).getText();
        int numLikesAgregado = Integer.valueOf(likeAgregado.replaceAll("[^0-9]", ""));

        if(numLikesAgregado>numLikes){
            System.out.println("Tu like a la canción fue agregado");
            System.out.println("La cantidad de likes de la canción son: "+ numLikesAgregado);
        }
        else {
            System.out.println("Número de likes a la canción no quedó actualizado");
        }
    }

    public void cerrarBrowser() {
        driver.quit();
    }
}
