/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author John Tyronn Ocso
 */
public class RubrickContent extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public RubrickContent() {
        //fullscreen
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
        
        initComponents();
        //select 
        
        Toolkit tk = Toolkit.getDefaultToolkit();
            int xsize = (int) tk.getScreenSize().getWidth();
            int ysize = (int) tk.getScreenSize().getHeight();
        
        Get_Rubrick_Content();
        this.setSize(xsize,ysize);
        
        jPanel2.setBackground(new Color(0,0,0,30));
        jPanel3.setBackground(new Color(0,0,0,30));
        
    }
    public RubrickContent(int idRubR, String rTitle) {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
           initComponents();
        
        String rNr = ""+idRubR;
        idForR.setText(rNr);
        Get_Rubrick_Content();
        //select 
      
        Toolkit tk = Toolkit.getDefaultToolkit();
            int xsize = (int) tk.getScreenSize().getWidth();
            int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
        
        jPanel2.setBackground(new Color(0,0,0,30));
        jPanel3.setBackground(new Color(0,0,0,30));
        Title.setText(rTitle);
    }
    
    public Connection getConnection()
    {
        Connection con;
        try 
        {
            String path = "jdbc:mysql://localhost/usc_eval";
           String user = "root";
            String pass = "";
            con = DriverManager.getConnection(path,user,pass);
            return con;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "A B C D");
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Rubrick> getRubricList(){
        ArrayList<Rubrick> rubList = new ArrayList<Rubrick>();
        Connection con = getConnection();
        String query = "SELECT * FROM `rubrickcontent` where rubrickId = "+ idForR.getText();
        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            Rubrick rubric;
            while(rs.next()){
//                int rubrickId, int percentEdge, int maxScore, String rTitle, String coLn2, String coLn3, String coLn4, String coLn5, String coLn6
                rubric = new Rubrick(rs.getInt("atongId"),rs.getInt("rubrickId"), rs.getInt("percentEdge"), rs.getInt("maxScore"), rs.getString("rTitle") ,rs.getString("coLn2") , rs.getString("coLn3"), rs.getString("coLn4"), rs.getString("coLn5"), rs.getString("coLn6"));
                rubList.add(rubric);
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "SELECT * FROM `rubrickcontent` where rubrickId = "+idForR.getText());
         e.printStackTrace();
        }
        return rubList;
    }
    
    public void execute_Query(String query, String message){
        Connection con = getConnection();
        Statement st;
        try {
            st=con.createStatement();
            if((st.executeUpdate(query))==1){
                JOptionPane.showMessageDialog(null, message+ "successfully");
                this.setVisible(false);
                new RubrickContent(Integer.parseInt(idForR.getText()),Title.getText());
            }else{
                JOptionPane.showMessageDialog(null, "Data not "+message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void Get_Rubrick_Content(){
   
        ArrayList<Rubrick> list =  getRubricList();
        DefaultTableModel model = (DefaultTableModel)ContentTable.getModel();
        ContentTable.setRowHeight(150);
        ContentTable.setShowHorizontalLines(true);
        ContentTable.setShowVerticalLines(true);
        Object[] row = new Object[8];
        for(int i = 0; i < list.size();i++){
            row[0] =list.get(i).getRTitle();
              row[1] =list.get(i).getColn2();
              row[2] =list.get(i).getCoLn3();
              row[3] =list.get(i).getCoLn4();
              row[4] =list.get(i).getCoLn5();
              row[5] =list.get(i).getCoLn6();
              row[6] = list.get(i).getMaxScore();
              row[7] = list.get(i).getPercentEdge();
              model.addRow(row);
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        ContentTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        AddContent = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rDesc = new javax.swing.JTextPane();
        EditContent = new javax.swing.JButton();
        RemoveContent = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        pwersent = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ixscore = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        col2ni = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        col3ni = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        col4ni = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        col5ni = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        col6ni = new javax.swing.JTextPane();
        Exit = new javax.swing.JButton();
        LOGO = new javax.swing.JLabel();
        idForR = new javax.swing.JLabel();
        idC = new javax.swing.JLabel();
        cID = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(null);

        ContentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Criteria", "Expert", "Proficient", "Competent", "Novice", "Begingin", "Score", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ContentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ContentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ContentTable);
        if (ContentTable.getColumnModel().getColumnCount() > 0) {
            ContentTable.getColumnModel().getColumn(6).setMinWidth(80);
            ContentTable.getColumnModel().getColumn(6).setMaxWidth(80);
            ContentTable.getColumnModel().getColumn(7).setMinWidth(80);
            ContentTable.getColumnModel().getColumn(7).setMaxWidth(80);
        }

        jScrollPane8.setViewportView(jScrollPane2);

        jPanel3.add(jScrollPane8);
        jScrollPane8.setBounds(30, 40, 1060, 960);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 1120, 1040));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        AddContent.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        AddContent.setText("Add");
        AddContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddContentActionPerformed(evt);
            }
        });

        Title.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        Title.setText("Title");

        jScrollPane1.setViewportView(rDesc);

        EditContent.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        EditContent.setText("Edit");
        EditContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditContentActionPerformed(evt);
            }
        });

        RemoveContent.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        RemoveContent.setText("Remove");
        RemoveContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveContentActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Percent");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Max Score");

        ixscore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ixscoreActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(col2ni);

        jScrollPane4.setViewportView(col3ni);

        jScrollPane5.setViewportView(col4ni);

        jScrollPane6.setViewportView(col5ni);

        jScrollPane7.setViewportView(col6ni);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwersent, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ixscore, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EditContent, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddContent, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RemoveContent, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Title)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(pwersent, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ixscore, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(AddContent, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EditContent, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RemoveContent, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 620, 990));

        Exit.setBackground(new java.awt.Color(255, 51, 51));
        Exit.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        Exit.setText("RETURN");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jPanel1.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1020, 190, 60));
        jPanel1.add(LOGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, -40, 1310, 1130));
        jPanel1.add(idForR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 1030, 40, 40));
        jPanel1.add(idC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 1030, 40, 40));
        jPanel1.add(cID, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 1030, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddContentActionPerformed
       int idsaRubrick = Integer.parseInt(idForR.getText());
       int perc = Integer.parseInt(pwersent.getText());
       int mScore = Integer.parseInt(ixscore.getText());
       String rtit= rDesc.getText();
       String coL2= col2ni.getText();
       String coL3=col3ni.getText();
       String coL4=col4ni.getText();
       String coL5=col5ni.getText();
       String coL6=col6ni.getText();
       
        System.out.println(idsaRubrick+"__" + perc+" __ "+mScore+"__  "+rtit+"___"+coL2);
       String query = "INSERT INTO `rubrickcontent`(`rubrickId`, `percentEdge`, `maxScore`, `rTitle`, `coLn2`, `coLn3`, `coLn4`, `coLn5`, `coLn6`) VALUES ("+idsaRubrick+","+perc+","+mScore+",'"+rtit+"','"+coL2+"','"+coL3+"','"+coL4+"','"+coL5+"','"+coL6+"')";
       execute_Query(query,"Inserted");
       
    }//GEN-LAST:event_AddContentActionPerformed

    private void RemoveContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveContentActionPerformed
        // TODO add your handling code here:
