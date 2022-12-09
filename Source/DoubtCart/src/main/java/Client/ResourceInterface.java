/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Client;

import Entity.Comments;
import Entity.Resource;
import Entity.ResourceFiles;
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
@RegisterRestClient(baseUri = "http://localhost:8081/MsaResource/rest/example")	
public interface ResourceInterface {
        
    @Path("CountAllComments")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Integer CountAllComments();
     
    @Path("CountAllFiles")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Integer CountAllFiles();
            
    @Path("FilesByResource/{ResourceID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<ResourceFiles> FilesByResource(@PathParam("ResourceID") Integer ResourceID) ;
    
    @Path("ListResources")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Resource> ListResources() ;

    @Path("SingleResource/{ResourceID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Resource SingleResource(@PathParam("ResourceID") Integer ResourceID) ;

    @Path("LikeResource/{ResourceID}/{UserID}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void LikeResource(@PathParam("ResourceID") Integer ResourceID, @PathParam("UserID") String UserID) ;

//    @Path("SaveProfile/{username}/{profileurl}")
//    @POST
//    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
//    public void SaveProfile(@PathParam("username") String username ,@PathParam("profileurl") String profileurl) ;
    
    @Path("CountLikes/{ResourceID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Integer CountLikes(@PathParam("ResourceID") Integer ResourceID) ;

    @Path("IsLikedByUser/{ResourceID}/{UserID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Boolean IsLikedByUser( @PathParam("ResourceID")Integer ResourceID, @PathParam("UserID")String UserID) ;
    
    @Path("CommentsByResource/{ResourceID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Comments> CommentsByResource(@PathParam("ResourceID") Integer ResourceID) ;
     
    @Path("CommentResource/{ResourceID}/{UserID}/{Comment}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void CommentResource(@PathParam("ResourceID") Integer ResourceID, @PathParam("UserID") String UserID,@PathParam("Comment") String Comment) ;

    @Path("CountComments/{ResourceID}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Integer CountComments(@PathParam("ResourceID") Integer ResourceID) ;

    @Path("DeleteComment/{CommentID}")
    @DELETE
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void DeleteComment(@PathParam("CommentID") Integer CommentID) ;

//    public void DownloadResource(Integer ResourceID, Integer UserID) ;

    @Path("SearchByTitleandSubject/{SearchKey}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Resource> SearchByTitleandSubject(@PathParam("SearchKey") String SearchKey) ;

    @Path("ResourcesBySemester/{Semester}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Resource> ResourcesBySemester(@PathParam("Semester") Integer Semester) ;
    
    @Path("ResourcesBySubject/{Subject}")
    @GET
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Resource> ResourcesBySubject(@PathParam("Subject") String Subject) ;

    
    
    @Path("SaveResourceFiles/{ResId}/{file}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void SaveResourceFiles(@PathParam("ResId") Integer ResId,@PathParam("file") String file) ;
    
    @Path("Allcomments")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public Collection<Comments> Allcomments();
    
    
    @Path("createResources/{title}/{description}/{semester}/{Subject}/{Image}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void createResources(@PathParam("title") String title,@PathParam("description") String description, @PathParam("semester") Integer semester, @PathParam("Subject") String Subject, @PathParam("Image") String Image) ;
   
    @Path("updateResources/{ID}/{title}/{description}/{semester}/{Subject}/{Image}")
    @POST
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void updateResources(@PathParam("ID") Integer ID, @PathParam("title") String title,@PathParam("description") String description, @PathParam("semester") Integer semester, @PathParam("Subject") String Subject,@PathParam("Image") String Image) ;

    @Path("deleteResources/{ID}")
    @DELETE
    @ClientHeaderParam(name = "authorization", value="{generateJWTToken}")
    public void deleteResources(@PathParam("ID") Integer ID);
    
    
    
    
    
    default String generateJWTToken()
    {
        Config config = ConfigProvider.getConfig();

        // Token dynamically generated
        String token ="Bearer "+ GenerateToken.generateJWT();
        //System.out.println("Token = "+token);
        return token;
    }
   
}
