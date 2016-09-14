/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import runThrghTestNG.BaseClass;
/**
 *
 * @author Vikas.Gahlaut
 */
public class Login_logout extends BaseClass {

    BaseClass ob = new BaseClass();
    ElementLocator caller = new ElementLocator();

    public void wrong_login() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.findElement(caller.Elements(CONFIGOBJ.getProperty("signIn"))).click();
        driver.findElement(caller.Elements(CONFIGOBJ.getProperty("userName"))).sendKeys(CONFIGCRED.getProperty("testUserName"));
        driver.findElement(caller.Elements(CONFIGOBJ.getProperty("password"))).sendKeys(CONFIGCRED.getProperty("testPassword"));
        driver.findElement(caller.Elements(CONFIGOBJ.getProperty("loginButton"))).click();
    }

    public void success_logout() {
       
    	//TODO
    }

}
