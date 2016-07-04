package com.xj.dao;

import java.util.List;

import com.xj.model.XdpGroupDocType;

/**
 * An interface that provides a data management interface to the XdpGroupDocType table.
 */
public interface XdpGroupDocTypeDao extends GenericDao<XdpGroupDocType, Long> {
	
    /**
     * Gets a list of xdpGroupDocTypes ordered by the id desc.
     *
     * @return List populated list of xdpGroupDocTypes
     */
    List<XdpGroupDocType> getXdpGroupDocTypes();
    
    List<XdpGroupDocType> getXdpGroupDocTypes(String xdpGroupDocTypeId);


}