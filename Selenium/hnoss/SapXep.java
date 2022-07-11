package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.junit.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

@Test
public class SapXep {
    public static void SX(){
        WebDriver driver = driverFactory.getChromeDriver();

        try{
            //1. đi đến trang https://hnossfashion.com/
            driver.get("https://hnossfashion.com/");

            //2. đi đến trang "áo"
            driver.findElement(By.xpath("//div[2]/nav[1]/ul[1]/li[2]/a[1]")).click();

            //3. chọn sắp xếp tăng dần
            Select sapxep = new Select(driver.findElement(By.xpath("//select[1]")));
            sapxep.selectByVisibleText("Giá: Tăng dần");
            //4. kiểm tra sản phẩm đã sắp xếp
            String item1 =  driver.findElement(By.xpath("//div[1]/div[1]/div[3]/div[1]/div[1]/p[1]")).getText();
            String item2 =  driver.findElement(By.xpath("//div[2]/div[1]/div[3]/div[1]/div[1]/p[1]")).getText();
            String num1 =  item1.split(",",2)[0];
            String num2 =  item2.split(",",2)[0];
            if(Integer.parseInt(num1)>Integer.parseInt(num2)){
                assertEquals(num1,num2);
            }
            Thread.sleep(5000);
            //5. đi đến trang "Hàng mới"
            driver.findElement(By.xpath("//div[2]/nav[1]/ul[1]/li[1]/a[1]")).click();
            //6. chọn sắp xếp Giảm dần
            sapxep = new Select(driver.findElement(By.xpath("//body/div[2]/main[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]/select[1]")));
            sapxep.selectByVisibleText("Giá: Giảm dần");
            //7. kiểm tra sản phẩm đã sắp xếp
            item1 =  driver.findElement(By.xpath("//div[1]/div[1]/div[3]/div[1]/div[1]/p[1]")).getText();
            item2 =  driver.findElement(By.xpath("//div[2]/div[1]/div[3]/div[1]/div[1]/p[1]")).getText();
            num1 =  item1.split(",",2)[0];
            num2 =  item2.split(",",2)[0];
            if(Integer.parseInt(num1)<Integer.parseInt(num2)){
                assertEquals(num1,num2);
            }
            Thread.sleep(5000);


        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();

    }
}


