package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Test1 {
    protected static WebDriver driver;

    @BeforeMethod
    public void startOfBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        //open chrome browser
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void userShouldBeAbleToRegisterSuccessfully(){

    // click on register button
    clickOnElement(By.className("ico-register"));

    //select gender
    driver.findElement(By.xpath("//input[@id=\"gender-female\"]")).click();

    // enter firstname
    //driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Autoamtion");
    typeText(By.xpath("//input[@name='FirstName']"),"Pooja");

    //enter lastname
    //driver.findElement(By.id("LastName")).sendKeys("LastNameTest");
    typeText(By.id("LastName"),"Dhameliya");



        //select birthdate
    Select birthday = new Select(driver.findElement(By.name("DateOfBirthDay")));
    birthday.selectByIndex(10);

    //birthMonth
    Select birthMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
    birthMonth.selectByValue("4");

    //birthYear
    Select birthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
    birthYear.selectByVisibleText("1960");

    //enter email
        driver.findElement(By.xpath("//input[@name=\"Email\"]")).sendKeys("pooja21"+randomDate()+"@gmail.com");


        //enter password
    //driver.findElement(By.id("Password")).sendKeys("12345@abc.com");
    typeText(By.id("Password"),"12345@abc");

    //enter confirm password
    //driver.findElement(By.id("ConfirmPassword")).sendKeys("12345@abc.com");
    typeText(By.id("ConfirmPassword"),"12345@abc");

    //enter register button
    //  driver.findElement(By.name("register-button")).click();
    clickOnElement(By.name("register-button"));

    String expectedMessage = "Your registration completed";
    String actualMessage =  driver.findElement(By.xpath("//div[contains(text(),\"Your registration completed\")]")).getText();
    System.out.println("Actual message:" + actualMessage);

    Assert.assertEquals(actualMessage,expectedMessage,"Registration is not working");
}
    @Test
    public void userShouldBeAbleToAddProductInAddToCart()
    {
        //click on computer
        clickOnElement(By.linkText("Computers"));

        //click on Desktop
        clickOnElement(By.linkText("Desktops"));

        //click on add to cart
        clickOnElement(By.xpath("//div/div[1]/div/div[2]/div[3]/div[2]/button[@class=\"button-2 product-box-add-to-cart-button\"]"));

        //select processor
        Select processor = new Select(driver.findElement(By.name("product_attribute_1")));
        processor.selectByIndex(1);

        //select RAM
        Select ram = new Select(driver.findElement(By.name("product_attribute_2")));
        ram.selectByIndex(1);

        //select HDD
        clickOnElement(By.name("product_attribute_3"));

        //select OS
        clickOnElement(By.name("product_attribute_4"));

        //select software
        List<WebElement> elements = driver.findElements(By.xpath("//input[@name=\"product_attribute_5\"]"));
        System.out.println(Integer.toString(elements.size()));
        for (WebElement el:elements )
       {
           el.click();
       }

       //click add to cart button
        clickOnElement(By.xpath("//button[@id=\"add-to-cart-button-1\"]"));

       //open shopping cart
        clickOnElement(By.xpath("//span[@class=\"cart-label\"]"));

        String expectedMessage = "Shopping cart";
        String actualMessage = driver.findElement(By.linkText("Shopping cart")).getText();
        System.out.println("Actual message:" +actualMessage);
        Assert.assertEquals(expectedMessage,actualMessage,"Shopping cart is not working");
    }
    @Test
    public void userShouldBeAbleToSendEmailAsaFriend()
    {
        //click on register
        clickOnElement(By.className("ico-register"));  //

        // enter your  firstname
        typeText(By.xpath("//input[@name='FirstName']"), "Pooja");

        //enter your lastname
        typeText(By.id("LastName"), "Dhameliya");

        typeText(By.id("Email"), "pooja21" + randomDate() + "@gmail.com");

        //Enter your password
        typeText(By.id("Password"), "12345@abc");

        // Enter Confirm Password
        typeText(By.id("ConfirmPassword"), "12345@abc");

        //  Enter register Button
        driver.findElement(By.id("register-button")).click();

        //click on computer
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();

        //click on desk top
        driver.findElement(By.xpath("//img[@alt=\"Picture for category Desktops\"]")).click();

        //build your own compute added to the cart
        clickOnElement(By.xpath("//div[@data-productid=\"1\"]/div/div[3]/div[2]//button[@class=\"button-2 product-box-add-to-cart-button\"]"));

        //cilck on email friend button
        driver.findElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"]")).click();

        //enter your friend email
        driver.findElement(By.xpath("//*[@id=\"FriendEmail\"]")).sendKeys("abc@gmail.com");

        //enter your email address
        driver.findElement(By.xpath("//*[@id=\"YourEmailAddress\"]")).sendKeys(" ");

        // enter personal message
        driver.findElement(By.xpath("//*[@id=\"PersonalMessage\"]")).sendKeys("This computer is a very nice and good price.");

        //click on send email Button
        driver.findElement(By.name("send-email")).click();


        String expectedMessage = "Your message has been sent.";

        String actualMessage = driver.findElement(By.className("result")).getText();

        Assert.assertEquals( actualMessage,expectedMessage, " your message has not been sent");

    }
    @Test
    public void UserShouldBeAbleToChangeCurrency()
    {
        //click on currency
        clickOnElement(By.xpath("//select[@id=\"customerCurrency\"]"));

        //select USDollar
          Select USDollar = new Select(driver.findElement(By.xpath("//select[@name=\"customerCurrency\"]")));

        String expectedMessage  = "$1,200.00";
        String actualMessage = driver.findElement(By.xpath("//span[contains(text(),'$1,200.00')]")).getText();
        System.out.println("Actual message:" +actualMessage);
        Assert.assertEquals(actualMessage,expectedMessage,"price doesnt match");



    }

    @AfterMethod
    public void closeTheBrowser(){
        driver.quit();
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  Utility @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public static String randomDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        //String format=formatter.format(date);
        return formatter.format(date);

    }

    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public static void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public static void driverWaitElementToBeClickable(int time,By by)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public static void driverWaitUrlContains(int time, String fraction)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(fraction));
    }
    public static void driverWaitInvisibilityOfElementLocated(int time,By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public static void driverWait(int time,By by)
    {
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void driverWaitElementToBeSelected(int time, By by)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }
    public static void driVerWaitInvisibilityOf(int time, WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public static void driverWaitAlertIsPresent(int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    public static void driverWaitAttributeToBe(int time,By by, String attribute, String value)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBe(by,attribute,value));
    }
}

