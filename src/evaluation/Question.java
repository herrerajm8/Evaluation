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
public class Question {
    private int eq_id;
    private int eq_ms;
    private String eq_q;
    private String eq_type;

    public Question(int eq_id, int eq_ms, String eq_q, String eq_type) {
        this.eq_id = eq_id;
        this.eq_ms = eq_ms;
        this.eq_q = eq_q;
        this.eq_type = eq_type;
    }

    public int getEq_id() {
        return eq_id;
    }

    public void setEq_id(int eq_id) {
        this.eq_id = eq_id;
    }

    public int getEq_ms() {
        return eq_ms;
    }

    public void setEq_ms(int eq_ms) {
        this.eq_ms = eq_ms;
    }

    public String getEq_q() {
        return eq_q;
    }

    public void setEq_q(String eq_q) {
        this.eq_q = eq_q;
    }

    public String getEq_type() {
        return eq_type;
    }

    public void setEq_type(String eq_type) {
        this.eq_type = eq_type;
    }
    
    
}
