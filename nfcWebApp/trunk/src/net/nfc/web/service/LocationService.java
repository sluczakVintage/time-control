/**
 * 
 */
package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CLocation;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek
 *
 */
public interface LocationService {
	
	public void addLocation(CLocation location ) throws AdException;
	public List<CLocation> listLocations() throws AdException;
	public CLocation getLocation(long id) throws AdException;
	public void updateLocation(CLocation location) throws AdException;
	public List<CLocation> listLocationsForCompany(long companyID) throws AdException;
	public List<CLocation> searchLocation(CSearchForm form) throws AdException;
	public List<CLocation> listActiveLocations() ;
}
