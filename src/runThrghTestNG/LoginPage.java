/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import Actions.Utility;
import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Vikas.Gahlaut
 */
public class LoginPage extends BaseClass {

    Utility util = new Utility();

    @Test(priority = 1, description = "Test for verifying page title.", retryAnalyzer = Retry.class)
    public void verifyTitle() throws IOException, MessagingException {
   
    	String title = driver.getTitle();
    	
        System.out.println("Title of page: " + title);
        try {
            Assert.assertEquals(title, "Dropbox");
        } catch (Throwable t) {
            util.failure(t);
        }
    }
}
