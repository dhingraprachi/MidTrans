package Base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class basic {

    static WebDriver driver;
    public static Properties prop = new Properties();

    public static WebDriver initialiseDriver() throws IOException {
        String path= System.getProperty("user.dir")+"/src/main/resources/data.properties";
        FileInputStream fis = new FileInputStream(path);
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        String driverFile= System.getProperty("user.dir")+"/src/main/resources/chromedriver";

        if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", driverFile);
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
           System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/main/resources/chromedriver");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public String captureScreenshot(WebDriver driver, String tcName) throws IOException {
        TakesScreenshot ts= (TakesScreenshot)driver;
        File src =ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir")+"/src/main/resources/Sreenshots/"+tcName+".jpg";
        FileUtils.copyFile(src,new File(dest));
        return dest;
    }
}
