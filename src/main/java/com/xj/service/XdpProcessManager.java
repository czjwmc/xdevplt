package com.xj.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.xj.model.XdpProcess;

@WebService
public interface XdpProcessManager extends GenericManager<XdpProcess, Long> {
	
	@POST
    @Path("/xdpProcess")
	XdpProcess saveXdpProcess(XdpProcess xdpProcess) throws Exception;
	
    /**
     * Retrieves a list of all enabled XdpProcesses.
     *
     * @return List
     */
	@POST
    @Path("/xdpProcessesEnabled")
    List<XdpProcess> getXdpProcessesEnabled();

    
}