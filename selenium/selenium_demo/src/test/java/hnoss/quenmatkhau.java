package hnoss;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class quenmatkhau {
    public static void dangky() {
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //vào trang web
            driver.get("https://hnossfashion.com/account/login");
            // gamil
            Select phuong = new Select(driver.findElement(By.id("recover-email")));
            phuong.selectByVisibleText("Phường Phú Mỹ");
            // giu //div[2]/div[1]/input[1]
            driver.findElement(By.id("//div[2]/div[1]/input[1]")).click();
            Thread.sleep(5000);
}catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }

}
