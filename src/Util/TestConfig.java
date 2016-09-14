package Util;

public class TestConfig {

    public static String server = "smtp.gmail.com";
    public static String from = "";
    public static String password = "";
    public static String[] to = {""};
    public static String subject = "Test script failed in Automated Regression Run";
    public static String messageBody = "Test Script failed due to reason mentioned. Please find the attached screenshot:";
    public static String attachmentPath = System.getProperty("user.dir") + "\\src\\Error temp\\Automated Error Screen.jpg";
    public static String attachmentName = "Automated Error Screen.jpeg";

    //DataBase Details
    public static String driver = "net.sourceforge.jtds.jdbc.Driver";
    public static String dbConnectionUrlICMSStaging = "";
    public static String dbConnectionUrlICMSTest = "";
    public static String dbUserName = "";
    public static String dbPassword = "";

    /*
     public static String mysqldriver="";
     public static String mysqluserName = "";
     public static String mysqlpassword = "";
     public static String mysqlurl = "";
     */
}
