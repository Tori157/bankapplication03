/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankapplication03;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author monke
 */
public class BankApplication03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        int option = 0;
        Bank bank = new Bank();
        while (option != 6) {
            Scanner scan = new Scanner(System.in);
            System.out.println("1.Display All Accounts");
            System.out.println("2.Open new Account");
            System.out.println("3.Close Existing Account");
            System.out.println("4.Desposit:");
            System.out.println("5.Withdraw");
            System.out.println("6.Close program");
            System.out.println("Enter option:");
            option = scan.nextInt();
//            System.out.println(option);
            switch (option) {
                case 1:
                    bank.listAccounts();

                    break;
                case 2:
                    Random random = new Random();
                    int random_id = random.nextInt(100000) + 1;
                    System.out.println("Enter name:");
                    scan.nextLine();
                    String name = scan.nextLine();
                    System.out.println("Enter balance:");
                    double balance = Double.parseDouble(scan.nextLine());
                    System.out.println("Name: " + name);
                    System.out.println("Balance: " + balance);
                    Account new_account = new Account(random_id, name, balance);
                    bank.openAccount(new_account);
                    break;
                case 3:
                    System.out.println("Attention are you in Delete session !!!");
                    System.out.println("Carefully");
                    System.out.println("Enter accountID: ");
                    scan.nextLine();
                    int accountID = scan.nextInt();
                    bank.closeAccount(accountID);
                    
                    
                    break;
                case 4:
                    System.out.println("Welcome to deposit interface");
                    System.out.println("Enter account ID: ");
                    int account_id = scan.nextInt();
                    double amount = scan.nextDouble();
                    Account account = bank.getAccount(account_id);
                    bank.depositMoney(account,amount);
                    
                    break;
                case 5:
                    System.out.println("Welcome to Withdraw interface");
                    System.out.println("Enter account ID:");
                    var account_ids = scan.nextInt();
                    System.out.println("Enter amount");
                    var amount_withdraw = scan.nextDouble();
                    Account account_deposit = bank.getAccount(account_ids);
                    bank.withdrawMoney(account_deposit, amount_withdraw);
                    break;

                case 6:
                    break;
                   

            }
        }
    }

}
