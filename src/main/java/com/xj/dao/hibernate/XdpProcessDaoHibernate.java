package com.xj.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xj.dao.XdpProcessDao;
import com.xj.model.XdpProcess;

@Repository("xdpProcessDao")
public class XdpProcessDaoHibernate extends GenericDaoHibernate<XdpProcess, Long> implements XdpProcessDao {

    public XdpProcessDaoHibernate() {
        super(XdpProcess.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<XdpProcess> getXdpProcessesEnabled() {
    	Session session = getSessionFactory().getCurrentSession();
    	return session.createQuery("from XdpProcess a where a.enabled=TRUE  order by a.id desc").list();
    }

}
