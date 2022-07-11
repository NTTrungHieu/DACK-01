package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

@Test
public class khuyenmai {
    public static void khuyenmai() {
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //vào trang web
            driver.get("https://hnossfashion.com/collections/ao?itm_source=homepage&itm_medium=menu&itm_campaign=normal&itm_content=ao");
            //dong gia 99k
           driver.findElement(By.xpath("//div[2]/nav[1]/ul[1]/li[7]/a[1]")).click();
           Thread.sleep(5000);
            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/button[1]/span[1]")).click();
            Thread.sleep(5000);


}catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }

}
