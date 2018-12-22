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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    public Evaluate(int idE, String fName) {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        //init
           initComponents();
       
        System.out.println("ID: "+idE);
           System.out.println("Name: "+fName);
        String rNr = ""+idE;
                
        jLabel2.setText(rNr);
        EvalName.setText(fName);
        JOptionPane.showMessageDialog(null, jLabel2.getText()+" "+EvalName.getText());
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
    
//   public ArrayList<Question> getQuestionList(String query){
//        ArrayList<Question> queList = new ArrayList<Question>();
//        Connection con = getConnection();
//        String que =query;
//        Statement st;
//        ResultSet rs;
//        
//        try{
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            Question qwe;
//            while(rs.next()){
//                qwe = new Question(rs.getInt("eq_id"),rs.getInt("eq_ms"),rs.getString("eq_q"),rs.getString("eq_type"));
//                queList.add(qwe);
//            }
//        }catch(Exception e){
//         JOptionPane.showMessageDialog(null, "getQuestion?");
//         e.printStackTrace();
//        }
//        return queList;
//    }
//    public ArrayList<Faculty> getFacultyList(){
//         ArrayList<Faculty> facList = new ArrayList<Faculty>();
//         Connection con = getConnection();
//         String query = "SELECT * FROM `faculty`";
//         Statement st;
//         ResultSet rs;
//
//         try{
//             st = con.createStatement();
//             rs = st.executeQuery(query);
//             Faculty fac;
//             while(rs.next()){
//                 fac = new Faculty(rs.getInt("faculty_Id"),rs.getString("facultyName"),rs.getString("ffield"),rs.getString("gender"),rs.getString("contactInfo"));
// //                fac = new Faculty(rs.getInt("faculty_Id"),rs.getString("fullName"),rs.getString("course"),rs.getString("birthdate"),rs.getString("gender"));
//                 facList.add(fac);
//             }
//         }catch(Exception e){
//          JOptionPane.showMessageDialog(null, "getFaculty?");
//          e.printStackTrace();
//         }
//         return facList;
//     }
//      public ArrayList<Student> getStudentList(){
//        ArrayList<Student> studList = new ArrayList<Student>();
//        Connection con = getConnection();
//        String query = "SELECT * FROM `students`";
//        Statement st;
//        ResultSet rs;
//        
//        try{
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            Student stu;
//            while(rs.next()){
//                stu = new Student(rs.getInt("idNumber"),rs.getString("fullName"),rs.getString("course"),rs.getString("birthdate"),rs.getString("gender"));
//                studList.add(stu);
//            }
//        }catch(Exception e){
//         JOptionPane.showMessageDialog(null, "getStudent?");
//         e.printStackTrace();
//        }
//        return studList;
//    }
   public ArrayList<Question> getQuestionList(String query){
        ArrayList<Question> queList = new ArrayList<Question>();
        Connection con = getConnection();
        String que =query;
        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            Question qwe;
            while(rs.next()){
                qwe = new Question(rs.getInt("eq_id"),rs.getInt("eq_ms"),rs.getString("eq_q"),rs.getString("eq_type"));
                queList.add(qwe);
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "getQuestion?");
         e.printStackTrace();
        }
        return queList;
    }
   
    public void knowUser(){
        Connection con = getConnection();
        
        try{
            Statement st;
            ResultSet rs;
            ResultSet rs2;
            PreparedStatement pst = con.prepareStatement("SELECT `idNumber`, `fullName`, `course`, `birthdate`, `gender`, `password` FROM `students` WHERE `idNumber` = "+jLabel2.getText()+" AND `fullName` = '"+EvalName.getText()+"'");
            PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `faculty` WHERE `faculty_Id` = "+jLabel2.getText()+" AND `facultyName` = '"+EvalName.getText()+"'");
            
            rs= pst.executeQuery();
            rs2 = pst2.executeQuery();
            
            if (rs.next() == false) { 
                System.out.println("Data not found");
            } else {    
                do {
                    displayQuestions("SELECT `eq_id`, `eq_ms`, `eq_q`, `eq_type` FROM `evalquestion` WHERE `eq_type` = 'Student'");
                    String data = rs.getString("fullName");
                    System.out.println(data);
                }while (rs.next()); 
            }
            
            if (rs2.next() == false) { 
                System.out.println("data not found");
            } else {
                do {
                    displayQuestions("SELECT `eq_id`, `eq_ms`, `eq_q`, `eq_type` FROM `evalquestion` WHERE `eq_type` = 'Teacher'");
                    String data = rs.getString("facultyName");
                    System.out.println(data);
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
                ArrayList<Question> list =  getQuestionList(qu);
                    
                       String[] q = new String[10];
                      for(int i = 0; i < list.size();i++){
                          q[i] = (list.get(i).getEq_q());
                      }
                      
                      q1.setText(q[0]);
                      q2.setText(q[1]);
                      q3.setText(q[2]);
                      q4.setText(q[3]);
                      q5.setText(q[4]);
                      q6.setText(q[5]);
                      q7.setText(q[6]);
                      q8.setText(q[7]);
                      q9.setText(q[8]);
                      q10.setText(q[9]);
                      
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
        EvalName = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        qpanel = new javax.swing.JPanel();
        q1 = new javax.swing.JLabel();
        q2 = new javax.swing.JLabel();
        q3 = new javax.swing.JLabel();
        q4 = new javax.swing.JLabel();
        q5 = new javax.swing.JLabel();
        q6 = new javax.swing.JLabel();
        q7 = new javax.swing.JLabel();
        q8 = new javax.swing.JLabel();
        q9 = new javax.swing.JLabel();
        q10 = new javax.swing.JLabel();
        score = new javax.swing.JComboBox<>();
        score1 = new javax.swing.JComboBox<>();
        score2 = new javax.swing.JComboBox<>();
        score3 = new javax.swing.JComboBox<>();
        score4 = new javax.swing.JComboBox<>();
        score5 = new javax.swing.JComboBox<>();
        score6 = new javax.swing.JComboBox<>();
        score7 = new javax.swing.JComboBox<>();
        score8 = new javax.swing.JComboBox<>();
        score9 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fcId = new javax.swing.JLabel();
        Retrun = new javax.swing.JButton();
        LOGO = new javax.swing.JLabel();
        idForE = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        EvalName.setFont(new java.awt.Font("Times New Roman", 2, 30)); // NOI18N
        EvalName.setText("Name of who you are evaluating");

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));

        qpanel.setBackground(new java.awt.Color(255, 255, 255));

        q1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q2.setText("jLabel2");
        q2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q3.setText("jLabel2");
        q3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q4.setText("jLabel2");
        q4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q5.setText("jLabel2");
        q5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q6.setText("jLabel2");
        q6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q7.setText("jLabel2");
        q7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q8.setText("jLabel2");
        q8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q9.setText("jLabel2");
        q9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        q10.setText("jLabel2");
        q10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        score.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        score9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout qpanelLayout = new javax.swing.GroupLayout(qpanel);
        qpanel.setLayout(qpanelLayout);
        qpanelLayout.setHorizontalGroup(
            qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qpanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q10, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q9, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q8, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q7, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q6, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(score1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q5, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q4, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qpanelLayout.createSequentialGroup()
                        .addComponent(q3, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(score2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        qpanelLayout.setVerticalGroup(
            qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qpanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(qpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(qpanel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("ID number");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(EvalName, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(EvalName, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 970, 910));
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
        jPanel1.add(Retrun, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1000, 190, 60));
        jPanel1.add(LOGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, -70, 1310, 1130));
        jPanel1.add(idForE, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1030, -1, -1));

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

    private void RetrunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetrunActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new adminDash().setVisible(true);
    }//GEN-LAST:event_RetrunActionPerformed

    private void RetrunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RetrunMouseClicked

    }//GEN-LAST:event_RetrunMouseClicked

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
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton Retrun;
    private javax.swing.JLabel fcId;
    private javax.swing.JLabel idForE;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel q1;
    private javax.swing.JLabel q10;
    private javax.swing.JLabel q2;
    private javax.swing.JLabel q3;
    private javax.swing.JLabel q4;
    private javax.swing.JLabel q5;
    private javax.swing.JLabel q6;
    private javax.swing.JLabel q7;
    private javax.swing.JLabel q8;
    private javax.swing.JLabel q9;
    private javax.swing.JPanel qpanel;
    private javax.swing.JComboBox<String> score;
    private javax.swing.JComboBox<String> score1;
    private javax.swing.JComboBox<String> score2;
    private javax.swing.JComboBox<String> score3;
    private javax.swing.JComboBox<String> score4;
    private javax.swing.JComboBox<String> score5;
    private javax.swing.JComboBox<String> score6;
    private javax.swing.JComboBox<String> score7;
    private javax.swing.JComboBox<String> score8;
    private javax.swing.JComboBox<String> score9;
    // End of variables declaration//GEN-END:variables
}
