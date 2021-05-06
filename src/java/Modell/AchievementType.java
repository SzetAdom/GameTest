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
@Table(name = "achievement_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AchievementType.findAll", query = "SELECT a FROM AchievementType a"),
    @NamedQuery(name = "AchievementType.findByAchievementTypeId", query = "SELECT a FROM AchievementType a WHERE a.achievementTypeId = :achievementTypeId"),
    @NamedQuery(name = "AchievementType.findByNameOfAchievementType", query = "SELECT a FROM AchievementType a WHERE a.nameOfAchievementType = :nameOfAchievementType"),
    @NamedQuery(name = "AchievementType.findByIsActive", query = "SELECT a FROM AchievementType a WHERE a.isActive = :isActive")})
public class AchievementType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "achievement_type_id")
    private Integer achievementTypeId;
    @Column(name = "name_of_achievement_type")
    private String nameOfAchievementType;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "achievementType")
    private Collection<Achievement> achievementCollection;

    public AchievementType() {
    }

    public AchievementType(Integer achievementTypeId) {
        this.achievementTypeId = achievementTypeId;
    }

    public AchievementType(String nameOfAchievementType) {
        this.nameOfAchievementType = nameOfAchievementType;
    }

    public AchievementType(Integer achiTypeId, String name) {
        this.achievementTypeId = achiTypeId;
        this.nameOfAchievementType = name;
    }

    public Integer getAchievementTypeId() {
        return achievementTypeId;
    }

    public void setAchievementTypeId(Integer achievementTypeId) {
        this.achievementTypeId = achievementTypeId;
    }

    public String getNameOfAchievementType() {
        return nameOfAchievementType;
    }

    public void setNameOfAchievementType(String nameOfAchievementType) {
        this.nameOfAchievementType = nameOfAchievementType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<Achievement> getAchievementCollection() {
        return achievementCollection;
    }

    public void setAchievementCollection(Collection<Achievement> achievementCollection) {
        this.achievementCollection = achievementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (achievementTypeId != null ? achievementTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AchievementType)) {
            return false;
        }
        AchievementType other = (AchievementType) object;
        if ((this.achievementTypeId == null && other.achievementTypeId != null) || (this.achievementTypeId != null && !this.achievementTypeId.equals(other.achievementTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + achievementTypeId
                + ", name: " + nameOfAchievementType;
    }

}
