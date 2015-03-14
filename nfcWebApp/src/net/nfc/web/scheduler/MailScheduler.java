package net.nfc.web.scheduler;

import java.util.List;

import javax.annotation.processing.Processor;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CConstrains.eventStatus;
import net.nfc.db.entity.CEmployee;
import net.nfc.db.entity.CEvent;
import net.nfc.web.forms.CSearchForm;
import net.nfc.web.mailservice.MailService;
import net.nfc.web.service.EmployeeService;
import net.nfc.web.service.EventService;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("mailScheduler")
public class MailScheduler {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private EventService eventService;
	
	
	private TaskScheduler taskScheduler ;
	
	private long time = 60000;
	
	
	
	private String from = "info@nfcTimeControl.com";
	
	Logger logger = Logger.getLogger("MailScheduler");
	
	
	private void sendEmails(List<CEvent> eventList) throws AdException{
		
		List<CEmployee> list = employeeService.listEmployeesWithUsers();
		
		
		for (CEmployee cEmployee : list) {
			mailService.sendMail(from, cEmployee.getEmail(), eventList);
		}
	}
	//@Scheduled(fixedRate = 900000)
	public void process() throws AdException{
		CSearchForm form = new CSearchForm();
		form.setStatus(eventStatus.CREATED.getText());
		
		final List<CEvent> list = eventService.searchEvent(form);
		
		if(list != null ){
			sendEmails(list);
//			Runnable task = new Runnable() {
//				public void run() {
//					try {
//						sendEmails(list);
//					} catch (AdException e) {
//						e.printStackTrace();
//					}
//				}
//			};
//			taskScheduler.scheduleAtFixedRate(task, time);
		}
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	
	
}
