/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Client.DoubtAnswerInterface;
import Client.ResourceInterface;
import Client.UserInterface;
import Entity.Answer;
import Entity.Category;
import Entity.Comments;
import Entity.Doubt;
import Entity.Resource;
import Entity.ResourceFiles;
import Entity.Tags;
import Entity.User;
import Wrappers.DoubtDes;
import Wrappers.DoubtWrapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.shaded.commons.io.FilenameUtils;
import project.MyCredentials;

/**
 *
 * @author HP
 */
@Named(value = "resourceController")
@ApplicationScoped
public class ResourceController {

    
    @Inject @RestClient ResourceInterface ResourceApp;
    @Inject @RestClient UserInterface UserApp;
    @Inject @RestClient DoubtAnswerInterface DApp;
    
  
    Response res;
    Collection<Comments> CommentList;
    Collection<Resource> resList;
    Collection<Doubt> doubtlist;
    Collection<Answer> anslist;
    Collection<ResourceFiles> resfList;
    Integer cnt,UserID,ResourceID,ID,Semester,point;
    String Comment,Title,Description,subject,searchKey,Name,Email,Password,OTP,username,oldPassword,newPassword,successmsg,failmsg,grfile,profilemsg;
    Resource resource;
    Doubt doubt;
    Answer answer;
    User user;
    Comments Comments;
    Part file,res1,res2,res3;
    Collection<Category> catlist;
    Integer catslist[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,00,0,0,00,0,0,0,0,0,0,0,00,0,0,0,0,0,0000,0,00,0,0,0};  
    Integer tagslist[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,00,0,0,00,0,0,0,0,0,0,0,00,0,0,0,0,0,0000,0,00,0,0,0};  
    Collection<Tags> taglist;
    User ProfileUser,Userdetails;
    Integer UserAnswers,UserDoubts;
    DoubtDes description_doubt;
    @PostConstruct
    public void init()
    {
//        res = usercl.UserInfo(Response.class,KeepRecord.getUsername());
//        ProfileUser = res.readEntity(guser);
        //System.out.println("@@@@@@@@@@@@@@@@@@@@ResourceController@@@@@@@@"+MyCredentials.getUsername());
            ProfileUser = UserApp.UserInfo(MyCredentials.getUsername());
////                    //System.out.println("@@@@@@@@@@@@@@@@@@@@ResourceController@@@@@@@@"+ProfileUser.toString());
//
            catlist = DApp.ListCategory();
            taglist = DApp.ListTags();
    }
    /**
     * Creates a new instance of ResourceController
     */
    public ResourceController() {
        //user Profile
    }
    
    public String CallASkDoubt()
    {
        return "/WebApp/Userside/CreateDoubt.xhtml?faces-redirect=true";
    }
    
    public Collection<User> AllUsers() {
        return  UserApp.AllUsers();
    }
    public Integer CountAllComments() {
        return ResourceApp.CountAllComments();
    }

    public Integer CountAllFiles() {
        return ResourceApp.CountAllFiles();
    }
    public Integer CountAllUsers() {
        return UserApp.CountAllUsers();
    }
    
    public Integer CountAllUsersGTpoints(Integer Points) {
        return UserApp.CountAllUsersGTpoints(Points);
    }

