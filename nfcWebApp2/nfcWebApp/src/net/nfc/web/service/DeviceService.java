package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CDevices;
import net.nfc.web.forms.CSearchForm;

public interface DeviceService {

	public void addDevice(CDevices Device ) throws AdException;
	public List<CDevices> listDevices() throws AdException;
	public CDevices getDevice(long id) throws AdException;
	public void updateDevice(CDevices Device) throws AdException;
	public List<CDevices> getDevicesForEmployee(long id) throws AdException;
	public List<CDevices> searchDevice(CSearchForm form) throws AdException;
	public List<CDevices> getFreeDevices() throws AdException;
	
}
