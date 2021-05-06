/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adams
 */
@Entity
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findByGameId", query = "SELECT g FROM Game g WHERE g.gameId = :gameId"),
    @NamedQuery(name = "Game.findByName", query = "SELECT g FROM Game g WHERE g.name = :name"),
    @NamedQuery(name = "Game.findByDescriptionOfGame", query = "SELECT g FROM Game g WHERE g.descriptionOfGame = :descriptionOfGame"),
    @NamedQuery(name = "Game.findByDev", query = "SELECT g FROM Game g WHERE g.dev = :dev"),
    @NamedQuery(name = "Game.findByReleaseDate", query = "SELECT g FROM Game g WHERE g.releaseDate = :releaseDate"),
    @NamedQuery(name = "Game.findByPrice", query = "SELECT g FROM Game g WHERE g.price = :price"),
    @NamedQuery(name = "Game.findByIsActive", query = "SELECT g FROM Game g WHERE g.isActive = :isActive")})
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "game_id")
    private Integer gameId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description_of_game")
    private String descriptionOfGame;
    @Column(name = "dev")
    private String dev;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name = "price")
    private Integer price;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "gameId")
    private Collection<GameGenre> gameGenreCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Achievement> achievementCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Review> reviewCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Statistics> statisticsCollection;

    private String genre;

    public Game() {
    }

    public Game(Integer gameId) {
        this.gameId = gameId;
    }

    public Game(Integer gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }

    public Game(String name, String description, String dev, java.sql.Date releaseDate, Integer price) {
        this.name = name;
        this.descriptionOfGame = description;
        this.dev = dev;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Game(Integer gameId, String name, String description, String dev, java.sql.Date releaseDate, Integer price, Genre genre) {
        this.gameId = gameId;
        this.name = name;
        this.descriptionOfGame = description;
        this.dev = dev;
        this.releaseDate = releaseDate;
        this.price = price;
        this.genre = genre.getDescriptionOfGenre();
    }
    
    public Game(Integer gameId, String name, String description, String dev, java.sql.Date releaseDate, Integer price) {
        this.gameId = gameId;
        this.name = name;
        this.descriptionOfGame = description;
        this.dev = dev;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionOfGame() {
        return descriptionOfGame;
    }

    public void setDescriptionOfGame(String descriptionOfGame) {
        this.descriptionOfGame = descriptionOfGame;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    @XmlTransient
    public Collection<Achievement> getAchievementCollection() {
        return achievementCollection;
    }

    public void setAchievementCollection(Collection<Achievement> achievementCollection) {
        this.achievementCollection = achievementCollection;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<Statistics> getStatisticsCollection() {
        return statisticsCollection;
    }

    public void setStatisticsCollection(Collection<Statistics> statisticsCollection) {
        this.statisticsCollection = statisticsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gameId != null ? gameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.gameId == null && other.gameId != null) || (this.gameId != null && !this.gameId.equals(other.gameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + gameId
                + ", name: " + name
                + ", description: " + descriptionOfGame
                + ", dev: " + dev
                + ", releaseDate: " + releaseDate
                + ", price: " + price
                + ", genre: " + genre;
    }

}
