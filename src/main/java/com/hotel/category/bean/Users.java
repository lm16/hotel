package com.hotel.category.bean;

/**
 * @author 林晓锋
 * @date 2019/9/27
 * modified: 2019/9/27
 * 功能：
 */
public class Users {

    private  int id;

    private String name;

    private String password;

    private String telephone;

    private String username;

    private String isadmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public Users(int id, String name, String password, String telephone, String username, String isadmin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.telephone = telephone;
        this.username = username;
        this.isadmin = isadmin;
    }

    public Users() {

    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", username='" + username + '\'' +
                ", isadmin='" + isadmin + '\'' +
                '}';
    }
}
