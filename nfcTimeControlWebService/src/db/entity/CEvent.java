package db.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import db.dao.AdException;
public class CEvent {
	private long id;
	private String eventType;
	private Timestamp eventCreationDate;
	private Timestamp eventFinishDate;
	private CUsers user;
	private String comment;
	private String status;
	private String tagId;
	private Timestamp eventStartDate;
	private CUsers creator;
	private String token;
	private Timestamp eventSystemStartDate;
	private Timestamp eventSystemFinishDate;	
	private CLocation location;
	
	public CEvent() {}
	
	public CEvent(String eventType, 
								Timestamp eventCreationDate,
								Timestamp eventFinishDate,
								CUsers user, 
								String comment,
								String status, 
								String tagId,
								Timestamp eventStartDate, 
								CUsers creator, 
								String token,
								Timestamp eventSystemStartDate,
								Timestamp eventSystemFinishDate,
								CLocation location) {
		super();
		this.eventType = eventType;
		this.eventCreationDate = eventCreationDate;
		this.eventFinishDate = eventFinishDate;
		this.user = user;
		this.comment = comment;
		this.status = status;
		this.tagId = tagId;
		this.eventStartDate = eventStartDate;
		this.creator = creator;
		this.token = token;
		this.eventSystemStartDate = eventSystemStartDate;
		this.eventSystemFinishDate = eventSystemFinishDate;
		this.location = location;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Timestamp getEventCreationDate() {
		return eventCreationDate;
	}
	public void setEventCreationDate(Timestamp eventCreationDate) {
		this.eventCreationDate = eventCreationDate;
	}
	public Timestamp getEventFinishDate() {
		return eventFinishDate;
	}
	public void setEventFinishDate(Timestamp eventFinishDate) {
		this.eventFinishDate = eventFinishDate;
	}
	public CUsers getUser() {
		return user;
	}
	public void setUser(CUsers user) {
		this.user = user;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public Timestamp getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(Timestamp eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public CUsers getCreator() {
		return creator;
	}
	public void setCreator(CUsers creator) {
		this.creator = creator;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	public Timestamp getEventSystemStartDate() {
		return eventSystemStartDate;
	}
	public void setEventSystemStartDate(Timestamp eventSystemStartDate) {
		this.eventSystemStartDate = eventSystemStartDate;
	}
	public Timestamp getEventSystemFinishDate() {
		return eventSystemFinishDate;
	}
	public void setEventSystemFinishDate(Timestamp eventSystemFinishDate) {
		this.eventSystemFinishDate = eventSystemFinishDate;
	}
	
	public CLocation getLocation() {
		return location;
	}

	public void setLocation(CLocation location) {
		this.location = location;
	}

	public String createToken() throws AdException {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			String text  = getStringTimestamp(eventCreationDate) +user.getUserName()+tagId;
			String hash = calculateHash(sha1,text);
			return hash;
		}catch (Exception e) {
			throw new AdException("Could not create hash " , e);
			
	    }
		
	}
	
    private static String calculateHash(MessageDigest algorithm,  String text) throws Exception{

       InputStream is = new ByteArrayInputStream(text.getBytes("UTF-8"));
       
       DigestInputStream   dis = new DigestInputStream(is, algorithm);

       // read the file and update the hash calculation
       while (dis.read() != -1);

       // get the hash value as byte array
       byte[] hash = algorithm.digest();

        return byteArray2Hex(hash);
    }

    private static String byteArray2Hex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
    
    private static String getStringTimestamp(Timestamp ts ) {
    	
    	String S = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss ").format(ts);
    	return S;
    }

}
