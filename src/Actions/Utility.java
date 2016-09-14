/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import runThrghTestNG.BaseClass;
import Util.TestConfig;
import Util.monitoringMail;
import org.openqa.selenium.Keys;

/**
 *
 * @author Vikas.Gahlaut
 */
public class Utility extends BaseClass {

    public static String imageResult = null;
    monitoringMail mail = new monitoringMail();
    ElementLocator caller = new ElementLocator();

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public boolean elementWithTextNotPresent(String text) //this function asserts that http code is not displayed...
    {

        List<WebElement> Elements = driver.findElements(By.xpath("//*[contains(text()," + "'" + text + "'" + ")]"));

        int elementNo = Elements.size();
        boolean act = (elementNo == 0);
        return (act);
    }

    public void StaleElementHandleByID(String elementXpath) {
        int count = 0;
        while (count < 4) {
            try {
                WebElement yourSlipperyElement = driver.findElement(By.xpath(elementXpath));
                yourSlipperyElement.click();
            } catch (StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
                count = count + 1;
            }
            count = count + 4;
        }
    }

    public boolean waitForElementToBePresent(By by, int waitInMilliSeconds) throws Exception {
        // WebDriver driver = getDriver();
        int wait = waitInMilliSeconds;
        int iterations = (wait / 250);
        long startmilliSec = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            if ((System.currentTimeMillis() - startmilliSec) > wait) {
                return false;
            }
            List<WebElement> elements = driver.findElements(by);
            if (elements != null && elements.size() > 0) {
                return true;
            }
            Thread.sleep(250);
        }
        return false;
    }

    public String verifyImage(WebElement image) {
        Boolean imageLoaded1 = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);

        if (!imageLoaded1) {
            System.out.println("Image is not present or it is broken.");
            imageResult = "Fail";
        } else {
            System.out.println("Got the image.It is not broken");
            imageResult = "Pass";
        }

        return (imageResult);

    }

    public void failure(Throwable t) throws IOException, MessagingException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\src\\Error temp\\Automated Error Screen.jpg"));
        mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody + t.getMessage(), TestConfig.attachmentPath, TestConfig.attachmentName);
        Assert.fail("Failed due to reason mentioned in logs", t);

    }

    public void deleteAllText(WebElement fieldDeleted) throws InterruptedException {

        String selectAll = Keys.chord(Keys.CONTROL, "a");
        fieldDeleted.sendKeys(selectAll);
        Thread.sleep(500);
        fieldDeleted.sendKeys(Keys.DELETE);
        Thread.sleep(1000);
    }

}
