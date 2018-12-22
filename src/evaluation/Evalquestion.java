/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author John Tyronn Ocso
 */
@Entity
@Table(name = "evalquestion", catalog = "usc_eval", schema = "")
@NamedQueries({
    @NamedQuery(name = "Evalquestion.findAll", query = "SELECT e FROM Evalquestion e")
    , @NamedQuery(name = "Evalquestion.findByEqId", query = "SELECT e FROM Evalquestion e WHERE e.eqId = :eqId")
    , @NamedQuery(name = "Evalquestion.findByEqMs", query = "SELECT e FROM Evalquestion e WHERE e.eqMs = :eqMs")
    , @NamedQuery(name = "Evalquestion.findByEqQ", query = "SELECT e FROM Evalquestion e WHERE e.eqQ = :eqQ")
    , @NamedQuery(name = "Evalquestion.findByEqType", query = "SELECT e FROM Evalquestion e WHERE e.eqType = :eqType")})
public class Evalquestion implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eq_id")
    private Integer eqId;
    @Basic(optional = false)
    @Column(name = "eq_ms")
    private int eqMs;
    @Basic(optional = false)
    @Column(name = "eq_q")
    private String eqQ;
    @Basic(optional = false)
    @Column(name = "eq_type")
    private String eqType;

    public Evalquestion() {
    }

    public Evalquestion(Integer eqId) {
        this.eqId = eqId;
    }

    public Evalquestion(Integer eqId, int eqMs, String eqQ, String eqType) {
        this.eqId = eqId;
        this.eqMs = eqMs;
        this.eqQ = eqQ;
        this.eqType = eqType;
    }

    public Integer getEqId() {
        return eqId;
    }

    public void setEqId(Integer eqId) {
        Integer oldEqId = this.eqId;
        this.eqId = eqId;
        changeSupport.firePropertyChange("eqId", oldEqId, eqId);
    }

    public int getEqMs() {
        return eqMs;
    }

    public void setEqMs(int eqMs) {
        int oldEqMs = this.eqMs;
        this.eqMs = eqMs;
        changeSupport.firePropertyChange("eqMs", oldEqMs, eqMs);
    }

    public String getEqQ() {
        return eqQ;
    }

    public void setEqQ(String eqQ) {
        String oldEqQ = this.eqQ;
        this.eqQ = eqQ;
        changeSupport.firePropertyChange("eqQ", oldEqQ, eqQ);
    }

    public String getEqType() {
        return eqType;
    }

    public void setEqType(String eqType) {
        String oldEqType = this.eqType;
        this.eqType = eqType;
        changeSupport.firePropertyChange("eqType", oldEqType, eqType);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eqId != null ? eqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evalquestion)) {
            return false;
        }
        Evalquestion other = (Evalquestion) object;
        if ((this.eqId == null && other.eqId != null) || (this.eqId != null && !this.eqId.equals(other.eqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evaluation.Evalquestion[ eqId=" + eqId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
