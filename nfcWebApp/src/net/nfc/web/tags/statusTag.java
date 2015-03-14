package net.nfc.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.nfc.db.entity.CConstrains;
import net.nfc.db.entity.CConstrains.*;
;

public class statusTag extends SimpleTagSupport {
	private String value;


	public void setValue(String value) {
		this.value = value;
	}

	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.print(translate(value));
	}
	
	
	private String translate(String var) {
		
		if(var.equals(eventStatus.CREATED.getText())) {
			return CConstrains.getEventMap().get(var).toUpperCase();
		}
		else if(var.equals(eventStatus.STARTED.getText())) {
			return CConstrains.getEventMap().get(var).toUpperCase();
		}
		else if(var.equals(eventStatus.FINISHED.getText())) {
			return CConstrains.getEventMap().get(var).toUpperCase();
		}
		else if(var.equals(eventStatus.CLOSED.getText())) {
			return CConstrains.getEventMap().get(var).toUpperCase();
		}
		else if(var.equals(devicesStatus.ACTIVE.getText())) {
			return CConstrains.getDeviceMap().get(var).toUpperCase();
		}
		else if(var.equals(devicesStatus.INACTIVE.getText())) {
			return CConstrains.getDeviceMap().get(var).toUpperCase();
		}
		else if(var.equals(devicesStatus.CREATED.getText())) {
			return CConstrains.getDeviceMap().get(var).toUpperCase();
		}
		else if(var.equals(locationStatus.CREATED.getText())) {
			return CConstrains.getLocationMap().get(var).toUpperCase();
		}
		else if(var.equals(locationStatus.ACTIVE.getText())) {
			return CConstrains.getLocationMap().get(var).toUpperCase();
		}
		else if(var.equals(locationStatus.INACTIVE.getText())) {
			return CConstrains.getLocationMap().get(var).toUpperCase();
		}
	
		return " ";
	}
	
	
}
