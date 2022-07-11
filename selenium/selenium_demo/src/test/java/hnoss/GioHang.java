package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.text.Normalizer;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Test
public class GioHang {
    public static void GH(){
        WebDriver driver = driverFactory.getChromeDriver();

        try{
            //1. đi đến trang https://hnossfashion.com/
            driver.get("https://hnossfashion.com/");

            //2. đi đến trang "áo"
            driver.findElement(By.xpath("//div[2]/nav[1]/ul[1]/li[2]/a[1]")).click();
            Thread.sleep(2000);

            //3. chọn sản phẩm
            driver.findElement(By.xpath("//div[1]/div[1]/div[3]/div[1]/h3[1]/a[1]")).click();
            Thread.sleep(3000);

            //4. chọn màu
            driver.findElement(By.xpath("//*[@id=\"variant-swatch-0\"]/div[3]/div[1]/label/span")).click();

            //5. chọn size
            driver.findElement(By.xpath("//*[@id=\"variant-swatch-1\"]/div[2]/div[1]/label")).click();

            //6. click mua ngay
            driver.findElement(By.xpath("//form[1]/div[3]/div[2]/button[1]")).click();
            Thread.sleep(2000);

            //7. xem giỏ hàng
            driver.findElement(By.xpath("//*[@id=\"form-minicart\"]/div/a")).click();
            Thread.sleep(2000);

            //8. sửa số lượng
            driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div[1]/input[1]")).clear();
            driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div[1]/input[1]")).sendKeys("3");
            String soluongsanpham = driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div[1]/input[1]")).getText();

            //9. click cập nhật giỏ hàng
            driver.findElement(By.id("update-cart")).click();
            Thread.sleep(2000);

            //10. xác nhận số lượng được cập nhật
            String soluongsanphamSau = driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div[1]/input[1]")).getText();
            assertEquals(soluongsanpham,soluongsanphamSau);

            //11. xoá sản phẩm
            driver.findElement(By.xpath("//div[1]/div[2]/div[4]/a[1]")).click();
            Thread.sleep(2000);

            //12. xác nhận sản phẩm được xoá và giỏ hàng rỗng
            String giohangrong = driver.findElement(By.xpath("//form[1]/div[1]/h2[1]")).getText();

            assertEquals("Bạn đang có trong giỏ hàng",giohangrong);
            Thread.sleep(2000);


        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();

    }

}


