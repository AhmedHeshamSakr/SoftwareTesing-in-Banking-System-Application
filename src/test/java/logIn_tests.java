import com.example.demo1.DB_Management.DBMangment;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class logIn_tests {
    static DBMangment db = new DBMangment();
    static String username;
    static String password;
    static String fullname;
    static String nationalID;
    static String phoneNo;
    int output;
    @BeforeClass
    public static void SetUp() throws SQLException {
        System.out.println("testing sign in");
        username = "1";
        password = "1";
        fullname = "1";
        nationalID = "1";
        phoneNo = "1";
        db.signUp(username,password,fullname,nationalID,phoneNo);
    }
    @AfterClass
    public static void TearDown()throws SQLException{
        System.out.println("Tearing down database contents");
        db.delete_user("1");
    }

    @Test
    public void accepted_case_test() throws SQLException {
        username = "1";
        password = "1";
        output = db.logIn(username,password);
        Assert.assertEquals(1,output,0);
    }
    @Test
    public void incorrect_username() throws SQLException {
        username = "0";
        password = "1";
        output = db.logIn(username,password);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void incorrect_password() throws SQLException {
        String username = "1";
        String password = "0";
        output = db.logIn(username,password);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void incorrect_username_and_password() throws SQLException {
        String username = "0";
        String password = "0";
        output = db.logIn(username,password);
        Assert.assertEquals(-1,output,0);
    }
}
