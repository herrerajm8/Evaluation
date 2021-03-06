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
public class DashTeacherEval extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public DashTeacherEval() {
        //fullscreen
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
        
        initComponents();
        Show_Eval_table();
        
        //select 
        
        Toolkit tk = Toolkit.getDefaultToolkit();
            int xsize = (int) tk.getScreenSize().getWidth();
            int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
        jPanel2.setBackground(new Color(0,0,0,30));
        
    }
    public DashTeacherEval(int idNum, String atorName) {
        //fullscreen
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
        
        initComponents();
        idNumber.setText(idNum+"");
        idNumber1.setText(idNum+"");
        etorName.setText(atorName);
        Show_Eval_table();
        
        //select 
        
        Toolkit tk = Toolkit.getDefaultToolkit();
            int xsize = (int) tk.getScreenSize().getWidth();
            int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
        jPanel2.setBackground(new Color(0,0,0,30));
        idNumber.setText(idNum+"");
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
    public ArrayList<Project> getProjectList(){
        ArrayList<Project> proList = new ArrayList<Project>();
        Connection con = getConnection();
        String query = "SELECT DISTINCT  students.idNumber AS 'userID', students.fullName AS 'userName'\n" +
"from `students` \n" +
"JOIN classlist ON classlist.studentId = students.idNumber\n" +
"JOIN classgroup ON classlist.id = classgroup.class_List_id\n" +
"JOIN projects ON classgroup.groupId = projects.group_id\n" +
"JOIN faculty  ON faculty.faculty_Id = adviser_name OR faculty.faculty_Id = panel1 OR faculty.faculty_Id = panel2 OR faculty.faculty_Id = panel3\n" +
"WHERE projects.adviser_name = "+idNumber1.getText()+" OR projects.group_id = "+idNumber1.getText()+" OR projects.panel1 = "+idNumber1.getText()+" OR projects.panel2 = "+idNumber1.getText()+" OR projects.panel3 ="+idNumber1.getText()+"\n" +
"UNION\n" +
"SELECT DISTINCT projects.project_id AS 'userID', projects.project_name AS 'userName'\n"+
"from `students`\n"+
"JOIN classlist ON classlist.studentId = students.idNumber\n"+
"JOIN classgroup ON classlist.id = classgroup.class_List_id\n"+
"JOIN projects ON classgroup.groupId = projects.group_id\n"+
"JOIN faculty  ON faculty.faculty_Id = adviser_name OR faculty.faculty_Id = panel1 OR faculty.faculty_Id = panel2 OR faculty.faculty_Id = panel3\n"+
"WHERE projects.adviser_name =  "+idNumber1.getText()+" OR projects.group_id =  "+idNumber1.getText()+" OR projects.panel1 =  "+idNumber1.getText()+" OR projects.panel2 =  "+idNumber1.getText()+" OR projects.panel3 =  "+idNumber1.getText()+" ";
//        System.out.println(query);
        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            Project pro;
            while(rs.next()){
                pro = new Project(rs.getInt("userID"),rs.getString("userName"));
                proList.add(pro);
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "getStudent?");
         e.printStackTrace();
        }
        return proList;
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
     public void Show_Eval_table(){
        ArrayList<Project> list =  getProjectList();
        DefaultTableModel model = (DefaultTableModel)Evaluate.getModel();
        
        Object[] row = new Object[5];
        for(int i = 0; i < list.size();i++){
            row[0] = list.get(i).getIdNumber();
            row[1] = list.get(i).getStudentName();
            row[2] = list.get(i).getAdviser_name();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Evaluate = new javax.swing.JTable();
        Exit = new javax.swing.JButton();
        evate = new javax.swing.JButton();
        idNumber = new javax.swing.JLabel();
        nameE = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        idNumber1 = new javax.swing.JLabel();
        etorName = new javax.swing.JLabel();
        projectID = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        Evaluate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Full Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Evaluate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EvaluateMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Evaluate);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 1150, 800));

        Exit.setBackground(new java.awt.Color(255, 51, 51));
        Exit.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        Exit.setText("Log Out");
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
        jPanel1.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 930, 127, 60));

        evate.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        evate.setText("Evaluate");
        evate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evateActionPerformed(evt);
            }
        });
        jPanel1.add(evate, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 930, 124, 64));
        jPanel1.add(idNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 960, -1, -1));
        jPanel1.add(nameE, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 960, -1, -1));
        jPanel1.add(LOGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, -60, 1310, 1130));
        jPanel1.add(idNumber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 960, -1, -1));
        jPanel1.add(etorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 960, -1, -1));
        jPanel1.add(projectID, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 960, -1, -1));

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
        
    }//GEN-LAST:event_ExitMouseClicked

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_ExitActionPerformed

    private void evateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evateActionPerformed
        this.setVisible(false);
        System.out.println(idNumber.getText()+nameE.getText());
        new Evaluate( Integer.parseInt(idNumber.getText()),Integer.parseInt(idNumber1.getText()),etorName.getText(), nameE.getText());
    }//GEN-LAST:event_evateActionPerformed

    private void EvaluateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EvaluateMouseClicked
        // TODO add your handling code here:
        
         int i = Evaluate.getSelectedRow();
        TableModel model = Evaluate.getModel();
        idNumber.setText(model.getValueAt(i, 0).toString());
        nameE.setText(model.getValueAt(i, 1).toString());
        System.out.println("eval");
    }//GEN-LAST:event_EvaluateMouseClicked

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
            java.util.logging.Logger.getLogger(DashTeacherEval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashTeacherEval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashTeacherEval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashTeacherEval.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashTeacherEval().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Evaluate;
    private javax.swing.JButton Exit;
    private javax.swing.JLabel LOGO;
    private javax.swing.JLabel etorName;
    private javax.swing.JButton evate;
    private javax.swing.JLabel idNumber;
    private javax.swing.JLabel idNumber1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameE;
    private javax.swing.JLabel projectID;
    // End of variables declaration//GEN-END:variables
}
