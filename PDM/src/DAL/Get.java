/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Get extends DTO{
    /* DTO dto = new DTO(); */
    String name =  getName();
    //String ID = GetID;
    String pass =  getPass(); 
    //String name = "pdmtest1";
    //String pass = "pdmtest1";
    public String GetName(){
     
        String fullName;
        
        DBS_Connection conn = new  DBS_Connection();
        String Sql = "SELECT firstName, lastName FROM dbo.users where userID=?";
       
        return  fullName = conn.getFirstNameandLastName(Sql,name, pass);
    }
    
    public String GetDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return (dateFormat.format(date));
    }
    public String GetID(){
        return name;
    }
}
