package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {

@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class, groups = "DataDriver")

    public void verifyLoginPage(String userN,String pwd,String exp) throws InterruptedException {
try {
    HomePage hp = new HomePage(driver);
    hp.clickMyAccount();
    hp.clickLogin();
//        Login Page
    LoginPage lg = new LoginPage(driver);
    lg.setTxtEmailAdr(userN);
    lg.setTxPpassword(pwd);
    lg.setBtnLogin();

    Thread.sleep(1000);
//        My Account Page Object
    MyAccountPage myAccPage = new MyAccountPage(driver);
    boolean msgDisplayedAfterLogin = myAccPage.isMyAccountExist();

    if (exp.equalsIgnoreCase("valid")) {

        if (msgDisplayedAfterLogin == true) {
            Thread.sleep(3000);
            myAccPage.clkOnLogoutB();
            Assert.assertTrue(true);
        } else {

            Assert.assertTrue(false);
        }
    } else if (exp.equalsIgnoreCase("invalid")) {
        if (msgDisplayedAfterLogin == true) {

            Thread.sleep(3000);
            myAccPage.clkOnLogoutB();
            Assert.assertTrue(false);
        } else {

            Assert.assertTrue(true);
        }

    }
} catch (Exception e) {
    Assert.fail();
}


    }

}
