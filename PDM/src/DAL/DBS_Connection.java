/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DBS_Connection {

    private final String strJDBCDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //chuỗi Driver JDBC
    private final String strURL = "jdbc:sqlserver://61XBXOPGAAA5SY8\\SQLEXPRESS:1433;databaseName=PDMDB"; //chuỗi kết nối CSDL pdm2017, chuỗi phải đầy đủ cả tên CSDL
    private final String strUser = "sa"; //User SA
    private final String strPassWord = "blue"; //Pass SA
    private String strTable = "dbo.users"; // Bảng dbo.users
    protected java.sql.Connection conConnect; //biến kết nối
    //61XBXOPGAAA5SY8\SQLEXPRESS
    //Hàm khởi tạo

    public DBS_Connection() {
        connectOpen();
    }

    //Hàm mở kết nối
    public void connectOpen() {
        try {
            Class.forName(strJDBCDriver);
            conConnect = DriverManager.getConnection(strURL, strUser, strPassWord);
            System.out.println("Mở kết nối SQL Server 2012 thành công");
        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi: " + ex.getMessage()); //Thông báo nếu có lỗi
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage()); //Thông báo nếu có lỗi
        }

    }

    //Hàm đóng kết nối
    public void connectClose() {
        try {
            conConnect.close();
            System.out.println("Đóng kết nối mySQL thành công");
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage()); //Thông báo nếu có lỗi
        }
    }

    //Hàm truy vấn tất cả thông tin USER (Admin)
    public ResultSet getAllUserForAdmin(String strTable) {
        ResultSet rst = null;
        String strSqlCommand = "select * from " + strTable;
        Statement st;
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSqlCommand);
            System.out.println("UI_Tier truy vấn thành công.");
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return rst;
    }

    //Hàm truy vấn tất cả thông tin User (Normal)
    public ResultSet getAllUserForNormal(String strTable) {
        ResultSet rst = null;
        String strSqlCommand = "SELECT userID, firstName, lastName, status, registerOn, userRole FROM " + strTable;
        Statement st;
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSqlCommand);
            System.out.println("UI_Tier truy vấn thành công.");
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return rst;
    }

    //Hàm truy vấn lấy thông tin USER và PASS người dùng
    public ResultSet getUserIDandPassWord(String strUserID) {
        ResultSet rst;
        String strSqlCommand = "select userID, passWord from " + strTable + " where userID = ?;";
        PreparedStatement pst;
        try {
            pst = conConnect.prepareStatement(strSqlCommand);
            pst.setString(1, strUserID);
            rst = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return rst;

    }

    //Hàm truy vấn lấy firstName và lastName của người dùng
    public String getFirstNameandLastName(String sql, String name, String passs) {
        ResultSet rst;
        // String strSqlCommand = "select firstName, lastName from " + strTable + " where userID = ?;";
        PreparedStatement pst;
        String f_Name = null, l_Name = null;
        try {
            pst = conConnect.prepareStatement(sql);
            pst.setString(1, name);
            //pst.setString(2,pass)
            rst = pst.executeQuery();
            if (rst.next()) {

                f_Name = rst.getString("firstName");
                l_Name = rst.getString("lastName");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return f_Name + " " + l_Name;

    }

    //Hàm ADD Record mới cho bảng filePDF
    public Boolean addNewPDFfile(String strFileName, String strFileDescription, String strFileType,
            String strFileFullPath, String strFileLockedBy, String strFileCreatedBy, String strFileModifiedBy) {
        //Khai báo biến cục bộ
        Boolean resultCheck = false;
        String strSqlCommand = "insert into filePDF values (' ', ?, ?, ?, (select * from openrowset ( bulk N'" + strFileFullPath + "', single_blob) as FS), '-', 'Conceptual', 1, ?, ?, GETDATE(), ?, GETDATE())";
        PreparedStatement pst = null;
        try {
            pst = conConnect.prepareStatement(strSqlCommand);
            pst.setString(1, strFileName); //tên file
            pst.setString(2, strFileDescription); //mô tả file
            pst.setString(3, strFileType); //kiểu file
            //pst.setString(4,strFileFullPath); //đường dẫn file, không dùng được truy vấn bằng dấu ? với preparedStatement
            pst.setString(4, strFileLockedBy); //khóa bởi
            pst.setString(5, strFileCreatedBy); //tạo bởi
            pst.setString(6, strFileModifiedBy); //sửa bởi
            pst.executeUpdate();
            resultCheck = true;
            System.out.println("Add dữ liệu thành công");
            System.out.println("Chuỗi truy vấn là >>> " + strSqlCommand); //In ra câu lệnh truy vấn SQL
        } catch (SQLException ex) {
            resultCheck = false;
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            System.out.println("Chuỗi truy vấn là >>> " + strSqlCommand); //In ra câu lệnh truy vấn SQL
        }
        return resultCheck;
    }

    //Hàm SHOW ALL tất cả Record trong bảng filePDF
    public ResultSet showAllFilePDF(String strTable) {
        ResultSet rst = null;
        String strSqlCommand = "select fileName, fileDescription, fileType, fileRevision, fileState, fileIteration, fileLockedBy, fileCreatedBy, fileCreatedOn, fileModifiedBy, fileModifiedOn from " + strTable;
        Statement st;
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSqlCommand);
            System.out.println("UI_Tier truy vấn thành công.");
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return rst;
        //Hàm truy vấn
    }

    public boolean prepareStatementDBS(String sql, String nameUser, String passUser) {
        //RsuiltPrepa rst = null;
        ResultSet rst;
        //String strSqlCommand = "select firstName, lastName from " + strTable + " where userID = ?;";
        PreparedStatement pst;
        try {
            pst = conConnect.prepareStatement(sql);
            //st.setString(1, strUserID);
            pst.setString(1, nameUser);
            pst.setString(2, passUser);
            //rs = pst.executeQuery();
            rst = pst.executeQuery();
            if (rst.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return false;
        }
        return false;

    }

    public ResultSet getNode(String NameForm) {
        String strTbale = null;
        if (NameForm == "PDF") {
            strTable = "filePDF";
        } else if (NameForm == "Excel") {
            strTable = "fileExcel";
        } else if (NameForm == "Word") {
            strTable = "fileWord";
        }
        ResultSet rst = null;
        Statement st = null;
        String strSql = "select fileName from " + strTable;
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSql);
            System.out.println("truy vấn thành công.");

        } catch (SQLException ex) {
            System.out.print("Lỗi lấy dữ liệu" + ex.toString());
        }

        return rst;
    }

    public ResultSet getUser(String namefile, String NameFormFile) {
        System.out.println(namefile);
        System.out.println(NameFormFile);
        ResultSet rst = null;
        Statement st = null;
        String strResult = null;
        String strSql = "select fileLockedBy from " + NameFormFile + " where fileName = " + "'" + namefile + "'";
        System.out.println("Chuoi truy van: " + strSql);
        // seclec fileLockedBy from dbo.filePDE where fileName = Test
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSql);
            strResult = rst.getString("fileLockedBy");
            System.out.println("Chuoi truy van: " + strSql);
            System.out.println("Truy vấn thành công lay UserID LockedBy");
        } catch (SQLException ex) {
            System.out.print("Lỗi lấy dữ liệu có phải đây không " + ex.toString());
        }
        return rst;
    }

    //Hàm SHOW 1 dòng bất kì trong bảng
    public ResultSet ShowAllWhereFileName(String strTable, String fileNameRow) {
        ResultSet rst = null;
        String strSqlCommand = "select fileName, fileDescription, fileType, fileRevision, fileState, fileIteration, fileLockedBy, fileCreatedBy, fileCreatedOn, fileModifiedBy, fileModifiedOn from " + strTable + " where fileName = '" + fileNameRow + "'"; //đừng quên dấu nháy ở điều kiện where
        Statement st;
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSqlCommand);
            System.out.println("UI_Tier truy vấn thành công.");
            System.out.println("Chuoi truy cvan: " + strSqlCommand);
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return rst;
    }
    
        //Hàm SHOW ALL tất cả Record trong các bảng
    public ResultSet showAllFiles(String NameForm) {
        String strTbale = "";
        if (NameForm == "PDF") {
            strTable = "filePDF";
        } else if (NameForm == "Excel") {
            strTable = "fileExcel";
        } else if (NameForm == "Word") {
            strTable = "fileWord";
        }
        
        ResultSet rst = null;
        String strSqlCommand = "select fileName, fileDescription, fileType, fileRevision, fileState, fileIteration, fileLockedBy, fileCreatedBy, fileCreatedOn, fileModifiedBy, fileModifiedOn from " + strTable;
        Statement st;
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSqlCommand);
            System.out.println("UI_Tier truy vấn thành công.");
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return rst;
    }
    
    public ResultSet getBookMark(String userID) {
        ResultSet rst = null;
        String strSqlCommand = "select myWorkspaceBookMark from userBookMark where userID = '" +userID + "'" ;
        Statement st;
        try {
            st = conConnect.createStatement();
            rst = st.executeQuery(strSqlCommand);
            System.out.println("UI_Tier truy vấn thành công.");
            System.out.println(strSqlCommand);
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu: " + ex.toString());
            return null;
        }
        return rst;
    }
    
    public void updatebookmark(String namefile, String userID){
        Statement st;
        //ResultSet rst = null;
        String strSqlCommand = "UPDATE userBookMark SET myWorkspaceBookMark = '" +namefile +"' WHERE userID = '" +userID + "'" ;
        try {
            st = conConnect.createStatement();
            st.executeUpdate(strSqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(DBS_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getInforFile(String NameFile, String NameForm){
        Statement st;
        ResultSet rst = null;
        if (NameForm == "PDF") {
            strTable = "filePDF";
        } else if (NameForm == "Excel") {
            strTable = "fileExcel";
        } else if (NameForm == "Word") {
            strTable = "fileWord";
        }
        String strSql = "select fileSysGenID, fileName, fileDescription, fileType, fileRevision, fileState, fileIteration, "
                + "fileLockedBy, fileCreatedBy, fileCreatedOn, fileModifiedBy, fileModifiedOn, fileEditHistory "
                + "from " + strTable + " where fileName = '" + NameFile + "'"; //đừng quên dấu nháy ở điều kiện where
       
        try {
            System.out.println(strTable);
            System.out.println(NameFile);
            st = conConnect.createStatement();
            rst = st.executeQuery(strSql);
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu cua InforFile: " + ex.toString());
            return null;
        }
        return rst;
    }
    
    public void UpDateEdit(String IDFile, String NameForm,String NameFileEdit, String DescriptionEdit, String UpdateHistory){
        Statement st;
        
        if (NameForm == "PDF") {
            strTable = "filePDF";
        } else if (NameForm == "Excel") {
            strTable = "fileExcel";
        } else if (NameForm == "Word") {
            strTable = "fileWord";
        }
        String strSqlCommand = "UPDATE " +strTable +" SET fileEditHistory = '" + UpdateHistory + "', fileName = ' " + NameFileEdit + "', fileDescription = '" +DescriptionEdit + "'  WHERE fileSysGenID = '" +IDFile + "'" ;
        //String strSqlCommand = "UPDATE " + strTable + " SET fileName = '" + NameFileEdit + "', fileDescription = '" +DescriptionEdit + "'  WHERE fileName = '" +NameFile + "'" ;
        try {
            st = conConnect.createStatement();
            st.executeUpdate(strSqlCommand);
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu method UpDateEdit: " + ex.toString());
        }
       
    }
    public ResultSet getHistory(String IDFile, String NameForm){
        Statement st;
        ResultSet rst = null;
        if (NameForm == "PDF") {
            strTable = "filePDF";
        } else if (NameForm == "Excel") {
            strTable = "fileExcel";
        } else if (NameForm == "Word") {
            strTable = "fileWord";
        }
        
        String strSql = "select fileEditHistory  from " + strTable + " where fileSysGenID = '" + IDFile + "'"; //đừng quên dấu nháy ở điều kiện where
       
        try {
            System.out.println(strTable);
            System.out.println(IDFile);
            st = conConnect.createStatement();
            rst = st.executeQuery(strSql);
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu cua InforFile: " + ex.toString());
            return null;
        }
        return rst;
        
        }
    
}
