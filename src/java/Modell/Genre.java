/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adams
 */
@Entity
@Table(name = "genre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findByGenreId", query = "SELECT g FROM Genre g WHERE g.genreId = :genreId"),
    @NamedQuery(name = "Genre.findByDescriptionOfGenre", query = "SELECT g FROM Genre g WHERE g.descriptionOfGenre = :descriptionOfGenre"),
    @NamedQuery(name = "Genre.findByIsActive", query = "SELECT g FROM Genre g WHERE g.isActive = :isActive")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "genre_id")
    private Integer genreId;
    @Column(name = "description_of_genre")
    private String descriptionOfGenre;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "genreId")
    private Collection<GameGenre> gameGenreCollection;

    public Genre() {
    }

    public Genre(Integer genreId) {
        this.genreId = genreId;
    }

    public Genre(String descriptionOfGenre) {
        this.descriptionOfGenre = descriptionOfGenre;
    }

    public Genre(Integer id, String name) {
        this.genreId = id;
        this.descriptionOfGenre = name;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getDescriptionOfGenre() {
        return descriptionOfGenre;
    }

    public void setDescriptionOfGenre(String descriptionOfGenre) {
        this.descriptionOfGenre = descriptionOfGenre;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<GameGenre> getGameGenreCollection() {
        return gameGenreCollection;
    }

    public void setGameGenreCollection(Collection<GameGenre> gameGenreCollection) {
        this.gameGenreCollection = gameGenreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreId != null ? genreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.genreId == null && other.genreId != null) || (this.genreId != null && !this.genreId.equals(other.genreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + genreId
                + ", description: " + descriptionOfGenre;

    }

}
