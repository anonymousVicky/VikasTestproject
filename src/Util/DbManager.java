package Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class DbManager {

    private static Connection con = null;
    @SuppressWarnings("unused")
	private static Connection conn = null;

    // Database connection for SQL Server - Database icmsstaging
    public static void setDbConnectionICMSstaging() throws SQLException,
            ClassNotFoundException, AddressException, MessagingException {
        try {
            Class.forName(TestConfig.driver);
            con = DriverManager.getConnection(
                    TestConfig.dbConnectionUrlICMSStaging, TestConfig.dbUserName,
                    TestConfig.dbPassword);

            if (!con.isClosed()) {
                System.out.println("Successfully connected to SQL server.");
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());

        }

    }

    // Database connection for SQL Server - Database icmstest
    public static void setDbConnectionICMStest() throws SQLException,
            ClassNotFoundException, AddressException, MessagingException {
        try {
            Class.forName(TestConfig.driver);
            con = DriverManager.getConnection(TestConfig.dbConnectionUrlICMSTest,
                    TestConfig.dbUserName, TestConfig.dbPassword);

            if (!con.isClosed()) {
                System.out.println("Successfully connected to SQL server :)");
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());

        }

    }

    // Query list for SQL
    public static List<String> getQuery(String query) throws SQLException {

        Statement St = con.createStatement();
        ResultSet rs = St.executeQuery(query);
        List<String> values = new ArrayList<String>();
        while (rs.next()) {

            values.add(rs.getString(1));

        }
        return values;
    }

    //Column Result
    public static String icmstestArticlesDB(String COLUMN, String msid) throws AddressException, ClassNotFoundException, SQLException, MessagingException, InterruptedException {
        DbManager.setDbConnectionICMStest();
        String query = "select " + COLUMN + " from articles where msid=" + msid;
        List<String> queryList = DbManager.getQuery(query);
        System.out.println("\n ******Result from Database for query:" + query + " is as follows****** \n");
        String result = queryList.get(0);

        for (int i = 0; i < queryList.size(); i++) {
            System.out.println(queryList.get(i));
        }
        System.out.println("\n ******Closing the Database connection...******");
        DbManager.getConnection().close();
        System.out.println("\t  Database connection CLOSED  ");

        return (result);
    }

    /*
     * 
     * public static void setMysqlDbConnection() throws SQLException,
     * ClassNotFoundException, AddressException, MessagingException { try {
     * 
     * Class.forName (TestConfig.mysqldriver).newInstance (); conn =
     * DriverManager.getConnection (TestConfig.mysqlurl,
     * TestConfig.mysqluserName, TestConfig.mysqlpassword); if(!conn.isClosed())
     * System.out.println("Successfully connected to MySQL server");
     * 
     * 
     * } catch (Exception e) { System.err.println
     * ("Cannot connect to database server");
     * 
     * 
     * }
     * 
     * 
     * }
     * 
     * 
     * 
     * // Query list for MySQL
     * 
     * public static List<String> getMysqlQuery(String query) throws
     * SQLException{
     * 
     * 
     * Statement St = conn.createStatement(); ResultSet rs =
     * St.executeQuery(query); List<String> values1 = new ArrayList<String>();
     * while(rs.next()){
     * 
     * values1.add(rs.getString(1));
     * 
     * 
     * } return values1; }
     */
    public static Connection getConnection() {
        return con;
    }
}
