package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

public class Utilities {

    private static WebDriver driver;

    public static ExtentReports getReportObject(){
        ExtentReports report;

        String path = System.getProperty("user.dir")+"/src/main/resources/Reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Test Execution Report");
        reporter.config().setDocumentTitle("Results");

        report= new ExtentReports();
        report.attachReporter(reporter);
        report.setSystemInfo("Tester","PD");
        return report;
    }
}
