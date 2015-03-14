/**
 * 
 */
package net.nfc.db.dao;

import java.util.List;



import net.nfc.db.entity.CDevices;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek24-06-2011
 *
 */
public interface CDevicesDAO {

	public List<CDevices> list() throws AdException ;

	public CDevices get(long id) throws AdException ;
	
	public CDevices create(CDevices device) throws AdException ;
	
	public void update(CDevices device) throws AdException ;
	
	public CDevices getDeviceForEmployee(long empID) throws AdException ;
	
	public List<CDevices> getDevicesForEmployee(long id) throws AdException ;
	
	public List<CDevices> searchDevice(CSearchForm searchForm) throws AdException;
	
	public List<CDevices> getFreeDevices() throws AdException;
	
}
