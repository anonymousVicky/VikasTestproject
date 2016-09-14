/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package runThrghTestNG;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer
{
private int retryCount = 0;
private int maxRetryCount = 2;
public boolean retry(ITestResult result) 
{

if(retryCount < maxRetryCount) 
{ 
    System.out.println("Retrying test case as it has failed.");
    retryCount++; 
    return true; 
} return false; 
} 
} 