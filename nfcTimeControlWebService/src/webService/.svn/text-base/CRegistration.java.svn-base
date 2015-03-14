package webService;

import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import webService.request.CRequestRegistration;
import webService.response.CResponse;

import db.dao.AdException;
import db.dao.CEventDAOImpl;
import db.dao.CLocationDAO;
import db.dao.CLocationDAOImpl;
import db.dao.CUserDAOImpl;
import db.entity.CConstrains.eventStatus;
import db.entity.CConstrains.responseStatus;
import db.entity.CEvent;
import db.entity.CUsers;
/**
 * 
 * @author Maciek
 *
 */
@Path ("/reg")
public class CRegistration {

	private CUserDAOImpl userDAO = new CUserDAOImpl();
	private CEventDAOImpl eventDAO = new CEventDAOImpl();
	private CLocationDAOImpl locationDAO = new CLocationDAOImpl();
	
	@GET
	@Produces( MediaType.TEXT_PLAIN )
	public String sayHello() {
		return "hello World";
	}
	
	    @POST		
		@Produces("application/json")
		@Consumes("application/json")
		public CResponse Connect(CRequestRegistration request ) {
		
	    request = request.decrypt();
		System.out.println("Username " + request.getUser_login());
		System.out.println("Password " + request.getUser_password());
		System.out.println("Imei " + request.getImei());
		System.out.println("imsi " +request.getImsi());
	    	
	    System.out.println("Connect");
		
	    long Ldate = request.getDate();
		String user_name = request.getUser_login();
		String pass = request.getUser_password();
		String tagID = request.getTag_id();
		String token = request.getToken();
		String imsi = request.getImsi();
		String imei = request.getImei();
		
		Timestamp date = new Timestamp(Ldate);		

		try {
		CUsers user = userDAO.get(user_name);
		if(CUtility.checkUser(user, pass, imei, imsi )) {
		//if(user.getUserPassword().equals(pass)) {
		
			//jeøeli istnieje token to znajdü dla niego events started
			if(!token.equals("null")) {
				
				CEvent event = eventDAO.getStartedEvent(tagID, token);
				event.setEventFinishDate(date);
				event.setEventSystemFinishDate( new Timestamp(new java.util.Date().getTime()));
				event.setStatus(eventStatus.FINISHED.getText());
				eventDAO.update(event);	
			    System.out.println("EVENT_FINISHED");
				return new CResponse(responseStatus.EVENT_FINISHED.getText(), null, null);
			} 
			else {
				
				if(! eventDAO.isStartedEventFroUser(user.getId())) {
				
					CEvent event = eventDAO.getCreatedEvent(tagID);
					
					if(event != null) {
						//if event is created
						event.setStatus(eventStatus.STARTED.getText());
						event.setEventStartDate(date);
						event.setEventSystemStartDate( new Timestamp(new java.util.Date().getTime()));
						event.setUser(user);
						event.setToken(event.createToken());
						eventDAO.update(event);
						System.out.println(responseStatus.EVENT_STARTED.getText());
						return new CResponse(responseStatus.EVENT_STARTED.getText(),event.getComment(), event.getToken()) ;
					}
					else {
						
						if(locationDAO.getLocationForTag(tagID)== null ) {
							return new CResponse(responseStatus.TAG_NOT_FOUND.getText(), null,null);
						}
						else {
							// no event for tagID or token
							CEvent newEvent = new CEvent();
							newEvent.setEventCreationDate(date);
							newEvent.setEventStartDate(date);
							newEvent.setEventSystemStartDate( new Timestamp(new java.util.Date().getTime()));
							newEvent.setUser(user);
							newEvent.setCreator(user);
							newEvent.setStatus(eventStatus.STARTED.getText());
							newEvent.setComment("Event Created - need more comment");
							newEvent.setTagId(tagID);
							newEvent.setToken(newEvent.createToken());
							newEvent.setLocation(locationDAO.getLocationForTag(tagID));
							eventDAO.create(newEvent);
							System.out.println(responseStatus.CREATED_AND_STARTED.getText());
							return new CResponse(responseStatus.CREATED_AND_STARTED.getText(), newEvent.getComment(), newEvent.getToken());
						}
					}
				} else return new CResponse(responseStatus.EVENT_ALREADY_STARTED.getText(),null,null);
			}
		}
		else return new CResponse(responseStatus.WRONG_PASS.getText(),null,null);
		}catch (AdException e) {
		 System.out.println(e.getMessage());
		 System.out.println(responseStatus.USER_NOT_FOUND.getText());
		 return  new CResponse(responseStatus.USER_NOT_FOUND.getText(),null,null);
		 
	 }
	}
	
}
