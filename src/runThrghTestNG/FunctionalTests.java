/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import Actions.ElementLocator;
import Actions.Login_logout;
import Actions.Utility;
import java.io.IOException;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Vikas.Gahlaut
 */
public class FunctionalTests extends BaseClass {

    BaseClass ob = new BaseClass();
    ElementLocator caller = new ElementLocator();
    Login_logout login = new Login_logout();
    Utility util = new Utility();

    @Test(priority = 1, description = "Test for verifying page title.", retryAnalyzer = Retry.class)
    public void Loginfail() throws IOException, MessagingException {
   
    	login.wrong_login();
    }
}
