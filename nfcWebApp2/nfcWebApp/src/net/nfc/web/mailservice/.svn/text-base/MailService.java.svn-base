package net.nfc.web.mailservice;

import java.util.List;

import net.nfc.db.entity.CEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
 
@Service("mailService")
public class MailService {
     
    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage alertMailMessage;

    private String subject = "Nowo utworzone zdarzenia";
    
    public void sendMail(String from, String to, List<CEvent> eventList) {

    	SimpleMailMessage message = new SimpleMailMessage();

    	
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(buildMessage(eventList));
        mailSender.send(message);

    }

    public void sendAlertMail(String alert) {

    	SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);

    	mailMessage.setText(alert);
        mailSender.send(mailMessage);
         
    }
    
    private String buildMessage(List<CEvent> eventList) {
    	
    	StringBuilder msg = new StringBuilder();
    	String newLine = "\n";
    	
    	msg.append("Zosta³y stworzone nastêpuj¹ce zdarzenia oczekuj¹ce na realizcjê : \n" );
    	
    	for (CEvent event : eventList) {
		
    		msg.append("Lokalizacja : " + event.getLocation().getName() + " Komentarz : " + event.getComment() + " Typ : " + event.getEventType());
    		msg.append(newLine);
		}

    	msg.append("\n\n\n\n Wiadomoœæ zosta³a wygenerowa³a automatycznie, nie odpowiadaj na ni¹.");
    	
    	return msg.toString();
    }

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
    
    
}