    public Integer CountActiveUsers() {
        return UserApp.CountActiveUsers();
    }
    public String DoubtClosed(Integer AnswerId) {
        DApp.DoubtClosed(AnswerId);
        return "/WebApp/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
    public Collection<Doubt> MyDoubts() {
        return DApp.MyDoubts(MyCredentials.getUsername());
    }
    public Collection<Answer> DoubtAnswers(Doubt d){
        return DApp.DoubtAnswers(d.getId());
    }
    
    public Collection<Category> DoubtCategory(Doubt d){
        return DApp.DoubtCategory(d.getId());
    }
    
    public Collection<Tags> DoubtTags(Doubt d){
        return DApp.DoubtTags(d.getId());
    }
     public Collection<Doubt> SolvedDoubts() {
        return DApp.SolvedDoubts();
    }
    
    public Collection<Doubt> UnsolvedDoubt() {
        return DApp.UnsolvedDoubt();
    }
    
    public Collection<Answer> usefulAnswers() {
        return DApp.usefulAnswers();
    }
    public Collection<Answer> MyAnswers() {
        return DApp.MyAsnwers(MyCredentials.getUsername());
    }
    
    
    public String BlockUser(String username) {
       UserApp.BlockUser(username);
       return "/WebApp/Adminside/UserList.xhtml?faces-redirect=true";
    }
    
    public String UnBlockUser(String username) {
       UserApp.UnBlockUser(username);
       return "/WebApp/Adminside/UserList.xhtml?faces-redirect=true";
    }
    public String DeleteUser(String username) {
        UserApp.DeleteUser(username);
       return "/WebApp/Adminside/UserList.xhtml?faces-redirect=true";
    }

    public String UserDetails(String Username) {
        Userdetails = UserApp.UserInfo(Username);
        UserDoubts = DApp.MyDoubts(Username).size();
        UserAnswers = DApp.MyAsnwers(Username).size();
        return "/WebApp/Userside/UserDetails.xhtml?faces-redirect=true";
    }
    
    public String CreateDoubt(String title, Integer Point, String description) {
        //System.out.println("=====================:"+ MyCredentials.getUsername());
        //System.out.println("=====================:"+ title);
        //System.out.println("=====================:"+ description);
        //System.out.println("=====================:"+ Point);
                //System.out.println("=========%%%%%%%============:"+ title);

        
        DoubtDes dd = new DoubtDes(description);
        DApp.CreateDoubt(MyCredentials.getUsername(),title, Point,dd);
        this.Description="";
        return "/WebApp/Userside/MyDoubts.xhtml?faces-redirect=true";
    }

    public String CreateAnswer(Integer DoubtId,String description) {
            DoubtDes dd = new DoubtDes(description);
            DApp.CreateAnswer(DoubtId, MyCredentials.getUsername(), dd);
            doubt = DApp.SingleDoubt(DoubtId);
            this.Description ="";
            return "/WebApp/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
            
            
     public String CreateResource()
     {
         
         //System.out.println("Hello in crate res..");
//         return "google.com";
//        FacesContext.getCurrentInstance().getExternalContext().redirect("/DoubtCart/WebApp/Adminside/CreateResource.jsf?faces-redirect=true");
         return "/WebApp/Adminside/CreateResource.xhtml?faces-redirect=true";
     }
     
    public Collection<ResourceFiles> FilesByResource(Integer ResourceID) {
          return ResourceApp.FilesByResource(ResourceID);
    }
     
     public void SaveComment(Integer ResourceId,String Comment,String username)
     {
         ResourceApp.CommentResource(ResourceId, username,Comment);
         this.Comment = "";
     }
     
     public Integer CountComments(Integer ResourceId)
     {
         return ResourceApp.CountComments(ResourceId);
     }
     
     public Collection<Comments> CommentsByResource(Integer ResourceId)
     {
//         res = cl.CommentsByResource(Response.class, String.valueOf(ResourceId));
//         return res.readEntity(gCommentList);
         return ResourceApp.CommentsByResource(ResourceId);
     }
      
     public String ReadMore(Resource ress)
     {
         resource = ress;
         return "/WebApp/Userside/ViewResource.xhtml?faces-redirect=true";
     }
     
     public String ReadMoreDoubt(Doubt d)
     {
         doubt = d;
         return "/WebApp/Userside/ViewDoubt.xhtml?faces-redirect=true";
     }
        
     public String MyDoubtView(Doubt d)
     {
         doubt = d;
         return "/WebApp/Userside/ViewDoubt.xhtml?faces-redirect=true";
     }
     
      public String ReadMoreAnswer(Answer a)
     {
         answer = a;
         return "/WebApp/Userside/ViewAnswer.xhtml?faces-redirect=true";
     }

     public String SaveProfile() {
        try{
            InputStream input = file.getInputStream();
            String path = "F:\\DoubtCartMSA\\DoubtCart\\src\\main\\webapp\\WebApp\\FileAssets\\Profile";
            
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(file.getSubmittedFileName());
            if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("png"))
            {
                profilemsg = "Not Valid Image file !!";
                return "/WebApp/Profile.xhtml?faces-redirect=true";
            }
            
            grfile = temp + MyCredentials.getUsername()+ "."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            
            UserApp.SaveProfile(MyCredentials.getUsername(), grfile);
            ProfileUser = UserApp.UserInfo(MyCredentials.getUsername());
            profilemsg = "Profile Updated !!";
            return "/WebApp/Profile.xhtml?faces-redirect=true";
        }catch(Exception e){
            //System.out.println(e);
             profilemsg = "Something Went Wrong !!";
            return "/WebApp/Profile.xhtml?faces-redirect=true";
            }
     }
    
     
      
     public String changePassword(String username,String oldpassword, String newpassword)
     {
//        res = usercl.ChangePassword(Response.class,username,oldpassword,newpassword);
//        Boolean result = res.readEntity(gboolean);         
         Boolean result =UserApp.ChangePassword(MyCredentials.getUsername(),oldpassword, newpassword);
        if(result){
           successmsg = "Password Changed Successfully!!";
           return "/WebApp/Profile.xhtml?faces-redirect=true";
        }
        else{
          failmsg = "ChangePassword Failled !!";
           return "/WebApp/Profile.xhtml?faces-redirect=true";
        }
     }
     
     public String UpdateDoubt(Doubt d)
     {
         doubt = DApp.SingleDoubt(d.getId());
         cnt=0;
         DApp.DoubtCategory(doubt.getId()).forEach(b -> {
                catslist[cnt] = b.getId();
                cnt++;
                    //System.out.println("%%%%%%%%%%%%%% cat "+b.getName());

            });
         cnt = 0;
         DApp.DoubtTags(doubt.getId()).forEach(b -> {
                tagslist[cnt] = b.getId();
                cnt++;
                    //System.out.println("%%%%%%%%%%%%%% tag"+b.getName());

            });        
         
         return "/WebApp/Userside/UpdateDoubt.xhtml?faces-redirect=true";
     }
     
     public String UpdateAnswer(Answer a)
     {
         answer = a;
         return "/WebApp/Userside/UpdateAnswer.xhtml?faces-redirect=true";
     }
      
     public String UpdateResource(Resource ress)
     {
         resource = ress;
         return "/WebApp/Adminside/UpdateResource.xhtml?faces-redirect=true";
     }
     
     public String SaveResource(String title,String description,Integer semester,String Subject,Part rfile)
     {
         try{
            InputStream input = rfile.getInputStream();
            String path = "F:\\DoubtCartMSA\\DoubtCart\\src\\main\\webapp\\WebApp\\FileAssets\\ResourceImg";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(rfile.getSubmittedFileName());
            if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("png"))            
            {
                failmsg = "Not valid Image file ";
                return "/WebApp/Adminside/CreateResource.xhtml?faces-redirect=true";
            }
           
            
            grfile = temp + "_resource."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            
            
            ResourceApp.createResources(title, description, semester, Subject,grfile);
            this.Description ="";
            return "/WebApp/Userside/ShowResources.xhtml?faces-redirect=true";
           }catch(Exception e){
            return "/WebApp/Adminside/CreateResource.xhtml?faces-redirect=true";
            }
                
     }
     
