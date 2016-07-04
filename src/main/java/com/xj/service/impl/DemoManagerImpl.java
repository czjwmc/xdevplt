package com.xj.service.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xj.dao.DemoDao;
import com.xj.model.Demo;
import com.xj.model.User;
import com.xj.service.DemoManager;
import com.xj.service.exception.UniqueException;

@Service("demoManager")
@WebService(serviceName = "DemoService", endpointInterface = "com.xj.service.DemoManager")
public class DemoManagerImpl extends GenericManagerImpl<Demo, Long> implements DemoManager {
    DemoDao demoDao;

    @Autowired
    public DemoManagerImpl(DemoDao demoDao) {
        super(demoDao);
        this.demoDao = demoDao;
    }

    /**
     * {@inheritDoc}
     */
    public Demo getDemo(String id) {
        return demoDao.get(new Long(id));
    }

    /**
     * {@inheritDoc}
     */
    public List<Demo> getDemos() {
        return demoDao.getDemos();
    }

    /**
     * {@inheritDoc}
     */
    public List<Demo> getDemosEnabled() {
        return demoDao.getDemosEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public List<Demo> searchDemos(String searchTerm) {
        return super.search(searchTerm, Demo.class);
    }

    /**
     * {@inheritDoc}
     */
    public Demo getDemoNew() {
    	Demo demo = new Demo();
    	demo.setEnabled(true);
        return demo;
    }

    /**
     * {@inheritDoc}
     */
    public Demo saveDemo(Demo demo) throws UniqueException, Exception {
    	boolean isNew = (demo.getId() == null);
    	User currentUser = getCurrentUser();
    	if (isNew) {
    		demo.setCreateUser(currentUser);
    		demo.setCreateDate(new Date());
    	}
    	demo.setUpdateUser(currentUser);
    	demo.setUpdateDate(new Date());

        try {
        	return demoDao.save(demo);
        } catch (DataIntegrityViolationException e) {
            log.warn(e.getMessage());
            throw new UniqueException("Demo (" + demo.getName() + ") already exists!");
    	} catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception("Demo (" + demo.getName() + ") saving error!");
    	}
    }

    /**
     * {@inheritDoc}
     */
    public void removeDemo(String id) throws Exception {
        try {
        	demoDao.remove(new Long(id));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception("Demo (" + id + ") deleting error! May be a related record exists.");
    	}
    }
}