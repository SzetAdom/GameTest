/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adams
 */
@Entity
@Table(name = "statistics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statistics.findAll", query = "SELECT s FROM Statistics s"),
    @NamedQuery(name = "Statistics.findByStatisticsId", query = "SELECT s FROM Statistics s WHERE s.statisticsId = :statisticsId"),
    @NamedQuery(name = "Statistics.findByFirstPlayed", query = "SELECT s FROM Statistics s WHERE s.firstPlayed = :firstPlayed"),
    @NamedQuery(name = "Statistics.findByLastPlayed", query = "SELECT s FROM Statistics s WHERE s.lastPlayed = :lastPlayed"),
    @NamedQuery(name = "Statistics.findByPlayedMinutes", query = "SELECT s FROM Statistics s WHERE s.playedMinutes = :playedMinutes"),
    @NamedQuery(name = "Statistics.findByIsActive", query = "SELECT s FROM Statistics s WHERE s.isActive = :isActive")})
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "statistics_id")
    private Integer statisticsId;
    @Column(name = "first_played")
    @Temporal(TemporalType.DATE)
    private Date firstPlayed;
    @Column(name = "last_played")
    @Temporal(TemporalType.DATE)
    private Date lastPlayed;
    @Column(name = "played_minutes")
    private Integer playedMinutes;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @ManyToOne
    private Game gameId;

    public Statistics() {
    }

    public Statistics(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Statistics(Integer statisticsId, Game game, User user, java.sql.Date firstPlayed, java.sql.Date lastPlayed, Integer minutes) {
        this.statisticsId = statisticsId;
        this.gameId = game;
        this.userId = user;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.playedMinutes = minutes;
    }
    public Statistics(Game game, User user, Integer minutes) {
        this.gameId = game;
        this.userId = user;
        this.playedMinutes = minutes;
    }

    public Integer getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Date getFirstPlayed() {
        return firstPlayed;
    }

    public void setFirstPlayed(Date firstPlayed) {
        this.firstPlayed = firstPlayed;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public Integer getPlayedMinutes() {
        return playedMinutes;
    }

    public void setPlayedMinutes(Integer playedMinutes) {
        this.playedMinutes = playedMinutes;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        hash += (statisticsId != null ? statisticsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statistics)) {
            return false;
        }
        Statistics other = (Statistics) object;
        if ((this.statisticsId == null && other.statisticsId != null) || (this.statisticsId != null && !this.statisticsId.equals(other.statisticsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + statisticsId
                + ", gameName: " + gameId.getName()
                + ", userName: " + userId.getUsername()
                + ", firstPlayed: " + firstPlayed
                + ", lastPlayed: " + lastPlayed
                + ", playedMinutes: " + playedMinutes;
    }

}