    public String SaveResourceFiles(Part file) {    
        try{
            InputStream input = file.getInputStream();
            String path = "F:\\DoubtCartMSA\\DoubtCart\\src\\main\\webapp\\WebApp\\FileAssets\\ResourceFiles";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(file.getSubmittedFileName());
            
            grfile = temp + "_resource_files."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            
            
            ResourceApp.SaveResourceFiles(resource.getResourceID(),grfile);
            return "/WebApp/Userside/ShowResources.xhtml?faces-redirect=true";
           }catch(Exception e){
               e.printStackTrace();
            return "/WebApp/Adminside/UpdateResource.xhtml?faces-redirect=true";
            }
         
    }
    
    public String EditDoubt(Integer[] categorydata, Integer[] tagsdata)
    {
        DoubtWrapper dw = new DoubtWrapper(categorydata,tagsdata,doubt.getDescription());       
        //System.out.println(" ***************$$"+ dw.toString());
        DApp.UpdateDoubt(doubt.getId(),doubt.getTitle() ,doubt.getPoint(),dw);
        
        for (int i =0; i<catslist.length;i++)
        {
            catslist[i] =0;
        }
        for (int i =0; i<tagslist.length;i++)
        {
            tagslist[i]=0;
        }
        
        return "/WebApp/Userside/MyDoubts.xhtml?faces-redirect=true";
        
    }
    
