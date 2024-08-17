import com.example.demo1.DB_Management.DBMangment;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class transferMoney_tests {
    static String fromUsername = "3";
    static String toUsername = "5";
    static DBMangment db = new DBMangment();
    static double amount;
    int output;
    @BeforeClass
    public static void SetUp() throws SQLException {
        System.out.println("testing transfer money");
        fromUsername = "1";
        String password = "1";
        String fullname = "1";
        String nationalID = "1";
        String phoneNo = "1";
        db.signUp(fromUsername,password,fullname,nationalID,phoneNo);
        db.updateBalance(fromUsername,1000);


        toUsername = "2";
        String password1 = "2";
        String fullname1 = "2";
        String nationalID1 = "2";
        String phoneNo1 = "2";
        db.signUp(toUsername,password1,fullname1,nationalID1,phoneNo1);
        db.updateBalance(toUsername,0);
    }
    @AfterClass
    public static void TearDown()throws SQLException{
        System.out.println("Tearing down database contents");
        db.delete_user("1");
        db.delete_user("2");
    }
    @Test
    public void accepted_case_test() throws SQLException {
        fromUsername = "1";
        toUsername = "2";
        amount = 200;
        double fromBefore = db.retrieveBalance(fromUsername);
        double toBefore = db.retrieveBalance(toUsername);
        db.transferMoney(fromUsername, toUsername, amount);
        Assert.assertEquals(fromBefore-amount,db.retrieveBalance(fromUsername),0);
        Assert.assertEquals(toBefore+amount,db.retrieveBalance(toUsername),0);
    }
    @Test
    public void insufficient_balance() throws SQLException {
        fromUsername = "1";
        toUsername = "2";
        amount = 1500;
        output = db.transferMoney(fromUsername, toUsername, amount);
        Assert.assertEquals(-1, output ,0);
    }
    @Test
    public void negative_entry() throws SQLException {
        fromUsername = "1";
        toUsername = "2";
        amount = -500;
        output = db.transferMoney(fromUsername, toUsername, amount);
        Assert.assertEquals(-1, output ,0);
    }
    @Test
    public void zero_entry() throws SQLException {
        fromUsername = "1";
        toUsername = "2";
        amount = 0;
        output = db.transferMoney(fromUsername, toUsername, amount);
        Assert.assertEquals(-1, output ,0);
    }
    @Test
    public void username_not_found() throws SQLException {
        fromUsername = "1";
        toUsername = "0";
        amount = 200;
        output = db.transferMoney(fromUsername, toUsername, amount);
        Assert.assertEquals(-1, output ,0);
    }

}
