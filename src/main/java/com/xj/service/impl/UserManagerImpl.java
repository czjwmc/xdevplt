package com.xj.service.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xj.dao.UserDao;
import com.xj.model.User;
import com.xj.model.XdpGroup;
import com.xj.dao.XdpGroupDao;
import com.xj.service.UserExistsException;
import com.xj.service.UserManager;
import com.xj.service.UserService;
import com.xj.service.exception.XdpGroupException;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("userManager")
@WebService(serviceName = "UserService", endpointInterface = "com.xj.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager, UserService {
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;
    @Autowired(required = false)
    private SaltSource saltSource;
    XdpGroupDao xdpGroupDao; //add by wmc 2013/10/26

    public UserManagerImpl() {}; // Added by wx Ver0.0.1 for WebService.

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }
    
    @Autowired
    public void setXdpGroupDao(XdpGroupDao xdpGroupDao) {
        this.xdpGroupDao = xdpGroupDao;
    }


    /**
     * {@inheritDoc}
     */
    public User getUser(String userId) {
        return userDao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers() {
        //return userDao.getAllDistinct();
        return userDao.getUsers(); // Modified by wx Ver0.0.1.
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsersEnabled() { // Added by wx Ver0.0.1.
        return userDao.getUsersEnabled();
    }

    /**
     * {@inheritDoc}
     * @throws XdpGroupException 
     */
    public User saveUser(User user,String[] relatedXdpGroups) throws UserExistsException, XdpGroupException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }
        user.setUpdateDate(new Date()); // Added by wx Ver0.0.1.

        // Start: Added by wx Ver0.0.1.
        User currentUser = getCurrentUser();
        if (currentUser != null) {
        	user.setUpdateUser(getCurrentUser().getUsername());
        } else {
        	user.setUpdateUser(user.getUsername());
        }
        // End: Added by wx Ver0.0.1.

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                String currentPassword = userDao.getUserPassword(user.getId());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                if (saltSource == null) {
                    // backwards compatibility
                    user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
                    log.warn("SaltSource not set, encrypting password w/o salt");
                } else {
                    user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
                            saltSource.getSalt(user)));
                }
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }  

        for (int i = 0; relatedXdpGroups != null && i < relatedXdpGroups.length; i++) {
        	String xdpGroupId = relatedXdpGroups[i];
        	try {
        		XdpGroup xdpGroup = xdpGroupDao.get(new Long(xdpGroupId));
            	user.addXdpGroup(xdpGroup);
            } catch (Exception e) {
        		log.warn(e.getMessage());
                throw new XdpGroupException("XdpGroup (" + xdpGroupId + ") not found.");
            }
        }

        try {
            return userDao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(User user) {
        log.debug("removing user: " + user);
        userDao.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userId) throws Exception {
        log.debug("removing user: " + userId);
        //userDao.remove(new Long(userId));
		// Start: Modified by wx Ver0.0.1.
        try { // Modified by wx Ver0.0.1.
        	userDao.remove(new Long(userId));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception("User (" + userId + ") deleting error! May be a related record exists.");
    	}
		// End: Modified by wx Ver0.0.1.
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return (User) userDao.loadUserByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    public List<User> search(String searchTerm) {
        return super.search(searchTerm, User.class);
    }
}
