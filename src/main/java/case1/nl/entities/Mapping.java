/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "mapping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mapping.findAll", query = "SELECT m FROM Mapping m")
    , @NamedQuery(name = "Mapping.findById", query = "SELECT m FROM Mapping m WHERE m.id = :id")
    , @NamedQuery(name = "Mapping.findByFromURL", query = "SELECT m FROM Mapping m WHERE m.fromURL = :fromURL")
    , @NamedQuery(name = "Mapping.findByToURL", query = "SELECT m FROM Mapping m WHERE m.toURL = :toURL")})
public class Mapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fromURL")
    private String fromURL;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "toURL")
    private String toURL;

    public Mapping() {
    }

    public Mapping(Integer id) {
        this.id = id;
    }

    public Mapping(Integer id, String fromURL, String toURL) {
        this.id = id;
        this.fromURL = fromURL;
        this.toURL = toURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromURL() {
        return fromURL;
    }

    public void setFromURL(String fromURL) {
        this.fromURL = fromURL;
    }

    public String getToURL() {
        return toURL;
    }

    public void setToURL(String toURL) {
        this.toURL = toURL;
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
        if (!(object instanceof Mapping)) {
            return false;
        }
        Mapping other = (Mapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case1.nl.entities.Mapping[ id=" + id + " ]";
    }
    
}
