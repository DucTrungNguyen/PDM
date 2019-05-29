/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAL.*;        
import DTO.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Administrator
 */

public class AddNew extends JFrame{
     private static String NameForm;
     private String StrFileTypeForm;
     //DTOgetInFoUser getUserID = new DTOgetInFoUser();
    DBS_Connection con = new DBS_Connection();
    Get get = new Get();

    public AddNew(String NameForm) {
        initComponents(NameForm);
        if (NameForm == "PDF") StrFileTypeForm = "pdf";
        if( NameForm == "Word") StrFileTypeForm = "doc";
        if (NameForm == "Excel") StrFileTypeForm = "xls";
        //StrFileTypeForm = NameForm;
    }


    
    private void initComponents(String NameForm){
        
        /********* Mcreate Main AddNew********/
         setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
         setTitle("Add New " + NameForm +  " File ");
         setSize(500,350);
         setResizable(false);
         setLocationRelativeTo(null);
    
         /****** title boder*****/
        JPanel outer = new JPanel();
        outer.setBorder(BorderFactory.createTitledBorder("Add New " + NameForm + " File"));
        
        outer.add(C_Add());
        this.add(outer);
        
    }
    
    private JPanel C_Add(){
         JPanel panel = new JPanel(new GridLayout(2,1,30,40));
         panel.add(panel1());
         panel.add(panel2());
        return panel;
    }
    
    private JPanel panel1(){
        Bt_OpenFile = new JButton("Open File");
        
        //TextNameOpen = new JTextField();
        TextType = new JTextField(10);
        TextDescription = new JTextField();
        TextNameFile = new JTextField();
        TextFullPath = new JTextField();
        TextOpen = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(5,2,15,10));
        
        panel.add(new JLabel("File Name:"));
        panel.add(TextNameFile);
        
        
        panel.add(new JLabel("Descripion"));
        panel.add(TextDescription);
        
        panel.add(Bt_OpenFile);
                Bt_OpenFile.addActionListener(this::OpenFileAction);
        TextOpen.setEditable(false);
        panel.add(TextOpen);
        
        panel.add(new JLabel("Type"));
        TextType.setEditable(false);
        panel.add(TextType);
        
        
        panel.add(new JLabel("Full Path"));
        TextFullPath.setEditable(false);
        panel.add(TextFullPath);
        return panel;
    }
    
    public void OpenFileAction(ActionEvent e){
        String fileName = "";
        String fileExtension = "";
        String fileFullPath = "";
        String getFileName = "";
        int indexOfString = 0;
        JFileChooser fc = new JFileChooser();
        int response = fc.showOpenDialog(this);
        if(response == JFileChooser.APPROVE_OPTION){ //Nếu nhấn Yes hoặc OK
            getFileName = fc.getSelectedFile().getName(); //Lấy toàn bộ  tên và phần mở rộng của file
            fileFullPath = fc.getSelectedFile().getPath();
            indexOfString = getFileName.lastIndexOf(".");
            fileName = getFileName.substring(0, indexOfString);
            fileExtension = getFileName.substring(indexOfString+1);
            //System.out.print("Vị trí dấu chấm là: " + indexOfString);
            TextOpen.setText(fileName);
            TextType.setText(fileExtension);
            TextFullPath.setText(fileFullPath);
        }           
        
        
    }
    private JPanel panel2(){ 
        Bt_AddDB = new JButton("Add To Databse");
        Bt_Cancel = new JButton("Cancel");
        Bt_AddMore = new JButton("Add More File");
        
       
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 10, 20);
        JPanel panel = new JPanel(layout);
        panel.add(Bt_AddDB);
            Bt_AddDB.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Bt_AddtoDB(e);
                }
            
            });
                
        panel.add(Bt_AddMore);
            Bt_AddMore.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    Bt_AddMoreAction(e);
                }
            });
        
        panel.add(Bt_Cancel);
   
            Bt_Cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                        Bt_CancelAction(evt);
            }
                    
        });
        return panel;
    }
    
    //Hàm Check trước khi Add New PDF File
    public Boolean checkBeforeAddNewPDFfile(){
        Boolean resultCheck = false;
        String strFileName = TextNameFile.getText();
        String strFileDescription = TextDescription.getText(); //Được phép NULL
        String strFileType = TextType.getText();
        String strFileFullPath = TextFullPath.getText();
        String strFileLockedBy = get.GetID(); //user hiện hành
        String strFileCreatedBy = get.GetID(); //user hiện hành
        String strFileModifiedBy = get.GetID(); //user hiện hành
        //Check điều kiện khác NULL
        if(!StrFileTypeForm.equals(strFileType)){ //kiểm tra xem file đã chọn có đúng là file PDF không
            JOptionPane.showMessageDialog(null, "File bạn chọn là file [*."+strFileType+"]."+" Xin hãy chọn file PDF!","Chọn file "+StrFileTypeForm,JOptionPane.INFORMATION_MESSAGE);
        }
        else{
                if(     !"".equals(strFileName) && 
                        !"".equals(strFileType)&&
                        !"".equals(strFileFullPath)&&
                        !"".equals(strFileLockedBy)&&
                        !"".equals(strFileCreatedBy)&&
                        !"".equals(strFileModifiedBy)){
                    System.out.println("Điều kiện đã thỏa mãn. Các trường bắt buộc không NULL đã OK.");
                    System.out.println("User hiện hành " + get.GetID());
                    resultCheck = true;            
                }        
                else{
                    System.out.println("Điều kiện chưa thỏa mãn. Các trường bắt buộc NULL. " + get.GetID() );
                    resultCheck = false;
                }                       
        }
        return resultCheck;
 }
 
    private void Bt_AddtoDB (ActionEvent e){
        if(checkBeforeAddNewPDFfile()){
                Boolean check = false;
                String upperStrFileName = TextNameFile.getText().toUpperCase();
                String upperStrFileDescription = TextDescription.getText().toUpperCase();
                String upperStrFileType = TextType.getText().toUpperCase();
                String upperGetUserID = get.GetID().toUpperCase();
                //Add new PDF file
                check = con.addNewPDFfile(upperStrFileName, 
                        upperStrFileDescription, upperStrFileType, TextFullPath.getText(), 
                        upperGetUserID, upperGetUserID, upperGetUserID);
                    if(check){
                        JOptionPane.showMessageDialog(null, "Đã thêm file ["+TextNameFile.getText()+"."+TextType.getText()+" ] thành công.","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    }
            }
        
        
        
    }
    
    
    
    private void Bt_AddMoreAction( ActionEvent e){
        
    }
    
     private void Bt_CancelAction (ActionEvent e){
        this.dispose();
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNew(NameForm).setVisible(true);
            }
        });
           
     }
   
    private javax.swing.border.Border title;
    
    private javax.swing.JTextField TextNameFile;
    private javax.swing.JTextField TextType;
    private javax.swing.JTextField TextFullPath;
    private javax.swing.JTextField TextDescription;
    private javax.swing.JTextField TextNameOpen;
    private javax.swing.JTextField TextOpen;
    private javax.swing.JButton Bt_OpenFile;
    private javax.swing.JButton Bt_AddDB;
    private javax.swing.JButton Bt_Cancel;
    private javax.swing.JButton Bt_AddMore;
    
    
    
}