    public String EditAnswer()
    {
        DoubtDes dd = new DoubtDes(answer.getDescription());
        DApp.UpdateAnswer(answer.getId(),dd);
        return "/WebApp/Userside/ViewAnswer.xhtml?faces-redirect=true";

//        return "/WebApp/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
     
     
     public String EditResource(Part rfile)
     {
         try{
            InputStream input = rfile.getInputStream();
            String path = "F:\\DoubtCartMSA\\DoubtCart\\src\\main\\webapp\\WebApp\\FileAssets\\ResourceImg";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            String ext = FilenameUtils.getExtension(rfile.getSubmittedFileName());
            if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("jpeg") && !ext.equalsIgnoreCase("png"))            
            {
                failmsg = "Not valid Image file ";
                return "/WebApp/Adminside/UpdateResource.xhtml?faces-redirect=true";
            }
            
            grfile = temp + "_resource."+ext;
            Files.copy(input, new File(path, grfile).toPath());
            //rbl.updateResources(resource.getResourceID(), resource.getTitle(), resource.getDescription(), resource.getSemester(), resource.getSubject(), grfile);
            ResourceApp.updateResources(resource.getResourceID(),resource.getTitle(), resource.getDescription(), resource.getSemester(),resource.getSubject(),grfile);
                    

            //FacesContext.getCurrentInstance().getExternalContext().redirect("ShowResources.xhtml");
            return "/WebApp/Adminside/ResourceList.xhtml?faces-redirect=true";
           }catch(IOException e){
               e.printStackTrace();
                return "/WebApp/Adminside/UpdateResource.xhtml?faces-redirect=true";
            }
         
     }
     
    public String DeleteAnswer(Integer Id) {
        DApp.DeleteAnswer(Id);
        return "/WebApp/Userside/ViewDoubt.xhtml?faces-redirect=true";
    }
     public String DeleteDoubt(Integer DoubtID)
     {
         DApp.DeleteDoubt(MyCredentials.getUsername(),DoubtID);
         return "/WebApp/Userside/MyDoubts.xhtml?faces-redirect=true";
     } 
      
     
     public String DeleteDoubtAdmin(Integer DoubtID)
     {
         DApp.DeleteDoubt(MyCredentials.getUsername(),DoubtID);
         return "/WebApp/Adminside/DoubtList.xhtml?faces-redirect=true";
     } 
     
//     
     public String DeleteResource(Integer resID)
     {
         ResourceApp.deleteResources(resID);
         return "/WebApp/Userside/ResourceList.xhtml?faces-redirect=true";
     } 
     
     public String DeleteComment(Integer CommentID) {
        ResourceApp.DeleteComment(CommentID);
        
        return "/WebApp/Userside/ViewResource.xhtml?faces-redirect=true";
    }
     public Collection<Resource> ListResource()
     {
        return ResourceApp.ListResources();
     }
     
     
     public Collection<Doubt> ListDoubt()
     {
        return DApp.ListDoubts();
     }
     
     
     public Collection<Resource> ResourcesBySubject(String Subject) {
         return ResourceApp.ResourcesBySubject(Subject);
     }
     
     public String CallSearchResource(String key)
     {
         this.searchKey = key;
         return "/WebApp/Userside/SearchResource.xhtml?faces-redirect=true";
     }
     
     public String CallSearchDoubt(String key)
     {
         this.searchKey = key;
         return "/WebApp/Userside/SearchDoubt.xhtml?faces-redirect=true";
     }
     
     public Collection<Resource> SearchResources()
     {
        return ResourceApp.SearchByTitleandSubject(this.searchKey);
     }
     
     public Collection<Doubt> SearchDoubts()
     {
        return DApp.SearchDoubtByTitle(searchKey);
     }

   

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Part getRes1() {
        return res1;
    }

    public void setRes1(Part res1) {
        this.res1 = res1;
    }

    public Part getRes2() {
        return res2;
    }

    public void setRes2(Part res2) {
        this.res2 = res2;
    }

