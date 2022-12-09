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
@Table(name = "comments", catalog = "doubtcart", schema = "")
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findByCommentID", query = "SELECT c FROM Comments c WHERE c.commentID = :commentID"),
    @NamedQuery(name = "Comments.findByResourceID", query = "SELECT c FROM Comments c WHERE c.resourceID.resourceID = :resourceID"),
    @NamedQuery(name = "Comments.findByUserID", query = "SELECT c FROM Comments c WHERE c.userID.username = :userID"),
    @NamedQuery(name = "Comments.findByComment", query = "SELECT c FROM Comments c WHERE c.comment = :comment"),
    @NamedQuery(name = "Comments.findByCdate", query = "SELECT c FROM Comments c WHERE c.cdate = :cdate")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CommentID", nullable = false)
    private Integer commentID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "Comment", nullable = false, length = 2000)
    private String comment;
    @Column(name = "cdate")
    @Temporal(TemporalType.DATE)
    private Date cdate;
    @JoinColumn(name = "ResourceID", referencedColumnName = "ResourceID", nullable = false)
    @ManyToOne(optional = false)
    private Resource resourceID;
    @JoinColumn(name = "UserID", referencedColumnName = "Username", nullable = false)
    @ManyToOne(optional = false)
    private User userID;

    public Comments() {
    }


    public Comments(String comment) {
        this.comment = comment;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
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
        hash += (commentID != null ? commentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.commentID == null && other.commentID != null) || (this.commentID != null && !this.commentID.equals(other.commentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Comments[ commentID=" + commentID + " ]";
    }
    
}
