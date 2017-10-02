import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Simple {

    public boolean CreatePostMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.babyblog.ru/");
        //Клик на войти
        driver.findElement(By.id("enter")).click();
        //Заполнение логина и пароля
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Форма загружается нужен implicitlyWait
        WebElement loginField = driver.findElement(By.cssSelector("input[name= 'login']"));
        loginField.sendKeys("cqnpqu@hi2.in");
        WebElement passwordField = driver.findElement(By.cssSelector("input[name= 'password']"));
        passwordField.sendKeys("1111");
        //Кнопка войти
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
        //Проверяем
        //TODO: Логика првоерки
        //div.blog-text.user-used
        return true;
    }
}