package clases;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Es la clase usuario que tienen todos los datos necesarios.
 * It is the user class that has all the necessary data.
 * @author Equipo.
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
    private Timestamp lastAccess;
    private Timestamp lastPasswordChange;
    
    public User() {
    }
    
    public User(int id, String login, String email, String fullname, UserStatus status, UserPrivilege privilege, String password, Timestamp lastAccess, Timestamp lastPasswordChange) {
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
    
    public Timestamp getLastAccess() {
        return lastAccess;
    }
    
    public void setLastAccess(Timestamp lastAccess) {
        this.lastAccess = lastAccess;
    }
    
    public Timestamp getLastPasswordChange() {
        return lastPasswordChange;
    }
    
    public void setLastPasswordChange(Timestamp lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }
    
    
}
