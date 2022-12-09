/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entity.Answer;
import Entity.Category;
import Entity.Doubt;
import Entity.Tags;
import Entity.User;
import Wrappers.DoubtDes;
import Wrappers.DoubtWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DoubtAnswerBean {

    @PersistenceContext(unitName = "DoubtAnswerPU")
    EntityManager em;  
   
    
    public Collection<Doubt> SolvedDoubts() {
        return em.createNamedQuery("Doubt.findByIsClosed").setParameter("isClosed", true).getResultList();
    }

    public Collection<Doubt> UnsolvedDoubt() {
        return em.createNamedQuery("Doubt.findByIsClosed").setParameter("isClosed", false).getResultList();
    }

    public Collection<Answer> usefulAnswers() {
        return em.createNamedQuery("Answer.findByIsHelpful").setParameter("isHelpful", true).getResultList();
    }

    public Collection<Answer> NotusefulAnswers() {
        return em.createNamedQuery("Answer.findByIsHelpful").setParameter("isHelpful", false).getResultList();
    }

   
    
    public Collection<Doubt> MyDoubts(String usernm) {
        return em.find(User.class, usernm).getDoubtCollection();
    }

    public Collection<Answer> DoubtAnswers(Integer DoubtID){
        return em.find(Doubt.class, DoubtID).getAnswerCollection();
    }
    
    public Collection<Category> DoubtCategory(Integer DoubtID){
        return em.find(Doubt.class, DoubtID).getCategoryCollection();
    }
    
    public Collection<Tags> DoubtTags(Integer DoubtID){
        return em.find(Doubt.class, DoubtID).getTagsCollection();
    }
     
    public Collection<Doubt> SearchDoubtByTitle(String SearchKey) {
        return em.createNamedQuery("Doubt.findByTitle").setParameter("title", "%" + SearchKey + "%").getResultList();
    }
    
    public Collection<Answer> MyAsnwers(String usernm) {
        return em.find(User.class, usernm).getAnswerCollection();
    }
    
    public Collection<Category> ListCategory() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    public Collection<Tags> ListTags() {
        return em.createNamedQuery("Tags.findAll").getResultList();
    }

    public void CreateDoubt(String usernm,String title, Integer Point, DoubtDes description) {
        Doubt dd = new Doubt(title,description.getDescription(),Point,false);        
        User u = em.find(User.class,usernm);        
        dd.setUsername(u);
        em.persist(dd);
        //System.out.println("---------In Bean Before: -----------" + description);
        Collection<Doubt> dlist = u.getDoubtCollection();
        dlist.add(dd);
        //System.out.println("---------In Bean after: -----------" + description);
        u.setDoubtCollection(dlist);
        //System.out.println("---------In Bean after coll: -----------" + description);
        em.merge(u);
    }

    public void UpdateDoubt(Integer ID, String title, Integer Point, DoubtWrapper dw) {
        Doubt d = em.find(Doubt.class, ID);
        d.setTitle(title);
        d.setDescription(dw.getDescription());
        d.setPoint(Point);
        em.merge(d);
        
        ArrayList<Integer> category = (ArrayList<Integer>) Arrays.stream(dw.getCategorydata()).collect(Collectors.toList());
        ArrayList<Integer> tags = (ArrayList<Integer>) Arrays.stream(dw.getTagsdata()).collect(Collectors.toList());

            d.getCategoryCollection().clear();
            em.merge(d);
            Collection<Category> catlist = d.getCategoryCollection();

            for(Integer cid : dw.getCategorydata())
            {
                Category cat = (Category) em.find(Category.class, cid);

                if(!catlist.contains(cat))
                {
                    Collection<Doubt> doubt = cat.getDoubtCollection();
                    doubt.add(d);
                    catlist.add(cat);
                    d.setCategoryCollection(catlist);
                    cat.setDoubtCollection(doubt);

                    em.merge(d);
                    em.merge(cat);
                    //System.out.println("%%%%%%%%%%%%%% "+cat.getName());
                }
                
            }    
        
        
        
            d.getTagsCollection().clear();
            em.merge(d);
                    Collection<Tags> taglist = d.getTagsCollection();

        for(Integer tid : dw.getTagsdata())
            {
                Tags tag = (Tags) em.find(Tags.class, tid);

                if(!taglist.contains(tag))
                {
                    Collection<Doubt> doubt = tag.getDoubtCollection();
                    doubt.add(d);
                    taglist.add(tag);
                    d.setTagsCollection(taglist);
                    tag.setDoubtCollection(doubt);

                    em.merge(d);
                    em.merge(tag);
                }

            }   
    }

    public void DoubtClosed(Integer AnswerId) {
        Answer a = em.find(Answer.class, AnswerId);
        a.setIsHelpful(true);
        em.merge(a);
        
        Doubt d = em.find(Doubt.class, a.getDoubtID().getId());
        d.setIsClosed(true);
        em.merge(d);
        
        User u = em.find(User.class, a.getUsername().getUsername());
        Integer p = u.getPoints() + d.getPoint();
        u.setPoints(p);
        em.merge(u);
    }

    public void DeleteDoubt(String usernm,Integer Id) {
        Doubt d = em.find(Doubt.class, Id);
        User u = em.find(User.class,usernm);                
        Collection<Doubt> dlist = u.getDoubtCollection();
        dlist.remove(d);
        u.setDoubtCollection(dlist);
        
        em.merge(u);
        em.remove(d);

    }

    public Collection<Doubt> ListDoubts() {
        return em.createNamedQuery("Doubt.findAll").getResultList();
    }

    public Doubt SingleDoubt(Integer DoubtID) {
        return em.find(Doubt.class, DoubtID);
    }

    public void CreateAnswer(Integer DoubtId, String Username, DoubtDes description) {
        
        Answer a = new Answer(description.getDescription(),false);
        Doubt d = em.find(Doubt.class, DoubtId);
        User u = em.find(User.class, Username);
        
        a.setDoubtID(d);
        a.setUsername(u);
        em.persist(a);
        
        
        Collection<Answer> dAnswer = d.getAnswerCollection();
        dAnswer.add(a);
        em.merge(d);
        Collection<Answer> uAnswer = u.getAnswerCollection();
        uAnswer.add(a);
        em.merge(u);

    }

    public void UpdateAnswer(Integer Id, DoubtDes description) {
        Answer a = em.find(Answer.class, Id);
        a.setDescription(description.getDescription());
        em.merge(a);   
    }

    public void DeleteAnswer(Integer Id) {
        Answer a = em.find(Answer.class, Id);
        User u = em.find(User.class,a.getUsername().getUsername()); 
        Doubt d = a.getDoubtID();
        Collection<Answer> adlist = d.getAnswerCollection();
        adlist.remove(a);
        Collection<Answer> alist = u.getAnswerCollection();
        alist.remove(a);
        u.setAnswerCollection(alist);
        d.setAnswerCollection(adlist);
        em.merge(u);
        em.merge(d);
        em.remove(a);   
    }

    public Collection<Answer> ListAnswerByDoubt(Integer DoubtID) {
        return em.find(Doubt.class, DoubtID).getAnswerCollection();
    }

    public Collection<Answer> AllAnswers() {
        return em.createNamedQuery("Answer.findAll").getResultList();
    }
}
