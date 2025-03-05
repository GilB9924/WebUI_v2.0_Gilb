package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmailAdr;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement txtpassword;

    @FindBy(xpath="//button[normalize-space()='Login']")
    WebElement btnLogin;

    public void setTxtEmailAdr(String eMail){
        txtEmailAdr.sendKeys(eMail);

    }

    public void setTxPpassword(String password) {
        txtpassword.sendKeys(password);

    }

    public void setBtnLogin(){
        btnLogin.click();
    }

}
