/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package runThrghTestNG;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;


/**
 *
 * @author root
 */
public class HeadlessBase extends BaseClass
{
RemoteWebDriver webDriver;
    @BeforeTest
   	public void initObjects() {
		if (CONFIGOBJ == null) {

			CONFIGOBJ = new Properties();
			try {
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir")
								+ "//src//Configuration//object.properties");
				CONFIGOBJ.load(fs);

			} catch (Exception e) {
			}
		}
	}
        @BeforeTest
    	public void initConfigurations() {

		if (CONFIGPROP == null) {

			CONFIGPROP = new Properties();
			try {
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir")
								+ "//src//Configuration//config.properties");
				CONFIGPROP.load(fs);

			} catch (Exception e) {
			}
		}
	}
    
    
    @BeforeTest
    public void setHub() throws MalformedURLException
    {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        FirefoxProfile profile = new ProfilesIni().getProfile("selenium");
        capability.setCapability(FirefoxDriver.PROFILE, profile);
        webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
 
    }
    
    
}
