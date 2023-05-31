/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapplication03;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author monke
 */
public class Bank {

    String name = "SCB";
    Connection connect;

    void openAccount(Account account) throws SQLException {
        var connect = BankConnection.connect();
        String account_name = account.getName();
        double balance = account.getBalance();
        int id = account.getNumber();
        String sql = "INSERT INTO accounts VALUES(?,?,?,?)";
        PreparedStatement prepare;
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, Integer.toString(id));
        prepare.setString(2, account_name);
        prepare.setDouble(3, balance);
        prepare.setString(4, name);
        System.out.println(account_name);

        if (prepare.executeUpdate() == 1) {
            System.out.println("Open account success");
            System.out.println("Thank for open account" + " " + account.getName());
        }

    }

    void closeAccount(int number) throws SQLException {
        var connect = BankConnection.connect();
        String sql = "DELETE FROM accounts WHERE accountID = ?";
        PreparedStatement prepare;
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, Integer.toString(number));
        if (prepare.executeUpdate() == 1) {
            System.out.println("Delete accountID:" + " " + number + " " + "Success !");
        }

    }

    void depositMoney(Account account, double amount) throws SQLException {
        var connect = BankConnection.connect();
        String sql = "UPDATE accounts SET balance = ? WHERE accountID = ?;";
        account.deposit(amount);
        PreparedStatement prepare;
        prepare = connect.prepareStatement(sql);
        prepare.setDouble(1, account.balance);
        prepare.setString(2, Integer.toString(account.getNumber()));
        if (prepare.executeUpdate() == 1) {
            String getAmount = "SELECT balance FROM accounts WHERE accountID = ? ";
            prepare = connect.prepareStatement(getAmount);
            prepare.setString(1, Integer.toString(account.getNumber()));
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                System.out.println("Now your balance is ");
                System.out.println(result.getDouble("balance"));
            }

        }

    }

    void withdrawMoney(Account account, double amount) throws SQLException {
        var connect = BankConnection.connect();
        if (amount > account.balance) {
            System.out.println("Not enough balance");

        } else {
            account.withdraw(amount);
            String update_balance = "UPDATE accounts SET balance = ? WHERE accountID = ?;";
            PreparedStatement prepare;
            prepare = connect.prepareStatement(update_balance);
            prepare.setString(2, Integer.toString(account.getNumber()));
            prepare.setDouble(1, account.getBalance());
            if (prepare.executeUpdate() == 1) {
                String getAmount = "SELECT balance FROM accounts WHERE accountID = ? ";
                prepare = connect.prepareStatement(getAmount);
                prepare.setString(1, Integer.toString(account.getNumber()));
                ResultSet result = prepare.executeQuery();
                if (result.next()) {
                    System.out.println("Now your balance is ");
                    System.out.println(result.getDouble("balance"));
                }
            }
        }

    }

    void listAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts";
        var connect = BankConnection.connect();
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println("accountID" + "   " + "name" + "   " + "balance" + "       " + "bankName");
        while (result.next()) {
//          System.out.println("Inside loop");

            System.out.println(result.getString("accountID") + "      " + result.getString("name")
                    + "     " + result.getString("balance") + "    " + result.getString("bankName")
            );

        }
    }

    public static void main(String[] args) throws SQLException {
//        --Test execution
//        String sql = "SELECT * FROM accounts";
//      var connect = BankConnection.connect();
//      Statement statement = connect.createStatement();
//      ResultSet result = statement.executeQuery(sql);
//      System.out.println("accountID"+"   "+"name"+"   "+"balance"+"       "+"bankName");
//      while(result.next()){
////          System.out.println("Inside loop");
//
//
//          System.out.println(result.getString("accountID")+"      "+ result.getString("name")
//          + "     " + result.getString("balance") + "    " + result.getString("bankName")
//          );
//          
//        
//      }
//-- Test getAccount
//        Bank bank = new Bank();
//        Account user = bank.getAccount(10098);
//        System.out.println(user.getName());

    }

    public Account getAccount(int number) throws SQLException {
        String accountID;
        String name;
        double balance;
        var connect = BankConnection.connect();
        String sql = "SELECT * FROM accounts WHERE accountID = ? ";
        PreparedStatement prepare;
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, Integer.toString(number));

        ResultSet result = prepare.executeQuery();
        if (!result.next()) {
            return null;
        }
        accountID = result.getString("accountID");
        name = result.getString("name");
        balance = result.getDouble("balance");

        Account account = new Account(Integer.parseInt(accountID), name, balance);
        return account;
    }
}
