package com.xj.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xj.dao.XdpGroupDao;
import com.xj.model.User;
import com.xj.model.XdpGroup;
import com.xj.service.XdpGroupManager;
import com.xj.service.exception.UniqueException;

@Service("xdpGroupManager")
@WebService(serviceName = "XdpGroupService", endpointInterface = "com.xj.service.XdpGroupManager")
public class XdpGroupManagerImpl extends GenericManagerImpl<XdpGroup, Long> implements XdpGroupManager {
    XdpGroupDao xdpGroupDao;

    @Autowired
    public XdpGroupManagerImpl(XdpGroupDao xdpGroupDao) {
        super(xdpGroupDao);
        this.xdpGroupDao = xdpGroupDao;
    }
    
    public Set<XdpGroup> addxdpGroup(User user,String[] relatedXdpGroups) throws Exception{
		user.getXdpGroups().clear();
		Set<XdpGroup> xdpGroups = new HashSet<XdpGroup>();
		for (int i = 0; relatedXdpGroups != null && i < relatedXdpGroups.length; i++) {
			String xdpGroupId = relatedXdpGroups[i];
			XdpGroup xdpGroup = xdpGroupDao.get(new Long(xdpGroupId));
			xdpGroups.add(xdpGroup);
		}
		   	return xdpGroups;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<XdpGroup> getXdpGroups() {
        return xdpGroupDao.getXdpGroups();
    }
   
    /**
     * {@inheritDoc}
     */
    public List<XdpGroup> getXdpGroupsEnabled() {
        return xdpGroupDao.getXdpGroupsEnabled();
    }
    
    /**
     * {@inheritDoc}
     */
    public XdpGroup saveXdpGroup(XdpGroup xdpGroup) throws Exception {
    	User currentUser = getCurrentUser();
    	xdpGroup.setUpdateUser(currentUser);   	
    	xdpGroup.setUpdateDate(new Date());
        try {
        	return xdpGroupDao.save(xdpGroup);
        } catch (DataIntegrityViolationException e) {
            log.warn(e.getMessage());
            throw new UniqueException("xdpGroup (" + xdpGroup.getName() + ") already exists!");
    	} catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception("xdpGroup (" + xdpGroup.getName() + ") saving error!");
    	}
    }

}