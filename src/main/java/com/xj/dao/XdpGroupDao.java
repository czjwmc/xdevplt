package com.xj.dao;

import java.util.List;

import com.xj.model.XdpGroup;

/**
 * An interface that provides a data management interface to the XdpGroup table.
 */
public interface XdpGroupDao extends GenericDao<XdpGroup, Long> {
	
    /**
     * Gets a list of xdpGroupDocTypes ordered by the id desc.
     *
     * @return List populated list of xdpGroupDocTypes
     */
    List<XdpGroup> getXdpGroups();

    /**
     * Gets a list of enabled xdpGroupDocTypes ordered by the id desc.
     *
     * @return List populated list of xdpGroupDocTypes
     */
    List<XdpGroup> getXdpGroupsEnabled();
	


}