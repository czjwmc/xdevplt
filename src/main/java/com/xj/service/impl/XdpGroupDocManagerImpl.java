package com.xj.service.impl;

import com.xj.dao.XdpGroupDocDao;
import com.xj.model.XdpGroupDoc;
import com.xj.service.XdpGroupDocManager;
import com.xj.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("xdpGroupDocManager")
@WebService(serviceName = "XdpGroupDocService", endpointInterface = "com.xj.service.XdpGroupDocManager")
public class XdpGroupDocManagerImpl extends GenericManagerImpl<XdpGroupDoc, Long> implements XdpGroupDocManager {
    XdpGroupDocDao xdpGroupDocDao;

    @Autowired
    public XdpGroupDocManagerImpl(XdpGroupDocDao xdpGroupDocDao) {
        super(xdpGroupDocDao);
        this.xdpGroupDocDao = xdpGroupDocDao;
    }
}