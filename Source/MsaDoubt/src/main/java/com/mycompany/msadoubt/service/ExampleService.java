package com.mycompany.msadoubt.service;

import Beans.DoubtAnswerBean;
import Entity.Answer;
import Entity.Category;
import Entity.Doubt;
import Entity.Tags;
import Wrappers.DoubtDes;
import Wrappers.DoubtWrapper;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/example")
public class ExampleService {

    @EJB DoubtAnswerBean resource;

    @Path("SolvedDoubts")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Doubt> SolvedDoubts() {
        return resource.SolvedDoubts();
    }

    @Path("UnsolvedDoubt")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Doubt> UnsolvedDoubt() {
        return resource.UnsolvedDoubt();
    }

    @Path("usefulAnswers")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Answer> usefulAnswers() {
        return resource.usefulAnswers();
    }

    @Path("NotusefulAnswers")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Answer> NotusefulAnswers() {
        return resource.NotusefulAnswers();
    }

    
    @Path("MyDoubts/{usernm}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Doubt> MyDoubts(@PathParam("usernm") String usernm) {
        return resource.MyDoubts(usernm);
    }

    @Path("SearchDoubtByTitle/{SearchKey}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Doubt> SearchDoubtByTitle(@PathParam("SearchKey") String SearchKey) {
        return resource.SearchDoubtByTitle(SearchKey);
    }
    
    @Path("MyAsnwers/{usernm}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Answer> MyAsnwers(@PathParam("usernm") String usernm) {
        return resource.MyAsnwers(usernm);
    }
    
    @Path("ListCategory")
    @GET
    @RolesAllowed({"Admin"})
    public Collection<Category> ListCategory() {
        return resource.ListCategory();
    }

    @Path("ListTags")
    @GET
    @RolesAllowed({"Admin"})
    public Collection<Tags> ListTags() {
        return resource.ListTags();
    }

    @Path("CreateDoubt/{usernm}/{title}/{Point}")
    @POST
    @RolesAllowed({"Admin","User"})
    @Consumes("application/json")
    public void CreateDoubt(@PathParam("usernm") String usernm,@PathParam("title") String title,@PathParam("Point")Integer Point, DoubtDes description) {
        try {
        resource.CreateDoubt(usernm,title,Point,description);
        }
        catch(Exception e)
        {
            //System.out.println("%%%%%%%%%%%%%%%%%%%%%% : "+e);
        }
        
    }
          
    @Path("UpdateDoubt/{ID}/{title}/{Point}")
    @POST
    @RolesAllowed({"Admin","User"})
    @Consumes("application/json")
    @Produces("application/json")    
    public void UpdateDoubt(@PathParam("ID") Integer ID, @PathParam("title")  String title,@PathParam("Point")  Integer Point,DoubtWrapper dw) {
        resource.UpdateDoubt(ID,title,Point,dw);
//    categorydata=null;
//    tagsdata=null;
    }

    @Path("DoubtClosed/{AnswerId}")
    @POST
    @RolesAllowed({"Admin","User"})
    public void DoubtClosed(@PathParam("AnswerId") Integer AnswerId) {
        resource.DoubtClosed(AnswerId);
    }

    @Path("DeleteDoubt/{usernm}/{Id}")
    @DELETE
    @RolesAllowed({"Admin","User"})
    public void DeleteDoubt(@PathParam("usernm")String usernm,@PathParam("Id")Integer Id) {
        resource.DeleteDoubt(usernm,Id);
    }

    @Path("ListDoubts")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Doubt> ListDoubts() {
        return resource.ListDoubts();
    }
    
    @Path("DoubtAnswers/{DoubtID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Answer> DoubtAnswers(@PathParam("DoubtID") Integer DoubtID){
        return resource.DoubtAnswers(DoubtID);
    }
    
    @Path("DoubtCategory/{DoubtID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Category> DoubtCategory(@PathParam("DoubtID") Integer DoubtID){
        return resource.DoubtCategory(DoubtID);
    }
    
    @Path("DoubtTags/{DoubtID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Tags> DoubtTags(@PathParam("DoubtID") Integer DoubtID){
        return resource.DoubtTags(DoubtID);
    }

    @Path("SingleDoubt/{DoubtID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Doubt SingleDoubt(@PathParam("DoubtID") Integer DoubtID) {
        return resource.SingleDoubt(DoubtID);
    }

    @Path("CreateAnswer/{DoubtId}/{Username}")
    @POST
    @Consumes("application/json")
    @RolesAllowed({"Admin","User"})
    public void CreateAnswer(@PathParam("DoubtId")Integer DoubtId, @PathParam("Username")String Username, DoubtDes description) {
        resource.CreateAnswer(DoubtId,Username,description);
    }

    @Path("UpdateAnswer/{Id}")
    @POST
    @Consumes("application/json")
    @RolesAllowed({"Admin","User"})
    public void UpdateAnswer(@PathParam("Id") Integer Id, DoubtDes description) {
        resource.UpdateAnswer(Id,description);
    }

    @Path("DeleteAnswer/{Id}")
    @DELETE
    @RolesAllowed({"Admin","User"})
    public void DeleteAnswer(@PathParam("Id") Integer Id) {
        resource.DeleteAnswer(Id);
    }

    @Path("ListAnswerByDoubt/{DoubtID}")
    @GET
    @RolesAllowed({"Admin","User"})
    public Collection<Answer> ListAnswerByDoubt(@PathParam("DoubtID") Integer DoubtID) {
        return resource.ListAnswerByDoubt(DoubtID);
    }

    @Path("AllAnswers")
    @GET
    @RolesAllowed({"Admin"})
    public Collection<Answer> AllAnswers() {
        return resource.AllAnswers();
    }

}
