package com.xj.service;

import java.io.File;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.xj.model.XdpGroupDocType;



@WebService
public interface XdpGroupDocTypeManager extends GenericManager<XdpGroupDocType, Long> {
	
    /**
     * Retrieves a list of all xdpGroupDocTypes.
     *
     * @return List
     */
	@GET
    @Path("/xdpGroupDocTypes")
    List<XdpGroupDocType> getXdpGroupDocTypes();
	
    /**
     * Retrieves a list of  xdpGroupDocTypes by xdpGroupDocTypeId .
     *
     * @return List
     */
	@GET
    @Path("/xdpGroupDocTypes/{xdpGroupDocTypeId}")
    List<XdpGroupDocType> getXdpGroupDocTypes(@PathParam("xdpGroupDocTypeId") String xdpGroupDocTypeId);
	
    /**
     * Retrieves a list of all xdpGroupDocTypes.
     *
     * @return List
     * @throws Exception 
     */
	@GET
    @Path("/xdpGroupDocTypes")
    String upload(String path,String filename,String filePath,File files) throws Exception;
	
	@POST
    @Path("/XdpGroupDocType")
	XdpGroupDocType saveXdpGroupDocType(XdpGroupDocType xdpGroupDocType) throws Exception;
	
    
}