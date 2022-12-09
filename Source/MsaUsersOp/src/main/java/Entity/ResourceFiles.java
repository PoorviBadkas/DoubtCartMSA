/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "resource_files", catalog = "doubtcart", schema = "")
@NamedQueries({
    @NamedQuery(name = "ResourceFiles.findAll", query = "SELECT r FROM ResourceFiles r"),
    @NamedQuery(name = "ResourceFiles.findById", query = "SELECT r FROM ResourceFiles r WHERE r.id = :id"),
    @NamedQuery(name = "ResourceFiles.findByUrl", query = "SELECT r FROM ResourceFiles r WHERE r.url = :url")})
public class ResourceFiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "URL", nullable = false, length = 500)
    private String url;
    @JoinColumn(name = "ResourceID", referencedColumnName = "ResourceID", nullable = false)
    @ManyToOne(optional = false)
    private Resource resourceID;

    public ResourceFiles() {
    }

    public ResourceFiles(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Resource getResourceID() {
        return resourceID;
    }

    public void setResourceID(Resource resourceID) {
        this.resourceID = resourceID;
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
        if (!(object instanceof ResourceFiles)) {
            return false;
        }
        ResourceFiles other = (ResourceFiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ResourceFiles[ id=" + id + " ]";
    }
    
}
