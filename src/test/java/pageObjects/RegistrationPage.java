package pageObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    Actions action= new Actions(driver);

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }



    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtLastName;

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath="//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath="//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(xpath="//button[normalize-space()='Continue']")
    WebElement selectContinue;


    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confirmationMessage;

    public void SetFirstName(String firstName){
        txtFirstName.sendKeys(firstName);
    }

    public void setTxtLastName(String lastName){
        txtLastName.sendKeys(lastName);


    }
    public void setEmail(String email){
        txtEmail.sendKeys(email);

    }

    public void setTxtPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }

    public void setChkPolicy(){

        action.moveToElement(chkPolicy).click().perform();

    }

    public void setSelectContinue(){
        action.moveToElement(selectContinue).click().perform();
    }

    public String getConfirmationMsg(){
        try {
            return confirmationMessage.getText();
        } catch (Exception e) {
            return (e.getMessage());
        }



        }
    }



