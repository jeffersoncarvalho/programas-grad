package br.ufc.lia.es.solar.util;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 
 


public class JavaMail 
{ 

  public void sendSimpleMail(String assunto,String to,String mensagem)
  throws Exception 
  { 
     GMail gmail = new GMail(assunto,mensagem,to);
     gmail.send();
  }
  
  public static void main(String[] args) {
	
	  JavaMail jm = new JavaMail();
	  try {
		jm.sendSimpleMail("assunto","jeffersoncarvalho@lia.ufc.br","conteudo");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
} 

class GMail
{
    String  d_email = "solarme@gmail.com",
            d_password = "herbertjefferson",
            d_host = "smtp.gmail.com",
            d_port  = "465",
            m_to = "",
            m_subject = "Testing",
            m_text = "Hey, this is the testing email.";
    
    public GMail(String subject ,String mensagem, String destino)
    {
        this.m_subject = subject;
        this.m_text = mensagem;
        this.m_to = destino;
    }
    
    public void send(){
    	Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
 
        SecurityManager security = System.getSecurityManager();
 
        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            //session.setDebug(true);
 
            MimeMessage msg = new MimeMessage(session);
            //msg.setText(m_text); original
             
            msg.setContent (m_text , "text/html");
            
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport.send(msg);
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        } 
    }
     
 
    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(d_email, d_password);
        }
    }



	public void setM_to(String m_to) {
		this.m_to = m_to;
	}
    
    
}