/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "doubtcomment", catalog = "doubtcart", schema = "")
@NamedQueries({
    @NamedQuery(name = "Doubtcomment.findAll", query = "SELECT d FROM Doubtcomment d"),
    @NamedQuery(name = "Doubtcomment.findById", query = "SELECT d FROM Doubtcomment d WHERE d.id = :id"),
    @NamedQuery(name = "Doubtcomment.findByComment", query = "SELECT d FROM Doubtcomment d WHERE d.comment = :comment"),
    @NamedQuery(name = "Doubtcomment.findByDdate", query = "SELECT d FROM Doubtcomment d WHERE d.ddate = :ddate")})
public class Doubtcomment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "Comment", nullable = false, length = 2000)
    private String comment;
    @Column(name = "ddate")
    @Temporal(TemporalType.DATE)
    private Date ddate;
    @JoinColumn(name = "DoubtID", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false)
    private Doubt doubtID;
    @JoinColumn(name = "UserID", referencedColumnName = "Username", nullable = false)
    @ManyToOne(optional = false)
    private User userID;

    public Doubtcomment() {
    }



    public Doubtcomment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public Doubt getDoubtID() {
        return doubtID;
    }

    public void setDoubtID(Doubt doubtID) {
        this.doubtID = doubtID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doubtcomment)) {
            return false;
        }
        Doubtcomment other = (Doubtcomment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Doubtcomment[ id=" + id + " ]";
    }
    
}
