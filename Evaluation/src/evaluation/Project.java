/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

/**
 *
 * @author Administrator
 */
public class Project {
    private int project_Id;
    private int idNumber;
    private String adviser_name;
    private int group_id;
    private String panel1;
    private String panel2;
    private String panel3;
    private String project_name;
    private String studentName;

    public Project(int idNumber, String studentName) {
        this.idNumber = idNumber;
        this.studentName = studentName;
    }
    
    
    public Project(int project_Id, String adviser_name, int group_id, String panel1, String panel2, String panel3, String project_name) {
        this.project_Id = project_Id;
        this.adviser_name = adviser_name;
        this.group_id = group_id;
        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.project_name = project_name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public int getProject_Id() {
        return project_Id;
    }

    public void setProject_Id(int project_Id) {
        this.project_Id = project_Id;
    }

    public String getAdviser_name() {
        return adviser_name;
    }

    public void setAdviser_name(String adviser_name) {
        this.adviser_name = adviser_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getPanel1() {
        return panel1;
    }

    public void setPanel1(String panel1) {
        this.panel1 = panel1;
    }

    public String getPanel2() {
        return panel2;
    }

    public void setPanel2(String panel2) {
        this.panel2 = panel2;
    }

    public String getPanel3() {
        return panel3;
    }

    public void setPanel3(String panel3) {
        this.panel3 = panel3;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
    

            
}
