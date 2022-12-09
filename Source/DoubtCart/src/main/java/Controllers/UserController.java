/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Client.UserInterface;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author HP
 */
@Named(value = "userController")
@ApplicationScoped
public class UserController {

    
    @Inject @RestClient UserInterface UserApp;

    Integer semester,OTP;
    String name,email,password;
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
    
    
    public String register(String name,String email,String password,Integer semester)
     {
        UserApp.registerUser(name,email,password,semester);
         //rbl.registerUser(name, email, password, semester);
         this.name = "";
         this.email = "";
         this.semester = null;
         this.password ="";
         return "/WebApp/App.xhtml?faces-redirect=true";
     }
     
     public String forgotPassword(String Email)
     {
        Boolean result = UserApp.ForgotPassword(Email);
        this.email = "";
         //System.out.println("@@@@@@@@" + result);

        if(result){
           return "/WebApp/ResetPassword.xhtml?faces-redirect=true";
        }
        else{
               return "/WebApp/ForgotPassword.xhtml?faces-redirect=true";
        }
     }
     
     
      public String resetPassword(String Email,String Password, String OTP)
     {
        Boolean result = UserApp.ResetPassword(Email,Password,OTP) ;       
        this.email = "";
         this.OTP = null;
         this.password ="";
        if(result){
           return "/WebApp/App.xhtml?faces-redirect=true";
        }
        else{
           return "/WebApp/ResetPassword.xhtml?faces-redirect=true";
        }
     }

   
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getOTP() {
        return OTP;
    }

    public void setOTP(Integer OTP) {
        this.OTP = OTP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
      
      
      
}
