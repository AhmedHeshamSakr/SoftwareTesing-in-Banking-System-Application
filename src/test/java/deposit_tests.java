import com.example.demo1.DB_Management.DBMangment;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class deposit_tests {
    static DBMangment db = new DBMangment();
    static String username;
    static double output;
    static double before;
    static double amount;
    @BeforeClass
    public static void SetUp() throws SQLException {
        System.out.println("testing deposit");
        username = "1";
        String password = "1";
        String fullname = "1";
        String nationalID = "1";
        String phoneNo = "1";
        db.signUp(username,password,fullname,nationalID,phoneNo);
    }
    @AfterClass
    public static void TearDown()throws SQLException{
        System.out.println("Tearing down database contents");
        db.delete_user("1");
    }
    @Test
    public void accepted_case_test() throws SQLException {
        amount = 100.50;
        before = db.retrieveBalance(username);
        output = db.deposit(username,amount);
        Assert.assertEquals(before+amount, output, 0);
    }
    @Test
    public void negative_entry() throws SQLException {
        amount = -100;
        output = db.deposit(username,amount);
        Assert.assertEquals(-1, output, 0);
    }
    @Test
    public void zero_entry() throws SQLException {
        amount = 0;
        output = db.deposit(username,amount);
        Assert.assertEquals(-1, output, 0);
    }
}
