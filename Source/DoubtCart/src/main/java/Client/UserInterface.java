/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Client;

import Entity.User;
import java.util.Collection;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import token.GenerateToken;

/**
 *
 * @author HP
 */
@RegisterRestClient(baseUri = "http://localhost:8083/MsaUsersOp/rest/example")	
public interface UserInterface {
    
    @Path("registerUser/{Username}/{Email}/{Password}/{Semester}")
    @POST  
    public void registerUser(@PathParam("Username") String Username,@PathParam("Email") String Email, @PathParam("Password") String Password, @PathParam("Semester") Integer Semester) ;
   
    
    @Path("ForgotPassword/{Email}")
    @POST
    public Boolean ForgotPassword(@PathParam("Email")String Email) ;

    @Path("ResetPassword/{Email}/{Password}/{OTP}")
    @POST   
    public Boolean ResetPassword(@PathParam("Email") String Email, @PathParam("Password") String Password, @PathParam("OTP") String OTP) ;
    
    @Path("UserInfo/{Username}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public User UserInfo(@PathParam("Username") String Username) ;
   
    @Path("ChangePassword/{usernm}/{oldPassword}/{newPassword}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Boolean ChangePassword(@PathParam("usernm") String usernm,@PathParam("oldPassword") String oldPassword,@PathParam("newPassword") String newPassword) ;

    @Path("CountAllUsers")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Integer CountAllUsers() ;

    @Path("CountAllUsersGTpoints/{Points}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Integer CountAllUsersGTpoints(@PathParam("Points") Integer Points) ;

    @Path("CountActiveUsers")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Integer CountActiveUsers() ;
    
    @Path("AllUsers")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<User> AllUsers() ;

    @Path("BlockUser/{username}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void BlockUser(@PathParam("username") String username) ;
    
    @Path("UnBlockUser/{username}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void UnBlockUser(@PathParam("username") String username) ;
    
    @Path("DeleteUser/{username}")
    @DELETE
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void DeleteUser(@PathParam("username") String username) ;
    
    
    @Path("SaveProfile/{username}/{profileurl}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void SaveProfile(@PathParam("username") String username ,@PathParam("profileurl") String profileurl) ;
     
    default String generateJWTToken()
    {
        Config config = ConfigProvider.getConfig();

        // Token dynamically generated
        String token ="Bearer "+ GenerateToken.generateJWT();
        //System.out.println("Token = "+token);
        return token;
    }
}
