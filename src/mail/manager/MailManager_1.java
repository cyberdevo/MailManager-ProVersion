/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail.manager;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static mail.manager.StartPage.music;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Studio 7
 */
public class MailManager_1 {
    
    static Connection db_con=null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        /*
        //selenium
         System.setProperty("webdriver.chrome.driver","D:\\Amjad\\softwares\\selenium-java-3.141.59\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https//google.com/");
        
        driver.manage().window().maximize();
        driver.findElement(By.name("q")).sendKeys("software");
        driver.findElement(By.className("gNO89b")).click();
        String appTitle = driver.getTitle();
        System.out.println("Application Tiltle is" +appTitle);
        
        
        
        */
        
        StartPage obj1 = new StartPage();
        SignIn obj2 = new SignIn();
        obj1.setVisible(true);

        
            try {
             music("D:\\Amjad\\Semester-4\\ACP\\MAIL MANAGER\\mail manager\\fas\\Mucic\\12345.wav");  //Amjad
                ///  music("C:\\Users\\Haier\\Documents\\NetBeansProjects\\fas\\Music\\12345.wav");  //hussnain
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
            //amjad db
             String con_MM="jdbc:sqlserver://localhost:1433; databaseName=MailUserData;user=amjad;password=amjad123";
             //hussnain db
           //  String con_MM="jdbc:sqlserver://localhost:1433; databaseName=MailUserData;user=muhammadhusnain;password=101325";
             db_con = DriverManager.getConnection(con_MM);
             JOptionPane.showMessageDialog(null, "Connection Esteblished");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MailManager_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_con;
    }
    
     
    
}
