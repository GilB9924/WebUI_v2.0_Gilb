package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test(groups = {"sanity","Master"})
    public void verify_Login(){
        try {
//        Home Page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
//        Login Page
            LoginPage lg = new LoginPage(driver);
            lg.setTxtEmailAdr(p.getProperty("email"));
            lg.setTxPpassword(p.getProperty("password"));
            lg.setBtnLogin();
            Thread.sleep(2000);
//        My Account Page Object
            MyAccountPage myAccPage = new MyAccountPage(driver);
            boolean msgDisplayedAfterLogin = myAccPage.isMyAccountExist();
            Assert.assertEquals(msgDisplayedAfterLogin, true, "Nott Logged in");
        } catch (Exception e) {
            Assert.fail();
        }

    }

}
