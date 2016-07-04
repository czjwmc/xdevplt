package com.xj.service;

import com.xj.service.GenericManager;
import com.xj.model.XdpGroupDoc;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface XdpGroupDocManager extends GenericManager<XdpGroupDoc, Long> {
    
}