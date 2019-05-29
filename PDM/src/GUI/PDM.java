/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import BLL.GetNameForm;
import DAL.*;
import BLL.*;
import DTO.DTO;
import DTO.DTO_TrueFalseLockFile;
import DTO.DTONameForm;
import GUI.SearchFile;
import GUI.AddNew;
import BLL.IconTreeNodeRenderer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.GRAY;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ToolBar;
import javafx.scene.text.Font;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
//import javax.swing.Image.*

/**
 *
 * @author Nguyen Duc Trung( GalaxyCode)
 */
public class PDM extends javax.swing.JFrame implements ActionListener {

    public PDM() {
        initComponents();
    }

    private void initComponents() {
        UserID = dto.getName(); // Lay userID
        
        userBookMark = new BookMark(UserID);
        
        JMenu_BarPDM = new javax.swing.JMenuBar();
        JMenu_Program = new javax.swing.JMenu();
        JMenu_Edit = new javax.swing.JMenu();
        JMenu_Project = new javax.swing.JMenu();
        JMenu_Setting = new javax.swing.JMenu();
        JMenu_Help = new javax.swing.JMenu();
        JMenu_About = new javax.swing.JMenu();

        //Icon Icon_Refesh = new ImageIcon(getClass().getResource("/Icon/refresh-icon.png"));
        Bt_Refesh = new javax.swing.JButton("Refesh");
        Bt_Refesh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refresh-icon.png")));
        Bt_Refesh.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Refesh.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_Copy = new javax.swing.JButton("Copy");
        Bt_Copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Editing-Copy-icon.png")));
        Bt_Copy.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Copy.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_Cut = new javax.swing.JButton("Cut");
        Bt_Cut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Cut-icon.png")));
        Bt_Cut.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Cut.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_Paste = new javax.swing.JButton("Paste");
        Bt_Paste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Paste-icon.png")));
        Bt_Paste.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Paste.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_CheckIn = new javax.swing.JButton("CheckIn");
        Bt_CheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/box-in-icon.png")));
        Bt_CheckIn.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_CheckIn.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_CheckOut = new javax.swing.JButton("CheckOut");
        Bt_CheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/box-out-icon.png")));
        Bt_CheckOut.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_CheckOut.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_Lock = new javax.swing.JButton("Lock");
        Bt_Lock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Lock-Lock-icon.png")));
        Bt_Lock.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Lock.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_UnLock = new javax.swing.JButton("UnLock");
        Bt_UnLock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Lock-Unlock-icon.png")));
        Bt_UnLock.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_UnLock.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_Search = new javax.swing.JButton("Search");
        Bt_Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-blue-icon.png")));
        Bt_Search.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Search.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_Sort = new javax.swing.JButton("Sort");
        Bt_Sort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Arrow-Updown-icon.png")));
        Bt_Sort.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Sort.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_User = new javax.swing.JButton("User");
        Bt_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //       Bt_UserActionPerformed(evt);
            }
        });
        Bt_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Person-Male-Light-icon.png")));
        Bt_User.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_User.setHorizontalTextPosition(SwingConstants.CENTER);

        Bt_Database = new javax.swing.JButton("Database");
        Bt_Database.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/database-icon.png")));
        Bt_Database.setVerticalTextPosition(SwingConstants.BOTTOM);
        Bt_Database.setHorizontalTextPosition(SwingConstants.CENTER);

        itemSearch = new javax.swing.JMenuItem("Search");
        itemAdd = new javax.swing.JMenuItem("Add");
        itemShowAll = new javax.swing.JMenuItem("Show All");
        itemEdit = new javax.swing.JMenuItem("Edit");
        itemDelete = new javax.swing.JMenuItem("Delete");

        itemSignout = new javax.swing.JMenuItem("Sign out");
        itemExit = new javax.swing.JMenuItem("Exit");

        // **************** START MAIN**************** 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PDM");
        //setBackground(new java.awt.Color(0, 51, 204));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        setSize(width, height);
        setResizable(true);
        setLocationRelativeTo(null);

        // ***************MENU TASK BAR****************
        // add JMenu File  
        JMenu_Program.setText("Program");
        JMenu_Program.add(itemSignout);
        itemSignout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getSource() == itemSignout) {
                    Login login = new Login();
                    login.setVisible(true);
                    dispose();
                    setVisible(false);
                }

            }
        });
        JMenu_Program.add(itemExit);
        itemExit.addActionListener((ActionEvent e) -> {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if (e.getSource() == itemExit) {
                dispose();
                setVisible(false);
            }
        });

        JMenu_BarPDM.add(JMenu_Program);

        // add Jmenu Edit
        JMenu_Edit.setText("Edit");
        JMenu_BarPDM.add(JMenu_Edit);

        // add JMenu Project
        JMenu_Project.setText("Project");
        JMenu_BarPDM.add(JMenu_Project);

        // add JMenu Setting
        JMenu_Setting.setText("Setting");
        JMenu_BarPDM.add(JMenu_Setting);

        // add JMenu Help
        JMenu_Help.setText("Help");
        JMenu_BarPDM.add(JMenu_Help);

        // add JMenu About
        JMenu_About.setText("About");
        JMenu_BarPDM.add(JMenu_About);

        setJMenuBar(JMenu_BarPDM);

        /**
         * ******** Button Right + User Date Login***************
         */
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(Crete_Toolbar(), BorderLayout.NORTH);

        /**
         * ************ JTree + View + Protites ************
         */
        JSplitPane jsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, C_JTree(), C_ViewPro());
        panel1.add(jsplitpane, BorderLayout.CENTER);
        this.add(panel1);

    }

    /**
     * ****** Create ToolBar_Right**************
     */
    private JPanel Crete_Toolbar() {
        JPanel panel = new JPanel(new BorderLayout());
        ToolBar_Right = new JToolBar();
        ToolBar_Right.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        ToolBar_Right.setRollover(true);
        ToolBar_Right.setEnabled(false);
        ToolBar_Right.setFloatable(true);
        String[] NumberFile = {"Số lượng file hiện thị", "10", "20", "30", "50", "100"};
        JComboBox NumberFileList = new JComboBox(NumberFile);
        //NumberFileList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        ToolBar_Right.add(Bt_Refesh);
        ToolBar_Right.add(Bt_Copy);
        ToolBar_Right.add(Bt_Cut);
        ToolBar_Right.add(Bt_Paste);
        ToolBar_Right.add(Bt_CheckIn);
        ToolBar_Right.add(Bt_CheckOut);
        ToolBar_Right.add(Bt_Lock);
        ToolBar_Right.add(Bt_UnLock);
        ToolBar_Right.add(Bt_Search);
        ToolBar_Right.add(Bt_Sort);
        ToolBar_Right.add(Bt_User);
        ToolBar_Right.add(Bt_Database);
        ToolBar_Right.add(NumberFileList);
        panel.add(ToolBar_Right, BorderLayout.WEST);
        panel.add(C_Name_Date(), BorderLayout.EAST);
        return panel;
    }

    private JPanel C_Name_Date() {
        Get get = new Get();
        NameUser = get.GetName();
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JLabel("User name  : " + NameUser));
        panel.add(new JLabel("Date Login : " + get.GetDate()));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return panel;

    }

    private JPanel C_JTree() {

        panelProject = new JPanel();
        rootproject = new DefaultMutableTreeNode("Project");
        //create the child nodes
        DefaultMutableTreeNode productNode = new DefaultMutableTreeNode("Product");
        DefaultMutableTreeNode partNode = new DefaultMutableTreeNode("Part");
        DefaultMutableTreeNode documentNode = new DefaultMutableTreeNode("Document");
        BookMarkNode = new DefaultMutableTreeNode("Book Mark");
        //add the child nodes to the root node Project
        rootproject.add(productNode);
        rootproject.add(partNode);
        rootproject.add(documentNode);
        rootproject.add(BookMarkNode);

        //create the tree by passing in the root node Document
        DefaultMutableTreeNode pdfNode = new DefaultMutableTreeNode("PDF");
        DefaultMutableTreeNode excelNode = new DefaultMutableTreeNode("Excel");
        DefaultMutableTreeNode wordNode = new DefaultMutableTreeNode("Word");
        DefaultMutableTreeNode PPtNode = new DefaultMutableTreeNode("PowerPoint");
        DefaultMutableTreeNode pictureNode = new DefaultMutableTreeNode("Picture");
        DefaultMutableTreeNode videoNode = new DefaultMutableTreeNode("Video");
        DefaultMutableTreeNode drawingNode = new DefaultMutableTreeNode("Drawing");

        documentNode.add(pdfNode);
        documentNode.add(excelNode);
        documentNode.add(wordNode);
        documentNode.add(PPtNode);
        documentNode.add(pictureNode);
        documentNode.add(videoNode);
        documentNode.add(drawingNode);

        if (userBookMark.checkNull() != "") {
            char[] bookmark = userBookMark.checkNull().toCharArray();
            int len = bookmark.length;
            System.out.println(len);
            int i= 0, j = 1;
            
            String filecheck = "";
            for ( ; i < len; i++ ){
                if ( bookmark[i] == ']' ){
                    for ( ; j <i; j++){
                        filecheck = filecheck + bookmark[j]; 
                    }
                    DefaultMutableTreeNode nodeBookMark = new DefaultMutableTreeNode(filecheck);
                    BookMarkNode.add(nodeBookMark);
                    filecheck = "";
                    if (i < len-1 ) j = i+2;
                }
            }
          
        }

        treeproject = new JTree(rootproject);

        treeproject.setComponentPopupMenu(getPopUpMenu());

        itemSearch.addActionListener((ActionListener) this);
        itemSearch.setActionCommand("search");

        itemAdd.addActionListener((ActionListener) this);
        itemAdd.setActionCommand("add");

        treeproject.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        // tree.setToggleClickCount(2);
        treeproject.addTreeSelectionListener((TreeSelectionEvent e) -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
            Object nodeName = node.toString();
            NameForm = (String) nodeName;
        });

        treeproject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                doMouseClicked(e, NameForm);
            }
        });

        panelProject.setBackground(Color.WHITE);
        panelProject.add(treeproject);

        panelProject.setMinimumSize(new Dimension(150, 0));
        return panelProject;
    }

    public void doMouseClicked(java.awt.event.MouseEvent evt, String NameForm) {

        if (!evt.isConsumed() && evt.getClickCount() == 2) {
            evt.consume();
            System.out.println("Double Click " + NameForm);
            // PanelFile_Infor = new File_Infor(NameForm);
            //PanelFile_Infor.setSize(widthOuterC_View, heightOuterC_View);
            //treeShowFile = new JtreeShowFile(NameForm);
            //InForFile = new InFor();
            outerC_View.removeAll();
            outerC_View.repaint();
            outerC_View.revalidate();
            BorderLayout BorderTree = new BorderLayout();
            outerC_View.setLayout(BorderTree);
            //outerC_View.add(treefile(NameForm), BorderLayout.WEST);

            outerC_View.add(PanelTreeFileScroll(NameForm), BorderLayout.WEST);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("search")) {
            SearchFile S_File = new SearchFile(NameForm);
            S_File.setVisible(true);
        }

        if (ae.getActionCommand().equals("add")) {
            AddNew Add = new AddNew(NameForm);
            Add.setVisible(true);
        }
    }

    /**
     * ******** Sign out*********
     */
    private JPopupMenu getPopUpMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        itemSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-blue-icon.png")));
        popupMenu.add(itemSearch);

        itemAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Toolbar-Add-Folder-icon.png")));
        popupMenu.add(itemAdd);

        itemShowAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon-ShowAll.png")));
        popupMenu.add(itemShowAll);

        itemEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon-Edit.png")));
        popupMenu.add(itemEdit);

        itemDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon-Delete.png")));
        popupMenu.add(itemDelete);

        return popupMenu;
    }

    private JPopupMenu getPopUpMenuBookMark() {
        JPopupMenu popupMenu = new JPopupMenu();
        itemBookMark = new JMenuItem("BookMark");
        itemBookMark.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Icon-Bookmark.png")));
        popupMenu.add(itemBookMark);

        return popupMenu;

    }

    private JPanel C_ViewPro() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        //JSplitPane jsplitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,C_View(),C_Pro());
        //panel.add(jsplitpane);
        //panel.setBackground(Color.WHITE);
        panel.add(C_View());
        return panel;
    }

    private JPanel C_View() {
        outerC_View = new JPanel();
        outerC_View.setBorder(BorderFactory.createTitledBorder("View"));
        outerC_View.setMinimumSize(new Dimension(250, 500));
        // outerC_Pro.setBackground();
        //outerC_View.setOpaque(true);
        outerC_View.setBackground(Color.WHITE);
        widthOuterC_View = outerC_View.getWidth();
        heightOuterC_View = outerC_View.getHeight();
        return outerC_View;
    }

    private JPanel C_Pro() {
        JPanel outer = new JPanel();
        outer.setBorder(BorderFactory.createTitledBorder("Properties"));
        outer.setBackground(Color.WHITE);
        return outer;
    }

    private JTree treefile(String NameForm) {

        //UserID = dto.getName();
        if ("PDF".equals(NameForm)) {
            strIconWhite = getClass().getResource("/Icon/Icon-PDF.png");
            strIconRed = getClass().getResource("/Icon/Icon-LockRedPDF.png");
            strIconBlue = getClass().getResource("/Icon/Icon-LockBluePDF.png");
        }
        if ("Excel".equals(NameForm)) {
            strIconWhite = getClass().getResource("/Icon/Icon-Excel.png");
            strIconRed = getClass().getResource("/Icon/Icon-LockRedExcel.png");
            strIconBlue = getClass().getResource("/Icon/Icon-LockBlueExcel.png");
        }
        if ("Word".equals(NameForm)) {
            strIconWhite = getClass().getResource("/Icon/Icon-Word.png");
            strIconRed = getClass().getResource("/Icon/Icon-LockRedWord.png");
            strIconBlue = getClass().getResource("/Icon/Icon-LockBlueWord.png");
        }

        ImageIcon rootIcon = new ImageIcon(getClass().getResource("/Icon/Icon-folder.png"));
        DefaultMutableTreeNode root = new IconTreeNode(rootIcon, NameForm, null);

        rstfile = con.showAllFiles(NameForm);
        String LockedBy;
        try {
            while (rstfile.next()) {
                String strNodeName = "";
                strNodeName = rstfile.getString("fileName");
                //check
                System.out.println("Node: " + strNodeName);
                System.out.println("Node locked by: " + rstfile.getString("fileLockedBy"));
                LockedBy = rstfile.getString("fileLockedBy");

                //Lưu lại user đang lock file node vào class getInfoUser
                //       getInfoUser.setUserIDLockedNode(rst.getString("fileLockedBy"));
                if (LockedBy.equals(UserID)) { //Người dùng đang lock
                    ImageIcon iconLeaf = new ImageIcon(strIconBlue);
                    DefaultMutableTreeNode nodeMutable = new IconTreeNode(iconLeaf, strNodeName, "Blue"); //Nội dung và icon cho node  
                    
                    root.add(nodeMutable);
                } else {
                    if ("".equals(rstfile.getString("fileLockedBy"))) {
                        ImageIcon iconLeaf = new ImageIcon(strIconWhite); //Không có người lock
                        DefaultMutableTreeNode nodeMutable = new IconTreeNode(iconLeaf, strNodeName , "White"); //Nội dung và icon cho node  
                        root.add(nodeMutable);
                    } else {
                        ImageIcon iconLeaf = new ImageIcon(strIconRed); //Người khác đang lock
                        DefaultMutableTreeNode nodeMutable = new IconTreeNode(iconLeaf, strNodeName, "Red"); //Nội dung và icon cho node  
                        root.add(nodeMutable);
                    }
                }

            }
            System.out.println("Do du lieu thanh cong.");
        } catch (SQLException ex) {
            System.out.println("Lỗi đổ dữ liệu: " + ex.getMessage());
        }

        TreeFile = new JTree(root);

        TreeFile.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                IconTreeNode node = (IconTreeNode) e.getPath().getLastPathComponent();
                nameFileSelect = node.getNodeName();
                IconInfo = node.getIcon();

            }
        });
        TreeFile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                doMouseClickedfile(e, nameFileSelect, NameForm, IconInfo);
            }
        });

        TreeFile.setCellRenderer(new IconTreeNodeRenderer());
        TreeFile.setComponentPopupMenu(getPopUpMenuBookMark());

        itemBookMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (e.getSource() == itemBookMark) {
                    if (userBookMark.checkHave(nameFileSelect) == false) {
                        userBookMark.updateBookMark(nameFileSelect);
                        
                        DefaultMutableTreeNode nodeBookMark = new DefaultMutableTreeNode(nameFileSelect);
                        BookMarkNode.add(nodeBookMark);
                        System.out.println(nameFileSelect + " Select Book Mark");
                        //BookMarkNode.removeAllChildren();
                        panelProject.removeAll();
                        panelProject.repaint();
                        panelProject.revalidate();
                        panelProject.add(treeproject);
                    } else  JOptionPane.showMessageDialog(null, "File [" + nameFileSelect + "] đã có trong Book Mark");             
                }

            }
        });
        //itemBookMark.setActionCommand("bookmark");

        return TreeFile;

    }

    public void doMouseClickedfile(java.awt.event.MouseEvent evt, String NameFile, String NameForm, ImageIcon IconInfo) {
        if (!evt.isConsumed() && evt.getClickCount() == 2) {
            evt.consume();
            System.out.println(NameFile);
            // DTO_TrueFalseLockFile checkTrueFalse= new DTO_TrueFalseLockFile();
            //checkTrueFalse.setNameFile(NameFile);
            outerC_View.removeAll();
            outerC_View.repaint();
            outerC_View.revalidate();
            InForFile = new InFor();
            // outerC_View.add(treefile(NameForm), BorderLayout.WEST);
            outerC_View.add(PanelTreeFileScroll(NameForm), BorderLayout.WEST);
            outerC_View.add(InForFile.PannelScroll(NameFile, NameForm, IconInfo), BorderLayout.CENTER);
        }

    }

    public JPanel PanelTreeFileScroll(String NameForm) {
        JPanel panel = new JPanel(new BorderLayout());
        JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, 0, 300);

        panel.add(treefile(NameForm), BorderLayout.CENTER);

        panel.add(vbar, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setPreferredSize(new Dimension(200, 100));
        return panel;
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
            java.util.logging.Logger.getLogger(PDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PDM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PDM().setVisible(true);

        });
    }
    DTO dto = new DTO();
    boolean check = true;
    boolean checkBefore = false;
    int width;
    int height;
    int widthOuterC_View;
    int heightOuterC_View;
    private javax.swing.JMenuBar JMenu_BarPDM;
    private javax.swing.JMenu JMenu_Program;
    private javax.swing.JMenu JMenu_Edit;
    private javax.swing.JMenu JMenu_Project;
    private javax.swing.JMenu JMenu_Setting;
    private javax.swing.JMenu JMenu_Help;
    private javax.swing.JMenu JMenu_About;

    private javax.swing.JButton Bt_Refesh;
    private javax.swing.JButton Bt_Copy;
    private javax.swing.JButton Bt_Cut;
    private javax.swing.JButton Bt_Paste;
    private javax.swing.JButton Bt_CheckIn;
    private javax.swing.JButton Bt_CheckOut;
    private javax.swing.JButton Bt_Lock;
    private javax.swing.JButton Bt_UnLock;
    private javax.swing.JButton Bt_Search;
    private javax.swing.JButton Bt_Sort;
    private javax.swing.JButton Bt_User;
    private javax.swing.JButton Bt_Database;

    private javax.swing.JToolBar ToolBar_Right;
    public javax.swing.JPanel outerC_View;
    private javax.swing.JLabel Lb_Date;

    private javax.swing.BoxLayout Main_Layout;
    private JPanel panelProject;
    private DefaultMutableTreeNode rootproject;
    private DefaultMutableTreeNode BookMarkNode;
    private javax.swing.JTree treeproject;
    private javax.swing.JMenuItem itemSearch;
    private javax.swing.JMenuItem itemAdd;
    private javax.swing.JMenuItem itemShowAll;
    private javax.swing.JMenuItem itemEdit;
    private javax.swing.JMenuItem itemDelete;
    private javax.swing.JMenuItem itemBookMark;

    private javax.swing.JMenuItem itemSignout;
    private javax.swing.JMenuItem itemExit;
    //private File_Infor PanelFile_Infor;
    //private  JtreeShowFile treeShowFile;
    //private static InFor InForFile;
    private javax.swing.JTable jTableView;
    private String NameForm;
    private DTO_TrueFalseLockFile checkLock;
    public static String test = new String();

    DefaultMutableTreeNode root;
    URL strIconRed;
    URL strIconBlue;
    URL strIconWhite;
    DBS_Connection con = new DBS_Connection();
    ResultSet rstfile = null;
    ResultSet rstUserID = null;
    String nameFileSelect;
    private InFor InForFile;
    private JTree TreeFile;

    public String NameUser;
    public String UserID;
    public String UserID_Result;

    private ImageIcon IconInfo;
    private BookMark userBookMark;
    //private javax.swing.BoxLayout BorderTree;

}
