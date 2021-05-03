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
@Table(name = "achievement_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AchievementUser.findAll", query = "SELECT a FROM AchievementUser a"),
    @NamedQuery(name = "AchievementUser.findByAchievementUser", query = "SELECT a FROM AchievementUser a WHERE a.achievementUser = :achievementUser"),
    @NamedQuery(name = "AchievementUser.findByObtained", query = "SELECT a FROM AchievementUser a WHERE a.obtained = :obtained"),
    @NamedQuery(name = "AchievementUser.findByIsActive", query = "SELECT a FROM AchievementUser a WHERE a.isActive = :isActive")})
public class AchievementUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "achievement_user")
    private Integer achievementUser;
    @Column(name = "obtained")
    @Temporal(TemporalType.TIMESTAMP)
    private Date obtained;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "achievement_id", referencedColumnName = "achievement_id")
    @ManyToOne
    private Achievement achievementId;

    public AchievementUser() {
    }

    public AchievementUser(Integer achievementUser) {
        this.achievementUser = achievementUser;
    }

    public Integer getAchievementUser() {
        return achievementUser;
    }

    public void setAchievementUser(Integer achievementUser) {
        this.achievementUser = achievementUser;
    }

    public Date getObtained() {
        return obtained;
    }

    public void setObtained(Date obtained) {
        this.obtained = obtained;
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

    public Achievement getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Achievement achievementId) {
        this.achievementId = achievementId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (achievementUser != null ? achievementUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AchievementUser)) {
            return false;
        }
        AchievementUser other = (AchievementUser) object;
        if ((this.achievementUser == null && other.achievementUser != null) || (this.achievementUser != null && !this.achievementUser.equals(other.achievementUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modell.AchievementUser[ achievementUser=" + achievementUser + " ]";
    }

}
