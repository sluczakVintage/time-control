/**
 * 
 */
package net.nfc.db.dao;

import java.util.List;



import net.nfc.db.entity.CLocation;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek
 * 24-06-2011
 *
 */
public interface CLocationDAO {
	
	public List<CLocation> list() throws AdException ;

	public CLocation get(long id) throws AdException ;
	
	public boolean checkTag(String tag) throws AdException ;
	
	public CLocation create(CLocation loc) throws AdException ;
	
	public void update(CLocation loc) throws AdException ;
	
	public CLocation getLocationForTag(String tagID) throws AdException;
	
	public List<CLocation> listLocationsForCompany(long id) throws AdException;
	
	public List<CLocation> searchLocation(CSearchForm searchForm) throws AdException;
	
	public List<CLocation> listActiveLocations();
}
