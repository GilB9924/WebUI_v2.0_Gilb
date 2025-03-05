package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {



    @Test(groups = {"Regression","Master"})
    public void verifyAccountLogin()  {
        logger.info("*************** TC001_AccountRegistrationTest ****************");
        try{
        HomePage hp=new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on MyAccount Link..");
        hp.clickRegister();
        logger.info("Clicked on Register Link..");
        RegistrationPage regpage= new RegistrationPage(driver);
        logger.info("Providing customer details..");
        regpage.SetFirstName(generateRandomString().toUpperCase());
        regpage.setTxtLastName(generateRandomString().toUpperCase());
        regpage.setEmail(generateRandomString()+"@gmail.com");
        regpage.setTxtPassword(alphaNumeric());
        regpage.setChkPolicy();
//        Thread.sleep(1000);
        regpage.setSelectContinue();
        String comfirmationMessage= regpage.getConfirmationMsg();
        logger.info("Validating Expected Message");
        Assert.assertEquals(comfirmationMessage,"Your Account Has Been Created!");
        }
        catch (Exception e){

            logger.error("Test failed");
            logger.debug("Debug logs");
            Assert.fail();
        }
    }



}
