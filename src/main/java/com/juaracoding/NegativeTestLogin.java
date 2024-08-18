package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// Kelas ini saya buat terpisah untuk mencoba uji negatif
// apakah bisa masuk ke halaman produk tanpa login atau tidak
public class NegativeTestLogin {
    public static void main(String[] args) {
        // Mengatur WebDriver
        System.setProperty("webdriver.chrome.driver", "C://chromedriver-win64//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Buka URL Browser");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,300)");

        // Web scrapping (Uji Negatif)
        delay(3);
        WebElement brandingPerusahaan = driver.findElement(By.xpath("//div[@class='app_logo']"));
        System.out.println(brandingPerusahaan.isDisplayed());

        // Tutup browser
        delay(3);
        driver.quit(); // Pastikan browser ditutup dengan benar
        System.out.println("Tutup Browser");
    }

    // Metode utilitas untuk menambahkan jeda
    public static void delay(long detik){
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
