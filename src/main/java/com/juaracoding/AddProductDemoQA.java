package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AddProductDemoQA {
    public static void main(String[] args) {
        // Mengatur WebDriver
        System.setProperty("webdriver.chrome.driver", "C://chromedriver-win64//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Buka URL Browser");

        // Validasi apakah branding perusahaan ditampilkan
        delay(1);
        WebElement brandingPerusahaan = driver.findElement(By.xpath("//div[@class='login_logo']"));
        System.out.println(brandingPerusahaan.isDisplayed());

        // Validasi judul tab
        delay(1);
        String judulTab = driver.getTitle();
        String judulDiharapkan = "Swag Labs";
        System.out.println(judulTab);

        // Custom assert untuk validasi judul tab
        customAssertEquals(judulTab, judulDiharapkan);

        // Skenario: Uji login
        delay(2);
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        delay(2);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        delay(2);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Login berhasil");

        delay(1);
        String teksDashboard = driver.findElement(By.xpath("//span[@class='title']")).getText();
        String diharapkan = "Products";
        System.out.println("Buka Halaman Produk");

        // Custom assert untuk validasi halaman produk (login berhasil)
        customAssertEquals(teksDashboard, diharapkan);

        // Skenario: Uji tambah satu produk ke keranjang
        delay(2);
        WebElement tombolTambahProdukKeKeranjang = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        tombolTambahProdukKeKeranjang.click();
        System.out.println("--Tambah 1 produk ke keranjang--");

        delay(2);
        WebElement lencanaKeranjang = driver.findElement(By.className("shopping_cart_badge"));
        String jumlahItemKeranjang = lencanaKeranjang.getText();
        String jumlahItemDiharapkan = "1";
        customAssertEquals(jumlahItemKeranjang, jumlahItemDiharapkan);

        // Skenario: Uji negatif - validasi jumlah item keranjang yang salah
        delay(1);
        String jumlahItemTidakDiharapkan = "3"; // Gagal jika terdapat 3 item di keranjang setelah menambah 1 produk
        System.out.println("--Apakah terdapat 3 produk di keranjang setelah menambah 1 produk?--");
        customAssertEquals(jumlahItemKeranjang, jumlahItemTidakDiharapkan);

        // Tutup browser
        delay(3);
        driver.quit();
        System.out.println("Tutup Browser");
    }

    // Metode utilitas untuk menambahkan jeda
    public static void delay(int detik) {
        try {
            TimeUnit.SECONDS.sleep(detik);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Metode custom assert untuk pengecekan kesetaraan
    public static void customAssertEquals(String aktual, String diharapkan) {
        if (aktual.equals(diharapkan)) {
            System.out.println("Asersi Berhasil: " + aktual + " sama dengan " + diharapkan);
        } else {
            System.out.println("Asersi Gagal: Diharapkan " + diharapkan + " tapi ditemukan " + aktual);
        }
    }
}
