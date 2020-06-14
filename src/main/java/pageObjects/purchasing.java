package pageObjects;

import Base.basic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class purchasing extends basic {

    By buyNow      = By.xpath("//a[@class='btn buy']");
    By checkout    = By.xpath("//div[@class='cart-checkout']");
    By continueBtn = By.xpath("//a[@class='button-main-content']");
    By ccOption    = By.xpath("//div[@class='list-title text-actionable-bold'][1]");
    By cardNumber  = By.xpath("//input[@name='cardnumber']");
    By expiry      = By.xpath("//input[@placeholder='MM / YY']") ;
    By cvv         = By.xpath("//input[@placeholder='123']");
    By payNow      = By.xpath("//div[@class='text-button-main']");
    By otp         = By.id("PaRes");
    By payModeHdr  = By.className("text-page-title-content");
    By submit      = By.id("//button[@text()='OK']");


    public WebDriver driver;
    public static Logger Log = LogManager.getLogger(purchasing.class.getName());

    public purchasing() throws IOException {
        this.driver=initialiseDriver();
        getEnvready();
    }

    private void getEnvready() {
            Log.info("Driver invoked successfully");
            driver.get(prop.getProperty("URL"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Log.info("URL Entered :-" + prop.getProperty("URL"));
        }

    public void checkout(String status){
        driver.findElement(buyNow).click();
        Log.info("Buy Now Clicked");
        driver.findElement(checkout).click();
        Log.info("Check Out clicked");
        switchFrameAndPerformActionsOnFrame("snap-midtrans",status);
        driver.switchTo().defaultContent();
        driver.findElement(otp).sendKeys("123");
        driver.findElement(submit).click();
    }

    public void switchFrameAndPerformActionsOnFrame(String frameId,String status){
        driver.switchTo().frame(frameId);
//        Assert.assertEquals(driver.findElement(totalAmount),driver.findElement(prodAmt),"The total value is equal to the product value");
        driver.findElement(continueBtn).click();
        if(status.equalsIgnoreCase("Success"))
        enterCardDetailsforSucccessful();
        else
            enterCardDetailsforFail();
        driver.findElement(payNow).click();
    }

    public void enterCardDetailsforSucccessful() {
        driver.findElement(ccOption).click();
        Assert.assertTrue(driver.findElement(payModeHdr).getText().contains("Credit Card"));
        driver.findElement(cardNumber).sendKeys("4811 1111 1111 1114");
        driver.findElement(expiry).sendKeys("0220");
        driver.findElement(cvv).sendKeys("123");
    }

    public void enterCardDetailsforFail() {
        driver.findElement(ccOption).click();
        driver.findElement(cardNumber).sendKeys("4911 1111 1111 1113");
        driver.findElement(expiry).sendKeys("0220");
        driver.findElement(cvv).sendKeys("123");
    }

    @AfterTest
    public void closeDriver(){
        driver.close();
    }
}

