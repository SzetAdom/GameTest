/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "game_genre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GameGenre.findAll", query = "SELECT g FROM GameGenre g"),
    @NamedQuery(name = "GameGenre.findByGameGenreId", query = "SELECT g FROM GameGenre g WHERE g.gameGenreId = :gameGenreId"),
    @NamedQuery(name = "GameGenre.findByIsActive", query = "SELECT g FROM GameGenre g WHERE g.isActive = :isActive")})
public class GameGenre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "game_genre_id")
    private Integer gameGenreId;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    @ManyToOne
    private Genre genreId;
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @ManyToOne
    private Game gameId;

    public GameGenre() {
    }

    public GameGenre(Integer gameGenreId) {
        this.gameGenreId = gameGenreId;
    }

    public Integer getGameGenreId() {
        return gameGenreId;
    }

    public void setGameGenreId(Integer gameGenreId) {
        this.gameGenreId = gameGenreId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gameGenreId != null ? gameGenreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GameGenre)) {
            return false;
        }
        GameGenre other = (GameGenre) object;
        if ((this.gameGenreId == null && other.gameGenreId != null) || (this.gameGenreId != null && !this.gameGenreId.equals(other.gameGenreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.GameGenre[ gameGenreId=" + gameGenreId + " ]";
    }

}
