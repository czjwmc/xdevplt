package com.xj.service;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xj.model.User;
import com.xj.model.XdpGroup;

@WebService
public interface XdpGroupManager extends GenericManager<XdpGroup, Long> {
	
	Set<XdpGroup>  addxdpGroup(User user,String[] relatedXdpGroups) throws Exception;
	
	 /**
     * Retrieves a list of all xdpGroups.
     *
     * @return List
     */
	@GET
    @Path("/xdpGroups")
    List<XdpGroup> getXdpGroups();
	
    /**
     * Retrieves a list of all enabled xdpGroups.
     *
     * @return List
     */
	@GET
    @Path("/xdpGroupsEnabled")
    List<XdpGroup> getXdpGroupsEnabled();
	
	@POST
    @Path("/xdpGroup")
	XdpGroup saveXdpGroup(XdpGroup xdpGroup) throws Exception;


}