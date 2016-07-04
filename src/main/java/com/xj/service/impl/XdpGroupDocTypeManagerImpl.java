package com.xj.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xj.Constants;
import com.xj.dao.XdpGroupDocTypeDao;
import com.xj.model.User;
import com.xj.model.XdpGroup;
import com.xj.model.XdpGroupDocType;
import com.xj.service.XdpGroupDocTypeManager;
import com.xj.service.exception.UniqueException;

@Service("xdpGroupDocTypeManager")
@WebService(serviceName = "XdpGroupDocTypeService", endpointInterface = "com.xj.service.XdpGroupDocTypeManager")
public class XdpGroupDocTypeManagerImpl extends GenericManagerImpl<XdpGroupDocType, Long> implements XdpGroupDocTypeManager {
    XdpGroupDocTypeDao xdpGroupDocTypeDao;



    @Autowired
    public XdpGroupDocTypeManagerImpl(XdpGroupDocTypeDao xdpGroupDocTypeDao) {
        super(xdpGroupDocTypeDao);
        this.xdpGroupDocTypeDao = xdpGroupDocTypeDao;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<XdpGroupDocType> getXdpGroupDocTypes() {
        return xdpGroupDocTypeDao.getXdpGroupDocTypes();
    }
    
    
    /**
     * {@inheritDoc}
     */
    public List<XdpGroupDocType> getXdpGroupDocTypes(String xdpGroupDocTypeId) {
        return xdpGroupDocTypeDao.getXdpGroupDocTypes(xdpGroupDocTypeId);
    }
    
    /**
     * {@inheritDoc}
     * @throws Exception 
     */
    public String upload(String path,String filename,String filePath,File files) throws Exception {    	
        InputStream stream = new FileInputStream(files);     
        //write the file to the file specified
        OutputStream bos = new FileOutputStream(filePath + path + "/"  + filename);
        int bytesRead;
        byte[] buffer = new byte[8192];

        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
    }
    bos.close();
    stream.close();
	return filename;	   
    }
    
    /**
     * {@inheritDoc}
     */
    public XdpGroupDocType saveXdpGroupDocType(XdpGroupDocType xdpGroupDocType) throws Exception {
    	User currentUser = getCurrentUser();
    	xdpGroupDocType.setUpdateUser(currentUser);
    	xdpGroupDocType.setUpdateDate(new Date());
        try {
        	return xdpGroupDocTypeDao.save(xdpGroupDocType);
        } catch (DataIntegrityViolationException e) {
            log.warn(e.getMessage());
            throw new UniqueException("xdpGroupDocType (" + xdpGroupDocType.getName() + ") already exists!");
    	} catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception("xdpGroupDocType (" + xdpGroupDocType.getName() + ") saving error!");
    	}
    }

}

