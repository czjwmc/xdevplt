package com.xj.service;

import com.xj.service.GenericManager;
import com.xj.model.XdpSystem;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface XdpSystemManager extends GenericManager<XdpSystem, Long> {
    
}