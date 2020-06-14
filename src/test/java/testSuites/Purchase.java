package testSuites;

import Base.basic;
import org.testng.annotations.Test;
import pageObjects.purchasing;
import java.io.IOException;

public class Purchase extends basic {

    @Test(description = "Verify end to end checkout flow for purchasing “Pillow” using CC- SUCCESSFUL payment")
    public void TC01_Successful_Purchase() throws IOException {
        purchasing purchaser = new purchasing();
        purchaser.checkout("Success");
    }

    @Test(description = "Verify end to end checkout flow for purchasing “Pillow” using CC- Failed payment")
    public void TC02_Failed_Purchase() throws IOException {
        purchasing purchaser = new purchasing();
        purchaser.checkout("Fail");
    }
}
