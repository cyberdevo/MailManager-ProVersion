/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static mail.manager.StartPage.music;

/**
 *
 * @author Studio 7
 */
public class MailManager {
    
    static Connection db_con=null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StartPage obj1 = new StartPage();
        SignIn obj2 = new SignIn();
        obj1.setVisible(true);

        
            try {
                    music("C:\\Users\\Haier\\Documents\\NetBeansProjects\\fas\\Music\\12345.wav");
                } catch (IOException ex) {
                    Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        try {
            for (int i = 1; i <= 100; i++) {
                Thread.sleep(40);
                obj1.jLabel5.setText(Integer.toString(i) + "%");
                obj1.jProgressBar1.setValue(i);
                if (i == 100) {
                    obj1.setVisible(false);
                    obj2.setVisible(true);
                }
            }
        } catch (Exception e) {
            //  Block of code to handle errors
        }
    }

    
    public static Connection Db_MailsManager() throws SQLException{
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             String con_MM="jdbc:sqlserver://localhost:1433; databaseName=MailUserData;user=muhammadhusnain;password=101325";
             db_con = DriverManager.getConnection(con_MM);
             JOptionPane.showMessageDialog(null, "Connection Esteblished");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_con;
    }
    
}
