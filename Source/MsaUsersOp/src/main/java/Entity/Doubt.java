/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author HP
 */
@Entity
@Table(name = "doubt", catalog = "doubtcart", schema = "")
@NamedQueries({
    @NamedQuery(name = "Doubt.findAll", query = "SELECT d FROM Doubt d"),
    @NamedQuery(name = "Doubt.findById", query = "SELECT d FROM Doubt d WHERE d.id = :id"),
    @NamedQuery(name = "Doubt.findByTitle", query = "SELECT d FROM Doubt d WHERE d.title LIKE :title"),
    @NamedQuery(name = "Doubt.findByPoint", query = "SELECT d FROM Doubt d WHERE d.point = :point"),
    @NamedQuery(name = "Doubt.findByIsClosed", query = "SELECT d FROM Doubt d WHERE d.isClosed = :isClosed")})
public class Doubt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "Title", nullable = false, length = 1000)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Description", nullable = false, length = 65535)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Point", nullable = false)
    private int point;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsClosed", nullable = false)
    private boolean isClosed;
    @ManyToMany(mappedBy = "doubtCollection")
    private Collection<Category> categoryCollection;
    @ManyToMany(mappedBy = "doubtCollection")
    private Collection<Tags> tagsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doubtID")
    private Collection<Answer> answerCollection;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne
    private User username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doubtID")
    private Collection<Doubtcomment> doubtcommentCollection;

    public Doubt() {
    }



    public Doubt(String title, String description, int point, boolean isClosed) {
        this.title = title;
        this.description = description;
        this.point = point;
        this.isClosed = isClosed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    @JsonbTransient
    public Collection<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    @JsonbTransient
    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }

    @JsonbTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @JsonbTransient
    public Collection<Doubtcomment> getDoubtcommentCollection() {
        return doubtcommentCollection;
    }

    public void setDoubtcommentCollection(Collection<Doubtcomment> doubtcommentCollection) {
        this.doubtcommentCollection = doubtcommentCollection;
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
        if (!(object instanceof Doubt)) {
            return false;
        }
        Doubt other = (Doubt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Doubt[ id=" + id + " ]";
    }
    
}
