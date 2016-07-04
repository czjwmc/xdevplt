package com.xj.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xj.dao.XdpGroupDao;
import com.xj.model.XdpGroup;

@Repository("xdpGroupDao")
public class XdpGroupDaoHibernate extends GenericDaoHibernate<XdpGroup, Long> implements XdpGroupDao {

    public XdpGroupDaoHibernate() {
        super(XdpGroup.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<XdpGroup> getXdpGroups() {
    	Session session = getSessionFactory().getCurrentSession();
    	return session.createQuery("from XdpGroup a  order by a.id desc").list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<XdpGroup> getXdpGroupsEnabled() {
    	Session session = getSessionFactory().getCurrentSession();
    	return session.createQuery("from XdpGroup a where a.enabled=TRUE  order by a.id desc").list();
    }

   
}
