/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

/**
 *
 * @author John Tyronn Ocso
 */
public class Student {
    private int idNumber;
    private String fullName;
    private String course;
    private String birthdate;
    private String gender;
    private String password;
    private int groupId;
    private int class_List_id;

    public Student(int idNumber, String fullName, int groupId, int class_List_id) {
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.groupId = groupId;
        this.class_List_id = class_List_id;
    }

    
    
    public Student( String fullName, String course, String birthdate, String gender, String password) {
        this.fullName = fullName;
        this.course = course;
        this.birthdate = birthdate;
        this.gender = gender;
        this.password = password;
    }
    
    
    
    
    public Student(int idNumber, String fullName, String course, String birthdate, String gender) {
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.course = course;
        this.birthdate = birthdate;
        this.gender = gender;
    }
    

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getclass_List_id() {
        return class_List_id;
    }

    public void setclass_List_id(int class_List_id) {
        this.class_List_id = class_List_id;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
