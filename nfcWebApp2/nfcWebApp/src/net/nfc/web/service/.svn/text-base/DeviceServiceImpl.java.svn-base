/**
 * 
 */
package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CDevicesDAO;
import net.nfc.db.entity.CDevices;
import net.nfc.web.forms.CSearchForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Maciek
 *
 */

@Service("deviceService")
@Transactional
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
	private CDevicesDAO DevicesDAO;
	
	public DeviceServiceImpl() {}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addDevice(CDevices device) throws AdException {
		DevicesDAO.create(device);
	}

	@Transactional
	public List<CDevices> listDevices() throws AdException {
		return DevicesDAO.list();
	}
	
	@Transactional
	public CDevices getDevice(long id) throws AdException {
		return DevicesDAO.get(id);
	}
	
	@Transactional
	public void updateDevice(CDevices device) throws AdException {
		DevicesDAO.update(device);
	}

	@Transactional
	public List<CDevices> getDevicesForEmployee(long id) throws AdException {
		return DevicesDAO.getDevicesForEmployee(id);
	}

	@Override
	public List<CDevices> searchDevice(CSearchForm form) throws AdException {
		return DevicesDAO.searchDevice(form);
	}

	@Override
	public List<CDevices> getFreeDevices() throws AdException {
		return DevicesDAO.getFreeDevices();
	}
	

}
