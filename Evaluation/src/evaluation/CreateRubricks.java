/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author John Tyronn Ocso
 */
public class CreateRubricks extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public CreateRubricks() {
        //fullscreen
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
        
        initComponents();
        Show_Rubrics();
        //select 
        
        Toolkit tk = Toolkit.getDefaultToolkit();
            int xsize = (int) tk.getScreenSize().getWidth();
            int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
        
        jPanel2.setBackground(new Color(0,0,0,30));
        jPanel3.setBackground(new Color(0,0,0,30));
        
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
        String query = "SELECT * FROM `rubrick`";
        
        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            Rubrick rubric;
            while(rs.next()){
                rubric = new Rubrick(rs.getInt("rubrickId"),rs.getString("name"),rs.getString("type"));
                rubList.add(rubric);
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "getStudent?");
         e.printStackTrace();
        }
        return rubList;
    }
    public void Show_Rubrics(){
        ArrayList<Rubrick> list =  getRubricList();
        DefaultTableModel model = (DefaultTableModel)RubricsList.getModel();
        
        Object[] row = new Object[3];
        for(int i = 0; i < list.size();i++){
            row[0] = list.get(i).getRubrickId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getType();
            model.addRow(row);
        }
        
    }
    public void execute_Query(String query, String message){
        Connection con = getConnection();
        Statement st;
        try {
            st=con.createStatement();
            if((st.executeUpdate(query))==1){
                JOptionPane.showMessageDialog(null, "Data "+message+" successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Data not "+message);
            }
        }catch(Exception e){
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RubricsList = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AddUser = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        rTitle = new javax.swing.JTextField();
        RubrickType = new javax.swing.JComboBox<>();
        Content = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        rID = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        RubricsList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Rubrics Name", "Type"
            }
        ));
        RubricsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RubricsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(RubricsList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 1120, 800));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Type:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Rubrick Name:");

        AddUser.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        AddUser.setText("Add");
        AddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUserActionPerformed(evt);
            }
        });

        Delete.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Delete.setText("Remove");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        rTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rTitleActionPerformed(evt);
            }
        });

        RubrickType.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        RubrickType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------------Blank------------", "Group", "Individual", " " }));

        Content.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Content.setText("Content");
        Content.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rTitle))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(90, 90, 90)
                                .addComponent(RubrickType, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(108, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(AddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(Content, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(RubrickType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Content, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(453, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 620, 800));

        Exit.setBackground(new java.awt.Color(255, 51, 51));
        Exit.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        Exit.setText("Return");
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
        jPanel1.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 930, 190, 60));
        jPanel1.add(rID, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 950, -1, -1));
        jPanel1.add(LOGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, -40, 1310, 1130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        
    }//GEN-LAST:event_ExitMouseClicked

    private void AddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUserActionPerformed
        String rName = rTitle.getText();
        String rTitle = (String)RubrickType.getSelectedItem();
        String query= "INSERT INTO `rubrick`(`name`, `type`) VALUES ('"+rName+"','"+rTitle+"')";
        JOptionPane.showMessageDialog(null, query);
        execute_Query(query,"Added");
        new CreateRubricks();
    }//GEN-LAST:event_AddUserActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        
        String query= "DELETE FROM `rubrick` WHERE rubrickId = "+rID.getText();
        JOptionPane.showMessageDialog(null, query);
        execute_Query(query,"Deleted");
        new CreateRubricks();
    }//GEN-LAST:event_DeleteActionPerformed

    private void rTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rTitleActionPerformed
        // TODO add your handling code here:asdfszdgf
    }//GEN-LAST:event_rTitleActionPerformed

    private void RubricsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RubricsListMouseClicked
        // TODO add your handling code here:
        int i = RubricsList.getSelectedRow();
        TableModel model = RubricsList.getModel();
        rID.setText(model.getValueAt(i, 0).toString());
        rTitle.setText(model.getValueAt(i, 1).toString());
        RubrickType.setSelectedItem(model.getValueAt(i, 2).toString());
        
       
    }//GEN-LAST:event_RubricsListMouseClicked

    private void ContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContentActionPerformed
        // TODO add your handling code here:
        int idx = Integer.parseInt(rID.getText());
        String name= rTitle.getText();
        new RubrickContent(idx, name);
    }//GEN-LAST:event_ContentActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
         new adminDash().setVisible(true);
    }//GEN-LAST:event_ExitActionPerformed

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
            java.util.logging.Logger.getLogger(CreateRubricks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateRubricks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateRubricks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateRubricks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CreateRubricks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddUser;
    private javax.swing.JButton Content;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Exit;
    private javax.swing.JLabel LOGO;
    private javax.swing.JComboBox<String> RubrickType;
    private javax.swing.JTable RubricsList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel rID;
    private javax.swing.JTextField rTitle;
    // End of variables declaration//GEN-END:variables
}
