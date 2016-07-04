package com.xj.service.impl;

import com.xj.dao.XdpSystemDao;
import com.xj.model.XdpSystem;
import com.xj.service.XdpSystemManager;
import com.xj.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("xdpSystemManager")
@WebService(serviceName = "XdpSystemService", endpointInterface = "com.xj.service.XdpSystemManager")
public class XdpSystemManagerImpl extends GenericManagerImpl<XdpSystem, Long> implements XdpSystemManager {
    XdpSystemDao xdpSystemDao;

    @Autowired
    public XdpSystemManagerImpl(XdpSystemDao xdpSystemDao) {
        super(xdpSystemDao);
        this.xdpSystemDao = xdpSystemDao;
    }
}