package net.nfc.web.forms;

import java.util.List;

import net.nfc.db.entity.CEvent;

public class CObjectForm {

	public List<?> list;
	public List<?> getObjectList() {
		return list;
	}
	
	public void setObjectList(List<?> obj) {
		this.list = obj;
	}
	
}
