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
public class CreateGroup extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public CreateGroup() {
        //fullscreen
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
        
        initComponents();
        Show_NoGroup_table();
        Show_Group_table();
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
    
    public ArrayList<Student> getStudentList(String query){
        ArrayList<Student> studList = new ArrayList<Student>();
        Connection con = getConnection();
        String que = query;
        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            Student stu;
            while(rs.next()){
                stu = new Student(rs.getInt("idNumber"),rs.getString("fullName"), rs.getInt("groupId"),rs.getInt("class_List_id"));
                studList.add(stu);
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "getStudent?");
         e.printStackTrace();
        }
        return studList;
    }
    
    public void Show_NoGroup_table(){
        ArrayList<Student> list =  getStudentList("Select * from `students` JOIN classlist ON classlist.studentId = students.idNumber LEFT JOIN classgroup ON classlist.id = classgroup.class_List_id WHERE classgroup.groupId IS null");
        DefaultTableModel model = (DefaultTableModel)noGroupTable.getModel();
        
        Object[] row = new Object[5];
        for(int i = 0; i < list.size();i++){
            
            row[0] = list.get(i).getIdNumber();
            row[1] = list.get(i).getFullName();
            model.addRow(row);
        }
    }
    
    public void Show_Group_table(){
        ArrayList<Student> list =  getStudentList("Select * from `students` JOIN classlist ON classlist.studentId = students.idNumber JOIN classgroup ON classlist.id = classgroup.class_List_id");
        DefaultTableModel model = (DefaultTableModel)inGroup.getModel();
        
        Object[] row = new Object[5];
        for(int i = 0; i < list.size();i++){
            row[0] = list.get(i).getGroupId();
            row[1] = list.get(i).getclass_List_id();
            row[2] = list.get(i).getFullName();
            
            model.addRow(row);
        }
        
    }
    public void execute_Query(String query, String message){
        Connection con = getConnection();
        Statement st;
        try {
            st=con.createStatement();
            if((st.executeUpdate(query))==1){
                JOptionPane.showMessageDialog(null, message+ "successfully");
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        noGroupTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        inGroup = new javax.swing.JTable();
        KickGroup = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        groupN = new javax.swing.JTextField();
        AddUser = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        studentID = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        noGroupTable.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        noGroupTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name"
            }
        ));
        noGroupTable.setEditingRow(0);
        noGroupTable.setRowHeight(25);
        noGroupTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noGroupTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(noGroupTable);

        inGroup.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        inGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Group Number", "ID number", "Student Name", "Adviser"
            }
        ));
        inGroup.setEditingRow(0);
        inGroup.setRowHeight(25);
        inGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inGroupMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(inGroup);

        KickGroup.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        KickGroup.setText("Remove");
        KickGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KickGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(KickGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(KickGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 1120, 800));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Group Number");

        groupN.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        AddUser.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        AddUser.setText("Add");
        AddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(groupN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addComponent(AddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(510, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 620, 800));

        Exit.setBackground(new java.awt.Color(255, 51, 51));
        Exit.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        Exit.setText("EXIT");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });
        jPanel1.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 920, 190, 60));

        studentID.setText("jLabel6");
        jPanel1.add(studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 930, -1, -1));

        LOGO.setIcon(new javax.swing.ImageIcon("C:\\Users\\John Tyronn Ocso\\Desktop\\logo.png")); // NOI18N
        jPanel1.add(LOGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, -40, 1310, 1130));

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

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        this.setVisible(false);
        new CreateRubricks().setVisible(true);
    }//GEN-LAST:event_ExitMouseClicked

    private void AddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUserActionPerformed
        
        String query = "INSERT INTO `classgroup`(`groupId`, `class_List_id`) SELECT "+groupN.getText()+",`id` FROM `classlist` where studentId = "+ studentID.getText();
        execute_Query(query,"Joined group"+groupN.getText());
        new CreateGroup();
    }//GEN-LAST:event_AddUserActionPerformed

    private void noGroupTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noGroupTableMouseClicked
        // TODO add your handling code here:
        int i = noGroupTable.getSelectedRow();
        TableModel model = noGroupTable.getModel();
        studentID.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_noGroupTableMouseClicked

    private void inGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inGroupMouseClicked
        // TODO add your handling code here:
        int i = inGroup.getSelectedRow();
        TableModel model = inGroup.getModel();
        studentID.setText(model.getValueAt(i, 1).toString());
        JOptionPane.showMessageDialog(null, studentID.getText());
        
    }//GEN-LAST:event_inGroupMouseClicked

    private void KickGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KickGroupActionPerformed
        JOptionPane.showMessageDialog(null, studentID.getText());
        String query = "DELETE FROM `classgroup` WHERE class_List_id = "+ studentID.getText();
        execute_Query(query,"Kicked from group"+groupN.getText());
        new CreateGroup();
        this.dispose();
    }//GEN-LAST:event_KickGroupActionPerformed

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
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateGroup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddUser;
    private javax.swing.JButton Exit;
    private javax.swing.JButton KickGroup;
    private javax.swing.JLabel LOGO;
    private javax.swing.JTextField groupN;
    private javax.swing.JTable inGroup;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable noGroupTable;
    private javax.swing.JLabel studentID;
    // End of variables declaration//GEN-END:variables
}
