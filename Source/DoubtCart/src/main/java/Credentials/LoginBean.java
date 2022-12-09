/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Credentials;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import project.MyCredentials;

/**
 *
 * @author admin
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    
    private String errorstatus = MyCredentials.getLoginStatus();
    
    private String username = MyCredentials.getUsername();
    private String password = MyCredentials.getPassword();
    private List<String> roles = MyCredentials.getGroups();

    
    public String getErrorStatus() {
        return MyCredentials.getLoginStatus();
    }

    public void setErrorStatus(String status) {
        //status = KeepRecord.getErrorStatus();
        this.errorstatus = status;
    }

   
    public String getUsername() {
        return MyCredentials.getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return MyCredentials.getPassword();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public LoginBean() {
    }
    
}
