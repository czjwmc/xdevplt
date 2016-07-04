package com.xj.dao;

import com.xj.model.Role;
import com.xj.model.XdpGroup;

import java.util.List;

/**
 * Lookup Data Access Object (GenericDao) interface.  This is used to lookup values in
 * the database (i.e. for drop-downs).
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface LookupDao {
    //~ Methods ================================================================

    /**
     * Returns all Roles ordered by name
     * @return populated list of roles
     */
    List<Role> getRoles();
    
    //add by wmc 2013/9/22
    /**
     * Returns all Roles ordered by name
     * @return populated list of roles
     */
    List<XdpGroup> getXdpGroups();
}
