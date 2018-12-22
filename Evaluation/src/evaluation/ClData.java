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
public class ClData {
    private int clId;
    private int class_id;
    private int studentId;
    
    public ClData(int clId, int class_id, int studentId) {
        this.clId = clId;
        this.class_id = class_id;
        this.studentId = studentId;
    }
    
    public int getClId() {
        return clId;
    }

    public void setClId(int clId) {
        this.clId = clId;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

  
    
}
