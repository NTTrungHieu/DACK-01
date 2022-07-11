package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.text.Normalizer;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Test
public class TimKiem {
    public static void TK(){
        WebDriver driver = driverFactory.getChromeDriver();

        try{
            //1. đi đến trang https://hnossfashion.com/
            driver.get("https://hnossfashion.com/");

            //2. chọn nút tìm kiếm
            driver.findElement(By.xpath("//span[1]/a[1]/span[1]/img[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"site-search\"]/div/form/input[2]")).sendKeys("áo kiểu xếp sườn");
            driver.findElement(By.xpath("//*[@id=\"site-search\"]/div/form/button")).click();
            Thread.sleep(5000);
            //3. so sánh từ khoá với sản phẩm
            String text = driver.findElement(By.xpath("//div[1]/div[2]/div[1]/p[1]/strong[1]")).getText();
            text = deAccent(text.toUpperCase());
            String[] item = driver.findElement(By.xpath("//a[1]/div[2]/h3[1]")).getText().split(" ");
            int countKey = 0;
            for(int i=0; i< item.length;i++){
                if(text.contains(deAccent(item[i]))) countKey++;
                System.out.println(deAccent(item[i]));
                System.out.println(text);
            }
            Thread.sleep(2000);

            assertTrue(countKey>0);
            //4. tìm kiếm chuỗi không tồn tại
            //5. chọn nút tìm kiếm
            driver.findElement(By.xpath("//span[1]/a[1]/span[1]/img[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"site-search\"]/div/form/input[2]")).sendKeys("oa li ba");
            driver.findElement(By.xpath("//*[@id=\"site-search\"]/div/form/button")).click();
            Thread.sleep(2000);
            //6. so sánh từ khoá với sản phẩm
            text = driver.findElement(By.xpath("//div[1]/div[2]/div[1]/p[1]/strong[1]")).getText();
            text = deAccent(text.toUpperCase());
            item = driver.findElement(By.xpath("//a[1]/div[2]/h3[1]")).getText().split(" ");
            countKey = 0;
            for(int i=0; i< item.length;i++){
                if(text.contains(deAccent(item[i]))) countKey++;
            }
            Thread.sleep(2000);
            String text1 = driver.findElement(By.xpath("//div[2]/div[1]/div[1]/h2[1]")).getText();
            assertEquals("Không tìm thấy nội dung bạn yêu cầu",text1);

            Thread.sleep(2000);


        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();

    }
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}


