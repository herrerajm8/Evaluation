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
public class Faculty {
    private int faculty_Id;
    private String facultyName;
    private String field;
    private String gender;
    private String contactInfo;
    private String password;
    
    public Faculty(String facultyName, String field, String gender, String contactInfo, String password) {
        this.facultyName = facultyName;
        this.field = field;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.password = password;
    }

    public Faculty(int faculty_Id, String facultyName, String field, String gender, String contactInfo) {
        this.faculty_Id = faculty_Id;
        this.facultyName = facultyName;
        this.field = field;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    
    
    public int getFaculty_Id() {
        return faculty_Id;
    }

    
    public void setFaculty_Id(int faculty_Id) {
        this.faculty_Id = faculty_Id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
