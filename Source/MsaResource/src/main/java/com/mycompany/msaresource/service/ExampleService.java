package com.mycompany.msaresource.service;

import Beans.ResourceBean;
import Entity.Comments;
import Entity.Resource;
import Entity.ResourceFiles;
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
    
    @EJB ResourceBean resource;

    @Path("CountAllComments")
    @GET
    @RolesAllowed({"Admin","User"})
    public Integer CountAllComments() {
        return resource.CountAllComments();
    }

    @Path("CountAllFiles")
    @GET
    @RolesAllowed({"Admin","User"})
    public Integer CountAllFiles() {
        return resource.CountAllFiles();
    }

    
    @Path("FilesByResource/{ResourceID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<ResourceFiles> FilesByResource(@PathParam("ResourceID") Integer ResourceID) {
        return resource.FilesByResource(ResourceID);
    }
    
    

    @Path("ListResources")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Resource> ListResources() {
        return resource.ListResources();
    }

    @Path("SingleResource/{ResourceID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Resource SingleResource(@PathParam("ResourceID") Integer ResourceID) {
        return resource.SingleResource(ResourceID);
    }

    @Path("LikeResource/{ResourceID}/{UserID}")
    @POST
    @RolesAllowed({"Admin","User"})
    public void LikeResource(@PathParam("ResourceID") Integer ResourceID, @PathParam("UserID") String UserID) {
        resource.LikeResource(ResourceID, UserID);        
    }

//    @Path("SaveProfile/{username}/{profileurl}")
//    @POST
//    @RolesAllowed({"Admin","User"})
//    public void SaveProfile(@PathParam("username") String username ,@PathParam("profileurl") String profileurl) {
//        resource.SaveProfile(username, profileurl);
//    }
    
    @Path("CountLikes/{ResourceID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Integer CountLikes(@PathParam("ResourceID") Integer ResourceID) {
            return resource.CountLikes(ResourceID);
    }

    @Path("IsLikedByUser/{ResourceID}/{UserID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Boolean IsLikedByUser( @PathParam("ResourceID")Integer ResourceID, @PathParam("UserID")String UserID) {
        return resource.IsLikedByUser(ResourceID, UserID);
    }
    
    @Path("CommentsByResource/{ResourceID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Comments> CommentsByResource(@PathParam("ResourceID") Integer ResourceID) {
        return resource.CommentsByResource(ResourceID);
    }
     
    @Path("CommentResource/{ResourceID}/{UserID}/{Comment}")
    @POST
    @RolesAllowed({"Admin","User"})
    public void CommentResource(@PathParam("ResourceID") Integer ResourceID, @PathParam("UserID") String UserID,@PathParam("Comment") String Comment) {
          resource.CommentResource(ResourceID, Comment, UserID);
    }

    @Path("CountComments/{ResourceID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Integer CountComments(@PathParam("ResourceID") Integer ResourceID) {
        return resource.CountComments(ResourceID);
    }

    @Path("DeleteComment/{CommentID}")
    @DELETE
    @RolesAllowed({"Admin"})
    public void DeleteComment(@PathParam("CommentID") Integer CommentID) {
        resource.DeleteComment(CommentID);
    }

//    @Override
//    public void DownloadResource(Integer ResourceID, Integer UserID) {

//    }

    @Path("SearchByTitleandSubject/{SearchKey}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Resource> SearchByTitleandSubject(@PathParam("SearchKey") String SearchKey) {
        return resource.SearchByTitleandSubject(SearchKey);
    }

    @Path("ResourcesBySemester/{Semester}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Resource> ResourcesBySemester(@PathParam("Semester") Integer Semester) {
        return resource.ResourcesBySemester(Semester);
    }
    
    @Path("ResourcesBySubject/{Subject}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Resource> ResourcesBySubject(@PathParam("Subject") String Subject) {
        return resource.ResourcesBySubject(Subject);
    }

    
    
    @Path("SaveResourceFiles/{ResId}/{file}")
    @POST
    @RolesAllowed({"Admin"})
    public void SaveResourceFiles(@PathParam("ResId") Integer ResId,@PathParam("file") String file) {        
       resource.SaveResourceFiles(ResId, file);
    }
    
    @Path("Allcomments")
    @POST
    @RolesAllowed({"Admin"})
    public Collection<Comments> Allcomments() {
        return resource.Allcomments();
    }
    
    
    @Path("createResources/{title}/{description}/{semester}/{Subject}/{Image}")
    @POST
    @RolesAllowed({"Admin"})
    public void createResources(@PathParam("title") String title,@PathParam("description") String description, @PathParam("semester") Integer semester, @PathParam("Subject") String Subject, @PathParam("Image") String Image) {
        resource.createResources(title, description, semester, Subject,Image);
    }
    
    @Path("updateResources/{ID}/{title}/{description}/{semester}/{Subject}/{Image}")
    @POST
    @RolesAllowed({"Admin","User"})
    public void updateResources(@PathParam("ID") Integer ID, @PathParam("title") String title,@PathParam("description") String description, @PathParam("semester") Integer semester, @PathParam("Subject") String Subject,@PathParam("Image") String Image) {
        resource.updateResources(ID, title, description, semester, Subject,Image);
    }

    @Path("deleteResources/{ID}")
    @DELETE
    @RolesAllowed({"Admin","User"})
    public void deleteResources(@PathParam("ID") Integer ID) {
        resource.deleteResources(ID);
    }
}
