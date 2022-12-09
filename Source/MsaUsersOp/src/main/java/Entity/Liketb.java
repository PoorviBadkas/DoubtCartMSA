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

/**
 *
 * @author HP
 */
@Entity
@Table(name = "liketb", catalog = "doubtcart", schema = "")
@NamedQueries({
    @NamedQuery(name = "Liketb.findAll", query = "SELECT l FROM Liketb l"),
    @NamedQuery(name = "Liketb.findByLikeID", query = "SELECT l FROM Liketb l WHERE l.likeID = :likeID"),
    @NamedQuery(name = "Liketb.findByLDate", query = "SELECT l FROM Liketb l WHERE l.lDate = :lDate")})
public class Liketb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LikeID", nullable = false)
    private Integer likeID;
    @Column(name = "lDate")
    @Temporal(TemporalType.DATE)
    private Date lDate;
    @JoinColumn(name = "ResourceID", referencedColumnName = "ResourceID", nullable = false)
    @ManyToOne(optional = false)
    private Resource resourceID;
    @JoinColumn(name = "UserID", referencedColumnName = "Username", nullable = false)
    @ManyToOne(optional = false)
    private User userID;

    public Liketb() {
    }

    public Liketb(Integer likeID) {
        this.likeID = likeID;
    }

    public Integer getLikeID() {
        return likeID;
    }

    public void setLikeID(Integer likeID) {
        this.likeID = likeID;
    }

    public Date getLDate() {
        return lDate;
    }

    public void setLDate(Date lDate) {
        this.lDate = lDate;
    }

    public Resource getResourceID() {
        return resourceID;
    }

    public void setResourceID(Resource resourceID) {
        this.resourceID = resourceID;
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
        hash += (likeID != null ? likeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Liketb)) {
            return false;
        }
        Liketb other = (Liketb) object;
        if ((this.likeID == null && other.likeID != null) || (this.likeID != null && !this.likeID.equals(other.likeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Liketb[ likeID=" + likeID + " ]";
    }
    
}
