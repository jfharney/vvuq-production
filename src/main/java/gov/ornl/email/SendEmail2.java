package gov.ornl.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail2 {

    String host, port, emailid,username, password;
    Properties props = System.getProperties();
    Session l_session = null;
/*
    public static void main(String [] args) {
    	SendEmail2 s = new SendEmail2();
    }
*/    
    public SendEmail2() {
        host = "smtp.mail.yahoo.com";
        port = "587";
        emailid = "kjchrebet77@yahoo.com";
        username = "kjchrebet77";
        password = "mattryan12";

        emailSettings();
        createSession();
        sendMessage("kjchrebet77@yahoo.com", "kjchrebet77@yahoo.com","Test","test Mail");
    }

    public void emailSettings() {
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", port);
        
        props.setProperty("mail.smtp.user", "kjchrebet77");
        props.setProperty("mail.smtp.password", "mattryan12");
        props.setProperty("mail.smtp.auth", "true"); 
//        props.put("mail.smtp.socketFactory.port", port);
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");

    }

    public void createSession() {

        l_session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        l_session.setDebug(true); // Enable the debug mode

    }

    public boolean sendMessage(String emailFromUser, String toEmail, String subject, String msg) {
        //System.out.println("Inside sendMessage 2 :: >> ");
        try {
            //System.out.println("Sending Message *********************************** ");
            MimeMessage message = new MimeMessage(l_session);
            emailid = emailFromUser;
            //System.out.println("mail id in property ============= >>>>>>>>>>>>>> " + emailid);
            //message.setFrom(new InternetAddress(emailid));
            message.setFrom(new InternetAddress(this.emailid));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            //message.addRecipient(Message.RecipientType.BCC, new InternetAddress(AppConstants.fromEmail));
            message.setSubject(subject);
            message.setContent(msg, "text/html");

            //message.setText(msg);
            Transport.send(message);
            System.out.println("Message Sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }//end catch block
        return true;
    }

}