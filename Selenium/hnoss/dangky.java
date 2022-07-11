package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.lang.*;

import static java.util.concurrent.TimeUnit.SECONDS;

@Test
public class dangky {
    public static void dangky() {
        WebDriver driver = driverFactory.getChromeDriver();

        try{

            //vào trang web
            driver.get("https://hnossfashion.com/account/login");
            Thread.sleep(5000);

            // vao dang ky
            driver.findElement(By.xpath("//div[2]/a[2]")).click();
            Thread.sleep(5000);
            //nhap ho
            driver.findElement(By.id("last_name")).clear();
            driver.findElement(By.id("last_name")).sendKeys("Nguyen");
            //nhap ten
            driver.findElement(By.id("first_name")).clear();
            driver.findElement(By.id("first_name")).sendKeys("lam");
            //nhap email
            var random = String.valueOf(Math.floor(Math.random()*1000));

            driver.findElement(By.xpath("//div[2]/div[1]/form[1]/div[2]/input[1]")).clear();
            driver.findElement(By.xpath("//div[2]/div[1]/form[1]/div[2]/input[1]")).sendKeys("lam"+random+"@gmail.com");
            //nhap mk
            driver.findElement(By.xpath("//div[5]/div[1]/input[1]")).clear();
            driver.findElement(By.xpath("//div[5]/div[1]/input[1]")).sendKeys("123");
            //
            driver.findElement(By.id("//div[5]/div[1]/input[1]")).click();
            Thread.sleep(5000);
        }catch (Exception e) {
            e.printStackTrace();
        }
//        driver.close();
//        driver.quit();

    }

}
