package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;
import static org.testng.AssertJUnit.assertEquals;

@Test
public class thanhtoan {
    public static void thanhtoan() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
            driver.manage().timeouts().pageLoadTimeout(100, SECONDS);
            //1. đi đến trang https://hnossfashion.com/
            driver.get("https://hnossfashion.com/");

            //2. đi đến trang "áo"
            driver.findElement(By.xpath("//div[2]/nav[1]/ul[1]/li[2]/a[1]")).click();
            Thread.sleep(2000);
            // click vào sản phẩm
            driver.findElement(By.xpath("//div[1]/div[1]/div[3]/div[1]/h3[1]/a[1]")).click();
            Thread.sleep(1000);

            //size và mau sản phẩm
            driver.findElement(By.xpath("//*[@id=\"variant-swatch-0\"]/div[3]/div[1]/label/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"variant-swatch-1\"]/div[2]/div[1]/label")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("add-to-cart")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//tr[4]/td[2]/a[1]")).click();
            Thread.sleep(2000);
            //nhập ten
            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")).clear();
            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")).sendKeys("Nguyen Huy");
            Thread.sleep(1000);
            //nhap email
            driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[1]/input[1]")).clear();
            driver.findElement(By.id("checkout_user_email")).sendKeys("Nguyen12@gamil.com");
            Thread.sleep(1000);
            //nhap so dien thoai
            driver.findElement(By.id("billing_address_phone")).clear();
            driver.findElement(By.id("billing_address_phone")).sendKeys("1234566920");
            Thread.sleep(1000);
            //nhap so dia chi
            driver.findElement(By.id("billing_address_address1")).clear();
            driver.findElement(By.id("billing_address_address1")).sendKeys("1921/tokyo/moto");
            Thread.sleep(1000);
            //vi tri tỉnh
            Select tinh = new Select(driver.findElement(By.id("customer_shipping_province")));
            tinh.selectByVisibleText("Hồ Chí Minh");
            Thread.sleep(5000);
            //vi tri quận
            Select quan = new Select(driver.findElement(By.id("customer_shipping_district")));
            quan.selectByVisibleText("Quận 7");
            Thread.sleep(5000);
            //vi tri quận
            Select phuong = new Select(driver.findElement(By.id("customer_shipping_ward")));
            phuong.selectByVisibleText("Phường Phú Mỹ");
            Thread.sleep(5000);
            // phuong thức thanh toan
            driver.findElement(By.id("payment_method_id_1002920800")).click();
            Thread.sleep(5000);
            // hoan tất đơn hàng
            driver.findElement(By.xpath("//*[@id=\"form_next_step\"]/button")).click();
            Thread.sleep(5000);
            //thanh toan thanh công
            String success = driver.findElement(By.xpath("//div[2]/div[1]/div[1]/div[1]/div[1]/h2[1]")).getText();
            assertEquals("Đặt hàng thành công",success);
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }

}
