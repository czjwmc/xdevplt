package com.xj.dao;

import java.util.List;

import com.xj.model.XdpProcess;

/**
 * An interface that provides a data management interface to the XdpProcess table.
 */
public interface XdpProcessDao extends GenericDao<XdpProcess, Long> {
	
    /**
     * Gets a list of enabled xdpGroupDocTypes ordered by the id desc.
     *
     * @return List populated list of xdpGroupDocTypes
     */
    List<XdpProcess> getXdpProcessesEnabled();


}