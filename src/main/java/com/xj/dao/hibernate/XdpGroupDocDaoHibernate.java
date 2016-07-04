package com.xj.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xj.dao.XdpGroupDocDao;
import com.xj.model.XdpGroupDoc;
import com.xj.model.XdpGroupDocType;

@Repository("xdpGroupDocDao")
public class XdpGroupDocDaoHibernate extends GenericDaoHibernate<XdpGroupDoc, Long> implements XdpGroupDocDao {

    public XdpGroupDocDaoHibernate() {
        super(XdpGroupDoc.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<XdpGroupDocType> getXdpGroupDocTypes() {
    	Session session = getSessionFactory().getCurrentSession();
    	return session.createQuery("from XdpGroupDocType a order by a.id desc").list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<XdpGroupDocType> getXdpGroupDocTypesEnabled() {
    	Session session = getSessionFactory().getCurrentSession();
    	return session.createQuery("from XdpGroupDocType a where a.enabled=TRUE order by a.id desc").list();
    }
    
}
