/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DBS_Connection;
import DTO.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class InFor extends JPanel {

    /*public InFor (String NameForm){
    }*/
    public JPanel PannelScroll(String NameFile,String NameForm, ImageIcon IconInfo) {
        this.NameFile = NameFile;
        this.NameForm = NameForm;
        this.Icon = IconInfo;
        
        System.out.println(NameFile +" in method PannelScroll ");
        System.out.println(NameForm + " in method PannelScroll " );
        System.out.println(IconInfo + " in method PannelScroll ");
        
        strIconInfo = IconInfo.toString();
        strLockBlue = getClass().getResource("/Icon/Icon-Lock1.png");
        strLockRed = getClass().getResource("/Icon/Icon-Lock2.png");
        
        System.out.println(strLockBlue + " in method PannelScroll ");
        
        if ( strIconInfo.equals(strLockBlue.toString()) ) CanEdit = true;
            else CanEdit = false;
       
        System.out.println(CanEdit);
        
        JPanel panel = new JPanel(new BorderLayout());
        JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, 0, 300);
        JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, 0, 300);

        hbar.setUnitIncrement(2);
        hbar.setBlockIncrement(1);

        // hbar.addAdjustmentListener(new MyAdjustmentListener());
        //vbar.addAdjustmentListener(new MyAdjustmentListener());
        panel.add(hbar, BorderLayout.SOUTH);
        panel.add(vbar, BorderLayout.EAST);
        panel.add(Panel(NameFile, NameForm, IconInfo, CanEdit), BorderLayout.CENTER);
        
        
        
        return panel;
    }

    public JPanel Panel(String NameFile, String NameForm, ImageIcon IconInfo, Boolean CanEdit) {
        //labelIcon.setLocation(29, 37);
        panelInforandButton = new JPanel();
        BorderLayout BorderInFor = new BorderLayout();
        panelInforandButton.setLayout(BorderInFor);
        //panel.add(Tittle(NameFile), BorderLayout.NORTH);
        TittleFile = new JLabel("Current Selected File : " + NameFile,IconInfo, SwingConstants.LEFT);
        //TittleFile.
        panelInforandButton.add(TittleFile, BorderLayout.NORTH);
        //panel.add(labelIcon, BorderLayout.NORTH);
        //panel.add(IconInfoFile, BorderLayout.NORTH);
        //panel.
        panelInforandButton.add(panelInFor(NameFile, NameForm, CanEdit), BorderLayout.CENTER);
        panelInforandButton.add(buttonEdit(CanEdit), BorderLayout.SOUTH);
        return panelInforandButton;
    }

    private JLabel Tittle(String NameFile) {
        Tittle = new JLabel("Current Selected File : " + NameFile);
        Tittle.setFont(new Font("Serif", Font.CENTER_BASELINE, 22));
        System.out.println(NameFile + " test");

        //Tittle.setFont(new Font("Serif", Font.PLAIN, 16);
        return Tittle;
    }

    private JPanel panelInFor(String NameFile, String NameForm, Boolean CanEdit) {
        GridLayout GridInFor = new GridLayout(1, 2, 0, 0);
        JPanel panelInFor = new JPanel(GridInFor);

        panelInFor.add(LabelInForFile());
        panelInFor.add(TextInForFile(NameFile, NameForm, CanEdit));
        return panelInFor;
    }

    private JPanel LabelInForFile() {
        JPanel panel = new JPanel();

        GridLayout GridLayout = new GridLayout(11, 1);
        panel.setLayout(GridLayout);
        Label_fileName = new JLabel("FileName");
        Label_fileName.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileDescription = new JLabel("FileDescription");
        Label_fileDescription.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileType = new JLabel("FileType");
        Label_fileType.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileRevision = new JLabel("FileRevision");
        Label_fileRevision.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileState = new JLabel("FileState");
        Label_fileState.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileInteration = new JLabel("FileInteration");
        Label_fileInteration.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileLockBy = new JLabel("FileLockedBy");
        Label_fileLockBy.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileCreatedBy = new JLabel("FileCreatedBy");
        Label_fileCreatedBy.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_fileCreatedOn = new JLabel("FileCreatedOn");
        Label_fileCreatedOn.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_ModifiedBy = new JLabel("ModifiedBy");
        Label_ModifiedBy.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        Label_ModifiedOn = new JLabel("ModifiedOn");
        Label_ModifiedOn.setFont(new Font("Serif", Font.CENTER_BASELINE, 18));

        panel.add(Label_fileName);
        panel.add(Label_fileDescription);
        panel.add(Label_fileType);
        panel.add(Label_fileRevision);
        panel.add(Label_fileState);
        panel.add(Label_fileInteration);
        panel.add(Label_fileLockBy);
        panel.add(Label_fileCreatedBy);
        panel.add(Label_fileCreatedOn);
        panel.add(Label_ModifiedBy);
        panel.add(Label_ModifiedOn);

        return panel;
    }

    private JPanel TextInForFile(String NameFile, String NameForm, Boolean CanEdit) {
        panel_TextField = new JPanel();
        IconTick = new ImageIcon(getClass().getResource("/Icon/Icon-Tick.png"));
        //BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        GridLayout GridLayout = new GridLayout(11, 1);
        panel_TextField.setLayout(GridLayout);
        
        Text_fileID = new JTextField();
        Text_fileName = new JTextField();
        Text_fileDescription = new JTextField();
        Text_fileType = new JTextField();
        Text_fileRevision = new JTextField();
        Text_fileState = new JTextField();
        Text_fileInteration = new JTextField();
        Text_fileLockBy = new JTextField();
        Text_fileCreatedBy = new JTextField();
        Text_fileCreatedOn = new JTextField();
        Text_ModifiedBy = new JTextField();
        Text_ModifiedOn = new JTextField();

        panel_TextField.setLayout(GridLayout);
        
        rst = con.getInforFile(NameFile, NameForm);
        
        try{
            while(rst.next()){
                IDFile = rst.getString("fileSysGenID");
                
                Text_fileName.setText(NameFile);
                
                Description = rst.getString("fileDescription");
                Text_fileDescription.setText(Description);
                
                Text_fileType.setText(rst.getString("fileType"));
                Text_fileRevision.setText(rst.getString("fileRevision"));
                Text_fileState.setText(rst.getString("fileState"));
                Text_fileInteration.setText(rst.getString("fileIteration"));
                Text_fileLockBy.setText(rst.getString("fileLockedBy"));
                Text_fileCreatedBy.setText(rst.getString("fileCreatedBy"));
                Text_fileCreatedOn.setText(rst.getString("fileCreatedOn"));
                Text_ModifiedBy.setText(rst.getString("fileModifiedBy"));
                Text_ModifiedOn.setText(rst.getString("fileModifiedOn"));
                
                //EditHistory = rst.getString("fileEditHistory");
                //if (EditHistory == null) EditHistory = "";
              
            }
            System.out.println("Do du lieu Infofile thanh cong");
            
        } catch (SQLException ex) {
            System.out.println("Lỗi lấy dữ liệu method TextInForFile " + ex.toString());
        } 
        
        panel_NameFile = new JPanel(new BorderLayout());
        
        JLabel LabelNameFile = new JLabel(IconTick);
        LabelNameFile.setOpaque(true);
        LabelNameFile.setBackground(Text_fileName.getBackground());  
        LabelNameFile.setPreferredSize(new Dimension(LabelNameFile.getPreferredSize().width,Text_fileName.getPreferredSize().height));
        Text_fileName.setBorder(null);  
        panel_NameFile.add(LabelNameFile,BorderLayout.EAST);  
        panel_NameFile.add(Text_fileName,BorderLayout.CENTER);
        
        Text_fileName.setEditable(false);
        //Text_fileName.setEnabled(false);
        
        //panel_TextField.add(Text_fileName);
        panel_TextField.add(panel_NameFile);
        /***************************************************/
        JPanel panel_Description = new JPanel(new BorderLayout());
        
        JLabel LabelDescription = new JLabel(new ImageIcon(getClass().getResource("/Icon/Icon-Tick.png"))); 
        LabelDescription.setOpaque(true);
        LabelDescription.setBackground(Text_fileDescription.getBackground());  
        LabelDescription.setPreferredSize(new Dimension(LabelDescription.getPreferredSize().width,Text_fileDescription.getPreferredSize().height));
        Text_fileName.setBorder(null);  
        panel_Description.add(LabelDescription,BorderLayout.EAST);  
        panel_Description.add(Text_fileDescription,BorderLayout.CENTER);
        
        Text_fileDescription.setEditable(false);
        //Text_fileDescription.setEnabled(false);
        
        panel_TextField.add(panel_Description);
        /*****************************************/
        
        Text_fileType.setEditable(false);
        //Text_fileType.setEnabled(false);
        panel_TextField.add(Text_fileType);
        
        Text_fileRevision.setEditable(false);
        //Text_fileRevision.setEnabled(false);
        panel_TextField.add(Text_fileRevision);
        
        Text_fileState.setEditable(false);
        //Text_fileState.setEnabled(false);
        panel_TextField.add(Text_fileState);
        
        Text_fileInteration.setEditable(false);
        //Text_fileInteration.setEnabled(false);
        panel_TextField.add(Text_fileInteration);
        
        Text_fileLockBy.setEditable(false);
        //Text_fileLockBy.setEnabled(false);
        panel_TextField.add(Text_fileLockBy);
            
        Text_fileCreatedBy.setEditable(false);
        //Text_fileCreatedBy.setEnabled(false);
        panel_TextField.add(Text_fileCreatedBy);
        
        Text_fileCreatedOn.setEditable(false);
        //Text_fileCreatedOn.setEnabled(false);
        panel_TextField.add(Text_fileCreatedOn);
        
        Text_ModifiedBy.setEditable(false);
        //Text_ModifiedBy.setEnabled(false);
        panel_TextField.add(Text_ModifiedBy);
        
        Text_ModifiedOn.setEditable(false);
        //Text_ModifiedOn.setEnabled(false);
        panel_TextField.add(Text_ModifiedOn);

        return panel_TextField;
    }

    private JPanel buttonEdit(Boolean CanEdit) {
        //ImageIcon Icon = new ImageIcon(getClass().getResource("/Icon/Icon-Lock1.png"));
        PanelEdit = new JPanel();
        JButton Bt_Edit = new JButton();
        Bt_Edit.setText("Edit");
        
        if ( CanEdit == false ) Bt_Edit.setEnabled(false);
            else Bt_Edit.setEnabled(true);
        //Bt_Edit.setIcon(IconTest);
        
        PanelEdit.add(Bt_Edit);
        Bt_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_EditActionPerformed(evt, CanEdit);
            }
        });
        return PanelEdit;
    }
    
    private JPanel Bt_OkandCancel(){
        //JPanel PanelOkandCancel = new JPanel();
        JButton Bt_Ok = new JButton();
        Bt_Ok.setText("OK");
        Bt_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_OkActionPerformed(evt);
            }
        });
        
        JButton Bt_Cancel = new JButton();
        Bt_Cancel.setText("Cancel");
        Bt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_CancelActionPerformed(evt, true);
            }
        });
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,20,10);
        PanelOkandCancel = new JPanel(layout);
        PanelOkandCancel.add(Bt_Ok);
        PanelOkandCancel.add(Bt_Cancel);
        return PanelOkandCancel;
        
    }
    
    private void Bt_EditActionPerformed (ActionEvent  evt, Boolean CanEdit){
        panelInforandButton.remove(PanelEdit);
        
        Text_fileName.setEditable(true);
        Text_fileName.setEnabled(true);
        
//      panel_TextField.add(panel_NameFile);
        Text_fileDescription.setEditable(true);
        Text_fileDescription.setEnabled(true);
        
        /***************************************/
        
        //panelInforandButton.add(panelInFor(NameFile, NameForm, CanEdit), BorderLayout.CENTER);
        panelInforandButton.add(Bt_OkandCancel(), BorderLayout.SOUTH);
        
        panelInforandButton.updateUI();
        
    }
    private void Bt_CancelActionPerformed(ActionEvent evt, Boolean CanEdit ){
        panelInforandButton.remove(PanelOkandCancel );
        panelInforandButton.add(buttonEdit(CanEdit), BorderLayout.SOUTH);
        
        Text_fileName.setEditable(false);
        Text_fileName.setEnabled(false);
        
        Text_fileDescription.setEditable(false);
        Text_fileDescription.setEnabled(false);
        
        Text_fileName.setText(NameFile);
        Text_fileDescription.setText(Description);
        
        panelInforandButton.updateUI();
    }
    private void Bt_OkActionPerformed (ActionEvent evt){
        String NameFileEdit = "";
        String DescriptionEdit = "";
        
        NameFileEdit = Text_fileName.getText();
        DescriptionEdit = Text_fileDescription.getText();
        if ("".equals(NameFileEdit)){
            JOptionPane.showMessageDialog(null, "Tên file không được để trống","Câp nhật thông tin",JOptionPane.INFORMATION_MESSAGE);
        } else
            if (NameFile.equals(NameFileEdit) && Description.equals(DescriptionEdit)){
                JOptionPane.showMessageDialog(null, "Các mục chưa được cập nhật","Câp nhật thông tin",JOptionPane.INFORMATION_MESSAGE);
            } else{
                String UserID = dto.getName();
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String timeEdit = dateFormat.format(date);
                
                
                rst = con.getHistory(IDFile, NameForm);
        
                try{
                    while(rst.next()){
       
                        EditHistory = rst.getString("fileEditHistory");
                        if (EditHistory == null) EditHistory = "";
              
                    }
                    System.out.println("Lay History thanh cong");
            
                    } catch (SQLException ex) {
                        System.out.println("Lỗi lấy dữ liệu method TextInForFile " + ex.toString());
                    } 
                
                
                String UpDateHistory = EditHistory + "[ " + UserID + " - " + timeEdit + " ]";
                System.out.println(UpDateHistory + " method Bt_EditActionPerformed");
                System.out.println(NameFileEdit + " method Bt_EditActionPerformed");
                con.UpDateEdit(IDFile, NameForm, NameFileEdit, DescriptionEdit,UpDateHistory);
                
                panelInforandButton.remove(TittleFile);
               // Icon = new ImageIcon(strIconInfo);
                TittleFile = new JLabel("Current Selected File : " + NameFileEdit,Icon, SwingConstants.LEFT);
                panelInforandButton.add(TittleFile, BorderLayout.NORTH);
                
                panelInforandButton.remove(PanelOkandCancel );
                panelInforandButton.add(buttonEdit(CanEdit), BorderLayout.SOUTH);
        
                Text_fileName.setEditable(false);
                Text_fileName.setEnabled(false);
        
                Text_fileDescription.setEditable(false);
                Text_fileDescription.setEnabled(false);
        
                panelInforandButton.updateUI();
                JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công", "Câp nhật thông tin", JOptionPane.INFORMATION_MESSAGE);
            }
                
    }    
    // private JPanel panel;
    private JLabel Tittle;

    private JLabel Label_fileID;
    private JLabel Label_fileName;
    private JLabel Label_fileDescription;
    private JLabel Label_fileType;
    private JLabel Label_fileRevision;
    private JLabel Label_fileState;
    private JLabel Label_fileInteration;
    private JLabel Label_fileLockBy;
    private JLabel Label_fileCreatedBy;
    private JLabel Label_fileCreatedOn;
    private JLabel Label_ModifiedBy;
    private JLabel Label_ModifiedOn;

    private JTextField Text_fileID;
    private JTextField Text_fileName;
    private JTextField Text_fileDescription;
    private JTextField Text_fileType;
    private JTextField Text_fileRevision;
    private JTextField Text_fileState;
    private JTextField Text_fileInteration;
    private JTextField Text_fileLockBy;
    private JTextField Text_fileCreatedBy;
    private JTextField Text_fileCreatedOn;
    private JTextField Text_ModifiedBy;
    private JTextField Text_ModifiedOn;

   
    //private Get_InFoFile Get_InFo = new Get_InFoFile();
    DBS_Connection con = new DBS_Connection();
    JPanel panel_NameFile;
    JPanel panel_TextField;
    JLabel LabelNameFile;
    ImageIcon Icon;
    ImageIcon IconTick;
    JLabel TittleFile;
    String IDFile;
    String NameFile;
    String NameForm;
    String Description;
    String EditHistory;
    ResultSet rst = null;
    String strIconInfo;
    URL strLockRed;
    URL strLockBlue;
    Boolean CanEdit;
    JPanel PanelEdit ;
    JPanel panelInforandButton;
    JPanel PanelOkandCancel;
    DTO dto = new DTO();
}
