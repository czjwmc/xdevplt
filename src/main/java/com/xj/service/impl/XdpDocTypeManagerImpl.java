package com.xj.service.impl;

import java.util.Date;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xj.dao.XdpDocTypeDao;
import com.xj.model.User;
import com.xj.model.XdpDocType;
import com.xj.model.XdpProcess;
import com.xj.service.XdpDocTypeManager;
import com.xj.service.exception.UniqueException;

@Service("xdpDocTypeManager")
@WebService(serviceName = "XdpDocTypeService", endpointInterface = "com.xj.service.XdpDocTypeManager")
public class XdpDocTypeManagerImpl extends GenericManagerImpl<XdpDocType, Long> implements XdpDocTypeManager {
    XdpDocTypeDao xdpDocTypeDao;
    XdpProcess xdpProcess;

    @Autowired
    public XdpDocTypeManagerImpl(XdpDocTypeDao xdpDocTypeDao) {
        super(xdpDocTypeDao);
        this.xdpDocTypeDao = xdpDocTypeDao;
    }
    
    /**
     * {@inheritDoc}
     */
    public XdpDocType saveXdpDocType(XdpDocType xdpDocType) throws Exception {
    	User currentUser = getCurrentUser();
    	xdpDocType.setUpdateUser(currentUser);
    	xdpDocType.setUpdateDate(new Date());
        try {
        	return xdpDocTypeDao.save(xdpDocType);
        } catch (DataIntegrityViolationException e) {
            log.warn(e.getMessage());
            throw new UniqueException("xdpDocType (" + xdpDocType.getName() + ") already exists!");
    	} catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception("xdpDocType (" + xdpDocType.getName() + ") saving error!");
    	}
    }

}