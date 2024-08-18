package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    public static void main(String[] args) {
        // Setup WebDriver
        System.setProperty("webdriver.chrome.driver", "C://chromedriver-win64//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Browser berhasil dibuka.");

        // Validasi tampilan branding perusahaan
        jeda(3);
        WebElement brandingPerusahaan = driver.findElement(By.xpath("//div[@class='login_logo']"));
        if (brandingPerusahaan.isDisplayed()) {
            System.out.println("Branding perusahaan ditampilkan.");
        }

        // Validasi judul tab
        jeda(1);
        String judulTab = driver.getTitle();
        String judulTabDiharapkan = "Swag Labs";
        System.out.println("Judul tab: " + judulTab);

        // Custom assert untuk validasi judul tab
        customAssertEquals(judulTab, judulTabDiharapkan);

        // Skenario: Uji login
        jeda(2);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        jeda(2);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        jeda(2);
        driver.findElement(By.id("login-button")).click();
        System.out.println("Login berhasil.");

        // Validasi halaman produk terbuka
        jeda(1);
        String teksDashboard = driver.findElement(By.xpath("//span[@class='title']")).getText();
        String teksDashboardDiharapkan = "Products";
        System.out.println("Halaman produk terbuka.");

        // Custom assert untuk validasi halaman produk
        customAssertEquals(teksDashboard, teksDashboardDiharapkan);

        // Tutup browser
        jeda(3);
        driver.quit();
        System.out.println("Browser ditutup.");
    }

    // Metode jeda untuk menghentikan eksekusi sementara
    public static void jeda(long detik) {
        try {
            TimeUnit.SECONDS.sleep(detik);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Metode custom assert untuk pengecekan kesetaraan
    public static void customAssertEquals(String aktual, String diharapkan) {
        System.out.print("Hasil Uji: ");
        if (aktual.equals(diharapkan)) {
            System.out.println("Lulus.");
        } else {
            System.out.println("Gagal. Diharapkan '" + diharapkan + "' tetapi ditemukan '" + aktual + "'.");
        }
    }
}
