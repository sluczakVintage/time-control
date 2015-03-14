package net.nfc.web.service;

import java.util.ArrayList;
import java.util.Collection;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CUserDAO;
import net.nfc.db.dao.CUserPrivilegesDAO;
import net.nfc.db.entity.CUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CUserDAO userDAO;
	
	@Autowired
	private CUserPrivilegesDAO userPrivilegesDAO;
	
	
	
	@Transactional
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		try{
		
		CUsers user = userDAO.get(userName);
		 
	    String username = user.getUserName();
	    String password = user.getUserPassword();
    	boolean enabled ;
	    boolean accountNonExpired;
	    boolean credentialsNonExpired ;
	    boolean accountNonLocked ;
	    
	    if(user.getIsActive() == '1') {
	    	enabled = true;
		    accountNonExpired = true;
		    credentialsNonExpired = true;
		    accountNonLocked = true;
	    }
	    else {
	    	enabled = false;
		    accountNonExpired = false;
		    credentialsNonExpired = false;
		    accountNonLocked = false;
	    }
	    	    
	    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	      authorities.add(new GrantedAuthorityImpl(userPrivilegesDAO.get(user.getId()).getRole()));
	      
	     User springUser = new User(username, password, enabled,
	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
   
	     return springUser;
	     
		}catch (AdException e) {
			throw new UsernameNotFoundException("user not found");
			
		}
	}
}
