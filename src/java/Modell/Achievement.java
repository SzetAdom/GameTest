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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.json.JSONObject;

/**
 *
 * @author adams
 */
@Entity
@Table(name = "achievement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Achievement.findAll", query = "SELECT a FROM Achievement a"),
    @NamedQuery(name = "Achievement.findByAchievementId", query = "SELECT a FROM Achievement a WHERE a.achievementId = :achievementId"),
    @NamedQuery(name = "Achievement.findByDescriptionOfAchievment", query = "SELECT a FROM Achievement a WHERE a.descriptionOfAchievment = :descriptionOfAchievment"),
    @NamedQuery(name = "Achievement.findByIsActive", query = "SELECT a FROM Achievement a WHERE a.isActive = :isActive")})
public class Achievement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "achievement_id")
    private Integer achievementId;
    @Column(name = "description_of_achievment")
    private String descriptionOfAchievment;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @ManyToOne
    private Game gameId;
    @OneToMany(mappedBy = "prerequisite")
    private Collection<Achievement> achievementCollection;
    @JoinColumn(name = "prerequisite", referencedColumnName = "achievement_id")
    @ManyToOne
    private Achievement prerequisite;
    @JoinColumn(name = "achievement_type", referencedColumnName = "achievement_type_id")
    @ManyToOne
    private AchievementType achievementType;
    @OneToMany(mappedBy = "achievementId")
    private Collection<AchievementUser> achievementUserCollection;

    public Achievement() {
    }

    public Achievement(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public Achievement(Integer achievementId, Integer gameId, String description, Integer prerequisite, AchievementType type) {
        this.achievementId = achievementId;
        this.gameId = new Game();
        this.gameId.setGameId(gameId);
        this.descriptionOfAchievment = description;
        this.prerequisite = new Achievement();
        this.prerequisite.setAchievementId(prerequisite);
        this.achievementType = type;

    }

    public Achievement(Integer achievementId, Integer gameId, String description, Integer prerequisite, Integer typeId) {
        this.achievementId = achievementId;
        this.gameId = new Game();
        this.gameId.setGameId(gameId);
        this.descriptionOfAchievment = description;
        this.prerequisite = new Achievement();
        this.prerequisite.setAchievementId(prerequisite);
        this.achievementType = new AchievementType();
        this.achievementType.setAchievementTypeId(typeId);

    }

    public Achievement(Integer gameId, String description, Integer prerequisite, Integer typeId) {
        this.gameId = new Game();
        this.gameId.setGameId(gameId);
        this.descriptionOfAchievment = description;
        this.prerequisite = new Achievement();
        this.prerequisite.setAchievementId(prerequisite);
        this.achievementType = new AchievementType();
        this.achievementType.setAchievementTypeId(typeId);

    }

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public String getDescriptionOfAchievment() {
        return descriptionOfAchievment;
    }

    public void setDescriptionOfAchievment(String descriptionOfAchievment) {
        this.descriptionOfAchievment = descriptionOfAchievment;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    @XmlTransient
    public Collection<Achievement> getAchievementCollection() {
        return achievementCollection;
    }

    public void setAchievementCollection(Collection<Achievement> achievementCollection) {
        this.achievementCollection = achievementCollection;
    }

    public Achievement getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(Achievement prerequisite) {
        this.prerequisite = prerequisite;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(AchievementType achievementType) {
        this.achievementType = achievementType;
    }

    @XmlTransient
    public Collection<AchievementUser> getAchievementUserCollection() {
        return achievementUserCollection;
    }

    public void setAchievementUserCollection(Collection<AchievementUser> achievementUserCollection) {
        this.achievementUserCollection = achievementUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (achievementId != null ? achievementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Achievement)) {
            return false;
        }
        Achievement other = (Achievement) object;
        if ((this.achievementId == null && other.achievementId != null) || (this.achievementId != null && !this.achievementId.equals(other.achievementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + achievementId
                + ", game: " + gameId.toString()
                + ", description: " + descriptionOfAchievment
                + ", type: " + achievementType.toString();
    }

    public JSONObject toJSONObject() {
        JSONObject result = new JSONObject();
        result.put("id", achievementId);
        result.put("game", gameId.getGameId());
        result.put("description", descriptionOfAchievment);
        result.put("type", achievementType.getAchievementTypeId());

        return result;
    }

}
