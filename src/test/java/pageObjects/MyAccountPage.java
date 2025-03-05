package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;

public class MyAccountPage extends BasePage{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }




    @FindBy(xpath="//h1[normalize-space()='My Account']")
    WebElement myAccountMessage;


    @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement btnLogout;

    public  boolean isMyAccountExist(){
        try {
            return (myAccountMessage.isDisplayed());
        }
        catch (Exception e){
            return false;
        }

    }

    public void clkOnLogoutB()
    {
        Actions action1= new Actions(driver);
        action1.moveToElement(btnLogout).click().perform();
    }
}
