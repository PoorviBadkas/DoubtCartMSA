package com.mycompany.msausersop.service;

import Beans.UserBean;
import Entity.User;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/example")
public class ExampleService {
    
    @EJB UserBean resource;

    @Path("registerUser/{Username}/{Email}/{Password}/{Semester}")
    @POST
    public void registerUser(@PathParam("Username") String Username,@PathParam("Email") String Email, @PathParam("Password") String Password, @PathParam("Semester") Integer Semester) {
        resource.registerUser(Username, Email, Password, Semester,0,0,0,false);
    }
    
    @Path("ForgotPassword/{Email}")
    @POST
    public Boolean ForgotPassword(@PathParam("Email")String Email) {
        return resource.ForgotPassword(Email);
    }

    @Path("ResetPassword/{Email}/{Password}/{OTP}")
    @POST
    public Boolean ResetPassword(@PathParam("Email") String Email, @PathParam("Password") String Password, @PathParam("OTP") String OTP) {
        return resource.ResetPassword(Email,Password,OTP);
    }
    
    @Path("UserInfo/{Username}")
    @POST
    @RolesAllowed({"Admin","User"})
    public User UserInfo(@PathParam("Username") String Username) {
        return resource.UserInfo(Username);
    }
   
    @Path("ChangePassword/{usernm}/{oldPassword}/{newPassword}")
    @POST
    @RolesAllowed({"Admin","User"})
    public Boolean ChangePassword(@PathParam("usernm") String usernm,@PathParam("oldPassword") String oldPassword,@PathParam("newPassword") String newPassword) {
        return resource.ChangePassword( usernm,oldPassword, newPassword);
    }

    @Path("CountAllUsers")
    @POST
    @RolesAllowed({"Admin","User"})
    public Integer CountAllUsers() {
        return resource.CountAllUsers();
    }

    @Path("CountAllUsersGTpoints/{Points}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Integer CountAllUsersGTpoints(@PathParam("Points") Integer Points) {
        return resource.CountAllUsersGTpoints(Points);
    }

    @Path("CountActiveUsers")
    @GET
    @RolesAllowed({"Admin","User"})
    public Integer CountActiveUsers() {
        return resource.CountActiveUsers();
    }
    
    @Path("AllUsers")
    @GET
    @RolesAllowed({"Admin"})
    public Collection<User> AllUsers() {
        return resource.AllUsers();
    }

    @Path("BlockUser/{username}")
    @POST
    @RolesAllowed({"Admin"})
    public void BlockUser(@PathParam("username") String username) {
        resource.BlockUser(username);
    }
    
    @Path("UnBlockUser/{username}")
    @POST
    @RolesAllowed({"Admin"})
    public void UnBlockUser(@PathParam("username") String username) {
        resource.UnBlockUser(username);
    }
    
    @Path("DeleteUser/{username}")
    @DELETE
    @RolesAllowed({"Admin"})
    public void DeleteUser(@PathParam("username") String username) {
        resource.DeleteUser(username);
    }
    
    
    @Path("SaveProfile/{username}/{profileurl}")
    @POST
    @RolesAllowed({"Admin","User"})
     public void SaveProfile(@PathParam("username") String username ,@PathParam("profileurl") String profileurl) {
      resource.SaveProfile(username,profileurl);
    }   
}
