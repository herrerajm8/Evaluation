/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
//import java.sq
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author John Tyronn Ocso
 */
public class Evaluate extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Evaluate() {
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
        
        this.setSize(xsize,ysize);
        
        jPanel2.setBackground(new Color(0,0,0,30));
        
    }
    public Evaluate(int idE, int atorId,String atorName,String fName) {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
           initComponents();
       
        System.out.println("ID: "+idE);
           System.out.println("Name: "+fName);
        String rNr = ""+idE;
           
        evalwait.setText(rNr);
        EvalName.setText(fName);
        
        evalAtor.setText(atorId+"");
        evalAtorName.setText(atorName);
        
        knowUser();
        //select 
      
        Toolkit tk = Toolkit.getDefaultToolkit();
            int xsize = (int) tk.getScreenSize().getWidth();
            int ysize = (int) tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
        
        jPanel2.setBackground(new Color(0,0,0,30));
                
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
     public void execute_Query(String query){
        Connection con = getConnection();
        Statement st;
        try {
            st=con.createStatement();
            if((st.executeUpdate(query))==1){
                
            }else{
                st.executeUpdate("ROLLBACK to saved;");
                JOptionPane.showMessageDialog(null, "EMPTY FIELD || ENTER");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   public ArrayList<Rubrick> getCriteria(String query){
        ArrayList<Rubrick> rubList = new ArrayList<Rubrick>();
        Connection con = getConnection();
        String que =query;
        System.out.println("Get criteria");
        System.out.println(query);
        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            Rubrick cre;
            while(rs.next()){
                cre = new Rubrick(rs.getInt("atongId"),rs.getInt("rubrickId"),rs.getInt("percentEdge"),rs.getInt("maxScore"),rs.getString("rTitle"),rs.getString("coLn2"),rs.getString("coLn3"),rs.getString("coLn4"),rs.getString("coLn5"),rs.getString("coLn6"));
                rubList.add(cre);
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "getQuestion?");
         e.printStackTrace();
        }
        return rubList;
    }
   
    public void knowUser(){
        Connection con = getConnection();
        
        try{
            Statement st;
            ResultSet rs;
            ResultSet rs2;
            PreparedStatement pst = con.prepareStatement("SELECT `idNumber`, `fullName`, `course`, `birthdate`, `gender`, `password` FROM `students` WHERE `idNumber` = "+evalwait.getText()+" AND `fullName` = '"+EvalName.getText()+"'");
//            System.out.println("SELECT * FROM `projects` WHERE `projects`.`project_name` = '"+EvalName.getText()+"' AND `projects`.`project_id` =  "+evalwait.getText()+"");
            PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `projects` WHERE `projects`.`project_name` = '"+EvalName.getText()+"' AND `projects`.`project_id` =  "+evalwait.getText()+"");
            
            rs= pst.executeQuery();
            rs2 = pst2.executeQuery();
            
            if (rs.next() == false) { 
                System.out.println("2 Data not found");
            } else {    
                do {
                  
                  displayQuestions("SELECT `atongId`,`rubrickcontent`.`rubrickId`, `percentEdge`, `maxScore`, `rTitle`, `coLn2`, `coLn3`, `coLn4`, `coLn5`, `coLn6` FROM `rubrick` JOIN `rubrickcontent` ON `rubrick`.`rubrickId` = `rubrickcontent`.`rubrickId` WHERE `rubrick`.`type` = 'Individual' ");
                    String data = rs.getString("fullName");
                    System.out.println("User name: "+data);
                }while (rs.next()); 
            }
            
            if (rs2.next() == false) { 
                System.out.println("1 data not found");
            } else {
                do {
                    displayQuestions("SELECT `atongId`,`rubrickcontent`.`rubrickId`, `percentEdge`, `maxScore`, `rTitle`, `coLn2`, `coLn3`, `coLn4`, `coLn5`, `coLn6` FROM `rubrick` JOIN `rubrickcontent` ON `rubrick`.`rubrickId` = `rubrickcontent`.`rubrickId` WHERE `rubrick`.`type` = 'Group' ");
                    String data = rs.getString("project_name");
                    System.out.println("Project name"+data);
                }while (rs.next()); 
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void displayQuestions(String qu){
      Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/usc_eval?zeroDateTimeBehavior=convertToNull","root","");
//                while(rs.next()){
//                    System.out.println("a");
//                }
                ArrayList<Rubrick> list =  getCriteria(qu);
                
                DefaultTableModel model = (DefaultTableModel)qTable.getModel();
                TableColumnModel cmod = qTable.getColumnModel();
                qTable.setRowHeight(100);
                qTable.setShowHorizontalLines(true);
                qTable.setShowVerticalLines(true);
                Object[] row = new Object[3];
                 
                      if(list.size() == 0){
                          this.setVisible(false);
                          new DashTeacherEval(Integer.parseInt(evalAtor.getText()),evalAtorName.getText());
                          JOptionPane.showMessageDialog(null, "No questions created");
                      }else{
                         for(int i = 0; i < list.size();i++){
                            row[0] = (list.get(i).getRTitle());
                            row[1] = (list.get(i).getMaxScore());
                            model.addRow(row);
                         }
                      }
                      
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ErrorJava: " + e.getMessage());
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
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        qTable = new javax.swing.JTable();
        EvalName = new javax.swing.JLabel();
        Submit = new javax.swing.JButton();
        fcId = new javax.swing.JLabel();
        Retrun = new javax.swing.JButton();
        idForE = new javax.swing.JLabel();
        evalAtor = new javax.swing.JLabel();
        evalAtorName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        evalwait = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));

        qTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Criteria", "Max Score", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        qTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(qTable);
        if (qTable.getColumnModel().getColumnCount() > 0) {
            qTable.getColumnModel().getColumn(0).setResizable(false);
            qTable.getColumnModel().getColumn(1).setMinWidth(80);
            qTable.getColumnModel().getColumn(1).setMaxWidth(50);
            qTable.getColumnModel().getColumn(2).setMinWidth(100);
            qTable.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        EvalName.setFont(new java.awt.Font("Times New Roman", 2, 30)); // NOI18N
        EvalName.setText("fghjgfhj");

        Submit.setBackground(new java.awt.Color(255, 255, 51));
        Submit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EvalName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(EvalName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 900, 910));
        jPanel1.add(fcId, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1040, -1, -1));

        Retrun.setBackground(new java.awt.Color(255, 51, 51));
        Retrun.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        Retrun.setText("Return");
        Retrun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RetrunMouseClicked(evt);
            }
        });
        Retrun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetrunActionPerformed(evt);
            }
        });
        jPanel1.add(Retrun, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 990, 190, 60));

        idForE.setText("dfgbhdfghsdgfhfdgh");
        jPanel1.add(idForE, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 1020, -1, 20));

        evalAtor.setText("fghnfdghdfghjfghjghfj");
        jPanel1.add(evalAtor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 1010, -1, 20));

        evalAtorName.setText("sdfghdfghdfghdfghdfgh");
        jPanel1.add(evalAtorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 1020, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("dfghdfghjdfghj");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 1010, -1, 40));

        evalwait.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(evalwait, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 1000, 110, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1961, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RetrunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetrunActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new DashTeacherEval(Integer.parseInt(evalAtor.getText()), evalAtorName.getText());
        
    }//GEN-LAST:event_RetrunActionPerformed

    private void RetrunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RetrunMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_RetrunMouseClicked

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        Connection con = getConnection();
        int x = qTable.getRowCount();
        String evaluator = evalAtorName.getText();
        String evalatorID = evalAtor.getText();
        String evaluee = EvalName.getText();
        String eueeid = evalwait.getText();
        String criteria, score, maxscore;
        try{
            execute_Query("START TRANSACTION; SAVEPOINT saved;");
            for(int i =0;i<=x;i++){
                TableModel model = qTable.getModel();
                criteria = model.getValueAt(i, 0).toString();
                score = model.getValueAt(i, 1).toString();
                maxscore = model.getValueAt(i, 2).toString();
                System.out.println("\"START TRANSACTION; SAVEPOINT saved; INSERT INTO `evalanswer`(`evaluator`, `evaluator_id`, `evaluee`, `evaluee_id`, `criteria`, `score`, `maxscore`) VALUES ('"+evaluator+"',"+evalatorID+",'"+evaluee+"',"+eueeid+",'"+criteria+"',"+score+","+maxscore+");");
                
                if(i == x){
                    execute_Query("COMMIT");
                }else{
                    execute_Query("INSERT INTO `evalanswer`(`evaluator`, `evaluator_id`, `evaluee`, `evaluee_id`, `criteria`, `score`, `maxscore`) VALUES ('"+evaluator+"',"+evalatorID+",'"+evaluee+"',"+eueeid+",'"+criteria+"',"+score+","+maxscore+")");
                }
//                System.out.println(evaluee+" Id number:"+id+"\nCriteria: "+criteria+"\nScore:  "+score+"\nMax Score "+maxscore);    
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
        
    }//GEN-LAST:event_SubmitActionPerformed

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
            java.util.logging.Logger.getLogger(Evaluate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Evaluate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Evaluate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Evaluate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Evaluate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EvalName;
    private javax.swing.JButton Retrun;
    private javax.swing.JButton Submit;
    private javax.swing.JLabel evalAtor;
    private javax.swing.JLabel evalAtorName;
    private javax.swing.JLabel evalwait;
    private javax.swing.JLabel fcId;
    private javax.swing.JLabel idForE;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTable qTable;
    // End of variables declaration//GEN-END:variables
}
