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
@Table(name = "review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByReviewId", query = "SELECT r FROM Review r WHERE r.reviewId = :reviewId"),
    @NamedQuery(name = "Review.findByScore", query = "SELECT r FROM Review r WHERE r.score = :score"),
    @NamedQuery(name = "Review.findByComment", query = "SELECT r FROM Review r WHERE r.comment = :comment"),
    @NamedQuery(name = "Review.findByCreatedAt", query = "SELECT r FROM Review r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Review.findByIsActive", query = "SELECT r FROM Review r WHERE r.isActive = :isActive")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "review_id")
    private Integer reviewId;
    @Column(name = "score")
    private Integer score;
    @Column(name = "comment")
    private String comment;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @ManyToOne
    private Game gameId;

    public Review() {
    }

    public Review(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Review(Integer reviewId, User user, Game game, Integer score, String comment, Date createdAt) {
        this.reviewId = reviewId;
        this.userId = user;
        this.gameId = game;
        this.score = score;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Review(User user, Game game, Integer score, String comment) {
        this.userId = user;
        this.gameId = game;
        this.score = score;
        this.comment = comment;
    }

    public Review(Integer id, Integer score, String comment) {
        this.reviewId = id;
        this.score = score;
        this.comment = comment;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + reviewId
                + ", userId: " + userId.getUserId()
                + ", userName: " + userId.getUsername()
                + ", gameId: " + gameId.getGameId()
                + ", gameName: " + gameId.getName()
                + ", score: " + score
                + ", comment: " + comment
                + ", createdAt: " + createdAt;
    }

}
