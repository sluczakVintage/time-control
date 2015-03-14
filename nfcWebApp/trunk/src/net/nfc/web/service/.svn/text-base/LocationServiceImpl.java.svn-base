package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CLocationDAO;
import net.nfc.db.entity.CLocation;
import net.nfc.web.forms.CSearchForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Maciek
 *
 */
@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService{

	@Autowired
	private CLocationDAO locationDAO;
	
	public LocationServiceImpl() {}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addLocation(CLocation location) throws AdException {
		locationDAO.create(location);
	}

	@Transactional
	public List<CLocation> listLocations() throws AdException {
		return locationDAO.list();
	}
	
	@Transactional
	public CLocation getLocation(long id) throws AdException {
		return locationDAO.get(id);
	}

	@Transactional
	public void updateLocation(CLocation location) throws AdException {
		locationDAO.update(location);
	}

	
	@Transactional
	public List<CLocation> listLocationsForCompany(long companyID) throws AdException {
		return locationDAO.listLocationsForCompany(companyID);
	}

	@Override
	public List<CLocation> searchLocation(CSearchForm form) throws AdException {
		return locationDAO.searchLocation(form);
	}

	@Override
	public List<CLocation> listActiveLocations() {
		return locationDAO.listActiveLocations();
	}
	
}