//        String query= "DELETE FROM `rubrick` WHERE rubrickId = "+rID.getText();
//        JOptionPane.showMessageDialog(null, query);
//        execute_Query(query,"Deleted");
//        new CreateRubricks();
    }//GEN-LAST:event_RemoveContentActionPerformed

    private void EditContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditContentActionPerformed
        // TODO add your handling code here:
         String q = "Select * from rubrickcontent WHERE rTitle ='"+rDesc.getText() +"' AND coLn2 = '"+col2ni.getText()+"'" ;
        
         Connection con = getConnection();
        try{
            Statement ts = con.createStatement();
            ResultSet r = ts.executeQuery(q);
        if(r.next()){
            
            String qt = "UPDATE `rubrickcontent` SET `percentEdge`=["+pwersent.getText()+"], `maxScore`=["+ixscore.getText()+"], `rTitle`=['"+r.getString("rTitle")+"'],`coLn2`=['"+r.getString("coLn2")+"'],`coLn3`=['"+r.getString("coLn3")+"'],`coLn4`=['"+r.getString("coLn4")+"'],`coLn5`=['"+r.getString("coLn5")+"'],`coLn6`=['"+r.getString("coLn6")+"'] WHERE `atongId`="+r.getInt("atongId");
            execute_Query(qt,"Updated");
            this.setVisible(true);
            new RubrickContent(Integer.parseInt(idForR.getText()),Title.getText());
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_EditContentActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new CreateRubricks().setVisible(true);
    }//GEN-LAST:event_ExitActionPerformed

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked

    }//GEN-LAST:event_ExitMouseClicked

    private void ContentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContentTableMouseClicked
//      
        int i = ContentTable.getSelectedRow();
        TableModel model = ContentTable.getModel();
        rDesc.setText(model.getValueAt(i, 0).toString());
        col2ni.setText(model.getValueAt(i, 1).toString());
        col3ni.setText(model.getValueAt(i, 2).toString());
        col4ni.setText(model.getValueAt(i, 3).toString());
        col5ni.setText(model.getValueAt(i, 4).toString());
        col6ni.setText(model.getValueAt(i, 5).toString());
        ixscore.setText(model.getValueAt(i, 6).toString());
        pwersent.setText(model.getValueAt(i, 7).toString());
    }//GEN-LAST:event_ContentTableMouseClicked

    private void ixscoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ixscoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ixscoreActionPerformed

    /**
     * @param args the command line arguments
     */     
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
            java.util.logging.Logger.getLogger(RubrickContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RubrickContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RubrickContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RubrickContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RubrickContent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddContent;
    private javax.swing.JTable ContentTable;
    private javax.swing.JButton EditContent;
    private javax.swing.JButton Exit;
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton RemoveContent;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel cID;
    private javax.swing.JTextPane col2ni;
    private javax.swing.JTextPane col3ni;
    private javax.swing.JTextPane col4ni;
    private javax.swing.JTextPane col5ni;
    private javax.swing.JTextPane col6ni;
    private javax.swing.JLabel idC;
    private javax.swing.JLabel idForR;
    private javax.swing.JTextField ixscore;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField pwersent;
    private javax.swing.JTextPane rDesc;
    // End of variables declaration//GEN-END:variables
}
