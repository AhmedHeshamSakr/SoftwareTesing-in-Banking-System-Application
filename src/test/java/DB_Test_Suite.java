import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        signUp_tests.class,
        logIn_tests.class,
        deposit_tests.class,
        withdraw_tests.class,
        transferMoney_tests.class,
        DBMangment_test.class
})
public class DB_Test_Suite {
}
