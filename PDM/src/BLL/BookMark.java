/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DBS_Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Duc Trung
 */
public class BookMark {

    public BookMark(String user) {
        userID = user;
         // lay chuoi string bookmark tu co so du lieu
        rstbookmark = con.getBookMark(userID);
        try {
            while (rstbookmark.next()) {
                bookmark = rstbookmark.getString("myWorkspaceBookMark");
                System.out.println(bookmark);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookMark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // kiem tra chuo bookmark rong hay la khong
    public String checkNull() {
        String strbookmark ="";
        rstbookmark = con.getBookMark(userID);
        
        try {
            while (rstbookmark.next()) {
                strbookmark = rstbookmark.getString("myWorkspaceBookMark");
                System.out.println(strbookmark);
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookMark.class.getName()).log(Level.SEVERE, null, ex);
        }
        return strbookmark;
    }
    // lay chuoi string bookmark tu co so du lieu de them
   
    // update chuoi bookmark
    public void updateBookMark(String namefile) {
        String file = bookmark + "[" + namefile + "]";
        con.updatebookmark(file, userID);
    }
    
    // kiem tra file da duoc luu torng bookmark chua
    public Boolean checkHave(String namefile){
        System.out.println(bookmark + "Book mark cua checkHave");
        System.out.println(namefile);
        System.out.println(bookmark.indexOf(namefile));
        if (bookmark.indexOf("["+namefile+"]") < 0 )
            return false;
        
        return true;
    }
    String bookmark;
    String userID;
    String getBM;
    DBS_Connection con = new DBS_Connection();
    ResultSet rstbookmark = null;
}
