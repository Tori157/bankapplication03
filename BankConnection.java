/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapplication03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author monke
 */
public class BankConnection {
    public static Connection connect() throws SQLException{
        String URL = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "123456";
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            connection = DriverManager.getConnection(URL, username, password);
            
       
        
        return connection;
    }
  
}
