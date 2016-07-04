package com.xj.service.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xj.dao.XdpProcessDao;
import com.xj.model.User;
import com.xj.model.XdpProcess;
import com.xj.service.XdpProcessManager;
import com.xj.service.exception.UniqueException;

@Service("xdpProcessManager")
@WebService(serviceName = "XdpProcessService", endpointInterface = "com.xj.service.XdpProcessManager")
public class XdpProcessManagerImpl extends GenericManagerImpl<XdpProcess, Long> implements XdpProcessManager {
    XdpProcessDao xdpProcessDao;

    @Autowired
    public XdpProcessManagerImpl(XdpProcessDao xdpProcessDao) {
        super(xdpProcessDao);
        this.xdpProcessDao = xdpProcessDao;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<XdpProcess> getXdpProcessesEnabled() {
        return xdpProcessDao.getXdpProcessesEnabled();
    }

    
    /**
     * {@inheritDoc}
     */
    public XdpProcess saveXdpProcess(XdpProcess xdpProcess) throws Exception {
    	User currentUser = getCurrentUser();
    	xdpProcess.setUpdateUser(currentUser);
    	xdpProcess.setUpdateDate(new Date());
        try {
        	return xdpProcessDao.save(xdpProcess);
        } catch (DataIntegrityViolationException e) {
            log.warn(e.getMessage());
            throw new UniqueException("xdpProcess (" + xdpProcess.getName() + ") already exists!");
    	} catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception("xdpProcess (" + xdpProcess.getName() + ") saving error!");
    	}
    }

}