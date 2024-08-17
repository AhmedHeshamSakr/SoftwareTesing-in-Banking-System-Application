
package com.example.demo1.DB_Management;

import com.example.demo1.Transactions;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.sql.*;

public class DBMangment {
    private final String url = "jdbc:mysql://localhost:3306/TheDataBase";
    private final String username = "root";
    private final String password = "";
    private Connection connection;

    public DBMangment() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TheDataBase", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Login ");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
            // connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int signUp(String username, String password, String fullname,String nationalID,String phoneNo ) throws SQLException {
        if (username.length()==0 || password.length()==0 ||fullname.length()==0 ||nationalID.length()==0 ||phoneNo.length()==0 || username.trim().isEmpty() || password.trim().isEmpty()  ||fullname.trim().isEmpty() ||nationalID.trim().isEmpty()||phoneNo.trim().isEmpty()){
            System.out.println("you can't leave a field empty");
            return -1;

        }
        else if(exists(username, nationalID, phoneNo)==1){
            System.out.println("Username or national ID or phone number already exists.");
            return -1;
        }
        else {
            try {
                String uaQuery = "INSERT INTO TheDataBase.Login (Username, Password, FullName, NID, Phone , balance) VALUES (\"" + username + "\",\"" + password + "\",\"" + fullname + "\",\"" + nationalID + "\",\"" + phoneNo + "\",\""+ 0 +"\");";
                Statement uaSt = connection.createStatement();
                uaSt.executeUpdate(uaQuery);
            } catch (SQLException e) {
                throw new IllegalStateException("Unable to insert values into Login " + e.getMessage());
            }
            try {
                String uaQuery = "select id from TheDataBase.Login where Username = \"" + username + "\" and Password = \"" + password + "\" and FullName = \"" + fullname + "\" and NID = \"" + nationalID + "\" and Phone = \"" + phoneNo + "\"";
                Statement uaSt = connection.createStatement();
                ResultSet uaRs = uaSt.executeQuery(uaQuery);

                if (uaRs.next()) {
                    return 1;
                } else
                    return -1;
            } catch (SQLException e) {
                System.out.println("Something went wrong creating login account");
            }
            return -1;
        }
    }

    public int exists(String username, String nationalID, String phoneNo) throws SQLException {
        Statement statement1= null;
        statement1 = connection.createStatement();
        String sql = "SELECT * FROM Login WHERE Username = '" + username + "' OR NID='"+ nationalID+ "' OR Phone='"+phoneNo+"'" ;
        ResultSet resultSet1 = statement1.executeQuery(sql);

        // Check if login successful
        if (resultSet1.next()) {
            return 1;
        }
        else return -1;

    }

    public int logIn(String username, String password) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Login WHERE Username = '" + username + "' AND Password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            // Check if login successful
            if (resultSet.next()) {
                System.out.println("Login successful!");
                return 1;
                // Additional actions after successful login
            } else {
                System.out.println("Invalid username or password");
                return -1;
                // Additional actions for failed login
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        }
    }

    public double updateBalanceWithAmount(String username, double amount) {
        try {
            // Retrieve the current balance
            double currentBalance = retrieveBalance(username);

            // Calculate the updated balance
            double updatedBalance = currentBalance + amount;

            // Update the balance in the database
            double finalBalance = updateBalance(username, updatedBalance);

            System.out.println("Balance updated successfully.");

            return finalBalance;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public double retrieveBalance(String username) throws SQLException {
        String sql = "SELECT balance FROM Login WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                }

            } catch (Exception e) {
                System.out.println("Failed to retrieve balance for account with username: " + username);
                return -1;
            }

        } catch (Exception e) {
            System.out.println("Failed to retrieve balance for account with username: " + username);
            return -1;
        }
        return -1;
    }

    public double updateBalance(String username, double updatedBalance) throws SQLException {
        String sql = "UPDATE Login SET balance = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, updatedBalance);
            statement.setString(2, username);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected != 1) {
                System.out.println("Failed to update balance for account with username: " + username);
                return -1;
            }
            return retrieveBalance(username);
        }
    }


    public int recordTransaction(String username, String transactionType, double amount) throws SQLException {
        String sql = "INSERT INTO transactions (username, transaction_type, amount, timestamp2) VALUES (?,?,?,CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, transactionType);
            statement.setDouble(3, amount);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected != 1) {
                System.out.println("Failed to record transaction for account with username: " + username);
                return -1;
            }
            return 1;
        }
        catch (SQLException e){
            System.out.println(e);
            return -1;
        }
    }

    public double withdraw(String username, Double amount) throws SQLException {
        double balance = retrieveBalance(username);
        // Check if the fromAccount has sufficient balance
        if (balance < amount) {
            System.out.println("Insufficient balance in the source account.");
            return -1;
        } else if (amount <= 0) {
            System.out.println("Amount cannot be negative or zero.");
            return -1;
        }
        else if (Double.isNaN(amount)){
            System.out.println("amount cannot be empty");
            return -1;
        }
        else {
            amount = amount * (-1);
            recordTransaction(username, "withdrawal", amount);
            return updateBalanceWithAmount(username, amount);
        }
    }

    public int transferMoney(String fromUsername, String toUsername, Double amount) {
        try {
            if(fromUsername.equals(toUsername)){
                System.out.println("you can't transfer money to yourself");
                return -1;
            } else if (usernameExists(toUsername)==-1) {
                System.out.println("username not found");
                return -1;
            }
            else if (Double.isNaN(amount)){
                System.out.println("amount cannot be empty");
                return -1;
            }
            // Retrieve the balances of both accounts
            double fromAccountBalance = retrieveBalance(fromUsername);
            double toAccountBalance = retrieveBalance(toUsername);

            // Check if the fromAccount has sufficient balance
            if (fromAccountBalance < amount) {
                 System.out.println("Insufficient balance in the source account.");
                return -1;
            }
            else if (amount <= 0) {
                System.out.println("Amount cannot be negative or zero.");
                return -1;
            }
            else {
                // Calculate the updated balances
                double updatedFromAccountBalance = fromAccountBalance - amount;
                double updatedToAccountBalance = toAccountBalance + amount;

                // Update the balances in the database
                updateBalance(fromUsername, updatedFromAccountBalance);
                updateBalance(toUsername, updatedToAccountBalance);

                // Record the transfer transaction
                recordTransaction(fromUsername, "Transfer money to " + toUsername, amount);
                recordTransaction(toUsername, "deposit from " + fromUsername, amount);

                System.out.println("Money transfer completed successfully.");
                return 1;
            }
        } catch (SQLException e) {

            e.printStackTrace();
            return -1;
        }
    }
    public int usernameExists(String username) throws SQLException {
        Statement statement1= null;
        statement1 = connection.createStatement();
        String sql = "SELECT * FROM Login WHERE Username = '" + username + "'" ;
        ResultSet resultSet1 = statement1.executeQuery(sql);

        // Check if login successful
        if (resultSet1.next()) {
            return 1;
        }
        else return -1;

    }

    public double deposit(String username, double amount) throws SQLException {
        if (amount <= 0) {
            System.out.println("Amount cannot be negative or zero.");
            return -1;
        }
        else {
            recordTransaction(username, "deposit", amount);
            return updateBalanceWithAmount(username, amount);
        }
    }



    public int retrieveAndDisplayTransactions(String username) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int transactionId = resultSet.getInt("transaction_id");

                    String transactionType = resultSet.getString("transaction_type");
                    double amount = resultSet.getDouble("amount");
                    Timestamp timestamp = resultSet.getTimestamp("timestamp2");

                    int x;
                    x = Transactions.add_t(new Transactions(Integer.toString(transactionId), transactionType, Double.toString(amount), timestamp.toString()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public void delete_user(String username) throws SQLException {
        try {
            if(usernameExists(username)==1) {
                String query = "DELETE FROM Login WHERE Username = '" + username + "'";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.execute();
                System.out.println("Username deleted");
            }
        }
        catch (SQLException e){
            System.out.println("Error");
        }
    }
}
