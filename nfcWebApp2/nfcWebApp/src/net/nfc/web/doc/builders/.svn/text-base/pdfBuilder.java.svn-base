package net.nfc.web.doc.builders;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.nfc.db.entity.CEmployee;
import net.nfc.db.entity.CEvent;
import net.nfc.db.entity.CUsers;
import net.nfc.web.service.EmployeeService;
import net.nfc.web.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service("pdfBuilder")
public class pdfBuilder extends AbstractPdfView{
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	private static Font catFont = new Font(Font.TIMES_ROMAN, 18,Font.BOLD);
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 12,Font.BOLD);
	
	

	protected void buildPdfDocument(
			Map<String, Object> model,
			Document doc,
			PdfWriter writer,
			HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		CEvent event = (CEvent)model.get("event");
		long t;
		
		if(event.getEventFinishDate() == null ) {
			t = 0;
		}
		else  t = event.getEventFinishDate().getTime() - event.getEventStartDate().getTime(); 
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		
		CUsers user = userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		CEmployee employee = employeeService.getEmployeeForUser(user.getId());
		
		
		SimpleDateFormat format= new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		doc.add(new Paragraph("Szczegó³y zdarzenia : ", catFont));
		
		doc.add(new Paragraph("Wygenerowano przez : " + employee.getName() + " " + employee.getLastName() + ",  " + formatter.format(new java.util.Date().getTime()) , smallBold));
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph("ID : " + event.getId()));
		doc.add(new Paragraph("Nazwa Lokalizacji : " + event.getLocation().getName()));
		if(event.getEventType() != null ) { doc.add(new Paragraph("Typ Zdarzenia : " + event.getEventType())); }
		else doc.add(new Paragraph("Typ Zdarzenia : " )); 
		doc.add(new Paragraph("Data Utworznia : " + formatter.format(event.getEventCreationDate().getTime())));
		doc.add(new Paragraph("Data Rozpoczêcia : " + formatter.format(event.getEventStartDate().getTime())));
		if(event.getEventFinishDate() != null ) {doc.add(new Paragraph("Data Zakoñczenia : " + formatter.format(event.getEventFinishDate().getTime()))); }
		else doc.add(new Paragraph("Data Zakoñczenia : " ));
		doc.add(new Paragraph("Czas : " + format.format(t)));
		doc.add(new Paragraph("Status zdarzenia : " + event.getStatus()));
		doc.add(new Paragraph("Zdarzenie utworzy³ : " + event.getCreator().getUserName()));
		if(event.getUser() != null ) {doc.add(new Paragraph("Zdarzenie obs³ugiwa³ : " + event.getUser().getUserName())); }
		else doc.add(new Paragraph("Zdarzenie obs³ugiwa³ : " ));
		if(event.getComment() != null ) {doc.add(new Paragraph("Komentarz : " + event.getComment())); }
		else doc.add(new Paragraph("Komentarz : " ));
		doc.add(new Paragraph("Tag ID : " + event.getTagId()));
		if(event.getToken() != null ) {doc.add(new Paragraph("Token : " + event.getToken())); }
		else doc.add(new Paragraph("Token : " ));
		
	}

}
