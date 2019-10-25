/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package clases;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 2dam
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    private String email;
    private String fullname;
    private UserStatus status;
    private UserPrivilege privilege;
    private String password;
    private Date lastAccess;
    private Date lastPasswordChange;
    
    public User() {
    }
    
    public User(int id, String login, String email, String fullname, UserStatus status, UserPrivilege privilege, String password, Date lastAccess, Date lastPasswordChange) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.fullname = fullname;
        this.status = status;
        this.privilege = privilege;
        this.password = password;
        this.lastAccess = lastAccess;
        this.lastPasswordChange = lastPasswordChange;
    }
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public UserStatus getStatus() {
        return status;
    }
    
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    
    public UserPrivilege getPrivilege() {
        return privilege;
    }
    
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getLastAccess() {
        return lastAccess;
    }
    
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
    
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }
    
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }
    
    
}
