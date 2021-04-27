package Modell;

import java.util.Date;

public class User {

    private Integer userId;
    private String key;
    private String username;
    private Date birthDate;
    private Integer genderId;
    private Boolean isAdmin;

    public User(Integer userId, String key, String username, Date birthDate, Integer genderId, Boolean isAdmin) {
        this.userId = userId;
        this.key = key;
        this.username = username;
        this.birthDate = birthDate;
        this.genderId = genderId;
        this.isAdmin = isAdmin;
    }

    public User(String key, Boolean isAdmin) {
        this.key = key;
        this.isAdmin = isAdmin;
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

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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
