

import com.example.demo1.DB_Management.DBMangment;
import org.junit.*;

import java.sql.SQLException;

public class DBMangment_test {
    static DBMangment db = new DBMangment();
    static String username;
    double output;
    double amount;
    double expected;
    @BeforeClass
    public static void SetUp()throws SQLException{
        username = "1";
        String password = "1";
        String fullname = "1";
        String nationalID = "1";
        String phoneNo = "1";
        db.signUp(username,password,fullname,nationalID,phoneNo);
    }
    @AfterClass
    public static void TearDown()throws SQLException{
        db.delete_user("1");
    }
    @Test
    public void retrieveBalance_Test() throws SQLException {
        db.updateBalance(username,100);
        output = db.retrieveBalance(username);
        Assert.assertEquals(100.0,output,0);
    }

    @Test
    public void updateBalanceWithAmount_Test() throws SQLException {
        amount = 100;
        output = db.updateBalanceWithAmount(username,amount);
        expected = db.retrieveBalance(username);
        Assert.assertEquals(expected,output,0);
    }
    @Test
    public void updateBalance_Test() throws SQLException {
        double newBalance = 300;
        output = db.updateBalance(username,newBalance);
        expected = db.retrieveBalance(username);
        Assert.assertEquals(expected,output,0);
    }
    @Test
    public void recordTransaction_Test() throws Exception {
            String transactionType = "Deposit";
            amount = 100;
            output = db.recordTransaction(username, transactionType,amount);
            Assert.assertEquals(1,output,0);
    }



    @Test
    public void retrieveAndDisplayTransactions_Test() throws SQLException {
        output = db.retrieveAndDisplayTransactions(username);
        Assert.assertEquals(1, output, 0);
    }
}
