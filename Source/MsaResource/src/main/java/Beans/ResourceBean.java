/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entity.Comments;
import Entity.Liketb;
import Entity.Resource;
import Entity.ResourceFiles;
import Entity.User;
import java.io.File;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class ResourceBean {

  
@PersistenceContext(unitName = "RPU")
    EntityManager em;  

    public Integer CountAllComments() {
        return (Integer) em.createNamedQuery("Comments.findAll").getResultList().size();
    }

    public Integer CountAllFiles() {
        return (Integer) em.createNamedQuery("ResourceFiles.findAll").getResultList().size();
    }

    
    public Collection<Resource> ListResources() {
        return em.createNamedQuery("Resource.findAll").getResultList();
    }

    
    public Resource SingleResource(Integer ResourceID) {
        return (Resource) em.createNamedQuery("Resource.findById").setParameter("id", ResourceID).getSingleResult();
    }

    public void LikeResource(Integer ResourceID, String UserID) {
        Liketb l = new Liketb();
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        User u = (User) em.find(User.class, UserID);
        
        Collection<Liketb> resLike = r.getLiketbCollection();
        resLike.add(l);
        r.setLiketbCollection(resLike);
        
        Collection<Liketb> userLike = u.getLiketbCollection();
        userLike.add(l);
        u.setLiketbCollection(userLike);
        
        em.persist(l);
        
        
    }

//    public void SaveProfile(String username ,String profileurl) {
//        User u = (User) em.find(User.class, username);
//         if(u.getProfile() != null)
//        {
//            File ff = new File("F:\DoubtCartMSA\DoubtCart\src\main\webapp\WebApp\FileAssets\\Profile",u.getProfile());
//            if(ff.exists())
//            {
//                ff.deleteOnExit();
//            }
//        }
//        u.setProfile(profileurl);
//        em.merge(u);
//    }

    
    public Collection<Comments> CommentsByResource(Integer ResourceID) {
        return em.createNamedQuery("Comments.findByResourceID").setParameter("resourceID", ResourceID).getResultList();
    }

    public Integer CountLikes(Integer ResourceID) {
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        return r.getLiketbCollection().size();
    }

    public Boolean IsLikedByUser(Integer ResourceID, String UserID) {
        return !(em.createNamedQuery("Liketb.ResLikedByUser").setParameter("resid", ResourceID).setParameter("userid", UserID).getResultList().isEmpty());        
    }

    public void CommentResource(Integer ResourceID, String Comment,String username) {
        
        Comments c = new Comments(Comment);
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        User u = (User) em.find(User.class, username);
        c.setResourceID(r);
        c.setUserID(u);                       

        Collection<Comments> resComment = r.getCommentsCollection();
        resComment.add(c);
        r.setCommentsCollection(resComment);
        
        Collection<Comments> userComment = u.getCommentsCollection();
        userComment.add(c);
        u.setCommentsCollection(userComment);
        
        em.merge(r);
        em.merge(u);
        em.persist(c);
    }

    public Integer CountComments(Integer ResourceID) {
        Resource r = (Resource) em.find(Resource.class, ResourceID);
        return r.getCommentsCollection().size();
    }

    public void DeleteComment(Integer CommentID) {
        Comments c = (Comments) em.find(Comments.class, CommentID);
        
        Resource r = c.getResourceID();
        User u = c.getUserID();
        r.getCommentsCollection().remove(c);
        u.getCommentsCollection().remove(c);
        em.merge(r);
        em.merge(u);
        
        em.remove(c);
        
        //System.out.println("$$$$$$$$$$$$$ " + CommentID);
        
    }

//    public void DownloadResource(Integer ResourceID, Integer UserID) {
//        Resource r = (Resource) em.find(Resource.class, ResourceID);
//        User u = (User) em.find(User.class, UserID);
//        
//        Collection<User> resUsers = r.get();
//        resUsers.add(u);
//        r.setUserCollection(resUsers);
//        
//        Collection<Resource> userRes = u.getResourcesCollection();
//        userRes.add(r);
//        u.setResourcesCollection(userRes);   
//        
//        em.merge(r);
//    }

    public Collection<Resource> SearchByTitleandSubject(String SearchKey) {
//            @NamedQuery(name = "Resource.findByTitleAndSubject", query = "SELECT r FROM Resource r WHERE r.title = :key OR r.subject = :key"),
        return em.createNamedQuery("Resource.findByTitleAndSubject").setParameter("key", "%" + SearchKey + "%").getResultList();
    }

    public Collection<Resource> ResourcesBySemester(Integer Semester) {
        return em.createNamedQuery("Resource.findBySemester").setParameter("semester", Semester).getResultList();
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Collection<Resource> ResourcesBySubject(String Subject) {
        return em.createNamedQuery("Resource.findBySubject").setParameter("subject", Subject).getResultList();
    }

    public Collection<ResourceFiles> FilesByResource(Integer ResourceID) {
        return em.find(Resource.class,ResourceID).getResourceFilesCollection();
    }
    
    
    public void SaveResourceFiles(Integer ResId, String file) {        
        Resource r = em.find(Resource.class, ResId);
        Collection<ResourceFiles>  rfcollection = r.getResourceFilesCollection();
        ResourceFiles rf = new ResourceFiles(file);
        rf.setResourceID(r);
        rfcollection.add(rf);
        r.setResourceFilesCollection(rfcollection);
        em.persist(rf);
        em.merge(r);
        
    }
    
    public Collection<Comments> Allcomments() {
        return em.createNamedQuery("Comments.findAll").getResultList();
    }
    
    
    public void createResources(String title, String description, Integer semester, String Subject,String Image) {
        Resource r = new Resource(title,description,semester,Subject);
        r.setImage(Image);
        em.persist(r);
    }
    
    public void updateResources(Integer ID, String title, String description, Integer semester, String Subject,String Image) {
        //System.out.println("In resource update");
        Resource r = (Resource) em.find(Resource.class, ID);
        r.setTitle(title);
        r.setDescription(description);
        r.setSemester(semester);
        r.setSubject(Subject);
        
        if(r.getImage() != null)
        {
            File ff = new File("F:\\DoubtCartMSA\\DoubtCart\\src\\main\\webapp\\WebApp\\FileAssets\\ResourceImg",r.getImage());
            if(ff.exists())
            {
                ff.deleteOnExit();
            }
        }
        //System.out.println("In resource update last");
        r.setImage(Image);
        em.merge(r);
    }
    
    public void deleteResources(Integer ID) {
        //System.out.println("############### "+ID);
        Resource r = (Resource) em.find(Resource.class, ID);
        
        em.remove(r);
    }
    
}