    public Part getRes3() {
        return res3;
    }

    public void setRes3(Part res3) {
        this.res3 = res3;
    }

   
    public Collection<Comments> getCommentList() {
        return CommentList;
    }

    public void setCommentList(Collection<Comments> CommentList) {
        this.CommentList = CommentList;
    }

    

    public String getSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(String successmsg) {
        this.successmsg = successmsg;
    }

    public ResourceInterface getResourceApp() {
        return ResourceApp;
    }

    public void setResourceApp(ResourceInterface ResourceApp) {
        this.ResourceApp = ResourceApp;
    }

    public UserInterface getUserApp() {
        return UserApp;
    }

    public void setUserApp(UserInterface UserApp) {
        this.UserApp = UserApp;
    }

    public DoubtAnswerInterface getDApp() {
        return DApp;
    }

    public void setDApp(DoubtAnswerInterface DApp) {
        this.DApp = DApp;
    }

    public Integer getUserAnswers() {
        return UserAnswers;
    }

    public void setUserAnswers(Integer UserAnswers) {
        this.UserAnswers = UserAnswers;
    }

    public Integer getUserDoubts() {
        return UserDoubts;
    }

    public void setUserDoubts(Integer UserDoubts) {
        this.UserDoubts = UserDoubts;
    }

   

    public User getProfileUser() {
        return UserApp.UserInfo(MyCredentials.getUsername());
    }

    public void setProfileUser(User ProfileUser) {
        this.ProfileUser = ProfileUser;
    }

    public User getUserdetails() {
        return Userdetails;
    }

    public void setUserdetails(User Userdetails) {
        this.Userdetails = Userdetails;
    }

     
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getGrfile() {
        return grfile;
    }

    public void setGrfile(String grfile) {
        this.grfile = grfile;
    }

   

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

  

    public Collection<Doubt> getDoubtlist() {
        return doubtlist;
    }

    public void setDoubtlist(Collection<Doubt> doubtlist) {
        this.doubtlist = doubtlist;
    }

   

    public Integer[] getCatslist() {
        return catslist;
    }

    public void setCatslist(Integer[] catslist) {
        this.catslist = catslist;
    }

    public Integer[] getTagslist() {
        return tagslist;
    }

    public void setTagslist(Integer[] tagslist) {
        this.tagslist = tagslist;
    }

    
    public Collection<Answer> getAnslist() {
        return anslist;
    }

    public void setAnslist(Collection<Answer> anslist) {
        this.anslist = anslist;
    }

  
    public String getFailmsg() {
        return failmsg;
    }

    public void setFailmsg(String failmsg) {
        this.failmsg = failmsg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
    public Collection<ResourceFiles> getResfList() {
        return resfList;
    }

    public void setResfList(Collection<ResourceFiles> resfList) {
        this.resfList = resfList;
    }

    public Doubt getDoubt() {
        return doubt;
    }

    public void setDoubt(Doubt doubt) {
        this.doubt = doubt;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Collection<Category> getCatlist() {
        return catlist;
    }

    public void setCatlist(Collection<Category> catlist) {
        this.catlist = catlist;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Collection<Tags> getTaglist() {
        return taglist;
    }

    public void setTaglist(Collection<Tags> taglist) {
        this.taglist = taglist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
     

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Comments getComments() {
        return Comments;
    }

    public void setComments(Comments Comments) {
        this.Comments = Comments;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

 
    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }

    public Collection<Resource> getResList() {
        return resList;
    }

    public void setResList(Collection<Resource> resList) {
        this.resList = resList;
    }


    public String getProfilemsg() {
        return profilemsg;
    }

    public void setProfilemsg(String profilemsg) {
        this.profilemsg = profilemsg;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    public Integer getResourceID() {
        return ResourceID;
    }

    public void setResourceID(Integer ResourceID) {
        this.ResourceID = ResourceID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSemester() {
        return Semester;
    }

    public void setSemester(Integer Semester) {
        this.Semester = Semester;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public DoubtDes getDescription_doubt() {
        return description_doubt;
    }

    public void setDescription_doubt(DoubtDes description_doubt) {
        this.description_doubt = description_doubt;
    }
    
    
    
}
