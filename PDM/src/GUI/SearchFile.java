/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class SearchFile extends JFrame{

    private static String NameForm;
    
    public SearchFile(String NameForm) {
        initComponents(NameForm);
    }
   // public SearchFile ( String name){
      //  NameFrom = name;
    //}
    private void initComponents(String NameForm){
         
        /********* Mcreate Main AddNew********/
         setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
         setTitle("Search "+ NameForm);
         setSize(1000,550);
         setResizable(true);
         setLocationRelativeTo(null);
         /******** Title *********/
         JPanel outer = new JPanel(new GridLayout(1,1));
         JSplitPane jsplitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panel1(),panel2());
         outer.add(jsplitpane);
         add(outer);
  
    }
    private JPanel panel1(){
         JPanel outer = new JPanel(new GridLayout(2,1));
        // JPanel panel = new JPanel();
         //JScrollPane crollpane = new JScrollPane();
         outer.setBorder(BorderFactory.createTitledBorder("Search Conditions"));
         outer.setMinimumSize(new Dimension(250,225));
      
        String[] titles = new String[]{"File Name", "File Type", "File Revisions", 
            "File State", "File Ileration", "File Locked By","File Created By","File Created On","File Modified By","File Modifild On"};
        Object[][] objects = new Object[][]{//null, null, null, null, null,null, null, null, null, null
        };
        JTable table = new JTable(objects, titles);
       // crollpane.setViewportView(table);
        outer.add(new JScrollPane(table));
        //panel.setModel(table);
         outer.add(C_BtSearch());
    
        return outer;
    }
    private JPanel panel2(){
         JPanel outer = new JPanel(new GridLayout(2,1));
         outer.setBorder(BorderFactory.createTitledBorder("Search Resuilts"));
        
         //outer.add(C_Res());
         String[] titles = new String[]{"File Name", "File Type", "File Revisions", 
            "File State", "File Ileration", "File Locked By","File Created By","File Created On","File Modified By","File Modifild On"};
        Object[][] objects = new Object[][]{//null, null, null, null, null,null, null, null, null, null
        };
        JTable table = new JTable(objects, titles);
        outer.add(new JScrollPane(table));
        outer.add(C_BtOkCancel());
        return outer;
        
    }
    
    
    private JPanel C_BtSearch(){
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        JPanel panel = new JPanel(layout);
        panel.add(new JButton("Search"));
        return panel;
                
    }
    
    private JPanel C_BtOkCancel(){
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,20,20);
        JPanel panel = new JPanel(layout);
        panel.add(new JButton(" OK "));
        panel.add(new JButton("Canel"));
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
            java.util.logging.Logger.getLogger(SearchFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SearchFile(NameForm).setVisible(true);
            
        });
            
     }
}
