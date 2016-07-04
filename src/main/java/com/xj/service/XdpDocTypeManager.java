package com.xj.service;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xj.model.XdpDocType;

@WebService
public interface XdpDocTypeManager extends GenericManager<XdpDocType, Long> {
	
	@POST
    @Path("/xdpDocType")
	XdpDocType saveXdpDocType(XdpDocType xdpDocType) throws Exception;

    
}