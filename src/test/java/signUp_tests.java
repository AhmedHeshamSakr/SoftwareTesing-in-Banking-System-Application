import com.example.demo1.DB_Management.DBMangment;
import org.junit.Assert;
import org.junit.*;

import java.sql.SQLException;

public class signUp_tests {
    static DBMangment db = new DBMangment();
    String username;
    String password;
    String fullname;
    String nationalID;
    String phoneNo;
    int output;
    @BeforeClass
    public static void SetUp() throws SQLException {
        System.out.println("testing sign up");
        db.delete_user("1");
        db.delete_user("7");
        db.delete_user("8");
        db.delete_user("9");
    }
    @AfterClass
    public static void TearDown()throws SQLException{
        System.out.println("Tearing down the database contents");
        db.delete_user("1");
        db.delete_user("7");
        db.delete_user("8");
        db.delete_user("9");
    }

    @Test
    public void accepted_case_test() throws SQLException {
        username = "1";
        password = "1";
        fullname = "1";
        nationalID = "1";
        phoneNo = "1";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(1,output,0);
    }

    @Test
    public void empty_username() throws SQLException {
        username = " ";
        password = "2";
        fullname = "2";
        nationalID = "2";
        phoneNo = "2";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void empty_password() throws SQLException {
        username = "3";
        password = " ";
        fullname = "3";
        nationalID = "3";
        phoneNo = "3";
        output = db.signUp(username, password, fullname, nationalID, phoneNo);
        Assert.assertEquals(-1, output, 0);
    }
    @Test
    public void empty_fullname() throws SQLException {
        username = "4";
        password = "4";
        fullname = " ";
        nationalID = "4";
        phoneNo = "4";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void empty_nationalID() throws SQLException {
        username = "5";
        password = "5";
        fullname = "5";
        nationalID = " ";
        phoneNo = "5";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void empty_phoneNo() throws SQLException {
        username = "6";
        password = "6";
        fullname = "6";
        nationalID = "6";
        phoneNo = " ";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void used_username() throws SQLException {
        username = "7";
        password = "7";
        fullname = "7";
        nationalID = "7";
        phoneNo = "7";
        db.signUp(username,password,fullname,nationalID,phoneNo);
        username = "7";
        password = "10";
        fullname = "10";
        nationalID = "10";
        phoneNo = "10";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void used_nationalID() throws SQLException {
        username = "8";
        password = "8";
        fullname = "8";
        nationalID = "8";
        phoneNo = "8";
        db.signUp(username,password,fullname,nationalID,phoneNo);
        username = "11";
        password = "11";
        fullname = "11";
        nationalID = "8";
        phoneNo = "11";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(-1,output,0);
    }
    @Test
    public void used_phoneNo() throws SQLException {
        username = "9";
        password = "9";
        fullname = "9";
        nationalID = "9";
        phoneNo = "9";
        db.signUp(username,password,fullname,nationalID,phoneNo);
        username = "12";
        password = "12";
        fullname = "12";
        nationalID = "12";
        phoneNo = "9";
        output = db.signUp(username,password,fullname,nationalID,phoneNo);
        Assert.assertEquals(-1,output,0);
    }
}
