/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Client;

import Entity.Answer;
import Entity.Category;
import Entity.Doubt;
import Entity.Tags;
import Wrappers.DoubtDes;
import Wrappers.DoubtWrapper;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import token.GenerateToken;

/**
 *
 * @author HP
 */
@RegisterRestClient(baseUri = "http://localhost:8082/MsaDoubt/rest/example")	
public interface DoubtAnswerInterface {
    
    
    @Path("SolvedDoubts")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Doubt> SolvedDoubts() ;

    @Path("UnsolvedDoubt")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Doubt> UnsolvedDoubt() ;
    
    @Path("usefulAnswers")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Answer> usefulAnswers() ;

    @Path("NotusefulAnswers")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Answer> NotusefulAnswers() ;

    
    @Path("MyDoubts/{usernm}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Doubt> MyDoubts(@PathParam("usernm") String usernm) ;

    @Path("SearchDoubtByTitle/{SearchKey}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Doubt> SearchDoubtByTitle(@PathParam("SearchKey") String SearchKey) ;
    
    @Path("MyAsnwers/{usernm}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Answer> MyAsnwers(@PathParam("usernm") String usernm) ;
    
    @Path("ListCategory")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Category> ListCategory() ;

    @Path("ListTags")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Tags> ListTags() ;
    
    @Path("CreateDoubt/{usernm}/{title}/{Point}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void CreateDoubt(@PathParam("usernm") String usernm,@PathParam("title") String title,@PathParam("Point")Integer Point, DoubtDes description) ;

    @Path("UpdateDoubt/{ID}/{title}/{Point}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void UpdateDoubt(@PathParam("ID") Integer ID, @PathParam("title")  String title,@PathParam("Point")  Integer Point,DoubtWrapper dw) ;

    @Path("DoubtClosed/{AnswerId}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void DoubtClosed(@PathParam("AnswerId") Integer AnswerId) ;

    @Path("DeleteDoubt/{usernm}/{Id}")
    @DELETE
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void DeleteDoubt(@PathParam("usernm")String usernm,@PathParam("Id")Integer Id) ;

    @Path("ListDoubts")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Doubt> ListDoubts() ;

    @Path("DoubtAnswers/{DoubtID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Answer> DoubtAnswers(@PathParam("DoubtID") Integer DoubtID);
    
    @Path("DoubtCategory/{DoubtID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Category> DoubtCategory(@PathParam("DoubtID") Integer DoubtID);
    
    @Path("DoubtTags/{DoubtID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Tags> DoubtTags(@PathParam("DoubtID") Integer DoubtID);
        
    @Path("SingleDoubt/{DoubtID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Doubt SingleDoubt(@PathParam("DoubtID") Integer DoubtID) ;

    @Path("CreateAnswer/{DoubtId}/{Username}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void CreateAnswer(@PathParam("DoubtId")Integer DoubtId, @PathParam("Username")String Username,DoubtDes description) ;

    @Path("UpdateAnswer/{Id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void UpdateAnswer(@PathParam("Id") Integer Id,DoubtDes description);

    @Path("DeleteAnswer/{Id}")
    @DELETE
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void DeleteAnswer(@PathParam("Id") Integer Id) ;

    @Path("ListAnswerByDoubt/{DoubtID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Answer> ListAnswerByDoubt(@PathParam("DoubtID") Integer DoubtID) ;

    @Path("AllAnswers")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Answer> AllAnswers() ;
    
     default String generateJWTToken()
    {
        Config config = ConfigProvider.getConfig();

        // Token dynamically generated
        String token ="Bearer "+ GenerateToken.generateJWT();
        //System.out.println("Token = "+token);
        return token;
    }
}
