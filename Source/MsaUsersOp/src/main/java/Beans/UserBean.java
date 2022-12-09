/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;
import EmailService.SendEmail;
import Entity.Groups;
import Entity.User;
import java.io.File;
import java.util.Collection;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;


/**
 *
 * @author HP
 */
@Stateless
public class UserBean {

     @PersistenceContext(unitName = "UserPU")
    EntityManager em;
    Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();
    SendEmail se = new SendEmail();
   
    public Boolean sendEmailMessage(String toEmail, String subject, String body) {
     
                String USER_NAME = "doubt******@gmail.com";
                 String PASSSWORD = "hhbzcc*****qe";  //Password of the Google(gmail) account
            String FROM_ADDRESS = toEmail; 
            //Create email sending properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

           Session session = Session.getInstance(props,
           new javax.mail.Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(USER_NAME, PASSSWORD);
          }
           });

         try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME)); //Set from address of the email
            message.setContent(body,"text/html"); //set content type of the email

           message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(FROM_ADDRESS)); //Set email recipient

           message.setSubject(subject); //Set email message subject
           Transport.send(message); //Send email message

          //System.out.println("sent email successfully!");
          return true;

         } catch (MessagingException e) {
             return false;
       //       throw new RuntimeException(e);
         }
    }

        
        
    public void registerUser(String Name, String Email, String Password, Integer Semester,Integer OTP,Integer Points,Integer Warnings,Boolean isBlocked) {
        User u = new User(Name,Email,pb.generate(Password.toCharArray()),Semester,OTP,Points,Warnings,isBlocked);
        em.persist(u);
        
       Collection<Groups> group = em.createNamedQuery("Groups.findByGroupname").setParameter("groupname", "User").getResultList();
       u.setGroupsCollection(group);
       
       Groups g = em.find(Groups.class, "User");
       Collection<User> userg = g.getUserCollection();
       userg.add(u);
       g.setUserCollection(userg);
       em.merge(u);
    }  

    public Integer GenerateOTP() {
      Integer min = 100000;
      Integer max = 999999;
        
      //Generate random int value from 50 to 100 
      //System.out.println("Random value in int from "+min+" to "+max+ ":");
      return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    
    public Boolean ForgotPassword(String Email) {
        
           User u = (User) em.createNamedQuery("User.findByEmail").setParameter("email", Email).getResultList().get(0);
           Integer randomnum = this.GenerateOTP();
           u.setOtp(randomnum);
           em.merge(u);
           return this.sendEmailMessage(u.getEmail(), "Forgot Password OTP",String.valueOf(randomnum)) ;
        
//    JavaEmailSender email = new JavaEmailSender();
//     //Sending test email
//      return email.createAndSendEmail("badkaspoorvi@gmail.com", "Test email subject",
//      "Congratulations !!! \nThis is test email sent by java class.");
       
    }

    public Boolean ResetPassword(String Email, String Password, String OTP) {

        User u = (User) em.createNamedQuery("User.findByEmail").setParameter("email", Email).getResultList().get(0);
        if(OTP.equals(String.valueOf( u.getOtp() ) ) )
        {
            u.setPassword(pb.generate(Password.toCharArray()));
            u.setOtp(000000);
            em.merge(u);
            return true;
        }
        return false;
        
    }

    public Boolean ChangePassword(String usernm,String oldPassword,String newPassword) {

        User u = (User) em.createNamedQuery("User.findByUsername").setParameter("username", usernm).getSingleResult();
        if(pb.verify(oldPassword.toCharArray(), u.getPassword()))
        {
            u.setPassword(pb.generate(newPassword.toCharArray()));
            em.merge(u);
            return true;
        }
        return false;
        
    }
    
    public User UserInfo(String Username) {
                //System.out.println("@@@@@@@@@@@@@@@BEan"+Username);
                //System.out.println("#############"+em.createNamedQuery("User.findByUsername").setParameter("username",Username).getSingleResult().toString());
        return (User) em.createNamedQuery("User.findByUsername").setParameter("username",Username).getSingleResult();
    }

    public Integer CountAllUsers() {
        return (Integer) em.createNamedQuery("User.findAll").getResultList().size();
    }

    public Integer CountAllUsersGTpoints(Integer Points) {
        return (Integer) em.createNamedQuery("User.findByPoints").setParameter("points",Points).getResultList().size();
    }

    public Integer CountActiveUsers() {
        return (Integer) em.createNamedQuery("User.findByIsBlocked").setParameter("isBlocked",false).getResultList().size();
    }
    
    
    
    
    public Collection<User> AllUsers() {
        return  em.createNamedQuery("User.findAll").getResultList();
    }

    public void BlockUser(String username) {
        User u = em.find(User.class, username);
        u.setIsBlocked(true);
        em.merge(u);
    }
    
    public void UnBlockUser(String username) {
        User u = em.find(User.class, username);
        u.setIsBlocked(false);
        em.merge(u);
    }
    
    public void DeleteUser(String username) {
        User u = em.find(User.class, username);
        em.remove(u);
    }
    
     public void SaveProfile(String username ,String profileurl) {
        User u = (User) em.find(User.class, username);
        //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%" + username);
        //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%" + profileurl);

        if(u.getProfile() != null)
        {
            File ff = new File("F:\\DoubtCartMSA\\DoubtCart\\src\\main\\webapp\\WebApp\\FileAssets\\Profile",u.getProfile());
            if(ff.exists())
            {
                ff.deleteOnExit();
            }
        }
        u.setProfile(profileurl);
        em.merge(u);
    }
   
    
}
