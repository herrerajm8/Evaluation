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
public class Rubrick {
    private int rubrickId;
    private int atongID;
    private String name;
    private String type;
    private int percentEdge;
    private int maxScore;
    private String rTitle;
    private String coLn2;
    private String coLn3;
    private String coLn4;
    private String coLn5;
    private String coLn6;

    public Rubrick(int atongID,int rubrickId, int percentEdge, int maxScore, String rTitle, String coLn2, String coLn3, String coLn4, String coLn5, String coLn6) {
        this.atongID = atongID;
        this.rubrickId = rubrickId;
        this.rTitle = rTitle;
        this.coLn2 = coLn2;
        this.coLn3 = coLn3;
        this.coLn4 = coLn4;
        this.coLn5 = coLn5;
        this.coLn6 = coLn6;
        this.percentEdge = percentEdge;
        this.maxScore = maxScore;
    }
    
    
    public Rubrick(int rubrickId, String name, String type) {
        this.rubrickId = rubrickId;
        this.name = name;
        this.type = type;
    }

    public int getAtongID() {
        return atongID;
    }

    public void setAtongID(int atongID) {
        this.atongID = atongID;
    }

    public String getrTitle() {
        return rTitle;
    }

    public void setrTitle(String rTitle) {
        this.rTitle = rTitle;
    }

    public String getCoLn2() {
        return coLn2;
    }

    public void setCoLn2(String coLn2) {
        this.coLn2 = coLn2;
    }
    
    
    public int getPercentEdge() {
        return percentEdge;
    }

    public void setPercentEdge(int percentEdge) {
        this.percentEdge = percentEdge;
    }
    
    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
    public String getRTitle() {
        return rTitle;
    }

    public void setRTitle(String rTitle) {
        this.rTitle = rTitle;
    }

    public String getColn2() {
        return coLn2;
    }

    public void setColn2(String coLn2) {
        this.coLn2 = coLn2;
    }

    public String getCoLn3() {
        return coLn3;
    }

    public void setColn3(String coLn3) {
        this.coLn3 = coLn3;
    }

    public String getCoLn4() {
        return coLn4;
    }

    public void setCoLn4(String coLn4) {
        this.coLn4 = coLn4;
    }

    public String getCoLn5() {
        return coLn5;
    }

    public void setCoLn5(String coLn5) {
        this.coLn5 = coLn5;
    }

    public String getCoLn6() {
        return coLn6;
    }

    public void setCoLn6(String coLn6) {
        this.coLn6 = coLn6;
    }



    
    public int getRubrickId() {
        return rubrickId;
    }

    public void setRubrickId(int rubrickId) {
        this.rubrickId = rubrickId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

}
