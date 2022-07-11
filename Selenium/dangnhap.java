package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class dangnhap {
    public static void dangky() {
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //vào trang web
            driver.get("https://hnossfashion.com/account/login");
            // gamil
            driver.findElement(By.id("customer_email")).click();
            Thread.sleep(5000);
            // gamil
            driver.findElement(By.id("customer_email")).click();
            Thread.sleep(5000);
            //pass
            driver.findElement(By.xpath("customer_password")).clear();
            driver.findElement(By.xpath("customer_password")).sendKeys("12333");
            // dang nhap//div[3]/div[1]/input[1]"
            driver.findElement(By.id("//div[3]/div[1]/input[1]")).click();
            Thread.sleep(5000);
}catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }

}
