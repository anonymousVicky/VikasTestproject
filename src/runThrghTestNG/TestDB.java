package runThrghTestNG;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import Util.DbManager;

public class TestDB {

    public static void main(String args[]) throws AddressException, ClassNotFoundException, SQLException, MessagingException {

        DbManager.setDbConnectionICMStest();
        String query = "select ART_TITLE from articles where msid=4837686";
        List<String> query1 = DbManager.getQuery(query);

        System.out.println("\n ******Result from Database query:" + query + " are as follows****** \n");

        for (int i = 0; i < query1.size(); i++) {
            System.out.println(query1.get(i));
        }

        System.out.println("\n ******Closing the Database connection in 1 ms******");
        DbManager.getConnection().close();
        System.out.println("\t  Database connection CLOSED :( ");
    }

}
