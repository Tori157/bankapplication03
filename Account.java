/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapplication03;

/**
 *
 * @author monke
 */
public class Account {
     int number;
   String name;
   double balance;
   public void deposit(double amount){
       this.balance += amount;
   }

    public Account(int number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }
   public void withdraw(double amount){
       balance -= amount;
   }
   public double getBalance(){
       return balance;
   }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
