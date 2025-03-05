package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"sanity","Regression","Master"})
    @Parameters({"os","browser"})

    public void setUp(String os, String br) throws IOException {

//        loading config.properties file
        FileReader file= new FileReader("./src/main/resources/config.properties");
        p= new Properties();
        p.load(file);
//setting up a grid environment

        if(p.getProperty("execution_environment").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();
            if(os.equalsIgnoreCase("Windows")){
                capabilities.setPlatform(Platform.WIN10);
            }
            else if(os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else {
                System.out.println("No matching os in xml file");return;
            }
//            browser for remote environment taking from xml browser
            switch (br.toLowerCase()){
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                default: System.out.println(" No Matching browser"); return;

        }
//            driver for remote execution
            driver= new RemoteWebDriver(new URL("http://DESKTOP-0QL6CCM:4444"),capabilities);

        }
        if(p.getProperty("execution_environment").equalsIgnoreCase("local")){

            switch (br.toLowerCase()){
                case "chrome": driver= new ChromeDriver(); break;
                case "edge": driver=new EdgeDriver(); break;
                default: System.out.println(" invalid browser"); return;

            }
        }
        logger= LogManager.getLogger(this.getClass());


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL1")); //getting url from the properties file(config.properties)

    }

    @AfterClass(groups = {"sanity","Regression","Master"})
    public void tearDown(){

        driver.close();

    }
    public String generateRandomString() {

        String generatedREmail = RandomStringUtils.randomAlphabetic(5);
        return generatedREmail;
    }

    public String generateRandomNumber() {

        String generatedRNumber = RandomStringUtils.randomNumeric(10);
        return generatedRNumber;
    }

    public String alphaNumeric() {
        String pass = RandomStringUtils.randomAlphabetic(3);

        String word = RandomStringUtils.randomNumeric(3);
        return (pass+word);
    }

    public String captureScreen(String tname){
        String timeStamp= new SimpleDateFormat("yyyyMMddhhss").format(new Date());

        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath= System.getProperty("user.dir")+"//screenshots//"+ tname+"_"+ timeStamp+".png";
        File targetFile= new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }
}
