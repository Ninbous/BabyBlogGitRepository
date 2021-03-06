import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Simple {

    public boolean CreatePostMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
        //  System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);


//
//        driver.get(baseUrl);
        driver.get("https://www.babyblog.ru/");

        //Скриншот
//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(scrFile, new File("screen01.png"));
//            System.out.println("Скриншот1!");
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

        //Клик на войти
        driver.findElement(By.id("enter")).click();
        //Заполнение логина и пароля
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Форма загружается нужен implicitlyWait
        WebElement loginField = driver.findElement(By.cssSelector("input[name= 'login']"));

        //   loginField.sendKeys("cqnpqu@hi2.in");

        loginField.sendKeys("cqnpqu@hi2.in");
        WebElement passwordField = driver.findElement(By.cssSelector("input[name= 'password']"));
        passwordField.sendKeys("1111");
        //Кнопка войти
        //Обход капчи гугла
        Actions action1 = new Actions(driver);
        WebElement menu1 = driver.findElement(By.cssSelector("button.r-btn.r-btn__green.r-btn__fill"));
        action1.moveToElement(menu1).build().perform();

        driver.findElement(By.cssSelector("button.r-btn.r-btn__green.r-btn__fill")).click();
        //Наведение мыши на правое верхнее меню
        Actions action = new Actions(driver);
        WebElement menu = driver.findElement(By.id("headerUserMenuJournalLink"));
        action.moveToElement(menu).build().perform(); //Хз зачем .build.perform без него не работает.
        driver.findElement(By.linkText("Написать в дневник")).click(); //Тут просто если текст ссылка можно кликнуть на нее
        //Пишем в заголовке поста
        WebElement titleField = driver.findElement(By.cssSelector("input[name= 'title']"));
        titleField.sendKeys("Заголовок");
        //Пишем в тело поста это iframe поэтому тут сложнее
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe"))); //Выбор айфрейма без айди по css селектору
        WebElement textField = driver.findElement(By.cssSelector("body"));
        textField.sendKeys("Текст");
        //Выходим из айфрейма и жмем на радиобатон "Только я"
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("label[for= 'access_private']")).click();
        //Кликаем опубликовать
        driver.findElement(By.cssSelector("button.btn._b._b_s._b_btn._14.noi.sgn.super.ml15.css-corner-3.fl._18")).click();
        //Скриншот
        File scrFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile2, new File("screen02.png"));
            System.out.println("Скриншот1!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Проверяем
        //TODO: Логика првоерки
        //div.blog-text.user-used

        return true;
    }
}