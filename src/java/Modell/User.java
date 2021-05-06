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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adams
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByKey", query = "SELECT u FROM User u WHERE u.key = :key"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate"),
    @NamedQuery(name = "User.findByIsAdmin", query = "SELECT u FROM User u WHERE u.isAdmin = :isAdmin"),
    @NamedQuery(name = "User.findByCreatedAt", query = "SELECT u FROM User u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "User.findByIsActive", query = "SELECT u FROM User u WHERE u.isActive = :isActive")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "key")
    private String key;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "is_admin")
    private Boolean isAdmin;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "userId")
    private Collection<Review> reviewCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<AchievementUser> achievementUserCollection;
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
    @ManyToOne
    private Gender genderId;
    @OneToMany(mappedBy = "userId")
    private Collection<Statistics> statisticsCollection;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String key) {
        this.userId = userId;
        this.key = key;
    }

    public User(Integer userId, String username, Date birthDate, Gender gender) {
        this.userId = userId;
        this.username = username;
        this.birthDate = birthDate;
        this.genderId = gender;
    }

    public User(String username, Date birthDate, Gender gender, Boolean isAdmin) {
        this.username = username;
        this.birthDate = birthDate;
        this.genderId = gender;
        this.isAdmin = isAdmin;
    }

    public User(Integer userId, String key, String username, Date birthDate, Gender genderId, Boolean isAdmin) {
        this.userId = userId;
        this.key = key;
        this.username = username;
        this.birthDate = birthDate;
        this.isAdmin = isAdmin;
        this.genderId = genderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<AchievementUser> getAchievementUserCollection() {
        return achievementUserCollection;
    }

    public void setAchievementUserCollection(Collection<AchievementUser> achievementUserCollection) {
        this.achievementUserCollection = achievementUserCollection;
    }

    public Gender getGenderId() {
        return genderId;
    }

    public void setGenderId(Gender genderId) {
        this.genderId = genderId;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(this.userId);
        sb.append(", key: ");
        sb.append(this.key);
        sb.append(", username: ");
        sb.append(this.username);
        sb.append(", birthDate: ");
        sb.append(this.birthDate);
        sb.append(", genderId: ");
        sb.append(this.genderId);
        sb.append(", isAdmin: ");
        sb.append(this.isAdmin);
        return sb.toString();
    }

}
